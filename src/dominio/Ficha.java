package dominio;

/**
 * Clase que contiene la la estructura para instanciar una Ficha nueva
 * @author  Rodrigo Pistón(261777) | Rodrigo Camps(241344)
 * 
 */
public class Ficha {
    private int idColor;
    private String texto;
    private String color;

    /**
     *
     * @param idColor: <pre> Código de color de la ficha: 
     *      0 - Sin Color 
     *      1 - Rojo 
     *      2 - Azul 
     *      3 - Verde 
     *      4 - Amarillo </pre>
     * @param texto: Texto que representa la ficha
     */
    public Ficha(int idColor, String texto) {
        this.idColor = idColor;
        this.texto = texto;
        this.asignaColor();
    }

    public String getNombre() {
        return texto;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    private void asignaColor() {
        this.color = utilidad.Generico.Colores[idColor];
    }
    
    /**
     * Comprueba si la ficha es un númeral #
     * @return true | false
     */
    public boolean esNumeral() {
        return this.texto.equals("#");
    }
    
    @Override
    public boolean equals(Object obj) {
        return this.getNombre().equals(((Ficha) obj).getNombre()) && this.getIdColor() == ((Ficha) obj).getIdColor();
    }
    
    @Override
    public String toString() {
        return color + texto + "\u001B[0m";
    }
}
