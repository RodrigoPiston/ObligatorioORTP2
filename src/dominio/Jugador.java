/*
 */
package dominio;

/**
 *
 * @author  Rodrigo Pistón(261777) | Rodrigo Camps(241344)
 */
public class Jugador {
    private String nombre;
    private int edad;
    private String alias;

    public Jugador(String nombre, int edad, String alias) {
        this.setNombre(nombre);
        this.setEdad(edad);
        this.setAlias(alias);
    }

    public String getNombre() {
        return this.nombre;
    }
    
    public String getAlias() {
        return this.alias;
    }

    public int getEdad() {
        return this.edad;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    @Override
    public boolean equals(Object obj){
        return this.getAlias().equalsIgnoreCase(((Jugador) obj).getAlias());
    }

    
}
