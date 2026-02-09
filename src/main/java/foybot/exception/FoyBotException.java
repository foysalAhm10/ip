package foybot.exception;

/**
 * Represents an exception that occurs during the execution of foybot.FoyBot operations.
 */
public class FoyBotException extends Exception {
    /**
     * Creates a FoyBotException with the given message.
     *
     * @param message Description of the error.
     */
    public FoyBotException(String message) {
        super(message);
    }
}
