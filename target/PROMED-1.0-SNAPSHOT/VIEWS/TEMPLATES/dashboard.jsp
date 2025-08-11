<%-- 
    Document   : dashboard
    Created on : 09/08/2025
    Author     : joans
--%>

<%@page import="Modelo.DashboardData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>


<body class="bg-light">
    
<%
    // Obtener los datos del dashboard desde la sesión
    DashboardData dashboardData = (DashboardData) session.getAttribute("dashboardData");
    
    // Valores por defecto en caso de que no existan datos en sesión
    int empleadosActivos = 2;
    int clientesActivos = 0;
    int planesActivos = 0;
    int incidenciasPendientes = 0;
    
    if (dashboardData != null) {
        empleadosActivos = dashboardData.getEmpleadosActivos();
        clientesActivos = dashboardData.getClientesActivos();
        planesActivos = dashboardData.getPlanesActivos();
    } else {
        // También intentar obtener datos individuales si existen
        Integer empActivos = (Integer) session.getAttribute("empleadosActivos");
        Integer cliActivos = (Integer) session.getAttribute("clientesActivos");
        Integer planActivos = (Integer) session.getAttribute("planesActivos");
        Integer incPendientes = (Integer) session.getAttribute("incidenciasPendientes");
        
        if (empActivos != null) empleadosActivos = empActivos;
        if (cliActivos != null) clientesActivos = cliActivos;
        if (planActivos != null) planesActivos = planActivos;
        if (incPendientes != null) incidenciasPendientes = incPendientes;
    }
%>
<%
    out.println("Empleados en sesión: " + session.getAttribute("empleadosActivos"));
    out.println("Clientes en sesión: " + session.getAttribute("clientesActivos"));
%>

<div class="container-fluid py-4">
    <!-- Header del Dashboard -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">
            <i class="fas fa-chart-line me-2 text-primary"></i>Dashboard Principal
        </h1>
        <small class="text-muted">
            <i class="fas fa-clock me-1"></i>
             <span id="currentTime"></span>
        </small>
    </div>

    <!-- Tarjetas de estadísticas -->
    <div class="row mb-4">
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                Empleados Activos
                            </div>
                             <div class="h5 mb-0 font-weight-bold text-gray-800">
                           <%
    out.println("" + session.getAttribute("empleadosActivos"));
%>
                             </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-users fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                Total Clientes
                            </div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">
                            <%
    out.println(" " + session.getAttribute("clientesActivos"));
%>
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-handshake fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                PLANES DISPONIBLES
                            </div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800"> <%
    out.println(" " + session.getAttribute("planesActivos"));
