<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gestión de Empresas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet">
          <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="VIEWS/STYLES/EstilosModulos.css" rel="stylesheet">


</head>
<body>
    <div class="container mt-5 content-container">
        <!-- Botón para abrir modal de crear -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#empresaModalCrear">
            Crear Empresa
        </button>

        <h1 class="text-center mb-4" style="font-weight: bold; color: #1c3d5a;">EMPRESAS</h1>

        <!-- Inputs de búsqueda y filtro de estado -->
        <div class="d-flex justify-content-center mb-4">
            <input type="text" id="searchInputCodigo" class="form-control me-2" placeholder="Buscar por código" style="max-width: 300px;">
            <input type="text" id="searchInputNombre" class="form-control" placeholder="Buscar por nombre" style="max-width: 300px;">
        </div>
        
        <div class="d-flex justify-content-center mb-4">
            <select id="filterEstado" class="form-control ms-2" style="max-width: 150px;">
                <option value="">Todos</option>
                <option value="A">Activo (A)</option>
                <option value="N">Inactivo (N)</option>
            </select>
        </div>

        <!-- Tabla de empresas -->
        <div class="table-container">
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>CÓDIGO EMPRESA</th>
                        <th>NOMBRE</th>
                        <th>DIRECCIÓN</th>
                        <th>NIT</th>
                        <th>ESTADO</th>
                        <th>ACCIONES</th>
                    </tr>
                </thead>
                <tbody class="scrollable-tbody" id="clientesTable">
                    <c:forEach var="empsa" items="${empresa}">
                        <tr>
                            <td>${empsa.getID()}</td>
                            <td>${empsa.getCOD_EMPSA()}</td>
                            <td>${empsa.getNOMB_EMPSA()}</td>
                            <td>${empsa.getDIRE_EMPSA()}</td>
                            <td>${empsa.getNIT()}</td>
                            <td>${empsa.getEstado()}</td>
                            <td>
                                <a class="btn btn-warning btn-sm" href="Controlador?menu=Empresa&accion=Editar&empCod=${empsa.getCOD_EMPSA()}" title="Editar">
                                    <i class="fas fa-edit"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

   <!-- Modal Crear Empresa -->
    <div class="modal fade" id="empresaModalCrear" tabindex="-1" aria-labelledby="empresaModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="empresaModalLabel">CREAR NUEVA EMPRESA</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="Controlador?menu=Empresa" method="POST">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group mb-3">
                                    <label>Código Empresa</label>
                                    <input type="text" name="txtCodEmpsa" class="form-control">
                                </div>
                                <div class="form-group mb-3">
                                    <label>Nombre Empresa</label>
                                    <input type="text" name="txtNombreEmpsa" class="form-control">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group mb-3">
                                    <label>Dirección Empresa</label>
                                    <input type="text" name="txtDireccionEmpsa" class="form-control">
                                </div>
                                <div class="form-group mb-3">
                                    <label>NIT Empresa</label>
                                    <input type="text" name="txtNitEmpsa" class="form-control">
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

    <!-- Modal Editar Empresa -->
    <div class="modal fade" id="empresaModalEditar" tabindex="-1" aria-labelledby="empresaModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="empresaModalLabel">EDITAR EMPRESA</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="Controlador?menu=Empresa" method="POST">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group mb-3">
                                    <label>Código Empresa</label>
                                    <input type="text" name="txtCodEmpsa" class="form-control" value="${empresaEditar.COD_EMPSA}">
                                </div>
                                <div class="form-group mb-3">
                                    <label>Nombre Empresa</label>
                                    <input type="text" name="txtNombreEmpsa" class="form-control" value="${empresaEditar.NOMB_EMPSA}">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group mb-3">
                                    <label>Dirección Empresa</label>
                                    <input type="text" name="txtDireccionEmpsa" class="form-control" value="${empresaEditar.DIRE_EMPSA}">
                                </div>
                                <div class="form-group mb-3">
                                    <label>NIT Empresa</label>
                                    <input type="text" name="txtNitEmpsa" class="form-control" value="${empresaEditar.NIT}">
                                </div>
                                
                                   <div class="form-group mb-3">
                                    <label>Estado</label>
                                    <select name="txtEstado" class="form-control">
                                        <option value="A" ${empsa.Estado == 'A' ? 'selected' : ''}>Activo</option>
                                        <option value="N" ${empsa.Estado == 'N' ? 'selected' : ''}>Inactivo</option>
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
    <!-- Scripts de Bootstrap y SweetAlert -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    
    <!-- Lógica de búsqueda y filtrado -->
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
                        url: 'Controlador?menu=Empresa&accion=BuscarPorCodigo',
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
                        url: 'Controlador?menu=Empresa&accion=BuscarPorNombre',
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
                        url: 'Controlador?menu=Empresa&accion=FiltrarPorEstado',
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

        // Mostrar modal de edición si se está editando una empresa
        <% if (request.getAttribute("empresaEditar") != null) { %>
        document.addEventListener('DOMContentLoaded', function () {
            const empresaModal = new bootstrap.Modal(document.getElementById('empresaModalEditar'), {
                backdrop: 'static',
                keyboard: false
            });
            empresaModal.show();
        });
        <% } %>
    </script>
</body>
</html>