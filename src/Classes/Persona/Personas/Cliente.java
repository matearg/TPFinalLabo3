package Classes.Persona.Personas;

import Classes.Libreria.Productos.Ebook;

import java.util.List;

public class Cliente extends Persona {
    private List<Ebook> libros;

    public Cliente() {
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
