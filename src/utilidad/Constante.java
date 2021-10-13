/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidad;


/**
 *
 * @author heido
 */
public class Constante {
    
    // -- 0:RESET - 1:ROJO - 2:AZUL - 3:VERDE - 4:AMARILLO
    public static String [] COLORES = {"\u001B[0m","\u001B[31m","\u001B[34m","\u001B[32m","\u001B[33m"};

    // 0:RESET | 1:[#] ROJO | 2: [#] AZUL | 3: [#] VERDE | 4: [#] AMARILLO | 5: [-] Sin color | 6: [*] Sin Color
    public static String [] caracteresColores = {
        " ",
        COLORES[1]+"#"+ COLORES[0],
        COLORES[2]+"#"+ COLORES[0],
        COLORES[3]+"#"+ COLORES[0],
        COLORES[4]+"#"+ COLORES[0],
        "-",
        "*"
    };
    
    public static String ResolverColor(int codigo){
        return caracteresColores[codigo];
    } 
    
    public static String ResolverTextoColor(int codigo){
        String textoColor = "";
        switch(codigo){
            case 1:
                textoColor = COLORES[1] + "[R] Rojo";
                break;
            case 2:
                textoColor = COLORES[2] + "[A] Azul";
                break;
            case 3:
                textoColor = COLORES[3] + "[V] Verde";
                break;
            case 4:
                textoColor = COLORES[4] + "[M] Amarillo";
                break;
        }
        
        return textoColor + COLORES[0];
    } 
    
  
    
}