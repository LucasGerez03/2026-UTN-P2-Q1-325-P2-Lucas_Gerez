/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package segundoparcialprogramacion2;

import java.io.Serializable;

public class Libro implements Serializable, GestorDeId {

    private static final long serialVersionUID = 1L;
    private String codigo;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private boolean disponible;
    private int vecesPrestado;

    public Libro(String codigo, String titulo, String autor, int anioPublicacion) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.disponible = true;
        this.vecesPrestado = 0;
    }

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
