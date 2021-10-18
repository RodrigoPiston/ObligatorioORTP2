package dominio;

/**
 *
 * @author  Rodrigo Pistón(261777) | Rodrigo Camps(241344)
 */
public class TableroRectangulo extends Tablero {

    private static int Ancho = 20;
    private static int Largo = 20;
    
    public TableroRectangulo() {
        super(Ancho, Largo);
        this.generarFichasPredeterminadas();
    }

    /// -- Array de 20 posiciones, que contiene array con las fichas predeterminadas colocadas
    private int [][] posicionesPredeterminadas = {{3},{0},{3},{5},{0},{16},{0},
        {0},{10,11},{0},{0},{10,20},{18},{17},
        {6,11,20},{17},{5},{4,11},{3,14},{15}}; 

    public int[][] getPosicionesPredeterminadas() {
        return posicionesPredeterminadas;
    }
    
    @Override
    public void generarTablero() {
        String [][] contenidoTablero = new String[super.getLargo() + 2][super.getAncho() + 1];

        // -- Se arma los espacios sin utilizar
        contenidoTablero[0][0] = "  ";
        contenidoTablero[1][0] = "           ";

        for (int fila = 0; fila < this.getFichas().length ; fila++) {
            for (int columna = 0; columna < this.getFichas()[0].length ; columna++) {
                contenidoTablero[fila + 2][columna + 1] = getFichas()[fila][columna].toString(); 
                if(columna < 9){
                    contenidoTablero[0][columna+ 1] =  String.format("%d", ( columna + 1)) ;
                    contenidoTablero[1][columna + 1] =  "" ;
                }else{
                    contenidoTablero[0][columna +1] = Character.toString( String.format("%d", (columna + 1)).charAt(0)) ;
                    contenidoTablero[1][columna +1] = Character.toString( String.format("%02d", (columna - 9)).charAt(1));
                }
            }
            contenidoTablero[fila + 2][0] = String.format("%02d", (fila + 1));
        }
        super.setContenido(contenidoTablero);
    }

    @Override
    public void generarFichasAlAzar() {
        Ficha ficha;
        for (int fila = 0; fila < this.getFichas().length; fila++) {
            for (int columna = 0; columna < this.getFichas()[fila].length; columna++) {
                if(utilidad.Entrada.getRandom(0,20) == 0){
                    ficha = new Ficha(0,"*");
                }else{
                    ficha = new Ficha(0,"-");
                }
                this.agregarFicha(ficha, fila, columna);
            }
        }
    }

    @Override
    public void generarFichasPredeterminadas() {
     
        Ficha ficha;
        for (int fila = 0; fila < this.getFichas().length; fila++) {
            for (int columna = 0; columna < this.getFichas()[0].length; columna++) {
                if( this.esPosicionPredeterminada(fila, columna)){
                    ficha = new Ficha(0,"*");
                }else{
                    ficha = new Ficha(0,"-");
                }
                this.agregarFicha(ficha, fila, columna);
            }
        }
    }
    
    private boolean esPosicionPredeterminada(int fila, int columnaSeleccionada) {
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
}
