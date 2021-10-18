package dominio;

import java.time.LocalDateTime;


/**
 *  Clase que contiene la la estructura para instanciar un Juego nuevo
 * @author  Rodrigo Pistón(261777) | Rodrigo Camps(241344)
 */
public abstract class Juego {
    private boolean configuracionPredeterminada;
    private int puntaje;
    private Tablero tablero;
    private boolean movimientosDisponibles;
    
    /**
     *  Establece la distribución de las fichas del tablero del juego. 
     * @param configuracionPrecargada
     */
    public Juego(boolean configuracionPrecargada) {
        this.setConfiguracionPredeterminada(configuracionPrecargada);
    }

    public int getPuntaje() {
        return this.puntaje;
    }
    
    public Tablero getTablero() {
        return tablero;
    }
    
    public boolean getConfiguracionPredeterminada() {
        return this.configuracionPredeterminada;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
    public void setConfiguracionPredeterminada(boolean configuracionPrecargada) {
        this.configuracionPredeterminada = configuracionPrecargada;
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
