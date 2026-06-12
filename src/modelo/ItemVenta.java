/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author PC_WIN11
 */
public class ItemVenta {
    private Celular celular;
    private int cantidad;
    private double subtotal;

    public ItemVenta(Celular celular, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad vendida debe ser positiva");
        }
        this.celular = celular;
        this.cantidad = cantidad;
        this.subtotal = celular.getPrecio() * cantidad;
    }

    public Celular getCelular() {
        return celular;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }
    
    @Override
    public String toString() {
        return celular.getMarca() + " " +
               celular.getModelo() +
               " | Cantidad: " + cantidad +
               " | Subtotal: $" + subtotal;
    }
    
    
}
