/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Empleado;
import Modelo.EmpleadoDao;
import Modelo.Empleo;
import Modelo.EmpleoDao;
import Modelo.Empresa;
import Modelo.EmpresaDao;
import Modelo.User;
import Modelo.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
            request.getRequestDispatcher("VIEWS/TEMPLATES/menuPrincipal.jsp").forward(request, response);
            break;
            
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

        /*ZONAS*/
        if (menu.equals("Zonas")) {
            System.out.println("Dentro de ZONAS");

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

            request.getRequestDispatcher("VIEWS/TEMPLATES/Zonas.jsp").forward(request, response);
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

        /*PLANES*/
        if (menu.equals("Planes")) {
            System.out.println("Dentro de Planes");

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

            request.getRequestDispatcher("VIEWS/TEMPLATES/Planes.jsp").forward(request, response);
        }
        /*CLIENTES*/
        if (menu.equals("Clientes")) {
            System.out.println("Dentro de Clientes");

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

            request.getRequestDispatcher("VIEWS/TEMPLATES/Clientes.jsp").forward(request, response);
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
                .append("<td>").append(em.getDPI()).append("</td>")              // ✅ DPI agregado
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
                .append("<td>").append(em.getDPI()).append("</td>")              // ✅ DPI agregado
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
                .append("<td>").append(em.getDPI()).append("</td>")              // ✅ DPI agregado
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
                .append("<td>").append(em.getDPI()).append("</td>")              // ✅ DPI agregado
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
                .append("<td>").append(em.getDPI()).append("</td>")              // ✅ DPI agregado
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
                .append("<td>").append(em.getDPI()).append("</td>")              // ✅ DPI agregado
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

}
