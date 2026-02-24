package santzin.projeta.dev.model.enums;

public enum UserExperienceLevel {
    TRAINEE("TRAINEE"), JUNIOR("JUNIOR"), PLENO("PELO"), SENIOR("SENIOR");

    private String experienceLevel;

    UserExperienceLevel(String professionalLevel){
        this.experienceLevel = professionalLevel;
    }

    String getExperienceLevel(){
        return this.experienceLevel;
    }
}
