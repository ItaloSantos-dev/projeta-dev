package santzin.projeta.dev.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import santzin.projeta.dev.DTOs.project_position.CreateProjectPositionRequestDTO;
import santzin.projeta.dev.factory.ProjectFactory;
import santzin.projeta.dev.factory.ProjectPositionFactory;
import santzin.projeta.dev.factory.UserFactory;
import santzin.projeta.dev.mapper.ProjectPositionMapper;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.ProjectPositionModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.repository.ProjectPositionRepository;
import santzin.projeta.dev.repository.ProjectRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProjectPositionServiceTest {

    @InjectMocks
    private ProjectPositionService projectPositionService;

    @Mock
    private ProjectPositionRepository projectPositionRepository;

    @Mock
    private ProjectPositionMapper projectPositionMapper;

    @Mock
    private ProjectRepository projectRepository;


    @Test
    @DisplayName("Admin try create position in his project should create")
    void createProjectPositionCase1() {
        ProjectModel project = ProjectFactory.ProjectFactoryBuilder.projectModel();
        String name = "Position Name";
        UserModel user = UserFactory.UserFactoryBuilder.userModel();
        project.setCreator(user);

        Mockito.when(this.projectRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(project));

        CreateProjectPositionRequestDTO request = new CreateProjectPositionRequestDTO(name, project.getId());

        this.projectPositionService.createProjectPosition(request, user);

        Mockito.verify(this.projectPositionRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Another user try create position in project that is not you, should throw")
    void createProjectPositionCase2() {
        ProjectModel project = ProjectFactory.ProjectFactoryBuilder.projectModel();
        String name = "Position Name";
        UserModel user = UserFactory.UserFactoryBuilder.userModel();
        project.setCreator(user);

        UserModel anotherUser = UserFactory.UserFactoryBuilder.userModel();
        anotherUser.setId(2L);
        CreateProjectPositionRequestDTO request = new CreateProjectPositionRequestDTO(name, project.getId());

        Mockito.when(this.projectRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(project));

        assertThrows(RuntimeException.class, ()-> this.projectPositionService.createProjectPosition(request, anotherUser));
    }

    @Test
    @DisplayName("Admin try delete position in his project should delete")
    void deleteByIdCase1() {
        ProjectModel project = ProjectFactory.ProjectFactoryBuilder.projectModel();
        UserModel user = UserFactory.UserFactoryBuilder.userModel();
        project.setCreator(user);

        ProjectPositionModel position = ProjectPositionFactory.ProjectPositionFactoryBuilder.projectPositionModel(project);

        Mockito.when(this.projectPositionRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(position));

        this.projectPositionService.deleteById(project.getId(), user);

        Mockito.verify(this.projectPositionRepository, Mockito.times(1)).deleteById(Mockito.any());
    }

    @Test
    @DisplayName("Another user try create position in project that is not you, should throw")
    void deleteByIdCase2() {
        ProjectModel project = ProjectFactory.ProjectFactoryBuilder.projectModel();
        UserModel user = UserFactory.UserFactoryBuilder.userModel();
        project.setCreator(user);

        UserModel anotherUser = UserFactory.UserFactoryBuilder.userModel();
        anotherUser.setId(2L);

        ProjectPositionModel position = ProjectPositionFactory.ProjectPositionFactoryBuilder.projectPositionModel(project);

        Mockito.when(this.projectPositionRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(position));

        assertThrows(RuntimeException.class, ()-> this.projectPositionService.deleteById(project.getId(), anotherUser));
    }

    @Test
    @DisplayName("Admin try update position in his project should update")
    void updateByIdCase1() {
        ProjectModel project = ProjectFactory.ProjectFactoryBuilder.projectModel();

        String newName = "New Position Name";

        UserModel user = UserFactory.UserFactoryBuilder.userModel();
        project.setCreator(user);

        ProjectPositionModel position = ProjectPositionFactory.ProjectPositionFactoryBuilder.projectPositionModel(project);

        Mockito.when(this.projectPositionRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(position));

        this.projectPositionService.updateById(position.getId(), newName, user);
        Mockito.verify(this.projectPositionRepository, Mockito.times(1)).save(Mockito.any());


    }

    @Test
    @DisplayName("Another user try update position in project that is not you, should throw")
    void updateByIdCase2() {
        ProjectModel project = ProjectFactory.ProjectFactoryBuilder.projectModel();

        String newName = "New Position Name";

        UserModel user2 = UserFactory.UserFactoryBuilder.userModel();
        user2.setId(2L);

        UserModel user = UserFactory.UserFactoryBuilder.userModel();
        project.setCreator(user);

        ProjectPositionModel position = ProjectPositionFactory.ProjectPositionFactoryBuilder.projectPositionModel(project);

        Mockito.when(this.projectPositionRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(position));

        assertThrows(RuntimeException.class, () -> this.projectPositionService.updateById(position.getId(), newName, user2));


    }
}