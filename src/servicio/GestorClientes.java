/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import modelo.Cliente;
import java.util.ArrayList;

/**
 *
 * @author PC_WIN11
 */
public class GestorClientes {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    
    public void cargarClientes(ArrayList<Cliente> clientesBD) {
        this.clientes = clientesBD;
    }
    
    public void registrarCliente(Cliente cliente) {
        if (buscarPorIdentificacion(cliente.getIdentificacion()) != null) {
            throw new IllegalArgumentException("La identificación ya existe");
        }
        clientes.add(cliente);
    }
    
    public Cliente buscarPorIdentificacion(String identificacion) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificacion().equals(identificacion)) {
                return cliente;
            }
        }
        return null;
    }
    
    public void listarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }
    
    
}
