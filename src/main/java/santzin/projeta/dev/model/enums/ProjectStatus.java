package santzin.projeta.dev.model.enums;

public enum ProjectStatus {
    OPEND("OPEND"), CLOSED("CLOSED"), FINALED("FINALED");

    private String projectStatus;

    ProjectStatus (String projectStatus){
        this.projectStatus = projectStatus;
    }

    String getProjectStatus(){
        return this.projectStatus;
    }
}
