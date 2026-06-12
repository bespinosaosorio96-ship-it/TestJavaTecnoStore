/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dao.ClienteDAO;
import dao.CelularDAO;
import dao.VentaDAO;

import servicio.GestorClientes;
import servicio.GestorCelulares;
import servicio.GestorVentas;

/**
 *
 * @author PC_WIN11
 */
public class InicializadorDatos {
    public static void cargarDatos(
            GestorClientes gestorClientes,
            GestorCelulares gestorCelulares,
            GestorVentas gestorVentas) {
        
        ClienteDAO clienteDAO = new ClienteDAO();
        CelularDAO celularDAO = new CelularDAO();
        VentaDAO ventaDAO = new VentaDAO();
        
        gestorClientes.cargarClientes(clienteDAO.listarClientes());
        gestorCelulares.cargarCelulares(celularDAO.listarCelulares());
        gestorVentas.cargarVentas(ventaDAO.listarVentasCompletas());

    }
}
