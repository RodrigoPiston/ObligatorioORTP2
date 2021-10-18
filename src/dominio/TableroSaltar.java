/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author heido
 */
public class TableroSaltar extends Tablero{
    private static String [][] anexos = {{"60","40","30","20","10"},{},{""},{"1","2","3","4"}};
    private static int ancho = 11;
    private static int largo = 4;

    public TableroSaltar() {
        super(ancho, largo,anexos);
        this.generarFichasPredeterminadas();
    }

    @Override
    public void generarTablero() {
        String [][] contenidoTablero = new String[(super.getLargo() * 2) + 2][(super.getAncho()* 2) + 2];
        int puntuacion = 60;        
        int posicion = 1;
        int filaTableroFichas = 0;
        int columnaTableroFichas = 0;
        boolean filaPar = false;
        boolean columnaPar = false;
        for (int fila = 0; fila < contenidoTablero.length ; fila++) {
            filaPar = (fila  + 1)% 2 == 0;
            for (int columna = 0; columna < contenidoTablero[0].length ; columna++) {
                columnaPar = (columna + 2) % 2 == 0;
                if(columna == 0 ){
                    if(filaPar && puntuacion > 0){
                        contenidoTablero[fila][columna] = Integer.toString(puntuacion)+ " " ;
                        if(puntuacion == 60){
                            puntuacion -= 20;
                        }else{
                            puntuacion -= 10;
                        }
                    }else{
                        contenidoTablero[fila][columna] = "   ";
                    }
                }else if(fila == contenidoTablero.length - 1){
                    if(columnaPar && posicion <= 4){
                       contenidoTablero[fila][columna] = Integer.toString(posicion);
                        posicion ++;
                    }else{
                        contenidoTablero[fila][columna] = " ";
                    }
                }
                else if(filaPar){
                    if(!columnaPar){
                        contenidoTablero[fila][columna] = "|"; 
                    }else{
                        if(this.getFichas()[filaTableroFichas][columnaTableroFichas]!= null){
                            contenidoTablero[fila][columna] = this.getFichas()[filaTableroFichas][columnaTableroFichas].getFichaColoreada(); 
                        }
                        columnaTableroFichas ++;
                    }
                }
                else if(columnaPar){
                    contenidoTablero[fila][columna] = "-";
                }else if(!columnaPar) {
                    contenidoTablero[fila][columna] = "+"; 
                }
            }
            if(filaPar){
                filaTableroFichas ++;
                columnaTableroFichas = 0;
            }
        }
        super.setContenido(contenidoTablero);
    }

    @Override
    public void generarFichasAlAzar() {
        // -- Valores entre el 1 y el 4
        int offset = (int) (Math.random()*(4-0)) + 1;
        Ficha ficha;
        /// -- Una mejor forma es revisar la posición de la columna anterior, si coincide, no se pone,y se pone en la siguiente. 
        for (int fila = 0; fila < super.getFichas().length ; fila++) {
            for (int columna = 0; columna < super.getFichas()[0].length ; columna++) {
                ficha = super.getFichas()[fila][columna];
                
                if(!ficha.equals(new Ficha(0," "))){
                    int valorActual = ficha.getIdColor() + offset;
                    if(valorActual > 4){
                        int diferencia = (valorActual - 4);
                        ficha.setIdColor(diferencia);
                    }else{
                        ficha.setIdColor(valorActual);
                    }
                    
                    super.agregarFicha(ficha, fila, columna);
                }
            }
        }
    }

    @Override
    public void generarFichasPredeterminadas() {
        Ficha fRoja = new Ficha(1,"#");
        Ficha fAzul = new Ficha(2,"#");
        Ficha fVerde = new Ficha(3,"#");
        Ficha fAma = new Ficha(4,"#");
        Ficha fVacia = new Ficha(0," ");

        Ficha [][] listaFichas = {{fRoja,fAzul,fVerde,fAma},{fAzul,fRoja,fAma,fVerde},{fVerde,fAma,fAzul,fRoja},{fAma,fVerde,fRoja,fAzul}};
        
        for (int fila = 0; fila < this.getFichas().length; fila++) {
            for (int columna = 0; columna < this.getFichas()[0].length; columna++) {
                if(fila > 6){
                    this.agregarFicha(listaFichas[fila - 7][columna], fila, columna);
                }else{
                    this.agregarFicha(fVacia, fila, columna);
                }
            }
        }
    }
    
}
