/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.Celular;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.CategoriaGama;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author PC_WIN11
 */
public class CelularDAO {
    public void insertarCelular(Celular celular) {
        String sql = "INSERT INTO celulares (id, marca, modelo, sistema_operativo, gama, precio, stock) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (   Connection conn = ConexionDB.obtenerConexion(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, celular.getId());
            stmt.setString(2, celular.getMarca());
            stmt.setString(3, celular.getModelo());
            stmt.setString(4, celular.getSistemaOperativo());
            stmt.setString(5, celular.getGama().name());
            stmt.setDouble(6, celular.getPrecio());
            stmt.setInt(7, celular.getStock());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar celular: " + e.getMessage());
        }
    }
    
    public ArrayList<Celular> listarCelulares() {
        ArrayList<Celular> celulares = new ArrayList<>();
        String sql = "SELECT id, marca, modelo, sistema_operativo, gama, precio, stock FROM celulares";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Celular celular = new Celular(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getString("sistema_operativo"),
                        CategoriaGama.valueOf(rs.getString("gama"))
                );

                celulares.add(celular);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar celulares: " + e.getMessage());
        }

        return celulares;
    }
    
    public void actualizarStock(int idCelular, int nuevoStock) {
        String sql = "UPDATE celulares SET stock = ? WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, nuevoStock);
            stmt.setInt(2, idCelular);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar stock: " + e.getMessage());
        }
    }
    
    public void actualizarPrecioYStock(int idCelular, double precio, int stock) {
        String sql = "UPDATE celulares SET precio = ?, stock = ? WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, precio);
            stmt.setInt(2, stock);
            stmt.setInt(3, idCelular);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar celular: " + e.getMessage());
        }
    }
    
    public void eliminarCelular(int idCelular) {
        String sql = "DELETE FROM celulares WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCelular);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar celular: " + e.getMessage());
        }
    }
    
    public boolean tieneVentasAsociadas(int idCelular) {
        String sql = "SELECT COUNT(*) FROM detalle_ventas WHERE id_celular = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCelular);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar ventas asociadas: " + e.getMessage());
        }

        return false;
    }
}
