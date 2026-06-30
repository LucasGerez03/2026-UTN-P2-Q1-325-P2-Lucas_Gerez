package segundoparcialprogramacion2;

public class Socio extends Persona {
    private static final long serialVersionUID = 1L;
    private String email;

    public Socio(String dni, String nombre, String apellido, String email) {
        super(dni, nombre, apellido);
        this.email = email;
    }

    public String getEmail() { return email; }

    @Override
    public String toString() {
        return super.toString() + " | Email: " + email;
    }
}
