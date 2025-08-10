<%-- 
    Document   : menuPrincipal
    Created on : 22/07/2025, 23:56:45
    Author     : joans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MENU PRINCIPAL</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="VIEWS/STYLES/styleMenuPrincipal.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>
    <body>

  <!-- Barra de navegación -->
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1">.     Bienvenido ${usuario.nomb_Us} ${usuario.ape_Us} <i class="fas fa-check"></i></span>
        
        <!-- Botón Menú -->
        <div class="d-flex align-items-center">
            <form action="Controlador" method="POST" style="margin: 0;">
                <button name="menu" value="Principal" class="btn btn-outline-light me-3">
                    <i class="fas fa-home me-2"></i>Menú
                </button>
            </form>
            
            <div class="dropdown">
                <button class="btn btn-purple dropdown-toggle btn-outline-light" type="button" id="dropdown" data-bs-toggle="dropdown" aria-expanded="false">
                    <i class="fas fa-user"></i>
                </button>
                <ul class="dropdown-menu dropdown-menu-end">
                    <li class="text-center">
                        <img src="PAGES/img/usuario.png" alt="Usuario" width="60" class="rounded-circle my-2">
                    </li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="#"><i class="fas fa-user-edit me-2"></i>Mi Perfil</a></li>
                    <li><a class="dropdown-item" href="#"><i class="fas fa-cog me-2"></i>Configuración</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li>
                         <form action="Controlador" method="POST" style="margin: 0;">
                            <button name="menu" value="exis" class="dropdown-item text-danger">
                                <i class="fas fa-sign-out-alt me-2"></i>Cerrar Sesión
                            </button>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</nav>
      

        <!-- Iframe con dashboard por defecto -->
        <div class="m-4" style="height: 630px;">
            <iframe name="myFrame" src="VIEWS/TEMPLATES/dashboard.jsp" style="height: 100%; width: 100%; border: none; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1);">
            </iframe>
        </div>

        <!-- Botón para mostrar/ocultar la barra lateral -->
        <button class="sidebar-toggle" id="sidebarToggle">☰</button>

        <!-- Barra lateral -->
        <div class="sidebar" id="sidebar">
            <ul>
                <!-- Opción para volver al dashboard 
                <li><a href="VIEWS/TEMPLATES/dashboard.jsp" target="myFrame"><i class="fas fa-chart-bar"></i> Dashboard</a></li>-->
                
                <li>
                    <a href="#empresaSubmenu" data-bs-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                        <i class="fas fa-user-cog"></i> ADMINISTRACIÓN
                    </a>
                    <ul class="collapse list-unstyled" id="empresaSubmenu">
                        <li><a href="Controlador?menu=Clientes&accion=Listar" target="myFrame"><i class="fas fa-users"></i> Clientes</a></li>
                        <li><a href="Controlador?menu=Empleados&accion=Listar" target="myFrame"><i class="fas fa-user-plus"></i> Empleados</a></li>
                        <li><a href="Controlador?menu=Empleos&accion=Listar" target="myFrame"><i class="fas fa-user-tie"></i> Empleo</a></li>
                        <li><a href="Controlador?menu=Empresa&accion=Listar" target="myFrame"><i class="fas fa-building"></i> Empresa</a></li> 
                        <li><a href="Controlador?menu=Planes&accion=Listar" target="myFrame"><i class="fas fa-layer-group"></i> Planes</a></li>
                        <li><a href="Controlador?menu=Incidencias&accion=Listar" target="myFrame"><i class="fas fa-exclamation-triangle"></i> Incidencias</a></li>
                        <li><a href="Controlador?menu=Zonas&accion=Listar" target="myFrame"><i class="fas fa-map-marked-alt"></i> Zonas</a></li>
                    </ul>
                </li>

                <li><a href="Controlador?menu=Reportes&accion=Listar" target="myFrame"><i class="fas fa-file-alt"></i> Reportes</a></li>
                <li><a href="Controlador?menu=Seguridad&accion=Listar" target="myFrame"><i class="fas fa-user-cog"></i> Seguridad</a></li>
            </ul>
        </div>

        <!-- Scripts de Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="VIEWS/SCRIPTS/menu.js"></script>

    </body>
</html>