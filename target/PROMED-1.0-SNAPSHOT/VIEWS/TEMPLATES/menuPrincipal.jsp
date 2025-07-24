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
                <span class="navbar-brand mb-0 h1">.   Bienvenido ${usuario.nomb_Us} ${usuario.ape_Us}  <i class="fas fa-check"></i> </span>

                <div class="dropdown">
                    <button class="btn btn-purple dropdown-toggle btn-outline-light" type="button" id="dropdown" data-bs-toggle="dropdown" aria-expanded="false">
                    </button>
                    <ul class="dropdown-menu text-center">
                        <li>
                            <a class="dropdown-item" href="#"><img src="PAGES/img/usuario.png" alt="Usuario" width="60"></a>
                        </li>
                        <li><a class="dropdown-item">Opción 1</a></li>
                        <li><a class="dropdown-item">Opción 2</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li>
                            <form action="Validar" method="POST">
                                <button name="accion" value="Salir" class="dropdown-item">Salir</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="m-4" style="height: 630px;">

            <iframe name="myFrame"  style="height: 100%; width: 100%">



            </iframe>
        </div>

        <!-- Botón para mostrar/ocultar la barra lateral -->
        <button class="sidebar-toggle" id="sidebarToggle">☰</button>

        <!-- Barra lateral -->
        <div class="sidebar" id="sidebar">
            <ul>

                <li>
                    <a href="#empresaSubmenu" data-bs-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                        <i class="fas fa-user-cog"></i> ADMINISTRACIÓN
                    </a>
                    <ul class="collapse list-unstyled" id="empresaSubmenu">
                        <li><a href="Controlador?menu=Clientes&accion=Listar" target="myFrame"><i class="fas fa-users"></i> Clientes</a></li>
                        <li><a href="Controlador?menu=Empleados&accion=Listar" target="myFrame"><i class="fas fa-user-plus"></i> Empleados</a></li>
                        <!--<li><a href="Controlador?menu=Asistencia&accion=GENERARTICKET" target="myFrame"><i class="fas fa-calendar-check"></i> Asistencia listado</a></li>
  -->   
  <li><a href="Controlador?menu=Empleos&accion=Listar" target="myFrame"><i class="fas fa-user-tie"></i> Empleo</a></li>
                        <li><a href="Controlador?menu=Empresa&accion=Listar" target="myFrame"><i class="fas fa-building"></i> Empresa</a></li> 
                        <li><a href="Controlador?menu=Planes&accion=Listar" target="myFrame"><i class="fas fa-layer-group"></i> Planes</a></li>
                          <li><a href="Controlador?menu=Incidencias&accion=Listar" target="myFrame"><i class="fas fa-map-marked-alt"></i> Incidencias</a></li>
                          <li><a href="Controlador?menu=Zonas&accion=Listar" target="myFrame"><i class="fas fa-map-marked-alt"></i> Zonas</a></li>

 -->                    </ul>
                </li>

              


                </li>




                <li><a href="Controlador?menu=Reportes&accion=Listar" target="myFrame"><i class="fas fa-file-alt"></i> Reportes</a></li>
                <li><a href="Controlador?menu=Seguridad&accion=Listar" target="myFrame"><i class="fas fa-user-cog"></i>Seguridad</a></li>



            </ul>
        </div>

        <!-- Scripts de Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="VIEWS/SCRIPTS/menu.js"></script>

    </body>
</html>
