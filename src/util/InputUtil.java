package util;

import java.util.Scanner;

public class InputUtil {

    /**
     * Solicita un número entero por consola. Si el usuario escribe letras,
     * atrapa la excepción y vuelve a solicitarlo hasta que sea correcto.
     */
    public static int leerEntero(Scanner scanner, String mensaje) {
        int numero = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            try {
                numero = Integer.parseInt(entrada);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada no válida. Por favor, ingrese un número entero.");
            }
        }
        return numero;
    }

}