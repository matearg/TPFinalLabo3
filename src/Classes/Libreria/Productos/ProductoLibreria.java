package Classes.Libreria.Productos;

import java.util.List;
import java.util.Objects;

// Clase padre abstracta productoLibreria
public abstract class ProductoLibreria {
    private String tipo;
    private String nombre;
    private String marca;
    private double precio;
    private int cantidad;
    private List<Especificacion> especificaciones;
    private List<Embalaje> embalajes;

    public ProductoLibreria() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<Especificacion> getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(List<Especificacion> especificaciones) {
        this.especificaciones = especificaciones;
    }

    public List<Embalaje> getEmbalajes() {
        return embalajes;
    }

    public void setEmbalajes(List<Embalaje> embalajes) {
        this.embalajes = embalajes;
    }

    // Metodo de visualizacion de los productoLibreria
    public void ver() {
        System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
        System.out.println("Nombre: " + nombre);
        System.out.println("Marca: " + marca);
        System.out.println("Tipo: " + tipo);
        System.out.println("Precio: $" + precio);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Detalle:");
        verDetalle();
        for (Especificacion e : especificaciones) {
            e.ver();
        }
        for (Embalaje e : embalajes) {
            e.ver();
        }
    }

    // Metodos equals y hashcode personalizados
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductoLibreria that = (ProductoLibreria) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre) * 32;
    }

    // Metodo abstracto que implemetaran todas las subclases
    // muestra las caracteristicas de cada subclase
    public abstract void verDetalle();
}
