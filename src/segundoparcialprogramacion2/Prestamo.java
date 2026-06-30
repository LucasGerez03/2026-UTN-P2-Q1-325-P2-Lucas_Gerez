/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package segundoparcialprogramacion2;


import java.io.Serializable;
import java.time.LocalDate;

public class Prestamo implements Serializable, GestorDeId {
    //---------------------------------------------------------
    private static final long serialVersionUID = 1L;
    private int idPrestamo;
    private Libro libro;
    private Socio socio;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private boolean activo;
    
    //---------------------------------------------------------
    public Prestamo(int idPrestamo, Libro libro, Socio socio) {
        this.idPrestamo = idPrestamo;
        this.libro = libro;
        this.socio = socio;
        this.fechaPrestamo = LocalDate.now();
        this.activo = true;
    }
    
    
    public int getIdPrestamo() { return idPrestamo; }
    public Libro getLibro() { return libro; }
    public Socio getSocio() { return socio; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public boolean isActivo() { return activo; }

    //---------------------------------------------------------
    public void registrarDevolucion() {
        this.fechaDevolucion = LocalDate.now();
        this.activo = false;
        this.libro.setDisponible(true);
    }
    //---------------------------------------------------------
    @Override
    public String obtenerId() {
        return String.valueOf(idPrestamo);
    }

    @Override
    public String toString() {
        return "Prestamo #" + idPrestamo + " | Socio: " + socio.getNombre() + " " + socio.getApellido() + 
               " | Libro: " + libro.getTitulo() + " | Fecha: " + fechaPrestamo + 
               " | Estado: " + (activo ? "Activo" : "Devuelto en " + fechaDevolucion);
    }
}