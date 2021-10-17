/*
 */
package dominio;

import java.time.LocalDateTime;


/**
 *
 * @author Rodrigo Pistón
 */
public abstract class Juego {
    private boolean configuracionPrecargada;
    private int puntaje;

    public Juego(boolean configuracionPrecargada) {
        this.setConfiguracionPrecargada(configuracionPrecargada);
    }

    public int getPuntaje() {
        return this.puntaje;
    }
    
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
    public void setConfiguracionPrecargada(boolean configuracionPrecargada) {
        this.configuracionPrecargada = configuracionPrecargada;
    }
    
    public boolean getConfiguracionPredeterminada() {
        return this.configuracionPrecargada;
    }

    public abstract void iniciar();
    public abstract void recargar();    
    public abstract boolean siguienteMovimiento(int colorSeleccionado,int colSeleccionada);
    public abstract int calcularPuntaje();    


}
