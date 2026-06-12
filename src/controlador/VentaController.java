package controlador;

import dao.CelularDAO;
import dao.CreditoDAO_test;
import dao.VentaDAO;

import modelo.Celular;
import modelo.Cliente;
import modelo.ItemVenta;
import modelo.Venta;
import modelo.VentaContado;
import modelo.VentaContado_test;
import modelo.VentaCredito_test;

import servicio.GestorCelulares;
import servicio.GestorClientes;
import servicio.GestorCreditos_test;
import servicio.GestorVentas;

public class VentaController {

    private final GestorVentas gestorVentas;
    private final GestorClientes gestorClientes;
    private final GestorCelulares gestorCelulares;
    private final GestorCreditos_test gestorCreditos;

    private final VentaDAO ventaDAO;
    private final CelularDAO celularDAO;
    private final CreditoDAO_test creditoDAO;

    public VentaController(
            GestorVentas gestorVentas, GestorClientes gestorClientes, GestorCelulares gestorCelulares) {

        this.gestorVentas = gestorVentas;
        this.gestorClientes = gestorClientes;
        this.gestorCelulares = gestorCelulares;
        this.gestorCreditos = gestorCreditos;

        this.ventaDAO = new VentaDAO();
        this.celularDAO = new CelularDAO();
        this.creditoDAO = new CreditoDAO_test();
    }

    public void listarClientes() {
        gestorClientes.listarClientes();
    }

    public void listarCelulares() {
        gestorCelulares.listarCelulares();
    }

    public void listarVentas() {
        gestorVentas.listarVentas();
    }

    public void registrarVenta(
            int idVenta,
            String identificacionCliente,
            int idCelular,
            int cantidad,
            int tipoVenta) {

        Cliente cliente
                = gestorClientes.buscarPorIdentificacion(
                        identificacionCliente);

        Celular celular
                = gestorCelulares.buscarPorId(idCelular);

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "Cliente no encontrado");
        }

        if (celular == null) {
            throw new IllegalArgumentException(
                    "Celular no encontrado");
        }

        Venta venta;

        if (tipoVenta == 1) {

            venta = new VentaContado_test(
                    idVenta,
                    cliente);

        } else {

            venta = new VentaCredito_test(
                    idVenta,
                    cliente);
        }

        ItemVenta item
                = new ItemVenta(
                        celular,
                        cantidad);

        venta.agregarItem(item);

        gestorVentas.registrarVenta(venta);

        if (venta instanceof VentaCredito_test credito) {

            credito.inicializarCredito();

            gestorCreditos.registrarCredito(credito);

            creditoDAO.insertarCredito(credito);
        }

        ventaDAO.insertarVenta(venta);

        venta.getItems().forEach(itemVenta
                -> celularDAO.actualizarStock(
                        itemVenta.getCelular().getId(),
                        itemVenta.getCelular().getStock()
                )
        );
    }
}
