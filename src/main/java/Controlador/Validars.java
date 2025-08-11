/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.User;
import Modelo.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author joans
 */
public class Validars extends HttpServlet {

    User us = new User();
    UserDao usdao = new UserDao();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Validars</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Validars at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("ingresar")) {
            System.out.println("entrando  ingresar");
            String user = request.getParameter("txtuser");
            String pass = request.getParameter("txtpass");



            System.out.println("user: " + user);
            System.out.println("pss " + pass);
            User us = usdao.validar(user, pass);
            if (us != null && us.getUser_US() != null) {
    System.out.println("=== LOGIN EXITOSO ===");
    System.out.println("Usuario: " + us.getUser_US());
    System.out.println("Rol del usuario: " + us.getRol_Us());
    
    // Obtener nivel de permisos
    int nivelPermiso = usdao.obtenerNivelPermiso(us.getRol_Us());
    System.out.println("Nivel de permisos asignado: " + nivelPermiso);
    
    // Guardar en sesión
    request.getSession().setAttribute("usuario", us);
    request.getSession().setAttribute("nivelPermiso", nivelPermiso);
    
    System.out.println("Datos guardados en sesión - redirigiendo al menú principal");
    request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
            } else {
                request.getRequestDispatcher("index.html").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("index.html").forward(request, response);
            System.out.println("daots incorrectos!");

        }
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
