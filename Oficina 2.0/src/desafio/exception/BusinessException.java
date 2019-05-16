package desafio.exception;

/**
 *
 * @author João Victor Bonfim
 */
public class BusinessException extends RuntimeException {

    /**
     * Creates a new instance of <code>BusinessException</code> without detail
     * message.
     */
    public BusinessException() {
    }

    /**
     * Constructs an instance of <code>BusinessException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BusinessException(String msg) {
        super(msg);
    }
}