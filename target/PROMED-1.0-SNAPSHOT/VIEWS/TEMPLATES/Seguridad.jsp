<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Gestión de Usuarios</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
          <link href="VIEWS/STYLES/EstilosModulos.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#userModalCrear">Crear Usuario</button>
            <h1 class="text-center mb-4" style="font-weight: bold; color: #1c3d5a;">Usuarios</h1>

            <!-- Inputs de búsqueda -->
            <div class="d-flex justify-content-center mb-4">
                <input type="text" id="searchInputCodigo" class="form-control me-2" placeholder="Buscar por código" style="max-width: 300px;">
                <input type="text" id="searchInputUsuario" class="form-control" placeholder="Buscar por usuario" style="max-width: 300px;">
            </div>

            <!-- Dropdown para filtrar por estado -->
            <div class="d-flex justify-content-center mb-4">
                <select id="filterEstado" class="form-control" style="max-width: 150px;">
                    <option value="">Todos</option>
                    <option value="A">Activo (A)</option>
                    <option value="N">Inactivo (N)</option>
                </select>
            </div>

            <!-- Tabla de usuarios -->
            <!-- Tabla de usuarios -->
            <div class="table-container">
                <table class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Código</th>
                            <th>Usuario</th>
                            <th>Rol</th>
                            <th>EMPRESA</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody class="scrollable-tbody" id="clientesTable">
                        <c:forEach var="user" items="${usuarios}">
                            <tr>
                                <td>${user.ID}</td>
                                <td>${user.cod_user}</td>
                                <td>${user.user_US}</td>
                                <td>${user.rol_Us}</td>
                                <td>${user.cod_empsa}</td>
                                <td>${user.esdado}</td>
                                <td>
                                    <a href="Controlador?menu=Seguridad&accion=Editar&codUser=${user.cod_user}" class="btn btn-warning btn-sm">Editar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>


        <!-- Modal Crear Usuario -->
        <div class="modal fade" id="userModalCrear" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <form action="Controlador?menu=Seguridad" method="POST">
                        <div class="modal-header">
                            <h5 class="modal-title">Crear Usuario</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <!-- Primera columna -->
                                <div class="col-md-6">
                                    <div class="form-group mb-3">
                                        <label>Código</label>
                                        <input type="text" name="txtCodUser" class="form-control" placeholder="Código">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>Usuario</label>
                                        <input type="text" name="txtUserUs" class="form-control" placeholder="Usuario">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>Rol</label>
                                        <input type="text" name="txtRolUs" class="form-control" placeholder="Rol">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>Contraseña</label>
                                        <input type="password" name="txtPswUs" class="form-control" placeholder="Contraseña">
                                    </div>
                                </div>
                                <!-- Segunda columna -->
                                <div class="col-md-6">
                                    <div class="form-group mb-3">
                                                                                <label>Empresa</label>

                                                                            <select name="txtCodEmpsa" class="form-control">
    <c:forEach var="emp" items="${empresad}">
        <option value="${emp.COD_EMPSA}">${emp.NOMB_EMPSA}</option>
    </c:forEach>
</select>

                                                                        </div>
                                    <div class="form-group mb-3">
                                        <label>Nombre</label>
                                        <input type="text" name="txtNombUs" class="form-control" placeholder="Nombre">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>Apellido</label>
                                        <input type="text" name="txtApeUs" class="form-control" placeholder="Apellido">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>Correo</label>
                                        <input type="text" name="txtCorrUs" class="form-control" placeholder="Correo">
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" name="accion" value="agregar" class="btn btn-success">Agregar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Modal Editar Usuario -->
        <div class="modal fade" id="userModalEditar" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <form action="Controlador?menu=Seguridad" method="POST">
                        <div class="modal-header">
                            <h5 class="modal-title">Editar Usuario</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <!-- Primera columna -->
                                <div class="col-md-6">
                                    <div class="form-group mb-3">
                                        <label>Código</label>
                                        <input type="text" name="txtCodUser" class="form-control" value="${usuarioEditar.cod_user}" placeholder="Código">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>Usuario</label>
                                        <input type="text" name="txtUserUs" class="form-control" value="${usuarioEditar.user_US}" placeholder="Usuario">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>Rol</label>
                                        <input type="text" name="txtRolUs" class="form-control" value="${usuarioEditar.rol_Us}" placeholder="Rol">
                                    </div>
                                    
                                    <div class="form-group mb-3">
                                        <label>Contraseña</label>
                                        <input type="password" name="txtPswUs" class="form-control" value="${usuarioEditar.psw_Us}" placeholder="Contraseña">
                                    </div>
                                </div>
                                <!-- Segunda columna -->
                                
                                <div class="col-md-6">
                                                                        <div class="form-group mb-3">
                                                                                                                                                            <label>Empresa</label>

                                                                            <select name="txtCodEmpsa" class="form-control">
    <c:forEach var="emp" items="${empresad}">
        <option value="${emp.COD_EMPSA}">${emp.NOMB_EMPSA}</option>
    </c:forEach>
