package Exceptions;

// Excepcion personalizada que informa que un elemento no se encontro
// Utlizar idealmente en una coleccion de datos
public class ElementoInexistenteException extends RuntimeException {
    public ElementoInexistenteException(String message) {
        super(message);
    }
}
