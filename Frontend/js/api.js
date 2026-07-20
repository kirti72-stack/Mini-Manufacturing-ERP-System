const BASE_URL = "http://localhost:8081";

async function fetchWithAuth(url, method = "GET", body = null) {

    const response = await fetch(`${BASE_URL}/${url}`, {
        method: method,
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token"),
            "role": localStorage.getItem("role"),
            "username": localStorage.getItem("username")
        },
        body: body ? JSON.stringify(body) : null
    });

    // ✅ HANDLE DELETE (VERY IMPORTANT)
    if (response.status === 204) {
        return null;
    }

    // ❌ THROW ONLY IF REAL ERROR
    if (!response.ok) {
        const errorText = await response.text();
        console.error("Backend Error:", errorText);
        throw new Error(errorText);
    }

    // ✅ SAFE PARSE
    const text = await response.text();
    return text ? JSON.parse(text) : null;
}