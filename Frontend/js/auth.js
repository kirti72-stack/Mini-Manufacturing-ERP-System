// Login function
async function login() {
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    let valid = true;

    document.getElementById("userError").textContent = "";
    document.getElementById("passError").textContent = "";

    if (!username) {
        document.getElementById("userError").textContent = "Username is required";
        valid = false;
    }

    if (!password) {
        document.getElementById("passError").textContent = "Password is required";
        valid = false;
    }

    if (!valid) return;

    try {
        const res = await fetch("http://localhost:8081/auth/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ username, password })
        });

        if (!res.ok) throw new Error();

        const data = await res.json();

        // 🔥 SAVE EVERYTHING
        localStorage.setItem("token", data.token);
        localStorage.setItem("role", data.role);
        localStorage.setItem("username", data.username);

        alert("Login successful");

        window.location.href = "dashboard.html";

    } catch {
        alert("Invalid username or password ");
    }
}

// ✅ ENTER KEY HANDLING (IMPORTANT)
document.addEventListener("DOMContentLoaded", () => {

    const usernameField = document.getElementById("username");
    const passwordField = document.getElementById("password");

    // Enter on username → move to password
    usernameField.addEventListener("keydown", function(event) {
        if (event.key === "Enter") {
            event.preventDefault();
            passwordField.focus();
        }
    });

    // Enter on password → login
    passwordField.addEventListener("keydown", function(event) {
        if (event.key === "Enter") {
            event.preventDefault();
            login();
        }
    });

});