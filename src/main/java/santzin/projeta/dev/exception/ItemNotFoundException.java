package santzin.projeta.dev.exception;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException() {
        super("O item buscado não existe");
    }

    public ItemNotFoundException(Long id, String itemType) {
        super("O(A) "+ itemType+" de id " + id + " não foi encontrado(a)");
    }
}
