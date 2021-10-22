/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.ProductoDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JMTN
 */
public class ProductoDAO {

    private static final String SQL_INSERT = "INSERT INTO producto (nombreProducto, descripcionProducto, precio, existencia, stockMinimo, claveCategoria) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE producto SET nombreProducto = ?, descripcionProducto = ?, precio = ?, existencia = ?, stockMinimo = ?, claveCategoria = ? WHERE idProducto=?";
    private static final String SQL_DELETE = "DELETE FROM producto WHERE idProducto = ?";
    private static final String SQL_READ = "SELECT idProducto, nombreProducto, descripcionProducto, precio, existencia, stockMinimo, claveCategoria FROM producto WHERE idProducto = ?";
    private static final String SQL_READ_ALL = "SELECT idProducto, nombreProducto, descripcionProducto, precio, existencia, stockMinimo, claveCategoria FROM producto";

    private Connection conexion;

    /*private void conectar() {
        String user = "postgres";
        String pwd = "123";
        String url = "jdbc:postgresql://localhost:5432/Base3CM13";
        String pgDriver = "org.postgresql.Driver";
        try {
            //registra el driver de java para el manejador de base de datos
            Class.forName(pgDriver);
            conexion = DriverManager.getConnection(url, user, pwd);
            //debería de ser dos excepciones, class not found exception y sql exception, por no colocar de forma correcta los datos para la conexion a la base 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    private void conectar() {
        String user = "cxffhmminabyzh";
        String pwd = "b78bc1426a8c49195a09fae302216b016d43b35b1ee318a1ce6123c8672ac3f2";
        String url = "jdbc:postgresql://ec2-3-220-214-162.compute-1.amazonaws.com:5432/dbmtr1hpauq6dl";//sslmoderequire
        String pgDriver = "org.postgresql.Driver";
        try {
            //registra el driver de java para el manejador de base de datos
            Class.forName(pgDriver);
            conexion = DriverManager.getConnection(url, user, pwd);
            //debería de ser dos excepciones, class not found exception y sql exception, por no colocar de forma correcta los datos para la conexion a la base 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List readAll() throws SQLException {
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement(SQL_READ_ALL);
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if (resultados.size() > 0) {
                return resultados;
            } else {
                return null;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    public void create(ProductoDTO dto) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getEntidad().getNombreProducto());
            ps.setString(2, dto.getEntidad().getDescripcionProducto());
            ps.setFloat(3, dto.getEntidad().getPrecio());
            ps.setInt(4, dto.getEntidad().getExistencia());
            ps.setInt(5, dto.getEntidad().getStockMinimo());
            ps.setInt(6, dto.getEntidad().getClaveCategoria());
            ps.executeUpdate();
            
                    
        } finally {
            if (ps != null) {ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    public void update(ProductoDTO dto) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getEntidad().getNombreProducto());
            ps.setString(2, dto.getEntidad().getDescripcionProducto());
            ps.setFloat(3, dto.getEntidad().getPrecio());
            ps.setInt(4, dto.getEntidad().getExistencia());
            ps.setInt(5, dto.getEntidad().getStockMinimo());
            ps.setInt(6, dto.getEntidad().getClaveCategoria());
            ps.setInt(7, dto.getEntidad().getIdProducto());
            ps.executeUpdate();
            
                    
        } finally {
            if (ps != null) {ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
    public void delete(ProductoDTO dto) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdProducto());
            ps.executeUpdate();
            
                    
        } finally {
            if (ps != null) {ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
    
    public ProductoDTO read(ProductoDTO dto) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement(SQL_READ);
            ps.setInt(1, dto.getEntidad().getIdProducto());
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if (resultados.size() > 0) {
                return (ProductoDTO) resultados.get(0);
            } else {
                return null;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
    
    private List obtenerResultados(ResultSet rs) throws SQLException {
        List resultados = new ArrayList();
        while (rs.next()) {
            ProductoDTO p = new ProductoDTO();
            p.getEntidad().setIdProducto(rs.getInt("idProducto"));
            p.getEntidad().setNombreProducto(rs.getString("nombreProducto"));
            p.getEntidad().setDescripcionProducto(rs.getString("descripcionProducto"));
            p.getEntidad().setPrecio(rs.getFloat("precio"));
            p.getEntidad().setExistencia(rs.getInt("existencia"));
            p.getEntidad().setStockMinimo(rs.getInt("stockMinimo"));
            p.getEntidad().setClaveCategoria(rs.getInt("claveCategoria"));
            resultados.add(p);
        }
        return resultados;
    }

    public static void main(String[] args) {
        try {
            ProductoDAO dao = new ProductoDAO();
            ProductoDTO dto = new ProductoDTO();
            dto.getEntidad().setNombreProducto("PC");
            dto.getEntidad().setDescripcionProducto("PC para juegos");
            dto.getEntidad().setExistencia(100);
            dto.getEntidad().setPrecio(1000);
            dto.getEntidad().setStockMinimo(10);
            dto.getEntidad().setClaveCategoria(2);
            dao.create(dto);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
