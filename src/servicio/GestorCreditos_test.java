package servicio;

import java.util.ArrayList;
import modelo.Abono_test;
import modelo.VentaCredito_test;

public class GestorCreditos_test {

    private ArrayList<VentaCredito_test> creditos;

    public GestorCreditos_test() {
        this.creditos = new ArrayList<>();
    }

    public void registrarCredito(VentaCredito_test credito) {
        creditos.add(credito);
    }

    public ArrayList<VentaCredito_test> getCreditos() {
        return creditos;
    }

    public VentaCredito_test buscarPorId(int id) {

        for (VentaCredito_test credito : creditos) {
            if (credito.getId() == id) {
                return credito;
            }
        }

        return null;
    }

    public void listarCreditos() {

        for (VentaCredito_test credito : creditos) {
            System.out.println(credito);
        }
    }

    public ArrayList<VentaCredito_test> obtenerCreditosPendientes() {

        ArrayList<VentaCredito_test> pendientes = new ArrayList<>();

        for (VentaCredito_test credito : creditos) {

            if (credito.tieneSaldoPendiente()) {
                pendientes.add(credito);
            }

        }

        return pendientes;
    }
    

    public void registrarAbono(int idCredito, Abono_test abono) {

        VentaCredito_test credito = buscarPorId(idCredito);

        if (credito == null) {
            throw new IllegalArgumentException(
                    "No existe un crédito con ese ID");
        }

        credito.registrarAbono(abono);
    }

    public double obtenerSaldoTotalPendiente() {

        double total = 0;

        for (VentaCredito_test credito : creditos) {
            total += credito.getSaldoPendiente();
        }

        return total;
    }
    
    public int cantidadCreditosPendientes() {

    int contador = 0;

    for (VentaCredito_test credito : creditos) {

        if (credito.tieneSaldoPendiente()) {
            contador++;
        }

    }

    return contador;
}
}
