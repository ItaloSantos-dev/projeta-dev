package santzin.projeta.dev.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import santzin.projeta.dev.DTOs.auth.RegisterRequestDTO;
import santzin.projeta.dev.DTOs.hability.HabilityResponseDTO;
import santzin.projeta.dev.DTOs.project.ProjectResponseDTO;
import santzin.projeta.dev.DTOs.user.UserResponseDTO;
import santzin.projeta.dev.DTOs.user.UserWithProjectsResponseDTO;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.model.enums.StatusRequestProject;
import santzin.projeta.dev.model.enums.UserRole;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private HabilityMapper habilityMapper;

    public UserModel requestRegisterToModel(RegisterRequestDTO request) {
        UserModel user = new UserModel();

        user.setName(request.name());
        user.setUsernameProperty(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setTelephoneNumber(request.telephoneNumber());
        user.setExperienceLevel(request.experienceLevel());
        user.setPrincipalStack(request.principalStack());

        user.setRole(UserRole.USER);
        user.setCreatedAt(LocalDate.now());

        return user;
    }

    public UserResponseDTO modelToResponse(UserModel userModel, List<ProjectResponseDTO> myProjectsFixed){

        List<HabilityResponseDTO> habilitys = new ArrayList<>();

        if (userModel.getHabilitys()!=null)
            habilitys = userModel.getHabilitys().stream()
                    .map(h -> this.habilityMapper.modelToResponse(h))
                    .toList();

        int notificationsCount = 0;
        if (userModel.getNotifications()!=null)
            notificationsCount = userModel.getNotifications().stream()
                    .filter(prnm -> !prnm.getRead())
                    .toList()
                    .size();
        List<String> following = new ArrayList<>();
        List<String> followers = new ArrayList<>();

        if (userModel.getFollowing() != null)
            following = userModel.getFollowing().stream()
                    .map(uf -> uf.getUserFollowed().getUsernameProperty())
                    .toList();

        if (userModel.getFollowers() != null)
            followers = userModel.getFollowers().stream()
                    .map(uf -> uf.getUserFollowing().getUsernameProperty())
                    .toList();


        return new UserResponseDTO(
                userModel.getId(),
                userModel.getName(),
                userModel.getUsernameProperty(),
                userModel.getExperienceLevel(),
                userModel.getPrincipalStack(),
                myProjectsFixed,
                userModel.getAbout(),
                userModel.getCoverUrl(),
                userModel.getPerfilUrl(),
                userModel.getLink1(),
                userModel.getLink2(),
                userModel.getLink3(),
                userModel.getLink4(),
                userModel.getLink5(),
                habilitys,
                notificationsCount,
                following,
                followers
        );
    }

    public UserWithProjectsResponseDTO modelToWithProjectsResponse(UserModel userModel){

        List<ProjectResponseDTO> myProjects = new ArrayList<>();

        if (userModel.getMyProjects()!=null)
            myProjects = userModel.getMyProjects().stream()
                    .map( p-> this.projectMapper.modelToResponse(p))
                    .toList();

        List<HabilityResponseDTO> habilitys = new ArrayList<>();

        if (userModel.getHabilitys()!=null)
            habilitys = userModel.getHabilitys().stream()
                    .map(h -> this.habilityMapper.modelToResponse(h))
                    .toList();

        int notificationsCount = 0;
        if (userModel.getNotifications()!=null)
            notificationsCount = userModel.getNotifications().stream()
                    .filter(prnm -> !prnm.getRead())
                    .toList()
                    .size();
        List<String> following = new ArrayList<>();
        List<String> followers = new ArrayList<>();

        if (userModel.getFollowing() != null)
            following = userModel.getFollowing().stream()
                    .map(uf -> uf.getUserFollowed().getUsernameProperty())
                    .toList();

        if (userModel.getFollowers() != null)
            followers = userModel.getFollowers().stream()
                    .map(uf -> uf.getUserFollowing().getUsernameProperty())
                    .toList();


        return new UserWithProjectsResponseDTO(
                userModel.getId(),
                userModel.getName(),
                userModel.getUsernameProperty(),
                userModel.getExperienceLevel(),
                userModel.getPrincipalStack(),
                myProjects,
                userModel.getAbout(),
                userModel.getCoverUrl(),
                userModel.getPerfilUrl(),
                userModel.getLink1(),
                userModel.getLink2(),
                userModel.getLink3(),
                userModel.getLink4(),
                userModel.getLink5(),
                habilitys,
                notificationsCount,
                following,
                followers
        );
    }
}
