const PURCHASE_URL = BASE_URL + "/purchases";

const role = localStorage.getItem("role");

// 🔐 PAGE LOAD
document.addEventListener("DOMContentLoaded", () => {

    if (!localStorage.getItem("token")) {
        alert("Please login first!");
        window.location.href = "../login.html";
        return;
    }

    // 🔥 STAFF → hide add button
    if (role === "STAFF") {
        document.querySelector("button").style.display = "none";
    }

    loadPurchases();
    loadProductsDropdown();
    loadSuppliersDropdown();
});


// ✅ LOAD PURCHASES
async function loadPurchases() {

    try {
        const data = await fetchWithAuth("purchases");

        const table = document.getElementById("purchaseTable");
        table.innerHTML = "";

        data.forEach(p => {
            table.innerHTML += `
                <tr>
                    <td>${p.id}</td>
                    <td>${p.product.id}</td>
                    <td>${p.supplier.id}</td>
                    <td>${p.quantity}</td>
                    <td>${p.price}</td>
                    <td>
                        ${role === "ADMIN" ? `<button onclick="cancelPurchase(${p.id})">Cancel</button>` : ""}
                    </td>
                </tr>
            `;
        });

    } catch (error) {
        alert("Error loading purchases!");
    }
}


// ✅ LOAD PRODUCTS
async function loadProductsDropdown() {

    try {
        const data = await fetchWithAuth("products");

        const dropdown = document.getElementById("productId");
        dropdown.innerHTML = '<option value="">Select Product</option>';

        data.forEach(p => {
            dropdown.innerHTML += `<option value="${p.id}">${p.name}</option>`;
        });

    } catch (error) {
        alert("Error loading products!");
    }
}


// ✅ LOAD SUPPLIERS
async function loadSuppliersDropdown() {

    try {
        const data = await fetchWithAuth("suppliers");

        const dropdown = document.getElementById("supplierId");
        dropdown.innerHTML = '<option value="">Select Supplier</option>';

        data.forEach(s => {
            dropdown.innerHTML += `<option value="${s.id}">${s.name}</option>`;
        });

    } catch (error) {
        alert("Error loading suppliers!");
    }
}


// ✅ ADD PURCHASE
async function addPurchase() {

    // 🔥 ROLE PROTECTION
    if (role === "STAFF") {
        alert("Access Denied");
        return;
    }

    const productId = document.getElementById("productId").value;
    const supplierId = document.getElementById("supplierId").value;
    const quantity = document.getElementById("quantity").value;
    const price = document.getElementById("price").value;

    // VALIDATION
    if (!productId || !supplierId || !quantity || !price) {
        alert("All fields are required!");
        return;
    }

    if (quantity <= 0 || price <= 0) {
        alert("Quantity and Price must be greater than 0");
        return;
    }

    try {

        await fetchWithAuth("purchases", "POST", {
            product: { id: parseInt(productId) },
            supplier: { id: parseInt(supplierId) },
            quantity: parseInt(quantity),
            price: parseFloat(price)
        });

        alert("Purchase added successfully!");

        // CLEAR
        document.getElementById("productId").value = "";
        document.getElementById("supplierId").value = "";
        document.getElementById("quantity").value = "";
        document.getElementById("price").value = "";

        loadPurchases();

    } catch (error) {
        alert("Error saving purchase!");
    }
}


// ✅ CANCEL PURCHASE
async function cancelPurchase(id) {

    if (role !== "ADMIN") {
        alert("Only admin can cancel");
        return;
    }

    if (!confirm("Cancel this purchase?")) return;

    try {

        await fetchWithAuth(`purchases/cancel/${id}`, "PUT");

        alert("Purchase cancelled!");
        loadPurchases();

    } catch (error) {
        alert("Error cancelling purchase!");
    }
}