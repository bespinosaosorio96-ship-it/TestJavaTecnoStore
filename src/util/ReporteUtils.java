/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import modelo.Celular;
import java.util.ArrayList;
import modelo.Venta;
import modelo.ItemVenta;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.Month;

/**
 *
 * @author PC_WIN11
 */
public class ReporteUtils {
    public static void mostrarStockBajo(ArrayList<Celular> celulares) {
        celulares.stream()
                .filter(celular -> celular.getStock() < 5)
                .forEach(System.out::println);
    }
    
    public static void mostrarTop3CelularesMasVendidos(ArrayList<Venta> ventas) {
        Map<String, Integer> vendidos = ventas.stream()
                .flatMap(venta -> venta.getItems().stream())
                .collect(Collectors.groupingBy(
                        item -> item.getCelular().getMarca() + " " + item.getCelular().getModelo(),
                        Collectors.summingInt(ItemVenta::getCantidad)
                ));
        vendidos.entrySet().stream()
        .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
        .limit(3)
        .forEach(entry -> System.out.println(entry.getKey() + " - Vendidos: " + entry.getValue()));
    }
    
    public static void mostrarVentasTotalesPorMes(ArrayList<Venta> ventas) {
        Map<Month, Double> ventasPorMes = ventas.stream()
                .collect(Collectors.groupingBy(
                        venta -> venta.getFecha().getMonth(),
                        Collectors.summingDouble(Venta::getTotalConIva)
                ));

        ventasPorMes.forEach((mes, total) ->
                System.out.println(mes + " -> $" + total));
    }
}