%></div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                Incidencias Pendientes
                            </div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><%= incidenciasPendientes %></div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-exclamation-triangle fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Resto del código HTML permanece igual... -->
    <!-- Gráficas principales -->
    <div class="row">
        <!-- Gráfico de líneas -->
        <div class="col-xl-8 col-lg-7">
            <div class="card shadow mb-4">
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">
                        <i class="fas fa-chart-area me-2"></i>Empleados Registrados por Mes
                    </h6>
                    <div class="dropdown no-arrow">
                        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
                            <div class="dropdown-header">Opciones:</div>
                            <a class="dropdown-item" href="#">Ver detalles</a>
                            <a class="dropdown-item" href="#">Exportar datos</a>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <div class="chart-area">
                        <canvas id="empleadosChart" style="height: 320px;"></canvas>
                    </div>
                </div>
            </div>
        </div>

        <!-- Gráfico de dona -->
        <div class="col-xl-4 col-lg-5">
            <div class="card shadow mb-4">
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">
                        <i class="fas fa-chart-pie me-2"></i>Distribución por Departamento
                    </h6>
                </div>
                <div class="card-body">
                    <div class="chart-pie pt-4 pb-2">
                        <canvas id="departamentosChart" style="height: 320px;"></canvas>
                    </div>
                    <div class="mt-4 text-center small">
                        <span class="mr-2">
                            <i class="fas fa-circle text-primary"></i> Administración
                        </span>
                        <span class="mr-2">
                            <i class="fas fa-circle text-success"></i> Operaciones
                        </span>
                        <span class="mr-2">
                            <i class="fas fa-circle text-info"></i> Ventas
                        </span>
                        <br>
                        <span class="mr-2">
                            <i class="fas fa-circle text-warning"></i> IT
                        </span>
                        <span class="mr-2">
                            <i class="fas fa-circle text-danger"></i> RRHH
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Segunda fila de gráficas -->
    <div class="row">
        <!-- Gráfico de barras -->
        <div class="col-xl-8 col-lg-7">
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">
                        <i class="fas fa-chart-bar me-2"></i>Rendimiento Trimestral
                    </h6>
                </div>
                <div class="card-body">
                    <div class="chart-bar">
                        <canvas id="rendimientoChart" style="height: 300px;"></canvas>
                    </div>
                </div>
            </div>
        </div>

        <!-- Panel de actividad reciente -->
        <div class="col-xl-4 col-lg-5">
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">
                        <i class="fas fa-list me-2"></i>Actividad Reciente
                    </h6>
                </div>
                <div class="card-body">
                    <div class="list-group list-group-flush">
                        <div class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-2">
                            <div class="me-3">
                                <i class="fas fa-user-plus text-success me-2"></i>
                                <span class="small">Nuevo empleado registrado</span>
                            </div>
                            <small class="text-muted">2h</small>
                        </div>
                        <div class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-2">
                            <div class="me-3">
                                <i class="fas fa-exclamation-triangle text-warning me-2"></i>
                                <span class="small">Incidencia reportada</span>
                            </div>
                            <small class="text-muted">4h</small>
                        </div>
                        <div class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-2">
                            <div class="me-3">
                                <i class="fas fa-building text-primary me-2"></i>
                                <span class="small">Nueva empresa cliente</span>
                            </div>
                            <small class="text-muted">1d</small>
                        </div>
                        <div class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-2">
                            <div class="me-3">
                                <i class="fas fa-check text-success me-2"></i>
                                <span class="small">Proyecto completado</span>
                            </div>
                            <small class="text-muted">2d</small>
                        </div>
                        <div class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-2">
                            <div class="me-3">
                                <i class="fas fa-user-edit text-info me-2"></i>
                                <span class="small">Perfil actualizado</span>
                            </div>
                            <small class="text-muted">3d</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <!-- Gráficas principales -->
    <div class="row">
        <!-- Gráfico de líneas -->
        <div class="col-xl-8 col-lg-7">
            <div class="card shadow mb-4">
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">
                        <i class="fas fa-chart-area me-2"></i>Empleados Registrados por Mes
                    </h6>
                    <div class="dropdown no-arrow">
                        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
                            <div class="dropdown-header">Opciones:</div>
                            <a class="dropdown-item" href="#">Ver detalles</a>
                            <a class="dropdown-item" href="#">Exportar datos</a>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <div class="chart-area">
                        <canvas id="empleadosChart" style="height: 320px;"></canvas>
                    </div>
                </div>
            </div>
        </div>

        <!-- Gráfico de dona -->
        <div class="col-xl-4 col-lg-5">
            <div class="card shadow mb-4">
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">
                        <i class="fas fa-chart-pie me-2"></i>Distribución por Departamento
                    </h6>
                </div>
                <div class="card-body">
                    <div class="chart-pie pt-4 pb-2">
                        <canvas id="departamentosChart" style="height: 320px;"></canvas>
                    </div>
                    <div class="mt-4 text-center small">
                        <span class="mr-2">
                            <i class="fas fa-circle text-primary"></i> Administración
                        </span>
                        <span class="mr-2">
                            <i class="fas fa-circle text-success"></i> Operaciones
                        </span>
                        <span class="mr-2">
                            <i class="fas fa-circle text-info"></i> Ventas
                        </span>
                        <br>
                        <span class="mr-2">
                            <i class="fas fa-circle text-warning"></i> IT
                        </span>
                        <span class="mr-2">
                            <i class="fas fa-circle text-danger"></i> RRHH
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Segunda fila de gráficas -->
    <div class="row">
        <!-- Gráfico de barras -->
        <div class="col-xl-8 col-lg-7">
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">
                        <i class="fas fa-chart-bar me-2"></i>Rendimiento Trimestral
                    </h6>
                </div>
                <div class="card-body">
                    <div class="chart-bar">
                        <canvas id="rendimientoChart" style="height: 300px;"></canvas>
                    </div>
                </div>
            </div>
        </div>

        <!-- Panel de actividad reciente -->
        <div class="col-xl-4 col-lg-5">
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">
                        <i class="fas fa-list me-2"></i>Actividad Reciente
                    </h6>
                </div>
                <div class="card-body">
                    <div class="list-group list-group-flush">
                        <div class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-2">
                            <div class="me-3">
                                <i class="fas fa-user-plus text-success me-2"></i>
                                <span class="small">Nuevo empleado registrado</span>
                            </div>
                            <small class="text-muted">2h</small>
                        </div>
                        <div class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-2">
                            <div class="me-3">
                                <i class="fas fa-exclamation-triangle text-warning me-2"></i>
                                <span class="small">Incidencia reportada</span>
                            </div>
                            <small class="text-muted">4h</small>
                        </div>
                        <div class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-2">
                            <div class="me-3">
                                <i class="fas fa-building text-primary me-2"></i>
                                <span class="small">Nueva empresa cliente</span>
                            </div>
                            <small class="text-muted">1d</small>
                        </div>
                        <div class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-2">
                            <div class="me-3">
                                <i class="fas fa-check text-success me-2"></i>
                                <span class="small">Proyecto completado</span>
                            </div>
                            <small class="text-muted">2d</small>
                        </div>
                        <div class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-2">
                            <div class="me-3">
                                <i class="fas fa-user-edit text-info me-2"></i>
                                <span class="small">Perfil actualizado</span>
                            </div>
                            <small class="text-muted">3d</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

