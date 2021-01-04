package fr.lcdlv.kata.bank;

public class OverdraftException extends Exception {
    public OverdraftException() {
    }

    public OverdraftException(String message) {
        super(message);
    }

    public OverdraftException(String message, Throwable cause) {
        super(message, cause);
    }

    public OverdraftException(Throwable cause) {
        super(cause);
    }
}
