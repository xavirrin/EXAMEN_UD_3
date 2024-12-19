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
public class FinalJuez {

    //Creamos Scanner static
    static Scanner teclado = new Scanner(System.in);

    /**
     * Metodo que procesa varios casos de prueba, validando los datos y determinando el ganador del torneo.
     */
    public static void main(String[] args) {
        //Metemos un try catch para controlar que no entren letras en el int
        try { //Guardamos los casos de prueba que haremos
            int numCasos = teclado.nextInt();
            teclado.nextLine();

            //Creamos un for para que el programa finalice con el ultimo caso de prueba
            for (int caso = 0; caso < numCasos; caso++) {
                int numEquipos = teclado.nextInt();
                teclado.nextLine();

                // Validamos que la cantidad de equipos sea potencia de 2 entre 2 y 64
                if (numEquipos < 2 || numEquipos > 64 || (numEquipos & (numEquipos - 1)) != 0) {
                    System.exit(0);
                }
                //Creamos Array con la longitud del numero de equipos anteriormente pedido
                String[] equipos = new String[numEquipos];
                //Llamamos al metodo leerEquipos para obtener los nombres
                leerEquipos(equipos, numEquipos);
                //Llamamos a procesarTorneo para determinar el ganador
                String ganador = procesarTorneo(equipos, numEquipos);
                //Y lo imprimimos
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
        //Iniciamos un contador
        int contador = 0;
        //Y mientras sea menor que el numero de equipos aportado
        while (contador < numEquipos) {
            //Iremos introduciendo lineas
            String linea = teclado.nextLine();
            //Y haciendoles split para guardarlas en el array de equipos
            //De esta manera no importa si se introducen todos en una linea,
            //O en distintas lineas
            String[] nombres = linea.split(" ");
            //Validamos que cada nombre contenga un maximo de 30 letras, mayuscula y minuscula inglesas
            for (String nombre : nombres) {
                if (contador < numEquipos) {
                    if (nombre.length() > 30 || !nombre.matches("[a-zA-Z]+")) {
                        System.exit(0);
                    }//Aumentamos contador y seguimos
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
        //Mientras quede mas de un equipo:
        while (numEquipos > 1) {
            //Determinamos que el numero de partidos que se jugaran, es la mitad del numero de equipos
            int numPartidos = numEquipos / 2;
            //Llamamos a leerResultados para obtener los resultados
            int[] resultados = leerResultados(numPartidos);
            // Llamamos a procesarRonda para procesar una ronda
            equipos = procesarRonda(equipos, resultados, numPartidos);
            numEquipos = numPartidos;
        }
        return equipos[0];
    }
    /**
     * Procesa una única ronda del torneo y devuelve los equipos ganadores.
     *
     * @param equipos    array de equipos participantes en la ronda actual.
     * @param resultados array con los resultados de los partidos (goles).
     * @param numPartidos número de partidos en esta ronda.
     * @return array de equipos ganadores de la ronda.
     */
    public static String[] procesarRonda(String[] equipos, int[] resultados, int numPartidos) {
            //Inicializamos array para guardar a los ganadores de cada ronda
            String[] ganadores = new String[numPartidos];
            //Creamos un indice para ir guardando en el Array de resultados cada uno
            int contador = 0;
            //Creamos un for que va sumando 2, ya que estamos procesando pares de equipos
            for (int i = 0; i < equipos.length; i += 2) {
                //Guardamos en variables aparte los goles del equipo1 y 2 de cada partido
                int golesEquipo1 = resultados[contador++];
                int golesEquipo2 = resultados[contador++];

                // Y con estas variables controlamos que no haya mas de 20 goles por equipo, y que no haya empate
                if (golesEquipo1 > 20 || golesEquipo2 > 20 || golesEquipo1 == golesEquipo2) {
                    System.exit(0);
                }
                //Finalmente guardamos en el array ganadores solo el equipo ganador
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
        //Creamos un array para guardar los goles de cada equipo en todos los partidos
        //Con longitud de la mitad de partidos recibidos
        int[] resultados = new int[numPartidos * 2];
        //Creamos contador
        int contador = 0;
        //Y mientras sea menor que la longitud de los resultados
        while (contador < resultados.length) {
            //Guardamos linea
            String linea = teclado.nextLine();
            //Y le hacemos split y la guardamos en numeros
            String[] numeros = linea.split(" ");
            //Hacemos for para validar que es un numero entero
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