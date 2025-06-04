package Classes.Persona.Personas;

import Classes.Libreria.Productos.Ebook;
import Classes.Libreria.Productos.ProductoLibreria;

import java.util.List;

// Subclase de Persona tienen la capacidad de alquilar y devolver ebooks
public class Cliente extends Persona {
    private List<Ebook> libros;

    public Cliente() {
        this.setTipo("Cliente");
    }

    public List<Ebook> getLibros() {
        return libros;
    }

    public void setLibros(List<Ebook> libros) {
        this.libros = libros;
    }

    public void ver() {
        super.ver();
    }

    public void verLibros() {
        System.out.println("Libros:");
        for(Ebook p: libros) {
            p.ver();
        }
    }
}
