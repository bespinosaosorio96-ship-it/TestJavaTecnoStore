package main;

import util.ScannerManager;
import vista.MenuPrincipal;

public class Main {

    public static void main(String[] args) {

        MenuPrincipal menuPrincipal = new MenuPrincipal();

        menuPrincipal.mostrar();

        ScannerManager
                .getInstancia()
                .cerrarScanner();
    }
}