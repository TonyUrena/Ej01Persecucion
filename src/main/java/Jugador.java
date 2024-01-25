import javafx.beans.Observable;
import javafx.beans.value.ObservableIntegerValue;

public class Jugador extends Entidad {

    static boolean isDeadJim = false;

    public Jugador(int x, int y) {
        super.coordenadaX = x;
        super.coordenadaY = y;
    }

}
