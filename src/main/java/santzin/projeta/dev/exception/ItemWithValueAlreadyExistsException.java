package santzin.projeta.dev.exception;

public class ItemWithValueAlreadyExistsException extends RuntimeException {
    public ItemWithValueAlreadyExistsException(String message) {
        super(message);
    }

    public ItemWithValueAlreadyExistsException() {
        super("Ja existe um item com este campo");
    }

    public ItemWithValueAlreadyExistsException(String itemType, String columnName, String value ){
        super("Já existe um(a) " + itemType + " com " + columnName + value);
    }
}
