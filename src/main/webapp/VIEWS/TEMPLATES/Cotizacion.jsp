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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link href="VIEWS/STYLES/EstilosModulos.css" rel="stylesheet">
     
    </head>
    <body>
        <div class="container-fluid mt-5 content-container"></div>

        <!-- Botón de Regresar -->
        <div class="d-flex justify-content-start mb-3">
            <button class="btn btn-secondary btn-regresar" onclick="regresarPagina()">
                <i class="fas fa-arrow-left"></i> Regresar
            </button>
        </div>

        <!-- Título estilizado -->
        <h1 class="text-center mb-4" style="font-weight: bold; color: #1c3d5a;">
            <c:choose>
                <c:when test="${tipoVista == 'cliente'}">Historial de Pagos</c:when>
                <c:otherwise>Cotizaciones por Período</c:otherwise>
            </c:choose>
        </h1>

        <!-- ============================================
             VISTA CLIENTE - Tabla Original
             ============================================ -->
        <c:if test="${tipoVista == 'cliente'}">
            
            <!-- Botón crear (solo para admin/cobrador) -->
            <c:if test="${nivelPermiso == null || nivelPermiso != 3}">
                <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#cotizacionModal">
                    <i class="fas fa-plus"></i> Nueva Cotización
                </button>
            </c:if>

            <!-- Inputs de búsqueda -->
            <div class="row mb-3">
                <div class="col-md-4">
                    <input type="text" id="searchInputCodCli" class="form-control" 
                           placeholder="Buscar por código cliente">
                </div>
                <div class="col-md-4">
                    <input type="text" id="searchInputNumCot" class="form-control" 
                           placeholder="Buscar por nº cotización">
                </div>
                <div class="col-md-4">
                    <input type="text" id="searchInputPeriodo" class="form-control" 
                           placeholder="Buscar por periodo">
                </div>
            </div>

            <!-- Dropdown para filtrar por estado -->
            <div class="mb-3">
                <select id="filterEstado" class="form-control" style="max-width: 250px;">
                    <option value="">Todos los estados</option>
                    <option value="A">Pendiente de Pago</option>
                    <option value="C">Cancelado</option>
                </select>
            </div>

            <hr>

            <!-- Tabla estilizada de datos - CLIENTE -->
            <div class="table-container">
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>CÓDIGO</th>
                            <th>NÚM. COT.</th>
                            <th>ID TRANS.</th>
                            <th>PERIODO</th>
                            <th>EMISIÓN</th>
                            <th>VENC.</th>
                            <th>MONTO</th>
                            <th>ESTADO</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody  id="cotizacionesTable">
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
                                            <span class="badge bg-danger">Pendiente</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-success">Cancelado</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
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
                                               onclick="return confirm('¿Estás seguro de cancelar?')">
                                                <i class="fas fa-times-circle"></i>
                                            </a>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${cot.getESTADO() == 'C'}">
                                        <a class="btn btn-info btn-sm"
                                           href="Controlador?menu=Cotizaciones&accion=GenerarPDF&numCot=${cot.getNUM_COT()}&idTrans=${cot.getID_TRANS()}"
                                           title="Comprobante">
                                            <i class="fas fa-file-pdf"></i>
                                        </a>
                                    </c:if>
                                    <c:if test="${nivelPermiso == 3}">
                                        <span class="text-muted">-</span>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>

        <!-- ============================================
             VISTA ADMIN/COBRADOR - Tabla Período Actual
             ============================================ -->
        <c:if test="${tipoVista == 'admin'}">
            
            <!-- Información del período -->
            <div class="alert alert-info mb-4" role="alert">
                <strong><i class="fas fa-calendar-alt"></i> Período Actual:</strong> ${periodoActual}
            </div>

            <!-- Tabla de resumen por cliente -->
            <div class="table-container">
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>CLIENTE</th>
                            <th>PER. ACT.</th>
                            <th>NÚM. COT.</th>
                            <th>ESTADO</th>
                            <th>MONTO</th>
                            <th>SITUACIÓN</th>
                            <th>ÚLTIMA COT.</th>
                            <th>PER.</th>
                            <th>EMISIÓN</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody id="cotizacionesPeriodoTable">
                        <c:forEach var="cot" items="${cotizacionesPeriodo}">
                            <tr>
                                <td><strong>${cot.get('cod_cli')}</strong></td>
                                <td>${cot.get('periodo_actual')}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${cot.get('num_cot_periodo') != null}">
                                            ${cot.get('num_cot_periodo')}
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-muted">-</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${cot.get('estado_periodo') == 'A'}">
                                            <span class="badge bg-warning text-dark">Pendiente</span>
                                        </c:when>
                                        <c:when test="${cot.get('estado_periodo') == 'C'}">
                                            <span class="badge bg-success">Cancelado</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-muted">-</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${cot.get('monto_periodo') > 0}">
                                            Q ${cot.get('monto_periodo')}
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-muted">-</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${cot.get('situacion_periodo') == 'Emitida'}">
                                            <span class="badge bg-success">Emitida</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-danger">No emitida</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${cot.get('ultima_num_cot')}</td>
                                <td>${cot.get('ultimo_periodo')}</td>
                                <td>${cot.get('ultima_emision')}</td>
                                <td>
                                    <button type="button" class="btn btn-info btn-sm" 
                                            data-bs-toggle="modal" 
                                            data-bs-target="#modalDetalleCliente"
                                            onclick="cargarDetalleCliente('${cot.get('cod_cli')}', '${cot.get('periodo_actual')}')">
                                        <i class="fas fa-eye"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>

        <!-- ============================================
             MODAL - Detalle de Cotizaciones del Cliente
             ============================================ -->
        <div class="modal fade" id="modalDetalleCliente" tabindex="-1">
            <div class="modal-dialog modal-xl">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">
                            Cotizaciones de: <strong id="clienteNombre"></strong>
                        </h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <div class="table-container">
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>NÚM. COT.</th>
                                        <th>PERÍODO</th>
                                        <th>EMISIÓN</th>
                                        <th>VENC.</th>
                                        <th>MONTO</th>
                                        <th>ESTADO</th>
                                        <th>ACCIONES</th>
                                    </tr>
                                </thead>
                                <tbody id="detalleClienteTable">
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- ============================================
             MODALES (Crear/Editar)
             ============================================ -->

        <!-- Modal Crear -->
        <div class="modal fade" id="cotizacionModal" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">CREAR NUEVA COTIZACIÓN</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <form action="Controlador?menu=Cotizaciones" method="POST">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group mb-3">
                                        <label>CÓDIGO CLIENTE</label>
                                        <select name="txtCodCli" class="form-control" required>
                                            <option value="">Seleccione un cliente</option>
                                            <c:forEach var="cli" items="${clientes}">
                                                <option value="${cli.COD_CLI}">${cli.COD_CLI} - ${cli.NOMBRES}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>NÚMERO COTIZACIÓN</label>
                                        <input type="text" name="txtNumCot" class="form-control" required>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>ID TRANSACCIÓN</label>
                                        <input type="text" name="txtIdTrans" class="form-control" required>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>PERIODO</label>
                                        <input type="text" name="txtPeriodo" class="form-control" required>
                                    </div>
                                </div>
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
                                        <input type="number" step="0.01" name="txtMonto" class="form-control" required>
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
        <div class="modal fade" id="cotizacionModaledit" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">EDITAR COTIZACIÓN</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <form action="Controlador?menu=Cotizaciones" method="POST">
                            <input type="hidden" name="txtId" value="${cotizacione.getID()}">
                            <div class="row">
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
                    text: 'Hubo un problema al agregar la cotización.'
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
                    text: 'Hubo un problema al actualizar la cotización.'
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
            });
            <% }%>
        </script>

        <!-- Scripts AJAX para búsquedas en VISTA CLIENTE -->
        <script>
            $(document).ready(function () {
                var originalTableData = $('#cotizacionesTable').html();

                $('#searchInputCodCli').on('keyup', function () {
                    var searchText = $(this).val();
                    if (searchText.trim() !== '') {
                        $.ajax({
                            type: 'GET',
                            url: 'Controlador?menu=Cotizaciones&accion=BuscarPorCodCli',
                            data: {codCli: searchText},
                            success: function (response) {
                                $('#cotizacionesTable').html(response);
                            }
                        });
                    } else {
                        $('#cotizacionesTable').html(originalTableData);
                    }
                });

                $('#searchInputNumCot').on('keyup', function () {
                    var searchText = $(this).val();
                    if (searchText.trim() !== '') {
                        $.ajax({
                            type: 'GET',
                            url: 'Controlador?menu=Cotizaciones&accion=BuscarPorNumCot',
                            data: {numCot: searchText},
                            success: function (response) {
                                $('#cotizacionesTable').html(response);
                            }
                        });
                    } else {
                        $('#cotizacionesTable').html(originalTableData);
                    }
                });

                $('#searchInputPeriodo').on('keyup', function () {
                    var searchText = $(this).val();
                    if (searchText.trim() !== '') {
                        $.ajax({
                            type: 'GET',
                            url: 'Controlador?menu=Cotizaciones&accion=BuscarPorPeriodo',
                            data: {periodo: searchText},
                            success: function (response) {
                                $('#cotizacionesTable').html(response);
                            }
                        });
                    } else {
                        $('#cotizacionesTable').html(originalTableData);
                    }
                });

                $('#filterEstado').on('change', function () {
                    var estado = $(this).val();
                    if (estado !== '') {
                        $.ajax({
                            type: 'GET',
                            url: 'Controlador?menu=Cotizaciones&accion=FiltrarPorEstado',
                            data: {estado: estado},
                            success: function (response) {
                                $('#cotizacionesTable').html(response);
                            }
                        });
                    } else {
                        $('#cotizacionesTable').html(originalTableData);
                    }
                });
            });
        </script>

