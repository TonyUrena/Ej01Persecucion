import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args) {

        int cantidadEnemigos;
        int turnosJugador = 0;

        Random r = new Random();

        // La lista observable contiene un jugador en la posición 0
        // si este jugador cambia, el observador del enemigo reacciona
        ObservableList<Jugador> posicionJugador = FXCollections.observableArrayList();
        List<Enemigo> listaEnemigos = new ArrayList<Enemigo>();

        Jugador jugador = new Jugador(r.nextInt(30)+1, r.nextInt(30)+1);
        posicionJugador.add(jugador);

        System.out.println("----------------------------------------------");
        System.out.println("            ¿Cantidad de enemigos?");
        System.out.println("----------------------------------------------");

        Scanner reader = new Scanner(System.in);
        cantidadEnemigos = reader.nextInt();


        System.out.println("----------------------------------------------");
        System.out.println("          W,A,S,D mueve al jugador");
        System.out.println("----------------------------------------------");

        // Instanciamos la cantidad deseada de enemigos.
        // Podrían instanciarse como hilos independientes para mejorar el rendimiento, pero supondría
        // reestructurar parte del código para evitar condiciones de carrera
        // También imprimimos cada enemigo para que la partida sea "justa"
        for (int i = 0; i < cantidadEnemigos; i++) {
            Enemigo enemigo = new Enemigo(r.nextInt(30)+1, r.nextInt(30)+1, i+1);
            System.out.println(enemigo);
            listaEnemigos.add(enemigo);
            posicionJugador.addListener(enemigo);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String movimiento;

        // Movemos al jugador.
        // Sin una interfaz gráfica real no puede moverse al jugador de forma dinámica
        // capturando eventos del teclado, por lo que se necesita pulsar Enter después
        // de cada movimiento
        try {
        while (!Jugador.isDeadJim) {

            System.out.println("----------------------------------------------");
            System.out.println(jugador);

            movimiento = in.readLine();

            switch (movimiento) {
                case "w":
                    if (jugador.coordenadaY > 1) jugador.coordenadaY--;
                    break;
                case "a":
                    if (jugador.coordenadaX > 1) jugador.coordenadaX--;
                    break;
                case "s":
                    if (jugador.coordenadaY < 30) jugador.coordenadaY++;
                    break;
                case "d":
                    if (jugador.coordenadaX < 30) jugador.coordenadaX++;
                    break;
                default:
                    System.out.println(movimiento + " no es un movimiento válido.");
                    break;
            }

            turnosJugador++;

            // Establecemos la posición del jugador en la lista observable
            posicionJugador.set(0, jugador);

            // (Cada enemigo tiene la capacidad de establecer la variable que declara al
            // jugador como muerto, por lo que no recorremos la lista para comprobar si
            // el juego ha terminado

        }

        in.close();
            System.out.println("----------------------------------------------");
            System.out.println("                HAS PERDIDO");
            System.out.println("            Aguantaste " + turnosJugador + " turnos.");
            System.out.println("----------------------------------------------");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
