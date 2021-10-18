/*
 */
package dominio;
import java.time.LocalDateTime; 

/**
 *
 * @author Rodrigo Pistón
 */
public class Saltar extends Juego{

  //  private String [][] matrizInterfaz = new String[24][10];
    /// -- Fichas predetermiandas
   // private int [][] tableroFichasBase = {{1,2,3,4},{2,1,4,3},{3,4,2,1},{4,3,1,2}};
  //  private int [][] tableroFichas = new int [11][4];
    
    public Saltar(boolean configuracionPredeterminada) {
        super(configuracionPredeterminada);
        this.setTablero(new TableroSaltar());
    }

    public void iniciar(){
        if(!this.getConfiguracionPredeterminada()){
            this.getTablero().generarFichasAlAzar();
        }
        this.recargar();
    }
    
    public void recargar(){
        this.getTablero().generarTablero();
    }

    public boolean siguienteMovimiento(Ficha fichaActual,int colSeleccionada){
        boolean movimientoPermitido = false;
        Ficha fichaVacia = new Ficha(0," ");
        Ficha [][] tableroFichas = this.getTablero().getFichas();
        if(colSeleccionada >= 0 && colSeleccionada <= 3 ){
            int filaOrigen = 0;
            int cantidadSaltos = 0;
            int filaDestino = 0;
            for (int fila = tableroFichas.length -1; fila > 0 ; fila--) {
                // -- Si se encuentra el color en la columna, se verifica cuantas veces hay que saltar
                if(tableroFichas[fila][colSeleccionada].equals(fichaActual)){
                    filaOrigen = fila;
                    for (int columna = 0; columna < tableroFichas[0].length; columna++) {
                        if(!tableroFichas[fila][columna].equals(fichaVacia)){
                            cantidadSaltos ++;
                        }                    
                    }
                }
            }
            filaDestino = filaOrigen - cantidadSaltos;
            if(filaDestino > 0 && filaOrigen > 4  && tableroFichas[filaDestino][colSeleccionada].equals(fichaVacia) ){
                movimientoPermitido = true;
                for (int columna = 0; columna < tableroFichas[filaDestino].length && movimientoPermitido; columna++) {
                    if(tableroFichas[filaDestino][columna] == fichaActual){
                        movimientoPermitido = false;
                    }
                }
                if(movimientoPermitido){
                    tableroFichas[filaOrigen][colSeleccionada] = fichaVacia;
                    tableroFichas[filaDestino][colSeleccionada] = fichaActual;
                }
            }else{
                movimientoPermitido = false;
            }
        }
        this.getTablero().setFichas(tableroFichas);
        return movimientoPermitido;
    }
    
    public int calcularPuntaje(){
        Ficha [][] tableroFichas = this.getTablero().getFichas();
        Ficha fichaVacia = new Ficha(0," ");
        int puntaje = 0;
        for (int fila = 0; fila <  tableroFichas.length && fila <= 4; fila++) {
            for (int columna = 0; columna <  tableroFichas[0].length; columna++) {
                if(!tableroFichas[fila][columna].equals(fichaVacia)){
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