<!-- Función para cargar detalle del cliente en el modal -->
<script>
    function cargarDetalleCliente(codCli, periodo) {
        console.log("Cargando detalles para cliente: " + codCli);
        
        $('#clienteNombre').text(codCli);
        $('#detalleClienteTable').html('<tr><td colspan="8" class="text-center"><i class="fas fa-spinner fa-spin"></i> Cargando...</td></tr>');
        
        $.ajax({
            type: 'GET',
            url: 'Controlador?menu=Cotizaciones&accion=ObtenerCotizacionesCliente',
            data: {codCli: codCli},
            dataType: 'json',
            success: function (response) {
                console.log("Respuesta recibida:", response);
                
                if (response.cotizaciones && response.cotizaciones.length > 0) {
                    let html = '';
                    response.cotizaciones.forEach(function(cot) {
                        let estadoBadge = cot.ESTADO === 'A' 
                            ? '<span class="badge bg-warning text-dark">Pendiente</span>' 
                            : '<span class="badge bg-success">Cancelado</span>';
                        
                        // Construir botones de acciones
                        let botones = '<a class="btn btn-warning btn-sm" href="Controlador?menu=Cotizaciones&accion=Editar&idCot=' + cot.ID + '" title="Editar">' +
                                     '<i class="fas fa-edit"></i></a>';
                        
                        // Agregar botón de cancelar si está pendiente
                        if (cot.ESTADO === 'A') {
                            botones += ' <a class="btn btn-danger btn-sm" href="Controlador?menu=Cotizaciones&accion=Cancelar&idCot=' + cot.ID + '" title="Cancelar" onclick="return confirm(\'¿Está seguro de cancelar esta cotización?\')">' +
                                      '<i class="fas fa-times-circle"></i></a>';
                        }
                        
                        // Agregar botón de PDF si está cancelada
                        if (cot.ESTADO === 'C') {
                            botones += ' <a class="btn btn-info btn-sm" href="Controlador?menu=Cotizaciones&accion=GenerarPDF&numCot=' + cot.NUM_COT + '&idTrans=' + cot.ID_TRANS + '" title="Comprobante">' +
                                      '<i class="fas fa-file-pdf"></i></a>';
                        }
                        
                        html += '<tr>' +
                                '<td>' + cot.ID + '</td>' +
                                '<td>' + cot.NUM_COT + '</td>' +
                                '<td>' + cot.PERIODO + '</td>' +
                                '<td>' + cot.FEC_EMISION + '</td>' +
                                '<td>' + cot.FEC_VENC + '</td>' +
                                '<td>Q ' + cot.MONTO.toFixed(2) + '</td>' +
                                '<td>' + estadoBadge + '</td>' +
                                '<td>' + botones + '</td>' +
                                '</tr>';
                    });
                    $('#detalleClienteTable').html(html);
                } else {
                    $('#detalleClienteTable').html('<tr><td colspan="8" class="text-center text-muted">No hay cotizaciones para este cliente</td></tr>');
                }
            },
            error: function (xhr, status, error) {
                console.error('Error al cargar cotizaciones:', error);
                $('#detalleClienteTable').html('<tr><td colspan="8" class="text-center text-danger">Error al cargar datos</td></tr>');
            }
        });
    }
</script>

        <script>
            function regresarPagina() {
                window.history.back();
            }
        </script>

    </body>
</html>