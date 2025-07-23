
    
const sidebar = document.getElementById('sidebar');
const sidebarToggle = document.getElementById('sidebarToggle');
const content = document.querySelector('.content');

sidebarToggle.addEventListener('click', () => {
    sidebar.classList.toggle('active');
    content.classList.toggle('active');
});


    function cerrarSesion() {
        // Redirigir al usuario a la página index
        window.location.href = "index.html";
    }
    

