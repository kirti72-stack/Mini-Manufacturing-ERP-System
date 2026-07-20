async function loadDashboard() {

    try {

        /* =========================
           SUMMARY CARDS
        ========================== */

        document.getElementById("totalSales").innerText =
            await fetchWithAuth("dashboard/total-sales");

        document.getElementById("totalPurchases").innerText =
            await fetchWithAuth("dashboard/total-purchases");

        document.getElementById("totalProducts").innerText =
            await fetchWithAuth("dashboard/total-products");

        document.getElementById("totalCustomers").innerText =
            await fetchWithAuth("dashboard/total-customers");

        const revenue =
            await fetchWithAuth("dashboard/revenue");

        document.getElementById("revenue").innerText =
            Number(revenue || 0).toLocaleString();



        /* =========================
           LOW STOCK PRODUCTS
        ========================== */

        const lowStock =
            await fetchWithAuth("dashboard/low-stock");

        const lowStockTable =
            document.getElementById("lowStockTable");

        lowStockTable.innerHTML = "";

        lowStock.forEach(item => {

            let statusClass = "";
            let statusText = "";

            if (item.quantity === 0) {
                statusClass = "out";
                statusText = "Out of Stock";
            } else {
                statusClass = "low";
                statusText = "Low Stock";
            }

            lowStockTable.innerHTML += `
                <tr>
                    <td>${item.product.name}</td>

                    <td>${item.quantity}</td>

                    <td>
                        <span class="${statusClass}">
                            ${statusText}
                        </span>
                    </td>
                </tr>
            `;
        });



        /* =========================
           TOP SELLING PRODUCTS
        ========================== */

        const topProducts =
            await fetchWithAuth("dashboard/top-products");

        const topProductsTable =
            document.getElementById("topProductsTable");

        topProductsTable.innerHTML = "";

        let maxSold = 0;

        topProducts.forEach(product => {

            if (product[1] > maxSold) {
                maxSold = product[1];
            }
        });

        topProducts.forEach(product => {

            const percentage =
                Math.round((product[1] / maxSold) * 100);

            topProductsTable.innerHTML += `
                <tr>

                    <td>${product[0]}</td>

                    <td>${product[1]}</td>

                    <td>
                        <div style="display:flex; align-items:center; gap:10px;">

                            <div class="progress-bar">

                                <div class="progress"
                                     style="width:${percentage}%">
                                </div>

                            </div>

                            <span>${percentage}%</span>

                        </div>
                    </td>

                </tr>
            `;
        });

    }

    catch (error) {

        console.error("Dashboard Error:", error);
        alert("Error loading dashboard");
    }
}

window.onload = function () {

    const role = localStorage.getItem("role");

    console.log("Current Role:", role);

    if (role === "STAFF") {

        const auditMenu =
            document.getElementById("auditMenu");

        if (auditMenu) {
            auditMenu.style.display = "none";
        }
    }

    loadDashboard();
};