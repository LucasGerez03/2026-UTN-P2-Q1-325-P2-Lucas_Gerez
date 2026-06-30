
package segundoparcialprogramacion2;


import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Repositorio<T extends GestorDeId> implements Serializable {
    private static final long serialVersionUID = 1L;
    private HashMap<String, T> elementos;

    public Repositorio() {
        this.elementos = new HashMap<>();
    }

    public void agregar(T objeto) {
        elementos.put(objeto.obtenerId(), objeto);
    }

    public List<T> listar() {
        return new ArrayList<>(elementos.values());
    }

    public T buscar(String id) {
        return elementos.get(id);
    }

    public boolean eliminar(String id) {
        return elementos.remove(id) != null;
    }

    public boolean existe(String id) {
        return elementos.containsKey(id);
    }
}