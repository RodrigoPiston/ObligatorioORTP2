/*
 */
package dominio;

import java.time.LocalDateTime;


/**
 *
 * @author Rodrigo Pistón
 */
public abstract class Juego {
    private String nombre; 
    private boolean configuracionPrecargada;
    private int puntaje;

    public Juego(String nombre, boolean configuracionPrecargada) {
        this.setNombre(nombre);
        this.setConfiguracionPrecargada(configuracionPrecargada);
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getPuntaje() {
        return this.puntaje;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
