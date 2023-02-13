package Paralelas;

public class Concurrente implements Runnable {
    public static int contador = 0;
    int fila;
    int columna;

    public Concurrente(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public void run() {
        Interfaz.matrizConcurrente[fila][columna] = 0;
        for (int i = 0; i < Main.tamanoMatriz; i++) {
            Interfaz.matrizConcurrente[fila][columna] += Interfaz.matriz1[fila][i] * Interfaz.matriz2[i][columna];
        }
        contador++;

    }
}
