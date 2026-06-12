/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import util.ScannerManager;
import java.util.Scanner;
import controlador.ClienteController;
import modelo.Cliente;
import util.Validador;
import util.InputUtil;


/**
 *
 * @author PC_WIN11
 */
public class MenuClientes {
    private final Scanner scanner;
    private final ClienteController clienteController;
    
    public MenuClientes(ClienteController clienteController) {
        this.scanner = ScannerManager.getInstancia().getScanner();
        this.clienteController = clienteController;
    }
    
    public void mostrar() {
        int opcion;

        do {
            System.out.println("=== MENÚ CLIENTES ===");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = InputUtil.leerEntero(scanner, "Selecciona una opcion");

            
            switch (opcion) {
                case 1:
                    try {
                        registrarCliente();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    clienteController.listarClientes();
                    break;
            }

        } while (opcion != 0);
    }
    
    private void registrarCliente() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Identificación: ");
        String identificacion = scanner.nextLine();

        System.out.print("Correo: ");
        String correo = scanner.nextLine();

        if (!Validador.correoValido(correo)) {
            throw new IllegalArgumentException("Correo inválido");
        }

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        Cliente cliente = new Cliente(id, nombre, identificacion, correo, telefono);
        clienteController.registrarCliente(cliente);

        System.out.println("Cliente registrado correctamente.");
    }
}
