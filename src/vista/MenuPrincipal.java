package vista;

import util.ScannerManager;
import java.util.Scanner;

import servicio.GestorCelulares;
import servicio.GestorClientes;
import servicio.GestorVentas;
import servicio.GestorCreditos_test;

import controlador.CelularController;
import controlador.ClienteController;
import controlador.VentaController;
import controlador.ReporteController;

import util.InicializadorDatos;
import util.InputUtil;

public class MenuPrincipal {

    private final Scanner scanner;

    private final MenuCelulares menuCelulares;
    private final MenuClientes menuClientes;
    private final MenuVentas menuVentas;
    private final MenuReportes menuReportes;

    private final GestorCelulares gestorCelulares;
    private final GestorClientes gestorClientes;
    private final GestorVentas gestorVentas;
    private final GestorCreditos_test gestorCreditos;

    private final CelularController celularController;
    private final ClienteController clienteController;
    private final VentaController ventaController;
    private final ReporteController reporteController;

    public MenuPrincipal() {

        this.scanner = ScannerManager
                .getInstancia()
                .getScanner();

        this.gestorCelulares = new GestorCelulares();
        this.gestorClientes = new GestorClientes();
        this.gestorVentas = new GestorVentas();
        this.gestorCreditos = new GestorCreditos_test();

        InicializadorDatos.cargarDatos(
                gestorClientes,
                gestorCelulares,
                gestorVentas
        );

        this.celularController
                = new CelularController(
                        gestorCelulares);

        this.clienteController
                = new ClienteController(
                        gestorClientes);

        this.ventaController
                = new VentaController(
                        gestorVentas,
                        gestorClientes,
                        gestorCelulares,
                        gestorCreditos
                );

        this.reporteController
                = new ReporteController(
                        gestorCelulares,
                        gestorVentas,
                        gestorCreditos
                );

        this.menuCelulares
                = new MenuCelulares(
                        celularController);

        this.menuClientes
                = new MenuClientes(
                        clienteController);

        this.menuVentas
                = new MenuVentas(
                        ventaController);

        this.menuReportes
                = new MenuReportes(
                        reporteController);
    }

    public void mostrar() {

        int opcion;

        do {

            System.out.println("\n=== MENÚ PRINCIPAL TECNOSTORE ===");
            System.out.println("1. Gestión de celulares");
            System.out.println("2. Gestión de clientes");
            System.out.println("3. Gestión de ventas");
            System.out.println("4. Reportes");
            System.out.println("0. Salir");

            opcion = InputUtil.leerEntero(
                    scanner,
                    "Seleccione una opción: ");

            switch (opcion) {

                case 1:
                    menuCelulares.mostrar();
                    break;

                case 2:
                    menuClientes.mostrar();
                    break;

                case 3:
                    menuVentas.mostrar();
                    break;

                case 4:
                    menuReportes.mostrar();
                    break;

                case 0:
                    System.out.println(
                            "Saliendo del sistema...");
                    break;

                default:
                    System.out.println(
                            "Opción no válida");
            }

        } while (opcion != 0);
    }
}
