<%-- 
  Document   : Reportes (Vista con 9 modales independientes)
  Created on : 20/10/2025
  Author     : joans
  Descripción: Vista de Reportes con tarjetas/botones y 9 modales independientes.
               Sin lógica de backend. Cada modal tiene texto "PRUEBA — <Reporte>"
               y botón de imprimir solo esa vista.
--%>

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
            // La lista ya viene desde el Controlador cuando llamas a ListarRE
            List<Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");

            if (ajax && "empleados".equalsIgnoreCase(partial)) {
        %>
        <!-- ===== FRAGMENTO: SOLO TABLA DE EMPLEADOS ===== -->
        <h3 class="mb-3">PRUEBA — Reporte de Empleados</h3>
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
                // ¡Muy importante! cortar aquí para que NO imprima el resto de la página.
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
      <div class="report-card p-3 d-flex gap-3 align-items-start" data-bs-toggle="modal" data-bs-target="#modalUsuarios">
        <div class="icon-badge"><i class="fa-solid fa-users-gear"></i></div>
        <div>
          <div class="report-name">Usuarios</div>
          <div class="report-desc">Cuentas, roles y estados.</div>
        </div>
      </div>
    </div>

    <!-- 3) Total de clientes -->
    <div class="col">
      <div class="report-card p-3 d-flex gap-3 align-items-start" data-bs-toggle="modal" data-bs-target="#modalTotalClientes">
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

    <!-- 5) Planes -->
    <div class="col">
      <div class="report-card p-3 d-flex gap-3 align-items-start" data-bs-toggle="modal" data-bs-target="#modalPlanes">
        <div class="icon-badge"><i class="fa-solid fa-layer-group"></i></div>
        <div>
          <div class="report-name">Planes</div>
          <div class="report-desc">Catálogo de planes.</div>
        </div>
      </div>
    </div>

    <!-- 6) Planes más usados -->
    <div class="col">
      <div class="report-card p-3 d-flex gap-3 align-items-start" data-bs-toggle="modal" data-bs-target="#modalPlanesMasUsados">
        <div class="icon-badge"><i class="fa-solid fa-chart-column"></i></div>
        <div>
          <div class="report-name">Planes más usados</div>
          <div class="report-desc">Frecuencia por clientes.</div>
        </div>
      </div>
    </div>

    <!-- 7) Pagos por fechas -->
    <div class="col">
      <div class="report-card p-3 d-flex gap-3 align-items-start" data-bs-toggle="modal" data-bs-target="#modalPagosFechas">
        <div class="icon-badge"><i class="fa-solid fa-money-check-dollar"></i></div>
        <div>
          <div class="report-name">Pagos por fechas</div>
          <div class="report-desc">Movimientos por periodo.</div>
        </div>
      </div>
    </div>

    <!-- 8) Pagos por cliente -->
    <div class="col">
      <div class="report-card p-3 d-flex gap-3 align-items-start" data-bs-toggle="modal" data-bs-target="#modalPagosCliente">
        <div class="icon-badge"><i class="fa-solid fa-people-arrows"></i></div>
        <div>
          <div class="report-name">Pagos por cliente</div>
          <div class="report-desc">Totales por cliente.</div>
        </div>
      </div>
    </div>

    <!-- 9) Cotizaciones -->
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
<div class="modal fade" id="modalEmpleados" tabindex="-1" aria-hidden="true" data-bs-backdrop="static">
  <div class="modal-dialog modal-fullscreen">
    <div class="modal-content vh-100 d-flex">
      
      <!-- Header fijo -->
      <div class="modal-header sticky-top bg-white">
        <h5 class="modal-title mb-0">Reporte de Empleados</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>

      <!-- Body ocupa todo el alto restante con scroll interno -->
      <div class="modal-body flex-grow-1 overflow-auto">
        <!-- Loader -->
        <div id="empLoader" class="text-center py-4 d-none">Cargando...</div>
        <!-- Aquí se inyecta el fragmento del controlador -->
        <div id="printEmpleados" class="print-area"></div>
      </div>

      <!-- Footer fijo -->
      <div class="modal-footer sticky-bottom bg-white">
        <button type="button" class="btn btn-primary btn-print" data-print="#printEmpleados">
          <i class="fa-solid fa-print me-1"></i> Imprimir
        </button>
      </div>

    </div>
  </div>
