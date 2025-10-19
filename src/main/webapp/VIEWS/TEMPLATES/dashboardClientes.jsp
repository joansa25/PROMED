<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Soluciones.com S.A. - Internet de Alta Calidad en Tiquisate</title>
    
    <!-- Bootstrap & Font Awesome -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            overflow-x: hidden;
            background: #0a0e27;
        }

        /* ===== HERO SECTION ===== */
        .hero-section {
            position: relative;
            height: 100vh;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            overflow: hidden;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .hero-background {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            opacity: 0.3;
        }

        .particles {
            position: absolute;
            width: 100%;
            height: 100%;
        }

        .particle {
            position: absolute;
            width: 10px;
            height: 10px;
            background: white;
            border-radius: 50%;
            opacity: 0.5;
            animation: float 15s ease-in-out infinite;
        }

        @keyframes float {
            0%, 100% { transform: translateY(0) translateX(0); }
            25% { transform: translateY(-100px) translateX(50px); }
            50% { transform: translateY(-50px) translateX(-50px); }
            75% { transform: translateY(-150px) translateX(100px); }
        }

        .hero-content {
            position: relative;
            z-index: 2;
            text-align: center;
            color: white;
            padding: 0 20px;
        }

        .hero-logo {
            font-size: 5rem;
            margin-bottom: 1rem;
            animation: pulse 2s ease-in-out infinite;
        }

        @keyframes pulse {
            0%, 100% { transform: scale(1); }
            50% { transform: scale(1.1); }
        }

        .hero-title {
            font-size: 4rem;
            font-weight: 800;
            margin-bottom: 1rem;
            animation: slideInDown 1s ease;
            text-shadow: 2px 2px 10px rgba(0,0,0,0.3);
        }

        .hero-subtitle {
            font-size: 1.5rem;
            margin-bottom: 1rem;
            opacity: 0.9;
            animation: slideInUp 1s ease 0.3s both;
        }

        .hero-location {
            font-size: 1.2rem;
            margin-bottom: 2rem;
            opacity: 0.8;
            animation: slideInUp 1s ease 0.5s both;
        }

        .hero-location i {
            color: #ffd700;
            margin-right: 10px;
        }

        .hero-buttons {
            animation: fadeIn 1s ease 0.8s both;
        }

        .btn-hero {
            padding: 15px 40px;
            font-size: 1.1rem;
            border-radius: 50px;
            margin: 10px;
            transition: all 0.3s ease;
            border: none;
            cursor: pointer;
            display: inline-flex;
            align-items: center;
            gap: 10px;
        }

        .btn-primary-hero {
            background: white;
            color: #667eea;
            font-weight: 600;
        }

        .btn-primary-hero:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 30px rgba(255,255,255,0.3);
        }

        .btn-secondary-hero {
            background: transparent;
            color: white;
            border: 2px solid white;
        }

        .btn-secondary-hero:hover {
            background: white;
            color: #667eea;
            transform: translateY(-5px);
        }

        /* ===== SERVICES SECTION ===== */
        .services-section {
            padding: 100px 0;
            background: linear-gradient(to bottom, #0a0e27, #1a1f3a);
        }

        .section-title {
            text-align: center;
            font-size: 3rem;
            font-weight: 700;
            color: white;
            margin-bottom: 1rem;
        }

        .section-subtitle {
            text-align: center;
            font-size: 1.2rem;
            color: rgba(255,255,255,0.7);
            margin-bottom: 4rem;
        }

        .service-card {
            background: linear-gradient(135deg, #1e2139 0%, #2a2d4a 100%);
            border-radius: 20px;
            padding: 40px;
            margin: 20px 0;
            transition: all 0.4s ease;
            border: 2px solid transparent;
            position: relative;
            overflow: hidden;
            height: 100%;
        }

        .service-card::before {
            content: '';
            position: absolute;
            top: 0;
            left: -100%;
            width: 100%;
            height: 100%;
            background: linear-gradient(90deg, transparent, rgba(102,126,234,0.2), transparent);
            transition: 0.8s;
        }

        .service-card:hover::before {
            left: 100%;
        }

        .service-card:hover {
            transform: translateY(-10px);
            border-color: #667eea;
            box-shadow: 0 20px 40px rgba(102,126,234,0.4);
        }

        .service-icon {
            font-size: 4rem;
            background: linear-gradient(135deg, #667eea, #764ba2);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            margin-bottom: 20px;
        }

        .service-title {
            font-size: 1.8rem;
            font-weight: 700;
            color: white;
            margin-bottom: 15px;
        }

        .service-description {
            color: rgba(255,255,255,0.8);
            line-height: 1.8;
            font-size: 1.05rem;
        }

        /* ===== PLANS SECTION ===== */
        .plans-section {
            padding: 100px 0;
            background: #0a0e27;
        }

        .plan-card {
            background: linear-gradient(135deg, #1e2139 0%, #2a2d4a 100%);
            border-radius: 20px;
            padding: 40px;
            margin: 20px 0;
            transition: all 0.3s ease;
            border: 2px solid transparent;
            position: relative;
            overflow: hidden;
        }

        .plan-card.featured {
            border-color: #ffd700;
            transform: scale(1.05);
        }

        .featured-badge {
            position: absolute;
            top: 20px;
            right: 20px;
            background: #ffd700;
            color: #0a0e27;
            padding: 8px 20px;
            border-radius: 50px;
            font-weight: 700;
            font-size: 0.9rem;
        }

        .plan-card:hover {
            transform: translateY(-10px) scale(1.02);
            border-color: #667eea;
            box-shadow: 0 20px 40px rgba(102,126,234,0.3);
        }

        .plan-icon {
            font-size: 4rem;
            background: linear-gradient(135deg, #667eea, #764ba2);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            margin-bottom: 20px;
        }

        .plan-name {
            font-size: 2rem;
            font-weight: 700;
            color: white;
            margin-bottom: 10px;
        }

        .plan-speed {
            font-size: 3rem;
            font-weight: 800;
            color: #667eea;
            margin-bottom: 10px;
        }

        .plan-price {
            font-size: 2rem;
            color: #ffd700;
            margin-bottom: 20px;
        }

        .plan-features {
            list-style: none;
            padding: 0;
            margin: 30px 0;
        }

        .plan-features li {
            color: rgba(255,255,255,0.8);
            padding: 12px 0;
            border-bottom: 1px solid rgba(255,255,255,0.1);
            display: flex;
            align-items: center;
        }

        .plan-features li i {
            color: #667eea;
            margin-right: 15px;
            font-size: 1.2rem;
        }

        .btn-plan {
            width: 100%;
            padding: 15px;
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: white;
            border: none;
            border-radius: 50px;
            font-size: 1.1rem;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .btn-plan:hover {
            transform: scale(1.05);
            box-shadow: 0 10px 30px rgba(102,126,234,0.5);
        }

        /* ===== MISSION & VISION ===== */
        .mission-vision-section {
            padding: 100px 0;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            position: relative;
            overflow: hidden;
        }

        .mission-vision-section::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 600"><circle cx="300" cy="200" r="150" fill="rgba(255,255,255,0.05)" /><circle cx="900" cy="400" r="200" fill="rgba(255,255,255,0.05)" /></svg>');
            animation: float 30s ease-in-out infinite;
        }

        .mv-card {
            background: rgba(255,255,255,0.15);
            backdrop-filter: blur(15px);
            border-radius: 20px;
            padding: 50px;
            margin: 20px 0;
            border: 2px solid rgba(255,255,255,0.3);
            transition: all 0.4s ease;
            position: relative;
            z-index: 1;
        }

        .mv-card:hover {
            transform: translateY(-10px);
            background: rgba(255,255,255,0.2);
            box-shadow: 0 20px 40px rgba(0,0,0,0.2);
        }

        .mv-icon {
            font-size: 5rem;
            color: white;
            margin-bottom: 25px;
            animation: bounce 2s ease-in-out infinite;
        }

        @keyframes bounce {
            0%, 100% { transform: translateY(0); }
            50% { transform: translateY(-20px); }
        }

        .mv-title {
            font-size: 2.5rem;
            font-weight: 700;
            color: white;
            margin-bottom: 20px;
            text-shadow: 2px 2px 5px rgba(0,0,0,0.2);
        }

        .mv-text {
            font-size: 1.2rem;
            color: rgba(255,255,255,0.95);
            line-height: 1.9;
            text-align: justify;
        }

        /* ===== COVERAGE SECTION ===== */
        .coverage-section {
            padding: 100px 0;
            background: #1a1f3a;
        }

        .coverage-card {
            background: linear-gradient(135deg, #667eea, #764ba2);
            border-radius: 20px;
            padding: 60px;
            text-align: center;
            color: white;
            position: relative;
            overflow: hidden;
        }

        .coverage-card::before {
            content: '';
            position: absolute;
            width: 300px;
            height: 300px;
            background: rgba(255,255,255,0.1);
            border-radius: 50%;
            top: -150px;
            right: -150px;
        }

        .coverage-icon {
            font-size: 5rem;
            margin-bottom: 20px;
        }

        .coverage-title {
            font-size: 2.5rem;
            font-weight: 700;
            margin-bottom: 20px;
        }

        .coverage-text {
            font-size: 1.3rem;
            line-height: 1.8;
        }

        /* ===== ANIMATIONS ===== */
        @keyframes slideInDown {
            from {
                opacity: 0;
                transform: translateY(-50px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes slideInUp {
            from {
                opacity: 0;
                transform: translateY(50px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }

        /* ===== SCROLL REVEAL ===== */
        .reveal {
            opacity: 0;
            transform: translateY(50px);
            transition: all 1s ease;
        }

        .reveal.active {
            opacity: 1;
            transform: translateY(0);
        }

        /* ===== RESPONSIVE ===== */
        @media (max-width: 768px) {
            .hero-title { font-size: 2.5rem; }
            .hero-subtitle { font-size: 1.2rem; }
            .section-title { font-size: 2rem; }
            .plan-card { margin: 10px 0; }
            .mv-card { padding: 30px; }
            .btn-hero { width: 100%; margin: 5px 0; }
        }
    </style>
</head>
<body>

    <!-- ===== HERO SECTION ===== -->
    <section class="hero-section">
        <div class="hero-background">
            <div class="particles">
                <div class="particle" style="top: 10%; left: 20%; animation-delay: 0s;"></div>
                <div class="particle" style="top: 30%; left: 70%; animation-delay: 2s;"></div>
                <div class="particle" style="top: 60%; left: 40%; animation-delay: 4s;"></div>
                <div class="particle" style="top: 80%; left: 80%; animation-delay: 6s;"></div>
            </div>
        </div>

        <div class="hero-content">
            <div class="hero-logo">
                <i class="fas fa-globe"></i>
            </div>
            <h1>BIENVENIDO</h1>
            <h1 class="hero-title">Soluciones.com S.A.</h1>
            <p class="hero-subtitle">Internet de Alta Calidad para tu Hogar y Negocio</p>
            <p class="hero-location">
                <i class="fas fa-map-marker-alt"></i>
                Tiquisate y zonas cercanas - Guatemala
            </p>
            <div class="hero-buttons">
                <button class="btn-hero btn-primary-hero" onclick="scrollToPlans()">
                    <i class="fas fa-wifi"></i> Ver Planes
                </button>
                <button class="btn-hero btn-secondary-hero" onclick="scrollToServices()">
                    <i class="fas fa-cogs"></i> Nuestros Servicios
                </button>
            </div>
        </div>
    </section>

    <!-- ===== SERVICES SECTION ===== -->
    <section class="services-section" id="services">
        <div class="container">
            <h2 class="section-title reveal">Nuestros Servicios</h2>
            <p class="section-subtitle reveal">Soluciones completas de conectividad</p>

            <div class="row">
                <div class="col-md-4">
                    <div class="service-card reveal">
                        <div class="service-icon">
                            <i class="fas fa-satellite-dish"></i>
                        </div>
                        <h3 class="service-title">Instalación Completa</h3>
                        <p class="service-description">
                            Evaluación de cobertura, instalación y activación del enlace, 
                            configuración de router y Wi-Fi (nombres de red, canales y seguridad), 
                            y optimización de señal dentro del inmueble.
                        </p>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="service-card reveal" style="animation-delay: 0.2s;">
                        <div class="service-icon">
                            <i class="fas fa-network-wired"></i>
                        </div>
                        <h3 class="service-title">Planes de Internet</h3>
                        <p class="service-description">
                            Ofrecemos planes de servicio con velocidades acordes a navegación, 
                            videollamadas y streaming, diseñados para satisfacer las necesidades 
                            de hogares y negocios.
                        </p>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="service-card reveal" style="animation-delay: 0.4s;">
                        <div class="service-icon">
                            <i class="fas fa-headset"></i>
                        </div>
                        <h3 class="service-title">Soporte Técnico</h3>
                        <p class="service-description">
                            Diagnóstico remoto y visita en sitio cuando aplica. Mantenemos 
                            monitoreo constante de incidencias para garantizar la continuidad 
                            del servicio.
                        </p>
                    </div>
                </div>
            </div>

            <div class="row mt-4">
                <div class="col-md-6">
                    <div class="service-card reveal" style="animation-delay: 0.6s;">
                        <div class="service-icon">
                            <i class="fas fa-tv"></i>
                        </div>
                        <h3 class="service-title">Plan IPTV</h3>
                        <p class="service-description">
                            Como valor añadido, disponemos de un plan de IPTV para quienes 
                            desean integrar TV por Internet sobre la misma conexión, 
                            ofreciendo entretenimiento de calidad.
                        </p>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="service-card reveal" style="animation-delay: 0.8s;">
                        <div class="service-icon">
                            <i class="fas fa-chart-line"></i>
                        </div>
                        <h3 class="service-title">Monitoreo Continuo</h3>
                        <p class="service-description">
                            Sistema de monitoreo de incidencias 24/7 para garantizar la 
                            continuidad del servicio y respuesta rápida ante cualquier 
                            eventualidad en tu conexión.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- ===== PLANS SECTION ===== -->
    <section class="plans-section" id="plans">
        <div class="container">
            <h2 class="section-title reveal">Planes de Internet</h2>
            <p class="section-subtitle reveal">Velocidad y conectividad para cada necesidad</p>

            <div class="row">
                <!-- Plan 1 -->
                <div class="col-md-4">
                    <div class="plan-card reveal">
                        <div class="plan-icon">
                            <i class="fas fa-home"></i>
                        </div>
                        <h3 class="plan-name">Plan Hogar</h3>
                        <div class="plan-speed">15 Mbps</div>
                        <div class="plan-price">Q150/mes</div>
                        <ul class="plan-features">
                            <li><i class="fas fa-check-circle"></i> Navegación fluida</li>
                            <li><i class="fas fa-check-circle"></i> Videollamadas HD</li>
                            <li><i class="fas fa-check-circle"></i> Streaming básico</li>
                            <li><i class="fas fa-check-circle"></i> WiFi incluido</li>
                            <li><i class="fas fa-check-circle"></i> Instalación gratis</li>
                            <li><i class="fas fa-check-circle"></i> Soporte técnico</li>
                        </ul>
                        <button class="btn-plan" onclick="contactar('Plan Hogar 15 Mbps')">
                            <i class="fas fa-shopping-cart"></i> Contratar Ahora
                        </button>
                    </div>
                </div>

                <!-- Plan 2 - DESTACADO -->
                <div class="col-md-4">
                    <div class="plan-card featured reveal" style="animation-delay: 0.2s;">
                        <span class="featured-badge">⭐ MÁS POPULAR</span>
                        <div class="plan-icon">
                            <i class="fas fa-users"></i>
                        </div>
                        <h3 class="plan-name">Plan Familia</h3>
                        <div class="plan-speed">20 Mbps</div>
                        <div class="plan-price">Q200/mes</div>
                        <ul class="plan-features">
                            <li><i class="fas fa-check-circle"></i> Navegación rápida</li>
                            <li><i class="fas fa-check-circle"></i> Videollamadas Full HD</li>
                            <li><i class="fas fa-check-circle"></i> Streaming HD múltiple</li>
                            <li><i class="fas fa-check-circle"></i> WiFi potente</li>
                            <li><i class="fas fa-check-circle"></i> Router premium</li>
                            <li><i class="fas fa-check-circle"></i> Soporte prioritario</li>
                        </ul>
                        <button class="btn-plan" onclick="contactar('Plan Familia 20 Mbps')">
                            <i class="fas fa-shopping-cart"></i> Contratar Ahora
                        </button>
                    </div>
                </div>

                <!-- Plan 3 -->
                <div class="col-md-4">
                    <div class="plan-card reveal" style="animation-delay: 0.4s;">
                        <div class="plan-icon">
                            <i class="fas fa-rocket"></i>
                        </div>
                        <h3 class="plan-name">Plan Premium</h3>
                        <div class="plan-speed">25 Mbps</div>
                        <div class="plan-price">Q250/mes</div>
                        <ul class="plan-features">
                            <li><i class="fas fa-check-circle"></i> Velocidad máxima</li>
                            <li><i class="fas fa-check-circle"></i> Videollamadas 4K</li>
                            <li><i class="fas fa-check-circle"></i> Streaming 4K</li>
                            <li><i class="fas fa-check-circle"></i> WiFi de alta potencia</li>
                            <li><i class="fas fa-check-circle"></i> Router última generación</li>
                            <li><i class="fas fa-check-circle"></i> Soporte VIP 24/7</li>
                        </ul>
                        <button class="btn-plan" onclick="contactar('Plan Premium 25 Mbps')">
                            <i class="fas fa-shopping-cart"></i> Contratar Ahora
                        </button>
                    </div>
                </div>
            </div>

            <!-- Plan IPTV -->
            <div class="row mt-4">
                <div class="col-md-12">
                    <div class="plan-card reveal" style="animation-delay: 0.6s; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);">
                        <div class="row align-items-center">
                            <div class="col-md-2 text-center">
                                <div class="plan-icon" style="-webkit-text-fill-color: white;">
                                    <i class="fas fa-tv"></i>
                                </div>
                            </div>
                            <div class="col-md-7">
                                <h3 class="plan-name">Plan IPTV</h3>
                                <p class="service-description" style="color: white; margin: 0;">
                                    TV por Internet de alta calidad sobre tu conexión existente. 
                                    Disfruta de tus canales favoritos con tecnología de streaming.
                                </p>
                            </div>
                            <div class="col-md-3 text-center">
                                <div class="plan-price" style="color: white;">Q50/mes</div>
                                <button class="btn-plan" onclick="contactar('Plan IPTV')" style="background: white; color: #667eea;">
                                    <i class="fas fa-play-circle"></i> Agregar IPTV
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- ===== MISSION & VISION ===== -->
    <section class="mission-vision-section">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="mv-card reveal">
                        <div class="mv-icon">
                            <i class="fas fa-bullseye"></i>
                        </div>
                        <h3 class="mv-title">Nuestra Misión</h3>
                        <p class="mv-text">
                            Brindar servicios de conectividad confiable y accesible que impulsen 
                            la comunicación, el entretenimiento y la productividad de hogares y 
                            empresas en Tiquisate y sus alrededores. Soluciones.com S.A. se 
                            compromete a ofrecer Internet de alta calidad, soporte técnico oportuno 
                            y atención personalizada, contribuyendo al desarrollo tecnológico de 
                            la comunidad.
                        </p>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="mv-card reveal" style="animation-delay: 0.3s;">
                        <div class="mv-icon">
                            <i class="fas fa-eye"></i>
                        </div>
                        <h3 class="mv-title">Nuestra Visión</h3>
                        <p class="mv-text">
                            Consolidarse como la empresa líder en servicios de Internet en la 
                            región sur de Guatemala, reconocida por su innovación, cobertura y 
                            excelencia en atención al cliente, expandiendo soluciones digitales 
                            que integren conectividad, entretenimiento y eficiencia tecnológica 
                            para mejorar la calidad de vida de sus usuarios.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- ===== COVERAGE SECTION ===== -->
    <section class="coverage-section">
        <div class="container">
            <div class="coverage-card reveal">
                <div class="coverage-icon">
                    <i class="fas fa-map-marked-alt"></i>
                </div>
                <h3 class="coverage-title">Cobertura en Tiquisate</h3>
                <p class="coverage-text">
                    Servicio de Internet para hogares y negocios en <strong>Tiquisate y zonas cercanas</strong>.<br>
                    ¿Quieres saber si tenemos cobertura en tu área? ¡Contáctanos ahora!
                </p>
               <button class="btn-hero btn-primary-hero mt-4" onclick="verificarCobertura()">
  <i class="fas fa-search-location"></i> Verificar Cobertura
</button>

            </div>
        </div>
    </section>

    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    
    <script>
        // Scroll suave a secciones
        function scrollToPlans() {
            document.getElementById('plans').scrollIntoView({ behavior: 'smooth' });
        }

        function scrollToServices() {
            document.getElementById('services').scrollIntoView({ behavior: 'smooth' });
        }

        // Función para contactar
        function contactar(plan) {
            Swal.fire({
                title: '¡Excelente elección!',
                html: `
                    <p>Has seleccionado: <strong>${plan}</strong></p>
                    <p>Para contratar este plan, contáctanos:</p>
                    <div style="margin: 20px 0;">
                        <i class="fas fa-phone" style="color: #667eea;"></i> 
                        <strong>Teléfono:</strong> +502 5312 8423
                    </div>
                    <div style="margin: 20px 0;">
                        <i class="fas fa-envelope" style="color: #667eea;"></i> 
                        <strong>Email:</strong> info@soluciones.com
                    </div>
                    <div style="margin: 20px 0;">
                        <i class="fab fa-whatsapp" style="color: #25D366;"></i> 
                        <strong>WhatsApp:</strong> +502 5312 8423
                    </div>
                `,
                icon: 'success',
                confirmButtonText: 'Entendido',
                confirmButtonColor: '#667eea'
            });
        }

        // Función para verificar cobertura
       

function verificarCobertura() {
  const bannerCobertura = "📍 Actualmente brindamos servicio en <b>Tiquisate</b>, <b>Escuintla</b> y <b>sectores rurales cercanos</b>.";

  const ubicaciones = [
    "Aldea Almolonga","Aldea Caspirol","Aldea Champas de Pinula",
    "Aldea El Arisco (solo el sector 1 y 2)","Aldea El Semillero","Aldea Huitzitzil",
    "Aldea Las Trozas","Aldea Los Barriles (solo barriles 1)","Aldea Pinula",
    "Aldea Playa el Semillero","Aldea San Juan la Noria","Aldea Ticanlu",
    "Caserio Canoas","Caserio Ceiba Hueca","Caserio El Astillero","Caserio El Jordan",
    "Caserio Floridalma","Caserio Justo Molina","Caserio La Ponderosa","Caserio Moyuta el Campesino",
    "Caserio Rinconcito","Caserio Rocarena","Caserio Villa Lopez","Caserio Villa Ofelia",
    "Colonia 17 de Enero","Colonia Bordas Barriles","Colonia Colonia 15 de Septiembre",
    "Colonia Colonia 1O. de Mayo","Colonia Colonia Bartolome de las Casas","Colonia Colonia el Buen Pastor",
    "Colonia Colonia la Fortaleza","Colonia El Prado","Colonia Jardines de Fatima","Colonia La Bendicion",
    "Colonia Las Yardas","Colonia San Juan Jose Castillo","Colonia Sanchez","Colonia Shalom",
    "Condominio San Carlos","Finca Santa Rosita","15 de Junio","La Pradera","Ponderosa",
    "Poblacion Dispersa","Villa Tiquisate"
  ];

  const OPCION_NO_APARECE = "__no_aparece__";

  const listItems = ubicaciones.map(function(u){
    return '' +
      '<label class="ubicacion-item">' +
        '<input type="radio" name="ubicacion" value="' + u + '">' +
        '<i class="fas fa-map-marker-alt"></i>' +
        '<span>' + u + '</span>' +
      '</label>';
  }).join('') + 
  '<label class="ubicacion-item ubicacion-no-aparece">' +
    '<input type="radio" name="ubicacion" value="' + OPCION_NO_APARECE + '">' +
    '<i class="fas fa-question-circle"></i>' +
    '<span><strong>Mi ubicación no aparece</strong></span>' +
  '</label>';

  Swal.fire({
    title: 'Verificar Cobertura',
    html:
      '<style>' +
        '.banner-cobertura{background:#f5f7ff;border:1px solid #e2e6ff;color:#334;padding:10px 12px;border-radius:10px;margin-bottom:10px;text-align:left}' +
        '.ubicacion-item{display:flex;align-items:center;gap:8px;padding:10px 12px;margin-bottom:8px;border:1px solid #eee;border-radius:10px;cursor:pointer;user-select:none}' +
        '.ubicacion-item:hover{border-color:#667eea;background:#f8f9ff}' +
        '.ubicacion-item i{color:#667eea}' +
        '.ubicacion-item input{accent-color:#667eea}' +
        '.ubicaciones{max-height:280px;overflow:auto;text-align:left}' +
        '.swal2-input{margin:0 0 10px 0}' +
        '.ubicacion-no-aparece{border-style:dashed;background:#fffdf7}' +
        '.ubicacion-no-aparece i{color:#ff9800}' +
      '</style>' +
      '<div class="banner-cobertura">' + bannerCobertura + '</div>' +
      '<input type="text" id="buscador" class="swal2-input" placeholder="Busca tu aldea, caserío o colonia..."/>' +
      '<div class="ubicaciones" id="listaUbicaciones">' + listItems + '</div>',
    focusConfirm: false,
    showCancelButton: true,
    confirmButtonText: 'Seleccionar',
    cancelButtonText: 'Cancelar',
    confirmButtonColor: '#667eea',

    didOpen: function() {
      var buscador = document.getElementById('buscador');
      var lista = document.getElementById('listaUbicaciones');

      buscador.addEventListener('input', function(e) {
        var q = e.target.value.toLowerCase();
        Array.prototype.forEach.call(lista.querySelectorAll('.ubicacion-item'), function(li) {
          var txt = li.querySelector('span').textContent.toLowerCase();
          li.style.display = txt.indexOf(q) !== -1 ? 'flex' : 'none';
        });
      });

      lista.addEventListener('click', function(e) {
        var label = e.target.closest('.ubicacion-item');
        if (label) label.querySelector('input').checked = true;
      });
    },

    preConfirm: function() {
      var seleccionado = document.querySelector('input[name="ubicacion"]:checked');
      if (!seleccionado) {
        Swal.showValidationMessage('Por favor selecciona una ubicación');
        return false;
      }
      return seleccionado.value;
    }
  }).then(function(result){
    if (!result.isConfirmed) return;

    var value = result.value;

    if (value === OPCION_NO_APARECE) {
      Swal.fire({
        icon: 'info',
        title: 'Lo sentimos',
        html:
          '<p>No encontramos tu ubicación en la lista.</p>' +
          '<p>Puedes comunicarte con nosotros por llamada o WhatsApp al:</p>' +
          '<p style="font-size:1.1em;margin:14px 0;">' +
            '<a href="tel:+50253128423" style="text-decoration:none;">' +
              '<i class="fas fa-phone" style="color:#667eea;"></i> +502 5312 8423' +
            '</a>' +
            '&nbsp;|&nbsp;' +
            '<a href="https://wa.me/50253128423" target="_blank" style="text-decoration:none;">' +
              '<i class="fab fa-whatsapp"></i> WhatsApp' +
            '</a>' +
          '</p>',
        confirmButtonText: 'Entendido',
        confirmButtonColor: '#667eea'
      });
      return;
    }

    Swal.fire({
      title: '¡Buenas noticias!',
      html:
        '<p>Tenemos cobertura en tu zona: <strong>' + value + '</strong></p>' +
        '<p>Contáctanos para agendar tu instalación:</p>' +
        '<div style="margin: 20px 0;">' +
          '<i class="fas fa-phone" style="color:#667eea;"></i> +502 5312 8423' +
        '</div>',
      icon: 'success',
      confirmButtonColor: '#667eea'
    });
  });
}


        // Animación de scroll reveal
        function reveal() {
            const reveals = document.querySelectorAll('.reveal');
            
            reveals.forEach(element => {
                const windowHeight = window.innerHeight;
                const elementTop = element.getBoundingClientRect().top;
                const elementVisible = 150;
                
                if (elementTop < windowHeight - elementVisible) {
                    element.classList.add('active');
                }
            });
        }

        window.addEventListener('scroll', reveal);
        reveal(); // Ejecutar al cargar la página

        // Crear partículas dinámicas
        function createParticles() {
            const particles = document.querySelector('.particles');
            for (let i = 0; i < 20; i++) {
                const particle = document.createElement('div');
                particle.className = 'particle';
                particle.style.top = Math.random() * 100 + '%';
                particle.style.left = Math.random() * 100 + '%';
                particle.style.animationDelay = Math.random() * 10 + 's';
                particles.appendChild(particle);
            }
        }

        createParticles();
    </script>
</body>
</html>