const AUDIT_URL = BASE_URL + "/audit"; // adjust if needed

async function loadAuditLogs() {
    try {
        const res = await fetch(AUDIT_URL);
        const data = await res.json();

        const table = document.getElementById("auditTable");
        table.innerHTML = "";

        data.forEach(log => {
            table.innerHTML += `
                <tr>
                    <td>${log.id}</td>
                    <td>${log.username}</td>
                    <td>${log.action}</td>
                    <td>${log.module}</td>
                    <td>${log.timestamp}</td>
                </tr>
            `;
        });

    } catch (error) {
        console.error(error);
        alert("Error loading audit logs!");
    }
}

window.onload = loadAuditLogs;