package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Programa para determinar el ganador de un torneo basado en los resultados de los partidos.
 * El programa valida los datos de entrada y procesa los resultados de los equipos clasificados.
 *
 * @author elxavo
 * @version 1.0
 */
public class Proyecto {

    static Scanner teclado = new Scanner(System.in);

    /**
     * Metodo que procesa varios casos de prueba, validando los datos y determinando el ganador del torneo.
     */
    public static void main(String[] args) {

        try {
            int numCasos = teclado.nextInt();
            teclado.nextLine();

            for (int caso = 0; caso < numCasos; caso++) {
                int numEquipos = teclado.nextInt();
                teclado.nextLine();

                if (numEquipos < 2 || numEquipos > 64 || (numEquipos & (numEquipos - 1)) != 0) {
                    System.exit(0);
                }

                String[] equipos = new String[numEquipos];

                leerEquipos(equipos, numEquipos);

                String ganador = procesarTorneo(equipos, numEquipos);

                System.out.println(ganador);
            }
        } catch (InputMismatchException e) {
            System.exit(0);
        }
    }

    /**
     * Lee los nombres y los almacena.
     * Valida que los nombres no excedan 30 caracteres y contengan solo letras.
     *
     * @param equipos Se almacenan los nombres de los equipos.
     * @param numEquipos cantidad de equipos a leer.
     */
    public static void leerEquipos(String[] equipos, int numEquipos) {

        int contador = 0;

        while (contador < numEquipos) {

            String linea = teclado.nextLine();

            String[] nombres = linea.split(" ");

            for (String nombre : nombres) {
                if (contador < numEquipos) {
                    if (nombre.length() > 30 || !nombre.matches("[a-zA-Z]+")) {
                        System.exit(0);
                    }
                    equipos[contador++] = nombre.trim();
                }
            }
        }
    }

    /**
     * Procesa un torneo de eliminación directa para determinar el equipo ganador.
     * Valida los resultados de los partidos para evitar valores invalidos.
     *
     * @param equipos contiene los nombres de los equipos participantes.
     * @param numEquipos cantidad inicial de equipos en el torneo.
     * @return el nombre del equipo ganador.
     */
    public static String procesarTorneo(String[] equipos, int numEquipos) {

        while (numEquipos > 1) {

            int numPartidos = numEquipos / 2;
            int[] resultados = leerResultados(numPartidos);

            equipos = procesarRonda(equipos, resultados, numPartidos);
            numEquipos = numPartidos;
        }
        return equipos[0];
    }
    /**
     * Procesa una unica ronda del torneo y devuelve los equipos ganadores.
     *
     * @param equipos    array de equipos participantes en la ronda actual.
     * @param resultados array con los goles de los partidos.
     * @param numPartidos número de partidos en esta ronda.
     * @return array de equipos ganadores de la ronda.
     */
    public static String[] procesarRonda(String[] equipos, int[] resultados, int numPartidos) {

        String[] ganadores = new String[numPartidos];

        int contador = 0;

        for (int i = 0; i < equipos.length; i += 2) {

            int golesEquipo1 = resultados[contador++];
            int golesEquipo2 = resultados[contador++];


            if (golesEquipo1 > 20 || golesEquipo2 > 20 || golesEquipo1 == golesEquipo2) {
                System.exit(0);
            }

            if (golesEquipo1 > golesEquipo2) {
                ganadores[i / 2] = equipos[i];
            } else {
                ganadores[i / 2] = equipos[i + 1];
            }
        }

        return ganadores;
    }

    /**
     * Lee los resultados de los partidos desde la entrada estandar.
     * Valida que los resultados sean enteros y que sean validos.
     *
     * @param numPartidos cantidad de partidos cuyos resultados se deben leer.
     * @return contiene los resultados de los partidos (goles de cada equipo).
     */
    public static int[] leerResultados(int numPartidos) {

        int[] resultados = new int[numPartidos * 2];

        int contador = 0;

        while (contador < resultados.length) {

            String linea = teclado.nextLine();
            String[] numeros = linea.split(" ");

            for (String numero : numeros) {
                if (contador < resultados.length) {
                    try {
                        int goles = Integer.parseInt(numero);
                        resultados[contador++] = goles;
                    } catch (NumberFormatException e) {
                        System.exit(0);
                    }
                }
            }
        }

        return resultados;
    }
}