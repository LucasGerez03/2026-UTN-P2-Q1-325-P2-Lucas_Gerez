
package segundoparcialprogramacion2;

import java.util.Scanner;

public class Menu {
    
    private Scanner sc;
    private Biblioteca biblioteca;

    public Menu(Scanner sc, Biblioteca biblioteca) {
        this.sc = sc;
        this.biblioteca = biblioteca;
    }
    //metodos auxiliares para levantar las opciones del menu, ( iniciar() )
    //------------------------------------------------------------------
    public static int leerEnteros(String mensaje, Scanner scanner) {
        while (true) {
            System.out.print(mensaje);
            try {
                int v = Integer.parseInt(scanner.nextLine());
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero valido.");
            }
        }
    }

    public static String leerTexto(String mensaje, Scanner scanner) {
        String texto = "";
        while (texto.trim().isEmpty()) {
            System.out.print(mensaje);
            texto = scanner.nextLine();
            if (texto.trim().isEmpty()) {
                System.out.println("Error: El campo no puede estar vacio.");
            }
        }
        return texto.trim();
    }
    //------------------------------------------------------------------
    public void iniciar() {
        System.out.println("===== BIBLIOTECA DIGITAL UTN AVELLANEDA =====");
        boolean activo = true;
        
        while (activo) {
            System.out.println("\n************ Menu Principal ************");
            System.out.println("1. Registrar nuevo socio");
            System.out.println("2. Registrar nuevo libro");
            System.out.println("3. Listar socios registrados");
            System.out.println("4. Listar libros disponibles");
            System.out.println("5. Buscar libro por codigo");
            System.out.println("6. Registrar prestamo de libro");
            System.out.println("7. Registrar devolucion");
            System.out.println("8. Mostrar prestamos activos");
            System.out.println("9. Generar informe institucional (TXT)");
            System.out.println("10. Cargar datos previos (.dat)");
            System.out.println("0. Guardar informacion y salir");

            int opcion = leerEnteros("Seleccione una opcion del menu: ", sc);
            
            try {
                switch (opcion) {
                    case 1:
                        System.out.println("\n--- REGISTRAR SOCIO ---");
                        String dni = leerTexto("DNI: ", sc);
                        String nom = leerTexto("Nombre: ", sc);
                        String ape = leerTexto("Apellido: ", sc);
                        String email = leerTexto("Email: ", sc);
                        this.biblioteca.registrarSocio(dni, nom, ape, email);
                        break;

                    case 2:
                        System.out.println("\n--- REGISTRAR LIBRO ---");
                        String cod = leerTexto("Codigo: ", sc);
                        String tit = leerTexto("Titulo: ", sc);
                        String aut = leerTexto("Autor: ", sc);
                        int anio = leerEnteros("Anio de publicacion: ", sc);
                        this.biblioteca.registrarLibro(cod, tit, aut, anio);
                        break;
                        
                    case 3:
                        System.out.println("\n--- SOCIOS REGISTRADOS ---");
                        this.biblioteca.listarSocios();
                        break;
                        
                    case 4:
                        System.out.println("\n--- LIBROS DISPONIBLES ---");
                        this.biblioteca.listarLibrosDisponibles();
                        break;
                        
                    case 5:
                        System.out.println("\n--- BUSCAR LIBRO ---");
                        String codigoBusqueda = leerTexto("Ingrese el codigo a buscar: ", sc);
                        Libro l = this.biblioteca.buscarLibroPorCodigo(codigoBusqueda);
                        if (l != null) {
                            System.out.println("Resultado: " + l.toString());
                        } else {
                            System.out.println("El libro no fue encontrado.");
                        }
                        break;
                        
                    case 6:
                        System.out.println("\n--- REGISTRAR PRESTAMO ---");
                        String codLibro = leerTexto("Codigo del libro: ", sc);
                        String dniSocio = leerTexto("DNI del socio: ", sc);
                        this.biblioteca.realizarPrestamo(codLibro, dniSocio);
                        break;
                        
                    case 7:
                        System.out.println("\n--- REGISTRAR DEVOLUCION ---");
                        int idPrestamo = leerEnteros("ID del prestamo a devolver: ", sc);
                        this.biblioteca.registrarDevolucion(idPrestamo);
                        break;
                        
                    case 8:
                        System.out.println("\n--- PRESTAMOS ACTIVOS ---");
                        this.biblioteca.listarPrestamosActivos();
                        break;
                        
                    case 9:
                        this.biblioteca.generarInforme("informe.txt");
                        break;

                    case 10:
                        this.biblioteca.cargarDatos();
                        break;
                        
                    case 0:
                        System.out.println("Guardando informacion...");
                        this.biblioteca.guardarDatos();
                        System.out.println("--------- PROGRAMA FINALIZADO ---------");
                        System.out.println("Alumno: Lucas Gaston Gerez");
                        activo = false;
                        break;
                        
                    default:
                        System.out.println("Opcion invalida. Intente nuevamente.");
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Atencion: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }
}