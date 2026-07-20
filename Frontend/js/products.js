let editId = null;

// 🔐 ROLE
const role = localStorage.getItem("role");

// 🔐 AUTH FETCH FUNCTION (UPDATED)
async function fetchWithAuth(endpoint, method = "GET", data = null) {

    const options = {
        method: method,
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token"),
            "role": localStorage.getItem("role"),
            "username": localStorage.getItem("username")
        }
    };

    if (data) {
        options.body = JSON.stringify(data);
    }

    const response = await fetch(`${BASE_URL}/${endpoint}`, options);

    if (!response.ok) {
        throw new Error("API Error");
    }

    if (method === "DELETE") return;

    return response.json();
}


// 🔐 INIT
document.addEventListener("DOMContentLoaded", () => {

    if (!localStorage.getItem("token")) {
        alert("Please login first!");
        window.location.href = "../login.html";
        return;
    }

    // 🔥 STAFF → hide add button
    if (role === "STAFF") {
        document.getElementById("addBtn").style.display = "none";
    }

    loadProducts();
});


// ✅ LOAD PRODUCTS
async function loadProducts() {
    try {
        const data = await fetchWithAuth("products");

        const table = document.querySelector("#productTable tbody");
        table.innerHTML = "";

        data.forEach(p => {

            const row = `
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.description}</td>
                    <td>${p.price}</td>
                    <td>${p.quantity}</td>
                    <td>
                        ${
                            role !== "STAFF"
                            ? `<button onclick="editProduct(${p.id}, '${p.name}', '${p.description}', ${p.price}, ${p.quantity})">Edit</button>`
                            : ""
                        }

                        ${
                            role === "ADMIN"
                            ? `<button onclick="deleteProduct(${p.id})">Delete</button>`
                            : ""
                        }
                    </td>
                </tr>
            `;

            table.innerHTML += row;
        });

    } catch (error) {
        alert("Error loading products!");
    }
}


// ✅ ADD / UPDATE PRODUCT
async function addProduct() {

    if (role === "STAFF") {
        alert("Access Denied ");
        return;
    }

    const name = document.getElementById("name").value.trim();
    const description = document.getElementById("description").value.trim();
    const price = document.getElementById("price").value.trim();
    const quantity = document.getElementById("quantity").value.trim();

    // 🔥 VALIDATION
    if (!name || !description || !price || !quantity) {
        alert("All fields are required");
        return;
    }

    if (!/^[A-Za-z ]+$/.test(name)) {
        alert("Product name must contain only letters");
        return;
    }

    if (!/^[A-Za-z0-9 ]+$/.test(description)) {
        alert("Description must not contain special characters");
        return;
    }

    if (price <= 0) {
        alert("Price must be greater than 0");
        return;
    }

    if (quantity < 0) {
        alert("Quantity cannot be negative");
        return;
    }

    const method = editId ? "PUT" : "POST";
    const endpoint = editId ? `products/${editId}` : "products";

    try {

        await fetchWithAuth(endpoint, method, {
            name,
            description,
            price: parseFloat(price),
            quantity: parseInt(quantity)
        });

        alert(editId ? "Product updated!" : "Product added!");

        editId = null;

        // RESET FORM
        document.getElementById("name").value = "";
        document.getElementById("description").value = "";
        document.getElementById("price").value = "";
        document.getElementById("quantity").value = "";

        loadProducts();

    } catch (error) {
        alert("Error saving product!");
    }
}


// ✅ DELETE PRODUCT
async function deleteProduct(id) {

    if (role !== "ADMIN") {
        alert("Only admin can delete ");
        return;
    }

    if (!confirm("Are you sure you want to delete this product?")) return;

    try {

        await fetchWithAuth(`products/${id}`, "DELETE");

        alert("Product deactivated!");
        loadProducts();

    } catch (error) {
        alert("Error deleting product!");
    }
}


// ✅ EDIT PRODUCT
function editProduct(id, name, description, price, quantity) {

    if (role === "STAFF") {
        alert("Access Denied ");
        return;
    }

    document.getElementById("name").value = name;
    document.getElementById("description").value = description;
    document.getElementById("price").value = price;
    document.getElementById("quantity").value = quantity;

    editId = id;
}


// 🌐 GLOBAL EXPORTS
window.addProduct = addProduct;
window.deleteProduct = deleteProduct;
window.editProduct = editProduct;