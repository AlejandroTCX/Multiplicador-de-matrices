package Paralelas;

import javax.swing.*;

public class Main {
    public static int tamanoMatriz;
    public static int numeroHilos;

    public static void main(String[] args) {
        String auxInput = "";

        // Datos a ingresar
        auxInput = JOptionPane.showInputDialog("Ingresa la dimension de la matriz");
        tamanoMatriz = Integer.parseInt(auxInput);

        auxInput = JOptionPane.showInputDialog("Cuantos hilos quieres usar?");
        numeroHilos = Integer.parseInt(auxInput);

        Interfaz interfaz = new Interfaz();
        interfaz.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        interfaz.setVisible(true);
    }
}
