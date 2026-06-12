/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patron;

import modelo.Celular;
import modelo.CategoriaGama;

/**
 *
 * @author PC_WIN11
 */
public class FactoryCelular {
    public static Celular crearCelular(int id, String marca, String modelo,
                                   double precio, int stock,
                                   String sistemaOperativo,
                                   CategoriaGama gama) {
        return new Celular(id, marca, modelo, precio, stock, sistemaOperativo, gama);
    }
}
