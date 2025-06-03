package Classes.Libreria;

import Classes.Libreria.Productos.ProductoLibreria;

public class GestionLibreria {
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
}
