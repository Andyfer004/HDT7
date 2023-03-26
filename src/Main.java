import java.io.BufferedReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        controladora controladora = new controladora();
        Scanner scan = new Scanner(System.in);
        System.out.println("Bienvenido al programa de traducción de palabras");
        System.out.println("Por favor seleccione una opción");
        System.out.println("1. Leer diccionario de palabras");
        System.out.println("2. Traducir frase");
        Integer input = scan.nextInt();
            switch (input) {
                case 1:
                    controladora.leerArchivo("/Users/andyfer004/IdeaProjects/HDT7/src/values.txt");
                    break;
                case 2:
                    System.out.println("Ingrese la frase que desea traducir: ");
                    Scanner scanner = new Scanner(System.in);
                    String frase = scanner.nextLine();
                    System.out.println("Ingrese el idioma de origen (ingles/espanol/frances): ");
                    String idiomaOrigen = scanner.nextLine();
                    System.out.println("Ingrese el idioma de destino (ingles/espanol/frances): ");
                    String idiomaDestino = scanner.nextLine();
                    String traduccion = controladora.traducir(frase, idiomaOrigen, idiomaDestino);
                    System.out.println("La traducción es: " + traduccion);
                    break;
            }

}

    }

