package Exceptions;

// Excepcion personalizada para indicar cuando un producto tiene algun
// atributo erroneo, por lo tanto no puede ser creado o cargado a una coleccion
public class ProductoInvalidoException extends RuntimeException {
    public ProductoInvalidoException(String message) {
        super(message);
    }
}
