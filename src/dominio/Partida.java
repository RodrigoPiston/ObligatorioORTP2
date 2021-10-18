package dominio;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author  Rodrigo Pistón(261777) | Rodrigo Camps(241344)
 */
public class Partida{
    
    private Date fechaHoraComienzo;
    private Jugador jugador;
    private Juego juego;

    public Partida(Jugador jugador, Juego juego,int puntaje) {
        this.setFechaHoraComienzo(new Date() );
        this.setJuego(juego);
        this.setJugador(jugador);
    }

    public Juego getJuego() {
        return juego;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public String getFechaHoraComienzoString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

        String horaComienzoString = format.format(this.fechaHoraComienzo );
        return horaComienzoString;
    }

    public Date getFechaHoraComienzo() {
        return fechaHoraComienzo;
    }
    
    private void setFechaHoraComienzo(Date horaComienzo) {
        this.fechaHoraComienzo = horaComienzo;
    }

    private void setJuego(Juego juego) {
        this.juego = juego;
    }

    private void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
    @Override
    public String toString(){
        return String.format(utilidad.Generico.AlineamientoString,this.getJuego().getClass().getSimpleName(),
                        this.getJugador().getAlias(),this.getJugador().getEdad(),
                        this.getJuego().getPuntaje(),this.getFechaHoraComienzoString());
    }
}

