package Classes.Libreria.Productos;

// Especificaciones de los productos
public class Especificacion {
    private String nombre;
    private String valor;

    public Especificacion() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void ver() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Valor: " + valor);
    }
}
