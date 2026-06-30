package segundoparcialprogramacion2;

public class Socio extends Persona {
    //_---------------------------------------------------
    //atributos
    private static final long serialVersionUID = 1L;
    private String email;
    
    //---------------------------------------------------
    //Constructor
    public Socio(String dni, String nombre, String apellido, String email) {
        super(dni, nombre, apellido);
        this.email = email;
    }
    
    //_---------------------------------------------------
    //getters
    public String getEmail() {
        return email;
    }
    
    //_---------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + " | Email: " + email;
    }
}
