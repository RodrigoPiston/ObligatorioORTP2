/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidad;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author heido
 */
public class Entrada {
    
     public static String leerString(String texto){
        Scanner input = new Scanner(System.in);
        String textoIngresado = "";
        boolean entradaValida = false;
        while (!entradaValida){
            try {
                System.out.printf("Ingrese %s\n" ,texto);
                textoIngresado = input.nextLine().trim();
                if(textoIngresado.equals("")){
                    System.out.print("No puede ingresar un valor vacío, ");
                }else{
                    entradaValida = true;
                }
            }catch (InputMismatchException e) {
                System.out.print("Error, entrada inválida, ");
            }
        }
        return textoIngresado;
    } 
     
    public static int leerInt(String texto,int minimo,int maximo){
      Scanner input = new Scanner(System.in);
      boolean entradaValida = false;
      int numeroIngresado = 0;
      while (!entradaValida) {
          try {
              System.out.println("Ingrese " +texto);
              numeroIngresado = input.nextInt();
              if(minimo != 0 && numeroIngresado <= minimo){
                  System.out.printf("Debes de ingresar un valor de %s mayor a %d\n",texto,minimo);
              }else if(maximo != 0 && numeroIngresado >= maximo){
                  System.out.printf("Debes de ingresar un valor de %s menor a %d\n",texto,maximo);
              }else{
                  entradaValida = true;
              }
          } catch (InputMismatchException e) {
              System.out.printf("Debes ingresar un valor de %s valido.\n",texto );
              input.nextLine();
          }
      }
      return numeroIngresado;
    } 
    
       /**
     *
     * @param texto
     * @return
     */
    public static boolean leerBoolean(String texto){
        Scanner input = new Scanner(System.in);
        String textoIngresado = "";
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.printf("Ingrese %s\n" ,texto);
                textoIngresado = input.nextLine().trim();
                if(textoIngresado.equals("")){
                    System.out.print("No puede ingresar un valor vacío, ");
                }else{
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.print("Error, entrada inválida, ");
            }
        }
        return textoIngresado == "Y" || textoIngresado == "y" || textoIngresado == "S" || textoIngresado == "s" ;
    } 
    
    public static boolean esNumero(String str) { 
        try {  
          Integer.parseInt(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }
    
    public static int getRandom(int min, int max) {
        return new java.util.Random().nextInt(max - min + 1) + min;
    }
}
