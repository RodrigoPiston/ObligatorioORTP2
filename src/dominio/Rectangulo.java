/*
 */
package dominio;

import java.time.LocalDateTime;

/**
 *
 * @author Rodrigo Pistón
 */
public class Rectangulo extends Juego {

    /// -- Array de 20 posiciones, que contiene array con las fichas predeterminadas colocadas
    private int [][] posicionesPredeterminadas = {
        {0},{0},{1,3,19},{18},{4,17},{15},{0},
        {0},{0},{9,12},{9,15,18},{0},{0},{19},
        {20},{6},{14,16},{13},{0},{12,15}}; 
    
    private int [][] tableroFichas = new int [20][20];
    //private String [][] tablero = new String[22][21];
    private String [][] tablero = new String[20][20];

    public Rectangulo(boolean configuracionPredeterminada) {
        super("Rectangulo", configuracionPredeterminada);
    }

    public int[][] getTableroFichas() {
        return tableroFichas;
    }

    public String[][] getTablero() {
        return tablero;
    }

    public int[][] getPosicionesPredeterminadas() {
        return posicionesPredeterminadas;
    }
    
    
    public void iniciar(){
        if(!this.getConfiguracionPredeterminada()){
            this.generarFichasAlAzar();
        }else{
            this.generarFichasPredeterminadas();
        }
        this.generarTablero();
    }
    
    /**
     *
     */
    public void recargar(){
        
    }
    
    private void generarFichasAlAzar(){
    }
    
    private void generarFichasPredeterminadas(){
        for (int fila = 0; fila < this.getTableroFichas().length; fila++) {
            for (int columna = 0; columna < this.getTableroFichas()[0].length; columna++) {
                if(posicionPredeterminada(fila,columna)){
                    this.getTableroFichas()[fila][columna] = 6;
                }else{
                    this.getTableroFichas()[fila][columna] = 5;
                }
            }
        }
    }
    
    private void generarTablero() {
        for (int fila = 0; fila < this.getTableroFichas().length ; fila++) {
            for (int columna = 0; columna < this.getTableroFichas()[0].length ; columna++) {
                this.getTablero()[fila][columna] = utilidad.Constante.ResolverColor(this.getTableroFichas()[fila][columna]) + " "; 
            }
        }
    }

    private boolean posicionPredeterminada(int fila, int columnaSeleccionada) {
        boolean generar = false;
        for (int columna = 0; columna < this.getPosicionesPredeterminadas()[fila].length && !generar; columna++) {
            if(this.getPosicionesPredeterminadas()[fila][columna] == columnaSeleccionada){
                generar = true;
            }
        }
        return generar;
    }

    
    public boolean siguienteMovimiento(int colorSeleccionado,int colSeleccionada){
        boolean movimientoPermitido = false;
        if(colSeleccionada >= 0 && colSeleccionada <= 3 ){
            int filaOrigen = 0;
            int cantidadSaltos = 0;
            int filaDestino = 0;
            for (int fila = this.getTableroFichas().length -1; fila > 0 ; fila--) {

                // -- Si se encuentra el color en la columna, se verifica cuantas veces hay que saltar
                if(this.getTableroFichas()[fila][colSeleccionada] == colorSeleccionado){
                    filaOrigen = fila;
                     for (int columna = 0; columna < this.getTableroFichas()[0].length; columna++) {
                        if(this.getTableroFichas()[fila][columna] != 0){
                            cantidadSaltos ++;
                        }                    
                    }
                }
            }
            filaDestino = filaOrigen - cantidadSaltos;
            if(filaDestino > 0 && filaOrigen > 4  && this.getTableroFichas()[filaDestino][colSeleccionada] == 0 ){
                movimientoPermitido = true;
                for (int columna = 0; columna < this.getTableroFichas()[filaDestino].length && movimientoPermitido; columna++) {
                    if(this.getTableroFichas()[filaDestino][columna] == colorSeleccionado){
                        movimientoPermitido = false;
                    }
                }
                if(movimientoPermitido){
                    this.getTableroFichas()[filaOrigen][colSeleccionada] = 0;
                    this.getTableroFichas()[filaDestino][colSeleccionada] = colorSeleccionado;
                }
            }else{
                movimientoPermitido = false;
            }
        }
        return movimientoPermitido;
    }
    
    public int calcularPuntaje(){
        return 0;
    };


}
