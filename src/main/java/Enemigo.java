import javafx.collections.ListChangeListener;

public class Enemigo extends Entidad implements ListChangeListener<Jugador> {

    int movimientos = 2;

    public Enemigo(int x, int y, int numeroEntidad) {
        super.coordenadaX = x;
        super.coordenadaY = y;
        super.numeroEntidad = numeroEntidad;
    }

    @Override
    public void onChanged(Change<? extends Jugador> change) {

        // Establecemos dos movimientos para el enemigo por cada movimiento del jugador
        // Obtenemos la distancia entre el enemigo y el jugador restando sus coordenadas,
        // el signo de esta resta devolverá 1 o -1 dependiendo si éstas son positivas o
        // negativas.
        // Si X o Y son iguales, el enemigo mueve sus dos turnos en la otra coordenada.
        // Si las coordenadas son iguales, dejamos los movimientos a cero para permitir
        // al hilo principal terminar el programa

        while (change.next()){

            Jugador jugador = change.getAddedSubList().get(0);

            while (movimientos > 0) {

                if(this.coordenadaX != jugador.coordenadaX) {
                    this.coordenadaX += Integer.signum(jugador.coordenadaX - this.coordenadaX);
                    movimientos--;
                }

                if(this.coordenadaY != jugador.coordenadaY) {
                    this.coordenadaY += Integer.signum(jugador.coordenadaY - this.coordenadaY);
                    movimientos--;
                }

                if (jugador.coordenadaX == this.coordenadaX && jugador.coordenadaY == this.coordenadaY){
                    movimientos = 0;
                    Jugador.isDeadJim = true;
                }

            }

            // Mostramos la posición del enemigo al finalizar su turno.
            System.out.println(this);
        }
    }
}
