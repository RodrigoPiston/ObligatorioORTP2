package dominio;

/**
 *
 * @author  Rodrigo Pistón(261777) | Rodrigo Camps(241344)
 */
public class Ficha {
    private int idColor;
    private String texto;
    private String color;

    public Ficha(int id, String nombre) {
        this.idColor = id;
        this.texto = nombre;
        this.asignaColor();
    }

    public String getNombre() {
        return texto;
    }

    public String getFichaColoreada() {
        return color + texto + "\u001B[0m";
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }
    
    @Override
    public boolean equals(Object obj) {
        return this.getNombre().equals(((Ficha) obj).getNombre()) && this.getIdColor() == ((Ficha) obj).getIdColor();
    }

    private void asignaColor() {
        this.color = utilidad.Generico.Colores[idColor];
    }
    
    public boolean esNumeral() {
        return this.texto.equals("#");
    }
}
