window.editSupplierId = null;

// 🔐 ROLE CHECK
const role = localStorage.getItem("role");

document.addEventListener("DOMContentLoaded", () => {

    if (!localStorage.getItem("token")) {
        alert("Please login first!");
        window.location.href = "../login.html";
        return;
    }

    // 🔥 STAFF → hide add button
    if (role === "STAFF") {
        document.getElementById("submitBtn").style.display = "none";
    }

    loadSuppliers();
});


// ✅ LOAD
async function loadSuppliers() {
    try {
        const data = await fetchWithAuth("suppliers");

       const table = document.querySelector("#supplierTable");
       table.innerHTML = "";

        data.forEach(s => {
            table.innerHTML += `
                <tr>
                    <td>${s.id}</td>
                    <td>${s.name}</td>
                    <td>${s.email}</td>
                    <td>${s.phone}</td>
                    <td>${s.company}</td>
                    <td>${s.address}</td>
                   <td>
                       ${role !== "STAFF" ? `<button onclick='editSupplier(${s.id}, "${s.name}", "${s.email}", "${s.phone}", "${s.company}", "${s.address}")'>Edit</button>` : ""}
                       ${role === "ADMIN" ? `<button onclick="deleteSupplier(${s.id})">Delete</button>` : ""}
                    </td>
                </tr>
            `;
        });

    } catch (error) {
        alert("Error loading suppliers!");
    }
}

// Edit 
function editSupplier(id, name, email, phone, company, address) {

    // 🔥 Fill form
    document.getElementById("name").value = name;
    document.getElementById("email").value = email;
    document.getElementById("phone").value = phone;
    document.getElementById("company").value = company;
    document.getElementById("address").value = address;

    // 🔥 Store ID for update
    window.editSupplierId = id;

    // 🔥 Change button text
    document.getElementById("submitBtn").innerText = "Update Supplier";
}

// ✅ ADD / UPDATE SUPPLIER

async function addSupplier() {

    if (role === "STAFF") {
    alert("Access Denied ");
    return;
}

    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const phone = document.getElementById("phone").value.trim();
    const company = document.getElementById("company").value.trim();
    const address = document.getElementById("address").value.trim();

    // 🔥 VALIDATION
    if (!name || !email || !phone || !company || !address) {
        alert("All fields are required!");
        return;
    }

    if (!/^[A-Za-z ]+$/.test(name)) {
        alert("Name must contain only letters");
        return;
    }

    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        alert("Invalid email format");
        return;
    }

    if (!/^[0-9]{10}$/.test(phone)) {
        alert("Phone must be 10 digits");
        return;
    }

    if (!/^[A-Za-z0-9 ]+$/.test(company)) {
        alert("Company must not contain special characters");
        return;
    }

    if (!/^[A-Za-z0-9, ]+$/.test(address)) {
        alert("Address must not contain special characters");
        return;
    }

    try {

        // 🔥 UPDATE MODE
        if (window.editSupplierId) {
            console.log("Updating supplier:", window.editSupplierId);
            await fetchWithAuth(`suppliers/${window.editSupplierId}`, "PUT", {
                name,
                email,
                phone,
                company,
                address
            });

            alert("Supplier updated!");
            window.editSupplierId = null;
            document.getElementById("submitBtn").innerText = "Add Supplier";
        } 
        
        // 🔥 ADD MODE
        else {

            await fetchWithAuth("suppliers", "POST", {
                name,
                email,
                phone,
                company,
                address
            });

            alert("Supplier added!");
        }

        // ✅ CLEAR FORM
        document.getElementById("name").value = "";
        document.getElementById("email").value = "";
        document.getElementById("phone").value = "";
        document.getElementById("company").value = "";
        document.getElementById("address").value = "";

        loadSuppliers();

    } catch (error) {
        console.error("Error:", error);
        alert("Error saving supplier!");
    }
}

// ✅ DELETE

async function deleteSupplier(id) {

    if (role !== "ADMIN") {
    alert("Only admin can delete ");
    return;
}

    console.log("Deleting supplier ID:", id); // 🔥 DEBUG

    if (!confirm("Delete this supplier?")) return;

    try {

        const res = await fetchWithAuth(`suppliers/${id}`, "DELETE");

        console.log("Delete response:", res); // 🔥 DEBUG

        alert("Supplier deactivated!");
        loadSuppliers();

    } catch (error) {
        console.error("Delete error:", error); // 🔥 DEBUG
        alert("Error deleting supplier!");
    }
}


// 🔥 GLOBAL ACCESS
window.addSupplier = addSupplier;
window.deleteSupplier = deleteSupplier;
window.editSupplier = editSupplier;