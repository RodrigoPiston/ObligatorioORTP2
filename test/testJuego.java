
import dominio.Juego;
import dominio.Saltar;
import static utilidad.Entrada.esNumero;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author heido
 */
public class testJuego {
    public static void main(String[] args) throws InterruptedException {
        Saltar instanciaSaltar = new Saltar(false);
        instanciaSaltar.iniciar();
        String entrada = "";
        int color = 1;
        int puntaje = 0;
        // -- Se itera hasta que se presione X
        while(!entrada.equals("X") && !entrada.equals("x")){
            // -- Se muestra puntaje en pantalla
            System.out.printf("\nPuntaje: %d",puntaje);
            
            // -- Se imprime el tablero en pantalla
            for (int i = 0; i < instanciaSaltar.getTablero().length; i++) {
                System.out.print("\n\t\t");
                for (int j = 0; j < instanciaSaltar.getTablero()[0].length; j++) {
                    System.out.print(instanciaSaltar.getTablero()[i][j]);
                }
            }
            // -- Se indica que ficha va a ser la siguiente en mover
            System.out.printf("\nSiguiente color de ficha a mover: %s \n",utilidad.Generico.ResolverTextoColor(color));
            // -- Se lee la entrada del usuario
            entrada = utilidad.Entrada.leerString("columna o X para finalizar");
            int columnaIngresada = 0; 
            System.out.println("\n\n\n\n\n\n");
            System.out.println("__________________________________________________________________________________________");

            if(esNumero(entrada)){
                columnaIngresada = Integer.parseInt(entrada) - 1; 
                
                if(!instanciaSaltar.siguienteMovimiento(color,columnaIngresada)){
                    System.out.println("Error: Movimiento invalido.");
                };
                
            }else if(!entrada.equals("X") && !entrada.equals("x")){
                System.out.println("Debe de ingresar una columna para realizar la siguiente jugada o X para finalizar");
            }
            
            if(color == 4){
                color = 1;
            }else{
                color ++;
            }
            instanciaSaltar.recargar();
            puntaje = instanciaSaltar.calcularPuntaje();
        }
        Thread.sleep(1000);
    }
}
