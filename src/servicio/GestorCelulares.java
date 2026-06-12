/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import modelo.Celular;
import java.util.ArrayList;

/**
 *
 * @author PC_WIN11
 */
public class GestorCelulares {
    private ArrayList<Celular> celulares = new ArrayList<>();
    
    public void cargarCelulares(ArrayList<Celular> celularesBD) {
        this.celulares = celularesBD;
    }

    public ArrayList<Celular> getCelulares() {
        return celulares;
    }        
    
    public void registrarCelular(Celular celular) {
        if (buscarPorId(celular.getId()) != null) {
            throw new IllegalArgumentException("El ID del celular ya existe");
        }
        celulares.add(celular);
    }
    
    public Celular buscarPorId(int id) {
        for (Celular celular : celulares) {
            if (celular.getId() == id) {
                return celular;
            }
        }
        return null;
    }
    
    public void listarCelulares() {
        for (Celular celular : celulares) {
            System.out.println(celular);
        }
    }
    
    public void mostrarStockBajo() {
        for (Celular celular : celulares) {
            if (celular.getStock() < 5) {
                System.out.println(celular);
            }
        }
    }
    
    public void eliminarCelular(int id) {
        Celular celular = buscarPorId(id);
        if (celular == null) {
            throw new IllegalArgumentException("No existe un celular con ese ID");
        }
        celulares.remove(celular);
    }
    
    public void actualizarPrecioYStock(int id, double nuevoPrecio, int nuevoStock) {
        Celular celular = buscarPorId(id);
        
        if (celular == null) {
            throw new IllegalArgumentException("No existe un celular con ese ID");
        }
        
        celular.setPrecio(nuevoPrecio);
        celular.setStock(nuevoStock);
    }
    
}



