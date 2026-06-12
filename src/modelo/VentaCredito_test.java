package modelo;

import java.util.ArrayList;

public class VentaCredito_test extends Venta {

    private double saldoPendiente;
    private ArrayList<Abono_test> abonos;

    public VentaCredito_test(int id, Cliente cliente) {
        super(id, cliente);
        this.abonos = new ArrayList<>();
    }

    public void inicializarCredito() {
        this.saldoPendiente = getTotalConIva();
    }

    public void registrarAbono(Abono_test abono) {
        abonos.add(abono);
        saldoPendiente -= abono.getValor();

        if (saldoPendiente < 0) {
            saldoPendiente = 0;
        }
    }

    public boolean tieneSaldoPendiente() {
        return saldoPendiente > 0;
    }

    public double getSaldoPendiente() {
        return saldoPendiente;
    }

    public ArrayList<Abono_test> getAbonos() {
        return abonos;
    }

    public void setSaldoPendiente(double saldoPendiente) {
    this.saldoPendiente = saldoPendiente;
}
}
