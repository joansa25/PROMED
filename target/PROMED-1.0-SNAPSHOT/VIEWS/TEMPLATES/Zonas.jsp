<%-- 
    Document   : Zonas
    Created on : 19/09/2024, 21:39:43
    Author     : joans
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zonas</title>
        <!-- Agregar estilos de Bootstrap, Font Awesome y SweetAlert2 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <!-- Leaflet CSS para el mapa -->
        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />

        <link href="VIEWS/STYLES/EstilosModulos.css" rel="stylesheet">
        
        <style>
            .map-container {
                position: relative;
                border-radius: 10px;
                overflow: hidden;
                box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
                margin: 20px 0;
            }
            
            #map {
                height: 500px;
                width: 100%;
            }
            
            .map-toggle-btn {
                background: linear-gradient(45deg, #007bff, #0056b3);
                border: none;
                padding: 12px 24px;
                border-radius: 8px;
                color: white;
                font-weight: bold;
                transition: all 0.3s;
                box-shadow: 0 4px 8px rgba(0, 123, 255, 0.3);
            }
            
            .map-toggle-btn:hover {
                transform: translateY(-2px);
                box-shadow: 0 6px 12px rgba(0, 123, 255, 0.4);
                color: white;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5 content-container">
            <!-- Botón de Regresar -->
            <div class="d-flex justify-content-start mb-3">
                <button class="btn btn-secondary btn-regresar" onclick="regresarPagina()">
                    <i class="fas fa-arrow-left"></i> Regresar
                </button>
            </div>

            <!-- Título estilizado -->
            <h1 class="text-center mb-4" style="font-weight: bold; color: #1c3d5a;">Zonas</h1>
            
            <!-- Botones principales -->
            <div class="d-flex justify-content-between align-items-center mb-4">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#empleadoModal">
                    <i class="fas fa-plus"></i> Crear Zona
                </button>
                <button id="btnMostrarMapa" class="btn map-toggle-btn">
                    <i class="fas fa-map"></i> <span id="textoBoton">Mostrar Mapa</span>
                </button>
            </div>
            
            <!-- Inputs de búsqueda -->
            <div class="d-flex justify-content-center mb-4">
                <input type="text" id="searchInputCodigo" class="form-control me-2" 
                       placeholder="Buscar por código" style="max-width: 300px;">
                <input type="text" id="searchInputNombre" class="form-control" 
                       placeholder="Buscar por nombre" style="max-width: 300px;">
            </div>
     
            <!-- Dropdown para filtrar por estado -->
            <select id="filterEstado" class="form-control ms-2" style="max-width: 150px;">
                <option value="A">Activo (A)</option>
                <option value="N">Inactivo (N)</option>
                <option value="">Todos</option>
            </select>

            <hr>

            <!-- Tabla estilizada de datos de Zonas -->
            <div class="table-container">
                <table class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>CÓDIGO ZONA</th>
                            <th>NOMBRE</th>
                            <th>DESCRIPCIÓN</th>
                            <th>ESTADO</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody class="scrollable-tbody" id="zonasTable">
                        <c:forEach var="zona" items="${zonas}">
                            <tr>
                                <td>${zona.getId()}</td>
                                <td>${zona.getCOD_ZON()}</td>
                                <td>${zona.getCOD_NOMB()}</td>
                                <td>${zona.getCOD_DESC()}</td>
                                <td>${zona.getESTADO()}</td>
                                <td>
                                    <a class="btn btn-warning btn-sm" href="Controlador?menu=Zonas&accion=Editar&zonaCod=${zona.getCOD_ZON()}" title="Editar">
                                        <i class="fas fa-edit"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Sección del Mapa (inicialmente oculta) -->
            <div id="seccionMapa" style="display: none;">
                <h3 class="text-center mb-4" style="color: #1c3d5a;">
                    <i class="fas fa-map-marked-alt"></i> Mapa de Guatemala
                </h3>
                
                <!-- Botón para centrar mapa -->
                <div class="text-center mb-3">
                    <button onclick="centrarMapa()" class="btn btn-outline-primary">
                        <i class="fas fa-crosshairs"></i> Centrar Mapa en Guatemala
                    </button>
                </div>
                
                <!-- Contenedor del mapa -->
                <div class="map-container">
                    <div id="map"></div>
                </div>
                
                <!-- Información básica -->
                <div class="text-center mt-3">
                    <small class="text-muted">
                        <i class="fas fa-info-circle"></i> 
                        Haz clic en los marcadores para ver información de las ubicaciones
                    </small>
                </div>
            </div>

            <!-- Modal Crear Zona (simplificado) -->
            <div class="modal fade" id="empleadoModal" tabindex="-1" aria-labelledby="empleadoModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="empleadoModalLabel">CREAR NUEVA ZONA</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="Controlador?menu=Zonas" method="POST">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group mb-3">
                                            <label>CÓDIGO ZONA</label>
                                            <input type="text" name="txtCodigoZona" class="form-control" required>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label>NOMBRE</label>
                                            <input type="text" name="txtNombre" class="form-control" required>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label>DESCRIPCIÓN</label>
                                            <textarea name="txtDescripcion" class="form-control" rows="3"></textarea>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group mb-3">
                                            <label>DEPARTAMENTO</label>
                                            <select name="txtDepartamento" class="form-control">
                                                <option value="">Seleccionar...</option>
                                                <option value="Guatemala">Guatemala</option>
                                                <option value="Sacatepéquez">Sacatepéquez</option>
                                                <option value="Chimaltenango">Chimaltenango</option>
                                                <option value="Escuintla">Escuintla</option>
                                            </select>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label>ESTADO</label>
                                            <select name="txtEstado" class="form-control">
                                                <option value="A">Activo (A)</option>
                                                <option value="N">Inactivo (N)</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" name="accion" value="agregar" class="btn btn-info">
                                        <i class="fas fa-save"></i> Agregar Zona
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <!-- Scripts -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>

        <!-- JavaScript de SweetAlert -->
        <script>
            <% if (request.getAttribute("resultado") != null) {%>
            var resultado = "<%= request.getAttribute("resultado")%>";
            if (resultado === "1") {
                Swal.fire({
                    icon: 'success',
                    title: 'Zona agregada!',
                    text: 'La zona se ha agregado correctamente.'
                });
            } else if (resultado === "0") {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Hubo un problema al agregar la zona.'
                });
            }
            <% } %>

            <% if (request.getAttribute("resultadoUpdate") != null) {%>
            var resultadoUpdate = "<%= request.getAttribute("resultadoUpdate")%>";
            if (resultadoUpdate === "1") {
                Swal.fire({
                    icon: 'success',
                    title: 'Zona Actualizada!',
                    text: 'La zona se ha actualizado correctamente.'
                });
            } else if (resultadoUpdate === "0") {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Hubo un problema al actualizar la zona.'
                });
            }
            <% } %>
        </script>

        <!-- JavaScript del Mapa (SIMPLIFICADO) -->
        <script>
            var miMapa = null;
            var mapaVisible = false;

            function mostrarOcultarMapa() {
                console.log('Función mostrarOcultarMapa ejecutada');
                
                var seccionMapa = document.getElementById('seccionMapa');
                var textoBoton = document.getElementById('textoBoton');
                
                if (!mapaVisible) {
                    console.log('Mostrando mapa...');
                    seccionMapa.style.display = 'block';
                    textoBoton.textContent = 'Ocultar Mapa';
                    
                    setTimeout(function() {
                        inicializarMapa();
                    }, 100);
                    
                    mapaVisible = true;
                } else {
                    console.log('Ocultando mapa...');
                    seccionMapa.style.display = 'none';
                    textoBoton.textContent = 'Mostrar Mapa';
                    mapaVisible = false;
                }
            }

            function inicializarMapa() {
                console.log('Inicializando mapa...');
                
                if (miMapa === null) {
                    miMapa = L.map('map').setView([15.7835, -90.2308], 7);
                    
                    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                        attribution: 'Map data © OpenStreetMap contributors'
                    }).addTo(miMapa);
                    
                    // Agregar marcadores de ejemplo
                    L.marker([14.6349, -90.5069]).addTo(miMapa)
                        .bindPopup('<b>Ciudad de Guatemala</b><br>Capital de Guatemala');
                    
                    L.marker([14.5586, -90.7349]).addTo(miMapa)
                        .bindPopup('<b>Antigua Guatemala</b><br>Sacatepéquez');
                    
                    L.marker([14.8411, -91.5189]).addTo(miMapa)
                        .bindPopup('<b>Quetzaltenango</b><br>Xela');
                    
                    console.log('Mapa inicializado correctamente');
                } 
                
                setTimeout(function() {
                    if (miMapa) {
                        miMapa.invalidateSize();
                        console.log('Mapa redimensionado');
                    }
                }, 200);
            }

            function centrarMapa() {
                if (miMapa) {
                    miMapa.setView([15.7835, -90.2308], 7);
                    console.log('Mapa centrado');
                }
            }

            // Asignar evento al botón cuando la página esté lista
            window.onload = function() {
                console.log('Página cargada completamente');
                
                var botonMapa = document.getElementById('btnMostrarMapa');
                if (botonMapa) {
                    botonMapa.onclick = mostrarOcultarMapa;
                    console.log('Evento asignado al botón del mapa');
                } else {
                    console.error('No se encontró el botón del mapa');
                }
            };

            function regresarPagina() {
                window.history.back();
            }
        </script>

        <!-- Tu JavaScript existente para la búsqueda de tabla -->
        <script>
            $(document).ready(function () {
                var originalTableData = $('#zonasTable').html();

                $('#searchInputCodigo').on('keyup', function () {
                    var searchText = $(this).val();
                    if (searchText.trim() !== '') {
                        $.ajax({
                            type: 'GET',
                            url: 'Controlador?menu=Zonas&accion=BuscarPorCodigo',
                            data: {codigo: searchText},
                            success: function (response) {
                                $('#zonasTable').html(response);
                            },
                            error: function (xhr, status, error) {
                                console.error('Error en la búsqueda:', error);
                            }
                        });
                    } else {
                        $('#zonasTable').html(originalTableData);
                    }
                });

                $('#searchInputNombre').on('keyup', function () {
                    var searchText = $(this).val();
                    if (searchText.trim() !== '') {
                        $.ajax({
                            type: 'GET',
                            url: 'Controlador?menu=Zonas&accion=BuscarPorNombre',
                            data: {nombre: searchText},
                            success: function (response) {
                                $('#zonasTable').html(response);
                            },
                            error: function (xhr, status, error) {
                                console.error('Error en la búsqueda:', error);
                            }
                        });
                    } else {
                        $('#zonasTable').html(originalTableData);
                    }
                });

                $('#filterEstado').on('change', function () {
                    var estado = $(this).val();
                    if (estado !== '') {
                        $.ajax({
                            type: 'GET',
                            url: 'Controlador?menu=Zonas&accion=FiltrarPorEstado',
                            data: {estado: estado},
                            success: function (response) {
                                $('#zonasTable').html(response);
                            },
                            error: function (xhr, status, error) {
                                console.error('Error en el filtro:', error);
                            }
                        });
                    } else {
                        $('#zonasTable').html(originalTableData);
                    }
                });
            });
        </script>
    </body>
</html>