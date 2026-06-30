package segundoparcialprogramacion2;

import java.io.*;

public class ArchivoUtil {
    // -----------------------------------------------------------------

    public static void guardarSerializado(Object objeto, String ruta) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(objeto);
        }
    }

    public static Object cargarSerializado(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return ois.readObject();
        }
    }

    public static void guardarTexto(String contenido, String ruta) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            writer.write(contenido);
        }
    }
    // -----------------------------------------------------------------
    
}
