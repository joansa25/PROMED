<%-- 
  Document   : Reportes (Vista con 9 modales independientes)
  Created on : 20/10/2025
  Author     : joans
  Descripción: Vista de Reportes con tarjetas/botones y 9 modales independientes.
               Sin lógica de backend. Cada modal tiene texto "PRUEBA — <Reporte>"
               y botón de imprimir solo esa vista.
--%>
<%@page import="Modelo.Cliente"%>
<%@page import="Modelo.User"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="Modelo.Empleado" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Reportes (Vista con modales)</title>

        <!-- Bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet"/>

        <style>
            :root{
                --ink:#0f172a;
                --muted:#64748b;
            }
            body{
                background: linear-gradient(180deg,#ffffff 0%, #f8fafc 100%);
            }
            .page-header{
                border-bottom: 1px solid #e2e8f0;
            }
            .page-title{
                color: var(--ink);
                font-weight: 800;
            }
            .subtitle{
                color: var(--muted);
            }

            .report-card{
                cursor:pointer;
                border:1px solid #e2e8f0;
                border-radius:1rem;
                background:#fff;
                height:100%;
                transition:.2s;
            }
            .report-card:hover{
                transform:translateY(-2px);
                box-shadow:0 12px 30px rgba(2,6,23,.08);
                border-color:#cbd5e1;
            }
            .icon-badge{
                width:48px;
                height:48px;
                border-radius:12px;
                display:flex;
                align-items:center;
                justify-content:center;
                background:#e0f2fe;
                color:#0369a1;
                font-size:1.3rem;
            }
            .report-name{
                font-weight:700;
                color:var(--ink);
            }
            .report-desc{
                color:var(--muted);
                font-size:.925rem;
            }

.modal-body thead th {
  position: sticky;
  top: 0;
  background: #f8f9fa;
  z-index: 1;
}

        </style>
    </head>
    <body>

   <%
    boolean ajax = "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    String partial = request.getParameter("partial");
    List<Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");
    List<User> usuarios = (List<User>) request.getAttribute("usuarios");

    // ===== FRAGMENTO DE EMPLEADOS =====
    if (ajax && "empleados".equalsIgnoreCase(partial)) {
%>
<!-- ===== FRAGMENTO: SOLO TABLA DE EMPLEADOS ===== -->
<h3 class="mb-3">Reporte de Empleados</h3>
<div class="table-responsive">
    <table class="table table-sm table-striped align-middle">
        <thead class="table-light">
            <tr>
                <th>#</th><th>COD_EMPD</th><th>DPI</th><th>N_IGSS</th><th>NIT</th>
                <th>NOMBRES</th><th>APELLIDOS</th>
                <th>CELULAR</th><th>CORREO</th><th>ESTADO</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (empleados != null) {
                    int i = 1;
                    for (Empleado em : empleados) {
            %>
            <tr>
                <td><%= i++%></td>
                <td><%= em.getCOC_EMPD()%></td>
                <td><%= em.getDPI()%></td>
                <td><%= em.getN_IGSS()%></td>
                <td><%= em.getNIT()%></td>
                <td><%= em.getNOMBRES()%></td>
                <td><%= em.getAPELLIDOS()%></td>
                <td><%= em.getCELULAR()%></td>
                <td><%= em.getCorreo()%></td>
                <td>
                    <span class="badge <%= "A".equals(em.getEstado()) ? "bg-success" : "bg-secondary"%>">
                        <%= em.getEstado()%>
                    </span>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</div>
<%
        return;
    }
    
    // ===== FRAGMENTO DE USUARIOS =====
    if (ajax && "usuarios".equalsIgnoreCase(partial)) {
%>
<!-- ===== FRAGMENTO: SOLO TABLA DE USUARIOS ===== -->
<h3 class="mb-3">Reporte de Usuarios</h3>
<div class="table-responsive">
    <table class="table table-sm table-striped align-middle">
        <thead class="table-light">
            <tr>
                <th>#</th><th>CÓD. USER</th><th>USUARIO</th><th>ROL</th>
                <th>NOMBRES</th><th>APELLIDOS</th><th>CORREO</th><th>ESTADO</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (usuarios != null) {
                    int i = 1;
                    for (User user : usuarios) {
            %>
            <tr>
                <td><%= i++%></td>
                <td><%= user.getCod_user()%></td>
                <td><%= user.getUser_US()%></td>
                <td><%= user.getRol_Us()%></td>
                <td><%= user.getNomb_Us()%></td>
                <td><%= user.getApe_Us()%></td>
                <td><%= user.getCorr_Us()%></td>
                <td>
                    <span class="badge <%= "A".equals(user.getEsdado()) ? "bg-success" : "bg-secondary"%>">
                        <%= user.getEsdado()%>
                    </span>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</div>
<%
        return;
    }

// ===== FRAGMENTO DE CLIENTES =====
if (ajax && "clientes".equalsIgnoreCase(partial)) {
%>
<!-- ===== FRAGMENTO: SOLO TABLA DE CLIENTES ===== -->
<h3 class="mb-3">Reporte de Total de Clientes</h3>
<div class="table-responsive">
    <table class="table table-sm table-striped align-middle">
        <thead class="table-light">
            <tr>
                <th>#</th><th>CÓD. CLI</th><th>DPI</th><th>NOMBRES</th>
                <th>APELLIDOS</th><th>CELULAR</th><th>CORREO</th>
                <th>DIRECCIÓN</th><th>NIT</th><th>PLAN</th>
                <th>ZONA</th><th>USER</th><th>ESTADO</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
                if (clientes != null) {
                    int i = 1;
                    for (Cliente cli : clientes) {
            %>
            <tr>
                <td><%= i++%></td>
                <td><%= cli.getCOD_CLI()%></td>
                <td><%= cli.getDPI()%></td>
                <td><%= cli.getNOMBRES()%></td>
                <td><%= cli.getAPELLIDOS()%></td>
                <td><%= cli.getCELULAR()%></td>
                <td><%= cli.getCORREO()%></td>
                <td><%= cli.getDIRECCION()%></td>
                <td><%= cli.getNIT()%></td>
                <td><%= cli.getCOD_PLAN()%></td>
                <td><%= cli.getCOD_ZONA()%></td>
                <td><%= cli.getCOD_USER()%></td>
                <td>
                    <span class="badge <%= "A".equals(cli.getESTADO()) ? "bg-success" : "bg-secondary"%>">
                        <%= cli.getESTADO()%>
                    </span>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</div>
<%
        return;
    }
// ===== FRAGMENTO DE PLANES MÁS USADOS =====
if (ajax && "planesmasusados".equalsIgnoreCase(partial)) {
%>
<!-- ===== FRAGMENTO: SOLO TABLA DE PLANES MÁS USADOS ===== -->
<h3 class="mb-3">Reporte de Planes Más Usados</h3>
<div class="table-responsive">
    <table class="table table-sm table-striped align-middle">
        <thead class="table-light">
            <tr>
                <th>#</th>
                <th>CÓDIGO PLAN</th>
                <th>NOMBRE PLAN</th>
                <th>DESCRIPCIÓN</th>
                <th>TOTAL CLIENTES</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Map<String, Object>> planesMasUsados = 
                    (List<Map<String, Object>>) request.getAttribute("planesMasUsados");
                
                if (planesMasUsados != null) {
                    int i = 1;
                    for (Map<String, Object> plan : planesMasUsados) {
            %>
            <tr>
                <td><%= i++%></td>
                <td><%= plan.get("COD_PLAN")%></td>
                <td><%= plan.get("NOMBRE_PLAN")%></td>
                <td><%= plan.get("DESCRIPCION")%></td>
                <td>
                    <span class="badge bg-primary">
                        <%= plan.get("TOTAL_CLIENTES")%> clientes
                    </span>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</div>
<%
        return;
    }
%>
 
        
        
        <header class="page-header py-4 mb-2">
            <div class="container">
                <h1 class="page-title h3 mb-1">Reportes del Sistema (Vista)</h1>
                <p class="subtitle mb-0">Haz clic en un reporte para abrir su modal independiente.</p>
            </div>
        </header>

       <main class="container pb-5">
  <!-- 1 col en móvil, 2 en tablet, 3 en desktop -->
  <div class="row g-3 row-cols-1 row-cols-md-2 row-cols-xl-3">

    <!-- 1) Empleados -->
    <div class="col">
      <div class="report-card p-3 d-flex gap-3 align-items-start"
           data-url="Controlador?menu=Empleados&accion=ListarRE&partial=empleados"
           data-bs-toggle="modal" data-bs-target="#modalEmpleados">
        <div class="icon-badge"><i class="fa-solid fa-id-badge"></i></div>
        <div>
          <div class="report-name">Empleados</div>
          <div class="report-desc">Listado general de empleados.</div>
        </div>
      </div>
    </div>

  <!-- 2) Usuarios -->
<div class="col">
  <div class="report-card p-3 d-flex gap-3 align-items-start" 
       data-url="Controlador?menu=Seguridad&accion=ListarRE&partial=usuarios"
       data-bs-toggle="modal" data-bs-target="#modalUsuarios">
    <div class="icon-badge"><i class="fa-solid fa-users-gear"></i></div>
    <div>
      <div class="report-name">Usuarios</div>
      <div class="report-desc">Cuentas, roles y estados.</div>
    </div>
  </div>
</div>

 <!-- 3) Total de clientes -->
<div class="col">
  <div class="report-card p-3 d-flex gap-3 align-items-start" 
       data-url="Controlador?menu=Clientes&accion=ListarRE&partial=clientes"
       data-bs-toggle="modal" data-bs-target="#modalTotalClientes">
    <div class="icon-badge"><i class="fa-solid fa-user-check"></i></div>
    <div>
      <div class="report-name">Total de clientes</div>
      <div class="report-desc">Conteo global por estado/plan.</div>
    </div>
  </div>
</div>

    <!-- 4) Incidencias por fecha -->
    <div class="col">
      <div class="report-card p-3 d-flex gap-3 align-items-start" data-bs-toggle="modal" data-bs-target="#modalIncidencias">
        <div class="icon-badge"><i class="fa-solid fa-triangle-exclamation"></i></div>
        <div>
          <div class="report-name">Incidencias por fecha</div>
          <div class="report-desc">Vista por periodo.</div>
        </div>
      </div>
    </div>



<!-- 5) Planes más usados -->
<div class="col">
  <div class="report-card p-3 d-flex gap-3 align-items-start" 
       data-url="Controlador?menu=PlanesMasUsados&accion=ListarRE&partial=planesmasusados"
       data-bs-toggle="modal" data-bs-target="#modalPlanesMasUsados">
    <div class="icon-badge"><i class="fa-solid fa-chart-column"></i></div>
    <div>
      <div class="report-name">Planes más usados</div>
      <div class="report-desc">Frecuencia por clientes.</div>
    </div>
  </div>
</div>

    <!-- 6) Pagos por fechas -->
    <div class="col">
      <div class="report-card p-3 d-flex gap-3 align-items-start" data-bs-toggle="modal" data-bs-target="#modalPagosFechas">
        <div class="icon-badge"><i class="fa-solid fa-money-check-dollar"></i></div>
        <div>
          <div class="report-name">Pagos por fechas</div>
          <div class="report-desc">Movimientos por periodo.</div>
        </div>
      </div>
    </div>

    <!-- 7) Pagos por cliente -->
    <div class="col">
      <div class="report-card p-3 d-flex gap-3 align-items-start" data-bs-toggle="modal" data-bs-target="#modalPagosCliente">
        <div class="icon-badge"><i class="fa-solid fa-people-arrows"></i></div>
        <div>
          <div class="report-name">Pagos por cliente</div>
          <div class="report-desc">Totales por cliente.</div>
        </div>
      </div>
    </div>

    <!-- 8) Cotizaciones -->
    <div class="col">
      <div class="report-card p-3 d-flex gap-3 align-items-start" data-bs-toggle="modal" data-bs-target="#modalCotizaciones">
        <div class="icon-badge"><i class="fa-solid fa-file-invoice-dollar"></i></div>
        <div>
          <div class="report-name">Cotizaciones</div>
          <div class="report-desc">Vista similar Admin/Cobrador.</div>
        </div>
      </div>
    </div>

  </div>
</main>






        <!-- ===== 9 MODALES INDEPENDIENTES (solo vista) ===== -->

        <!-- Utilidad: plantilla de modal -->
        <!-- Para evitar repetir estilos, todas comparten la misma estructura -->

<!-- Modal Empleados (pantalla completa y con header/footer fijos) -->
<!-- Modal Empleados (pantalla completa y con header/footer fijos) -->
<div class="modal fade" id="modalEmpleados" tabindex="-1" aria-hidden="true" data-bs-backdrop="static">
  <div class="modal-dialog modal-fullscreen">
    <div class="modal-content vh-100 d-flex flex-column">
      
      <!-- Header fijo -->
      <div class="modal-header sticky-top bg-white border-bottom">
        <h5 class="modal-title mb-0">Reporte de Empleados</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>

      <!-- Body ocupa todo el alto restante con scroll interno -->
      <div class="modal-body flex-grow-1 overflow-auto">
        <!-- Loader -->
        <div id="empLoader" class="text-center py-4 d-none">
          <div class="spinner-border text-primary" role="status">
            <span class="visually-hidden">Cargando...</span>
          </div>
        </div>
        <!-- Aquí se inyecta el fragmento del controlador -->
        <div id="printEmpleados" class="print-area"></div>
      </div>

      <!-- Footer fijo con botones -->
      <div class="modal-footer sticky-bottom bg-white border-top">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
          <i class="fa-solid fa-times me-1"></i> Cerrar
        </button>
        <!-- BOTÓN DE IMPRESIÓN: Abre el diálogo de impresión del navegador -->
        <button type="button" class="btn btn-primary" id="btnImprimirPDF">
          <i class="fa-solid fa-print me-1"></i> Imprimir PDF
        </button>
      </div>

    </div>
  </div>
</div>


        <!-- 2 Usuarios -->
     <!-- Modal Usuarios (pantalla completa) -->
<div class="modal fade" id="modalUsuarios" tabindex="-1" aria-hidden="true" data-bs-backdrop="static">
  <div class="modal-dialog modal-fullscreen">
    <div class="modal-content vh-100 d-flex flex-column">
      
      <div class="modal-header sticky-top bg-white border-bottom">
        <h5 class="modal-title mb-0">Reporte de Usuarios</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>

      <div class="modal-body flex-grow-1 overflow-auto">
        <div id="userLoader" class="text-center py-4 d-none">
          <div class="spinner-border text-primary" role="status">
            <span class="visually-hidden">Cargando...</span>
          </div>
        </div>
        <div id="printUsuarios" class="print-area"></div>
      </div>

      <div class="modal-footer sticky-bottom bg-white border-top">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
          <i class="fa-solid fa-times me-1"></i> Cerrar
        </button>
        <button type="button" class="btn btn-primary" id="btnImprimirPDFUsuarios">
          <i class="fa-solid fa-print me-1"></i> Generar PDF
        </button>
      </div>

    </div>
  </div>
</div>

   <!-- Modal Total de Clientes (pantalla completa) -->
<div class="modal fade" id="modalTotalClientes" tabindex="-1" aria-hidden="true" data-bs-backdrop="static">
  <div class="modal-dialog modal-fullscreen">
    <div class="modal-content vh-100 d-flex flex-column">
      
      <div class="modal-header sticky-top bg-white border-bottom">
        <h5 class="modal-title mb-0">Reporte de Total de Clientes</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>

      <div class="modal-body flex-grow-1 overflow-auto">
        <div id="clienteLoader" class="text-center py-4 d-none">
          <div class="spinner-border text-primary" role="status">
            <span class="visually-hidden">Cargando...</span>
          </div>
        </div>
        <div id="printTotalClientes" class="print-area"></div>
      </div>

      <div class="modal-footer sticky-bottom bg-white border-top">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
          <i class="fa-solid fa-times me-1"></i> Cerrar
        </button>
        <button type="button" class="btn btn-primary" id="btnImprimirPDFClientes">
          <i class="fa-solid fa-print me-1"></i> Generar PDF
        </button>
      </div>

    </div>
  </div>
</div>

        <!-- 4 Incidencias por Fecha -->
        <div class="modal fade" id="modalIncidencias" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header"><h5 class="modal-title">Reporte de Incidencias por Fecha</h5><button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button></div>
                    <div class="modal-body">
                        <div id="printIncidencias" class="print-area">
                            <h3 class="mb-2">PRUEBA — Reporte de Incidencias por Fecha</h3>
                            <p class="text-muted">(Aquí irá el listado por rango)</p>
                        </div>
                    </div>
                    <div class="modal-footer"><button type="button" class="btn btn-primary btn-print" data-print="#printIncidencias"><i class="fa-solid fa-print me-1"></i> Imprimir</button></div>
                </div>
            </div>
        </div>

   

<!-- Modal Planes Más Usados (pantalla completa) -->
<div class="modal fade" id="modalPlanesMasUsados" tabindex="-1" aria-hidden="true" data-bs-backdrop="static">
  <div class="modal-dialog modal-fullscreen">
    <div class="modal-content vh-100 d-flex flex-column">
      
      <div class="modal-header sticky-top bg-white border-bottom">
        <h5 class="modal-title mb-0">Reporte de Planes Más Usados</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>

      <div class="modal-body flex-grow-1 overflow-auto">
        <div id="planesLoader" class="text-center py-4 d-none">
          <div class="spinner-border text-primary" role="status">
            <span class="visually-hidden">Cargando...</span>
          </div>
        </div>
        <div id="printPlanesMasUsados" class="print-area"></div>
      </div>

      <div class="modal-footer sticky-bottom bg-white border-top">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
          <i class="fa-solid fa-times me-1"></i> Cerrar
        </button>
        <button type="button" class="btn btn-primary" id="btnImprimirPDFPlanes">
          <i class="fa-solid fa-print me-1"></i> Generar PDF
        </button>
      </div>

    </div>
  </div>
</div>

        <!-- 6 Pagos por Fechas -->
        <div class="modal fade" id="modalPagosFechas" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header"><h5 class="modal-title">Reporte de Pagos por Fechas</h5><button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button></div>
                    <div class="modal-body">
                        <div id="printPagosFechas" class="print-area">
                            <h3 class="mb-2">PRUEBA — Reporte de Pagos por Fechas</h3>
                            <p class="text-muted">(Aquí irá el detalle por periodo)</p>
                        </div>
                    </div>
                    <div class="modal-footer"><button type="button" class="btn btn-primary btn-print" data-print="#printPagosFechas"><i class="fa-solid fa-print me-1"></i> Imprimir</button></div>
                </div>
            </div>
        </div>

        <!-- 7 Pagos por Cliente (JOINs) -->
        <div class="modal fade" id="modalPagosCliente" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header"><h5 class="modal-title">Reporte de Pagos por Cliente</h5><button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button></div>
                    <div class="modal-body">
                        <div id="printPagosCliente" class="print-area">
                            <h3 class="mb-2">PRUEBA — Reporte de Pagos por Cliente</h3>
                            <p class="text-muted">(Aquí irá el total pagado por cliente)</p>
                        </div>
                    </div>
                    <div class="modal-footer"><button type="button" class="btn btn-primary btn-print" data-print="#printPagosCliente"><i class="fa-solid fa-print me-1"></i> Imprimir</button></div>
                </div>
            </div>
        </div>

        <!-- 8 Cotizaciones -->
        <div class="modal fade" id="modalCotizaciones" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-xl modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header"><h5 class="modal-title">Reporte de Cotizaciones</h5><button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button></div>
                    <div class="modal-body">
                        <div id="printCotizaciones" class="print-area">
                            <h3 class="mb-2">PRUEBA — Reporte de Cotizaciones</h3>
                            <p class="text-muted">(Aquí irá la tabla como en Admin/Cobrador)</p>
                        </div>
                    </div>
                    <div class="modal-footer"><button type="button" class="btn btn-primary btn-print" data-print="#printCotizaciones"><i class="fa-solid fa-print me-1"></i> Imprimir</button></div>
                </div>
            </div>
        </div>

        <!-- JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

         <!-- ✅ SCRIPT CORRECTO PARA EL BOTÓN DE PDF -->
        <script>
          // Cargar datos en el modal al hacer clic en la tarjeta
          document.addEventListener('click', async (e) => {
            const card = e.target.closest('.report-card[data-bs-target="#modalEmpleados"][data-url]');
            if (!card) return;

            const url = card.getAttribute('data-url');
            const container = document.getElementById('printEmpleados');
            const loader = document.getElementById('empLoader');
            
            container.innerHTML = '';
            if (loader) loader.classList.remove('d-none');

            try {
              const resp = await fetch(url, {headers: {'X-Requested-With': 'XMLHttpRequest'}});
              const html = await resp.text();
              container.innerHTML = html;
            } catch (err) {
              container.innerHTML = '<div class="alert alert-danger">No se pudo cargar el reporte.</div>';
              console.error(err);
            } finally {
              if (loader) loader.classList.add('d-none');
            }
          });

          // ✅ Generar PDF usando el controlador (iText)
          document.getElementById('btnImprimirPDF')?.addEventListener('click', function() {
            const url = 'Controlador?menu=Empleados&accion=GenerarPDF';
            window.open(url, '_blank');
            console.log('Generando PDF desde: ' + url);
          });
        </script>

<script>
  // ✅ Cargar datos de USUARIOS en el modal
  document.addEventListener('click', async (e) => {
    const card = e.target.closest('.report-card[data-bs-target="#modalUsuarios"][data-url]');
    if (!card) return;

    const url = card.getAttribute('data-url');
    const container = document.getElementById('printUsuarios');
    const loader = document.getElementById('userLoader');
    
    container.innerHTML = '';
    if (loader) loader.classList.remove('d-none');

    try {
      const resp = await fetch(url, {headers: {'X-Requested-With': 'XMLHttpRequest'}});
      const html = await resp.text();
      container.innerHTML = html;
    } catch (err) {
      container.innerHTML = '<div class="alert alert-danger">No se pudo cargar el reporte.</div>';
      console.error(err);
    } finally {
      if (loader) loader.classList.add('d-none');
    }
  });

  // ✅ Generar PDF de Usuarios usando el controlador
  document.getElementById('btnImprimirPDFUsuarios')?.addEventListener('click', function() {
    const url = 'Controlador?menu=Seguridad&accion=GenerarPDF';
    window.open(url, '_blank');
    console.log('Generando PDF de usuarios desde: ' + url);
  });
</script>

<script>
  // ✅ Cargar datos de CLIENTES en el modal
  document.addEventListener('click', async (e) => {
    const card = e.target.closest('.report-card[data-bs-target="#modalTotalClientes"][data-url]');
    if (!card) return;

    const url = card.getAttribute('data-url');
    const container = document.getElementById('printTotalClientes');
    const loader = document.getElementById('clienteLoader');
    
    container.innerHTML = '';
    if (loader) loader.classList.remove('d-none');

    try {
      const resp = await fetch(url, {headers: {'X-Requested-With': 'XMLHttpRequest'}});
      const html = await resp.text();
      container.innerHTML = html;
    } catch (err) {
      container.innerHTML = '<div class="alert alert-danger">No se pudo cargar el reporte.</div>';
      console.error(err);
    } finally {
      if (loader) loader.classList.add('d-none');
    }
  });

  // ✅ Generar PDF de Clientes usando el controlador
  document.getElementById('btnImprimirPDFClientes')?.addEventListener('click', function() {
    const url = 'Controlador?menu=Clientes&accion=GenerarPDF';
    window.open(url, '_blank');
    console.log('Generando PDF de clientes desde: ' + url);
  });
</script>


<script>
  // ✅ Cargar datos de PLANES MÁS USADOS en el modal
  document.addEventListener('click', async (e) => {
    const card = e.target.closest('.report-card[data-bs-target="#modalPlanesMasUsados"][data-url]');
    if (!card) return;

    const url = card.getAttribute('data-url');
    const container = document.getElementById('printPlanesMasUsados');
    const loader = document.getElementById('planesLoader');
    
    container.innerHTML = '';
    if (loader) loader.classList.remove('d-none');

    try {
      const resp = await fetch(url, {headers: {'X-Requested-With': 'XMLHttpRequest'}});
      const html = await resp.text();
      container.innerHTML = html;
    } catch (err) {
      container.innerHTML = '<div class="alert alert-danger">No se pudo cargar el reporte.</div>';
      console.error(err);
    } finally {
      if (loader) loader.classList.add('d-none');
    }
  });

  // ✅ Generar PDF de Planes Más Usados
  document.getElementById('btnImprimirPDFPlanes')?.addEventListener('click', function() {
    const url = 'Controlador?menu=PlanesMasUsados&accion=GenerarPDF';
    window.open(url, '_blank');
    console.log('Generando PDF de planes más usados desde: ' + url);
  });
</script>
    </body>
</html>