<script>
    // Actualizar fecha y hora
    function updateTime() {
        const now = new Date();
        const timeString = now.toLocaleString('es-GT', {
            year: 'numeric',
            month: 'long',
            day: 'numeric',
            hour: '2-digit',
            minute: '2-digit'
        });
        document.getElementById('currentTime').textContent = timeString;
    }
    
    // Actualizar tiempo cada minuto
    updateTime();
    setInterval(updateTime, 60000);

    // Inicializar gráficas
    document.addEventListener('DOMContentLoaded', function() {
        // Gráfico de línea - Empleados por mes
        const ctx1 = document.getElementById('empleadosChart').getContext('2d');
        const empleadosChart = new Chart(ctx1, {
            type: 'line',
            data: {
                labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio'],
                datasets: [{
                    label: 'Empleados Nuevos',
                    data: [12, 19, 15, 25, 22, 18, 24],
                    backgroundColor: 'rgba(78, 115, 223, 0.1)',
                    borderColor: 'rgba(78, 115, 223, 1)',
                    borderWidth: 2,
                    pointRadius: 3,
                    pointBackgroundColor: 'rgba(78, 115, 223, 1)',
                    pointBorderColor: 'rgba(78, 115, 223, 1)',
                    pointHoverRadius: 5,
                    pointHoverBackgroundColor: 'rgba(78, 115, 223, 1)',
                    pointHoverBorderColor: 'rgba(78, 115, 223, 1)',
                    pointHitRadius: 10,
                    pointBorderWidth: 2,
                    fill: true
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        display: false
                    }
                },
                scales: {
                    x: {
                        grid: {
                            color: 'rgba(0, 0, 0, 0.1)'
                        }
                    },
                    y: {
                        beginAtZero: true,
                        grid: {
                            color: 'rgba(0, 0, 0, 0.1)'
                        }
                    }
                }
            }
        });

        // Gráfico de dona - Distribución por departamento
        const ctx2 = document.getElementById('departamentosChart').getContext('2d');
        const departamentosChart = new Chart(ctx2, {
            type: 'doughnut',
            data: {
                labels: ['Administración', 'Operaciones', 'Ventas', 'IT', 'RRHH'],
                datasets: [{
                    data: [35, 28, 20, 12, 5],
                    backgroundColor: [
                        'rgba(78, 115, 223, 0.8)',
                        'rgba(28, 200, 138, 0.8)',
                        'rgba(54, 185, 204, 0.8)',
                        'rgba(246, 194, 62, 0.8)',
                        'rgba(231, 74, 59, 0.8)'
                    ],
                    borderColor: [
                        'rgba(78, 115, 223, 1)',
                        'rgba(28, 200, 138, 1)',
                        'rgba(54, 185, 204, 1)',
                        'rgba(246, 194, 62, 1)',
                        'rgba(231, 74, 59, 1)'
                    ],
                    borderWidth: 2,
                    hoverBorderWidth: 3
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        display: false
                    }
                },
                cutout: '60%'
            }
        });

        // Gráfico de barras - Rendimiento trimestral
        const ctx3 = document.getElementById('rendimientoChart').getContext('2d');
        const rendimientoChart = new Chart(ctx3, {
            type: 'bar',
            data: {
                labels: ['Q1 2025', 'Q2 2025', 'Q3 2025', 'Q4 2025'],
                datasets: [{
                    label: 'Ingresos (Miles Q)',
                    data: [450, 520, 380, 670],
                    backgroundColor: 'rgba(28, 200, 138, 0.8)',
                    borderColor: 'rgba(28, 200, 138, 1)',
                    borderWidth: 1
                }, {
                    label: 'Gastos (Miles Q)',
                    data: [300, 350, 280, 400],
                    backgroundColor: 'rgba(231, 74, 59, 0.8)',
                    borderColor: 'rgba(231, 74, 59, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        display: true,
                        position: 'top'
                    }
                },
                scales: {
                    x: {
                        grid: {
                            display: false
                        }
                    },
                    y: {
                        beginAtZero: true,
                        grid: {
                            color: 'rgba(0, 0, 0, 0.1)'
                        }
                    }
                }
            }
        });
    });
</script>

<style>
    .border-left-primary {
        border-left: 0.25rem solid #4e73df !important;
    }
    .border-left-success {
        border-left: 0.25rem solid #1cc88a !important;
    }
    .border-left-info {
        border-left: 0.25rem solid #36b9cc !important;
    }
    .border-left-warning {
        border-left: 0.25rem solid #f6c23e !important;
    }
    .text-gray-800 {
        color: #5a5c69 !important;
    }
    .text-gray-300 {
        color: #dddfeb !important;
    }
    .text-xs {
        font-size: 0.7rem;
    }
    .font-weight-bold {
        font-weight: 700 !important;
    }
    .shadow {
        box-shadow: 0 0.15rem 1.75rem 0 rgba(58, 59, 69, 0.15) !important;
    }
    .card-header {
        background-color: #f8f9fc;
        border-bottom: 1px solid #e3e6f0;
    }
</style>

</body>
</html>