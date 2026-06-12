/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Scanner;

/**
 *
 * @author PC_WIN11
 */
public class ScannerManager {
    private static ScannerManager instancia;
    private final Scanner scanner;

    private ScannerManager() {
        scanner = new Scanner(System.in);
    }

    public static synchronized ScannerManager getInstancia() {
        if (instancia == null) {
            instancia = new ScannerManager();
        }
        return instancia;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void cerrarScanner() {
        scanner.close();
    }
}
