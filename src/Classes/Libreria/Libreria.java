package Classes.Libreria;

import Classes.Libreria.Productos.ProductoLibreria;
import Exceptions.ElementoInexistenteException;
import Exceptions.ProductoInvalidoException;

import java.util.List;

// Clase generica con metodos para
// agregar y eliminar productos (Libros, Revistas, Comics, Ebooks)
public class Libreria<T extends ProductoLibreria> {
    private String nombre;
    private String ubicacion;
    private List<T> inventario;

    public Libreria() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<T> getInventario() {
        return inventario;
    }

    public void setInventario(List<T> inventario) {
        this.inventario = inventario;
    }

    // Busca un elemento dentro de la lista generica
    // devuelve su indice, en caso de no encontrarlo
    // devuelve -1
    public int buscar(T t) {
        for (int i = 0; i < inventario.size(); i++) {
            if (t.equals(inventario.get(i))) {
                return i;
            }
        }
        return -1;
    }

    // Utiliza el metodo buscar para verificar que el producto
    // no este cargado y que su precio sea mayor a 0, en ese caso,
    // lo agrega a la lista caso contrario
    // lanza una excepcion personalizada (ProductoInvalidoException)
    public void agregar(T t) {
        int index = buscar(t);
        if (index == -1 && t.getPrecio() > 0) {
            inventario.add(t);
        } else {
            throw new ProductoInvalidoException("El precio debe ser mayo a cero.");
        }
    }

    // Utliza el metodo buscar para conocer el indice del producto buscado
    // si lo encuentra lo elimina de la lista, caso contrario lanza una
    // excepcion personalizada (ElementoInexistenteException)
    public void eliminar(T t) {
        int index = buscar(t);
        if (index != -1) {
            inventario.remove(index);
        } else {
            throw new ElementoInexistenteException("El libro no se ha encontrado.");
        }
    }

    public void ver() {
        System.out.println(" --------------------------------------  ");
        System.out.println("Nombre: " + nombre);
        System.out.println("Ubicacion: " + ubicacion);
        for (ProductoLibreria p : inventario) {
            p.ver();
        }
    }
}
