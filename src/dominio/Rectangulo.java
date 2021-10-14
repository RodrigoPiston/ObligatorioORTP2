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
    private int [][] posicionesPredeterminadas = {{3},{0},{3},{5},{0},{16},{0},
        {0},{10,11},{0},{0},{10,20},{18},{17},
        {6,11,20},{17},{5},{4,11},{3,14},{15}}; 
    
    private int [][] tableroFichas = new int [20][20];
    //private String [][] tablero = new String[22][21];
    private String [][] tablero = new String[22][21];

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
        for (int fila = 0; fila < this.getTableroFichas().length; fila++) {
            for (int columna = 0; columna < this.getTableroFichas()[fila].length; columna++) {
                if(utilidad.Entrada.getRandom(0,20) == 0){
                    this.getTableroFichas()[fila][columna] = 6;
                }else{
                    this.getTableroFichas()[fila][columna] = 5;
                }
            }
        }
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
        // -- Se arma los espacios sin utilizar
        this.getTablero()[0][0] = "xx";
        this.getTablero()[1][0] = "           ";

        for (int fila = 0; fila < this.getTableroFichas().length ; fila++) {
            for (int columna = 0; columna < this.getTableroFichas()[0].length ; columna++) {
                this.getTablero()[fila + 2][columna + 1] = utilidad.Constante.ResolverColor(this.getTableroFichas()[fila ][columna]); 
                if(columna < 9){
                    this.getTablero()[0][columna+ 1] =  String.format("%d", ( columna + 1)) ;
                    this.getTablero()[1][columna + 1] =  "" ;
                }else{
                    this.getTablero()[0][columna +1] = Character.toString( String.format("%d", (columna + 1)).charAt(0)) ;
                     this.getTablero()[1][columna +1] = Character.toString( String.format("%02d", (columna - 9)).charAt(1));
                }
            }
            this.getTablero()[fila + 2][0] = String.format("%02d", (fila + 1));

        }
    }

    private boolean posicionPredeterminada(int fila, int columnaSeleccionada) {
        boolean generar = false;
        // --  Para no tratar con la posición 0
        columnaSeleccionada += 1;
        for (int columna = 0; columna < this.getPosicionesPredeterminadas()[fila].length && !generar; columna++) {
            System.out.printf("valor:%d fila:%d columna:%d\n",this.getPosicionesPredeterminadas()[fila][columna],fila,columnaSeleccionada);
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
