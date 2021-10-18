package dominio;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author  Rodrigo Pistón(261777) | Rodrigo Camps(241344)
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
    
    public ArrayList<Partida> partidasOrdenadasPuntajeDesc(){
        Collections.sort(this.getListaPartidas(),(partida1, partida2) -> {
            return partida2.getJuego().getPuntaje() - partida1.getJuego().getPuntaje(); 
        });
        return this.getListaPartidas();
    }

    public ArrayList<Partida> partidasOrdenadasAliasCrec(){
        Collections.sort(this.getListaPartidas(),(partida1, partida2) -> {
            return partida1.getJugador().getAlias().compareTo(partida2.getJugador().getAlias()) ; 
        });
        return this.getListaPartidas();
    }
    
    public ArrayList<Partida> partidasOrdenadasFechaDecr(){
        Collections.sort(this.getListaPartidas(),(partida1, partida2) -> {
            return partida1.getFechaHoraComienzo().compareTo(partida2.getFechaHoraComienzo()) ; 
        });
        return this.getListaPartidas();
    }
    
    
}
