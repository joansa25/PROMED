/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Empresa;
import Modelo.EmpresaDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joans
 */
public class Controlador extends HttpServlet {

   Empresa empsa = new Empresa();
    EmpresaDao empsadao = new EmpresaDao();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        System.out.println("Valor de 'menu': " + menu);
        System.out.println("Valor de 'accion': " + accion);
        if (menu.equals("Principal")) {
            request.getRequestDispatcher("VIEWS/TEMPLATES/menuPrincipal.jsp").forward(request, response);
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
