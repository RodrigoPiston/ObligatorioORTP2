/*
 */
package dominio;
import java.time.LocalDateTime; 

/**
 *
 * @author Rodrigo Pistón
 */
public class Saltar extends Juego{

    private String [][] matrizInterfaz = new String[24][10];
    /// -- Fichas predetermiandas
    private int [][] tableroFichasBase = {{1,2,3,4},{2,1,4,3},{3,4,2,1},{4,3,1,2}};
    private int [][] tableroFichas = new int [11][4];
    
    public Saltar(boolean configuracionPredeterminada) {
        super(configuracionPredeterminada);
    }

    public String[][] getTablero() {
        return matrizInterfaz;
    }

    public int[][] getTableroFichas() {
        return tableroFichas;
    }

    public int[][] getTableroFichasBase() {
        return tableroFichasBase;
    }
    
    public void iniciar(){
        if(!this.getConfiguracionPredeterminada()){
            this.generarFichasAlAzar();
        }
        this.generarTableroPuntos();
        this.generarTablero();
    }
    
    public void recargar(){
        this.generarTablero();
    }
    
    private void generarFichasAlAzar(){
        // -- Valores entre el 1 y el 4
        int offset = (int) (Math.random()*(4-0)) + 1;
        /// -- Una mejor forma es revisar la posición de la columna anterior, si coincide, no se pone,y se pone en la siguiente. 
        for (int fila = 0; fila < this.getTableroFichasBase().length ; fila++) {
            for (int columna = 0; columna < this.getTableroFichasBase()[0].length ; columna++) {
                int valorActual = this.getTableroFichasBase()[fila][columna] + offset;
                if(valorActual > 4){
                    int diferencia = (valorActual - 4);
                    this.getTableroFichasBase()[fila][columna] = diferencia;
                }else{
                    this.getTableroFichasBase()[fila][columna] = valorActual;
                }
            }
        }
    }
    
    private void generarTableroPuntos(){
        for (int fila = 7; fila < this.getTableroFichas().length; fila++) {
            this.getTableroFichas()[fila] = this.getTableroFichasBase()[ fila - 7];
        }
    }
    
    public void generarTablero(){
        int puntuacion = 60;        
        int posicion = 1;
        int filaTableroFichas = 0;
        int columnaTableroFichas = 0;
        boolean filaPar = false;
        boolean columnaPar = false;
        for (int fila = 0; fila < this.getTablero().length ; fila++) {
            filaPar = (fila  + 1)% 2 == 0;
            for (int columna = 0; columna < this.getTablero()[0].length ; columna++) {
                columnaPar = (columna + 2) % 2 == 0;
                if(columna == 0 ){
                    if(filaPar && puntuacion > 0){
                        this.getTablero()[fila][columna] = Integer.toString(puntuacion)+ " ";
                        if(puntuacion == 60){
                            puntuacion -= 20;
                        }else{
                            puntuacion -= 10;
                        }
                    }else{
                        this.getTablero()[fila][columna] = "   ";
                    }
                }else if(fila == this.getTablero().length - 1){
                    if(columnaPar && posicion <= 4){
                        this.getTablero()[fila][columna] = Integer.toString(posicion);
                        posicion ++;
                    }else{
                        this.getTablero()[fila][columna] = " ";
                    }
                }
                else if(filaPar){
                    if(!columnaPar){
                        this.getTablero()[fila][columna] = "|"; 
                    }else{
                            this.getTablero()[fila][columna] = utilidad.Generico.RetornarColor(tableroFichas[filaTableroFichas][columnaTableroFichas]); 
                        columnaTableroFichas ++;
                    }
                }
                else if(columnaPar){
                    this.getTablero()[fila][columna] = "-"; 
                }else if(!columnaPar) {
                    this.getTablero()[fila][columna] = "+"; 
                }
            }
            if(filaPar){
                filaTableroFichas ++;
                columnaTableroFichas = 0;
            }
        }
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
        int puntaje = 0;
        for (int fila = 0; fila < this.getTableroFichas().length && fila <= 4; fila++) {
            for (int columna = 0; columna < this.getTableroFichas()[0].length; columna++) {
                if(this.getTableroFichas()[fila][columna] != 0){
                    switch(fila){
                        case 0:
                            puntaje += 60;
                        break;
                        case 1:
                            puntaje += 40;
                        break;
                        case 2:
                            puntaje += 30;
                        break;
                        case 3:
                            puntaje += 20;
                        break;
                        case 4:
                            puntaje += 10;
                        break;
                    }   
                }
            }
        }
        return puntaje;
    }

}
