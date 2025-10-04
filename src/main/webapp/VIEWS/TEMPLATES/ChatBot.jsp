<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Asistente Virtual</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #e5ddd5;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 0;
            margin: 0;
        }

        .chat-container {
            width: 100%;
            max-width: 600px;
            height: 90vh;
            min-height: 500px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.2);
            display: flex;
            flex-direction: column;
            overflow: hidden;
        }

        .chat-header {
            background: #075e54;
            color: white;
            padding: 15px 20px;
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .chat-header img {
            width: 45px;
            height: 45px;
            border-radius: 50%;
            background: white;
            padding: 5px;
        }

        .header-info h3 {
            font-size: 16px;
            font-weight: 500;
        }

        .header-info p {
            font-size: 13px;
            opacity: 0.8;
        }

        .chat-messages {
            flex: 1;
            overflow-y: auto;
            padding: 20px;
            background: #e5ddd5;
            background-image: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100"><rect fill="%23e5ddd5" width="100" height="100"/><circle fill="%23d9d9d9" fill-opacity="0.1" cx="50" cy="50" r="40"/></svg>');
        }

        .message {
            margin-bottom: 15px;
            display: flex;
            animation: slideIn 0.3s ease-out;
        }

        @keyframes slideIn {
            from {
                opacity: 0;
                transform: translateY(10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .message.bot {
            justify-content: flex-start;
        }

        .message.user {
            justify-content: flex-end;
        }

        .message-bubble {
            max-width: 75%;
            padding: 10px 15px;
            border-radius: 8px;
            position: relative;
            word-wrap: break-word;
        }

        .message.bot .message-bubble {
            background: white;
            box-shadow: 0 1px 2px rgba(0,0,0,0.1);
        }

        .message.user .message-bubble {
            background: #dcf8c6;
            box-shadow: 0 1px 2px rgba(0,0,0,0.1);
        }

        .message-time {
            font-size: 11px;
            color: #667781;
            margin-top: 5px;
            text-align: right;
        }

        .options-container {
            display: flex;
            flex-direction: column;
            gap: 10px;
            margin-top: 10px;
        }

        .option-button {
            background: white;
            border: 2px solid #075e54;
            color: #075e54;
            padding: 12px 20px;
            border-radius: 25px;
            cursor: pointer;
            transition: all 0.3s;
            font-size: 14px;
            text-align: left;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .option-button:hover {
            background: #075e54;
            color: white;
            transform: translateX(5px);
        }

        .option-button i {
            font-size: 16px;
        }

        .typing-indicator {
            display: none;
            padding: 15px;
            background: white;
            border-radius: 8px;
            width: fit-content;
            box-shadow: 0 1px 2px rgba(0,0,0,0.1);
        }

        .typing-indicator.active {
            display: block;
        }

        .typing-dot {
            display: inline-block;
            width: 8px;
            height: 8px;
            border-radius: 50%;
            background: #90949c;
            margin: 0 2px;
            animation: typing 1.4s infinite;
        }

        .typing-dot:nth-child(2) {
            animation-delay: 0.2s;
        }

        .typing-dot:nth-child(3) {
            animation-delay: 0.4s;
        }

        @keyframes typing {
            0%, 60%, 100% {
                transform: translateY(0);
            }
            30% {
                transform: translateY(-10px);
            }
        }

        .whatsapp-button {
            background: #25d366;
            color: white;
            padding: 12px 20px;
            border-radius: 25px;
            border: none;
            cursor: pointer;
            font-size: 14px;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 10px;
            transition: all 0.3s;
        }

        .whatsapp-button:hover {
            background: #128c7e;
            transform: scale(1.05);
        }

        .success-button {
            background: #34b7f1;
            color: white;
            border: none;
        }

        .success-button:hover {
            background: #2a96c7;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            body {
                padding: 0;
            }

            .chat-container {
                max-width: 100%;
                height: 100vh;
                border-radius: 0;
            }

            .chat-header {
                padding: 12px 15px;
            }

            .chat-header i {
                font-size: 35px !important;
            }

            .header-info h3 {
                font-size: 14px;
            }

            .header-info p {
                font-size: 12px;
            }

            .chat-messages {
                padding: 15px 10px;
            }

            .message-bubble {
                max-width: 85%;
                padding: 8px 12px;
                font-size: 14px;
            }

            .message-time {
                font-size: 10px;
            }

            .option-button {
                padding: 10px 15px;
                font-size: 13px;
            }

            .option-button i {
                font-size: 14px;
            }

            .whatsapp-button {
                padding: 10px 15px;
                font-size: 13px;
            }

            .typing-indicator {
                padding: 12px;
            }

            /* Ajustes adicionales para botones */
            .options-container {
                gap: 8px;
            }
        }

        @media (max-width: 480px) {
            .chat-header {
                padding: 10px 12px;
            }

            .chat-header i {
                font-size: 30px !important;
            }

            .header-info h3 {
                font-size: 13px;
            }

            .header-info p {
                font-size: 11px;
            }

            .chat-messages {
                padding: 10px 8px;
            }

            .message-bubble {
                max-width: 90%;
                padding: 8px 10px;
                font-size: 13px;
            }

            .option-button {
                padding: 8px 12px;
                font-size: 12px;
                gap: 8px;
            }

            .option-button i {
                font-size: 13px;
            }

            .whatsapp-button {
                padding: 8px 12px;
                font-size: 12px;
            }

            .success-button {
                font-size: 12px;
            }

            /* Reducir márgenes en móviles pequeños */
            .message {
                margin-bottom: 12px;
            }

            .options-container {
                gap: 8px;
                margin-top: 8px;
            }
        }

        @media (max-width: 360px) {
            .chat-header {
                padding: 8px 10px;
            }

            .chat-header i {
                font-size: 28px !important;
            }

            .header-info h3 {
                font-size: 12px;
            }

            .header-info p {
                font-size: 10px;
            }

            .chat-messages {
                padding: 8px 6px;
            }

            .message-bubble {
                padding: 7px 9px;
                font-size: 12px;
            }

            .option-button {
                padding: 7px 10px;
                font-size: 11px;
                gap: 6px;
            }

            .option-button i {
                font-size: 12px;
            }

            .whatsapp-button {
                padding: 7px 10px;
                font-size: 11px;
            }

            .message-time {
                font-size: 9px;
            }
        }

        /* Landscape móvil */
        @media (max-height: 500px) and (orientation: landscape) {
            .chat-container {
                height: 100vh;
            }

            .chat-header {
                padding: 8px 12px;
            }

            .chat-header i {
                font-size: 28px !important;
            }

            .header-info h3 {
                font-size: 13px;
            }

            .header-info p {
                font-size: 11px;
            }

            .chat-messages {
                padding: 10px;
            }

            .message-bubble {
                padding: 6px 10px;
            }

            .option-button {
                padding: 6px 12px;
                font-size: 12px;
            }

            .whatsapp-button {
                padding: 6px 12px;
                font-size: 12px;
            }
        }

        /* Tablets */
        @media (min-width: 769px) and (max-width: 1024px) {
            body {
                padding: 15px;
            }

            .chat-container {
                max-width: 700px;
                height: 85vh;
            }
        }

        /* Desktop grande */
        @media (min-width: 1025px) {
            body {
                padding: 20px;
            }

            .chat-container {
                max-width: 650px;
            }
        }
    </style>
</head>
<body>
    <div class="chat-container">
        <div class="chat-header">
            <i class="fas fa-robot" style="font-size: 45px;"></i>
            <div class="header-info">
                <h3>Asistente Virtual</h3>
                <p>En línea</p>
            </div>
        </div>

        <div class="chat-messages" id="chatMessages">
            <div class="typing-indicator" id="typingIndicator">
                <span class="typing-dot"></span>
                <span class="typing-dot"></span>
                <span class="typing-dot"></span>
            </div>
        </div>
    </div>

    <script>
        const chatMessages = document.getElementById('chatMessages');
        const typingIndicator = document.getElementById('typingIndicator');
        let conversationState = 'initial';

        // Función para obtener la hora actual
        function getCurrentTime() {
            const now = new Date();
            return now.getHours().toString().padStart(2, '0') + ':' + 
                   now.getMinutes().toString().padStart(2, '0');
        }

        // Función para agregar mensaje del bot
        function addBotMessage(text, delay = 1000) {
            return new Promise(resolve => {
                typingIndicator.classList.add('active');
                
                setTimeout(() => {
                    typingIndicator.classList.remove('active');
                    
                    const messageDiv = document.createElement('div');
                    messageDiv.className = 'message bot';
                    messageDiv.innerHTML = `
                        <div class="message-bubble">
                            \${text}
                            <div class="message-time">\${getCurrentTime()}</div>
                        </div>
                    `;
                    
                    chatMessages.appendChild(messageDiv);
                    chatMessages.scrollTop = chatMessages.scrollHeight;
                    resolve();
                }, delay);
            });
        }

        // Función para agregar mensaje del usuario
        function addUserMessage(text) {
            const messageDiv = document.createElement('div');
            messageDiv.className = 'message user';
            messageDiv.innerHTML = `
                <div class="message-bubble">
                    \${text}
                    <div class="message-time">\${getCurrentTime()}</div>
                </div>
            `;
            
            chatMessages.appendChild(messageDiv);
            chatMessages.scrollTop = chatMessages.scrollHeight;
        }

        // Función para mostrar opciones de preguntas frecuentes
        async function showFAQOptions() {
            await addBotMessage('Por favor, selecciona una de las siguientes opciones:', 800);
            
            const optionsDiv = document.createElement('div');
            optionsDiv.className = 'message bot';
            optionsDiv.innerHTML = `
                <div class="message-bubble" style="max-width: 90%;">
                    <div class="options-container">
                        <button class="option-button" onclick="handleFAQ(1)">
                            <i class="fas fa-wifi"></i> ¿Por qué mi internet está lento?
                        </button>
                        <button class="option-button" onclick="handleFAQ(2)">
                            <i class="fas fa-file-invoice-dollar"></i> ¿Cómo verificar si mi factura está pagada?
                        </button>
                        <button class="option-button" onclick="handleFAQ(3)">
                            <i class="fas fa-exclamation-triangle"></i> Mi servicio fue suspendido
                        </button>
                        <button class="option-button" onclick="handleFAQ(4)">
                            <i class="fas fa-map-marker-alt"></i> ¿En qué áreas tienen cobertura?
                        </button>
                        <button class="option-button" onclick="handleFAQ(5)">
                            <i class="fas fa-exchange-alt"></i> ¿Puedo cambiar de plan?
                        </button>
                        <button class="option-button" onclick="handleFAQ(6)">
                            <i class="fas fa-tags"></i> ¿Tienen promociones o descuentos?
                        </button>
                    </div>
                </div>
            `;
            
            chatMessages.appendChild(optionsDiv);
            chatMessages.scrollTop = chatMessages.scrollHeight;
        }

        // Respuestas a preguntas frecuentes
        const faqAnswers = {
            1: {
                question: '¿Por qué mi internet está lento?',
                answer: 'La velocidad de tu conexión puede verse afectada por varios factores: cantidad de dispositivos conectados, interferencias de señal, o saturación temporal en la red.'
            },
            2: {
                question: '¿Cómo puedo verificar si mi factura está pagada y mi servicio activo?',
                answer: 'Puedes revisar el estado de tu factura desde el menú principal de la aplicación web.'
            },
            3: {
                question: 'Mi servicio fue suspendido, ¿cómo puedo reactivarlo rápidamente?',
                answer: 'Si tu servicio fue suspendido por falta de pago, puedes reactivarlo de inmediato realizando el pago en línea desde tu cuenta.'
            },
            4: {
                question: '¿En qué áreas geográficas está disponible el servicio?',
                answer: 'Actualmente prestamos servicio en Tiquisate, Escuintla y en zonas aledañas del Municipio.'
            },
            5: {
                question: '¿Puedo cambiar de plan una vez contratado el servicio?',
                answer: '¡Claro que sí! Puedes cambiar tu plan en cualquier momento desde la aplicación web o contactando a un asesor.'
            },
            6: {
                question: '¿Tienen promociones o descuentos especiales?',
                answer: 'Sí, contamos con promociones temporales y descuentos especiales por contratación en línea, referencias o paquetes combinados de servicios.'
            }
        };

        // Manejar selección de FAQ
        async function handleFAQ(optionNumber) {
            const faq = faqAnswers[optionNumber];
            addUserMessage(faq.question);
            
            await addBotMessage(faq.answer, 1500);
            
            await showSatisfactionOptions();
        }

        // Mostrar opciones de satisfacción
        async function showSatisfactionOptions() {
            await addBotMessage('¿Te he podido ayudar con tu consulta? ?', 1000);
            
            const optionsDiv = document.createElement('div');
            optionsDiv.className = 'message bot';
            optionsDiv.innerHTML = `
                <div class="message-bubble" style="max-width: 90%;">
                    <div class="options-container">
                        <button class="option-button success-button" onclick="handleSatisfied()">
                            <i class="fas fa-check-circle"></i> Sí, fuiste de mucha ayuda
                        </button>
                        <button class="whatsapp-button" onclick="contactWhatsApp()">
                            <i class="fab fa-whatsapp"></i> Deseo contactarme con una persona
                        </button>
                    </div>
                </div>
            `;
            
            chatMessages.appendChild(optionsDiv);
            chatMessages.scrollTop = chatMessages.scrollHeight;
        }

        // Usuario satisfecho
        async function handleSatisfied() {
            addUserMessage('Sí, fuiste de mucha ayuda');
            await addBotMessage('¡Excelente! Me alegra mucho haber podido ayudarte. ?', 800);
            await addBotMessage('Si necesitas algo más en el futuro, no dudes en contactarme. ¡Que tengas un excelente día! ?', 1000);
            
            setTimeout(() => {
                const restartDiv = document.createElement('div');
                restartDiv.className = 'message bot';
                restartDiv.innerHTML = `
                    <div class="message-bubble">
                        <button class="option-button" onclick="location.reload()">
                            <i class="fas fa-redo"></i> Iniciar nueva consulta
                        </button>
                    </div>
                `;
                chatMessages.appendChild(restartDiv);
                chatMessages.scrollTop = chatMessages.scrollHeight;
            }, 2000);
        }

        // Contactar por WhatsApp
        function contactWhatsApp() {
            addUserMessage('Deseo contactarme con una persona');
            
            const message = 'Hola, necesito ayuda con una consulta';
            
            addBotMessage('Perfecto! Te voy a conectar con uno de nuestros agentes. ?', 800)
                .then(() => {
                    setTimeout(() => {
                        const copyDiv = document.createElement('div');
                        copyDiv.className = 'message bot';
                        copyDiv.innerHTML = `
                            <div class="message-bubble" style="max-width: 90%;">
                                <p style="margin-bottom: 10px;">? <strong>Copia este mensaje:</strong></p>
                                <div style="background: #f0f0f0; padding: 10px; border-radius: 5px; margin-bottom: 15px; font-style: italic; color: #333;">
                                    "${message}"
                                </div>
                                <button class="option-button" onclick="copyMessageAndOpenWhatsApp()" style="background: #25d366; color: white; border: none; justify-content: center;">
                                    <i class="fas fa-copy"></i> Copiar mensaje y abrir WhatsApp
                                </button>
                            </div>
                        `;
                        chatMessages.appendChild(copyDiv);
                        chatMessages.scrollTop = chatMessages.scrollHeight;
                    }, 1000);
                });
        }

        // Copiar mensaje y abrir WhatsApp
        function copyMessageAndOpenWhatsApp() {
            const message = 'Hola, necesito ayuda con una consulta';
            const phoneNumber = '50253128423';
            
            // Copiar al portapapeles
            navigator.clipboard.writeText(message).then(() => {
                // Mostrar confirmación
                addBotMessage('? ¡Mensaje copiado! Ahora pégalo en WhatsApp cuando se abra.', 500)
                    .then(() => {
                        setTimeout(() => {
                            // Abrir WhatsApp sin mensaje pre-escrito
                            const whatsappURL = 'https://api.whatsapp.com/send?phone=' + phoneNumber;
                            window.open(whatsappURL, '_blank');
                        }, 1000);
                    });
            }).catch(() => {
                addBotMessage('?? No se pudo copiar automáticamente. Por favor, copia manualmente el mensaje de arriba.', 500);
            });
        }

        // Mostrar opciones iniciales
        async function showInitialOptions() {
            await addBotMessage('¿Cómo puedo ayudarte hoy? ?', 800);
            
            const optionsDiv = document.createElement('div');
            optionsDiv.className = 'message bot';
            optionsDiv.innerHTML = `
                <div class="message-bubble" style="max-width: 90%;">
                    <div class="options-container">
                        <button class="whatsapp-button" onclick="contactWhatsApp()">
                            <i class="fab fa-whatsapp"></i> Contactar con una persona
                        </button>
                        <button class="option-button" onclick="showFAQMenu()">
                            <i class="fas fa-question-circle"></i> Ver preguntas frecuentes
                        </button>
                    </div>
                </div>
            `;
            
            chatMessages.appendChild(optionsDiv);
            chatMessages.scrollTop = chatMessages.scrollHeight;
        }

        // Mostrar menú de preguntas frecuentes
        async function showFAQMenu() {
            addUserMessage('Ver preguntas frecuentes');
            await showFAQOptions();
        }

        // Iniciar conversación
        async function startConversation() {
            await addBotMessage('¡Hola! ? Bienvenido a nuestro asistente virtual.', 1000);
            await addBotMessage('Estoy aquí para ayudarte. ?', 1200);
            await showInitialOptions();
        }

        // Hacer las funciones globales
        window.handleFAQ = handleFAQ;
        window.handleSatisfied = handleSatisfied;
        window.contactWhatsApp = contactWhatsApp;
        window.copyMessageAndOpenWhatsApp = copyMessageAndOpenWhatsApp;
        window.showFAQMenu = showFAQMenu;

        // Iniciar cuando cargue la página
        window.onload = () => {
            setTimeout(startConversation, 500);
        };
    </script>
</body>
</html>