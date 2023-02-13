package Paralelas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Interfaz extends JFrame {

    public static int[][] matrizSecuencial;
    public static int[][] matrizConcurrente;
    public static int[][] matrizParalela;
    public static int[][] matriz1;
    public static int[][] matriz2;
    private JPanel textoBotones;

    JLabel lblSecuencial;
    JLabel lblConcurrente;


    public static JLabel lblTiempo;

    public static JLabel lblSecuencialTiempo;
    public static JLabel lblConcurrenteTiempo;

    public static JLabel tiempoConcurrencia;

    public static JLabel lblMatriz;

    public static JLabel lblTamanoMatriz;

    public static JLabel lblHilos;

    public static JLabel lblNumeroHilos;





    public Interfaz() {
        setBounds(400, 200, 550, 180);
        setTitle(" Proyecto");


        matriz1 = new int[Main.tamanoMatriz][Main.tamanoMatriz];
        matriz2 = new int[Main.tamanoMatriz][Main.tamanoMatriz];

        matrizSecuencial = new int[Main.tamanoMatriz][Main.tamanoMatriz];
        matrizConcurrente = new int[Main.tamanoMatriz][Main.tamanoMatriz];
        matrizParalela = new int[Main.tamanoMatriz][Main.tamanoMatriz];


        textoBotones = new JPanel();
        ingresarValoresAleatorios(matriz1);
        ingresarValoresAleatorios(matriz2);
        ponerItems();
        logicaBotones();

        add(textoBotones, BorderLayout.SOUTH);
    }

    private void ponerItems() {

        lblTiempo = new JLabel("Tiempo de ejecucion");
        lblTiempo.setBounds(15,2,1000,20);
        add(lblTiempo);
        //tamano de la matriz
        lblMatriz = new JLabel("Tamano de la matriz:");
        lblMatriz.setBounds(350,15,1000,20);
        add(lblMatriz);

        lblTamanoMatriz = new JLabel(String.valueOf(Main.tamanoMatriz)+"x"+String.valueOf(Main.tamanoMatriz));
        lblTamanoMatriz.setBounds(350,30,100,20);
        add(lblTamanoMatriz);

        //Numero de hilos
        lblHilos = new JLabel("Numero de hilos usados:");
        lblHilos.setBounds(350,50,1000,20);
        add(lblHilos);

        lblNumeroHilos = new JLabel(String.valueOf(Main.numeroHilos));
        lblNumeroHilos.setBounds(350,65,1000,20);
        add(lblNumeroHilos);


        // Secuencial
        lblSecuencial = new JLabel("Secuencial: ");
        lblSecuencial.setBounds(15, 25, 100, 20);
        add(lblSecuencial);

        lblSecuencialTiempo = new JLabel("0ms");
        lblSecuencialTiempo.setBounds(100, 25, 150, 20);
        add(lblSecuencialTiempo);


        // Concurrente
        lblConcurrente = new JLabel("Concurrente: ");
        lblConcurrente.setBounds(15, 55, 100, 20);
        add(lblConcurrente);

        tiempoConcurrencia = new JLabel("0ms--- ");
        tiempoConcurrencia.setBounds(100,55,100,10);
        add(tiempoConcurrencia);






    }

    private void logicaBotones() {

        ponerBoton(textoBotones, "Secuencial", e -> {
            Thread hiloSecuencial = new Secuencial();
            hiloSecuencial.start();
        });

        ponerBoton(textoBotones, "Concurrente", e -> {

            long time_start, time_end;
            time_start = System.currentTimeMillis();

            ExecutorService executor = Executors.newFixedThreadPool(Main.numeroHilos);
            for (int fila = 0; fila < Main.tamanoMatriz; fila++) {
                for (int columna = 0; columna < Main.tamanoMatriz; columna++) {
                    executor.execute(new Concurrente(fila, columna));
                }
            }
            time_end = System.currentTimeMillis();
            int resutlado = (int) (time_end-time_start);
            tiempoConcurrencia.setText(String.valueOf(resutlado));
            executor.shutdown();


        });
    }

    private void ponerBoton(Container c, String titulo, ActionListener oyente) {
        JButton boton = new JButton(titulo);
        c.add(boton);
        boton.addActionListener(oyente);
    }

    private void ingresarValoresAleatorios(int[][] matriz) {
        for (int y = 0; y < Main.tamanoMatriz; y++) {
            for (int x = 0; x < Main.tamanoMatriz; x++) {
                matriz[y][x] = (int) (Math.random() * 10 + 1);
            }
        }
    }
}
