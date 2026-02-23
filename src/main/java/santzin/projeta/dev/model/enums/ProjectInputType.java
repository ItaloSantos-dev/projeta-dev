package santzin.projeta.dev.model.enums;

public enum ProjectInputType {
    PAID("PAID"), FREE("FREE");
    private String projectInputType;

    ProjectInputType (String projectInputType){
        this.projectInputType = projectInputType;
    }

    String getProjectInputType(){
        return this.projectInputType;
    }
}
