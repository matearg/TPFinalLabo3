package Classes.Persona;

import Exceptions.ElementoExistenteException;
import Exceptions.ElementoInexistenteException;

import java.util.ArrayList;
import java.util.List;

public class GestionPersona<T extends Persona> {
    private List<T> personas = new ArrayList<>();

    public GestionPersona() {
    }

    public List<T> getPersonas() {
        return personas;
    }

    public void setPersonas(List<T> personas) {
        this.personas = personas;
    }

    public int buscar(T t) {
        for (int i = 0; i < personas.size(); i++) {
            if (t.getDni().equals(personas.get(i).getDni())) {
                return i;
            }
        }
        return -1;
    }

    public void agregar(T t) {
        int index = buscar(t);
        if (index == -1) {
            personas.add(t);
        } else {
            throw new ElementoExistenteException("El dni de la persona ya se encuntra registrado.");
        }
    }

    public void eliminar(T t) {
        int index = buscar(t);
        if (index != -1) {
            personas.remove(index);
        } else {
            throw new ElementoInexistenteException("No se ha encontrado a la persona.");
        }
    }
}
