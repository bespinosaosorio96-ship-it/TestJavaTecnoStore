package test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.stream.Collectors;

import modelo.Celular;
import modelo.ItemVenta;
import modelo.Venta;
import modelo.VentaCredito_test;

import servicio.GestorCelulares;
import servicio.GestorCreditos_test;
import servicio.GestorVentas;

public class ReporteGlobalTXT {

    private final GestorVentas gestorVentas;
    private final GestorCelulares gestorCelulares;
    private final GestorCreditos_test gestorCreditos;

    public ReporteGlobalTXT(
            GestorVentas gestorVentas,
            GestorCelulares gestorCelulares,
            GestorCreditos_test gestorCreditos) {

        this.gestorVentas = gestorVentas;
        this.gestorCelulares = gestorCelulares;
        this.gestorCreditos = gestorCreditos;
    }

    public void generar() {

        try (PrintWriter writer =
                new PrintWriter(new FileWriter("reporte_global.txt"))) {

            writer.println("=================================");
            writer.println("REPORTE GLOBAL - TECNOSTORE");
            writer.println("=================================");
            writer.println();

            // REPORTE GENERAL

            writer.println("===== REPORTE GENERAL =====");
            writer.println();

            writer.println("Numero de ventas: "
                    + gestorVentas.getVentas().size());

            double totalVentas =
                    gestorVentas.getVentas()
                            .stream()
                            .mapToDouble(Venta::getTotalConIva)
                            .sum();

            writer.println("Total vendido: $" + totalVentas);
            writer.println();

            // CELULARES VENDIDOS

            writer.println(
                    "===== CELULARES VENDIDOS POR MODELO =====");

            writer.println();

            Map<String, Integer> celularesVendidos =
                    gestorVentas.getVentas()
                            .stream()
                            .flatMap(
                                    venta -> venta.getItems().stream()
                            )
                            .collect(
                                    Collectors.groupingBy(
                                            item ->
                                                    item.getCelular().getMarca()
                                                    + " "
                                                    + item.getCelular().getModelo(),
                                            Collectors.summingInt(
                                                    ItemVenta::getCantidad
                                            )
                                    )
                            );

            celularesVendidos.forEach(
                    (modelo, cantidad) ->
                            writer.println(
                                    modelo
                                    + " -> "
                                    + cantidad
                                    + " unidades")
            );

            writer.println();

            // CREDITOS PENDIENTES

            writer.println(
                    "===== CLIENTES CON CREDITO PENDIENTE =====");

            writer.println();

            for (VentaCredito_test credito
                    : gestorCreditos.obtenerCreditosPendientes()) {

                writer.println(
                        credito.getCliente().getNombre()
                        + " -> Saldo pendiente: $"
                        + credito.getSaldoPendiente());
            }

            writer.println();

            // RESUMEN CREDITOS

            writer.println(
                    "===== RESUMEN DE CREDITOS =====");

            writer.println();

            writer.println(
                    "Creditos pendientes: "
                    + gestorCreditos.cantidadCreditosPendientes());

            writer.println(
                    "Saldo total pendiente: $"
                    + gestorCreditos.obtenerSaldoTotalPendiente());

            writer.println();

            // INVENTARIO

            writer.println(
                    "===== INVENTARIO ACTUAL =====");

            writer.println();

            for (Celular celular
                    : gestorCelulares.getCelulares()) {

                writer.println(
                        celular.getMarca()
                        + " "
                        + celular.getModelo());

                writer.println(
                        "Stock: "
                        + celular.getStock());

                if (celular.getStock() < 3) {

                    writer.println(
                            "*** ALERTA STOCK BAJO ***");
                }

                writer.println();
            }

            writer.println("=================================");
            writer.println("FIN DEL REPORTE");
            writer.println("=================================");

            System.out.println(
                    "Reporte generado correctamente.");

        } catch (IOException e) {

            System.out.println(
                    "Error al generar reporte: "
                    + e.getMessage());
        }
    }
}