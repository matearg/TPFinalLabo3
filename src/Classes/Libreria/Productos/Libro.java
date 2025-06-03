package Classes.Libreria.Productos;

import Classes.Libreria.ProductoLibreria;

public class Libro extends ProductoLibreria {
    private String genero;
    private int anioPublicacion;

    public Libro() {
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    @Override
    public void verDetalle() {
        System.out.println("Genero: " + genero);
        System.out.println("Anio de Publicacion: " + anioPublicacion);
    }
}
