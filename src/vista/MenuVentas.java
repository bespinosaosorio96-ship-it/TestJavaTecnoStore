/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import util.ScannerManager;
import java.util.Scanner;
import controlador.VentaController;
import util.InputUtil;

/**
 *
 * @author PC_WIN11
 */
public class MenuVentas {

    private final Scanner scanner;
    private final VentaController ventaController;

    public MenuVentas(VentaController ventaController) {
        this.scanner = ScannerManager.getInstancia().getScanner();
        this.ventaController = ventaController;
    }

    public void mostrar() {
        int opcion;

        do {
            System.out.println("=== MENÚ VENTAS ===");
            System.out.println("1. Registrar venta");
            System.out.println("2. Listar ventas");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = InputUtil.leerEntero(scanner, "Selecciona una opcion");

            switch (opcion) {
                case 1:
                    try {
                    registrarVenta();
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
                case 2:
                    ventaController.listarVentas();
                    break;
            }

        } while (opcion != 0);
    }

    private void registrarVenta() {

        System.out.print("ID venta: ");
        int idVenta = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Identificación del cliente: ");
        String identificacion = scanner.nextLine();

        System.out.print("ID celular: ");
        int idCelular = scanner.nextInt();

        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\n=== TIPO DE VENTA ===");
        System.out.println("1. Contado");
        System.out.println("2. Crédito");

        int tipoVenta = InputUtil.leerEntero(
                scanner,
                "Seleccione una opción: ");

        ventaController.registrarVenta(
                idVenta,
                identificacion,
                idCelular,
                cantidad,
                tipoVenta
        );

        System.out.println("Venta registrada correctamente.");
    }
}
