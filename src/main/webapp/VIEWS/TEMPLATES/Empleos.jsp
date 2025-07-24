<%-- 
    Document   : Empleos
    Created on : 27/09/2024, 15:12:54
    Author     : joans
--%>



<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Asistencia</title>
        <!-- Agregar estilos de Bootstrap, Font Awesome y SweetAlert2 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link href="VIEWS/STYLES/EstilosModulos.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5 content-container">


            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#empleoModal">
                Crear Empleo
            </button>




        </div>

        <!-- Botón de Historial alineado a la derecha 
        <div class="btn-container btn-right">
            <button type="submit" class="btn btn-custom btn-lg">
                <i class="fas fa-history"></i> HISTORIAL
            </button>
        </div>-->

        <!-- Título estilizado -->
        <h1 class="text-center mb-4" style="font-weight: bold; color: #1c3d5a;">EMPLEOS</h1>

        <!-- Inputs de búsqueda estilizados -->
        <div class="d-flex justify-content-center mb-4">
            <input type="text" id="searchInputCodigo" class="form-control me-2" placeholder="Buscar por código" style="max-width: 300px;">
            <input type="text" id="searchInputNombre" class="form-control" placeholder="Buscar por nombre" style="max-width: 300px;">
        </div>


        <!-- Dropdown para filtrar por estado -->
        <select id="filterEstado" class="form-control ms-2" style="max-width: 150px;">
            <option value="A">Activo (A)</option>
            <option value="N">Inactivo (N)</option>
            <option value="">Todos</option>

        </select>


        <hr>



        <!-- Tabla estilizada -->
        <!-- Tabla estilizada de Empleos -->
        <div class="table-container">
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>COD EMPLEO</th>
                        <th>NOMBRE EMPLEO</th>
                        <th>SALARIO</th>
                        <th>ESTADO</th>
                        <th>ACCIONES</th>
                    </tr>
                </thead>
                <tbody class="scrollable-tbody" id="clientesTable">
                    <c:forEach var="emp" items="${empleos}">
                        <tr>
                            <td>${emp.getID()}</td>
                            <td>${emp.getCOD_EMP()}</td>
                            <td>${emp.getNOMB_EMP()}</td>
                            <td>Q ${emp.getSALARIO()}</td>
                            <td>${emp.getESTADO()}</td>
                            <td>
                                <a class="btn btn-warning btn-sm" href="Controlador?menu=Empleos&accion=Editar&empCod=${emp.getCOD_EMP()}" title="Editar">
                                    <i class="fas fa-edit"></i> <!-- Icono de editar -->
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>


        <!-- Modal Crear Empleo -->
        <div class="modal fade" id="empleoModal" tabindex="-1" aria-labelledby="empleoModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg"> <!-- Modal más ancho -->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="empleoModalLabel">CREAR NUEVO EMPLEO</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Formulario dentro del modal -->
                        <form action="Controlador?menu=Empleos" method="POST">
                            <div class="row">
                                <!-- Primera columna -->
                                <div class="col-md-6">
                                    <div class="form-group mb-3">
                                        <label>Código Emplesfsdo</label>
                                        <input type="text" name="txtCod_emp" class="form-control" required>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>Nombre Empleo</label>
                                        <input type="text" name="txtNom_emp" class="form-control" required>
                                    </div>
                                </div>

                                <!-- Segunda columna -->
                                <div class="col-md-6">
                                    <div class="form-group mb-3">
                                        <label>Salario</label>
                                        <input type="text" name="txtSalario" step="0.01" class="form-control" required>
                                    </div>

                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" name="accion" value="agregar" class="btn btn-info">Agregar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>




        <!-- Modal Editar Empleo -->
        <div class="modal fade" id="empleoModalEdit" tabindex="-1" aria-labelledby="empleoModalEditLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg"> <!-- Modal más ancho -->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="empleoModalEditLabel">EDITAR EMPLEO</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Formulario dentro del modal -->
                        <form action="Controlador?menu=Empleos" method="POST">
                            <div class="row">
                                <!-- Primera columna -->
                                <div class="col-md-6">
                                    <div class="form-group mb-3">
                                        <label>Código Empleo</label>
                                        <input type="text" name="txtCod_emp" value="${empleo.getCOD_EMP()}" class="form-control" readonly>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>Nombre Empleo</label>
                                        <input type="text" name="txtNom_emp" value="${empleo.getNOMB_EMP()}" class="form-control" required>
                                    </div>
                                </div>

                                <!-- Segunda columna -->
                                <div class="col-md-6">
                                    <div class="form-group mb-3">
                                        <label>Salario</label>
                                        <input type="text" name="txtSalario" value="${empleo.getSALARIO()}" step="0.01" class="form-control" required>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>Estado</label>
                                        <select name="txtEstado" class="form-control" required>
                                            <option value="A" ${empleo.getESTADO() == 'A' ? 'selected' : ''}>Activo</option>
                                            <option value="N" ${empleo.getESTADO() == 'N' ? 'selected' : ''}>Inactivo</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" name="accion" value="Actualizar" class="btn btn-success">Actualizar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>




        <script>
