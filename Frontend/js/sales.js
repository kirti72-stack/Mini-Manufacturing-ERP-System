const SALES_URL = BASE_URL + "/sales";
const PRODUCT_URL = BASE_URL + "/products";
const CUSTOMER_URL = BASE_URL + "/customers";
const INVENTORY_URL = BASE_URL + "/inventory";

const role = localStorage.getItem("role");

// 🔐 PAGE LOAD
document.addEventListener("DOMContentLoaded", () => {

    // ❌ STAFF cannot add sale → hide button
    if (role === "STAFF") {
        document.querySelector("button").style.display = "none";
    }

    loadSales();
    loadDropdowns();

    // 🔥 AUTO-FILL PRICE
    document.getElementById("productId").addEventListener("change", async function () {

        const productId = this.value;
        if (!productId) return;

        try {
            const products = await fetchWithAuth("products");
            const product = products.find(p => p.id == productId);

            if (product) {
                document.getElementById("price").value = product.price;
            }

        } catch (error) {
            console.error(error);
        }
    });
});


// ✅ LOAD SALES
async function loadSales() {
    try {
        const data = await fetchWithAuth("sales");

        const table = document.getElementById("salesTable");
        table.innerHTML = "";

        data.forEach(s => {
            table.innerHTML += `
                <tr>
                    <td>${s.id}</td>
                    <td>${s.product ? s.product.name : "-"}</td>
                    <td>${s.customer ? s.customer.name : "-"}</td>
                    <td>${s.quantity}</td>
                    <td>${s.price}</td>
                </tr>
            `;
        });

    } catch (error) {
        console.error(error);
        alert("Error loading sales!");
    }
}


// ✅ ADD SALE
async function addSale() {

    // 🔒 RBAC
    if (role === "STAFF") {
        alert("Access Denied");
        return;
    }

    const productId = document.getElementById("productId").value;
    const customerId = document.getElementById("customerId").value;
    const quantity = parseInt(document.getElementById("quantity").value);
    const price = document.getElementById("price").value;

    // VALIDATION
    if (!productId || !customerId || !quantity || !price) {
        alert("All fields are required!");
        return;
    }

    if (quantity <= 0 || price <= 0) {
        alert("Quantity and Price must be greater than 0");
        return;
    }

    try {
        // 🔥 CHECK INVENTORY
        const inventory = await fetchWithAuth("inventory");

        const item = inventory.find(i => i.product.id == productId);

        if (!item) {
            alert("Product not found in inventory!");
            return;
        }

        if (item.quantity < quantity) {
            alert(`Only ${item.quantity} items available in stock!`);
            return;
        }

        // 🔥 SAVE SALE (ONLY ONE API CALL)
        await fetchWithAuth("sales", "POST", {
            product: { id: parseInt(productId) },
            customer: { id: parseInt(customerId) },
            quantity: quantity,
            price: parseFloat(price)
        });

        alert("Sale completed successfully!");

        // CLEAR FORM
        document.getElementById("productId").value = "";
        document.getElementById("customerId").value = "";
        document.getElementById("quantity").value = "";
        document.getElementById("price").value = "";

        loadSales();

    } catch (error) {
        console.error(error);
        alert("Error saving sale!");
    }
}


// ✅ LOAD DROPDOWNS
async function loadDropdowns() {
    try {
        const products = await fetchWithAuth("products");
        const customers = await fetchWithAuth("customers");

        const productSelect = document.getElementById("productId");
        productSelect.innerHTML = '<option value="">Select Product</option>';

        products.forEach(p => {
            productSelect.innerHTML += `<option value="${p.id}">${p.name}</option>`;
        });

        const customerSelect = document.getElementById("customerId");
        customerSelect.innerHTML = '<option value="">Select Customer</option>';

        customers.forEach(c => {
            customerSelect.innerHTML += `<option value="${c.id}">${c.name}</option>`;
        });

    } catch (error) {
        console.error(error);
        alert("Error loading dropdowns!");
    }
}


// 🔥 GLOBAL ACCESS
window.addSale = addSale;