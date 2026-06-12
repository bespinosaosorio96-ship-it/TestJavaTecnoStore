/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Cliente;
import servicio.GestorClientes;
import dao.ClienteDAO;

/**
 *
 * @author camper
 */
public class ClienteController {
    private final GestorClientes gestorClientes;
    private final ClienteDAO clienteDAO;
    
    public ClienteController(GestorClientes gestorClientes) {
        this.gestorClientes = gestorClientes;
        this.clienteDAO = new ClienteDAO();
    }
    
    public void registrarCliente(Cliente cliente) {
        gestorClientes.registrarCliente(cliente);
        clienteDAO.insertarCliente(cliente);
    }
    
    public void listarClientes() {
        gestorClientes.listarClientes();
    }
}
