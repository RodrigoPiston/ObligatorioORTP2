/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import dominio.Sistema;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Rodrigo Pistón
 */
public class MenuPrincipal {
    
    public static Sistema sistema = new Sistema();
    
    public void mostrarOpciones(){
        System.out.println("[1] Registrar Jugador");
        System.out.println("[2] Jugar a Saltar");
        System.out.println("[3] Jugar a Rectangulo");
        System.out.println("[4] Bitácora");
        System.out.println("[5] Salir");
    }
    
    public void mostrarMenu(){
        Scanner input = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        while (!salir) {
            mostrarOpciones();
            try {
                System.out.println("Escribe una de las opciones");
                opcion = input.nextInt();
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
                        System.out.println("Has seleccionado la opcion 4");
                        break;
                    case 5:
                        System.out.println("Fin de la ejecución");
                        salir = true;
                        break;
                    default:
                        System.out.println("Debes seleccionar solo números entre 1 y 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                input.next();
            }
        }
    }
}
