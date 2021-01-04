package fr.lcdlv.kata.bank;

public class MinimumMoneyAllowedException extends Exception {
    public MinimumMoneyAllowedException() {
    }

    public MinimumMoneyAllowedException(String message) {
        super(message);
    }

    public MinimumMoneyAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MinimumMoneyAllowedException(Throwable cause) {
        super(cause);
    }
}
