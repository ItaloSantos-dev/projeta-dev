package santzin.projeta.dev.exception;

public class FailedRegisterException extends RuntimeException {
    public FailedRegisterException(String message) {
        super(message);
    }

    public FailedRegisterException(){
        super("Falha ao registrar usuário");
    }
}
