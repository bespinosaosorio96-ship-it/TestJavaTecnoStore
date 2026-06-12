/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author PC_WIN11
 */
public class ClienteDAO {
    public void insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (id, nombre, identificacion, correo, telefono) VALUES (?, ?, ?, ?, ?)";
        
        try (   Connection conn = ConexionDB.obtenerConexion(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getId());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getIdentificacion());
            stmt.setString(4, cliente.getCorreo());
            stmt.setString(5, cliente.getTelefono());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
        }
    }
    
    public ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT id, nombre, identificacion, correo, telefono FROM clientes";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                );

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }

        return clientes;
    }
}
