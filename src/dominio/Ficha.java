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
        return color + texto;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }
    
    @Override
    public boolean equals(Object obj) {
        return this.getNombre().equalsIgnoreCase(((Ficha) obj).getNombre());
    }

    private void asignaColor() {
        this.color = utilidad.Generico.COLORES[idColor];
    }
}
