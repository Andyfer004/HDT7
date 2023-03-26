import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Arrays;

public class controladora {
    private BinarySearchTree<Association<String, String>> diccionario;

    public controladora() {
        Comparator<Association<String, String>> comparator = Comparator.comparing(Association::getKey);
        diccionario = new BinarySearchTree<>(comparator);
    }

    public void leerArchivo(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/andyfer004/IdeaProjects/HDT7/src/values.txt"))) {
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



    private String buscarTraduccion(String palabra) {
        Association<String, String> association = diccionario.find(new Association<>(palabra, ""));
        if (association != null) {
            return association.getValue();
        } else {
            return null;
        }
    }

    public String traducir(String frase, String idiomaOrigen, String idiomaDestino) {
        String[] palabras = frase.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String palabra : palabras) {
            String traduccion = buscarTraduccion(palabra.toLowerCase(), idiomaOrigen, idiomaDestino);
            if (traduccion != null) {
                sb.append(traduccion).append(" ");
            } else {
                sb.append("*").append(palabra).append("*").append(" ");
            }
        }
        return sb.toString().trim();
    }

    private String buscarTraduccion(String palabra, String idiomaOrigen, String idiomaDestino) {
        String[] idiomas = {"ingles", "espanol", "frances"};
        int indexOrigen = Arrays.asList(idiomas).indexOf(idiomaOrigen);
        int indexDestino = Arrays.asList(idiomas).indexOf(idiomaDestino);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("/Users/andyfer004/IdeaProjects/HDT7/src/values.txt"));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equals(palabra)) {
                    return datos[indexDestino + 1  ];
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de diccionario");
            e.printStackTrace();
        }

        return null;
    }

}





