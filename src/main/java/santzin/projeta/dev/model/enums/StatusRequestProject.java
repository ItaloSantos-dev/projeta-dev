package santzin.projeta.dev.model.enums;

public enum StatusRequestProject {
    PENDING("PENDING"), ACCEPTED("ACCEPTED"), REJECTED("REJECTED"), CANCELED("CANCELED");
    private String statusRequestProject;

    StatusRequestProject (String statusRequestProject){
        this.statusRequestProject = statusRequestProject;
    }

    String getStatusRequestProject(){
        return this.statusRequestProject;
    }

    public TypeProjectRequestNotification getTypeProjectByStatusRequest(){
        if (this == ACCEPTED)
            return TypeProjectRequestNotification.ACCEPTED;
        if (this == REJECTED)
            return TypeProjectRequestNotification.DENIED;
        return TypeProjectRequestNotification.REQUEST;
    }
}


