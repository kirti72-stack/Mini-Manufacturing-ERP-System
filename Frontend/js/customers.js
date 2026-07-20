const role = localStorage.getItem("role");
const CUSTOMER_URL = BASE_URL + "/customers";

// ✅ GLOBAL VARIABLE (TOP)
var editId = null; // use var to avoid initialization issue

document.addEventListener("DOMContentLoaded", function () {
    if (role === "STAFF") {
    document.getElementById("submitBtn").style.display = "none";
}
    loadCustomers();
});


// LOAD
async function loadCustomers() {

     const data = await fetchWithAuth("customers");

   // console.log("Sending:", { name, email, address, phone }); // debug

    const table = document.querySelector("#customerTable tbody");
    table.innerHTML = "";

    data.forEach(c => {
        const row = `
            <tr>
                <td>${c.id}</td>
                <td>${c.name}</td>
                <td>${c.email}</td>
                <td>${c.address}</td>
                <td>${c.phone}</td>
                <td>
                   ${role !== "STAFF" ? `<button onclick="editCustomer(${c.id}, '${c.name}', '${c.email}', '${c.address}', '${c.phone}')">Edit</button>` : ""}
                   ${role === "ADMIN" ? `<button onclick="deleteCustomer(${c.id})">Delete</button>` : ""}
                </td>
            </tr>
        `;
        table.innerHTML += row;
    });
}

// ADD / UPDATE
async function addCustomer() {

    if (role === "STAFF") {
    alert("Access Denied");
    return;
}
    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const address = document.getElementById("address").value.trim();
    const phone = document.getElementById("phone").value.trim();
   
    const method = editId ? "PUT" : "POST";
    const url = editId ? `${CUSTOMER_URL}/${editId}` : CUSTOMER_URL;

    if (!name || !email || !address || !phone) {
    alert("All fields required");
    return;
}
// Email validation
if (!email.includes("@")) {
    alert("Enter valid email");
    return;
}
// phone validation
if (!/^[0-9]{10}$/.test(phone)) {
    alert("Phone must be exactly 10 digits");
    return;
}

const body = {
        name,
        email,
        address,
        phone
    };

    try {
    await fetchWithAuth(
    editId ? `customers/${editId}` : "customers",
    editId ? "PUT" : "POST",
    body
);

    alert(editId ? "Customer updated!" : "Customer added!");

    editId = null;
    loadCustomers();

} catch (error) {
    alert("Error saving customer!");
}

}    


// EDIT
function editCustomer(id, name, email, address, phone) {
    document.getElementById("name").value = name;
    document.getElementById("email").value = email;
    document.getElementById("address").value = address;
    document.getElementById("phone").value = phone;

    editId = id;
}

// DELETE
async function deleteCustomer(id) {
    if (role !== "ADMIN") {
    alert("Only admin can delete");
    return;
}

    if (!confirm("Delete this customer?")) return;

    try {
        await fetchWithAuth(`customers/${id}`, "DELETE");

        alert("Customer deleted!");
        loadCustomers();

    } catch (error) {
        console.error("DELETE ERROR:", error);
        alert("Error deleting customer!");
    }
}