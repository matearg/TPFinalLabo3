package Exceptions;

public class LibroInexistenteException extends RuntimeException {
    public LibroInexistenteException(String message) {
        super(message);
    }
}
