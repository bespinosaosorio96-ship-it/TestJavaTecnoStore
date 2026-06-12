/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.ItemVenta;
import modelo.Cliente;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Celular;
import modelo.CategoriaGama;
import modelo.ItemVenta;
import java.util.LinkedHashMap;
import java.util.Map;
import modelo.VentaCredito_test;

/**
 *
 * @author PC_WIN11
 */
public class VentaDAO {

    public void insertarVenta(Venta venta) {
        String sqlVenta
                = "INSERT INTO ventas (id, id_cliente, fecha, total, tipo_venta) VALUES (?, ?, ?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalle_ventas (id_venta, id_celular, cantidad, subtotal) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmtVenta = conn.prepareStatement(sqlVenta)) {
            stmtVenta.setInt(1, venta.getId());
            stmtVenta.setInt(2, venta.getCliente().getId());
            stmtVenta.setDate(3, java.sql.Date.valueOf(venta.getFecha()));
            stmtVenta.setDouble(4, venta.getTotalConIva());
            if (venta instanceof VentaCredito_test) {
                stmtVenta.setString(5, "CREDITO");
            } else {
                stmtVenta.setString(5, "CONTADO");
            }
            stmtVenta.executeUpdate();
            try (PreparedStatement stmtDetalle = conn.prepareStatement(sqlDetalle)) {
                for (ItemVenta item : venta.getItems()) {
                    stmtDetalle.setInt(1, venta.getId());
                    stmtDetalle.setInt(2, item.getCelular().getId());
                    stmtDetalle.setInt(3, item.getCantidad());
                    stmtDetalle.setDouble(4, item.getSubtotal());
                    stmtDetalle.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar venta: " + e.getMessage());
        }
    }

    public ArrayList<Venta> listarVentasBasicas() {
        ArrayList<Venta> ventas = new ArrayList<>();

        String sql = "SELECT v.id, v.id_cliente, c.nombre, c.identificacion, c.correo, c.telefono "
                + "FROM ventas v "
                + "INNER JOIN clientes c ON v.id_cliente = c.id";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                );

                Venta venta = new Venta(
                        rs.getInt("id"),
                        cliente
                );

                ventas.add(venta);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar ventas: " + e.getMessage());
        }

        return ventas;
    }

    public ArrayList<Venta> listarVentasCompletas() {
        Map<Integer, Venta> mapaVentas = new LinkedHashMap<>();

        String sql = "SELECT v.id AS id_venta, "
                + "c.id AS id_cliente, c.nombre, c.identificacion, c.correo, c.telefono, "
                + "cel.id AS id_celular, cel.marca, cel.modelo, cel.precio, cel.stock, "
                + "cel.sistema_operativo, cel.gama, "
                + "dv.cantidad "
                + "FROM ventas v "
                + "INNER JOIN clientes c ON v.id_cliente = c.id "
                + "INNER JOIN detalle_ventas dv ON v.id = dv.id_venta "
                + "INNER JOIN celulares cel ON dv.id_celular = cel.id";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idVenta = rs.getInt("id_venta");
                Venta venta = mapaVentas.get(idVenta);
                if (venta == null) {
                    Cliente cliente = new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nombre"),
                            rs.getString("identificacion"),
                            rs.getString("correo"),
                            rs.getString("telefono")
                    );

                    venta = new Venta(idVenta, cliente);
                    mapaVentas.put(idVenta, venta);
                }
                Celular celular = new Celular(
                        rs.getInt("id_celular"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getString("sistema_operativo"),
                        CategoriaGama.valueOf(rs.getString("gama"))
                );
                ItemVenta item = new ItemVenta(
                        celular,
                        rs.getInt("cantidad")
                );
                venta.agregarItem(item);

            }

        } catch (SQLException e) {
            System.out.println("Error al listar ventas completas: " + e.getMessage());
        }

        return new ArrayList<>(mapaVentas.values());
    }
}
