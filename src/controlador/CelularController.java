/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import servicio.GestorCelulares;
import modelo.Celular;
import java.util.ArrayList;
import dao.CelularDAO;

/**
 *
 * @author camper
 */
public class CelularController {
    private final GestorCelulares gestorCelulares;
    private final CelularDAO celularDAO;
    
    
    public CelularController(GestorCelulares gestorCelulares) {
        this.gestorCelulares = gestorCelulares;
        this.celularDAO = new CelularDAO();
    }
    
    public void registrarCelular(Celular celular) {
        gestorCelulares.registrarCelular(celular);
        celularDAO.insertarCelular(celular);
    }
    
    public void listarCelulares() {
        gestorCelulares.listarCelulares();
    }
    
    public void actualizarPrecioYStock(int id, double precio, int stock) {
        gestorCelulares.actualizarPrecioYStock(id, precio, stock);
        celularDAO.actualizarPrecioYStock(id, precio, stock);
    }
    
    public void eliminarCelular(int id) {
        if (celularDAO.tieneVentasAsociadas(id)) {
            throw new IllegalArgumentException("No se puede eliminar un celular con ventas asociadas");
        }
        gestorCelulares.eliminarCelular(id);
        celularDAO.eliminarCelular(id);
    }
    
    public ArrayList<Celular> getCelulares() {
        return gestorCelulares.getCelulares();
    }
}
