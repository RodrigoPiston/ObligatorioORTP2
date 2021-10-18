package dominio;

/**
 *
 * @author  Rodrigo Pistón(261777) | Rodrigo Camps(241344)
 */
public class Rectangulo extends Juego {

    public Rectangulo(boolean configuracionPredeterminada) {
        super(configuracionPredeterminada);
        this.setTablero(new TableroRectangulo());
    }

    public void iniciar(){
        if(!this.getConfiguracionPredeterminada()){
            this.getTablero().generarFichasAlAzar();
        }
        recargar();
    }
    
    public void recargar(){
        this.getTablero().generarTablero();
    }

    public boolean siguienteMovimiento(Ficha ficha,int coordenadaXInicial, int coordenadaYInicial, int rectanguloAlto, int rectanguloAncho, boolean primeraJugada) {
        boolean movimientoPermitido = true;
        boolean encontroAdyacente = false;
        Ficha fGuion = new Ficha(0,"-");
        // -- Si no se le resta 1 estaría desfasado en el array
        coordenadaXInicial -= 1;
        coordenadaYInicial -= 1;
        int coordenadaXFinal = (coordenadaXInicial + rectanguloAlto);
        int coordenadaYFinal = (coordenadaYInicial + rectanguloAncho);
        Ficha[][] tableroAuxiliar = new Ficha [this.getTablero().getAncho()][this.getTablero().getLargo()];  
        
        utilidad.Generico.CopiarMatriz(this.getTablero().getFichas(), tableroAuxiliar);
        
        // -- Si las coordenadas estan dentro del tamaño del tablero
        if(coordenadaXInicial >= 0 && coordenadaXInicial < tableroAuxiliar[0].length && coordenadaYInicial >= 0 && coordenadaYInicial < tableroAuxiliar.length){
            // -- Se recorre el tablero solo en las posiciones del rectangulo cálculado para ahorrar iteraciones innecesarias
            for(int fila = coordenadaXInicial ; fila < coordenadaXFinal && movimientoPermitido; fila++) {
                for (int columna = coordenadaYInicial; columna < coordenadaYFinal && movimientoPermitido; columna++) {
                    // -- Si es igual a un * o a un númeral se termina, porque estaría pisando un rectangulo
                    if(!tableroAuxiliar[fila][columna].equals(fGuion)){
                       movimientoPermitido = false;
                    }else{
                        tableroAuxiliar[fila][columna] = ficha;
                    }
                    
                    // -- Se controlan los bordes exteriores, sin pasarse del límite del rectangulo,
                    //    Si esta en el límite || es un rectangulo de 1 de ancho || 1 de alto && no se sobrepasa del límite de la matríz && es númeral
                    // -- Borde Superior
                    if((fila == coordenadaXInicial || rectanguloAncho == 1) && (fila - 1) >= 0 && tableroAuxiliar[fila -1][columna].esNumeral()){
                        encontroAdyacente = true;
                    // -- Borde Inferior
                    }else if((fila == coordenadaXFinal || rectanguloAncho == 1) && (fila + 1) < tableroAuxiliar.length && tableroAuxiliar[fila + 1][columna].esNumeral()){
                        encontroAdyacente = true;
                    // -- Borde Izquierdo
                    }else if((columna == coordenadaYInicial || rectanguloAlto == 1) && (columna - 1) >= 0   && tableroAuxiliar[fila][columna - 1].esNumeral()){
                        encontroAdyacente = true;
                     // -- Borde Derecho
                    }else if((columna == coordenadaYFinal  || rectanguloAlto == 1) && (columna + 1) < tableroAuxiliar[0].length && tableroAuxiliar[fila][columna + 1].esNumeral()){
                        encontroAdyacente = true;
                    }         
                }
            }
        }else{
            movimientoPermitido = false;
        }
        movimientoPermitido = movimientoPermitido && (encontroAdyacente || primeraJugada);
        if(movimientoPermitido){
            utilidad.Generico.CopiarMatriz(tableroAuxiliar, this.getTablero().getFichas());
        }
        return movimientoPermitido;
    }
    
    public int calcularPuntaje(){
        int puntaje = 0;
        for (int fila = 0; fila < this.getTablero().getFichas().length; fila++) {
            for (int columna = 0; columna < this.getTablero().getFichas()[0].length; columna++) {
                if(this.getTablero().getFichas()[fila][columna].esNumeral()){
                    puntaje ++ ;
                }
            }
        }
        return puntaje;
    };
    
    public boolean quedanRectangulosDisponibles(){
        boolean disponible = false;
        for (int fila = 0; fila < this.getTablero().getFichas().length; fila++) {
            for (int columna = 0; columna < this.getTablero().getFichas()[0].length && !disponible ; columna++) {
                disponible = this.getTablero().getFichas()[fila][columna].esNumeral();
            }
        }
        return disponible;
    }
}
