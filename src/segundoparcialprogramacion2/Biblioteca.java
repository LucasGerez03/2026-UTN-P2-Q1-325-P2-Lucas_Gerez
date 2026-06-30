/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package segundoparcialprogramacion2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Biblioteca implements Exportable {
    // -----------------------------------------------------------------
    private Repositorio<Libro> repoLibros = new Repositorio<>();
    private Repositorio<Socio> repoSocios = new Repositorio<>();
    private Repositorio<Prestamo> repoPrestamos = new Repositorio<>();
    
    private int contadorIdPrestamos = 1;
// -----------------------------------------------------------------
    //OPCION  1
    public void registrarSocio(String dni, String nombre, String apellido, String email) {
        if (repoSocios.existe(dni)) {
            throw new IllegalArgumentException("Error: Ya existe un socio con el DNI " + dni);
        }
        repoSocios.agregar(new Socio(dni, nombre, apellido, email));
        System.out.println("Socio registrado exitosamente.");
    }

    // -----------------------------------------------------------------
    //opcion  3
    public void listarSocios() {
        for (Socio socio : repoSocios.listar()) {
            System.out.println(socio.toString());
        }
    }
    
    // -----------------------------------------------------------------
    //opcion 2
    public void registrarLibro(String codigo, String titulo, String autor, int anio) {
        if (repoLibros.existe(codigo)) {
            throw new IllegalArgumentException("Error: Ya existe un libro con el codigo " + codigo);
        }
        repoLibros.agregar(new Libro(codigo, titulo, autor, anio));
        System.out.println("Libro registrado exitosamente.");
    }
    // -----------------------------------------------------------------
    //opcion 4
    public void listarLibrosDisponibles() {
        boolean hayDisponibles = false;
        for (Libro libro : repoLibros.listar()) {
            if (libro.isDisponible()) {
                System.out.println(libro.toString());
                hayDisponibles = true;
            }
        }
        if (!hayDisponibles) {
            System.out.println("No hay libros disponibles en este momento.");
        }
    }
    // -----------------------------------------------------------------
    //opcion 5
    public Libro buscarLibroPorCodigo(String codigo) {
        return repoLibros.buscar(codigo);
    }
    
    
    //-----------------------------------------------------------------
    //opcion 6
    public void realizarPrestamo(String codigoLibro, String dniSocio) {
        Libro libro = repoLibros.buscar(codigoLibro);
        if (libro == null) throw new IllegalArgumentException("Libro no encontrado.");
        if (!libro.isDisponible()) throw new IllegalArgumentException("El libro no esta disponible.");

        Socio socio = repoSocios.buscar(dniSocio);
        if (socio == null) throw new IllegalArgumentException("Socio no encontrado.");

        Prestamo nuevoPrestamo = new Prestamo(contadorIdPrestamos, libro, socio);
        libro.setDisponible(false); 
        libro.incrementarPrestamos(); // Aumentamos el contador para las estadisticas
        
        repoPrestamos.agregar(nuevoPrestamo);
        System.out.println("Prestamo realizado exitosamente. ID: " + contadorIdPrestamos);
        contadorIdPrestamos++;
    }
    
    // -----------------------------------------------------------------
    //opcion 7
    public void registrarDevolucion(int idPrestamo) {
        Prestamo prestamo = repoPrestamos.buscar(String.valueOf(idPrestamo));
        if (prestamo == null) throw new IllegalArgumentException("Prestamo inexistente.");
        if (!prestamo.isActivo()) throw new IllegalArgumentException("El prestamo ya fue devuelto.");

        prestamo.registrarDevolucion();
        System.out.println("Devolucion registrada exitosamente.");
    }
    
    // -----------------------------------------------------------------
    //opcion 8
    public void listarPrestamosActivos() {
        boolean hayActivos = false;
        for (Prestamo prestamo : repoPrestamos.listar()) {
            if (prestamo.isActivo()) {
                System.out.println(prestamo.toString());
                hayActivos = true;
            }
        }
        if (!hayActivos) {
            System.out.println("No hay prestamos activos.");
        }
    }
    
    // -----------------------------------------------------------------
    //opcion 9
    
    @Override
    public void generarInforme(String rutaArchivo) throws Exception {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaHora = LocalDateTime.now().format(formato);
        
        List<Socio> socios = repoSocios.listar();
        List<Libro> libros = repoLibros.listar();
        List<Prestamo> prestamos = repoPrestamos.listar();
        
        int prestamosActivos = 0;
        for (Prestamo p : prestamos) {
            if (p.isActivo()) {
                prestamosActivos++;
            }
        }
        
        int librosDisponibles = 0;
        Libro libroMasPrestado = null;
        int maxPrestamos = -1;
        
        for (Libro l : libros) {
            if (l.isDisponible()) {
                librosDisponibles++;
            }
            if (l.getVecesPrestado() > maxPrestamos) {
                maxPrestamos = l.getVecesPrestado();
                libroMasPrestado = l;
            }
        }

        String contenido = "****** INFORME INSTITUCIONAL BIBLIOTECA UTN AVELLANEDA ******\n";
        contenido += "Fecha y hora de generacion: " + fechaHora + "\n";
        contenido += "--------------------------------------------------------\n";
        contenido += "Cantidad total de socios registrados: " + socios.size() + "\n";
        contenido += "Cantidad total de libros registrados: " + libros.size() + "\n";
        contenido += "Cantidad de libros disponibles: " + librosDisponibles + "\n";
        contenido += "Cantidad de prestamos activos: " + prestamosActivos + "\n";
        
        if (libroMasPrestado != null && maxPrestamos > 0) {
            contenido += "Libro mas solicitado: " + libroMasPrestado.getTitulo() + " (Prestado " + maxPrestamos + " veces)\n";
        } else {
            contenido += "Libro mas solicitado: N/A (Aun no hay registros de prestamos)\n";
        }
        contenido += "---------------------------------------------------------------\n";

        ArchivoUtil.guardarTexto(contenido, rutaArchivo);
        System.out.println("Informe institucional generado exitosamente en " + rutaArchivo);
    }

    // -----------------------------------------------------------------
    public void guardarDatos() {
        try {
            ArchivoUtil.guardarSerializado(repoLibros, "libros.dat");
            ArchivoUtil.guardarSerializado(repoSocios, "socios.dat");
            ArchivoUtil.guardarSerializado(repoPrestamos, "prestamos.dat");
            System.out.println("Datos guardados correctamente en archivos binarios.");
        } catch (Exception e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        try {
            repoLibros = (Repositorio<Libro>) ArchivoUtil.cargarSerializado("libros.dat");
            repoSocios = (Repositorio<Socio>) ArchivoUtil.cargarSerializado("socios.dat");
            repoPrestamos = (Repositorio<Prestamo>) ArchivoUtil.cargarSerializado("prestamos.dat");
            
            int maxId = 0;
            for (Prestamo prestamo : repoPrestamos.listar()) {
                if (prestamo.getIdPrestamo() > maxId) {
                    maxId = prestamo.getIdPrestamo();
                }
            }
            contadorIdPrestamos = maxId + 1;
            
            System.out.println("Datos previos cargados exitosamente.");
        } catch (Exception e) {
            System.out.println("No se encontraron datos previos o hubo un error al cargar. Iniciando base vacia.");
        }
    }
}