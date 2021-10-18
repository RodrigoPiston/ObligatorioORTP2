/*
 */
package dominio;

import java.time.LocalDateTime;
import java.util.Arrays;
import utilidad.Generico;

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
    private String [][] tablero = new String[22][21];

    public Rectangulo(boolean configuracionPredeterminada) {
        super(configuracionPredeterminada);
    }

    public int[][] getTableroFichas() {
        return tableroFichas;
    }

    public void setTableroFichas(int[][] tablero) {
        this.tableroFichas = tablero;
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
    
    public void recargar(){
         this.generarTablero();
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
       /* this.getTablero()[0][0] = "  ";
        this.getTablero()[1][0] = "           ";

        for (int fila = 0; fila < this.getTableroFichas().length ; fila++) {
            for (int columna = 0; columna < this.getTableroFichas()[0].length ; columna++) {
                this.getTablero()[fila + 2][columna + 1] = utilidad.Generico.RetornarColor(this.getTableroFichas()[fila][columna]); 
                if(columna < 9){
                    this.getTablero()[0][columna+ 1] =  String.format("%d", ( columna + 1)) ;
                    this.getTablero()[1][columna + 1] =  "" ;
                }else{
                    this.getTablero()[0][columna +1] = Character.toString( String.format("%d", (columna + 1)).charAt(0)) ;
                     this.getTablero()[1][columna +1] = Character.toString( String.format("%02d", (columna - 9)).charAt(1));
                }
            }
            this.getTablero()[fila + 2][0] = String.format("%02d", (fila + 1));
        }*/
    }

    private boolean posicionPredeterminada(int fila, int columnaSeleccionada) {
        boolean generar = false;
        // --  Para no tratar con la posición 0
        columnaSeleccionada += 1;
        for (int columna = 0; columna < this.getPosicionesPredeterminadas()[fila].length && !generar; columna++) {
            if(this.getPosicionesPredeterminadas()[fila][columna] == columnaSeleccionada){
                generar = true;
            }
        }
        return generar;
    }

    public boolean siguienteMovimiento(int colorActual,int coordenadaXInicial, int coordenadaYInicial, int rectanguloAlto, int rectanguloAncho, boolean primeraJugada) {
        boolean movimientoPermitido = true;
        boolean encontroAdyacente = false;
        // -- Si no se le resta 1 estaría desfasado en el array
        coordenadaXInicial -= 1;
        coordenadaYInicial -= 1;
        int coordenadaXFinal = (coordenadaXInicial + rectanguloAlto);
        int coordenadaYFinal = (coordenadaYInicial + rectanguloAncho);
        int [][] tableroAuxiliar = new int [20][20];  
        utilidad.Generico.CopiarMatriz(tableroFichas, tableroAuxiliar);
        System.out.printf("Color %d rectanguloPosicionX:%d rectanguloPosicionY:%d rectanguloAlto:%d rectanguloAncho:%d\n",colorActual,coordenadaXInicial,coordenadaYInicial, rectanguloAlto,rectanguloAncho,rectanguloAncho);

        // -- Si las coordenadas estan dentro del tamaño del tablero
        if(coordenadaXInicial >= 0 && coordenadaXInicial < tableroAuxiliar[0].length && coordenadaYInicial >= 0 && coordenadaYInicial < tableroAuxiliar.length){
            // -- Se recorre el tablero solo en las posiciones del rectangulo cálculado para ahorrar iteraciones innecesarias
            for(int fila = coordenadaXInicial ; fila < coordenadaXFinal && movimientoPermitido; fila++) {
                for (int columna = coordenadaYInicial; columna < coordenadaYFinal && movimientoPermitido; columna++) {
                    // -- Si es igual a un * o a un númeral se termina, porque estaría pisando un rectangulo
                    if(tableroAuxiliar[fila][columna] == 6 || Generico.EsNumeral(tableroAuxiliar[fila][columna])){
                       movimientoPermitido = false;
                    }else{
                        tableroAuxiliar[fila][columna] = colorActual;
                    }
                    
                    // -- Se controlan los bordes exteriores, sin pasarse del límite del rectangulo,
                    //    Si esta en el límite || es un rectangulo de 1 de ancho || 1 de alto && no se sobrepasa del límite de la matríz && es númeral
                    // -- Borde Superior
                    if((fila == coordenadaXInicial || rectanguloAncho == 1)   && (fila - 1) >= 0 && Generico.EsNumeral(tableroAuxiliar[fila -1][columna])){
                        encontroAdyacente = true;
                    // -- Borde Inferior
                    }else if((fila == coordenadaXFinal || rectanguloAncho == 1) && (fila + 1) < tableroAuxiliar.length && Generico.EsNumeral(tableroAuxiliar[fila + 1][columna])){
                        encontroAdyacente = true;
                    // -- Borde Izquierdo
                    }else if((columna == coordenadaYInicial || rectanguloAlto == 1) && (columna - 1) >= 0   && Generico.EsNumeral(tableroAuxiliar[fila][columna - 1])){
                        encontroAdyacente = true;
                     // -- Borde Derecho
                    }else if((columna == coordenadaYFinal  || rectanguloAlto == 1) && (columna + 1) < tableroAuxiliar[0].length && Generico.EsNumeral(tableroAuxiliar[fila][columna + 1]) ){
                        encontroAdyacente = true;
                    }         
                }
            }
        }else{
            movimientoPermitido = false;
        }
        movimientoPermitido = movimientoPermitido && (encontroAdyacente || primeraJugada);
        if(movimientoPermitido){
            utilidad.Generico.CopiarMatriz(tableroAuxiliar, this.getTableroFichas());
        }
        return movimientoPermitido;
    }
    
    public int calcularPuntaje(){
        int puntaje = 0;
        for (int fila = 0; fila < tableroFichas.length; fila++) {
            for (int columna = 0; columna < tableroFichas[0].length; columna++) {
                if(Generico.EsNumeral(tableroFichas[fila][columna])){
                    puntaje ++ ;
                }
            }
        }
        return puntaje;
    };
    
    public boolean quedanRectangulosDisponibles(){
        boolean disponible = false;
        for (int fila = 0; fila < tableroFichas.length; fila++) {
            for (int columna = 0; columna < tableroFichas[0].length && !disponible ; columna++) {
                disponible = this.getTableroFichas()[fila][columna] == 5;
            }
        }
        return disponible;
    }

    @Override
    public boolean siguienteMovimiento(Ficha fichaActual, int colSeleccionada) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
