package Classes.Persona.Personas;

import Classes.Libreria.GestionLibreria;
import Classes.Libreria.Productos.Ebook;
import Classes.Libreria.Productos.ProductoLibreria;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Subclase de Persona tienen la capacidad de alquilar y devolver ebooks
public class Cliente extends Persona {
    private List<Ebook> libros;

    public Cliente() {
        this.libros = new ArrayList<>();
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
        for (Ebook p : libros) {
            p.ver();
        }
    }

    public void cargarLibro(GestionLibreria gestionLibreria) {
        Scanner teclado = new Scanner(System.in);
        String tituloEbook;
        System.out.println("Ingrese el nombre del libro que quiere ");
        tituloEbook = teclado.nextLine();
        teclado.nextLine();

        for (ProductoLibreria e : gestionLibreria.getLibreria().getInventario()) {
            if (e.getNombre().equalsIgnoreCase(tituloEbook) && e instanceof Ebook) {
                libros.add((Ebook) e);
            }
        }

    }

}
