/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web.categoria;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JMTN
 */
@WebServlet(name = "AgregarCategoria", urlPatterns = {"/AgregarCategoria"})
public class AgregarCategoria extends HttpServlet {

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
            out.println("<title>Agregar Categoria</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css' rel='stylesheet' >");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js' ></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js' ></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js' ></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<nav class='navbar navbar-expand-lg navbar-light bg-light'>");
            out.println("<div class='container-fluid'>");
            out.println("<a class='navbar-brand' href='index.html'>Inicio</a>");
            out.println("<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarText' aria-controls='navbarText' aria-expanded='false' aria-label='Toggle navigation'>");
            out.println("<span class='navbar-toggler-icon'></span>");
            out.println("</button>");
            out.println("<div class='collapse navbar-collapse' id='navbarText'>");
            out.println("<ul class='navbar-nav me-auto mb-2 mb-lg-0'>");
            out.println(" <li class='nav-item'>");
            out.println("<a class='nav-link' href='TablasDeMultiplicar'>Tablas de multiplicar</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println(" <a class='nav-link' href='MostrarDatosCategoria'>Listado de categorias</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println(" <a class='nav-link' href='categoriaForm.html'>Nueva categoria</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link' href='ProductoController?accion=listaDeProductos'>Listado de productos</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println(" <a class='nav-link' href='productoForm.html'>Nuevo producto</a>");
            out.println("</li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("</div>");
            out.println("</nav>");
            String msg = "";
            CategoriaDAO dao = new CategoriaDAO();
            CategoriaDTO dto = new CategoriaDTO();
            if (request.getParameter("txtNombre").length() > 2 && request.getParameter("txtDescripcion").length() > 2) {
                dto.getEntidad().setNombreCategoria(request.getParameter("txtNombre"));
                dto.getEntidad().setDescripcionCategoria(request.getParameter("txtDescripcion"));
                try {
                    dao.create(dto);
                    msg = "Registro creado satisfactoriamente";
                } catch (SQLException ex) {
                    Logger.getLogger(AgregarCategoria.class.getName()).log(Level.SEVERE, null, ex);
                }

                out.println("<div align='center'>");
                out.println("<h1><b>" + msg + "</b></h1>");
                out.println("<a href='MostrarDatosCategoria'>Listado de categorias</a>");
                out.println("</div>");
            } else if (request.getParameter("txtNombre").length() < 2 || request.getParameter("txtDescripcion").length() < 2 || request.getParameter("txtNombre").equals(" ") || request.getParameter("txtDescripcion").equals(" ")) {
                msg = "ERROR, debes de ingresar datos en el formulario anterior";
                out.println("<center>");
                out.println("<h1><b>" + msg + "</b></h1>");
                out.println("<a class='btn btn-warning' title='Regresar al formulario' href='MostrarDatosCategoria'>Regresar</a>");
                out.println("</center>");
            }
            //out.println("<h1>Servlet AgregarCategoria at " + request.getContextPath() + "</h1>");
            out.println("</div>");
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

/*

if(request.getParameter("txtNombre").length() <= 2){
                out.println("ERROR");
            }


dto.getEntidad().setNombreCategoria(request.getParameter("txtNombre"));
            dto.getEntidad().setDescripcionCategoria(request.getParameter("txtDescripcion"));
            try {
                dao.create(dto);
                msg = "Registro creado satisfactoriamente";
            } catch (SQLException ex) {
                Logger.getLogger(AgregarCategoria.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            out.println("<div align='center'>");
            out.println("<b>"+msg+"</b>");
            out.println("<a href='MostrarDatosCategoria'>Listado de categorias</a>");
            out.println("</div>");




 */
