package santzin.projeta.dev.model.enums;

public enum TypeProjectRequestNotification {
    REQUEST("REQUEST"), ACCEPTED("ACCEPTED"), DENIED("DENIED");

    private String typeProjectRequestNotification;

    TypeProjectRequestNotification(String typeProjectRequestNotification){
        this.typeProjectRequestNotification= typeProjectRequestNotification;
    }

    String getTypeProjectRequestNotification(){
        return this.typeProjectRequestNotification;
    }
}
