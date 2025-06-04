package Exceptions;

// Excepcion personalizada que informa que un elemento no se encontro
// Utlizar idealmente en una coleccion de datos
public class ElementoExistenteException extends RuntimeException {
    public ElementoExistenteException(String message) {
        super(message);
    }
}
