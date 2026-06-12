package modelo;

import java.time.LocalDate;

public class Abono_test {

    private int id;
    private double valor;
    private LocalDate fecha;

    public Abono_test(int id, double valor) {
        this.id = id;
        this.valor = valor;
        this.fecha = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}