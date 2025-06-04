package Classes.Persona.Personas;

import Exceptions.PinAccesoInvalidoException;

// Clase abstracta persona (Utiliza abstraccion para evitar
// que se instancien objetos Persona)
public abstract class Persona {
    private String tipo;
    private String nombre;
    private String pinAcceso;
    private String dni;
    private int edad;

    public Persona() {
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

    public String getPinAcceso() {
        return pinAcceso;
    }

    public void setPinAcceso(String pinAcceso) {
        if (pinAcceso.matches("\\d{4}")) {
            this.pinAcceso = pinAcceso;
        } else {
            throw new PinAccesoInvalidoException("El pin debe contener cuatro cifras.");
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void ver() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("DNI: " + dni);
    }
}
