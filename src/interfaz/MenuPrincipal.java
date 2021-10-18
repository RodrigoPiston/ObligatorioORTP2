package interfaz;

import dominio.Sistema;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author  Rodrigo Pistón(261777) | Rodrigo Camps(241344)
 */
public class MenuPrincipal {
    
    public static Sistema sistema = new Sistema();
    
    public void mostrarOpciones(){
        System.out.println("_________________________");
        System.out.println("| [1] Registrar Jugador |");
        System.out.println("| [2] Jugar a Saltar    |");
        System.out.println("| [3] Jugar a Rectangulo|");
        System.out.println("| [4] Bitácora          |");   
        System.out.println("| [5] Salir             |");
        System.out.println("________________________");

    }
    
    public void mostrarOpcionesBitacora(){
        System.out.println("______________________________");
        System.out.println(" Seleccione orden de vista:   ");
        System.out.println("| [1] Por alias creciente     |");
        System.out.println("| [2] Por alias decreciente   |");
        System.out.println("| [3] Por puntaje creciente   |");
        System.out.println("| [4] Por puntaje decreciente |");   
        System.out.println("| [5] Volver                  |");
        System.out.println("______________________________");
    }
    
    public void mostrarMenu(){
        boolean salir = false;
        int opcion;
        while (!salir) {
            mostrarOpciones();
            try {
                opcion = utilidad.Entrada.leerInt("una de las opciones", 0, 5);
                switch (opcion) {
                    case 1:
                        MenuSecundario.RegistrarJugador();
                        break;
                    case 2: case 3:
                        if(this.sistema.getListaJugadores().size() == 0){
                            System.out.println("Debe de ingresar un jugador primero para elegir cualquier juego.");
                        }else{
                            MenuSecundario.CrearPartida(opcion);
                        }
                        break;
                    case 4:
                        if(this.sistema.getListaPartidas().size() == 0){
                            System.out.println("No hay partidas registradas en la bitacora.");
                        }else{
                            mostrarOpcionesBitacora();
                            opcion = utilidad.Entrada.leerInt("una de las opciones", 0, 5);
                            if(opcion != 5){
                                MenuSecundario.MostrarBitacora(opcion);
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Fin de la ejecución");
                        salir = true;
                        break;
                    default:
                        System.out.println("Debes seleccionar solo números entre 1 y 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes de ingrasar un número");
            }
        }
    }
}
