// 🔥 LOAD PRODUCTS DROPDOWN
async function loadProducts() {
    try {
        const data = await fetchWithAuth("products");

        const dropdown = document.getElementById("productId");
        dropdown.innerHTML = '<option value="">Select Product</option>';

        data.forEach(p => {
            dropdown.innerHTML += `<option value="${p.id}">${p.name}</option>`;
        });

    } catch (error) {
        console.error("Error loading products:", error);
    }
}

// 🔥 LOAD STOCK HISTORY
async function loadHistory() {

    const productId = document.getElementById("productId").value;

    if (!productId) {
        alert("Please select a product");
        return;
    }

    try {
        const data = await fetchWithAuth(`inventory/history/${productId}`);

        console.log("Stock Movement:", data); // 🔥 DEBUG

        const table = document.getElementById("movementTable");
        table.innerHTML = "";

        data.forEach(m => {
            table.innerHTML += `
<tr>
    <td>${m.id}</td>
    <td>${m.product.name}</td>
    <td>
        <span class="${m.type === 'SALE' ? 'sale' : 'purchase'}">
            ${m.type}
        </span>
    </td>
    <td>${m.quantity}</td>
</tr>
`;
        });
    
    } catch (error) {
        console.error("Error loading movement:", error);
        alert("Error loading stock movement!");
    }
}

// 🚀 INIT
window.onload = () => {
    loadProducts();
};