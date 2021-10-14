
import dominio.Rectangulo;
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
public class testRectangulo {
    public static void main(String[] args) {
        Rectangulo juego = new Rectangulo(false);
        juego.iniciar();
        String entrada = "";
        int color = 1;
        int puntaje = 0;
        while(!entrada.equals("X") && !entrada.equals("x")){
            for (int i = 0; i < juego.getTablero().length; i++) {
    //            System.out.print("\n\t\t");
      System.out.print("\n");

                for (int j = 0; j < juego.getTablero()[0].length; j++) {
                    System.out.print(juego.getTablero()[i][j]+ " ");
                }
              }
            System.out.printf("\nPuntaje:%d Siguiente color de ficha a mover: %s \n",puntaje,utilidad.Constante.ResolverTextoColor(color));
            entrada = utilidad.Entrada.leerString("columna o X para finalizar");
            int columnaIngresada = 0; 
            System.out.println("\n\n\n\n\n\n");
            System.out.println("__________________________________________________________________________________________");

            if(esNumero(entrada)){
                columnaIngresada = Integer.parseInt(entrada) - 1; 
                if(!juego.siguienteMovimiento(color,columnaIngresada)){
                    System.out.println("Error: Movimiento invalido.");
                };
                
            }else if(!entrada.equals("X")){
                System.out.println("Debe de ingresar una columna para realizar la siguiente jugada o X para finalizar");
            }
            
            if(color == 4){
                color = 1;
            }else{
                color ++;
            }
            juego.recargar();

            puntaje = juego.calcularPuntaje();
        }
    }
}
