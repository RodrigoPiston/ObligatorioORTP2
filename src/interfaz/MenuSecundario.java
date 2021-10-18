/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import dominio.Ficha;
import utilidad.Entrada;
import dominio.Juego;
import dominio.Jugador;
import dominio.Partida;
import dominio.Rectangulo;
import dominio.Saltar;
import java.util.InputMismatchException;
import java.util.List;
import static utilidad.Entrada.esNumero;

/**
 *
 * @author heido
 */
public class MenuSecundario {
    
    public static void RegistrarJugador(){
        
        try{
            String nombre = Entrada.leerString("nombre.");
            String alias = Entrada.leerString("alias.");
            int edad = Entrada.leerInt(" edad",0,99);
            
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

    private static Jugador SeleccionarJugador(){
        
        System.out.println("Seleccione un jugador:");
        List<Jugador> listaJugadores = MenuPrincipal.sistema.getListaJugadores();
        for(int posicion = 0; posicion < listaJugadores.size(); posicion ++ ){
            System.out.printf("[%d] %s\n",(posicion + 1), listaJugadores.get(posicion).getNombre());
        }

        int posicionSeleccioanda = Entrada.leerInt(" ",0,99);
        return listaJugadores.get(posicionSeleccioanda - 1);
    }
    
    /**
     *
     * @return True:Configuración Predeterminada, False:Configuración al Azar
    */
    private static boolean SeleccionarConfiguracion(){
        // -- Se selecciona la configuración
        System.out.println("Seleccione configuracion: \n\t-[1]Predetermianda\n\t-[2]Azar");
        int ingresoConfiguracion = Entrada.leerInt(" 1 o 2",0,3);
        return ingresoConfiguracion == 1;
    }
    
        
    public static void CrearPartida(int opcion){
        // -- Se selecciona el jugador
        Jugador jugadorSeleccionado = SeleccionarJugador();
        Juego nuevoJuego;
        
        switch(opcion){
            case 2:
                nuevoJuego = jugarSaltar();
                break;
            case 3:
                nuevoJuego = jugarRectangulo();
                break;
            default: 
                System.out.println("Ningun juego seleccionado.");
                return;
        }
        // -- Se crea la nueva partida
        Partida partida = new Partida(jugadorSeleccionado,nuevoJuego);
    }
    
    private static Juego jugarRectangulo (){
        Rectangulo instanciaRectangulo = new Rectangulo(SeleccionarConfiguracion());
        instanciaRectangulo.iniciar();
        String [] arrayEntrada = {"","","",""};        
        String entrada = "";
        Ficha[] listaFichas = {new Ficha(1,"#"),new Ficha(2,"#"),new Ficha(3,"#"),new Ficha(4,"#")};
        Ficha ficha;
        int contListaFichas = 0;
        int color = 1;
        int rectanguloPosicionX = 0;
        int rectanguloPosicionY = 0;
        int rectanguloAlto = 0;
        int rectanguloAncho = 0;
        int cantidadRectangulos = 0;
        boolean primeraJugada = true;
        boolean terminaJuego = false;
        while(terminaJuego){
            ficha = listaFichas[contListaFichas];
            System.out.println(instanciaRectangulo.getTablero().getContenidoString());
            System.out.println("\n");
            // -- Se lee la entrada con una función utilitaria
            entrada = utilidad.Entrada.leerString("los siguientes valores [Posición X] [Posición Y] [Alto] [Ancho]\nen una sola línea númerica o presiones X para finalizar:");
           
            // -- Se convierte la entrada en un array con split, se utiliza expresiones regulares para separar por espacios o tabuladores, 
            //    para imperdir que tome mal la entrada con muchos espacios
            arrayEntrada = entrada.trim().split("\\s+\\t|\\t\\s+|\\s+");
            // -- Para cortar un poco el rastro en pantalla y que parezca que refresca las mismas posiciones
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
            try
            {
                if(arrayEntrada.length == 4){
                    rectanguloPosicionX = Integer.parseInt(arrayEntrada[0]); 
                    rectanguloPosicionY = Integer.parseInt(arrayEntrada[1]); 
                    rectanguloAlto = Integer.parseInt(arrayEntrada[2]); 
                    rectanguloAncho = Integer.parseInt(arrayEntrada[3]);
                    if(!instanciaRectangulo.siguienteMovimiento(ficha,rectanguloPosicionX,rectanguloPosicionY,rectanguloAlto,rectanguloAncho,primeraJugada)){
                        System.out.println("Error: Movimiento invalido.");
                    }else{
                        cantidadRectangulos ++;
                        primeraJugada = false;
                    };
                    
                    // -- Solo se cambia de color cuando es correcta la entrada
                    if(contListaFichas == 3){
                        contListaFichas = 1;
                    }else{
                        contListaFichas ++;
                    }
                }else if(!arrayEntrada[0].equals("X"))
                {
                    System.out.println("\nError: Se ingresaron datos invalidos, debe ingresar al menos 4 valores");
                }
                
            }catch(Exception e){
                System.out.println("\nError: Se ingresaron datos invalidos, solo puede ingresar números");
            }
            instanciaRectangulo.recargar();
            System.out.printf("El puntaje es: %d \n",instanciaRectangulo.calcularPuntaje());
            if(arrayEntrada[0].equals("X") || arrayEntrada[0].equals("x") || cantidadRectangulos <= 10 || instanciaRectangulo.quedanRectangulosDisponibles()){
                terminaJuego = true;
                System.out.println("Fin del juego!");
            }
        }
        return instanciaRectangulo;
    }

    private static Saltar jugarSaltar(){
        String entrada = "";
        Ficha[] listaFichas = {new Ficha(1,"#"),new Ficha(2,"#"),new Ficha(3,"#"),new Ficha(4,"#")};
        int contListaFichas = 0;
        int puntaje = 0;
        int columnaIngresada = 0;
        boolean terminaJuego = false;
        Ficha ficha;
        Saltar instanciaSaltar = new Saltar(SeleccionarConfiguracion());
        instanciaSaltar.iniciar();
        
        // -- Se itera hasta que se presione X
        while(!terminaJuego){
            ficha = listaFichas[contListaFichas];

            // -- Se muestra puntaje en pantalla
            System.out.printf("\nPuntaje: %d",puntaje);
            
            System.out.println(instanciaSaltar.getTablero().getContenidoString());
            // -- Se indica que ficha va a ser la siguiente en mover
            System.out.printf("\nSiguiente color de ficha a mover: %s \n",ficha.getFichaColoreada());
            // -- Se lee la entrada del usuario
            entrada = utilidad.Entrada.leerString("columna o X para finalizar");
            System.out.println("\n\n\n\n\n\n");
            
            if(esNumero(entrada)){
                columnaIngresada = Integer.parseInt(entrada) - 1;
                if(!instanciaSaltar.siguienteMovimiento(ficha,columnaIngresada)){
                    System.out.println("Error: Movimiento invalido.");
                };
            }else if(!entrada.equals("X") && !entrada.equals("x")){
                System.out.println("Debe de ingresar una columna para realizar la siguiente jugada o X para finalizar");
            }
            
            if(contListaFichas == 3){
                contListaFichas = 1;
            }else{
                contListaFichas ++;
            }
            instanciaSaltar.recargar();
            if(entrada.equals("X") || entrada.equals("x")){
                terminaJuego = true;
                System.out.println("Fin del juego!");
            }
        }
        return instanciaSaltar;
    }
    
}
