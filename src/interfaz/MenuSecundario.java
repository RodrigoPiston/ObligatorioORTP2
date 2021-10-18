package interfaz;

import dominio.Ficha;
import utilidad.Entrada;
import dominio.Juego;
import dominio.Jugador;
import dominio.Partida;
import dominio.Rectangulo;
import dominio.Saltar;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import static utilidad.Entrada.esNumero;

/**
 *
 * @author  Rodrigo Pistón(261777) | Rodrigo Camps(241344)
 */
public class MenuSecundario {

    public static void registrarJugador() {

        try {
            String nombre = Entrada.leerString("nombre.");
            String alias = Entrada.leerString("alias.");
            int edad = Entrada.leerInt(" edad", 0, 99);

            // -- Hacer las validaciones al input al nombre
            Jugador jugador = new Jugador(nombre, edad, alias);
            boolean jugadorAgregado = MenuPrincipal.Sistema.agregarJugador(jugador);
            if (jugadorAgregado) {
                System.out.println("Jugador agregado correctamente!");
            } else {
                System.out.println("El jugador ya existe en el sistema.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error al agregar al jugador");
        }
    }

    private static Jugador seleccionarJugador() {

        System.out.println("Seleccione un jugador:");
        List < Jugador > listaJugadores = MenuPrincipal.Sistema.getListaJugadores();
        for (int posicion = 0; posicion < listaJugadores.size(); posicion++) {
            System.out.printf("[%d] %s\n", (posicion + 1), listaJugadores.get(posicion).getNombre());
        }

        int posicionSeleccioanda = Entrada.leerInt("jugador", 0, listaJugadores.size() + 1);
        Jugador jugador = listaJugadores.get(posicionSeleccioanda - 1);

        return jugador;
    }

    /**
     *
     * @return True:Configuración Predeterminada, False:Configuración al Azar
     */
    private static boolean seleccionarConfiguracion() {
        // -- Se selecciona la configuración
        System.out.println("Seleccione configuracion: \n\t-[1]Predetermianda\n\t-[2]Azar");
        int ingresoConfiguracion = Entrada.leerInt(" opción", 0, 3);
        return ingresoConfiguracion == 1;
    }

    public static void crearPartida(int opcion) {
        // -- Se selecciona el jugador
        Jugador jugadorSeleccionado = seleccionarJugador();
        Juego nuevoJuego;

        switch (opcion) {
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
        Partida partida = new Partida(jugadorSeleccionado, nuevoJuego, nuevoJuego.getPuntaje());

        MenuPrincipal.Sistema.agregarPartida(partida);
    }

    private static Juego jugarRectangulo() {
        Rectangulo juegoRectangulo = new Rectangulo(seleccionarConfiguracion());
        Ficha[] listaFichas = {new Ficha(1, "#"),new Ficha(2, "#"),new Ficha(3, "#"),new Ficha(4, "#")};
        String[] arrayEntrada = {"","","",""};
        String entrada = "";
        Ficha ficha;
        int contListaFichas = 0;
        int rectanguloPosicionX = 0;
        int rectanguloPosicionY = 0;
        int rectanguloAlto = 0;
        int rectanguloAncho = 0;
        int cantidadRectangulos = 0;
        boolean primeraJugada = true;
        boolean terminaJuego = false;
        juegoRectangulo.iniciar();
        while (!terminaJuego) {
            // -- Para cortar un poco el rastro en pantalla y que parezca que refresca las mismas posiciones
         
            // -- Se va alternando entre fichas
            ficha = listaFichas[contListaFichas];
            // -- Se imprime el tablero a pantalla
            System.out.println(juegoRectangulo.getTablero().getContenidoString() + "\n");
            // -- Se lee la entrada con una función utilitaria
            entrada = utilidad.Entrada.leerString("los siguientes valores [Posición X] [Posición Y] [Alto] [Ancho]\nen una sola línea númerica o presiones X para finalizar:");
            // -- Se convierte la entrada en un array con split, se utiliza expresiones regulares para separar por espacios o tabuladores, 
            //    para imperdir que tome mal la entrada con muchos espacios
            arrayEntrada = entrada.trim().split("\\s+\\t|\\t\\s+|\\s+");
            try {
                if (arrayEntrada.length == 4) {
                    rectanguloPosicionX = Integer.parseInt(arrayEntrada[0]);
                    rectanguloPosicionY = Integer.parseInt(arrayEntrada[1]);
                    rectanguloAlto = Integer.parseInt(arrayEntrada[2]);
                    rectanguloAncho = Integer.parseInt(arrayEntrada[3]);
                    System.out.println("____________________________________________________________________________");
                    // -- Se calcula el siguiente movimiento
                    if (!juegoRectangulo.siguienteMovimiento(ficha, rectanguloPosicionX, rectanguloPosicionY, rectanguloAlto, rectanguloAncho, primeraJugada)) {
                        
                        System.out.println("Error: Movimiento ingresado invalido, se saltea el color.");
                    } else {
                        cantidadRectangulos++;
                        primeraJugada = false;
                    };

                    // -- Solo se cambia de color cuando es correcta la entrada
                    if (contListaFichas == 3) {
                        contListaFichas = 1;
                    } else {
                        contListaFichas++;
                    }
                } else if (!arrayEntrada[0].toUpperCase().equals("X")) {
                    System.out.println("\nError: Se ingresaron datos invalidos, debe ingresar al menos 4 valores");
                }

            } catch (Exception e) {
                System.out.println("\nError: Se ingresaron datos invalidos, solo puede ingresar números");
            }
            juegoRectangulo.recargar();
            System.out.printf("[ * El puntaje es: %d * ]\n", juegoRectangulo.getPuntaje());

            if (arrayEntrada[0].toUpperCase().equals("X") || cantidadRectangulos >= 10 || !juegoRectangulo.getMovimientosDisponibles()) {
                terminaJuego = true;
                System.out.println("Fin del juego!");
            }
            System.out.println("____________________________________________________________________________");
        }
        return juegoRectangulo;
    }

    private static Saltar jugarSaltar() {
        Saltar juegoSaltar = new Saltar(seleccionarConfiguracion());
        String entrada = "";
        Ficha[] listaFichas = {
            new Ficha(1, "#"),
            new Ficha(2, "#"),
            new Ficha(3, "#"),
            new Ficha(4, "#")
        };
        int puntaje = 0;
        int movimientosNoPermitidos = 0;
        int columnaIngresada = 0;
        boolean terminaJuego = false;

        // -- Se establece la primera ficha
        juegoSaltar.setFichaActual(listaFichas[0]);
        juegoSaltar.iniciar();

        // -- Se itera hasta que se presione X
        while (!terminaJuego) {
            // -- Para cortar un poco el rastro en pantalla y que parezca que refresca las mismas posiciones
            // -- Se imprime el tablero a pantalla
            System.out.println(juegoSaltar.getTablero().getContenidoString());
            // -- Se indica que ficha va a ser la siguiente en mover
            System.out.printf("\nSiguiente color de ficha a mover: %s \n", juegoSaltar.getFichaActual().getFichaColoreada());
            // -- Se lee la entrada del usuario
            entrada = utilidad.Entrada.leerString("columna, [A] para ayuda o [X] para finalizar");
            System.out.println("____________________________________________________________________________");
            if (esNumero(entrada)) {
                columnaIngresada = Integer.parseInt(entrada) - 1;
                if (!juegoSaltar.siguienteMovimiento(columnaIngresada)) {
                    System.out.println("Error: Movimiento ingresado invalido, se saltea el color.");
                };
            } else if (!entrada.toUpperCase().equals("X") && !entrada.toUpperCase().equals("A")) {
                System.out.println("Error, debe de ingresar una columna para realizar la siguiente jugada o X para finalizar");
            }

            // -- Se va alternando entre fichas
            juegoSaltar.recargar();
            if (juegoSaltar.getSiguienteColumnaRecomendada() == -1) {
                if (movimientosNoPermitidos == 4) {
                    System.out.println("No quedan movimientos restantes.");
                    terminaJuego = true;
                } else {
                    System.out.printf("La ficha no tiene movimientos disponibles %s \n", juegoSaltar.getFichaActual().getFichaColoreada());
                }
                movimientosNoPermitidos++;
            } else if (!juegoSaltar.getMovimientosDisponibles()) {
                terminaJuego = true;
                System.out.println("No quedan mas movimientos!");
            } else if (entrada.toUpperCase().equals("A")) {
                System.out.printf("Columna recomendada:%d", juegoSaltar.getSiguienteColumnaRecomendada() + 1);
            }

            if (entrada.toUpperCase().equals("X")) {
                terminaJuego = true;
            }
        }
        System.out.printf("Puntaje final: %d\n", juegoSaltar.getPuntaje());
        System.out.println("Fin del juego!, volviendo al menú...");
        System.out.println("____________________________________________________________________________");
        return juegoSaltar;
    }

    public static void mostrarPartidas() {
        String entrada = "";

        ArrayList < Partida > listaPartidas = MenuPrincipal.Sistema.getListaPartidas();
        while (!entrada.equals("X")) {
            System.out.format("+------------+-------------+------+---------+---------------------+%n");
            System.out.format("|                    Lista de Partidas                            |%n");
            System.out.format("+------------+-------------+------+---------+---------------------+%n");

            System.out.format("+------------+-------------+------+---------+---------------------+%n");
            System.out.format("| Juego      | Alias       | Edad | Puntaje | Fecha               |%n");
            System.out.format("+------------+-------------+------+---------+---------------------+%n");

            for (Partida partida: listaPartidas) {
                System.out.format(partida.toString());
            }
            System.out.format("+------------+-------------+------+---------+---------------------+%n%n");

            entrada = utilidad.Entrada.leerString("[X] para volver, o ingrese: \n - [A] Listar partidas por Alias creciente\n - [B] Listar partidas por puntaje decreciente\n - [C] Listar partidas por fecha ascendente").toUpperCase();

            if (entrada.equals("A")) {
                listaPartidas = MenuPrincipal.Sistema.partidasOrdenadasAliasCrec();
            } else if (entrada.equals("B")) {
                listaPartidas = MenuPrincipal.Sistema.partidasOrdenadasPuntajeDesc();
            } else if (entrada.equals("C")) {
                listaPartidas = MenuPrincipal.Sistema.partidasOrdenadasFechaDecr();
            }
        }
    }
}