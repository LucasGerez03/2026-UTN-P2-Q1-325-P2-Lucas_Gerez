
package segundoparcialprogramacion2;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        cargarDatosDePrueba(biblioteca);
        
        
        Menu menu = new Menu(scanner, biblioteca);
        menu.iniciar();
        
        scanner.close();
    }
    
    private static void cargarDatosDePrueba(Biblioteca b) {
        try {
            //-----------------------------------------------------------------
            //Registro 3 libros
            
            b.registrarLibro("A001", "el Aleph", "Jorge Luis Borges", 1959);
            b.registrarLibro("E000", "rayuela", "Julio Cortazar", 1973);
            b.registrarLibro("C912", "El principito", "nose", 1994);

            //-----------------------------------------------------------------
            //registro de socios
            b.registrarSocio("1234", "Lucas", "Messi", "asd@email.com");
            b.registrarSocio("5678", "pepito", "pepito", "pepito@email.com");

            
            //-----------------------------------------------------------------
            //prestamos
            b.realizarPrestamo("A001", "1234");
            
            //-----------------------------------------------------------------
            System.out.println("[INFO] Se han cargado datos de prueba automaticamente.");
        } catch (Exception e) {
            System.out.println("[INFO] No se pudieron cargar los datos de prueba: " + e.getMessage());
        }
    }
}
