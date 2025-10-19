<%-- 
    Document   : Cotizaciones
    Created on : 10/10/2025
    Author     : joans
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión de Cotizaciones</title>
        <!-- Agregar estilos de Bootstrap, Font Awesome y SweetAlert2 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <link href="VIEWS/STYLES/EstilosModulos.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5 content-container">
        </div>

        <!-- Botón de Regresar -->
        <div class="d-flex justify-content-start mb-3">
            <button class="btn btn-secondary btn-regresar" onclick="regresarPagina()">
                <i class="fas fa-arrow-left"></i> Regresar
            </button>
        </div>

        <!-- Título estilizado -->
        <h1 class="text-center mb-4" style="font-weight: bold; color: #1c3d5a;">historial de pagos</h1>

        <!-- Botón que abre el modal de crear -->
        <c:if test="${nivelPermiso == null || nivelPermiso != 3}">
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#cotizacionModal">
                <i class="fas fa-plus"></i> Nueva Cotización
            </button>
        </c:if>
        <!-- Inputs de búsqueda -->
        <div class="d-flex justify-content-center mb-4 mt-3">
            <input type="text" id="searchInputCodCli" class="form-control me-2" 
                   placeholder="Buscar por código cliente" style="max-width: 250px;">
            <input type="text" id="searchInputNumCot" class="form-control me-2" 
                   placeholder="Buscar por número cotización" style="max-width: 250px;">
            <input type="text" id="searchInputPeriodo" class="form-control" 
                   placeholder="Buscar por periodo (2025-01)" style="max-width: 250px;">
        </div>

        <!-- Dropdown para filtrar por estado -->
        <div class="d-flex justify-content-center mb-3">
            <select id="filterEstado" class="form-control" style="max-width: 200px;">
                <option value="">Todos los estados</option>
                <option value="A">Pendiente de Pago</option>
                <option value="C">Cancelado</option>
            </select>
        </div>

        <hr>

        <!-- Tabla estilizada de datos -->
        <div class="table-container">
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>CÓDIGO CLIENTE</th>
                        <th>NÚM. COTIZACIÓN</th>
                        <th>ID TRANSACCIÓN</th>
                        <th>PERIODO</th>
                        <th>FECHA EMISIÓN</th>
                        <th>FECHA VENC.</th>
                        <th>MONTO</th>
                        <th>ESTADO</th>
                        <th>ACCIONES</th>
                    </tr>
                </thead>
                <tbody class="scrollable-tbody" id="cotizacionesTable">
                    <c:forEach var="cot" items="${cotizaciones}">
                        <tr>
                            <td>${cot.getID()}</td>
                            <td>${cot.getCOD_CLI()}</td>
                            <td>${cot.getNUM_COT()}</td>
                            <td>${cot.getID_TRANS()}</td>
                            <td>${cot.getPERIODO()}</td>
                            <td>${cot.getFEC_EMISION()}</td>
                            <td>${cot.getFEC_VENC()}</td>
                            <td>Q ${cot.getMONTO()}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${cot.getESTADO() == 'A'}">
                                        <span class="badge bg-danger">Pendiente de Pago</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-success">Cancelado</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <!-- VALIDACIÓN: Solo Admin y Cobrador ven botones -->
                                <c:if test="${nivelPermiso == null || nivelPermiso != 3}">
                                    <a class="btn btn-warning btn-sm" 
                                       href="Controlador?menu=Cotizaciones&accion=Editar&idCot=${cot.getID()}" 
                                       title="Editar">
                                        <i class="fas fa-edit"></i>
                                    </a>

                                    <c:if test="${cot.getESTADO() == 'A'}">
                                        <a class="btn btn-danger btn-sm" 
                                           href="Controlador?menu=Cotizaciones&accion=Cancelar&idCot=${cot.getID()}" 
                                           title="Cancelar"
                                           onclick="return confirm('Â¿EstÃ¡ seguro de cancelar esta cotizaciÃ³n?')">
                                            <i class="fas fa-times-circle"></i>
                                        </a>
                                    </c:if>

                                  
                                </c:if>
                                  <c:if test="${cot.getESTADO() == 'C'}">
                                        <a class="btn btn-info btn-sm"
                                           href="Controlador?menu=Cotizaciones&accion=GenerarPDF&numCot=${cot.getNUM_COT()}&idTrans=${cot.getID_TRANS()}"
                                           title="Comprobante de pago">
                                            <i class="fas fa-file-alt"></i>
                                        </a>


                                    </c:if>

                                <!-- ✅ SI es cliente, celda vacía o mensaje -->
                                <c:if test="${nivelPermiso == 3}">
                                    
                                    <span class="text-muted" style="font-size: 0.85rem;">-</span>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Modal Crear -->
        <div class="modal fade" id="cotizacionModal" tabindex="-1" aria-labelledby="cotizacionModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="cotizacionModalLabel">CREAR NUEVA COTIZACIÓN</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="Controlador?menu=Cotizaciones" method="POST">
                            <div class="row">
                                <!-- Primera columna -->
                                <div class="col-md-6">
                                    <div class="form-group mb-3">

                                        <label>CÓDIGO CLIENTE</label>
                                        <select name="txtCodCli" class="form-control" required>
                                            <option value="">Seleccione un cliente</option>
                                            <c:forEach var="cli" items="${clientes}">
                                                <option value="${cli.COD_CLI}">${cli.COD_CLI} - ${cli.NOMBRES} ${cli.APELLIDOS}</option>                                            </c:forEach>
                                            </select>

                                        </div>
                                        <div class="form-group mb-3">
                                            <label>NÚMERO COTIZACIÓN</label>
                                            <input type="text" name="txtNumCot" class="form-control" 
                                                   placeholder="Ej: 2025-001-CLI001" required>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label>ID TRANSACCIÓN</label>
                                            <input type="text" name="txtIdTrans" class="form-control" 
                                                   placeholder="Ej: TRX-CLI001-2025-01" required>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label>PERIODO</label>
                                            <input type="text" name="txtPeriodo" class="form-control" 
                                                   placeholder="Ej: 2025-01" required>
                                        </div>
                                    </div>

                                    <!-- Segunda columna -->
                                    <div class="col-md-6">
                                        <div class="form-group mb-3">
                                            <label>FECHA EMISIÓN</label>
                                            <input type="date" name="txtFecEmision" class="form-control" required>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label>FECHA VENCIMIENTO</label>
                                            <input type="date" name="txtFecVenc" class="form-control" required>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label>MONTO (Q)</label>
                                            <input type="number" step="0.01" name="txtMonto" class="form-control" 
                                                   placeholder="Ej: 150.00" required>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label>ESTADO</label>
                                            <select name="txtEstado" class="form-control" required>
                                                <option value="A">Activa</option>
                                                <option value="C">Cancelada</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                    <button type="submit" name="accion" value="agregar" class="btn btn-info">
                                        <i class="fas fa-save"></i> Guardar
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal Editar -->
            <div class="modal fade" id="cotizacionModaledit" tabindex="-1" aria-labelledby="cotizacionModalEditLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="cotizacionModalEditLabel">EDITAR COTIZACIÓN</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="Controlador?menu=Cotizaciones" method="POST">
                                <input type="hidden" name="txtId" value="${cotizacione.getID()}">
                            <div class="row">
                                <!-- Primera columna -->
                                <div class="col-md-6">
                                    <div class="form-group mb-3">
                                        <label>CÓDIGO CLIENTE</label>
                                        <input type="text" name="txtCodCli" class="form-control" 
                                               value="${cotizacione.getCOD_CLI()}" readonly>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>NÚMERO COTIZACIÓN</label>
                                        <input type="text" name="txtNumCot" class="form-control" 
                                               value="${cotizacione.getNUM_COT()}" readonly>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>ID TRANSACCIÓN</label>
                                        <input type="text" name="txtIdTrans" class="form-control" 
                                               value="${cotizacione.getID_TRANS()}" readonly>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>PERIODO</label>
                                        <input type="text" name="txtPeriodo" class="form-control" 
                                               value="${cotizacione.getPERIODO()}" readonly>
                                    </div>
                                </div>

                                <!-- Segunda columna -->
                                <div class="col-md-6">
                                    <div class="form-group mb-3">
                                        <label>FECHA EMISIÓN</label>
                                        <input type="date" name="txtFecEmision" class="form-control" 
                                               value="${cotizacione.getFEC_EMISION()}" required>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>FECHA VENCIMIENTO</label>
                                        <input type="date" name="txtFecVenc" class="form-control" 
                                               value="${cotizacione.getFEC_VENC()}" required>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>MONTO (Q)</label>
                                        <input type="number" step="0.01" name="txtMonto" class="form-control" 
                                               value="${cotizacione.getMONTO()}" required>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>ESTADO</label>
                                        <select name="txtEstado" class="form-control" required>
                                            <option value="A" ${cotizacione.getESTADO() == 'A' ? 'selected' : ''}>Activa</option>
                                            <option value="C" ${cotizacione.getESTADO() == 'C' ? 'selected' : ''}>Cancelada</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <button type="submit" name="accion" value="Actualizar" class="btn btn-success">
                                    <i class="fas fa-sync-alt"></i> Actualizar
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Scripts -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <!-- Script para alertas de agregar -->
        <script>
            <% if (request.getAttribute("resultado") != null) {%>
                                               let resultado = "<%= request.getAttribute("resultado")%>";

                                               if (resultado === "1") {
                                                   Swal.fire({
                                                       icon: 'success',
                                                       title: '¡Cotización agregada!',
                                                       text: 'La cotización se ha agregado correctamente.'
                                                   });
                                               } else if (resultado === "0") {
                                                   Swal.fire({
                                                       icon: 'error',
                                                       title: 'Error',
                                                       text: 'Hubo un problema al agregar la cotización. Inténtalo de nuevo.'
                                                   });
                                               }
            <% }%>
        </script>

        <!-- Script para alertas de actualizar -->
        <script>
            <% if (request.getAttribute("resultadoUpdate") != null) {%>
            let resultadoUpdate = "<%= request.getAttribute("resultadoUpdate")%>";

            if (resultadoUpdate === "1") {
                Swal.fire({
                    icon: 'success',
                    title: '¡Cotización Actualizada!',
                    text: 'La cotización se ha actualizado correctamente.'
                });
            } else if (resultadoUpdate === "0") {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Hubo un problema al actualizar la cotización. ¡Inténtalo de nuevo!'
                });
            }
            <% }%>
        </script>

        <!-- Script para alertas de cancelar -->
        <script>
            <% if (request.getAttribute("resultadoCancelar") != null) {%>
            let resultadoCancelar = "<%= request.getAttribute("resultadoCancelar")%>";

            if (resultadoCancelar === "1") {
                Swal.fire({
                    icon: 'success',
                    title: '¡Cotización Cancelada!',
                    text: 'La cotización se ha cancelado correctamente.'
                });
            } else if (resultadoCancelar === "0") {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Hubo un problema al cancelar la cotización.'
                });
            }
            <% }%>
        </script>

        <!-- Script para abrir modal de edición -->
        <script>
            <% if (request.getAttribute("cotizacione") != null) {%>
            document.addEventListener('DOMContentLoaded', function () {
                const cotizacionModal = new bootstrap.Modal(document.getElementById('cotizacionModaledit'), {
                    backdrop: 'static',
                    keyboard: false
                });
                cotizacionModal.show();
                console.log("Modal de edición abierta");
            });
            <% }%>
        </script>

        <!-- Scripts AJAX para búsquedas -->
        <script>
            $(document).ready(function () {
                var originalTableData = $('#cotizacionesTable').html();

                // Buscador por Código Cliente
                $('#searchInputCodCli').on('keyup', function () {
                    var searchText = $(this).val();
                    if (searchText.trim() !== '') {
                        $.ajax({
                            type: 'GET',
                            url: 'Controlador?menu=Cotizaciones&accion=BuscarPorCodCli',
                            data: {codCli: searchText},
                            success: function (response) {
                                $('#cotizacionesTable').html(response);
                            },
                            error: function (xhr, status, error) {
                                console.error('Error en la búsqueda:', error);
                            }
                        });
                    } else {
                        $('#cotizacionesTable').html(originalTableData);
                    }
                });

                // Buscador por Número de Cotización
                $('#searchInputNumCot').on('keyup', function () {
                    var searchText = $(this).val();
                    if (searchText.trim() !== '') {
                        $.ajax({
                            type: 'GET',
                            url: 'Controlador?menu=Cotizaciones&accion=BuscarPorNumCot',
                            data: {numCot: searchText},
                            success: function (response) {
                                $('#cotizacionesTable').html(response);
                            },
                            error: function (xhr, status, error) {
                                console.error('Error en la búsqueda:', error);
                            }
                        });
                    } else {
                        $('#cotizacionesTable').html(originalTableData);
                    }
                });

                // Buscador por Periodo
                $('#searchInputPeriodo').on('keyup', function () {
                    var searchText = $(this).val();
                    if (searchText.trim() !== '') {
                        $.ajax({
                            type: 'GET',
                            url: 'Controlador?menu=Cotizaciones&accion=BuscarPorPeriodo',
                            data: {periodo: searchText},
                            success: function (response) {
                                $('#cotizacionesTable').html(response);
                            },
                            error: function (xhr, status, error) {
                                console.error('Error en la búsqueda:', error);
                            }
                        });
                    } else {
                        $('#cotizacionesTable').html(originalTableData);
                    }
                });

                // Filtro por Estado
                $('#filterEstado').on('change', function () {
                    var estado = $(this).val();

                    if (estado !== '') {
                        $.ajax({
                            type: 'GET',
                            url: 'Controlador?menu=Cotizaciones&accion=FiltrarPorEstado',
                            data: {estado: estado},
                            success: function (response) {
                                $('#cotizacionesTable').html(response);
                            },
                            error: function (xhr, status, error) {
                                console.error('Error en el filtro:', error);
                            }
                        });
                    } else {
                        $('#cotizacionesTable').html(originalTableData);
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