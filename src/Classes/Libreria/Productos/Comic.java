package Classes.Libreria.Productos;


// Subclase de ProductoLibreria
public class Comic extends ProductoLibreria {
    private String universo;
    private String color;

    public Comic() {
        this.setTipo("Comic");
    }

    public String getUniverso() {
        return universo;
    }

    public void setUniverso(String universo) {
        this.universo = universo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void verDetalle() {
        System.out.println("Universo: " + universo);
        System.out.println("Color: " + color);
    }
}
