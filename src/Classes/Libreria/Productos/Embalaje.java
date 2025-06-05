package Classes.Libreria.Productos;

// Subclase de ProductoLibreria
public class Embalaje {
    private String tipo;
    private String resistencia;
    private String dimensiones;

    public Embalaje() {
    }

    public Embalaje(String tipo, String resistencia, String dimensiones) {
        this.tipo = tipo;
        this.resistencia = resistencia;
        this.dimensiones = dimensiones;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getResistencia() {
        return resistencia;
    }

    public void setResistencia(String resistencia) {
        this.resistencia = resistencia;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public void ver() {
        System.out.println("Tipo: " + tipo);
        System.out.println("Resistencia: " + resistencia);
        System.out.println("Dimensiones: " + dimensiones);
    }
}
