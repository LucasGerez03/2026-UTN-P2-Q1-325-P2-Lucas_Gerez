package segundoparcialprogramacion2;

import java.io.Serializable;

public class Libro implements Serializable, GestorDeId {
    //------------------------------------------------
    //atributos
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private boolean disponible;
    private int vecesPrestado;
    
    //------------------------------------------------
    //Constructor
    public Libro(String codigo, String titulo, String autor, int anioPublicacion) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.disponible = true;
        this.vecesPrestado = 0;
    }

    //------------------------------------------------
    //getters
    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getVecesPrestado() {
        return vecesPrestado;
    }
    
    //--------------------------------------------
    //metodos
    public void incrementarPrestamos() {
        this.vecesPrestado++;
    }

    @Override
    public String obtenerId() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Libro [" + codigo + "] " + titulo + " - " + autor + " (" + anioPublicacion + ") - "
                + (disponible ? "Disponible" : "Prestado");
    }
}
