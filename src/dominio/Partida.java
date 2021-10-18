/*
 */
package dominio;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 *
 * @author Rodrigo Pistón
 */
public class Partida{
    
    private LocalDateTime horaComienzo;
    private Jugador jugador;
    private Juego juego;
    private int puntaje;

    public Partida(Jugador jugador, Juego juego,int puntaje) {
        this.setHoraComienzo(LocalDateTime.now());
        this.setJuego(juego);
        this.setJugador(jugador);
        this.setPuntaje(puntaje);
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public Juego getJuego() {
        return juego;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public LocalDateTime getHoraComienzo() {
        return horaComienzo;
    }

    private void setHoraComienzo(LocalDateTime horaComienzo) {
        this.horaComienzo = horaComienzo;
    }

    private void setJuego(Juego juego) {
        this.juego = juego;
    }

    private void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}

