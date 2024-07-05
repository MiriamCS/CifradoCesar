import java.security.SecureRandom;
import java.util.*;

public class PracticaCifrado {
    private static final HashMap<Character, Integer> mapa = new HashMap<>();
    private static final HashMap<Character, Integer> mapa2 = new HashMap<>();
    private static final String alfabeto;
    private static final String simbolos;
    private static final String caracteres;
    private static final String nuevoCaracteres;
    private static final int k;
    private static final String OPCION_CIFRAR = "C";
    private static final String OPCION_DESCIFRAR = "D";
    private static final String OPCION_SI = "Y";
    private static final String OPCION_NO = "N";
    private static final Random random = new SecureRandom();

    static {
        alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚ";
        simbolos = "1234567890¿?*Ç=()/&%$¡!,.\"\'";
        caracteres = alfabeto + alfabeto.toLowerCase() + simbolos;
        nuevoCaracteres = barajarCadena(caracteres);
        k = random.nextInt(10);
        for (int i = 0; i < caracteres.length(); i++) {
            mapa.put(caracteres.charAt(i), i);
            mapa2.put(nuevoCaracteres.charAt(i), i);
        }
    }

    public static void main(String[] args) {
        boolean continua = true;
        final Scanner leer = new Scanner(System.in);
        while (continua) {
            System.out.print("Para cifrar introduzca \"" + OPCION_CIFRAR + "\". Para descifrar introduzca \"" + OPCION_DESCIFRAR + "\": ");
            String opcion = leer.next().toUpperCase();
            while (!opcion.equals(OPCION_CIFRAR) && !opcion.equals(OPCION_DESCIFRAR)) {
                System.out.println("Opción no válida");
                System.out.print("Para cifrar introduzca \"" + OPCION_CIFRAR + "\". Para descifrar introduzca \"" + OPCION_DESCIFRAR + "\": ");
                opcion = leer.next().toUpperCase();
            }
            System.out.print("Introduzca el texto: ");
            String texto = leer.next();
            switch (opcion) {
                case OPCION_CIFRAR:
                    cifrar(texto);
                    break;
                case OPCION_DESCIFRAR:
                    descifrar(texto);
                    break;
            }
            System.out.print("¿Quiere continuar? Ten en cuenta que tras salir habrá un nuevo cifrado (" + OPCION_SI + "/" + OPCION_NO + "): ");
            String opContinuar = leer.next().toUpperCase();
            while (!opContinuar.equals(OPCION_SI) && !opContinuar.equals(OPCION_NO)) {
                System.out.println("Opción no válida");
                System.out.print("Para continuar introduzca \"" + OPCION_SI + "\". Para parar introduzca \"" + OPCION_NO + "\": ");
                opContinuar = leer.next().toUpperCase();
            }
            if (opContinuar.equals(OPCION_NO)) {
                continua = false;
            }
        }
        leer.close();
    }

    public static String barajarCadena(String input) {
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while (!characters.isEmpty()) {
            int selector = random.nextInt(characters.size());
            output.append(characters.remove(selector));
        }
        return output.toString();
    }

    public static void cifrar(String texto) {
        StringBuilder salida = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c == ' ') {
                salida.append(' ');
            } else {
                int codigoNuevaLetra = mapa.get(texto.charAt(i)) + k + k * i;
                while (codigoNuevaLetra > caracteres.length()) {
                    codigoNuevaLetra -= caracteres.length();
                }
                salida.append(nuevoCaracteres.charAt(codigoNuevaLetra));
            }
        }
        System.out.println("El texto cifrado es \"" + salida + "\"");
    }

    public static void descifrar(String texto) {
        StringBuilder salida = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c == ' ') {
                salida.append(' ');
            } else {
                int codigoNuevaLetra = mapa2.get(texto.charAt(i)) - (k + k * i);
                while (codigoNuevaLetra < 0) {
                    codigoNuevaLetra += caracteres.length();
                }
                salida.append(caracteres.charAt(codigoNuevaLetra));
            }
        }
        System.out.println("El texto descifrado es \"" + salida + "\"");
    }
}