</div>



        <!-- 2 Usuarios -->
        <div class="modal fade" id="modalUsuarios" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header"><h5 class="modal-title">Reporte de Usuarios</h5><button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button></div>
                    <div class="modal-body">
                        <div id="printUsuarios" class="print-area">
                            <h3 class="mb-2">PRUEBA — Reporte de Usuarios</h3>
                            <p class="text-muted">(Aquí irá tu tabla de usuarios)</p>
                        </div>
                    </div>
                    <div class="modal-footer"><button type="button" class="btn btn-primary btn-print" data-print="#printUsuarios"><i class="fa-solid fa-print me-1"></i> Imprimir</button></div>
                </div>
            </div>
        </div>

        <!-- 3 Total de Clientes -->
        <div class="modal fade" id="modalTotalClientes" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header"><h5 class="modal-title">Reporte de Total de Clientes</h5><button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button></div>
                    <div class="modal-body">
                        <div id="printTotalClientes" class="print-area">
                            <h3 class="mb-2">PRUEBA — Reporte de Total de Clientes</h3>
                            <p class="text-muted">(Aquí irá el conteo y desglose)</p>
                        </div>
                    </div>
                    <div class="modal-footer"><button type="button" class="btn btn-primary btn-print" data-print="#printTotalClientes"><i class="fa-solid fa-print me-1"></i> Imprimir</button></div>
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

        <!-- 5 Planes -->
        <div class="modal fade" id="modalPlanes" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header"><h5 class="modal-title">Reporte de Planes</h5><button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button></div>
                    <div class="modal-body">
                        <div id="printPlanes" class="print-area">
                            <h3 class="mb-2">PRUEBA — Reporte de Planes</h3>
                            <p class="text-muted">(Aquí irá el catálogo de planes)</p>
                        </div>
                    </div>
                    <div class="modal-footer"><button type="button" class="btn btn-primary btn-print" data-print="#printPlanes"><i class="fa-solid fa-print me-1"></i> Imprimir</button></div>
                </div>
            </div>
        </div>

        <!-- 6 Planes más usados -->
        <div class="modal fade" id="modalPlanesMasUsados" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header"><h5 class="modal-title">Reporte de Planes más Usados</h5><button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button></div>
                    <div class="modal-body">
                        <div id="printPlanesMasUsados" class="print-area">
                            <h3 class="mb-2">PRUEBA — Reporte de Planes más Usados</h3>
                            <p class="text-muted">(Aquí irá el ranking por frecuencia)</p>
                        </div>
                    </div>
                    <div class="modal-footer"><button type="button" class="btn btn-primary btn-print" data-print="#printPlanesMasUsados"><i class="fa-solid fa-print me-1"></i> Imprimir</button></div>
                </div>
            </div>
        </div>

        <!-- 7 Pagos por Fechas -->
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

        <!-- 8 Pagos por Cliente (JOINs) -->
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

        <!-- 9 Cotizaciones -->
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

        <script>
            (function () {
                // Cargamos Bootstrap en la ventana de impresión y esperamos a que TERMINE de cargar
                const BOOTSTRAP_CSS = "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css";
                const PRINT_CSS = `
            @page { size: A4; margin: 12mm; }
            html, body { height: 100%; }
            body { margin: 0; padding: 0; }
            .print-container { padding: 8px 12px; }
            h3, h4, h5 { margin-top: 0; }
            table { width: 100%; border-collapse: collapse; }
            thead th { position: sticky; top: 0; background: #f8f9fa; }
            .table { font-size: 12px; }
          `;

                function printSection(selector, title) {
                    const area = document.querySelector(selector);
                    if (!area) {
                        alert("No se encontró el contenido a imprimir.");
                        return;
                    }
                    if (!area.innerHTML.trim()) {
                        alert("El reporte aún no tiene contenido.");
                        return;
                    }

                    // 1) Abrimos una ventana vacía
                    const w = window.open("", "_blank");
                    const doc = w.document;

                    // 2) Esqueleto del documento (sin document.write)
                    const html = doc.documentElement;
                    const head = doc.head || doc.getElementsByTagName("head")[0];
                    const body = doc.body || doc.getElementsByTagName("body")[0];

                    // Título
                    doc.title = title ? ("Imprimir - " + title) : "Imprimir";

                    // 3) Cargar Bootstrap y nuestro CSS. Imprimir SOLO cuando haya cargado el CSS.
                    const link = doc.createElement("link");
                    link.rel = "stylesheet";
                    link.href = BOOTSTRAP_CSS;

                    const style = doc.createElement("style");
                    style.textContent = PRINT_CSS;

                    head.appendChild(link);
                    head.appendChild(style);

                    // 4) Clonamos el contenido de la modal y lo adoptamos en la nueva ventana
                    const wrapper = doc.createElement("div");
                    wrapper.className = "print-container container";
                    // Importa el nodo con todos sus hijos (true)
                    const clone = area.cloneNode(true);
                    // Asegúrate de que el clon sea adoptado por el nuevo documento
                    wrapper.appendChild(doc.importNode(clone, true));
                    body.appendChild(wrapper);

                    // 5) Esperar a que cargue Bootstrap antes de imprimir (link.onload es clave)
                    link.onload = () => {
                        // Un pequeño delay ayuda a que el motor renderice antes de abrir la vista de impresión
                        setTimeout(() => {
                            w.focus();
                            w.print();
                        }, 100);
                    };

                    // Fallback: si por alguna razón no dispara onload (cache etc.), imprime tras 800ms
                    setTimeout(() => {
                        try {
                            w.focus();
                            w.print();
                        } catch (_) {
                        }
                    }, 800);
                }

                // Un solo listener para TODAS las modales
                document.addEventListener("click", (e) => {
                    const btn = e.target.closest(".btn-print");
                    if (!btn)
                        return;
                    const sel = btn.getAttribute("data-print");
                    const modal = btn.closest(".modal-content");
                    const title = modal?.querySelector(".modal-title")?.textContent?.trim() || "Reporte";
                    printSection(sel, title);
                });
            })();
        </script>

        <script>
            document.addEventListener('click', async (e) => {
                const card = e.target.closest('.report-card[data-bs-target="#modalEmpleados"][data-url]');
                if (!card)
                    return;

                const url = card.getAttribute('data-url');
                const container = document.getElementById('printEmpleados');
                const loader = document.getElementById('empLoader');
                container.innerHTML = '';
                if (loader)
                    loader.classList.remove('d-none');

                try {
                    const resp = await fetch(url, {headers: {'X-Requested-With': 'XMLHttpRequest'}});
                    const html = await resp.text();
                    container.innerHTML = html;
                } catch (err) {
                    container.innerHTML = '<div class="alert alert-danger">No se pudo cargar el reporte.</div>';
                    console.error(err);
                } finally {
                    if (loader)
                        loader.classList.add('d-none');
                }
            });
        </script>


    </body>
</html>