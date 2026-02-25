package santzin.projeta.dev.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import santzin.projeta.dev.DTOs.RegisterRequestDTO;
import santzin.projeta.dev.DTOs.UserResponseDTO;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.model.enums.UserRole;

import java.time.LocalDate;
@Component
public class UserMapper {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserModel requestRegisterToModel(RegisterRequestDTO request) {

        UserModel user = new UserModel();

        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setTelephoneNumber(request.telephoneNumber());
        user.setExperienceLevel(request.experienceLevel());
        user.setPrincipalStack(request.principalStack());

        user.setRole(UserRole.USER);
        user.setCreatedAt(LocalDate.now());

        return user;
    }

    public UserResponseDTO modelToResponse(UserModel userModel){
        return new UserResponseDTO(
                userModel.getName(),
                userModel.getEmail(),
                userModel.getExperienceLevel(),
                userModel.getTelephoneNumber(),
                userModel.getPrincipalStack()
        );
    }
}
