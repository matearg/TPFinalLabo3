package Classes.Libreria;

import Classes.Libreria.Productos.Ebook;
import Classes.Libreria.Productos.ProductoLibreria;

import java.util.Scanner;

// Clase gestora de libreria
public class GestionLibreria extends Libreria<ProductoLibreria> {
    private Libreria<ProductoLibreria> libreria;

    public GestionLibreria() {
        this.libreria = new Libreria<>();
    }

    public Libreria<ProductoLibreria> getLibreria() {
        return libreria;
    }

    public void setLibreria(Libreria<ProductoLibreria> libreria) {
        this.libreria = libreria;
    }

    public void ver() {
        libreria.ver();
    }

    public void cambiarEstadoAlquiler() { /// Cambia el estado del alquiler segun el nombre del articulo que se le envie
        Scanner teclado = new Scanner(System.in);
        System.out.println(" Ingrese el nombre del libro ");
        String nombreLibro = teclado.nextLine();
        teclado.nextLine();

        for (ProductoLibreria p : libreria.getInventario()) {
            if (p instanceof Ebook ebook && p.getNombre().equalsIgnoreCase(nombreLibro)) { /// Chequea q sea un ebook ya que solos esos se pueden alquilar

                if (ebook.isAlquilado()) {
                    ebook.devolver();
                    System.out.println("Ebook devuelto ");
                } else {
                    ebook.alqiular();
                    System.out.println("Ebook alquilado ");
                }
            }
        }
    }
}
