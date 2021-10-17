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
public class Tablero {
    private int ancho;
    private int largo;
    private String[][] contenido;
    private Ficha [][] fichas;

    public Tablero(int largo, int ancho) {
        this.largo = largo;
        this.ancho = ancho;
    }

    public String[][] getContenido() {
        return contenido;
    }
    
    public String getStringContenido() {
        String contenido = "";
        for (int fila = 0; fila < this.getContenido().length; fila++) {
            contenido += String.join("\\s", this.getContenido()[fila]);
        }
        return contenido;
    }

    public void setContenido(String[][] contenido) {
        this.contenido = contenido;
    }
    
    public void agegarElementoContenido(String elemento,int x,int y) {
        this.contenido[x][y] = elemento;
    }
    
    public Ficha[][] getFichas() {
        return fichas;
    }

    public void setFichas(Ficha[][] fichas) {
        this.fichas = fichas;
    }
    
    public void agregarFicha(Ficha ficha,int x,int y) {
        this.fichas[x][y] = ficha;
    }
    
    private void generarTablero(){
        String [][] contenidoTablero = new String[ancho][largo];
    }
    
    private void generarFichasAlAzarSaltar(){
        for (int fila = 0; fila < this.getFichas().length; fila++) {
            for (int columna = 0; columna < this.getFichas()[fila].length; columna++) {
                if(utilidad.Entrada.getRandom(0,20) == 0){
                    this.agregarFicha(new Ficha(6,"#"),fila,columna);
                }else{
                    this.agregarFicha(new Ficha(5,"-"),fila,columna);
                }
            }
        }
    }
}
