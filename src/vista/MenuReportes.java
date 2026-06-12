package vista;

import util.ScannerManager;
import java.util.Scanner;
import controlador.ReporteController;
import util.InputUtil;


public class MenuReportes {

    private final Scanner scanner;
    private final ReporteController reporteController;

    public MenuReportes(ReporteController reporteController) {
        this.scanner = ScannerManager.getInstancia().getScanner();
        this.reporteController = reporteController;
    }

    public void mostrar() {

        int opcion;

        do {

            System.out.println("\n=== MENÚ REPORTES ===");
            System.out.println("1. Celulares con stock bajo");
            System.out.println("2. Top 3 celulares más vendidos");
            System.out.println("3. Ventas totales por mes");
            System.out.println("4. Generar reporte_ventas.txt");
            System.out.println("5. Generar reporte_global.txt");
            System.out.println("0. Volver");

            opcion = InputUtil.leerEntero(
                    scanner,
                    "Seleccione una opción: ");

            switch (opcion) {

                case 1:
                    reporteController.mostrarStockBajo();
                    break;

                case 2:
                    reporteController.mostrarTop3CelularesMasVendidos();
                    break;

                case 3:
                    reporteController.mostrarVentasTotalesPorMes();
                    break;

                case 4:
                    reporteController.generarReporteVentasArchivo();
                    System.out.println(
                            "Archivo reporte_ventas.txt generado correctamente.");
                    break;

                case 5:
                    reporteController.generarReporteGlobal();
                    System.out.println(
                            "Archivo reporte_global.txt generado correctamente.");
                    break;

                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }
}