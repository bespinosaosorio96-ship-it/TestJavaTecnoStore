/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import modelo.Venta;
import java.util.ArrayList;

/**
 *
 * @author PC_WIN11
 */
public class GestorVentas {
    private ArrayList<Venta> ventas = new ArrayList<>();
    
    public void cargarVentas(ArrayList<Venta> ventasBD) {
        this.ventas = ventasBD;
    }

    public void registrarVenta(Venta venta) {
        for (modelo.ItemVenta item : venta.getItems()) {
            modelo.Celular celular = item.getCelular();
            if (item.getCantidad() > celular.getStock()) {
                throw new IllegalArgumentException("Stock insuficiente para " + celular.getModelo());
            }
            celular.setStock(celular.getStock() - item.getCantidad());
        }
        ventas.add(venta);
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }
    
    public void listarVentas() {
        for (Venta venta : ventas) {
            System.out.println(venta);
        }
    }
}
