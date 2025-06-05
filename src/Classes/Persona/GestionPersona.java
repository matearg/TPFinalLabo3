package Classes.Persona;

import Classes.Persona.Personas.Administrador;
import Classes.Persona.Personas.Empleado;
import Classes.Persona.Personas.Persona;
import Exceptions.ElementoInexistenteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Clase gestora de Persona
public class GestionPersona<T extends Persona> extends Persona {
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

    public int buscarNombre(String nombre) {
        for (int i = 0; i < personas.size(); i++) {
            if (nombre.equals(personas.get(i).getNombre())) {
                return i;
            }
        }
        return -1;
    }

    // Utiliza el metodo buscar para verificar que la persona
    // no este cargada, en ese caso, la agrega a la lista caso contrario
    // lanza una excepcion personalizada (ElementoExistenteException)
    public void agregar(T t) {
        personas.add(t);
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

    public void ver() {
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas.");
        } else {
            for (T persona : personas) {
                persona.ver();
                System.out.println("-----");
            }
        }
    }

    public void eliminarPorDni(String dni) {
        T personaEliminar = null;

        for (T p : personas) {
            if (p.getDni().equalsIgnoreCase(dni)) {
                System.out.println(" dni encontrado a eliminar");
                personaEliminar = p;
            }
        }
        if (personaEliminar != null) {
            eliminar(personaEliminar);
        }

    }

    /// Cambia el sueldo segun el dni
    public void cambiarSueldoPorDni(String dni) {
        Scanner teclado = new Scanner(System.in);

        System.out.println(" ingrese el salario ");
        double salario = teclado.nextDouble();

        for (T p : personas) {
            if (p.getDni().equalsIgnoreCase(dni)) { /// chequear que sea empleado o Admin ya que solo esos cuentan con el atributo salario
                if (p instanceof Empleado) {
                    ((Empleado) p).setSalario(salario);
                    System.out.println("salario actualizado ");
                }
                if (p instanceof Administrador) {
                    ((Administrador) p).setSalario(salario);
                    System.out.println("salario actualizado ");
                }
            }
        }
    }
}