// Mostrar la modal de edición si se pasa un objeto "empleo" desde el backend
            <% if (request.getAttribute("empleo") != null) { %>
            document.addEventListener('DOMContentLoaded', function () {
                const empleoModal = new bootstrap.Modal(document.getElementById('empleoModalEdit'), {
                    backdrop: 'static',
                    keyboard: false
                });
                empleoModal.show();
            });
            <% }%>
        </script>



    </div>


    <!-- Scripts de Bootstrap, SweetAlert2 y Font Awesome -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>












    <script>
        <% if (request.getAttribute("resultadoa") != null) {%>
            let resultado = "<%= request.getAttribute("resultadoa")%>";

            if (resultado === "1") {
                Swal.fire({
                    icon: 'success',
                    title: '¡Éxito!',
                    text: 'El empleo ha sido guardado correctamente irvin.',
                });
            } else if (resultado === "0") {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Hubo un problema al guardar el empleo.',
                });
            }
        <% } %>
    </script>


    <script>
        <% if (request.getAttribute("resultadoUpdate") != null) {%>
          let resultado = "<%= request.getAttribute("resultadoUpdate")%>";

          if (resultado === "1") {
              Swal.fire({
                  icon: 'success',
                  title: '¡Éxito!',
                  text: 'El empleo ha sido actualizado correctamente.',
              });
          } else if (resultado === "0") {
              Swal.fire({
                  icon: 'error',
                  title: 'Error',
                  text: 'Hubo un problema al actualizar el empleo.',
              });
          }
        <% }%>
    </script>



    <script>
        $(document).ready(function () {
            // Guardar el HTML original de la tabla
            var originalTableData = $('#clientesTable').html();

            // Buscador por Código
            $('#searchInputCodigo').on('keyup', function () {
                var searchText = $(this).val();
                if (searchText.trim() !== '') {
                    $.ajax({
                        type: 'GET',
                        url: 'Controlador?menu=Empleos&accion=BuscarPorCodigo',
                        data: {codigo: searchText},
                        success: function (response) {
                            $('#clientesTable').html(response);
                        },
                        error: function (xhr, status, error) {
                            console.error('Error en la búsqueda:', error);
                        }
                    });
                } else {
                    // Restaurar la tabla original si el input está vacío
                    $('#clientesTable').html(originalTableData);
                }
            });

            // Buscador por Nombre
            $('#searchInputNombre').on('keyup', function () {
                var searchText = $(this).val();
                if (searchText.trim() !== '') {
                    $.ajax({
                        type: 'GET',
                        url: 'Controlador?menu=Empleos&accion=BuscarPorNombre',
                        data: {nombre: searchText},
                        success: function (response) {
                            $('#clientesTable').html(response);
                        },
                        error: function (xhr, status, error) {
                            console.error('Error en la búsqueda:', error);
                        }
                    });
                } else {
                    // Restaurar la tabla original si el input está vacío
                    $('#clientesTable').html(originalTableData);
                }
            });
        });



        // Filtro por Estado
        $('#filterEstado').on('change', function () {
            var estadoSeleccionado = $(this).val(); // Obtener valor del select
            $.ajax({
                type: 'GET',
                url: 'Controlador?menu=Empleos&accion=FiltrarPorEstado',
                data: {estado: estadoSeleccionado}, // Enviar estado (puede ser vacío)
                success: function (response) {
                    $('#clientesTable').html(response); // Actualizar tabla con respuesta
                },
                error: function (xhr, status, error) {
                    console.error('Error en el filtro por estado:', error);
                }
            });
        });


    </script>



    <script src="VIEWS/SCRIPTS/moduloAsistencia.js"></script>


</body>
</html>


