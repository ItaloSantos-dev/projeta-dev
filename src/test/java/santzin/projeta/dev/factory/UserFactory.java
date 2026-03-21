package santzin.projeta.dev.factory;

import santzin.projeta.dev.DTOs.auth.RegisterRequestDTO;
import santzin.projeta.dev.DTOs.user.UserResponseDTO;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.model.enums.UserExperienceLevel;
import santzin.projeta.dev.model.enums.UserRole;

import java.util.List;

public class  UserFactory {

    public static class UserFactoryBuilder {
        public static UserModel userModel() {
            UserModel userModel = new UserModel();
            userModel.setId(1L);
            userModel.setName("italo");
            userModel.setEmail("italo@gmail");
            userModel.setPassword("1515");
            userModel.setRole(UserRole.USER);
            userModel.setTelephoneNumber("99981587631");
            userModel.setExperienceLevel(UserExperienceLevel.JUNIOR);
            userModel.setPrincipalStack("Java, Spring");

            return userModel;
        }

        public static RegisterRequestDTO registerRequestDTO(){
            return  new RegisterRequestDTO("italo", "it", "italo@gmail", "1515", "99981587631", UserExperienceLevel.JUNIOR, "Java, Spring");
        }

        public static UserResponseDTO userResponseDTO(){
            return new UserResponseDTO(
                    1L,
                    "italo",
                    "it",
                    UserExperienceLevel.JUNIOR,
                    "Java, Spring",
                    List.of(),
                    "Nada",
                    "nada",
                    "nada",
                    "nada",
                    "nada",
                    "nada",
                    "nada",
                    "nada",
                    List.of(),
                    0,
                    List.of(),
                    List.of()

                    );
        }
    }
}
