import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

public class controladora {
    private BinarySearchTree<Association<String, String>> diccionario;

    public controladora() {
        Comparator<Association<String, String>> comparator = Comparator.comparing(Association::getKey);
        diccionario = new BinarySearchTree<>(comparator);
    }

    public void leerArchivo(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split(",");
                String key = palabras[0].trim();
                String value = palabras[1].trim();
                diccionario.add(new Association<>(key, value));
            }
            System.out.println("Diccionario cargado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
    }

    public String traducir(String frase) {
        String[] palabras = frase.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String palabra : palabras) {
            String traduccion = buscarTraduccion(palabra.toLowerCase());
            if (traduccion != null) {
                sb.append(traduccion).append(" ");
            } else {
                sb.append("*").append(palabra).append("*").append(" ");
            }
        }
        return sb.toString().trim();
    }

    private String buscarTraduccion(String palabra) {
        Association<String, String> association = diccionario.find(new Association<>(palabra, ""));
        if (association != null) {
            return association.getValue();
        } else {
            return null;
        }
    }
}





