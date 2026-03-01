package santzin.projeta.dev.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import santzin.projeta.dev.DTOs.project_user.CreateProjectUserDTO;
import santzin.projeta.dev.exception.NotPermitException;
import santzin.projeta.dev.factory.ProjectFactory;
import santzin.projeta.dev.factory.ProjectPositionFactory;
import santzin.projeta.dev.factory.ProjectUserlFactory;
import santzin.projeta.dev.factory.UserFactory;
import santzin.projeta.dev.mapper.ProjectUserMapper;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.ProjectPositionModel;
import santzin.projeta.dev.model.ProjectUserModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.repository.ProjectPositionRepository;
import santzin.projeta.dev.repository.ProjectRepository;
import santzin.projeta.dev.repository.ProjectUserRepository;
import santzin.projeta.dev.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProjectUserServiceTest {

    @InjectMocks
    private ProjectUserService projectUserService;

    @Mock
    private ProjectUserRepository projectUserRepository;

    @Mock
    private ProjectUserMapper projectUserMapper;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProjectPositionRepository projectPositionRepository;


    @Test
    @DisplayName("User of the relation try create a relation with project")
    void createProjectUserCase1() {
        ProjectUserModel projectUserModel = ProjectUserlFactory.ProjectUserFactoryBuilder.projectUserModel();
        UserModel user2 = UserFactory.UserFactoryBuilder.userModel();
        user2.setId(2L);
        projectUserModel.getProject().setCreator(user2);

        CreateProjectUserDTO createProjectUserDTO = new CreateProjectUserDTO(
                projectUserModel.getProject().getId(),
                projectUserModel.getUser().getId(),
                projectUserModel.getPosition().getId()
        );

        Mockito.when(this.projectRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(projectUserModel.getProject()));

        Mockito.when(this.userRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(projectUserModel.getUser()));

        Mockito.when(this.projectPositionRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(projectUserModel.getPosition()));

        this.projectUserService.createProjectUser(createProjectUserDTO, projectUserModel.getUser());

        Mockito.verify(this.projectUserRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Creator of project try create a relation between another user and project")
    void createProjectUserCase2() {
        ProjectUserModel projectUserModel = ProjectUserlFactory.ProjectUserFactoryBuilder.projectUserModel();
        UserModel user2 = UserFactory.UserFactoryBuilder.userModel();
        user2.setId(2L);
        projectUserModel.getProject().setCreator(user2);

        CreateProjectUserDTO createProjectUserDTO = new CreateProjectUserDTO(
                projectUserModel.getProject().getId(),
                projectUserModel.getUser().getId(),
                projectUserModel.getPosition().getId()
        );

        Mockito.when(this.projectRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(projectUserModel.getProject()));

        Mockito.when(this.userRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(projectUserModel.getUser()));

        Mockito.when(this.projectPositionRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(projectUserModel.getPosition()));

        this.projectUserService.createProjectUser(createProjectUserDTO, user2);

        Mockito.verify(this.projectUserRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("the user of trying a create the relation of User and project is not equal of the user of the relation and not creator of project")
    void createProjectUserCase3() {
        ProjectUserModel projectUserModel = ProjectUserlFactory.ProjectUserFactoryBuilder.projectUserModel();

        UserModel user2 = UserFactory.UserFactoryBuilder.userModel();
        user2.setId(2L);

        UserModel userCreator = UserFactory.UserFactoryBuilder.userModel();
        userCreator.setId(3L);
        projectUserModel.getProject().setCreator(userCreator);

        CreateProjectUserDTO createProjectUserDTO = new CreateProjectUserDTO(
                projectUserModel.getProject().getId(),
                projectUserModel.getUser().getId(),
                projectUserModel.getPosition().getId()
        );

        Mockito.when(this.projectRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(projectUserModel.getProject()));


        assertThrows(NotPermitException.class, () -> this.projectUserService.createProjectUser(createProjectUserDTO, user2));

    }


    @Test
    @DisplayName("User of the relation try delete a relation with project")
    void deleteProjectUserByIdCase1() {
        ProjectUserModel projectUserModel = ProjectUserlFactory.ProjectUserFactoryBuilder.projectUserModel();

        UserModel userCreator = UserFactory.UserFactoryBuilder.userModel();
        userCreator.setId(2L);
        projectUserModel.getProject().setCreator(userCreator);

        Mockito.when(this.projectUserRepository.findById(Mockito.any())).thenReturn(Optional.of(projectUserModel));

        this.projectUserService.deleteProjectUserById(projectUserModel.getId(), projectUserModel.getUser());

        Mockito.verify(this.projectUserRepository, Mockito.times(1)).deleteById(Mockito.any());
    }

    @Test
    @DisplayName("Project creator (admin) try delete a relation with project")
    void deleteProjectUserByIdCase2() {
        ProjectUserModel projectUserModel = ProjectUserlFactory
                .ProjectUserFactoryBuilder
                .projectUserModel();

        UserModel userCreator = UserFactory.UserFactoryBuilder.userModel();
        userCreator.setId(2L);

        projectUserModel.getProject().setCreator(userCreator);

        UserModel relationUser = projectUserModel.getUser();
        relationUser.setId(3L);

        Mockito.when(this.projectUserRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(projectUserModel));

        this.projectUserService.deleteProjectUserById(
                projectUserModel.getId(),
                userCreator
        );

        Mockito.verify(this.projectUserRepository, Mockito.times(1))
                .deleteById(Mockito.any());
    }

    @Test
    @DisplayName("Third user try delete a relation with project and should fail")
    void deleteProjectUserByIdCase3() {
        ProjectUserModel projectUserModel = ProjectUserlFactory
                .ProjectUserFactoryBuilder
                .projectUserModel();


        UserModel userCreator = UserFactory.UserFactoryBuilder.userModel();
        userCreator.setId(2L);
        projectUserModel.getProject().setCreator(userCreator);


        UserModel relationUser = projectUserModel.getUser();
        relationUser.setId(3L);


        UserModel thirdUser = UserFactory.UserFactoryBuilder.userModel();
        thirdUser.setId(99L);

        Mockito.when(this.projectUserRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(projectUserModel));

        assertThrows(
                RuntimeException.class,
                () -> this.projectUserService.deleteProjectUserById(
                        projectUserModel.getId(),
                        thirdUser
                )
        );

        Mockito.verify(this.projectUserRepository, Mockito.never())
                .deleteById(Mockito.any());
    }

    @Test
    @DisplayName("Admin of the project try update a relation with project")
    void updateByIdCase1() {
        ProjectUserModel projectUserModel = ProjectUserlFactory.ProjectUserFactoryBuilder.projectUserModel();

        UserModel userCreator = UserFactory.UserFactoryBuilder.userModel();
        userCreator.setId(2L);
        projectUserModel.getProject().setCreator(userCreator);

        ProjectPositionModel actuallyPosition = projectUserModel.getPosition();

        ProjectPositionModel newPosition = ProjectPositionFactory.ProjectPositionFactoryBuilder.projectPositionModel(projectUserModel.getProject());
        newPosition.setId(2L);

        Mockito.when(this.projectUserRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(projectUserModel));

        Mockito.when(this.projectPositionRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(newPosition));
        this.projectUserService.updateById(projectUserModel.getId(), 2L, userCreator);

        Mockito.verify(this.projectUserRepository, Mockito.times(1)).save(Mockito.any());

    }

    @Test
    @DisplayName("User, he is not admin of project, try update a relation with project and throw RuntimeException")
    void updateByIdCase2() {
        ProjectUserModel projectUserModel = ProjectUserlFactory.ProjectUserFactoryBuilder.projectUserModel();

        UserModel userCreator = UserFactory.UserFactoryBuilder.userModel();
        userCreator.setId(2L);
        projectUserModel.getProject().setCreator(userCreator);

        ProjectPositionModel actuallyPosition = projectUserModel.getPosition();

        ProjectPositionModel newPosition = ProjectPositionFactory.ProjectPositionFactoryBuilder.projectPositionModel(projectUserModel.getProject());
        newPosition.setId(2L);

        Mockito.when(this.projectUserRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(projectUserModel));

        Mockito.when(this.projectPositionRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(newPosition));

        assertThrows(NotPermitException.class, ()->this.projectUserService.updateById(projectUserModel.getId(), 2L, projectUserModel.getUser()));

    }
}