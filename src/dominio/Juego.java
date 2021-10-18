package dominio;

import java.time.LocalDateTime;


/**
 *
 * @author  Rodrigo Pistón(261777) | Rodrigo Camps(241344)
 */
public abstract class Juego {
    private boolean configuracionPrecargada;
    private int puntaje;
    private Tablero tablero;
    private boolean movimientosDisponibles;
    
    public Juego(boolean configuracionPrecargada) {
        this.setConfiguracionPrecargada(configuracionPrecargada);
    }

    public int getPuntaje() {
        return this.puntaje;
    }
    
    public Tablero getTablero() {
        return tablero;
    }
    
    public boolean getConfiguracionPredeterminada() {
        return this.configuracionPrecargada;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
    public void setConfiguracionPrecargada(boolean configuracionPrecargada) {
        this.configuracionPrecargada = configuracionPrecargada;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public boolean getMovimientosDisponibles() {
        return movimientosDisponibles;
    }

    public void setMovimientosDisponibles(boolean movimientosDisponibles) {
        this.movimientosDisponibles = movimientosDisponibles;
    }
    
    public abstract void iniciar();
    public abstract void recargar();    
    public abstract void calcularPuntaje(); 
    public abstract boolean obtenerMovimientosDisponibles();
}
