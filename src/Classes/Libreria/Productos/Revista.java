package Classes.Libreria.Productos;

// Subclase de ProductoLibreria
public class Revista extends ProductoLibreria {
    private String frecuenciaDePublicacion;
    private String numeroDeEdicion;

    public Revista() {
        this.setTipo("Revista");
    }

    public String getFrecuenciaDePublicacion() {
        return frecuenciaDePublicacion;
    }

    public void setFrecuenciaDePublicacion(String frecuenciaDePublicacion) {
        this.frecuenciaDePublicacion = frecuenciaDePublicacion;
    }

    public String getNumeroDeEdicion() {
        return numeroDeEdicion;
    }

    public void setNumeroDeEdicion(String numeroDeEdicion) {
        this.numeroDeEdicion = numeroDeEdicion;
    }

    @Override
    public void verDetalle() {
        System.out.println("Frecuencia: " + frecuenciaDePublicacion);
        System.out.println("Edicion: " + numeroDeEdicion);
    }
}
