package Classes.Productos;

import Classes.ProductoLibreria;
import Interfaces.I_Alquiler;

public class Ebook extends ProductoLibreria implements I_Alquiler {
    private boolean drm;
    private String idioma;
    private boolean alquilado = false;

    public Ebook() {
    }

    public boolean isDrm() {
        return drm;
    }

    public void setDrm(boolean drm) {
        this.drm = drm;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean isAlquilado() {
        return alquilado;
    }

    public void setAlquilado(boolean alquilado) {
        this.alquilado = alquilado;
    }

    @Override
    public void verDetalle() {
        System.out.println("DRM: " + drm);
        System.out.println("Idioma: " + idioma);
        System.out.println("Alquilado: " + alquilado);
    }

    @Override
    public void alqiular() {
        this.alquilado = true;
    }

    @Override
    public void devolver() {
        this.alquilado = false;
    }
}
