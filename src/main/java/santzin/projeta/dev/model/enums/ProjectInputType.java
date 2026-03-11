package santzin.projeta.dev.model.enums;

public enum ProjectInputType {
    RELESEAD("RELESEAD"), TEST("TEST"), PERMISSION("PERMISSION"), PAID("PAID");
    private String projectInputType;

    ProjectInputType (String projectInputType){
        this.projectInputType = projectInputType;
    }

    String getProjectInputType(){
        return this.projectInputType;
    }
}
