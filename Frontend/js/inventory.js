const INVENTORY_URL = BASE_URL + "/inventory";

let editId = null;

// LOAD DATA
/*async function loadInventory() {
    try {
        const res = await fetch(INVENTORY_URL);

        if (!res.ok) throw new Error();

        const data = await res.json();

        const table = document.getElementById("inventoryTable");
        table.innerHTML = "";

        data.forEach(inv => {

            const productId = inv.product ? inv.product.id : 0;

            table.innerHTML += `
                <tr>
                    <td>${inv.id}</td>
                    <td>${inv.product ? inv.product.name : "-"}</td>
                    <td>${inv.quantity}</td>
                    <td>${inv.warehouseLocation || "-"}</td>
                 </tr>
            ;
            
        });
 }*/
   async function loadInventory() {
    try {
        const data = await fetchWithAuth("inventory");

        console.log("Inventory Data:", data);

        const table = document.getElementById("inventoryTable");
        table.innerHTML = "";

        data.forEach(i => {
            table.innerHTML += `
                <tr>
                    <td>${i.id}</td>
                    <td>${i.product ? i.product.name : "N/A"}</td>
                    <td>${i.quantity}</td>
                    <td>${i.warehouseLocation || "-"}</td>
                </tr>
            `;
        });

    } catch (error) {
        console.error("Inventory Load Error:", error);
        alert("Error loading inventory!");
    }
}
// AUTO LOAD
window.onload = () => {
    loadInventory();
    //loadProductDropdown();  ✅ ADD THIS LINE
};