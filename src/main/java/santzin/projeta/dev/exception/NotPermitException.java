package santzin.projeta.dev.exception;

public class NotPermitException extends RuntimeException {
    public NotPermitException() {
        super("Você não possui permissão para acessar este item");
    }

    public NotPermitException(String message) {
        super(message);
    }
}
