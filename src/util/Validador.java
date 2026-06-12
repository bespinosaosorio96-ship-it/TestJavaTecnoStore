/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author PC_WIN11
 */
public class Validador {
    public static boolean correoValido(String correo) {
        return correo != null && correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
}
