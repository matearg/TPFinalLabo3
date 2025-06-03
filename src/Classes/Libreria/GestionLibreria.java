package Classes.Libreria;

public class GestionLibreria {
    private Libreria<ProductoLibreria> libreria;

    public GestionLibreria(Libreria<ProductoLibreria> libreria) {
        this.libreria = new Libreria<>();
    }

    public Libreria<ProductoLibreria> getLibreria() {
        return libreria;
    }

    public void setLibreria(Libreria<ProductoLibreria> libreria) {
        this.libreria = libreria;
    }
}
