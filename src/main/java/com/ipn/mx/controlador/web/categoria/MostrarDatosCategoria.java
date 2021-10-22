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
import java.util.List;
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
@WebServlet(name = "MostrarDatosCategoria", urlPatterns = {"/MostrarDatosCategoria"})
public class MostrarDatosCategoria extends HttpServlet {

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
            out.println("<title>Lista de Categorias</title>");
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
            out.println("<h1>Lista de Categorías</h1>");
            CategoriaDAO dao = new CategoriaDAO();
            /*Creamos la tabla*/
            out.println("<table class='table table-dark  table-bordered border-primary'>");
            try {
                //out.println(dao.readAll());
                List lista = dao.readAll();
                out.println("<tr class='table-active'>");
                out.println("<th>Clave</th>");
                out.println("<th>Nombre</th>");
                out.println("<th>Descripción</th>");
                out.println("<th>Eliminar</th>");
                out.println("<th>Actualizar</th>");
                out.println("</tr>");
                for (int i = 0; i < lista.size(); i++) {
                    out.println("<tr>");
                    CategoriaDTO dto = (CategoriaDTO) lista.get(i);
                    out.println("<td><a href='VerCategoria?id=" + dto.getEntidad().getIdCategoria() + "'  class='btn btn-warning'>");
                    out.println(dto.getEntidad().getIdCategoria() + "</a></td>");
                    out.println("<td>" + dto.getEntidad().getNombreCategoria() + "</td>");
                    out.println("<td>" + dto.getEntidad().getDescripcionCategoria() + "</td>");
                    out.println("<td><a href='EliminarCategoria?id=" + dto.getEntidad().getIdCategoria() + "' class= 'btn btn-danger'>Eliminar</a></td>");
                    out.println("<td><a href='ActualizarCategoria?id=" + dto.getEntidad().getIdCategoria() + "' class= 'btn btn-success'>Actualizar</a></td>");
                    out.println("</tr>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(MostrarDatosCategoria.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</table>");
            out.println("<a href='categoriaForm.html' class='btn btn-primary'>Agregar Categoria</a>");
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
