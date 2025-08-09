<%-- 
    Document   : Empleados
    Created on : 19/09/2024, 21:39:43
    Author     : joans
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- //EMPLEADOS NEW > -->
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
            <!-- Botón de registrar asistencia alineado a la izquierda -->


           




        </div>

        <!-- Botón de Historial alineado a la derecha 
        <div class="btn-container btn-right">
            <button type="submit" class="btn btn-custom btn-lg">
                <i class="fas fa-history"></i> HISTORIAL
            </button>
        </div>-->
        <!-- Botón de Regresar -->
<div class="d-flex justify-content-start mb-3">
<button class="btn btn-secondary btn-regresar" onclick="regresarPagina()">
        <i class="fas fa-arrow-left"></i> Regresar
    </button>
</div>

        <!-- Título estilizado -->
        <h1 class="text-center mb-4" style="font-weight: bold; color: #1c3d5a;">EMPLEADOS</h1>
<!-- Botón que abre el modal -->
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#empleadoModal">
                Crear
            </button>
        <!-- Inputs de búsqueda  -->
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

        <!-- Tabla estilizada de datos-->
<div class="table-container">
    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>CODIGO</th>
                                <th>DPI</th>

                <th>NOMBRES</th>
                <th>APELLIDOS</th>
                <th>NO. IGSS</th>
                <th>NIT</th>
                <th>COD EMPLEO</th>
                <th>COD EMPSA</th>
                <th>CELULAR</th>
                <th>CORREO</th>
                <th>ESTADO</th>
                <th>ACCIONES</th>
            </tr>
        </thead>
        <tbody class="scrollable-tbody" id="clientesTable">
            <c:forEach var="em" items="${empleados}">
                <tr>
                    <td>${em.getId()}</td>
                    <td>${em.getCOC_EMPD()}</td>
                    <td>${em.getDPI()}</td>
                    <td>${em.getNOMBRES()}</td>
                    <td>${em.getAPELLIDOS()}</td>
                    <td>${em.getN_IGSS()}</td>
                    <td>${em.getNIT()}</td>
                    <td>${em.getCOD_EMP()}</td>
                    <td>${em.getCOD_EMPSA()}</td>
                    <td>${em.getCELULAR()}</td>
                    <td>${em.getCorreo()}</td>
                    <td>${em.getEstado()}</td>
                    <td>
                        <a class="btn btn-warning btn-sm" href="Controlador?menu=Empleados&accion=Editar&empCod=${em.getCOC_EMPD()}" title="Editar">
                            <i class="fas fa-edit"></i>
                        </a>
                             
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

        <!-- Modal Crear-->
        
        <div class="modal fade" id="empleadoModal" tabindex="-1" aria-labelledby="empleadoModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg"> <!-- Cambié a modal-lg para que el modal sea más ancho -->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="empleadoModalLabel">CREAR NUEVO EMPLEADO</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Formulario dentro del modal -->
                        <form action="Controlador?menu=Empleados" method="POST">
                            <div class="row">
                                <!-- Primera columna -->
                                <div class="col-md-6">
                                    <div class="form-group mb-3">
                                        <label>CODIGO</label>
                                        <input type="text" value="${empleado.getCOC_EMPD()}" name="txtCod" class="form-control">
                                    </div>
                                     <div class="form-group mb-3">
                                        <label>DPI</label>
                                        <input type="text" value="${empleado.getDPI()}" name="txtDpi" class="form-control">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>NOMBRES</label>
                                        <input type="text" value="${empleado.getNOMBRES()}" name="txtNombres" class="form-control">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>APELLIDOS</label>
                                        <input type="text" value="${empleado.getAPELLIDOS()}" name="txtApellidos" class="form-control">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>NO. IGSS</label>
                                        <input type="text" value="${empleado.getN_IGSS()}" name="txtIgss" class="form-control">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>NIT</label>
                                        <input type="text" value="${empleado.getNIT()}" name="txtNit" class="form-control">
                                    </div>
                                </div>

                                <!--   Segunda columna -->
                                <div class="col-md-6">
                                    <!--   <div class="form-group mb-3">
                                          <label>COD_EMP</label>
                                          <input type="text" value="${empleado.getCOD_EMP()}" name="txtCod_emp" class="form-control">
                                      </div> -->


                                    <div class="form-group">

                                        <label>EMPLEO</label>
                                        <select name="txtCod_emp" class="form-control">
                                            <c:forEach var="emp" items="${empleosd}">
                                                <option value="${emp.COD_EMP}"> ${emp.NOMB_EMP}</option>
                                            </c:forEach>
                                        </select>


                                    </div>

                                    <!-- <div class="form-group mb-3">
                                         <label>COD_EMPSA</label>
                                         <input type="text" value="${empleado.getCOD_EMPSA()}" name="txtCod_empsa" class="form-control">
                                     </div>-->

                                    <div class="form-group">

                                        <label>EMPRESA</label>
                                        <select name="txtCod_empsa" class="form-control">
                                            <c:forEach var="empsa" items="${empresad}">
                                                <option value="${empsa.COD_EMPSA}"> ${empsa.NOMB_EMPSA}</option>
                                            </c:forEach>
                                        </select>


                                    </div>





                                    <div class="form-group mb-3">
                                        <label>CELULAR</label>
                                        <input type="text" value="${empleado.getCELULAR()}" name="txtCelular" class="form-control">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>CORREO</label>
                                        <input type="text" value="${empleado.getCorreo()}" name="txtCorreo" class="form-control">
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






        <!-- Modal Editar-->
        <div class="modal fade" id="empleadoModaledit" tabindex="-1" aria-labelledby="empleadoModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg"> <!-- Cambié a modal-lg para que el modal sea más ancho -->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="empleadoModalLabel">EDITAR EMPLEADO</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Formulario dentro del modal -->
                        <form action="Controlador?menu=Empleados" method="POST">
                            <div class="row">
                                <!-- Primera columna -->
                                <div class="col-md-6">
                                     <div class="form-group mb-3">
                                        <label>CODIGO</label>
                                        <input type="text" value="${empleadoe.getCOC_EMPD()}" name="txtCod" class="form-control">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>DPI</label>
                                        <input type="text" value="${empleadoe.getDPI()}" name="txtDpi" class="form-control">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>NOMBRES</label>
                                        <input type="text" value="${empleadoe.getNOMBRES()}" name="txtNombres" class="form-control">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>APELLIDOS</label>
                                        <input type="text" value="${empleadoe.getAPELLIDOS()}" name="txtApellidos" class="form-control">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>NO. IGSS</label>
                                        <input type="text" value="${empleadoe.getN_IGSS()}" name="txtIgss" class="form-control">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>NIT</label>
                                        <input type="text" value="${empleadoe.getNIT()}" name="txtNit" class="form-control">
                                    </div>
                                </div>

                                <!-- Segunda columna -->
                                <div class="col-md-6">
                                         <div class="form-group">

                                        <label>EMPLEO</label>
                                        <select name="txtCod_emp" class="form-control">
                                            <c:forEach var="emp" items="${empleosd}">
                                                <option value="${emp.COD_EMP}"> ${emp.NOMB_EMP}</option>
                                            </c:forEach>
                                        </select>


                                    </div>

                                  
                                    <div class="form-group">

                                        <label>EMPRESA</label>
                                        <select name="txtCod_empsa" class="form-control">
                                            <c:forEach var="empsa" items="${empresad}">
                                                <option value="${empsa.COD_EMPSA}"> ${empsa.NOMB_EMPSA}</option>
                                            </c:forEach>
                                        </select>


                                    </div>
                                    <div class="form-group mb-3">
                                        <label>CELULAR</label>
                                        <input type="text" value="${empleadoe.getCELULAR()}" name="txtCelular" class="form-control">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>CORREO</label>
                                        <input type="text" value="${empleadoe.getCorreo()}" name="txtCorreo" class="form-control">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>ESTADO</label>
                                        <input type="text" value="${empleadoe.getEstado()}" name="txtEstado" class="form-control">
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


        <!-- Scripts -->
        <!-- Scripts -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>





























        <script>
            // Verificar si existe el atributo "resultado" para mostrar la alerta
            <% if (request.getAttribute("resultado") != null) {%>
            let resultado = "<%= request.getAttribute("resultado")%>";

            if (resultado === "1") {
                Swal.fire({
                    icon: 'success',
                    title: '¡Empleado agregado!',
                    text: 'El empleado se ha agregado correctamente.'
                });
            } else if (resultado === "0") {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Hubo un problema al agregar el empleado. Inténtalo de nuevo.'
                });
            }
            <% } %>


            // Verificar si existe el atributo "resultado" para mostrar la alerta
            <% if (request.getAttribute("resultadoUpdate") != null) {%>
            let resultado = "<%= request.getAttribute("resultadoUpdate")%>";

            if (resultado === "1") {
                Swal.fire({
                    icon: 'success',
                    title: '¡Empleado Actualizado!',
                    text: 'El empleado se ha Actualizado correctamente.'
                });
            } else if (resultado === "0") {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Hubo un problema al actualizar el empleado. ¡Inténtalo de nuevo.!'
                });
            }
            <% } %>

        </script>


        <script>
            // Mostrar la modal de edición si el atributo "empleado" existe
            <% if (request.getAttribute("empleadoe") != null) { %>
            document.addEventListener('DOMContentLoaded', function () {
                const empleadoModal = new bootstrap.Modal(document.getElementById('empleadoModaledit'), {
                    backdrop: 'static',
                    keyboard: false
                });
                empleadoModal.show();
                console.log("Modal de edición abierta");
            });
            <% }%>
        </script>
        <script>

            $(document).ready(function () {
                // Guardar los datos originales de la tabla
                var originalTableData = $('#clientesTable').html();

                // Buscador por Código
                $('#searchInputCodigo').on('keyup', function () {
                    var searchText = $(this).val();
                    if (searchText.trim() !== '') {
                        $.ajax({
                            type: 'GET',
                            url: 'Controlador?menu=Empleados&accion=BuscarPorCodigo',
                            data: {codigo: searchText},
                            success: function (response) {
                                $('#clientesTable').html(response);
                            },
                            error: function (xhr, status, error) {
                                console.error('Error en la búsqueda:', error);
                            }
                        });
                    } else {
                        $('#clientesTable').html(originalTableData);
                    }
                });

                // Buscador por Nombre
                $('#searchInputNombre').on('keyup', function () {
                    var searchText = $(this).val();
                    if (searchText.trim() !== '') {
                        $.ajax({
                            type: 'GET',
                            url: 'Controlador?menu=Empleados&accion=BuscarPorNombre',
                            data: {nombre: searchText},
                            success: function (response) {
                                $('#clientesTable').html(response);
                            },
                            error: function (xhr, status, error) {
                                console.error('Error en la búsqueda:', error);
                            }
                        });
                    } else {
                        $('#clientesTable').html(originalTableData);
                    }
                });

                // Filtro por Estado
                $('#filterEstado').on('change', function () {
                    var estado = $(this).val();
                    if (estado !== '') {
                        $.ajax({
                            type: 'GET',
                            url: 'Controlador?menu=Empleados&accion=FiltrarPorEstado',
                            data: {estado: estado},
                            success: function (response) {
                                $('#clientesTable').html(response);
                            },
                            error: function (xhr, status, error) {
                                console.error('Error en el filtro:', error);
                            }
                        });
                    } else {
                        $('#clientesTable').html(originalTableData);
                    }
                });
            });

        </script>
        
        <script>
    function regresarPagina() {
        window.history.back();
    }
</script>

    </body>
</html>

