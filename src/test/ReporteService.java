package test;

import java.util.Map;
import java.util.stream.Collectors;

import modelo.ItemVenta;
import modelo.Venta;
import modelo.VentaCredito_test;

import servicio.GestorCreditos_test;
import servicio.GestorVentas;

public class ReporteService {

    private final GestorVentas gestorVentas;
    private final GestorCreditos_test gestorCreditos;

    public ReporteService(
            GestorVentas gestorVentas,
            GestorCreditos_test gestorCreditos) {

        this.gestorVentas = gestorVentas;
        this.gestorCreditos = gestorCreditos;
    }

    public double obtenerTotalVentas() {

        return gestorVentas.getVentas()
                .stream()
                .mapToDouble(Venta::getTotalConIva)
                .sum();
    }

    public void mostrarReporteGeneral() {

        System.out.println(
                "\n===== REPORTE GENERAL =====");

        System.out.println(
                "Número de ventas: "
                + gestorVentas.getVentas().size());

        System.out.println(
                "Total vendido: $"
                + obtenerTotalVentas());
    }

    public void mostrarCelularesVendidosPorModelo() {

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

        System.out.println(
                "\n===== CELULARES VENDIDOS POR MODELO =====");

        celularesVendidos.forEach(
                (modelo, cantidad) ->
                        System.out.println(
                                modelo
                                + " -> "
                                + cantidad
                                + " unidades"
                        )
        );
    }

    public void mostrarClientesConCreditoPendiente() {

        System.out.println(
                "\n===== CLIENTES CON CRÉDITO PENDIENTE =====");

        for (VentaCredito_test credito
                : gestorCreditos.obtenerCreditosPendientes()) {

            System.out.println(
                    credito.getCliente().getNombre()
                    + " -> Saldo pendiente: $"
                    + credito.getSaldoPendiente());
        }
    }

    public void mostrarResumenCreditos() {

        System.out.println(
                "\n===== RESUMEN DE CRÉDITOS =====");

        System.out.println(
                "Créditos pendientes: "
                + gestorCreditos.cantidadCreditosPendientes());

        System.out.println(
                "Saldo total pendiente: $"
                + gestorCreditos.obtenerSaldoTotalPendiente());
    }
}