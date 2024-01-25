public class Entidad{

    // Tanto el jugador como los enemigos comparten el atributo coordenadas
    // y un id para mostrar en pantalla su instancia

    int coordenadaX, coordenadaY, numeroEntidad;

    @Override
    public String toString() {
        return "Posición del " +
                this.getClass().getSimpleName() +
                " " + numeroEntidad +
                ": X " + coordenadaX +
                " : Y " + coordenadaY;
    }
}
