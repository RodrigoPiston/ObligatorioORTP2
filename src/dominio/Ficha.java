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
    private int id;
    private String nombre;
    private String color;

    public Ficha(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.asignaColor();
    }

    public String getNombre() {
        return nombre;
    }

    public String getFichaColoreada() {
        return color + nombre;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getNombre().equalsIgnoreCase(((Ficha) obj).getNombre());
    }

    private void asignaColor() {
        this.color = utilidad.Generico.COLORES[id];
    }
}
