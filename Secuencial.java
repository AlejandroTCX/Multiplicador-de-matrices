package Paralelas;




public class Secuencial extends Thread {
    public static int contador = 0;

    @Override
    public void run() {
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        for (int y = 0; y < Main.tamanoMatriz; y++) {
            for (int x = 0; x < Main.tamanoMatriz; x++) {
                for (int i = 0; i < Main.tamanoMatriz; i++) {
                    Interfaz.matrizSecuencial[y][x] += Interfaz.matriz1[y][i] * Interfaz.matriz2[i][x];
                }
                contador++;
            }

        }
        time_end = System.currentTimeMillis();
        int resultadoMS = (int) (time_end - time_start);
        Interfaz.lblSecuencialTiempo.setText("Tiempo: " + resultadoMS+ " ms");
        contador = 0;
    }
}
