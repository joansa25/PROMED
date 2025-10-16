/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDao;
import Modelo.Cotizacion;
import Modelo.CotizacionDao;

import Modelo.DashboardData;
import Modelo.Empleado;
import Modelo.EmpleadoDao;
import Modelo.Empleo;
import Modelo.EmpleoDao;
import Modelo.Empresa;
import Modelo.EmpresaDao;
import Modelo.IncidenciaDao;
import Modelo.Plan;
import Modelo.PlanDao;
import Modelo.User;
import Modelo.UserDao;
import Modelo.Zona;
import Modelo.ZonaDao;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Map;

/**
 *
 * @author joans
 */
public class Controlador extends HttpServlet {

    User us = new User();
    UserDao usdao = new UserDao();
    Empleo em = new Empleo();
    EmpleoDao emdao = new EmpleoDao();
    Empleado emp = new Empleado();
    EmpleadoDao empdao = new EmpleadoDao();
    Empresa empsa = new Empresa();
    EmpresaDao empsadao = new EmpresaDao();
    ClienteDao clidao = new ClienteDao();
    PlanDao plandao = new PlanDao();
    Plan plan = new Plan();
    Cotizacion cot = new Cotizacion();
    CotizacionDao cotdao = new CotizacionDao();

    ZonaDao zonadao = new ZonaDao();
    Zona zona = new Zona();

    Cliente cli = new Cliente();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        System.out.println("Valor de 'menu': " + menu);
        System.out.println("Valor de 'accion': " + accion);

