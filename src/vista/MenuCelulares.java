/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import util.ScannerManager;
import java.util.Scanner;
import controlador.CelularController;
import modelo.Celular;
import modelo.CategoriaGama;
import util.InputUtil;

/**
 *
 * @author PC_WIN11
 */
public class MenuCelulares {
    private final Scanner scanner;
    private final CelularController celularController;
    
    public MenuCelulares(CelularController celularController) {
        this.scanner = ScannerManager.getInstancia().getScanner();
        this.celularController = celularController;
    }
    
    public void mostrar() {
        int opcion;

        do {
            System.out.println("=== MENÚ CELULARES ===");
            System.out.println("1. Registrar celular");
            System.out.println("2. Listar celulares");
            System.out.println("3. Actualizar precio y stock");
            System.out.println("4. Eliminar celular");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = InputUtil.leerEntero(scanner, "Selecciona una opcion");
            
            switch (opcion) {
                case 1:
                    try {
                        registrarCelular();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    celularController.listarCelulares();
                    break;
                case 3:
                    try {
                        actualizarCelular();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        eliminarCelular();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 0);
    }
    
    private void registrarCelular() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();

        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Sistema operativo: ");
        String sistemaOperativo = scanner.nextLine();
        
        System.out.print("Gama (ALTA, MEDIA, BAJA): ");
        String gamaTexto = scanner.nextLine();

        CategoriaGama gama = CategoriaGama.valueOf(gamaTexto.toUpperCase());
        
        Celular celular = new Celular(id, marca, modelo, precio, stock, sistemaOperativo, gama);
        celularController.registrarCelular(celular);
        System.out.println("Celular registrado correctamente.");
    }
    
    private void actualizarCelular() {
        System.out.print("ID del celular a actualizar: ");
        int id = scanner.nextInt();

        System.out.print("Nuevo precio: ");
        double precio = scanner.nextDouble();

        System.out.print("Nuevo stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        celularController.actualizarPrecioYStock(id, precio, stock);
        System.out.println("Celular actualizado correctamente.");
    }
    
    private void eliminarCelular() {
        System.out.print("ID del celular a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        celularController.eliminarCelular(id);
        System.out.println("Celular eliminado correctamente.");
    }
}
