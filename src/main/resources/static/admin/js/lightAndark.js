
    // script.js
    const themeToggle = document.getElementById("themeToggle");

    themeToggle.addEventListener("change", () => {
    if (themeToggle.checked) {
    document.body.classList.remove("light-mode");
    document.body.classList.add("dark-mode");
} else {
    document.body.classList.remove("dark-mode");
    document.body.classList.add("light-mode");
}
});

