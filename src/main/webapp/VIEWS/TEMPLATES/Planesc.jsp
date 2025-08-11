<%-- 
    Document   : Planesc
    Created on : 10/08/2025, 21:14:44
    Author     : joans
--%>



<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Planes</title>
        <!-- Agregar estilos de Bootstrap, Font Awesome y SweetAlert2 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style>
        /* Estilos usando tu paleta de colores existente */
        
        /* Título de sección - usando tu estilo */
        .section-title {
            text-align: center;
            font-size: 2.5em;
            color: #184d8c;
            margin-bottom: 30px;
            font-weight: 600;
        }

        /* Estilos para el contenedor principal - usando tu CSS base */
        .main-content {
            padding: 20px;
            max-width: 1200px;
            margin: 0 auto;
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        /* Sección de planes - usando tu CSS existente */
        .planes {
            display: flex;
            gap: 20px;
            justify-content: center;
            flex-wrap: wrap;
            margin: 20px 0;
        }

        /* Tarjetas de plan - adaptando tu estilo existente */
        .plan {
            background: #fff;
            border-radius: 20px;
            box-shadow: 0 6px 18px #2a478c19;
            padding: 20px 25px;
            min-width: 200px;
            max-width: 250px;
            text-align: center;
            transition: transform 0.18s, box-shadow 0.2s;
            cursor: pointer;
            border: 2.5px solid #184d8c12;
            position: relative;
            overflow: hidden;
        }

        .plan:hover {
            transform: translateY(-8px) scale(1.05);
            box-shadow: 0 12px 36px #29eaaa40;
            border: 2.5px solid #29eaaa;
        }

        .plan h3 {
            font-size: 1.3rem;
            color: #184d8c;
            margin-bottom: 8px;
            letter-spacing: 1px;
            font-weight: bold;
        }

        .plan p {
            font-size: 1rem;
            color: #555;
        }

        /* Responsividad - adaptado a tu CSS existente */
        @media (max-width: 900px) {
            .planes {
                gap: 15px;
            }
            .plan {
                min-width: 180px;
                padding: 18px 20px;
            }
        }

        @media (max-width: 700px) {
            .planes {
                flex-direction: column;
                align-items: center;
                gap: 15px;
                margin: 15px 0;
            }
        }

        /* Título de sección - usando tu estilo */
        .section-title {
            text-align: center;
            font-size: 2.5em;
            color: #184d8c;
            margin-bottom: 30px;
            font-weight: 600;
        }
        /* Agregar estos estilos DENTRO de tu tag <style> existente */

/* Estilo para el plan actual del usuario */
.plan-actual {
    border: 3px solid #28a745 !important;
    background: linear-gradient(135deg, #f8fff9 0%, #e8f5e8 100%);
    position: relative;
    transform: scale(1.05);
    box-shadow: 0 8px 25px rgba(40, 167, 69, 0.3) !important;
}

.plan-actual:hover {
    transform: scale(1.08) translateY(-5px);
    box-shadow: 0 12px 35px rgba(40, 167, 69, 0.4) !important;
}

/* Badge del plan actual */
.plan-badge {
    position: absolute;
    top: -10px;
    right: -10px;
    background: linear-gradient(135deg, #ff6b35, #f7931e);
    color: white;
    padding: 5px 12px;
    border-radius: 20px;
    font-size: 0.75rem;
    font-weight: bold;
    box-shadow: 0 2px 8px rgba(255, 107, 53, 0.3);
    z-index: 10;
}

/* Check del plan actual */
.plan-check {
    position: absolute;
    bottom: 15px;
    right: 15px;
    color: #28a745;
    font-size: 1.2rem;
}

/* Animación para el plan actual */
.plan-actual h3 {
    color: #155724 !important;
}

/* Efecto de pulso sutil */
@keyframes pulso-suave {
    0% { box-shadow: 0 8px 25px rgba(40, 167, 69, 0.3); }
    50% { box-shadow: 0 8px 30px rgba(40, 167, 69, 0.4); }
    100% { box-shadow: 0 8px 25px rgba(40, 167, 69, 0.3); }
}

.plan-actual {
    animation: pulso-suave 3s ease-in-out infinite;
}
    </style>
        <link href="VIEWS/STYLES/EstilosModulos.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5 content-container">
            <!-- Botón de registrar asistencia alineado a la izquierda -->


           




        </div>



        <!-- Título estilizado -->
        <h1 class="text-center mb-4" style="font-weight: bold; color: #1c3d5a;">Planes Disponibles</h1>
<!-- Botón que abre el modal -->


    
       




<!-- Sección del plan del usuario -->
<c:if test="${tienePlan}">
    <div class="container mb-4">
        <div class="alert alert-success border-0 shadow-sm" style="background: linear-gradient(135deg, #28a745 0%, #20c997 100%); border-radius: 15px;">
            <div class="row align-items-center">
                <div class="col-md-8">
                    <h4 class="alert-heading text-white mb-2">
                        <i class="fas fa-star me-2"></i>Tu Plan Actual
                    </h4>
                    <p class="text-white mb-1">
                        <strong>Plan:</strong> <span id="tuPlanNombre">${planUsuario.COD_NOMB}</span>
                    </p>
                    <p class="text-white mb-0">
                        <strong>Descripción:</strong> ${planUsuario.COD_DESC}
                    </p>
                </div>
                <div class="col-md-4 text-end">
                    <span class="badge bg-light text-success fs-6 px-3 py-2">
                        <i class="fas fa-check-circle me-1"></i>Activo
                    </span>
                </div>
            </div>
        </div>
    </div>
</c:if>

<c:if test="${!tienePlan}">
    <div class="container mb-4">
        <div class="alert alert-warning border-0 shadow-sm" style="border-radius: 15px;">
            <h5 class="alert-heading">
                <i class="fas fa-exclamation-triangle me-2"></i>Sin Plan Asignado
            </h5>
            <p class="mb-0">${mensajeAdvertencia}</p>
        </div>
    </div>
</c:if>

<!-- Contenido principal -->
<div class="main-content">
    
    <!-- Sección de planes MODIFICADA -->
    <section class="planes">
        <c:forEach var="plan" items="${planes}">
            <div class="plan ${tienePlan && plan.COD_NOMB == planUsuario.COD_NOMB ? 'plan-actual' : ''}" 
                 onclick="mostrarMenu(this, '${plan.getCOD_NOMB()}', '${tienePlan && plan.COD_NOMB == planUsuario.COD_NOMB}')">
                
                <!-- Indicador de plan actual -->
                <c:if test="${tienePlan && plan.COD_NOMB == planUsuario.COD_NOMB}">
                    <div class="plan-badge">
                        <i class="fas fa-crown"></i> Tu Plan
                    </div>
                </c:if>
                
                <h3>${plan.getCOD_NOMB()}</h3>
                <p>${plan.getCOD_DESC()}</p>
                
                <!-- Indicador visual adicional -->
                <c:if test="${tienePlan && plan.COD_NOMB == planUsuario.COD_NOMB}">
                    <div class="plan-check">
                        <i class="fas fa-check-circle"></i>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </section>
</div>

   <script>
    // Reemplazar tu función mostrarMenu existente con esta:
    function mostrarMenu(elemento, nombrePlan, esPlanActual) {
        console.log('Plan seleccionado:', nombrePlan);
        console.log('¿Es tu plan actual?:', esPlanActual);
        
        // Si es el plan actual del usuario
        if (esPlanActual === 'true') {
            Swal.fire({
                icon: 'success',
                title: '¡Este es tu plan actual!',
                html: `
                    <div style="text-align: center;">
                        <i class="fas fa-crown" style="color: #ffd700; font-size: 2rem; margin-bottom: 10px;"></i>
                        <h4 style="color: #28a745; margin-bottom: 15px;">Plan: ${nombrePlan}</h4>
                        <p style="color: #6c757d;">Este es el plan que tienes asignado actualmente.</p>
                    </div>
                `,
                confirmButtonText: 'Entendido',
                confirmButtonColor: '#28a745',
                backdrop: true,
                allowOutsideClick: true
            });
        } else {
            // Si no es su plan actual
            Swal.fire({
                icon: 'info',
                title: 'Plan: ' + nombrePlan,
                text: 'Este no es tu plan actual. ¿Te interesa conocer más detalles?',
                showCancelButton: true,
                confirmButtonText: 'Ver detalles',
                cancelButtonText: 'Cerrar',
                confirmButtonColor: '#007bff'
            }).then((result) => {
                if (result.isConfirmed) {
                    // Aquí puedes agregar lógica para mostrar más detalles del plan
                    console.log('Mostrar detalles del plan:', nombrePlan);
                }
            });
        }
        
        // Efecto visual al hacer clic
        elemento.style.transform = 'scale(0.95)';
        setTimeout(() => {
            if (esPlanActual === 'true') {
                elemento.style.transform = 'scale(1.08) translateY(-5px)';
            } else {
                elemento.style.transform = 'translateY(-8px)';
            }
        }, 150);
    }

    // Función que se ejecuta al cargar la página
    document.addEventListener('DOMContentLoaded', function() {
        // Verificar si hay plan del usuario y mostrar mensaje de bienvenida
        <c:if test="${tienePlan}">
            const tuPlan = "${planUsuario.COD_NOMB}";
            
            // Mostrar mensaje de bienvenida con el plan del usuario
            setTimeout(() => {
                Swal.fire({
                    icon: 'info',
                    title: '¡Bienvenido!',
                    html: `
                        <div style="text-align: center;">
                            <i class="fas fa-user-check" style="color: #007bff; font-size: 2rem; margin-bottom: 10px;"></i>
                            <h4 style="color: #184d8c; margin-bottom: 15px;">Tu plan actual es:</h4>
                            <div style="background: linear-gradient(135deg, #28a745, #20c997); color: white; padding: 15px; border-radius: 10px; margin: 10px 0;">
                                <h3 style="margin: 0; color: white;"><i class="fas fa-star"></i> ${tuPlan}</h3>
                            </div>
                            <p style="color: #6c757d; margin-top: 15px;">Está destacado en verde más abajo 👇</p>
                        </div>
                    `,
                    confirmButtonText: 'Ver planes',
                    confirmButtonColor: '#28a745',
                    timer: 5000,
                    timerProgressBar: true
                });
            }, 1000);
        </c:if>
        
        <c:if test="${!tienePlan}">
            setTimeout(() => {
                Swal.fire({
                    icon: 'warning',
                    title: 'Sin plan asignado',
                    text: 'No tienes un plan asignado. Contacta al administrador para obtener acceso.',
                    confirmButtonText: 'Entendido',
                    confirmButtonColor: '#ffc107'
                });
            }, 1000);
        </c:if>
    });
</script>

    <!-- Sección para mostrar menús dinámicamente -->
    <div id="opciones-container"></div>
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

