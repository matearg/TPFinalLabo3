package Classes;

import Exceptions.LibroInexistenteException;
import Exceptions.ProductoInvalidoException;

import java.util.List;

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

    public int buscar(T t) {
        for(int i = 0; i < inventario.size(); i++) {
            if(t.equals(inventario.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public void agregar(T t) {
        int index = buscar(t);
        if(index == -1 && t.getPrecio() > 0) {
            inventario.add(t);
        } else {
            throw new ProductoInvalidoException("El precio debe ser mayo a cero.");
        }
    }

    public void eliminar(T t) {
        int index = buscar(t);
        if(index != -1) {
            inventario.remove(index);
        } else {
            throw new LibroInexistenteException("El libro no se ha encontrado.");
        }
    }

    public void ver() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Ubicacion: " + ubicacion);
        for (ProductoLibreria p: inventario) {
            p.ver();
        }
    }
}
