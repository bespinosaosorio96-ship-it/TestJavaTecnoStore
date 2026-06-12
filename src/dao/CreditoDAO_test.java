package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Cliente;
import modelo.VentaCredito_test;

public class CreditoDAO_test {

    public void insertarCredito(VentaCredito_test credito) {

        String sql =
                "INSERT INTO creditos (id_venta, saldo_pendiente) VALUES (?, ?)";

        try (
                Connection conn = ConexionDB.obtenerConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, credito.getId());
            stmt.setDouble(2, credito.getSaldoPendiente());

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(
                    "Error al insertar crédito: "
                    + e.getMessage());
        }
    }

    public ArrayList<VentaCredito_test> listarCreditos() {

        ArrayList<VentaCredito_test> creditos = new ArrayList<>();

        String sql =
                "SELECT c.id_venta, c.saldo_pendiente, "
                + "cl.id, cl.nombre, cl.identificacion, "
                + "cl.correo, cl.telefono "
                + "FROM creditos c "
                + "INNER JOIN ventas v ON c.id_venta = v.id "
                + "INNER JOIN clientes cl ON v.id_cliente = cl.id";

        try (
                Connection conn = ConexionDB.obtenerConexion();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                );

                VentaCredito_test credito =
                        new VentaCredito_test(
                                rs.getInt("id_venta"),
                                cliente
                        );

                credito.setSaldoPendiente(
                        rs.getDouble("saldo_pendiente")
                );

                creditos.add(credito);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar créditos: "
                    + e.getMessage());
        }

        return creditos;
    }

    public void actualizarSaldo(
            int idVenta,
            double nuevoSaldo) {

        String sql =
                "UPDATE creditos "
                + "SET saldo_pendiente = ? "
                + "WHERE id_venta = ?";

        try (
                Connection conn = ConexionDB.obtenerConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setDouble(1, nuevoSaldo);
            stmt.setInt(2, idVenta);

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(
                    "Error al actualizar saldo: "
                    + e.getMessage());
        }
    }
}