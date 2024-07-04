import java.util.*;

public class PracticaCifrado {
    private static final HashMap<Character, Integer> mapa = new HashMap<>();
    private static final HashMap<Character, Integer> mapa2 = new HashMap<>();
    private static String alfabeto;
    private static String nuevoAlfabeto;
    private static int k;

    static{
        alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚ1234567890¿?*Ç=()/&%$¡!,.;";
        nuevoAlfabeto = barajarCadena(alfabeto);
        k= (int) (Math.random() * 10);
        for (int i = 0; i<alfabeto.length(); i++){
            mapa.put(alfabeto.charAt(i), i);
            mapa2.put(nuevoAlfabeto.charAt(i), i);
        }
    }

    public static void main(String[] args) {
        boolean continua = true;
        final Scanner leer = new Scanner(System.in);
        while (continua) {
            System.out.print("Para cifrar introduzca \"C\". \"Para descifrar introduzca \"D\": ");
            String opcion = leer.next().toUpperCase();
            while (!opcion.equals("C") & !opcion.equals("D")) {
                System.out.println("Opción no válida");
                System.out.print("Para cifrar introduzca \"C\". \"Para descifrar introduzca \"D\": ");
                opcion = leer.next().toUpperCase();
            }
            System.out.print("Introduzca el texto: ");
            String texto = leer.next().toUpperCase();
            switch (opcion) {
                case "C": cifrar(texto);
                    break;
                case "D": descifrar(texto);
                    break;
            }
            System.out.print("¿Quiere continuar? Ten en cuenta que tras salir habrá un nuevo cifrado (Y/N): ");
            String opContinuar = leer.next().toUpperCase();
            while (!opContinuar.equals("Y") & !opContinuar.equals("N")) {
                System.out.println("Opción no válida");
                System.out.print("Para continuar introduzca \"Y\". \"Para parar introduzca \"N\": ");
                opContinuar = leer.next().toUpperCase();
            }
            if (opContinuar.equals("N")){
                continua = false;
            }
        }
        leer.close();

    }



    public static String barajarCadena(String input){
        List<Character> characters = new ArrayList<>();
        for(char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(!characters.isEmpty()){
            int selector = (int)(Math.random()*characters.size());
            output.append(characters.remove(selector));
        }
        return output.toString();
    }

    public static void cifrar(String texto){
        StringBuilder salida= new StringBuilder();
        for (int i = 0; i<texto.length(); i++){
            int codigoNuevaLetra= mapa.get(texto.charAt(i)) + k;
            if (codigoNuevaLetra >alfabeto.length())
                codigoNuevaLetra -= alfabeto.length();
            salida.append(nuevoAlfabeto.charAt(codigoNuevaLetra));
        }
        System.out.println("El texto cifrado es \"" + salida + "\"");
    }

    public static void descifrar(String texto){
        StringBuilder salida= new StringBuilder();
        for (int i = 0; i<texto.length(); i++){
            int codigoNuevaLetra= mapa2.get(texto.charAt(i)) - k;
            if (codigoNuevaLetra <0)
                codigoNuevaLetra += alfabeto.length();
            salida.append(alfabeto.charAt(codigoNuevaLetra));
        }
        System.out.println("El texto descifrado es \"" + salida + "\"");
    }

}
