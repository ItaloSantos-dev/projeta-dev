package santzin.projeta.dev.exception;

public class FailedLoginException extends RuntimeException {
    public FailedLoginException(String  message) {
        super(message);
    }

    public FailedLoginException() {
        super("Email ou senha incorretos");
    }
}