</select>

                                                                        </div>
                                    <div class="form-group mb-3">
                                        <label>Nombre</label>
                                        <input type="text" name="txtNombUs" class="form-control" value="${usuarioEditar.nomb_Us}" placeholder="Nombre">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>Apellido</label>
                                        <input type="text" name="txtApeUs" class="form-control" value="${usuarioEditar.ape_Us}" placeholder="Apellido">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>Correo</label>
                                        <input type="text" name="txtCorrUs" class="form-control" value="${usuarioEditar.corr_Us}" placeholder="Correo">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>Estado</label>
                                        <select name="txtEstado" class="form-control">
                                            <option value="A" ${usuarioEditar.esdado == 'A' ? 'selected' : ''}>Activo</option>
                                            <option value="N" ${usuarioEditar.esdado == 'N' ? 'selected' : ''}>Inactivo</option>
                                        </select>
                                    </div>
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

        <!-- Scripts de Bootstrap y SweetAlert -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <!-- Script de manejo de modales y alertas -->
        <script>
            $(document).ready(function () {
                // Mostrar modal de edición si existe "usuarioEditar"
            <% if (request.getAttribute("usuarioEditar") != null) { %>
                $('#userModalEditar').modal('show');
            <% } %>

                // Verificar si hay un resultado de inserción/actualización para mostrar una alerta
            <% if (request.getAttribute("resultado") != null) {%>
                var resultado = "<%= request.getAttribute("resultado")%>";
                if (resultado === "1") {
                    Swal.fire('Éxito', 'Usuario agregado correctamente.', 'success');
                } else {
                    Swal.fire('Error', 'No se pudo agregar el usuario.', 'error');
                }
            <% } %>

            <% if (request.getAttribute("resultadoUpdate") != null) {%>
                var resultadoUpdate = "<%= request.getAttribute("resultadoUpdate")%>";
                if (resultadoUpdate === "1") {
                    Swal.fire('Éxito', 'Usuario actualizado correctamente.', 'success');
                } else {
                    Swal.fire('Error', 'No se pudo actualizar el usuario.', 'error');
                }
            <% }%>
            });



            $(document).ready(function () {
                // Guardar los datos originales de la tabla para restaurar si no hay búsqueda
                var originalTableData = $('#clientesTable').html();

                // Búsqueda por código
                $('#searchInputCodigo').on('keyup', function () {
                    var codigo = $(this).val();
                    if (codigo.trim() !== '') {
                        $.ajax({
                            type: 'GET',
                            url: 'Controlador?menu=Seguridad&accion=BuscarPorCodigo',
                            data: {codigo: codigo},
                            success: function (response) {
                                $('#clientesTable').html(response);
                            },
                            error: function (xhr, status, error) {
                                console.error('Error en la búsqueda por código:', error);
                            }
                        });
                    } else {
                        // Restaurar datos originales si se borra el texto de búsqueda
                        $('#clientesTable').html(originalTableData);
                    }
                });

                // Búsqueda por usuario
                $('#searchInputUsuario').on('keyup', function () {
                    var usuario = $(this).val();
                    if (usuario.trim() !== '') {
                        $.ajax({
                            type: 'GET',
                            url: 'Controlador?menu=Seguridad&accion=BuscarPorUsuario',
                            data: {usuario: usuario},
                            success: function (response) {
                                $('#clientesTable').html(response);
                            },
                            error: function (xhr, status, error) {
                                console.error('Error en la búsqueda por usuario:', error);
                            }
                        });
                    } else {
                        // Restaurar datos originales si se borra el texto de búsqueda
                        $('#clientesTable').html(originalTableData);
                    }
                });

                // Filtro por estado
                $('#filterEstado').on('change', function () {
                    var estado = $(this).val();
                    if (estado !== '') {
                        $.ajax({
                            type: 'GET',
                            url: 'Controlador?menu=Seguridad&accion=FiltrarPorEstado',
                            data: {estado: estado},
                            success: function (response) {
                                $('#clientesTable').html(response);
                            },
                            error: function (xhr, status, error) {
                                console.error('Error en el filtro por estado:', error);
                            }
                        });
                    } else {
                        // Restaurar datos originales si no hay filtro seleccionado
                        $('#clientesTable').html(originalTableData);
                    }
                });

            });

        </script>
    </body>
</html>
