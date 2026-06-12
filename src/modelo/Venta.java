/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author PC_WIN11
 */
public class Venta {
    private int id;
    private Cliente cliente;
    private LocalDate fecha;
    private ArrayList<ItemVenta> items;
    private double total;

    public Venta(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = LocalDate.now();
        this.items = new ArrayList<>();
        this.total = 0;
    }
    
    public void agregarItem(ItemVenta item) {
        items.add(item);
        total += item.getSubtotal();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getTotal() {
        return total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public ArrayList<ItemVenta> getItems() {
        return items;
    }
    
    public double getTotalConIva() {
        return total * 1.19;
    }
    
    @Override
    public String toString() {
        return "Venta #" + id +
                " | Cliente: " + cliente.getNombre() +
                " | Fecha: " + fecha +
                " | Total con IVA: $" + getTotalConIva();
    }
    
}
