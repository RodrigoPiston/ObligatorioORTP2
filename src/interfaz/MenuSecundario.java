/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import utilidad.Entrada;
import dominio.Juego;
import dominio.Jugador;
import dominio.Partida;
import dominio.Rectangulo;
import dominio.Saltar;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author heido
 */
public class MenuSecundario {
    
    public static void RegistrarJugador(){
        
        try{
            String nombre = Entrada.LeerString("nombre.");
            String alias = Entrada.LeerString("alias.");
            int edad = Entrada.LeerInt(" edad",0,99);
            
            // -- Hacer las validaciones al input al nombre
            Jugador jugador = new Jugador(nombre,edad,alias);
            boolean jugadorAgregado = MenuPrincipal.sistema.agregarJugador(jugador);
            if(jugadorAgregado){
                System.out.println("Jugador agregado correctamente!");
            }else{
                System.out.println("El jugador ya existe en el sistema.");
            }
            
        }catch(InputMismatchException e){
            System.out.println("Error al agregar al jugador");
        }
    }
    
    public static void CrearPartida(int opcion){
        // -- Se selecciona el jugador
        Jugador jugadorSeleccionado = SeleccionarJugador();
        Juego nuevoJuego;
        switch(opcion){
            case 2:
                nuevoJuego = new Saltar(SeleccionarConfiguracion());
                break;
            case 3:
                nuevoJuego = new Rectangulo(SeleccionarConfiguracion());
                break;
            default: 
                System.out.println("Ningun juego seleccionado.");
                return;
        }
        // -- Se crea la nueva partida
        Partida nuevaPartida = new Partida(jugadorSeleccionado,nuevoJuego);
        nuevaPartida.getJuego().iniciar();
    }
    
    private static Jugador SeleccionarJugador(){
        
        System.out.println("Seleccione un jugador:");
        List<Jugador> listaJugadores = MenuPrincipal.sistema.getListaJugadores();
        for(int posicion = 0; posicion < listaJugadores.size(); posicion ++ ){
            System.out.printf("[%d] %s\n",(posicion + 1), listaJugadores.get(posicion).getNombre());
        }

        int posicionSeleccioanda = Entrada.LeerInt(" ",0,99);
        return listaJugadores.get(posicionSeleccioanda - 1);
    }
    
    /**
     *
     * @return True:Configuración Predeterminada, False:Configuración al Azar
    */
    private static boolean SeleccionarConfiguracion(){
        // -- Se selecciona la configuración
        System.out.println("Seleccione configuracion: \n\t-[1]Predetermianda\n\t-[2]Azar");
        int ingresoConfiguracion = Entrada.LeerInt(" 1 o 2",0,3);
        return ingresoConfiguracion == 1;
    }
}
