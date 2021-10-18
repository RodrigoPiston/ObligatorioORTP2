
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
    public static void main(String[] args) {/*
        Rectangulo juego = new Rectangulo(true);
        juego.iniciar();
        String [] arrayEntrada = {"","","",""};        
        String entrada = "";

        int color = 1;
        int rectanguloPosicionX = 0;
        int rectanguloPosicionY = 0;
        int rectanguloAlto = 0;
        int rectanguloAncho = 0;
        int cantidadRectangulos = 0;
        boolean primeraJugada = true;
        boolean terminaJuego = false;
        while(terminaJuego){
            for (int i = 0; i < juego.getTablero().length; i++) {
                System.out.println("");

                for (int j = 0; j < juego.getTablero()[0].length; j++) {
                    System.out.print(juego.getTablero()[i][j]+ " ");
                }
            }
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
                    if(!juego.siguienteMovimiento(color,rectanguloPosicionX,rectanguloPosicionY,rectanguloAlto,rectanguloAncho,primeraJugada)){
                        System.out.println("Error: Movimiento invalido.");
                    }else{
                        cantidadRectangulos ++;
                        primeraJugada = false;
                    };
                    
                    // -- Solo se cambia de color cuando es correcta la entrada
                    if(color == 4){
                        color = 1;
                    }else{
                        color ++;
                    }
                }else if(!arrayEntrada[0].equals("X"))
                {
                    System.out.println("\nError: Se ingresaron datos invalidos, debe ingresar al menos 4 valores");
                }
                
            }catch(Exception e){
                System.out.println("\nError: Se ingresaron datos invalidos, solo puede ingresar números");
            }
            
            juego.recargar();

            System.out.printf("El puntaje es: %d \n",juego.calcularPuntaje());
            if(!arrayEntrada[0].equals("X") && !arrayEntrada[0].equals("x") && cantidadRectangulos <= 10 && juego.quedanRectangulosDisponibles()){
                terminaJuego = true;
                System.out.println("Fin del juego!");
            }
        }*/
    }
}
