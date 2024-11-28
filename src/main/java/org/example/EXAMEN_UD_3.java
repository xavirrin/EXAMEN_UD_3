package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class EXAMEN_UD_3 {
    public void ejercicio1() {

        Scanner teclado = new Scanner(System.in);
        Random aleatorio = new Random();

        System.out.println("\n*** BIENVENIDO AL CASINO DEL CANTÁBRICO ***");

        int bolas_salidas = aleatorio.nextInt(10, 40);
        int bolas_array[] = new int[bolas_salidas];

        System.out.print("\n" + bolas_salidas + " bolas extraidas hasta ahora: ");
        for (int i = 0; i < bolas_salidas; i++) {
            bolas_array[i] = aleatorio.nextInt(90) + 1;
        }
        System.out.println(Arrays.toString(bolas_array));

        Arrays.sort(bolas_array);

        boolean ordenado = false;

        while (!ordenado) {
            ordenado = true;
            for (int i = 0; i < bolas_array.length - 1; i++) {
                if (bolas_array[i] == bolas_array[i + 1]) {
                    bolas_array[i] = aleatorio.nextInt(90) + 1;
                    ordenado = false;
                }
            }
            Arrays.sort(bolas_array);
        }
        System.out.println("\nBolas ordenadas sin repeticiones:");
        System.out.println(Arrays.toString(bolas_array));

        int filas = 3;
        int columnas = 3;

        int carton[][] = new int[filas][columnas];

        System.out.println("\n*** Introduce los datos de tu cartón ***");


            boolean formato = false;
            while (!formato) {
                formato = true;
                for (int i = 0; i < filas; i++) {
                System.out.println("Fila " + (i + 1) + " (Formato N-N-N):");
                    String fila = teclado.nextLine();
                     boolean formato_carton = fila.matches("\\d{1,2}-\\d{1,2}-\\d{1,2}");
                     if(!formato_carton) {
                         System.out.println("El formato es incorrecto.");
                         formato = false;
                         return;
                     }else{
                         String carton_vect[] = fila.split("-");

                         int fila_vector[] = new int [columnas];

                         for (int j = 0; j < carton.length; j++) {
                             fila_vector[j] = Integer.parseInt(carton_vect[j]);
                             carton[i][j] = fila_vector[j];
                         }
                         System.out.println(Arrays.toString(fila_vector));

                     }

                }
                System.out.println("\n");
                System.out.println("Datos del cartón introducido: ");
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        System.out.print(carton[i][j] + " ");
                    }
                    System.out.println();
                }
                int contador = 0;
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        boolean num_premiado = Arrays.asList(bolas_array).contains(carton[i][j]);
                        boolean linea_premiada = false;

                    if(num_premiado){
                        contador += 1;
                    }
                    if(contador == 9){
                        System.out.println("\nHAY BINGO!!!");
                    }else{
                        System.out.println("\nOtra vez será... ");
                        return;
                    }
                    }

                }

                }
            }
        }









