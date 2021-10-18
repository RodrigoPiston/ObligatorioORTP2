package dominio;

/**
 *
 * @author  Rodrigo Pistón(261777) | Rodrigo Camps(241344)
 */
public abstract class Tablero {
    private int ancho;
    private int largo;
    private String[][] contenido;
    private Ficha [][] fichas;
    
    /*
        En el anexo se encuentra las partes laterales del tablero, como los puntos y número de columna
    */
    private String [][] anexos = new String[4][1]; 

    public Tablero(int largo, int ancho,String[][] anexos) {
        this.largo = largo;
        this.ancho = ancho;
        this.anexos = anexos;
        this.fichas = new Ficha[largo][ancho];
    }

    private String[][] getContenido() {
        return contenido;
    }

    public String[][] getAnexos() {
        return anexos;
    }
    
    public Ficha[][] getFichas() {
        return fichas;
    }
    
    public int getAncho() {
        return ancho;
    }

    public int getLargo() {
        return largo;
    }
    
    public void agregarFicha(Ficha ficha,int fila,int columna) {
        this.fichas[fila][columna] = ficha;
    }
    
    public String getContenidoString() {
        String contenido = "\n";
        for (int fila = 0; fila < this.getContenido().length; fila++) {
            contenido += String.join(" ", this.getContenido()[fila]) + "\n";
        }
        return contenido;
    }

    public void setContenido(String[][] contenido) {
        this.contenido = contenido;
    }
    
    public void agegarElementoContenido(String elemento,int x,int y) {
        this.contenido[x][y] = elemento;
    }
    
    public void setFichas(Ficha[][] fichas) {
        this.fichas = fichas;
    }
    
    public abstract void generarTablero();
    public abstract void generarFichasAlAzar();
    public abstract void generarFichasPredeterminadas();

}
