import java.util.HashMap;
import java.util.Scanner;

public class PracticaCifrado {
    private static final HashMap<Character, Integer> mapa = new HashMap<>();
    private static final HashMap<Character, Integer> mapa2 = new HashMap<>();
    private static String alfabeto;
    private static String nuevoAlfabeto;

    public static void main(String[] args) {
        alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        nuevoAlfabeto = "79*$60?¿8453!=%&12@#mirsao";
        boolean continua = true;
        for (int i = 0; i<=25; i++){
            mapa.put(alfabeto.charAt(i), i);
            mapa2.put(nuevoAlfabeto.charAt(i), i);
        }
        Scanner leer = new Scanner(System.in);
        while (continua) {
            System.out.print("Para cifrar introduzca \"C\". \"Para descifrar introduzca \"D\": ");
            String opcion = leer.next().toUpperCase();
            while (!opcion.equals("C") & !opcion.equals("D")) {
                System.out.println("Opción no válida");
                System.out.print("Para cifrar introduzca \"C\". \"Para descifrar introduzca \"D\": ");
                opcion = leer.next().toUpperCase();
            }
            System.out.print("Introduzca el texto: ");
            String texto = leer.next();
            switch (opcion) {
                case "C": cifrar(texto.toUpperCase());
                    break;
                case "D": descifrar(texto);
                    break;
            }
            System.out.print("¿Quiere continuar? (Y/N): ");
            String opContinuar = leer.next().toUpperCase();
            while (!opContinuar.equals("Y") & !opContinuar.equals("N")) {
                System.out.println("Opción no válida");
                System.out.print("Para continuar introduzca \"Y\". \"Para parar introduzca \"N\": ");
                opContinuar = leer.next();
            }
            if (opContinuar.equals("N")){
                continua = false;
            }
        }
        leer.close();

    }

    public static void cifrar(String texto){
        StringBuilder salida= new StringBuilder();
        for (int i = 0; i<texto.length(); i++){
            int codigoNuevaLetra= mapa.get(texto.charAt(i)) + 8;
            if (codigoNuevaLetra >=26) codigoNuevaLetra -= 26;
            salida.append(nuevoAlfabeto.charAt(codigoNuevaLetra));
        }
        System.out.println("El texto cifrado es \"" + salida + "\"");
    }

    public static void descifrar(String texto){
        StringBuilder salida= new StringBuilder();
        System.out.println(mapa2);
        for (int i = 0; i<texto.length(); i++){
            int codigoNuevaLetra= mapa2.get(texto.charAt(i)) - 8;
            if (codigoNuevaLetra <0) codigoNuevaLetra += 26;
            salida.append(alfabeto.charAt(codigoNuevaLetra));
        }
        System.out.println("El texto descifrado es \"" + salida + "\"");
    }

}
