package interfaz;

import dominio.Sistema;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author  Rodrigo Pistón(261777) | Rodrigo Camps(241344)
 */
public class MenuPrincipal {
    
    public static Sistema Sistema = new Sistema();
    
    public void mostrarOpciones(){
        System.out.println("_________________________");
        System.out.println("| [1] Registrar Jugador |");
        System.out.println("| [2] Jugar a Saltar    |");
        System.out.println("| [3] Jugar a Rectangulo|");
        System.out.println("| [4] Bitácora          |");   
        System.out.println("| [5] Salir             |");
        System.out.println("________________________");
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
                        MenuSecundario.registrarJugador();
                        break;
                    case 2: case 3:
                        if(this.Sistema.getListaJugadores().size() == 0){
                            System.out.println("Debe de ingresar un jugador primero para elegir cualquier juego.");
                        }else{
                            MenuSecundario.crearPartida(opcion);
                        }
                        break;
                    case 4:
                        if(this.Sistema.getListaPartidas().size() == 0){
                            System.out.println("No hay partidas registradas en la bitacora.");
                        }else{
                            MenuSecundario.mostrarPartidas();
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
                System.out.println("Debes de ingresar un número");
            }
        }
    }
}