        // Verificar que menu no sea null
        if (menu != null) {
            switch (menu) {
                case "Principal":
                    System.out.println("dentro de Principal");

                    try {
                        // Crear instancias de los DAOs
                        EmpleadoDao empleadoDao = new EmpleadoDao();
                        ClienteDao clienteDao = new ClienteDao();
                        PlanDao planDao = new PlanDao();

                        // Obtener los datos reales de la base de datos
                        int empleadosActivos = empleadoDao.contarEmpleadosActivos();
                        int clientesActivos = clienteDao.contarClientesActivos();
                        int planesActivos = planDao.contarPlanesActivos();

                        // Crear el objeto DashboardData con los datos obtenidos
                        DashboardData dashboardData = new DashboardData(
                                empleadosActivos,
                                clientesActivos,
                                planesActivos
                        );

                        // Guardar en sesión para que el iframe pueda acceder
                        HttpSession session = request.getSession();
                        session.setAttribute("dashboardData", dashboardData);

                        // También puedes guardar datos individuales por si los necesitas separados
                        session.setAttribute("empleadosActivos", empleadosActivos);
                        session.setAttribute("clientesActivos", clientesActivos);
                        session.setAttribute("planesActivos", planesActivos);

                        System.out.println("Datos del dashboard cargados: "
                                + "Empleados=" + empleadosActivos
                                + ", Clientes=" + clientesActivos
                                + ", Planes=" + planesActivos
                        );

                    } catch (Exception e) {
                        System.err.println("Error al cargar datos del dashboard: " + e.getMessage());
                        e.printStackTrace();

                        // En caso de error, usar datos por defecto o mostrar mensaje
                        DashboardData defaultData = new DashboardData(0, 0, 0, 0);
                        request.getSession().setAttribute("dashboardData", defaultData);
                    }

                    // Redirigir al menú principal
                    request.getRequestDispatcher("VIEWS/TEMPLATES/menuPrincipal.jsp").forward(request, response);
                    return;

                case "exis":
                    // Cerrar sesión - invalidar la sesión
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        session.invalidate(); // Elimina toda la información de la sesión
                    }

                    // Limpiar cookies si las usas
                    Cookie[] cookies = request.getCookies();
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            cookie.setValue("");
                            cookie.setPath("/");
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }
                    }

                    // Redirigir al login o página de inicio
                    response.sendRedirect("index.html"); // Cambia por tu página de login
                    return; // Importante: terminar la ejecución aquí

                default:
                    // Si el menú no coincide con ninguno conocido
                    System.out.println("Menú no reconocido: " + menu);
                    break;

            }

        }

        /* Empresa */
        if (menu.equals("Empresa")) {
            System.out.println("Dentro de Empresa");

            switch (accion) {
                case "Listar":
                    System.out.println("Dentro de Empresa LISTAR");

                    // Obtener la lista de empresas
                    List<Empresa> listaEmpresas = empsadao.listar();

                    if (listaEmpresas == null || listaEmpresas.isEmpty()) {
                        System.out.println("La lista de empresas está vacía");
                    } else {
                        for (Empresa empresa : listaEmpresas) {
                            System.out.println(empresa.getCOD_EMPSA());
                        }
                    }

                    // Pasar la lista a la vista
                    request.setAttribute("empresa", listaEmpresas);
                    request.getRequestDispatcher("VIEWS/TEMPLATES/Empresas.jsp").forward(request, response);

                    break;
                case "ListarRE":
                    System.out.println("Dentro de Empresa LISTAR");

                    // Obtener la lista de empresas
                    listaEmpresas = empsadao.listar();

                    if (listaEmpresas == null || listaEmpresas.isEmpty()) {
                        System.out.println("La lista de empresas está vacía");
                    } else {
                        for (Empresa empresa : listaEmpresas) {
                            System.out.println(empresa.getCOD_EMPSA());
                        }
                    }

                    // Pasar la lista a la vista
                    request.setAttribute("empresa", listaEmpresas);
                    request.getRequestDispatcher("VIEWS/TEMPLATES/REPORTES/Empresas.jsp").forward(request, response);

                    return;

                case "agregar":
                    String codEmpsa = request.getParameter("txtCodEmpsa");
                    String nombreEmpsa = request.getParameter("txtNombreEmpsa");
                    String direccionEmpsa = request.getParameter("txtDireccionEmpsa");
                    int nitEmpsa = Integer.parseInt(request.getParameter("txtNitEmpsa"));
                    String estadoEmpsa = request.getParameter("txtEstado");

                    Empresa nuevaEmpresa = new Empresa();
                    nuevaEmpresa.setCOD_EMPSA(codEmpsa);
                    nuevaEmpresa.setNOMB_EMPSA(nombreEmpsa);
                    nuevaEmpresa.setDIRE_EMPSA(direccionEmpsa);
                    nuevaEmpresa.setNIT(nitEmpsa);
                    nuevaEmpresa.setEstado(estadoEmpsa);

                    int resultadoAgregar = empsadao.agregar(nuevaEmpresa);

                    // Agregar atributo según si fue éxito o error
                    if (resultadoAgregar > 0) {
                        request.setAttribute("resultado", 1);
                    } else {
                        request.setAttribute("resultado", 0);
                    }

                    request.getRequestDispatcher("Controlador?menu=Empresa&accion=Listar").forward(request, response);
                    return;

                case "Editar":
                    String codEmpresaEditar = request.getParameter("empCod");
                    Empresa empresaEditar = empsadao.ListarId(codEmpresaEditar);

                    request.setAttribute("empresaEditar", empresaEditar);
                    request.getRequestDispatcher("Controlador?menu=Empresa&accion=Listar").forward(request, response);
                    return;

                case "Actualizar":
                    String codEmpsaActualizar = request.getParameter("txtCodEmpsa");
                    String nombreEmpsaActualizar = request.getParameter("txtNombreEmpsa");
                    String direccionEmpsaActualizar = request.getParameter("txtDireccionEmpsa");
                    int nitEmpsaActualizar = Integer.parseInt(request.getParameter("txtNitEmpsa"));
                    String estadoEmpsaActualizar = request.getParameter("txtEstado");

                    Empresa empresaActualizada = new Empresa();
                    empresaActualizada.setCOD_EMPSA(codEmpsaActualizar);
                    empresaActualizada.setNOMB_EMPSA(nombreEmpsaActualizar);
                    empresaActualizada.setDIRE_EMPSA(direccionEmpsaActualizar);
                    empresaActualizada.setNIT(nitEmpsaActualizar);
                    empresaActualizada.setEstado(estadoEmpsaActualizar);

                    int resultadoActualizar = empsadao.actualizar(empresaActualizada);

                    if (resultadoActualizar > 0) {
                        request.setAttribute("resultadoUpdate", 1);
                    } else {
                        request.setAttribute("resultadoUpdate", 0);
                    }

                    request.getRequestDispatcher("Controlador?menu=Empresa&accion=Listar").forward(request, response);
                    return;

                case "BuscarPorCodigo":
                    String codigoEmpresa = request.getParameter("codigo");
                    List<Empresa> empresasPorCodigo = empsadao.buscarPorCodigo(codigoEmpresa);

                    StringBuilder htmlResponseCodigo = new StringBuilder();
                    for (Empresa empsa : empresasPorCodigo) {
                        htmlResponseCodigo.append("<tr>")
                                .append("<td>").append(empsa.getID()).append("</td>")
                                .append("<td>").append(empsa.getCOD_EMPSA()).append("</td>")
                                .append("<td>").append(empsa.getNOMB_EMPSA()).append("</td>")
                                .append("<td>").append(empsa.getDIRE_EMPSA()).append("</td>")
                                .append("<td>").append(empsa.getNIT()).append("</td>")
                                .append("<td>").append(empsa.getEstado()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' href='Controlador?menu=Empresa&accion=Editar&empCod=")
                                .append(empsa.getCOD_EMPSA()).append("' title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponseCodigo.toString());
                    return;

                case "BuscarPorNombre":
                    String nombreEmpresa = request.getParameter("nombre");
                    List<Empresa> empresasPorNombre = empsadao.buscarPorNombre(nombreEmpresa);

                    StringBuilder htmlResponseNombre = new StringBuilder();
                    for (Empresa empsa : empresasPorNombre) {
                        htmlResponseNombre.append("<tr>")
                                .append("<td>").append(empsa.getID()).append("</td>")
                                .append("<td>").append(empsa.getCOD_EMPSA()).append("</td>")
                                .append("<td>").append(empsa.getNOMB_EMPSA()).append("</td>")
                                .append("<td>").append(empsa.getDIRE_EMPSA()).append("</td>")
                                .append("<td>").append(empsa.getNIT()).append("</td>")
                                .append("<td>").append(empsa.getEstado()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' href='Controlador?menu=Empresa&accion=Editar&empCod=")
                                .append(empsa.getCOD_EMPSA()).append("' title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponseNombre.toString());
                    return;

                case "FiltrarPorEstado":
                    String estadoFiltro = request.getParameter("estado");
                    List<Empresa> empresasPorEstado = empsadao.filtrarPorEstado(estadoFiltro);
                    StringBuilder htmlResponseEstado = new StringBuilder();
                    for (Empresa empsa : empresasPorEstado) {
                        htmlResponseEstado.append("<tr>")
                                .append("<td>").append(empsa.getID()).append("</td>")
                                .append("<td>").append(empsa.getCOD_EMPSA()).append("</td>")
                                .append("<td>").append(empsa.getNOMB_EMPSA()).append("</td>")
                                .append("<td>").append(empsa.getDIRE_EMPSA()).append("</td>")
                                .append("<td>").append(empsa.getNIT()).append("</td>")
                                .append("<td>").append(empsa.getEstado()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' href='Controlador?menu=Empresa&accion=Editar&empCod=")
                                .append(empsa.getCOD_EMPSA()).append("' title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }
                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponseEstado.toString());

                    return;

                // PARA REPORTERIA
                case "BuscarPorCodigoRE":
                    codigoEmpresa = request.getParameter("codigo");
                    empresasPorCodigo = empsadao.buscarPorCodigo(codigoEmpresa);

                    htmlResponseCodigo = new StringBuilder();
                    for (Empresa empsa : empresasPorCodigo) {
                        htmlResponseCodigo.append("<tr>")
                                .append("<td>").append(empsa.getID()).append("</td>")
                                .append("<td>").append(empsa.getCOD_EMPSA()).append("</td>")
                                .append("<td>").append(empsa.getNOMB_EMPSA()).append("</td>")
                                .append("<td>").append(empsa.getDIRE_EMPSA()).append("</td>")
                                .append("<td>").append(empsa.getNIT()).append("</td>")
                                .append("<td>").append(empsa.getEstado()).append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponseCodigo.toString());
                    return;

                case "BuscarPorNombreRE":
                    nombreEmpresa = request.getParameter("nombre");
                    empresasPorNombre = empsadao.buscarPorNombre(nombreEmpresa);

                    htmlResponseNombre = new StringBuilder();
                    for (Empresa empsa : empresasPorNombre) {
                        htmlResponseNombre.append("<tr>")
                                .append("<td>").append(empsa.getID()).append("</td>")
                                .append("<td>").append(empsa.getCOD_EMPSA()).append("</td>")
                                .append("<td>").append(empsa.getNOMB_EMPSA()).append("</td>")
                                .append("<td>").append(empsa.getDIRE_EMPSA()).append("</td>")
                                .append("<td>").append(empsa.getNIT()).append("</td>")
                                .append("<td>").append(empsa.getEstado()).append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponseNombre.toString());
                    return;

                case "FiltrarPorEstadoRE":
                    estadoFiltro = request.getParameter("estado");
                    empresasPorEstado = empsadao.filtrarPorEstado(estadoFiltro);
                    htmlResponseEstado = new StringBuilder();
                    for (Empresa empsa : empresasPorEstado) {
                        htmlResponseEstado.append("<tr>")
                                .append("<td>").append(empsa.getID()).append("</td>")
                                .append("<td>").append(empsa.getCOD_EMPSA()).append("</td>")
                                .append("<td>").append(empsa.getNOMB_EMPSA()).append("</td>")
                                .append("<td>").append(empsa.getDIRE_EMPSA()).append("</td>")
                                .append("<td>").append(empsa.getNIT()).append("</td>")
                                .append("<td>").append(empsa.getEstado()).append("</td>")
                                .append("</tr>");
                    }
                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponseEstado.toString());

                    return;
                default:
                    throw new AssertionError();
            }

            //request.getRequestDispatcher("VIEWS/TEMPLATES/Empresas.jsp").forward(request, response);
        }

        /*EMPLEOS*/
        if (menu.equals("Empleos")) {
            System.out.println("dentro de Empleos");

            switch (accion) {
                case "Listar":
                    System.out.println("dentro de empleo LISTAR");

                    // Obtener la lista de empleados
                    List<Empleo> lista = emdao.listar();

                    if (lista == null || lista.isEmpty()) {
                        System.out.println("La lista de empleados está vacía");
                    } else {
                        for (Empleo empleo : lista) {
                            System.out.println(empleo.getCOD_EMP());
                        }
                    }

                    // Pasar la lista a la vista
                    request.setAttribute("empleos", lista);
                    request.getRequestDispatcher("VIEWS/TEMPLATES/Empleos.jsp").forward(request, response);

                    break;
                case "ListarRE":
                    System.out.println("dentro de empleo LISTAR");

                    // Obtener la lista de empleados
                    lista = emdao.listar();

                    if (lista == null || lista.isEmpty()) {
                        System.out.println("La lista de empleados está vacía");
                    } else {
                        for (Empleo empleo : lista) {
                            System.out.println(empleo.getCOD_EMP());
                        }
                    }

                    // Pasar la lista a la vista
                    request.setAttribute("empleos", lista);

                    request.getRequestDispatcher("VIEWS/TEMPLATES/REPORTES/Empleos.jsp").forward(request, response);

                    return;
                case "agregar":
                    // Obtener los parámetros del formulario
                    String codEmp = request.getParameter("txtCod_emp");
                    String nombreEmp = request.getParameter("txtNom_emp");
                    double salario = Double.parseDouble(request.getParameter("txtSalario"));
                    String estado = request.getParameter("txtEstado");

                    System.out.println("el codigo es :" + codEmp);

                    // Crear objeto empleo y asignar valores
                    Empleo empleo = new Empleo();
                    empleo.setCOD_EMP(codEmp);
                    empleo.setNOMB_EMP(nombreEmp);
                    empleo.setSALARIO(salario);
                    empleo.setESTADO(estado);

                    // Llamar al DAO para agregar el nuevo empleo
                    int resultadoAgregar = emdao.agregar(empleo);
                    if (resultadoAgregar > 0) {
                        request.setAttribute("resultadoa", 1); // Éxito
                    } else {
                        request.setAttribute("resultadoa", 0); // Error
                    }

                    // Redirigir a la lista
                    request.getRequestDispatcher("Controlador?menu=Empleos&accion=Listar").forward(request, response);
                    return;

                case "Editar":
                    // Obtener el código del empleo a editar
                    String empCod = request.getParameter("empCod");
                    System.out.println("El código a editar es: " + empCod);

                    // Obtener el empleo desde el DAO
                    Empleo empEditar = emdao.ListarId(empCod);
                    request.setAttribute("empleo", empEditar);

                    // Redirigir a la lista y abrir la modal de edición
                    request.getRequestDispatcher("Controlador?menu=Empleos&accion=Listar").forward(request, response);
                    return;

                case "Actualizar":
                    // Obtener los datos actualizados del formulario
                    String codEmpAct = request.getParameter("txtCod_emp");
                    String nombreEmpAct = request.getParameter("txtNom_emp");
                    double salarioAct = Double.parseDouble(request.getParameter("txtSalario"));
                    String estadoAct = request.getParameter("txtEstado");

                    // Asignar valores al objeto empleo
                    Empleo empleoAct = new Empleo();
                    empleoAct.setCOD_EMP(codEmpAct);
                    empleoAct.setNOMB_EMP(nombreEmpAct);
                    empleoAct.setSALARIO(salarioAct);
                    empleoAct.setESTADO(estadoAct);

                    // Llamar al DAO para actualizar el empleo
                    int resultadoActualizar = emdao.actualizar(empleoAct);
                    if (resultadoActualizar > 0) {
                        request.setAttribute("resultadoUpdate", 1); // Éxito
                    } else {
                        request.setAttribute("resultadoUpdate", 0); // Error
                    }

                    // Redirigir a la lista de empleos
                    request.getRequestDispatcher("Controlador?menu=Empleos&accion=Listar").forward(request, response);
                    return;

                case "BuscarPorCodigo":
                    // Obtener el código ingresado por el usuario
                    String codigoBuscar = request.getParameter("codigo");
                    List<Empleo> empleosPorCodigo = emdao.buscarPorCodigo(codigoBuscar);

                    // Construir la respuesta HTML
                    StringBuilder htmlResponse = new StringBuilder();
                    for (Empleo em : empleosPorCodigo) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(em.getID()).append("</td>")
                                .append("<td>").append(em.getCOD_EMP()).append("</td>")
                                .append("<td>").append(em.getNOMB_EMP()).append("</td>")
                                .append("<td>Q ").append(em.getSALARIO()).append("</td>")
                                .append("<td>").append(em.getESTADO()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' href='Controlador?menu=Empleos&accion=Editar&empCod=").append(em.getCOD_EMP()).append("' title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    // Enviar la respuesta HTML
                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                case "BuscarPorNombre":
                    // Obtener el nombre ingresado por el usuario
                    String nombreBuscar = request.getParameter("nombre");
                    List<Empleo> empleosPorNombre = emdao.buscarPorNombre(nombreBuscar);

                    // Construir la respuesta HTML
                    htmlResponse = new StringBuilder();
                    for (Empleo em : empleosPorNombre) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(em.getID()).append("</td>")
                                .append("<td>").append(em.getCOD_EMP()).append("</td>")
                                .append("<td>").append(em.getNOMB_EMP()).append("</td>")
                                .append("<td>Q ").append(em.getSALARIO()).append("</td>")
                                .append("<td>").append(em.getESTADO()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' href='Controlador?menu=Empleos&accion=Editar&empCod=").append(em.getCOD_EMP()).append("' title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    // Enviar la respuesta HTML
                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;
                case "FiltrarPorEstado":
                    // Obtener el estado seleccionado por el usuario
                    String estadoSeleccionado = request.getParameter("estado");
                    List<Empleo> empleosPorEstado;

                    // Si el estado está vacío, listamos todos los empleos
                    if (estadoSeleccionado == null || estadoSeleccionado.isEmpty()) {
                        empleosPorEstado = emdao.listar(); // Listar todos los empleos
                    } else {
                        empleosPorEstado = emdao.filtrarPorEstado(estadoSeleccionado); // Filtrar por estado
                    }

                    // Construir la respuesta HTML
                    htmlResponse = new StringBuilder();
                    for (Empleo em : empleosPorEstado) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(em.getID()).append("</td>")
                                .append("<td>").append(em.getCOD_EMP()).append("</td>")
                                .append("<td>").append(em.getNOMB_EMP()).append("</td>")
                                .append("<td>Q ").append(em.getSALARIO()).append("</td>")
                                .append("<td>").append(em.getESTADO()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' href='Controlador?menu=Empleos&accion=Editar&empCod=").append(em.getCOD_EMP()).append("' title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    // Enviar la respuesta HTML al cliente
                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                // REPORTERIA
                case "BuscarPorCodigoRE":
                    // Obtener el código ingresado por el usuario
                    codigoBuscar = request.getParameter("codigo");
                    empleosPorCodigo = emdao.buscarPorCodigo(codigoBuscar);

                    // Construir la respuesta HTML
                    htmlResponse = new StringBuilder();
                    for (Empleo em : empleosPorCodigo) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(em.getID()).append("</td>")
                                .append("<td>").append(em.getCOD_EMP()).append("</td>")
                                .append("<td>").append(em.getNOMB_EMP()).append("</td>")
                                .append("<td>Q ").append(em.getSALARIO()).append("</td>")
                                .append("<td>").append(em.getESTADO()).append("</td>")
                                .append("</tr>");
                    }

                    // Enviar la respuesta HTML
                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                case "BuscarPorNombreRE":
                    // Obtener el nombre ingresado por el usuario
                    nombreBuscar = request.getParameter("nombre");
                    empleosPorNombre = emdao.buscarPorNombre(nombreBuscar);

                    // Construir la respuesta HTML
                    htmlResponse = new StringBuilder();
                    for (Empleo em : empleosPorNombre) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(em.getID()).append("</td>")
                                .append("<td>").append(em.getCOD_EMP()).append("</td>")
                                .append("<td>").append(em.getNOMB_EMP()).append("</td>")
                                .append("<td>Q ").append(em.getSALARIO()).append("</td>")
                                .append("<td>").append(em.getESTADO()).append("</td>")
                                .append("</tr>");
                    }

                    // Enviar la respuesta HTML
                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;
                case "FiltrarPorEstadoRE":
                    // Obtener el estado seleccionado por el usuario
                    estadoSeleccionado = request.getParameter("estado");

                    // Si el estado está vacío, listamos todos los empleos
                    if (estadoSeleccionado == null || estadoSeleccionado.isEmpty()) {
                        empleosPorEstado = emdao.listar(); // Listar todos los empleos
                    } else {
                        empleosPorEstado = emdao.filtrarPorEstado(estadoSeleccionado); // Filtrar por estado
                    }

                    // Construir la respuesta HTML
                    htmlResponse = new StringBuilder();
                    for (Empleo em : empleosPorEstado) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(em.getID()).append("</td>")
                                .append("<td>").append(em.getCOD_EMP()).append("</td>")
                                .append("<td>").append(em.getNOMB_EMP()).append("</td>")
                                .append("<td>Q ").append(em.getSALARIO()).append("</td>")
                                .append("<td>").append(em.getESTADO()).append("</td>")
                                .append("</tr>");
                    }

                    // Enviar la respuesta HTML al cliente
                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                default:
                    throw new AssertionError();
            }

            // request.getRequestDispatcher("VIEWS/TEMPLATES/Empleos.jsp").forward(request, response);
        }

        if (menu.equals("Zonas")) {
            System.out.println("dentro de Zonas");

            switch (accion) {
                case "Listar":
                    System.out.println("dentro de Zonas LISTAR");

                    // Obtener la lista de zonas
                    List<Zona> lista = zonadao.listar();

                    if (lista == null || lista.isEmpty()) {
                        System.out.println("La lista de zonas está vacía");
                    } else {
                        for (Zona zona : lista) {
                            System.out.println(zona.getCOD_ZON());
                        }
                    }

                    // Pasar la lista a la vista
                    request.setAttribute("zonas", lista);

                    request.getRequestDispatcher("VIEWS/TEMPLATES/Zonas.jsp").forward(request, response);
                    break;

                case "ListarRE":
                    System.out.println("dentro de Zonas LISTAR REPORTES");

                    // Obtener la lista de zonas activas
                    lista = zonadao.listarActivos();

                    if (lista == null || lista.isEmpty()) {
                        System.out.println("La lista de zonas está vacía");
                    } else {
                        for (Zona zona : lista) {
                            System.out.println(zona.getCOD_ZON());
                        }
                    }

                    // Pasar la lista a la vista
                    request.setAttribute("zonas", lista);
                    request.getRequestDispatcher("VIEWS/TEMPLATES/REPORTES/Zonas.jsp").forward(request, response);
                    return;

                case "agregar":
                    String cod = request.getParameter("txtCod");
                    String nombre = request.getParameter("txtNombre");
                    String descripcion = request.getParameter("txtDescripcion");

                    zona.setCOD_ZON(cod);
                    zona.setCOD_NOMB(nombre);
                    zona.setCOD_DESC(descripcion);

                    int resultado = zonadao.agregar(zona);

                    // Agregar atributo según si fue éxito o error
                    if (resultado > 0) {
                        request.setAttribute("resultado", 1);
                    } else {
                        request.setAttribute("resultado", 0);
                    }

                    request.getRequestDispatcher("Controlador?menu=Zonas&accion=Listar").forward(request, response);
                    return;

                case "Editar":
                    System.out.println("dentro de editar zona");
                    String zona_cod = request.getParameter("zonaCod"); // Obtener código desde la URL
                    System.out.println("el cod a editar es : " + zona_cod);
                    System.out.println("-------------------------------------");

                    Zona z = zonadao.ListarId(zona_cod); // Llamar al método listarId en el DAO

                    request.setAttribute("zonae", z);

                    // Redirigir al JSP para que se muestre la modal con los datos de la zona
                    request.getRequestDispatcher("Controlador?menu=Zonas&accion=Listar").forward(request, response);
                    return;

                case "Actualizar":
                    // Obtener los datos del formulario
                    String CodActualizar = request.getParameter("txtCod");
                    String nombreActualizar = request.getParameter("txtNombre");
                    String descripcionActualizar = request.getParameter("txtDescripcion");
                    String estadoActualizar = request.getParameter("txtEstado");

                    // Asignar los valores al objeto zona
                    zona.setCOD_ZON(CodActualizar);
                    zona.setCOD_NOMB(nombreActualizar);
                    zona.setCOD_DESC(descripcionActualizar);
                    zona.setESTADO(estadoActualizar);

                    // Llamar al DAO para actualizar la zona en la base de datos
                    int resultadoActualizar = zonadao.actualizar(zona);

                    // Verificar si la actualización fue exitosa
                    if (resultadoActualizar > 0) {
                        request.setAttribute("resultadoUpdate", 1); // Éxito
                    } else {
                        request.setAttribute("resultadoUpdate", 0); // Error
                    }

                    // Redirigir a la lista de zonas
                    request.getRequestDispatcher("Controlador?menu=Zonas&accion=Listar").forward(request, response);
                    return;

                case "BuscarPorCodigo":
                    String codigo = request.getParameter("codigo");
                    ZonaDao zonaDao = new ZonaDao();
                    List<Zona> zonasPorCodigo = zonaDao.buscarPorCodigo(codigo);

                    // Construir la respuesta HTML
                    StringBuilder htmlResponse = new StringBuilder();
                    for (Zona zn : zonasPorCodigo) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(zn.getId()).append("</td>")
                                .append("<td>").append(zn.getCOD_ZON()).append("</td>")
                                .append("<td>").append(zn.getCOD_NOMB()).append("</td>")
                                .append("<td>").append(zn.getCOD_DESC()).append("</td>")
                                .append("<td>").append(zn.getESTADO()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' ")
                                .append("href='Controlador?menu=Zonas&accion=Editar&zonaCod=").append(zn.getCOD_ZON()).append("' ")
                                .append("title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                case "BuscarPorNombre":
                    String nombre_buscar = request.getParameter("nombre");
                    zonaDao = new ZonaDao();
                    List<Zona> zonasPorNombre = zonaDao.buscarPorNombre(nombre_buscar);

                    htmlResponse = new StringBuilder();
                    for (Zona zn : zonasPorNombre) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(zn.getId()).append("</td>")
                                .append("<td>").append(zn.getCOD_ZON()).append("</td>")
                                .append("<td>").append(zn.getCOD_NOMB()).append("</td>")
                                .append("<td>").append(zn.getCOD_DESC()).append("</td>")
                                .append("<td>").append(zn.getESTADO()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' ")
                                .append("href='Controlador?menu=Zonas&accion=Editar&zonaCod=").append(zn.getCOD_ZON()).append("' ")
                                .append("title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                case "FiltrarPorEstado":
                    String estado = request.getParameter("estado");
                    zonaDao = new ZonaDao();
                    List<Zona> zonasPorEstado = zonaDao.filtrarPorEstado(estado);

                    htmlResponse = new StringBuilder();
                    for (Zona zn : zonasPorEstado) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(zn.getId()).append("</td>")
                                .append("<td>").append(zn.getCOD_ZON()).append("</td>")
                                .append("<td>").append(zn.getCOD_NOMB()).append("</td>")
                                .append("<td>").append(zn.getCOD_DESC()).append("</td>")
                                .append("<td>").append(zn.getESTADO()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' ")
                                .append("href='Controlador?menu=Zonas&accion=Editar&zonaCod=").append(zn.getCOD_ZON()).append("' ")
                                .append("title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                // FILTROS DE REPORTERIA
                case "BuscarPorCodigoRE":
                    codigo = request.getParameter("codigo");
                    zonaDao = new ZonaDao();
                    zonasPorCodigo = zonaDao.buscarPorCodigo(codigo);

                    // Construir la respuesta HTML
                    htmlResponse = new StringBuilder();
                    for (Zona zn : zonasPorCodigo) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(zn.getId()).append("</td>")
                                .append("<td>").append(zn.getCOD_ZON()).append("</td>")
                                .append("<td>").append(zn.getCOD_NOMB()).append("</td>")
                                .append("<td>").append(zn.getCOD_DESC()).append("</td>")
                                .append("<td>").append(zn.getESTADO()).append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                case "BuscarPorNombreRE":
                    nombre_buscar = request.getParameter("nombre");
                    zonaDao = new ZonaDao();
                    zonasPorNombre = zonaDao.buscarPorNombre(nombre_buscar);

                    htmlResponse = new StringBuilder();
                    for (Zona zn : zonasPorNombre) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(zn.getId()).append("</td>")
                                .append("<td>").append(zn.getCOD_ZON()).append("</td>")
                                .append("<td>").append(zn.getCOD_NOMB()).append("</td>")
                                .append("<td>").append(zn.getCOD_DESC()).append("</td>")
                                .append("<td>").append(zn.getESTADO()).append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                case "FiltrarPorEstadoRE":
                    estado = request.getParameter("estado");
                    zonaDao = new ZonaDao();
                    zonasPorEstado = zonaDao.filtrarPorEstado(estado);

                    htmlResponse = new StringBuilder();
                    for (Zona zn : zonasPorEstado) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(zn.getId()).append("</td>")
                                .append("<td>").append(zn.getCOD_ZON()).append("</td>")
                                .append("<td>").append(zn.getCOD_NOMB()).append("</td>")
                                .append("<td>").append(zn.getCOD_DESC()).append("</td>")
                                .append("<td>").append(zn.getESTADO()).append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                default:
                    System.out.println("Acción no reconocida en Zonas: " + accion);
                    break;
            }
        }
        /*INCIDENCIAS*/
        if (menu.equals("Incidencias")) {
            System.out.println("Dentro de INCIDENCIAS");

            switch (accion) {
                case "Listar":

                    // Obtener la lista de empresas
                    List<Empresa> listaEmpresas = empsadao.listar();
                    List<User> listaUsuarios = usdao.listar();

                    if (listaUsuarios == null || listaUsuarios.isEmpty()) {
                        System.out.println("La lista de usuarios está vacía");
                    } else {
                        for (User usuario : listaUsuarios) {
                            System.out.println(usuario.getUser_US());
                        }
                    }

                    List<Empresa> listae = empsadao.listar();
                    listae = empsadao.listar();
                    // Pasar la lista a la vista
                    request.setAttribute("empresad", listae);
                    // Pasar la lista a la vista

                    request.setAttribute("usuarios", listaUsuarios);
                    break;

                default:
                    throw new AssertionError();
            }

            request.getRequestDispatcher("VIEWS/TEMPLATES/Incidencias.jsp").forward(request, response);
        }

        if (menu.equals("Planes")) {
            System.out.println("dentro de Planes");

            switch (accion) {
                case "Listar":
                    System.out.println("dentro de Planes LISTAR");

                    // Obtener la lista de planes
                    List<Plan> lista = plandao.listar();

                    if (lista == null || lista.isEmpty()) {
                        System.out.println("La lista de planes está vacía");
                    } else {
                        for (Plan plan : lista) {
                            System.out.println(plan.getCOD_PLAN());
                        }
                    }

                    // Pasar la lista a la vista
                    request.setAttribute("planes", lista);

                    request.getRequestDispatcher("VIEWS/TEMPLATES/Planes.jsp").forward(request, response);
                    break;

                case "Listarc":
                    System.out.println("dentro de Planes LISTAR");

                    // Obtener la lista de planes
                    lista = plandao.listar();

                    if (lista == null || lista.isEmpty()) {
                        System.out.println("La lista de planes está vacía");
                    } else {
                        for (Plan plan : lista) {
                            System.out.println(plan.getCOD_PLAN());
                        }
                    }

                    // Obtener el usuario de la sesión
                    User usuarioLogueado = (User) request.getSession().getAttribute("usuario");

                    if (usuarioLogueado != null) {
                        System.out.println("=== OBTENIENDO PLAN DEL USUARIO ===");
                        System.out.println("Usuario logueado: " + usuarioLogueado.getUser_US());
                        System.out.println("Código de usuario: " + usuarioLogueado.getCod_user());

                        // Obtener el plan del usuario usando la consulta corregida
                        Plan planUsuario = usdao.obtenerPlanUsuario(usuarioLogueado.getCod_user());

                        if (planUsuario != null) {
                            System.out.println("=== PLAN DEL USUARIO ENCONTRADO ===");
                            System.out.println("Plan: " + planUsuario.getCOD_NOMB());
                            System.out.println("Descripción: " + planUsuario.getCOD_DESC());
                            System.out.println("Estado: " + planUsuario.getEstado());

                            // Enviar datos a la vista
                            request.setAttribute("planUsuario", planUsuario);
                            request.setAttribute("tienePlan", true);
                            request.setAttribute("mensajeExito", "Plan cargado correctamente");
                        } else {
                            System.out.println("⚠️ EL USUARIO NO TIENE PLAN ASIGNADO");
                            request.setAttribute("tienePlan", false);
                            request.setAttribute("mensajeAdvertencia", "No tienes un plan asignado. Contacta al administrador.");
                        }

                    } else {
                        System.out.println("❌ NO HAY USUARIO EN SESIÓN");
                        request.setAttribute("error", "Sesión expirada. Por favor inicia sesión nuevamente.");
                    }

                    // Pasar la lista a la vista
                    request.setAttribute("planes", lista);

                    request.getRequestDispatcher("VIEWS/TEMPLATES/Planesc.jsp").forward(request, response);
                    break;
                case "ListarRE":
                    System.out.println("dentro de Planes LISTAR REPORTES");

                    // Obtener la lista de planes activos
                    lista = plandao.listarActivos();

                    if (lista == null || lista.isEmpty()) {
                        System.out.println("La lista de planes está vacía");
                    } else {
                        for (Plan plan : lista) {
                            System.out.println(plan.getCOD_PLAN());
                        }
                    }

                    // Pasar la lista a la vista
                    request.setAttribute("planes", lista);
                    request.getRequestDispatcher("VIEWS/TEMPLATES/REPORTES/Planes.jsp").forward(request, response);
                    return;

                case "agregar":
                    String cod = request.getParameter("txtCod");
                    String nombre = request.getParameter("txtNombre");
                    String descripcion = request.getParameter("txtDescripcion");

                    plan.setCOD_PLAN(cod);
                    plan.setCOD_NOMB(nombre);
                    plan.setCOD_DESC(descripcion);

                    int resultado = plandao.agregar(plan);

                    // Agregar atributo según si fue éxito o error
                    if (resultado > 0) {
                        request.setAttribute("resultado", 1);
                    } else {
                        request.setAttribute("resultado", 0);
                    }

                    request.getRequestDispatcher("Controlador?menu=Planes&accion=Listar").forward(request, response);
                    return;

                case "Editar":
                    System.out.println("dentro de editar plan");
                    String plan_cod = request.getParameter("planCod"); // Obtener código desde la URL
                    System.out.println("el cod a editar es : " + plan_cod);
                    System.out.println("-------------------------------------");

                    Plan p = plandao.ListarId(plan_cod); // Llamar al método listarId en el DAO

                    request.setAttribute("plane", p);

                    // Redirigir al JSP para que se muestre la modal con los datos del plan
                    request.getRequestDispatcher("Controlador?menu=Planes&accion=Listar").forward(request, response);
                    return;

                case "Actualizar":
                    // Obtener los datos del formulario
                    String CodActualizar = request.getParameter("txtCod");
                    String nombreActualizar = request.getParameter("txtNombre");
                    String descripcionActualizar = request.getParameter("txtDescripcion");
                    String estadoActualizar = request.getParameter("txtEstado");

                    // Asignar los valores al objeto plan
                    plan.setCOD_PLAN(CodActualizar);
                    plan.setCOD_NOMB(nombreActualizar);
                    plan.setCOD_DESC(descripcionActualizar);
                    plan.setEstado(estadoActualizar);

                    // Llamar al DAO para actualizar el plan en la base de datos
                    int resultadoActualizar = plandao.actualizar(plan);

                    // Verificar si la actualización fue exitosa
                    if (resultadoActualizar > 0) {
                        request.setAttribute("resultadoUpdate", 1); // Éxito
                    } else {
                        request.setAttribute("resultadoUpdate", 0); // Error
                    }

                    // Redirigir a la lista de planes
                    request.getRequestDispatcher("Controlador?menu=Planes&accion=Listar").forward(request, response);
                    return;

                case "BuscarPorCodigo":
                    String codigo = request.getParameter("codigo");
                    PlanDao planDao = new PlanDao();
                    List<Plan> planesPorCodigo = planDao.buscarPorCodigo(codigo);

                    // Construir la respuesta HTML
                    StringBuilder htmlResponse = new StringBuilder();
                    for (Plan pl : planesPorCodigo) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(pl.getId()).append("</td>")
                                .append("<td>").append(pl.getCOD_PLAN()).append("</td>")
                                .append("<td>").append(pl.getCOD_NOMB()).append("</td>")
                                .append("<td>").append(pl.getCOD_DESC()).append("</td>")
                                .append("<td>").append(pl.getEstado()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' ")
                                .append("href='Controlador?menu=Planes&accion=Editar&planCod=").append(pl.getCOD_PLAN()).append("' ")
                                .append("title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                case "BuscarPorNombre":
                    String nombre_buscar = request.getParameter("nombre");
                    planDao = new PlanDao();
                    List<Plan> planesPorNombre = planDao.buscarPorNombre(nombre_buscar);

                    htmlResponse = new StringBuilder();
                    for (Plan pl : planesPorNombre) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(pl.getId()).append("</td>")
                                .append("<td>").append(pl.getCOD_PLAN()).append("</td>")
                                .append("<td>").append(pl.getCOD_NOMB()).append("</td>")
                                .append("<td>").append(pl.getCOD_DESC()).append("</td>")
                                .append("<td>").append(pl.getEstado()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' ")
                                .append("href='Controlador?menu=Planes&accion=Editar&planCod=").append(pl.getCOD_PLAN()).append("' ")
                                .append("title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                case "FiltrarPorEstado":
                    String estado = request.getParameter("estado");
                    planDao = new PlanDao();
                    List<Plan> planesPorEstado = planDao.filtrarPorEstado(estado);

                    htmlResponse = new StringBuilder();
                    for (Plan pl : planesPorEstado) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(pl.getId()).append("</td>")
                                .append("<td>").append(pl.getCOD_PLAN()).append("</td>")
                                .append("<td>").append(pl.getCOD_NOMB()).append("</td>")
                                .append("<td>").append(pl.getCOD_DESC()).append("</td>")
                                .append("<td>").append(pl.getEstado()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' ")
                                .append("href='Controlador?menu=Planes&accion=Editar&planCod=").append(pl.getCOD_PLAN()).append("' ")
                                .append("title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                // FILTROS DE REPORTERIA
                case "BuscarPorCodigoRE":
                    codigo = request.getParameter("codigo");
                    planDao = new PlanDao();
                    planesPorCodigo = planDao.buscarPorCodigo(codigo);

                    // Construir la respuesta HTML
                    htmlResponse = new StringBuilder();
                    for (Plan pl : planesPorCodigo) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(pl.getId()).append("</td>")
                                .append("<td>").append(pl.getCOD_PLAN()).append("</td>")
                                .append("<td>").append(pl.getCOD_NOMB()).append("</td>")
                                .append("<td>").append(pl.getCOD_DESC()).append("</td>")
                                .append("<td>").append(pl.getEstado()).append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                case "BuscarPorNombreRE":
                    nombre_buscar = request.getParameter("nombre");
                    planDao = new PlanDao();
                    planesPorNombre = planDao.buscarPorNombre(nombre_buscar);

                    htmlResponse = new StringBuilder();
                    for (Plan pl : planesPorNombre) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(pl.getId()).append("</td>")
                                .append("<td>").append(pl.getCOD_PLAN()).append("</td>")
                                .append("<td>").append(pl.getCOD_NOMB()).append("</td>")
                                .append("<td>").append(pl.getCOD_DESC()).append("</td>")
                                .append("<td>").append(pl.getEstado()).append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                case "FiltrarPorEstadoRE":
                    estado = request.getParameter("estado");
                    planDao = new PlanDao();
                    planesPorEstado = planDao.filtrarPorEstado(estado);

                    htmlResponse = new StringBuilder();
                    for (Plan pl : planesPorEstado) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(pl.getId()).append("</td>")
                                .append("<td>").append(pl.getCOD_PLAN()).append("</td>")
                                .append("<td>").append(pl.getCOD_NOMB()).append("</td>")
                                .append("<td>").append(pl.getCOD_DESC()).append("</td>")
                                .append("<td>").append(pl.getEstado()).append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                default:
                    System.out.println("Acción no reconocida en Planes: " + accion);
                    break;
            }
        }

        /*CLIENTES*/
        if (menu.equals("Clientes")) {
            System.out.println("dentro de Clientes");

            switch (accion) {
                case "Listar":
                    System.out.println("dentro de Clientes LISTAR");

                    // Obtener la lista de clientes
                    List<Cliente> lista = clidao.listar();

                    if (lista == null || lista.isEmpty()) {
                        System.out.println("La lista de clientes está vacía");
                    } else {
                        for (Cliente cliente : lista) {
                            System.out.println(cliente.getCOD_CLI());
                        }
                    }

                    // Pasar la lista a la vista
                    request.setAttribute("clientes", lista);

                    // Obtener lista de planes para los dropdowns
                    List<Plan> listaPlanes = plandao.listar();
                    request.setAttribute("planesd", listaPlanes);

                    // Obtener lista de zonas para los dropdowns
                    List<Zona> listaZonas = zonadao.listar();
                    request.setAttribute("zonasd", listaZonas);

                    // Obtener lista de usuarios para los dropdowns
                    List<User> listaUsuarios = usdao.listar();
                    request.setAttribute("usuariosd", listaUsuarios);

                    request.getRequestDispatcher("VIEWS/TEMPLATES/Clientes.jsp").forward(request, response);
                    break;

                case "agregar":
                    String cod = request.getParameter("txtCod");
                    long dpi = Long.parseLong(request.getParameter("txtDpi"));
                    String nom = request.getParameter("txtNombres");
                    String ape = request.getParameter("txtApellidos");
                    int cel = Integer.parseInt(request.getParameter("txtCelular"));
                    String correo = request.getParameter("txtCorreo");
                    String direccion = request.getParameter("txtDireccion");
                    int nit = Integer.parseInt(request.getParameter("txtNit"));
                    String cod_plan = request.getParameter("txtCod_plan");
                    String cod_zona = request.getParameter("txtCod_zona");
                    String cod_user = request.getParameter("txtCod_user");

                    cli.setCOD_CLI(cod);
                    cli.setDPI(dpi);
                    cli.setNOMBRES(nom);
                    cli.setAPELLIDOS(ape);
                    cli.setCELULAR(cel);
                    cli.setCORREO(correo);
                    cli.setDIRECCION(direccion);
                    cli.setNIT(nit);
                    cli.setCOD_PLAN(cod_plan);
                    cli.setCOD_ZONA(cod_zona);
                    cli.setCOD_USER(cod_user);

                    int resultado = clidao.agregar(cli);

                    // Agregar atributo según si fue éxito o error
                    if (resultado > 0) {
                        request.setAttribute("resultado", 1);
                    } else {
                        request.setAttribute("resultado", 0);
                    }

                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    return;

                case "Editar":
                    System.out.println("dentro de editar cliente");
                    String cli_cod = request.getParameter("cliCod"); // Obtener ID desde la URL
                    System.out.println("el cod a editar es : " + cli_cod);
                    System.out.println("-------------------------------------");

                    Cliente c = clidao.ListarId(cli_cod); // Llamar al método listarId en el DAO

                    request.setAttribute("clientee", c);

                    // Redirigir al JSP para que se muestre la modal con los datos del cliente
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    return;

                case "Actualizar":
                    // Obtener los datos del formulario
                    String CodActualizar = request.getParameter("txtCod");
                    long dpiActualizar = Long.parseLong(request.getParameter("txtDpi"));
                    String nombresActualizar = request.getParameter("txtNombres");
                    String apellidosActualizar = request.getParameter("txtApellidos");
                    int celularActualizar = Integer.parseInt(request.getParameter("txtCelular"));
                    String correoActualizar = request.getParameter("txtCorreo");
                    String direccionActualizar = request.getParameter("txtDireccion");
                    int nitActualizar = Integer.parseInt(request.getParameter("txtNit"));
                    String codPlanActualizar = request.getParameter("txtCod_plan");
                    String codZonaActualizar = request.getParameter("txtCod_zona");
                    String codUserActualizar = request.getParameter("txtCod_user");
                    String estadoActualizar = request.getParameter("txtEstado");

                    // Asignar los valores al objeto cliente
                    cli.setCOD_CLI(CodActualizar);
                    cli.setDPI(dpiActualizar);
                    cli.setNOMBRES(nombresActualizar);
                    cli.setAPELLIDOS(apellidosActualizar);
                    cli.setCELULAR(celularActualizar);
                    cli.setCORREO(correoActualizar);
                    cli.setDIRECCION(direccionActualizar);
                    cli.setNIT(nitActualizar);
                    cli.setCOD_PLAN(codPlanActualizar);
                    cli.setCOD_ZONA(codZonaActualizar);
                    cli.setCOD_USER(codUserActualizar);
                    cli.setESTADO(estadoActualizar);

                    // Llamar al DAO para actualizar el cliente en la base de datos
                    int resultadoActualizar = clidao.actualizar(cli);

                    // Verificar si la actualización fue exitosa
                    if (resultadoActualizar > 0) {
                        request.setAttribute("resultadoUpdate", 1); // Éxito
                    } else {
                        request.setAttribute("resultadoUpdate", 0); // Error
                    }

                    // Redirigir a la lista de clientes
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    return;

                case "BuscarPorCodigo":
                    String codigo = request.getParameter("codigo");
                    ClienteDao clienteDao = new ClienteDao();
                    List<Cliente> clientesPorCodigo = clienteDao.buscarPorCodigo(codigo);

                    // Construir la respuesta HTML
                    StringBuilder htmlResponse = new StringBuilder();
                    for (Cliente cl : clientesPorCodigo) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(cl.getId()).append("</td>")
                                .append("<td>").append(cl.getCOD_CLI()).append("</td>")
                                .append("<td>").append(cl.getDPI()).append("</td>")
                                .append("<td>").append(cl.getNOMBRES()).append("</td>")
                                .append("<td>").append(cl.getAPELLIDOS()).append("</td>")
                                .append("<td>").append(cl.getCELULAR()).append("</td>")
                                .append("<td>").append(cl.getCORREO()).append("</td>")
                                .append("<td>").append(cl.getDIRECCION()).append("</td>")
                                .append("<td>").append(cl.getNIT()).append("</td>")
                                .append("<td>").append(cl.getCOD_PLAN()).append("</td>")
                                .append("<td>").append(cl.getCOD_ZONA()).append("</td>")
                                .append("<td>").append(cl.getCOD_USER()).append("</td>")
                                .append("<td>").append(cl.getESTADO()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' ")
                                .append("href='Controlador?menu=Clientes&accion=Editar&cliCod=").append(cl.getCOD_CLI()).append("' ")
                                .append("title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                case "BuscarPorNombre":
                    String nombre = request.getParameter("nombre");
                    clienteDao = new ClienteDao();
                    List<Cliente> clientesPorNombre = clienteDao.buscarPorNombre(nombre);

                    htmlResponse = new StringBuilder();
                    for (Cliente cl : clientesPorNombre) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(cl.getId()).append("</td>")
                                .append("<td>").append(cl.getCOD_CLI()).append("</td>")
                                .append("<td>").append(cl.getDPI()).append("</td>")
                                .append("<td>").append(cl.getNOMBRES()).append("</td>")
                                .append("<td>").append(cl.getAPELLIDOS()).append("</td>")
                                .append("<td>").append(cl.getCELULAR()).append("</td>")
                                .append("<td>").append(cl.getCORREO()).append("</td>")
                                .append("<td>").append(cl.getDIRECCION()).append("</td>")
                                .append("<td>").append(cl.getNIT()).append("</td>")
                                .append("<td>").append(cl.getCOD_PLAN()).append("</td>")
                                .append("<td>").append(cl.getCOD_ZONA()).append("</td>")
                                .append("<td>").append(cl.getCOD_USER()).append("</td>")
                                .append("<td>").append(cl.getESTADO()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' ")
                                .append("href='Controlador?menu=Clientes&accion=Editar&cliCod=").append(cl.getCOD_CLI()).append("' ")
                                .append("title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                case "FiltrarPorEstado":
                    String estado = request.getParameter("estado");
                    clienteDao = new ClienteDao();
                    List<Cliente> clientesPorEstado = clienteDao.filtrarPorEstado(estado);

                    htmlResponse = new StringBuilder();
                    for (Cliente cl : clientesPorEstado) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(cl.getId()).append("</td>")
                                .append("<td>").append(cl.getCOD_CLI()).append("</td>")
                                .append("<td>").append(cl.getDPI()).append("</td>")
                                .append("<td>").append(cl.getNOMBRES()).append("</td>")
                                .append("<td>").append(cl.getAPELLIDOS()).append("</td>")
                                .append("<td>").append(cl.getCELULAR()).append("</td>")
                                .append("<td>").append(cl.getCORREO()).append("</td>")
                                .append("<td>").append(cl.getDIRECCION()).append("</td>")
                                .append("<td>").append(cl.getNIT()).append("</td>")
                                .append("<td>").append(cl.getCOD_PLAN()).append("</td>")
                                .append("<td>").append(cl.getCOD_ZONA()).append("</td>")
                                .append("<td>").append(cl.getCOD_USER()).append("</td>")
                                .append("<td>").append(cl.getESTADO()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' ")
                                .append("href='Controlador?menu=Clientes&accion=Editar&cliCod=").append(cl.getCOD_CLI()).append("' ")
                                .append("title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                default:
                    throw new AssertionError();
            }
        }

        /*CHATBOT*/
 /*ChatBot*/
        if (menu.equals("ChatBot")) {
            System.out.println("dentro de ChatBot");

            switch (accion) {
                case "Listar":
                    System.out.println("dentro de ChatBot LISTAR");

                    request.getRequestDispatcher("VIEWS/TEMPLATES/ChatBot.jsp").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
        }

        /*Seguridad*/
        if (menu.equals("Seguridad")) {
            System.out.println("Dentro de Seguridad");

            switch (accion) {
                case "Listar":

                    // Obtener la lista de empresas
                    List<Empresa> listaEmpresas = empsadao.listar();
                    List<User> listaUsuarios = usdao.listar();

                    if (listaUsuarios == null || listaUsuarios.isEmpty()) {
                        System.out.println("La lista de usuarios está vacía");
                    } else {
                        for (User usuario : listaUsuarios) {
                            System.out.println(usuario.getUser_US());
                        }
                    }

                    List<Empresa> listae = empsadao.listar();
                    listae = empsadao.listar();
                    // Pasar la lista a la vista
                    request.setAttribute("empresad", listae);
                    // Pasar la lista a la vista

                    request.setAttribute("usuarios", listaUsuarios);
                    break;

                case "agregar":
                    String codUser = request.getParameter("txtCodUser");
                    String userUs = request.getParameter("txtUserUs");
                    String rolUs = request.getParameter("txtRolUs");
                    String pswUs = request.getParameter("txtPswUs");
                    String nombUs = request.getParameter("txtNombUs");
                    String apeUs = request.getParameter("txtApeUs");
                    String corrUs = request.getParameter("txtCorrUs");
                    String codEmpsa = request.getParameter("txtCodEmpsa"); // NUEVO

                    User nuevoUsuario = new User();
                    nuevoUsuario.setCod_user(codUser);
                    nuevoUsuario.setUser_US(userUs);
                    nuevoUsuario.setRol_Us(rolUs);
                    nuevoUsuario.setPsw_Us(pswUs);
                    nuevoUsuario.setNomb_Us(nombUs);
                    nuevoUsuario.setApe_Us(apeUs);
                    nuevoUsuario.setCorr_Us(corrUs);
                    nuevoUsuario.setCod_empsa(codEmpsa); // NUEVO

                    int resultadoAgregar = usdao.agregar(nuevoUsuario);
                    request.setAttribute("resultado", resultadoAgregar > 0 ? 1 : 0);
                    request.getRequestDispatcher("Controlador?menu=Seguridad&accion=Listar").forward(request, response);
                    return;

                case "Editar":
                    String codUsuarioEditar = request.getParameter("codUser");
                    User usuarioEditar = usdao.listarPorId(codUsuarioEditar);
                    request.setAttribute("usuarioEditar", usuarioEditar);
                    request.getRequestDispatcher("Controlador?menu=Seguridad&accion=Listar").forward(request, response);
                    return;

                case "Actualizar":
                    String codUserActualizar = request.getParameter("txtCodUser");
                    String userUsActualizar = request.getParameter("txtUserUs");
                    String rolUsActualizar = request.getParameter("txtRolUs");
                    String pswUsActualizar = request.getParameter("txtPswUs");
                    String nombUsActualizar = request.getParameter("txtNombUs");
                    String apeUsActualizar = request.getParameter("txtApeUs");
                    String corrUsActualizar = request.getParameter("txtCorrUs");
                    String estadoActualizar = request.getParameter("txtEstado");
                    String codEmpsaActualizar = request.getParameter("txtCodEmpsa"); // NUEVO

                    User usuarioActualizado = new User();
                    usuarioActualizado.setCod_user(codUserActualizar);
                    usuarioActualizado.setUser_US(userUsActualizar);
                    usuarioActualizado.setRol_Us(rolUsActualizar);
                    usuarioActualizado.setPsw_Us(pswUsActualizar);
                    usuarioActualizado.setNomb_Us(nombUsActualizar);
                    usuarioActualizado.setApe_Us(apeUsActualizar);
                    usuarioActualizado.setCorr_Us(corrUsActualizar);
                    usuarioActualizado.setEsdado(estadoActualizar);
                    usuarioActualizado.setCod_empsa(codEmpsaActualizar); // NUEVO

                    int resultadoActualizar = usdao.actualizar(usuarioActualizado);
                    request.setAttribute("resultadoUpdate", resultadoActualizar > 0 ? 1 : 0);
                    request.getRequestDispatcher("Controlador?menu=Seguridad&accion=Listar").forward(request, response);
                    return;

                case "BuscarPorCodigo":
                    String codigo = request.getParameter("codigo");
                    List<User> usuariosPorCodigo = usdao.buscarPorCodigo(codigo);

                    StringBuilder htmlResponseCodigo = new StringBuilder();
                    for (User user : usuariosPorCodigo) {
                        htmlResponseCodigo.append("<tr>")
                                .append("<td>").append(user.getID()).append("</td>")
                                .append("<td>").append(user.getCod_user()).append("</td>")
                                .append("<td>").append(user.getUser_US()).append("</td>")
                                .append("<td>").append(user.getRol_Us()).append("</td>")
                                .append("<td>").append(user.getEsdado()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' href='Controlador?menu=Seguridad&accion=Editar&codUser=")
                                .append(user.getCod_user()).append("' title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponseCodigo.toString());
                    return;

                case "BuscarPorUsuario":
                    String usuario = request.getParameter("usuario");
                    List<User> usuariosPorUsuario = usdao.buscarPorUsuario(usuario);

                    StringBuilder htmlResponseUsuario = new StringBuilder();
                    for (User user : usuariosPorUsuario) {
                        htmlResponseUsuario.append("<tr>")
                                .append("<td>").append(user.getID()).append("</td>")
                                .append("<td>").append(user.getCod_user()).append("</td>")
                                .append("<td>").append(user.getUser_US()).append("</td>")
                                .append("<td>").append(user.getRol_Us()).append("</td>")
                                .append("<td>").append(user.getEsdado()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' href='Controlador?menu=Seguridad&accion=Editar&codUser=")
                                .append(user.getCod_user()).append("' title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponseUsuario.toString());
                    return;

                case "FiltrarPorEstado":
                    String estadoFiltro = request.getParameter("estado");
                    List<User> usuariosPorEstado = usdao.filtrarPorEstado(estadoFiltro);

                    StringBuilder htmlResponseEstado = new StringBuilder();
                    for (User user : usuariosPorEstado) {
                        htmlResponseEstado.append("<tr>")
                                .append("<td>").append(user.getID()).append("</td>")
                                .append("<td>").append(user.getCod_user()).append("</td>")
                                .append("<td>").append(user.getUser_US()).append("</td>")
                                .append("<td>").append(user.getRol_Us()).append("</td>")
                                .append("<td>").append(user.getEsdado()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' href='Controlador?menu=Seguridad&accion=Editar&codUser=")
                                .append(user.getCod_user()).append("' title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponseEstado.toString());
                    return;

                default:
                    throw new AssertionError();
            }

            request.getRequestDispatcher("VIEWS/TEMPLATES/Seguridad.jsp").forward(request, response);
        }

//*Cotizaciones*/
    if (menu.equals("Cotizaciones")) {
    System.out.println("=== INICIO MÓDULO COTIZACIONES ===");

    // ✅ Obtener datos de sesión
    HttpSession sesion = request.getSession();
    Integer nivelPermiso = (Integer) sesion.getAttribute("nivelPermiso");
    String codUser = (String) sesion.getAttribute("COD_USER");
    
    System.out.println("Nivel de permiso: " + nivelPermiso);
    System.out.println("Código de usuario: " + codUser);

    // ✅ Si es cliente (nivel 3), obtener su COD_CLI
    String codClienteLogueado = null;
    if (nivelPermiso != null && nivelPermiso == 3) {
        System.out.println("=== USUARIO ES CLIENTE ===");
        Cliente clienteLogueado = clidao.obtenerClientePorUsuario(codUser);
        if (clienteLogueado != null) {
            codClienteLogueado = clienteLogueado.getCOD_CLI();
            System.out.println("✅ Cliente encontrado: " + codClienteLogueado);
        } else {
            System.out.println("❌ No se encontró cliente para el usuario: " + codUser);
        }
    }

    switch (accion) {
        case "Listar":
            System.out.println("=== LISTAR COTIZACIONES ===");
            List<Cotizacion> listaCot = null;

            // ✅ Filtrar según el nivel de usuario
            if (nivelPermiso != null && nivelPermiso == 3 && codClienteLogueado != null) {
                // 🔒 CLIENTE: Solo ve sus cotizaciones
                System.out.println("🔒 Cliente - Mostrando solo cotizaciones de: " + codClienteLogueado);
                listaCot = cotdao.listarPorCliente(codClienteLogueado);
            } else {
                // 🔓 Admin/Cobrador: Ve todas las cotizaciones
                System.out.println("🔓 Admin/Cobrador - Mostrando TODAS las cotizaciones");
                listaCot = cotdao.listar();
            }

            if (listaCot == null || listaCot.isEmpty()) {
                System.out.println("⚠️ La lista de cotizaciones está vacía");
            } else {
                System.out.println("✅ Se encontraron " + listaCot.size() + " cotizaciones");
            }

            // Pasar datos a la vista
            request.setAttribute("cotizaciones", listaCot);

            // Solo cargar lista de clientes si NO es cliente
            if (nivelPermiso == null || nivelPermiso != 3) {
                List<Cliente> listaClientes = clidao.listar();
                request.setAttribute("clientes", listaClientes);
            }

            request.setAttribute("nivelPermiso", nivelPermiso);
            request.getRequestDispatcher("VIEWS/TEMPLATES/Cotizacion.jsp").forward(request, response);
            return;

        case "agregar":
            // ✅ Solo Admin y Cobrador pueden agregar (nivel 1 y 2)
            if (nivelPermiso != null && nivelPermiso == 3) {
                System.out.println("❌ Cliente intentó agregar cotización");
                request.setAttribute("resultado", 0);
                request.getRequestDispatcher("Controlador?menu=Cotizaciones&accion=Listar").forward(request, response);
                return;
            }

            String codCli = request.getParameter("txtCodCli");
            String numCot = request.getParameter("txtNumCot");
            String idTrans = request.getParameter("txtIdTrans");
            String periodo = request.getParameter("txtPeriodo");
            String fecEmisionStr = request.getParameter("txtFecEmision");
            String fecVencStr = request.getParameter("txtFecVenc");
            double monto = Double.parseDouble(request.getParameter("txtMonto"));
            String estado = request.getParameter("txtEstado");

            Date fecEmision = null;
            Date fecVenc = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                fecEmision = sdf.parse(fecEmisionStr);
                fecVenc = sdf.parse(fecVencStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            cot.setCOD_CLI(codCli);
            cot.setNUM_COT(numCot);
            cot.setID_TRANS(idTrans);
            cot.setPERIODO(periodo);
            cot.setFEC_EMISION(fecEmision);
            cot.setFEC_VENC(fecVenc);
            cot.setMONTO(monto);
            cot.setESTADO(estado);

            int resultadoCot = cotdao.agregar(cot);

            if (resultadoCot > 0) {
                request.setAttribute("resultado", 1);
            } else {
                request.setAttribute("resultado", 0);
            }

            request.getRequestDispatcher("Controlador?menu=Cotizaciones&accion=Listar").forward(request, response);
            return;

        case "Editar":
            System.out.println("========================================");
            System.out.println("=== EDITAR COTIZACIÓN - INICIO ===");
            System.out.println("idCot: " + request.getParameter("idCot"));
            System.out.println("nivelPermiso: " + nivelPermiso);
            System.out.println("========================================");

            String idCotStr = request.getParameter("idCot");
            int idCot = Integer.parseInt(idCotStr);

            Cotizacion cotEdit = cotdao.listarPorId(idCot);

            // ✅ Si es cliente, validar que la cotización le pertenece
            if (nivelPermiso != null && nivelPermiso == 3 && codClienteLogueado != null) {
                if (!cotEdit.getCOD_CLI().equals(codClienteLogueado)) {
                    System.out.println("❌ Cliente intentó editar cotización que no le pertenece");
                    request.setAttribute("resultadoUpdate", 0);
                    request.getRequestDispatcher("Controlador?menu=Cotizaciones&accion=Listar").forward(request, response);
                    return;
                }
            }

            request.setAttribute("cotizacione", cotEdit);
            request.getRequestDispatcher("Controlador?menu=Cotizaciones&accion=Listar").forward(request, response);
            return;

        case "Actualizar":
            // ✅ Validación de permisos
            String idActualizar = request.getParameter("txtId");
            int idCotActualizar = Integer.parseInt(idActualizar);
            
            // Si es cliente, validar que la cotización le pertenece
            if (nivelPermiso != null && nivelPermiso == 3 && codClienteLogueado != null) {
                Cotizacion cotValidar = cotdao.listarPorId(idCotActualizar);
                if (!cotValidar.getCOD_CLI().equals(codClienteLogueado)) {
                    System.out.println("❌ Cliente intentó actualizar cotización que no le pertenece");
                    request.setAttribute("resultadoUpdate", 0);
                    request.getRequestDispatcher("Controlador?menu=Cotizaciones&accion=Listar").forward(request, response);
                    return;
                }
            }

            String codCliActualizar = request.getParameter("txtCodCli");
            String numCotActualizar = request.getParameter("txtNumCot");
            String idTransActualizar = request.getParameter("txtIdTrans");
            String periodoActualizar = request.getParameter("txtPeriodo");
            String fecEmisionActStr = request.getParameter("txtFecEmision");
            String fecVencActStr = request.getParameter("txtFecVenc");
            double montoActualizar = Double.parseDouble(request.getParameter("txtMonto"));
            String estadoActualizar = request.getParameter("txtEstado");

            Date fecEmisionAct = null;
            Date fecVencAct = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                fecEmisionAct = sdf.parse(fecEmisionActStr);
                fecVencAct = sdf.parse(fecVencActStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            cot.setID(idCotActualizar);
            cot.setCOD_CLI(codCliActualizar);
            cot.setNUM_COT(numCotActualizar);
            cot.setID_TRANS(idTransActualizar);
            cot.setPERIODO(periodoActualizar);
            cot.setFEC_EMISION(fecEmisionAct);
            cot.setFEC_VENC(fecVencAct);
            cot.setMONTO(montoActualizar);
            cot.setESTADO(estadoActualizar);

            int resultadoActualizar = cotdao.actualizar(cot);

            if (resultadoActualizar > 0) {
                request.setAttribute("resultadoUpdate", 1);
            } else {
                request.setAttribute("resultadoUpdate", 0);
            }

            request.getRequestDispatcher("Controlador?menu=Cotizaciones&accion=Listar").forward(request, response);
            return;

        case "Cancelar":
            // ✅ Solo Admin y Cobrador pueden cancelar (nivel 1 y 2)
            if (nivelPermiso != null && nivelPermiso == 3) {
                System.out.println("❌ Cliente intentó cancelar cotización");
                request.setAttribute("resultadoCancelar", 0);
                request.getRequestDispatcher("Controlador?menu=Cotizaciones&accion=Listar").forward(request, response);
                return;
            }

            System.out.println("dentro de cancelar cotización");
            String idCancelarStr = request.getParameter("idCot");
            int idCancelar = Integer.parseInt(idCancelarStr);

            int resultadoCancelar = cotdao.cancelarCotizacion(idCancelar);

            if (resultadoCancelar > 0) {
                request.setAttribute("resultadoCancelar", 1);
            } else {
                request.setAttribute("resultadoCancelar", 0);
            }

            request.getRequestDispatcher("Controlador?menu=Cotizaciones&accion=Listar").forward(request, response);
            return;

        case "BuscarPorCodCli":
            String codCliBuscar = request.getParameter("codCli");
            System.out.println("🔍 Buscando por código cliente: " + codCliBuscar + " | Nivel: " + nivelPermiso);
            
            CotizacionDao cotizacionDao = new CotizacionDao();
            List<Cotizacion> cotizacionesPorCliente;

            // ✅ NIVEL 3 (Cliente): Solo buscar en sus cotizaciones
            if (nivelPermiso != null && nivelPermiso == 3 && codClienteLogueado != null) {
                System.out.println("🔒 Búsqueda restringida a cliente: " + codClienteLogueado);
                cotizacionesPorCliente = cotizacionDao.listarPorCliente(codClienteLogueado);
                // Filtrar por el código buscado si se ingresó algo
                if (codCliBuscar != null && !codCliBuscar.trim().isEmpty()) {
                    List<Cotizacion> filtradas = new ArrayList<>();
                    for (Cotizacion c : cotizacionesPorCliente) {
                        if (c.getCOD_CLI().toLowerCase().contains(codCliBuscar.toLowerCase())) {
                            filtradas.add(c);
                        }
                    }
                    cotizacionesPorCliente = filtradas;
                }
            } else {
                // ✅ NIVEL 1 y 2 (Admin/Cobrador): Buscar en todas
                System.out.println("🔓 Búsqueda completa para Admin/Cobrador");
                cotizacionesPorCliente = cotizacionDao.buscarPorCodigoCliente(codCliBuscar);
            }

            System.out.println("✅ Resultados encontrados: " + cotizacionesPorCliente.size());

            StringBuilder htmlResponse = new StringBuilder();
            for (Cotizacion c : cotizacionesPorCliente) {
                htmlResponse.append("<tr>")
                        .append("<td>").append(c.getID()).append("</td>")
                        .append("<td>").append(c.getCOD_CLI()).append("</td>")
                        .append("<td>").append(c.getNUM_COT()).append("</td>")
                        .append("<td>").append(c.getID_TRANS()).append("</td>")
                        .append("<td>").append(c.getPERIODO()).append("</td>")
                        .append("<td>").append(c.getFEC_EMISION()).append("</td>")
                        .append("<td>").append(c.getFEC_VENC()).append("</td>")
                        .append("<td>Q ").append(c.getMONTO()).append("</td>")
                        .append("<td>");

                if (c.getESTADO().equals("A")) {
                    htmlResponse.append("<span class='badge bg-danger'>Pendiente de Pago</span>");
                } else {
                    htmlResponse.append("<span class='badge bg-success'>Cancelado</span>");
                }

                htmlResponse.append("</td>").append("<td>");

                // ✅ Solo Admin y Cobrador ven botones de acción
                if (nivelPermiso != null && nivelPermiso != 3) {
                    htmlResponse.append("<a class='btn btn-warning btn-sm' ")
                            .append("href='Controlador?menu=Cotizaciones&accion=Editar&idCot=").append(c.getID()).append("' ")
                            .append("title='Editar'><i class='fas fa-edit'></i></a>");

                    if (c.getESTADO().equals("A")) {
                        htmlResponse.append(" <a class='btn btn-danger btn-sm' ")
                                .append("href='Controlador?menu=Cotizaciones&accion=Cancelar&idCot=").append(c.getID()).append("' ")
                                .append("title='Cancelar' onclick='return confirm(\"¿Está seguro de cancelar esta cotización?\")'>")
                                .append("<i class='fas fa-times-circle'></i></a>");
                    }
                } else {
                    htmlResponse.append("<span class='text-muted' style='font-size: 0.85rem;'>-</span>");
                }

                htmlResponse.append("</td>").append("</tr>");
            }

            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(htmlResponse.toString());
            out.flush();
            out.close();
            return;

        case "BuscarPorNumCot":
            String numCotBuscar = request.getParameter("numCot");
            System.out.println("🔍 Buscando por número cotización: " + numCotBuscar + " | Nivel: " + nivelPermiso);
            
            cotizacionDao = new CotizacionDao();
            List<Cotizacion> cotizacionesEncontradas = cotizacionDao.buscarPorNumCot(numCotBuscar);

            htmlResponse = new StringBuilder();
            for (Cotizacion c : cotizacionesEncontradas) {
                // ✅ Si es cliente, validar que la cotización sea suya
                boolean mostrar = true;
                if (nivelPermiso != null && nivelPermiso == 3 && codClienteLogueado != null) {
                    mostrar = c.getCOD_CLI().equals(codClienteLogueado);
                }

                if (mostrar) {
                    htmlResponse.append("<tr>")
                            .append("<td>").append(c.getID()).append("</td>")
                            .append("<td>").append(c.getCOD_CLI()).append("</td>")
                            .append("<td>").append(c.getNUM_COT()).append("</td>")
                            .append("<td>").append(c.getID_TRANS()).append("</td>")
                            .append("<td>").append(c.getPERIODO()).append("</td>")
                            .append("<td>").append(c.getFEC_EMISION()).append("</td>")
                            .append("<td>").append(c.getFEC_VENC()).append("</td>")
                            .append("<td>Q ").append(c.getMONTO()).append("</td>")
                            .append("<td>");

                    if (c.getESTADO().equals("A")) {
                        htmlResponse.append("<span class='badge bg-danger'>Pendiente de Pago</span>");
                    } else {
                        htmlResponse.append("<span class='badge bg-success'>Cancelado</span>");
                    }

                    htmlResponse.append("</td>").append("<td>");

                    if (nivelPermiso != null && nivelPermiso != 3) {
                        htmlResponse.append("<a class='btn btn-warning btn-sm' ")
                                .append("href='Controlador?menu=Cotizaciones&accion=Editar&idCot=").append(c.getID()).append("' ")
                                .append("title='Editar'><i class='fas fa-edit'></i></a>");

                        if (c.getESTADO().equals("A")) {
                            htmlResponse.append(" <a class='btn btn-danger btn-sm' ")
                                    .append("href='Controlador?menu=Cotizaciones&accion=Cancelar&idCot=").append(c.getID()).append("' ")
                                    .append("title='Cancelar' onclick='return confirm(\"¿Está seguro de cancelar esta cotización?\")'>")
                                    .append("<i class='fas fa-times-circle'></i></a>");
                        }
                    } else {
                        htmlResponse.append("<span class='text-muted' style='font-size: 0.85rem;'>-</span>");
                    }

                    htmlResponse.append("</td>").append("</tr>");
                }
            }

            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.print(htmlResponse.toString());
            out.flush();
            out.close();
            return;

        case "BuscarPorPeriodo":
            String periodoBuscar = request.getParameter("periodo");
            System.out.println("🔍 Buscando por periodo: " + periodoBuscar + " | Nivel: " + nivelPermiso);
            
            cotizacionDao = new CotizacionDao();
            List<Cotizacion> cotizacionesPorPeriodo;

            if (nivelPermiso != null && nivelPermiso == 3 && codClienteLogueado != null) {
                List<Cotizacion> todas = cotizacionDao.listarPorPeriodo(periodoBuscar);
                cotizacionesPorPeriodo = new ArrayList<>();
                for (Cotizacion cotizacion : todas) {
                    if (cotizacion.getCOD_CLI().equals(codClienteLogueado)) {
                        cotizacionesPorPeriodo.add(cotizacion);
                    }
                }
            } else {
                cotizacionesPorPeriodo = cotizacionDao.listarPorPeriodo(periodoBuscar);
            }

            htmlResponse = new StringBuilder();
            for (Cotizacion c : cotizacionesPorPeriodo) {
                htmlResponse.append("<tr>")
                        .append("<td>").append(c.getID()).append("</td>")
                        .append("<td>").append(c.getCOD_CLI()).append("</td>")
                        .append("<td>").append(c.getNUM_COT()).append("</td>")
                        .append("<td>").append(c.getID_TRANS()).append("</td>")
                        .append("<td>").append(c.getPERIODO()).append("</td>")
                        .append("<td>").append(c.getFEC_EMISION()).append("</td>")
                        .append("<td>").append(c.getFEC_VENC()).append("</td>")
                        .append("<td>Q ").append(c.getMONTO()).append("</td>")
                        .append("<td>");

                if (c.getESTADO().equals("A")) {
                    htmlResponse.append("<span class='badge bg-danger'>Pendiente de Pago</span>");
                } else {
                    htmlResponse.append("<span class='badge bg-success'>Cancelado</span>");
                }

                htmlResponse.append("</td>").append("<td>");

                if (nivelPermiso != null && nivelPermiso != 3) {
                    htmlResponse.append("<a class='btn btn-warning btn-sm' ")
                            .append("href='Controlador?menu=Cotizaciones&accion=Editar&idCot=").append(c.getID()).append("' ")
                            .append("title='Editar'><i class='fas fa-edit'></i></a>");

                    if (c.getESTADO().equals("A")) {
                        htmlResponse.append(" <a class='btn btn-danger btn-sm' ")
                                .append("href='Controlador?menu=Cotizaciones&accion=Cancelar&idCot=").append(c.getID()).append("' ")
                                .append("title='Cancelar' onclick='return confirm(\"¿Está seguro de cancelar esta cotización?\")'>")
                                .append("<i class='fas fa-times-circle'></i></a>");
                    }
                } else {
                    htmlResponse.append("<span class='text-muted' style='font-size: 0.85rem;'>-</span>");
                }

                htmlResponse.append("</td>").append("</tr>");
            }

            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.print(htmlResponse.toString());
            out.flush();
            out.close();
            return;
case "GenerarPDF":
    System.out.println("entro a case GENERAR PDF");
    String numCotPDF = request.getParameter("numCot");
    String idTransPDF = request.getParameter("idTrans");
    
    System.out.println("=== GENERANDO PDF ===");
    System.out.println("NumCot: " + numCotPDF);
    System.out.println("IdTrans: " + idTransPDF);
    
    // Verificar que existe el pago
    if (!cotdao.existePago(numCotPDF, idTransPDF)) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontró el pago");
        return;
    }
    
    // Obtener datos completos del pago
    Map<String, Object> datosPago = cotdao.obtenerDatosPagoCompleto(numCotPDF, idTransPDF);
    
    if (datosPago.isEmpty()) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontraron datos del pago");
        return;
    }
    
    // Configurar respuesta para PDF
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", 
                      "inline; filename=Comprobante_" + numCotPDF + ".pdf");
    
    try {
        // Opción 1: Usar iText PDF (requiere librería iText)
        generarPDFConIText(response.getOutputStream(), datosPago);
        
        // Opción 2 (alternativa): Generar HTML y convertir a PDF
        // generarPDFDesdeHTML(response.getOutputStream(), datosPago);
        
    } catch (Exception e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                          "Error al generar el PDF");
    }
    return;
        case "FiltrarPorEstado":
            String estadoFiltro = request.getParameter("estado");
            System.out.println("🔍 Filtrando por estado: " + estadoFiltro + " | Nivel: " + nivelPermiso);
            
            cotizacionDao = new CotizacionDao();
            List<Cotizacion> cotizacionesPorEstado;

            if (nivelPermiso != null && nivelPermiso == 3 && codClienteLogueado != null) {
                List<Cotizacion> todasCliente = cotizacionDao.listarPorCliente(codClienteLogueado);
                cotizacionesPorEstado = new ArrayList<>();
                for (Cotizacion cotizacion : todasCliente) {
                    if (cotizacion.getESTADO().equals(estadoFiltro)) {
                        cotizacionesPorEstado.add(cotizacion);
                    }
                }
            } else {
                cotizacionesPorEstado = cotizacionDao.filtrarPorEstado(estadoFiltro);
            }

            htmlResponse = new StringBuilder();
            for (Cotizacion c : cotizacionesPorEstado) {
                htmlResponse.append("<tr>")
                        .append("<td>").append(c.getID()).append("</td>")
                        .append("<td>").append(c.getCOD_CLI()).append("</td>")
                        .append("<td>").append(c.getNUM_COT()).append("</td>")
                        .append("<td>").append(c.getID_TRANS()).append("</td>")
                        .append("<td>").append(c.getPERIODO()).append("</td>")
                        .append("<td>").append(c.getFEC_EMISION()).append("</td>")
                        .append("<td>").append(c.getFEC_VENC()).append("</td>")
                        .append("<td>Q ").append(c.getMONTO()).append("</td>")
                        .append("<td>");

                if (c.getESTADO().equals("A")) {
                    htmlResponse.append("<span class='badge bg-danger'>Pendiente de Pago</span>");
                } else {
                    htmlResponse.append("<span class='badge bg-success'>Cancelado</span>");
                }

                htmlResponse.append("</td>").append("<td>");

                if (nivelPermiso != null && nivelPermiso != 3) {
                    htmlResponse.append("<a class='btn btn-warning btn-sm' ")
                            .append("href='Controlador?menu=Cotizaciones&accion=Editar&idCot=").append(c.getID()).append("' ")
                            .append("title='Editar'><i class='fas fa-edit'></i></a>");

                    if (c.getESTADO().equals("A")) {
                        htmlResponse.append(" <a class='btn btn-danger btn-sm' ")
                                .append("href='Controlador?menu=Cotizaciones&accion=Cancelar&idCot=").append(c.getID()).append("' ")
                                .append("title='Cancelar' onclick='return confirm(\"¿Está seguro de cancelar esta cotización?\")'>")
                                .append("<i class='fas fa-times-circle'></i></a>");
                    }
                } else {
                    htmlResponse.append("<span class='text-muted' style='font-size: 0.85rem;'>-</span>");
                }

                htmlResponse.append("</td>").append("</tr>");
            }

            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.print(htmlResponse.toString());
            out.flush();
            out.close();
            return;
            // Agregar en el switch de Cotizaciones en el Controlador


        default:
            System.out.println("❌ Acción no reconocida: " + accion);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida");
            return;
    }
}

        /*Empleados*/
        if (menu.equals("Empleados")) {
            System.out.println("dentro de Empleados");

            switch (accion) {
                case "Listar":
                    System.out.println("dentro de Asistencia LISTAR");

                    // Obtener la lista de empleados
                    List<Empleado> lista = empdao.listar();

                    if (lista == null || lista.isEmpty()) {
                        System.out.println("La lista de empleados está vacía");
                    } else {
                        for (Empleado empleado : lista) {
                            System.out.println(empleado.getCOC_EMPD());
                        }
                    }

                    // Pasar la lista a la vista
                    request.setAttribute("empleados", lista);

                    List listar = emdao.listar();

                    // Pasar la lista a la vista
                    request.setAttribute("empleosd", listar);

                    List<Empresa> listae = empsadao.listar();

                    listae = empsadao.listar();
                    // Pasar la lista a la vista
                    request.setAttribute("empresad", listae);
                    request.getRequestDispatcher("VIEWS/TEMPLATES/Empleados.jsp").forward(request, response);

                    break;
                case "ListarRE":
                    System.out.println("dentro de Asistencia LISTAR REPORTES");

                    // Obtener la lista de empleados
                    lista = empdao.listarActivos();

                    if (lista == null || lista.isEmpty()) {
                        System.out.println("La lista de empleados está vacía");
                    } else {
                        for (Empleado empleado : lista) {
                            System.out.println(empleado.getCOC_EMPD());
                        }
                    }

                    // Pasar la lista a la vista
                    request.setAttribute("empleados", lista);
                    request.getRequestDispatcher("VIEWS/TEMPLATES/REPORTES/Empleados.jsp").forward(request, response);

                    return;

                case "agregar":
                    String cod = request.getParameter("txtCod");
                    long dpi = Long.parseLong(request.getParameter("txtDpi"));
                    String nom = request.getParameter("txtNombres");
                    String ape = request.getParameter("txtApellidos");
                    long igss = Long.parseLong(request.getParameter("txtIgss"));
                    int nit = Integer.parseInt(request.getParameter("txtNit"));
                    String cod_emp = request.getParameter("txtCod_emp");
                    String Cod_empsa = request.getParameter("txtCod_empsa");
                    int Celular = Integer.parseInt(request.getParameter("txtCelular"));
                    String Correo = request.getParameter("txtCorreo");

                    emp.setCOC_EMPD(cod);
                    emp.setDPI(dpi);
                    emp.setNOMBRES(nom);
                    emp.setAPELLIDOS(ape);
                    emp.setN_IGSS(igss);
                    emp.setNIT(nit);
                    emp.setCOD_EMP(cod_emp);
                    emp.setCOD_EMPSA(Cod_empsa);
                    emp.setCELULAR(Celular);
                    emp.setCorreo(Correo);

                    int resultado = empdao.agregar(emp);

                    // Agregar atributo según si fue éxito o error
                    if (resultado > 0) {
                        request.setAttribute("resultado", 1);
                    } else {
                        request.setAttribute("resultado", 0);
                    }

                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                    return;
                case "Editar":
                    System.out.println("dentro de editar");
                    String emp_cod = request.getParameter("empCod"); // Obtener ID desde la URL
                    System.out.println("el cod a editar es : " + emp_cod);
                    System.out.println("-------------------------------------");

                    Empleado e = empdao.ListarId(emp_cod); // Llamar al método listarId en el DAO

                    request.setAttribute("empleadoe", e);

                    // Redirigir al JSP para que se muestre la modal con los datos del empleado
                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                    return;

                case "Actualizar":
                    // Obtener los datos del formulario
                    String CodActualizar = request.getParameter("txtCod");
                    long dpiActualizar = Long.parseLong(request.getParameter("txtDpi"));
                    String nombresActualizar = request.getParameter("txtNombres");
                    String apellidosActualizar = request.getParameter("txtApellidos");
                    long igssActualizar = Long.parseLong(request.getParameter("txtIgss"));
                    int nitActualizar = Integer.parseInt(request.getParameter("txtNit"));
                    String codEmpActualizar = request.getParameter("txtCod_emp");
                    String codEmpsaActualizar = request.getParameter("txtCod_empsa");
                    int celularActualizar = Integer.parseInt(request.getParameter("txtCelular"));
                    String correoActualizar = request.getParameter("txtCorreo");
                    String estadoActualizar = request.getParameter("txtEstado");

// Asignar los valores al objeto empleado
                    emp.setCOC_EMPD(CodActualizar);
                    emp.setDPI(dpiActualizar);
                    emp.setNOMBRES(nombresActualizar);
                    emp.setAPELLIDOS(apellidosActualizar);
                    emp.setN_IGSS(igssActualizar);
                    emp.setNIT(nitActualizar);
                    emp.setCOD_EMP(codEmpActualizar);
                    emp.setCOD_EMPSA(codEmpsaActualizar);
                    emp.setCELULAR(celularActualizar);
                    emp.setCorreo(correoActualizar);
                    emp.setEstado(estadoActualizar);

                    // Llamar al DAO para actualizar el empleado en la base de datos
                    int resultadoActualizar = empdao.actualizar(emp);

                    // Verificar si la actualización fue exitosa
                    if (resultadoActualizar > 0) {
                        request.setAttribute("resultadoUpdate", 1); // Éxito
                    } else {
                        request.setAttribute("resultadoUpdate", 0); // Error
                    }

                    // Redirigir a la lista de empleados
                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                    return;
                case "BuscarPorCodigo":
                    String codigo = request.getParameter("codigo");
                    EmpleadoDao empleadoDao = new EmpleadoDao();
                    List<Empleado> empleadosPorCodigo = empleadoDao.buscarPorCodigo(codigo);

                    // Construir la respuesta HTML
                    StringBuilder htmlResponse = new StringBuilder();
                    for (Empleado em : empleadosPorCodigo) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(em.getId()).append("</td>")
                                .append("<td>").append(em.getCOC_EMPD()).append("</td>")
                                .append("<td>").append(em.getDPI()).append("</td>") // ✅ DPI agregado
                                .append("<td>").append(em.getNOMBRES()).append("</td>")
                                .append("<td>").append(em.getAPELLIDOS()).append("</td>")
                                .append("<td>").append(em.getN_IGSS()).append("</td>")
                                .append("<td>").append(em.getNIT()).append("</td>")
                                .append("<td>").append(em.getCOD_EMP()).append("</td>")
                                .append("<td>").append(em.getCOD_EMPSA()).append("</td>")
                                .append("<td>").append(em.getCELULAR()).append("</td>")
                                .append("<td>").append(em.getCorreo()).append("</td>")
                                .append("<td>").append(em.getEstado()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' ")
                                .append("href='Controlador?menu=Empleados&accion=Editar&empCod=").append(em.getCOC_EMPD()).append("' ")
                                .append("title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                case "BuscarPorNombre":
                    String nombre = request.getParameter("nombre");
                    empleadoDao = new EmpleadoDao();
                    List<Empleado> empleadosPorNombre = empleadoDao.buscarPorNombre(nombre);

                    htmlResponse = new StringBuilder();
                    for (Empleado em : empleadosPorNombre) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(em.getId()).append("</td>")
                                .append("<td>").append(em.getCOC_EMPD()).append("</td>")
                                .append("<td>").append(em.getDPI()).append("</td>") // ✅ DPI agregado
                                .append("<td>").append(em.getNOMBRES()).append("</td>")
                                .append("<td>").append(em.getAPELLIDOS()).append("</td>")
                                .append("<td>").append(em.getN_IGSS()).append("</td>")
                                .append("<td>").append(em.getNIT()).append("</td>")
                                .append("<td>").append(em.getCOD_EMP()).append("</td>")
                                .append("<td>").append(em.getCOD_EMPSA()).append("</td>")
                                .append("<td>").append(em.getCELULAR()).append("</td>")
                                .append("<td>").append(em.getCorreo()).append("</td>")
                                .append("<td>").append(em.getEstado()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' ")
                                .append("href='Controlador?menu=Empleados&accion=Editar&empCod=").append(em.getCOC_EMPD()).append("' ")
                                .append("title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                case "FiltrarPorEstado":
                    String estado = request.getParameter("estado");
                    empleadoDao = new EmpleadoDao();
                    List<Empleado> empleadosPorEstado = empleadoDao.filtrarPorEstado(estado);

                    htmlResponse = new StringBuilder();
                    for (Empleado em : empleadosPorEstado) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(em.getId()).append("</td>")
                                .append("<td>").append(em.getCOC_EMPD()).append("</td>")
                                .append("<td>").append(em.getDPI()).append("</td>") // ✅ DPI agregado
                                .append("<td>").append(em.getNOMBRES()).append("</td>")
                                .append("<td>").append(em.getAPELLIDOS()).append("</td>")
                                .append("<td>").append(em.getN_IGSS()).append("</td>")
                                .append("<td>").append(em.getNIT()).append("</td>")
                                .append("<td>").append(em.getCOD_EMP()).append("</td>")
                                .append("<td>").append(em.getCOD_EMPSA()).append("</td>")
                                .append("<td>").append(em.getCELULAR()).append("</td>")
                                .append("<td>").append(em.getCorreo()).append("</td>")
                                .append("<td>").append(em.getEstado()).append("</td>")
                                .append("<td>")
                                .append("<a class='btn btn-warning btn-sm' ")
                                .append("href='Controlador?menu=Empleados&accion=Editar&empCod=").append(em.getCOC_EMPD()).append("' ")
                                .append("title='Editar'><i class='fas fa-edit'></i></a>")
                                .append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

// FILTROS DE REPORTERIA
                case "BuscarPorCodigoRE":
                    codigo = request.getParameter("codigo");
                    empleadoDao = new EmpleadoDao();
                    empleadosPorCodigo = empleadoDao.buscarPorCodigo(codigo);

                    // Construir la respuesta HTML
                    htmlResponse = new StringBuilder();
                    for (Empleado em : empleadosPorCodigo) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(em.getId()).append("</td>")
                                .append("<td>").append(em.getCOC_EMPD()).append("</td>")
                                .append("<td>").append(em.getDPI()).append("</td>") // ✅ DPI agregado
                                .append("<td>").append(em.getNOMBRES()).append("</td>")
                                .append("<td>").append(em.getAPELLIDOS()).append("</td>")
                                .append("<td>").append(em.getN_IGSS()).append("</td>")
                                .append("<td>").append(em.getNIT()).append("</td>")
                                .append("<td>").append(em.getCOD_EMP()).append("</td>")
                                .append("<td>").append(em.getCOD_EMPSA()).append("</td>")
                                .append("<td>").append(em.getCELULAR()).append("</td>")
                                .append("<td>").append(em.getCorreo()).append("</td>")
                                .append("<td>").append(em.getEstado()).append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                case "BuscarPorNombreRE":
                    nombre = request.getParameter("nombre");
                    empleadoDao = new EmpleadoDao();
                    empleadosPorNombre = empleadoDao.buscarPorNombre(nombre);

                    htmlResponse = new StringBuilder();
                    for (Empleado em : empleadosPorNombre) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(em.getId()).append("</td>")
                                .append("<td>").append(em.getCOC_EMPD()).append("</td>")
                                .append("<td>").append(em.getDPI()).append("</td>") // ✅ DPI agregado
                                .append("<td>").append(em.getNOMBRES()).append("</td>")
                                .append("<td>").append(em.getAPELLIDOS()).append("</td>")
                                .append("<td>").append(em.getN_IGSS()).append("</td>")
                                .append("<td>").append(em.getNIT()).append("</td>")
                                .append("<td>").append(em.getCOD_EMP()).append("</td>")
                                .append("<td>").append(em.getCOD_EMPSA()).append("</td>")
                                .append("<td>").append(em.getCELULAR()).append("</td>")
                                .append("<td>").append(em.getCorreo()).append("</td>")
                                .append("<td>").append(em.getEstado()).append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                case "FiltrarPorEstadoRE":
                    estado = request.getParameter("estado");
                    empleadoDao = new EmpleadoDao();
                    empleadosPorEstado = empleadoDao.filtrarPorEstado(estado);

                    htmlResponse = new StringBuilder();
                    for (Empleado em : empleadosPorEstado) {
                        htmlResponse.append("<tr>")
                                .append("<td>").append(em.getId()).append("</td>")
                                .append("<td>").append(em.getCOC_EMPD()).append("</td>")
                                .append("<td>").append(em.getDPI()).append("</td>") // ✅ DPI agregado
                                .append("<td>").append(em.getNOMBRES()).append("</td>")
                                .append("<td>").append(em.getAPELLIDOS()).append("</td>")
                                .append("<td>").append(em.getN_IGSS()).append("</td>")
                                .append("<td>").append(em.getNIT()).append("</td>")
                                .append("<td>").append(em.getCOD_EMP()).append("</td>")
                                .append("<td>").append(em.getCOD_EMPSA()).append("</td>")
                                .append("<td>").append(em.getCELULAR()).append("</td>")
                                .append("<td>").append(em.getCorreo()).append("</td>")
                                .append("<td>").append(em.getEstado()).append("</td>")
                                .append("</tr>");
                    }

                    response.setContentType("text/html");
                    response.getWriter().write(htmlResponse.toString());
                    return;

                default:
                    throw new AssertionError();
            }

            //request.getRequestDispatcher("VIEWS/TEMPLATES/Empleados.jsp").forward(request, response);
        }
        
        // Agregar este método en el Controlador.java

// Necesitas agregar la librería iText a tu proyecto (itextpdf-5.5.13.jar)




    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
private void generarPDFConIText(OutputStream out, Map<String, Object> datos) throws Exception {
    Document document = new Document(PageSize.A4);
    PdfWriter.getInstance(document, out);
    document.open();
    
    // Fuentes
    Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
    Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    Font normalFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");
    
    // Título
    Paragraph title = new Paragraph("COMPROBANTE DE PAGO", titleFont);
    title.setAlignment(Element.ALIGN_CENTER);
    document.add(title);
    
    document.add(new Paragraph(" ")); // Espacio
    
    // Información de la empresa
    Paragraph empresa = new Paragraph("PROMED INTERNET S.A.", headerFont);
    empresa.setAlignment(Element.ALIGN_CENTER);
    document.add(empresa);
    
    Paragraph dirEmpresa = new Paragraph("Dirección: Av. Principal 123, Guatemala\n" +
                                       "Tel: +502 1234-5678 | Email: info@promed.com", normalFont);
    dirEmpresa.setAlignment(Element.ALIGN_CENTER);
    document.add(dirEmpresa);
    
    document.add(new Paragraph(" "));
    
    // Línea separadora
    document.add(new LineSeparator());
    document.add(new Paragraph(" "));
    
    // Crear tabla para datos del comprobante
    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(100);
    table.setWidths(new float[]{1, 2});
    
    // Datos del comprobante
    addTableRow(table, "Nº Referencia:", (String) datos.get("numRef"), headerFont, normalFont);
    addTableRow(table, "Fecha de Pago:", sdf.format(datos.get("fecPago")), headerFont, normalFont);
    addTableRow(table, "Nº Cotización:", (String) datos.get("numCot"), headerFont, normalFont);
    addTableRow(table, "ID Transacción:", (String) datos.get("idTrans"), headerFont, normalFont);
    
    document.add(table);
    document.add(new Paragraph(" "));
    
    // Datos del cliente
    Paragraph clienteHeader = new Paragraph("DATOS DEL CLIENTE", headerFont);
    document.add(clienteHeader);
    document.add(new Paragraph(" "));
    
    PdfPTable clienteTable = new PdfPTable(2);
    clienteTable.setWidthPercentage(100);
    clienteTable.setWidths(new float[]{1, 2});
    
    addTableRow(clienteTable, "Cliente:", (String) datos.get("nombreCliente"), headerFont, normalFont);
    addTableRow(clienteTable, "DPI:", (String) datos.get("dpiCliente"), headerFont, normalFont);
    addTableRow(clienteTable, "NIT:", (String) datos.get("nitCliente"), headerFont, normalFont);
    addTableRow(clienteTable, "Dirección:", (String) datos.get("dirCliente"), headerFont, normalFont);
    addTableRow(clienteTable, "Teléfono:", (String) datos.get("telCliente"), headerFont, normalFont);
    addTableRow(clienteTable, "Correo:", (String) datos.get("correoCliente"), headerFont, normalFont);
    
    document.add(clienteTable);
    document.add(new Paragraph(" "));
    
    // Detalle del pago
    Paragraph detalleHeader = new Paragraph("DETALLE DEL PAGO", headerFont);
    document.add(detalleHeader);
    document.add(new Paragraph(" "));
    
    PdfPTable detalleTable = new PdfPTable(4);
    detalleTable.setWidthPercentage(100);
    detalleTable.setWidths(new float[]{2, 3, 1, 1});
    
    // Headers
    PdfPCell cell;
    cell = new PdfPCell(new Phrase("PERIODO", headerFont));
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    cell.setPadding(5);
    detalleTable.addCell(cell);
    
    cell = new PdfPCell(new Phrase("DESCRIPCIÓN", headerFont));
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    cell.setPadding(5);
    detalleTable.addCell(cell);
    
    cell = new PdfPCell(new Phrase("MÉTODO", headerFont));
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    cell.setPadding(5);
    detalleTable.addCell(cell);
    
    cell = new PdfPCell(new Phrase("MONTO", headerFont));
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    cell.setPadding(5);
    detalleTable.addCell(cell);
    
    // Datos
    detalleTable.addCell(new Phrase((String) datos.get("periodo"), normalFont));
    
    String descripcion = "Pago del servicio: " + datos.get("nombrePlan") + 
                        "\n" + datos.get("descPlan");
    detalleTable.addCell(new Phrase(descripcion, normalFont));
    
    detalleTable.addCell(new Phrase((String) datos.get("metodoPago"), normalFont));
    
    Double monto = (Double) datos.get("montoPagado");
    detalleTable.addCell(new Phrase("Q " + String.format("%.2f", monto), normalFont));
    
    document.add(detalleTable);
    document.add(new Paragraph(" "));
    
    // Total
    Paragraph total = new Paragraph("TOTAL PAGADO: Q " + String.format("%.2f", monto), titleFont);
    total.setAlignment(Element.ALIGN_RIGHT);
    document.add(total);
    
    document.add(new Paragraph(" "));
    document.add(new LineSeparator());
    document.add(new Paragraph(" "));
    
    // Estado del pago
    Font successFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.GREEN);
    Paragraph estado = new Paragraph("✓ PAGO REALIZADO CON ÉXITO", successFont);
    estado.setAlignment(Element.ALIGN_CENTER);
    document.add(estado);
    
    // Pie de página
    document.add(new Paragraph(" "));
    document.add(new Paragraph(" "));
    
    Paragraph footer = new Paragraph("Este es un comprobante válido de pago\n" +
                                   "Generado el " + sdf.format(new Date()) + " a las " + 
                                   sdfHora.format(new Date()), normalFont);
    footer.setAlignment(Element.ALIGN_CENTER);
    document.add(footer);
    
    document.close();
}

// Método auxiliar para agregar filas a la tabla
private void addTableRow(PdfPTable table, String label, String value, Font labelFont, Font valueFont) {
    PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
    labelCell.setBorder(Rectangle.NO_BORDER);
    labelCell.setPadding(5);
    table.addCell(labelCell);
    
    PdfPCell valueCell = new PdfPCell(new Phrase(value != null ? value : "", valueFont));
    valueCell.setBorder(Rectangle.NO_BORDER);
    valueCell.setPadding(5);
    table.addCell(valueCell);
}
}
