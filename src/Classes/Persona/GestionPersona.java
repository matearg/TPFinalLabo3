package Classes.Persona;

import Classes.Persona.Personas.Persona;
import Exceptions.ElementoExistenteException;
import Exceptions.ElementoInexistenteException;

import java.util.ArrayList;
import java.util.List;

// Clase gestora de Persona
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

    // Busca un elemento dentro de la lista generica
    // devuelve su indice, en caso de no encontrarlo
    // devuelve -1
    public int buscar(T t) {
        for (int i = 0; i < personas.size(); i++) {
            if (t.getDni().equals(personas.get(i).getDni())) {
                return i;
            }
        }
        return -1;
    }

    // Utiliza el metodo buscar para verificar que la persona
    // no este cargada, en ese caso, la agrega a la lista caso contrario
    // lanza una excepcion personalizada (ElementoExistenteException)
    public void agregar(T t) {
        int index = buscar(t);
        if (index == -1) {
            personas.add(t);
        } else {
            throw new ElementoExistenteException("El dni de la persona ya se encuntra registrado.");
        }
    }

    // Utliza el metodo buscar para conocer el indice de la persona buscada
    // si la encuentra la elimina de la lista, caso contrario lanza una
    // excepcion personalizada (ElementoInexistenteException)
    public void eliminar(T t) {
        int index = buscar(t);
        if (index != -1) {
            personas.remove(index);
        } else {
            throw new ElementoInexistenteException("No se ha encontrado a la persona.");
        }
    }
}
