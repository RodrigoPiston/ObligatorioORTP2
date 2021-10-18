package utilidad;

import dominio.Ficha;

/**
 *
 * @author  Rodrigo Pistón(261777) | Rodrigo Camps(241344)
 */
public class Generico {
    // -- 0:RESET - 1:ROJO - 2:AZUL - 3:VERDE - 4:AMARILLO
    public static String[] Colores = {
        "\u001B[0m",
        "\u001B[31m",
        "\u001B[34m",
        "\u001B[32m",
        "\u001B[33m"
    };

    public static Ficha FichaVacia = new Ficha(0, " ");

    public static String AlineamientoString = "| %-10s | %-11s | %-4d | %-7d | %-19s |%n";

    // 0:RESET | 1:[#] ROJO | 2: [#] AZUL | 3: [#] VERDE | 4: [#] AMARILLO | 5: [-] Sin color | 6: [*] Sin Color
    public static String[] CaracteresColores = {
        " ",
        Colores[1] + "#" + Colores[0],
        Colores[2] + "#" + Colores[0],
        Colores[3] + "#" + Colores[0],
        Colores[4] + "#" + Colores[0],
        "-",
        "*"
    };

    public static String retornarColor(int codigo) {
        return CaracteresColores[codigo];
    }

    public static String resolverTextoColor(int codigo) {
        String textoColor = "";
        switch (codigo) {
            case 1:
                textoColor = Colores[1] + "[R] Rojo";
                break;
            case 2:
                textoColor = Colores[2] + "[A] Azul";
                break;
            case 3:
                textoColor = Colores[3] + "[V] Verde";
                break;
            case 4:
                textoColor = Colores[4] + "[M] Amarillo";
                break;
        }
        return textoColor + Colores[0];
    }


    public static void copiarMatriz(Ficha[][] original, Ficha[][] copia) {
        for (int fila = 0; fila < original.length; fila++) {
            for (int columna = 0; columna < original[0].length; columna++) {
                copia[fila][columna] = original[fila][columna];
            }
        }
    }
    public static boolean esNumeral(int codigo) {
        return CaracteresColores[codigo].contains("#");
    }
}