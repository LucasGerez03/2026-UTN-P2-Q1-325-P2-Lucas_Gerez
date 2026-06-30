
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
            
            b.registrarLibro("A001", "El Aleph", "Jorge Luis Borges", 1949);
            b.registrarLibro("E210", "Rayuela", "Julio Cortazar", 1963);
            b.registrarLibro("C007", "Ficciones", "Jorge Luis Borges", 1944);

            //-----------------------------------------------------------------
            b.registrarSocio("20111222", "Lucas", "Messi", "asd@email.com");
            b.registrarSocio("35444555", "pepito", "pepito", "pepito@email.com");

            
            //-----------------------------------------------------------------
            b.realizarPrestamo("P008", "20111222");
            
            //-----------------------------------------------------------------
            System.out.println("[INFO] Se han cargado datos de prueba automaticamente.");
        } catch (Exception e) {
            System.out.println("[INFO] No se pudieron cargar los datos de prueba: " + e.getMessage());
        }
    }
}
