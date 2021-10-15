
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
        Rectangulo juego = new Rectangulo(true);
        juego.iniciar();
        String entrada = "";
        int color = 1;
        int puntaje = 0;
        int rectanguloPosicionX = 0;
        int rectanguloPosicionY = 0;
        int rectanguloAlto = 0;
        int rectanguloAncho = 0;

        while(!entrada.split(" ")[0].equals("X") && !entrada.split(" ")[0].equals("x")){
            for (int i = 0; i < juego.getTablero().length; i++) {
                System.out.println("");

                for (int j = 0; j < juego.getTablero()[0].length; j++) {
                    System.out.print(juego.getTablero()[i][j]+ " ");
                }
            }
            System.out.println("\n");
            entrada = utilidad.Entrada.leerString("los siguientes valores [Posición X] [Posición Y] [Alto] [Ancho]\nen una sola línea númerica o presiones X para finalizar:");
            System.out.println("\n\n\n\n\n\n");
            System.out.println("__________________________________________________________________________________________");
            try
            {
                if(!entrada.split(" ")[0].equals("X")){
                  
                    rectanguloPosicionX = Integer.parseInt(entrada.split(" ")[0]); 
                    rectanguloPosicionY = Integer.parseInt(entrada.split(" ")[1]); 
                    rectanguloAlto = Integer.parseInt(entrada.split(" ")[2]); 
                    rectanguloAncho = Integer.parseInt(entrada.split(" ")[3]);
                    if(!juego.siguienteMovimiento(color,rectanguloPosicionX,rectanguloPosicionY,rectanguloAlto,rectanguloAncho)){
                        System.out.println("Error: Movimiento invalido.");
                    };
                }
            }catch(Exception e){
                System.out.println("\nError: Se ingresaron datos invalidos");
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
