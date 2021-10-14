/*
 
 */
package dominio;

import java.util.ArrayList;

/**
 *
 * @author Rodrigo Pistón
 */
public class Sistema {
    private ArrayList<Jugador> listaJugadores;
    private ArrayList<Juego> listaJuegos;
    private ArrayList<Partida> listaPartidas;

    public Sistema() {
        this.listaJuegos = new ArrayList<Juego>();
        this.listaJugadores = new ArrayList<Jugador>();
        this.listaPartidas = new ArrayList<Partida>();
    }

    public ArrayList<Juego> getListaJuegos() {
        return this.listaJuegos;
    }

    public ArrayList<Jugador> getListaJugadores() {
        return this.listaJugadores;
    }

    public ArrayList<Partida> getListaPartidas() {
        return this.listaPartidas;
    }

    public void agregarJuego(Juego juego) {
        this.getListaJuegos().add(juego);
    }
    
    public void agregarPartida(Partida partida) {
        this.getListaPartidas().add(partida);
    }
    
    public boolean agregarJugador(Jugador jugadorNuevo) {
        
        boolean agregar = false;
        
        for (final Jugador jugador : this.getListaJugadores()) {
            if (jugador.equals(jugadorNuevo)) {
                agregar = true;
            }
        }
        if(!agregar){
            this.getListaJugadores().add(jugadorNuevo);
        }
        return !agregar;
    }
    
}
