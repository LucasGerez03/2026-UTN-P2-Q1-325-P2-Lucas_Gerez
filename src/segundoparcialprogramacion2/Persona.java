package segundoparcialprogramacion2;

import java.io.Serializable;

public abstract class Persona implements Serializable, GestorDeId {

    //--------------------------------------------------
    //atributos
    private static final long serialVersionUID = 1L;
    protected String dni;
    protected String nombre;
    protected String apellido;

    //----------------------------------------------------------
    //Constructor
    public Persona(String dni, String nombre, String apellido) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    //----------------------------------------------------------
    //getters
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    //----------------------------------------------------------
    //metodos
    @Override
    public String obtenerId() {
        return dni;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " - DNI: " + dni;
    }
}
