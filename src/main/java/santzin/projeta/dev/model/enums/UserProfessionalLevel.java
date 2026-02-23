package santzin.projeta.dev.model.enums;

public enum UserProfessionalLevel {
    TRAINEE("TRAINEE"), JUNIOR("JUNIOR"), PLENO("PELO"), SENIOR("SENIOR");

    private String professionalLevel;

    UserProfessionalLevel(String professionalLevel){
        this.professionalLevel = professionalLevel;
    }

    String getProfessionalLevel(){
        return this.professionalLevel;
    }
}
