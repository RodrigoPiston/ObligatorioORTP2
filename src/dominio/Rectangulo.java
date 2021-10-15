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

    public void setTableroFichas(int[][] tablero) {
        this.tableroFichas = tablero;
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
        this.getTablero()[0][0] = "  ";
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

    
    public int calcularPuntaje(){
        return 0;
    };

    public boolean siguienteMovimiento(int colorActual,int rectanguloPosicionX, int rectanguloPosicionY, int rectanguloAlto, int rectanguloAncho) {
        boolean movimientoPermitido = true;
        // -- Si no se le resta 1 estaría desfasado en el array
        rectanguloPosicionX -= 1;
        rectanguloPosicionY -= 1;
        int coordenadaXFinal = (rectanguloPosicionX + rectanguloAlto);
        int coordenadaYFinal = (rectanguloPosicionY + rectanguloAncho);
        int [][] tableroAuxiliar = this.getTableroFichas().clone();        
        System.out.printf("Color %d rectanguloPosicionX:%d rectanguloPosicionY:%d rectanguloAlto:%d rectanguloAncho:%d\n",colorActual,rectanguloPosicionX,rectanguloPosicionY, rectanguloAlto,rectanguloAncho,rectanguloAncho);

        if(rectanguloPosicionX >= 0 && rectanguloPosicionX < tableroAuxiliar[0].length && rectanguloPosicionY >= 0 && rectanguloPosicionY < tableroAuxiliar.length){
            for(int fila = rectanguloPosicionX ; fila < coordenadaXFinal && movimientoPermitido ; fila++) {
                for (int columna = rectanguloPosicionY; columna < coordenadaYFinal && movimientoPermitido; columna++) {
                    // -- Si es igual a un * se termina
                    if(tableroAuxiliar[fila][columna] == 6 || tableroAuxiliar[fila][columna] == 1 || tableroAuxiliar[fila][columna] == 2 || 
                       tableroAuxiliar[fila][columna] == 3 || tableroAuxiliar[fila][columna] == 4){
                       movimientoPermitido = false;
                    }else{
                        tableroAuxiliar[fila][columna] = colorActual;
                    }
                }
            }
        }else{
            movimientoPermitido = false;
        }
        /* Hay que recorrer los bordes
        for (int fila = 0; fila < tableroAuxiliar.length; fila++) {
            
        }*/
        
        if(movimientoPermitido){
            this.setTableroFichas(tableroAuxiliar);
        }
        return movimientoPermitido;
    }

    @Override
    public boolean siguienteMovimiento(int colorSeleccionado, int colSeleccionada) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
