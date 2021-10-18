package dominio;
import java.time.LocalDateTime; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author  Rodrigo Pistón(261777) | Rodrigo Camps(241344)
 */
public class Saltar extends Juego{

    private Ficha fichaActual;
    private int siguienteColumnaRecomendada;
    private ArrayList<Ficha> listaFichas;
    private Iterator<Ficha> iteradorFichas;
    
    public Saltar(boolean configuracionPredeterminada) {
        super(configuracionPredeterminada);
        this.setTablero(new TableroSaltar());
        this.setListaFichas(new ArrayList<Ficha>());
        this.addListaFichas(new Ficha(1,"#"));
        this.addListaFichas(new Ficha(2,"#"));
        this.addListaFichas(new Ficha(3,"#"));
        this.addListaFichas(new Ficha(4,"#"));
        iteradorFichas = this.getListaFichas().iterator();
    }

    public Ficha getFichaActual() {
        return fichaActual;
    }

    public void setFichaActual(Ficha fichaActual) {
        this.fichaActual = fichaActual;
    }

    public int getSiguienteColumnaRecomendada() {
        return siguienteColumnaRecomendada;
    }

    private void setSiguienteColumnaRecomendada(int siguienteColumnaRecomendada) {
        this.siguienteColumnaRecomendada = siguienteColumnaRecomendada;
    }

    private ArrayList<Ficha> getListaFichas() {
        return listaFichas;
    }
   
    private void setListaFichas(ArrayList<Ficha> listaFichas) {
        this.listaFichas = listaFichas;
    }
    
    private void addListaFichas(Ficha ficha) {
        this.listaFichas.add(ficha);
    }

    private Iterator<Ficha> getIteradorFichas() {
        return iteradorFichas;
    }
    
    private void resetIteradorFichas(Iterator<Ficha> iteradorFichas) {
        this.iteradorFichas = this.getListaFichas().iterator();
    }
    
    public void iniciar(){
        if(!this.getConfiguracionPredeterminada()){
            this.getTablero().generarFichasAlAzar();
        }
        this.recargar();
    }
    
    public void recargar(){
        this.getTablero().generarTablero();
        this.siguienteFicha();
        if(this.getFichaActual() != null){
            this.setMovimientosDisponibles(this.comprobarFichasAreaBase());
            if(this.getMovimientosDisponibles()){
                this.setMovimientosDisponibles(this.obtenerMovimientosDisponibles());
            }
        }
        this.calcularPuntaje();
    }
    
    public void siguienteFicha(){
        if(!this.getIteradorFichas().hasNext()){
            this.resetIteradorFichas(iteradorFichas);
        }
        this.setFichaActual(this.getIteradorFichas().next());
    }
    
    public boolean siguienteMovimiento(int colSeleccionada){
        boolean movimientoPermitido = false;
        Ficha [][] tableroFichas = this.getTablero().getFichas();
        if(colSeleccionada >= 0 && colSeleccionada <= 3 ){
            int filaOrigen = 0;
            int cantidadSaltos = 0;
            int filaDestino = 0;
            for (int fila = tableroFichas.length -1; fila > 0 ; fila--) {
                // -- Si se encuentra el color en la columna, se verifica cuantas veces hay que saltar
                if(tableroFichas[fila][colSeleccionada].equals(this.getFichaActual())){
                    filaOrigen = fila;
                    for (int columna = 0; columna < tableroFichas[0].length; columna++) {
                        if(!tableroFichas[fila][columna].equals(utilidad.Generico.FichaVacia)){
                            cantidadSaltos ++;
                        }                    
                    }
                }
            }
            filaDestino = filaOrigen - cantidadSaltos;
            if(filaDestino > 0 && filaOrigen > 4  && tableroFichas[filaDestino][colSeleccionada].equals(utilidad.Generico.FichaVacia) ){
                movimientoPermitido = true;
                for (int columna = 0; columna < tableroFichas[filaDestino].length && movimientoPermitido; columna++) {
                    if(tableroFichas[filaDestino][columna] == this.getFichaActual()){
                        movimientoPermitido = false;
                    }
                }
                if(movimientoPermitido){
                    tableroFichas[filaOrigen][colSeleccionada] = utilidad.Generico.FichaVacia;
                    tableroFichas[filaDestino][colSeleccionada] = this.getFichaActual();
                }
            }else{
                movimientoPermitido = false;
            }
        }
        this.getTablero().setFichas(tableroFichas);
        return movimientoPermitido;
    }
    
    public int obtenerColumnaRecomendada(Ficha ficha){
        Ficha[][] matrizFichas = this.getTablero().getFichas();
        int columnaRecomendada = -1;
        int cantidadSaltos;
        int filaDestino;
        boolean fichaValida = true;
        for (int fila = 5; fila < matrizFichas.length && columnaRecomendada == -1; fila++) {
          
            for (int columna = 0; columna < matrizFichas[0].length; columna++) {
                fichaValida = true;
                cantidadSaltos = 0;
                if(matrizFichas[fila][columna].equals(ficha)){
                    for (int columnaFicha = 0; columnaFicha < matrizFichas[fila].length; columnaFicha++) {
                        if(!matrizFichas[fila][columnaFicha].equals(utilidad.Generico.FichaVacia)){
                            cantidadSaltos ++;
                        }
                    }
                    filaDestino = fila - cantidadSaltos;
                    if(filaDestino > 0 && matrizFichas[filaDestino][columna].equals(utilidad.Generico.FichaVacia) ){
                        for (int columnaDestino = 0; columnaDestino < matrizFichas[0].length && fichaValida; columnaDestino++) {
                            if(matrizFichas[filaDestino][columnaDestino].toString().equals(ficha.toString())){
                                fichaValida = false;
                            }
                        }
                    }else{
                        fichaValida = false;
                    }
                }else{
                    fichaValida = false;
                }
                if(fichaValida){
                    columnaRecomendada = columna;
                }
            }
        }
        return columnaRecomendada;
    }
    
    private boolean comprobarFichasAreaBase(){
        int contador = 0;
        for (int fila = 5; fila < this.getTablero().getFichas().length; fila++) {
            for (int columna = 0; columna < this.getTablero().getFichas()[0].length; columna++) {
                if(!this.getTablero().getFichas()[fila][columna].equals(utilidad.Generico.FichaVacia)){
                    contador ++;
                }
            }
        }
        return contador > 2;
    }
    
    public void calcularPuntaje(){
        Ficha [][] tableroFichas = this.getTablero().getFichas();
        int puntaje = 0;
        for (int fila = 0; fila <  tableroFichas.length && fila <= 4; fila++) {
            for (int columna = 0; columna <  tableroFichas[0].length; columna++) {
                if(!tableroFichas[fila][columna].equals(utilidad.Generico.FichaVacia)){
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
        this.setPuntaje(puntaje);
    }

    @Override
    public boolean obtenerMovimientosDisponibles() {
        this.setSiguienteColumnaRecomendada(obtenerColumnaRecomendada(this.getFichaActual()));
        return this.getSiguienteColumnaRecomendada()  != -1;
    }
}
