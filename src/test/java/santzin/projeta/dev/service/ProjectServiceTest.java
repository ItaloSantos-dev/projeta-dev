package santzin.projeta.dev.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import santzin.projeta.dev.DTOs.project.CreateProjectRequestDTO;
import santzin.projeta.dev.DTOs.project.ProjectResponseDTO;
import santzin.projeta.dev.DTOs.project.UpdateProjectRequestDTO;
import santzin.projeta.dev.factory.ProjectFactory;
import santzin.projeta.dev.factory.ProjectPositionFactory;
import santzin.projeta.dev.factory.ProjectUserlFactory;
import santzin.projeta.dev.factory.UserFactory;
import santzin.projeta.dev.mapper.ProjectMapper;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.ProjectPositionModel;
import santzin.projeta.dev.model.ProjectUserModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.model.enums.ProjectInputType;
import santzin.projeta.dev.model.enums.UserExperienceLevel;
import santzin.projeta.dev.model.enums.UserRole;
import santzin.projeta.dev.repository.ProjectPositionRepository;
import santzin.projeta.dev.repository.ProjectRepository;
import santzin.projeta.dev.repository.ProjectUserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;

    @Mock
    private ProjectPositionRepository projectPositionRepository;

    @Mock
    private ProjectUserRepository projectUserRepository;



    @Test
    @DisplayName("user try create a project and return ProjectResponseDTO")
    void createProjectCase1() {
        CreateProjectRequestDTO request = ProjectFactory.ProjectFactoryBuilder.projectRequestDTO();

        UserModel user = UserFactory.UserFactoryBuilder.userModel();

        ProjectModel project = ProjectFactory.ProjectFactoryBuilder.projectModel();

        ProjectPositionModel position = ProjectPositionFactory.ProjectPositionFactoryBuilder.projectPositionModel(project);


        ProjectUserModel projectUserModel = ProjectUserlFactory.ProjectUserFactoryBuilder.projectUserModel();

        ProjectResponseDTO projectResponseDTO = ProjectFactory.ProjectFactoryBuilder.projectResponseDTO(project);

         Mockito.when(this.projectRepository.existsByTitleAndCreatorId(Mockito.any(), Mockito.any())).thenReturn(false);

        Mockito.when(this.projectRepository.save(Mockito.any()))
                .thenReturn(project);

         Mockito.when(this.projectMapper.requestCreateToModel(request, user))
                 .thenReturn(project);

         Mockito.when(this.projectPositionRepository.save(Mockito.any()))
                         .thenReturn(position);

         Mockito.when(this.projectUserRepository.save(Mockito.any()))
                         .thenReturn(projectUserModel);

         Mockito.when(this.projectMapper.modelToResponse(project))
                 .thenReturn(projectResponseDTO);




        assertEquals(projectResponseDTO, this.projectService.createProject(request, user));


    }
    @Test
    @DisplayName("User try create project with name already exists and return RuntimeException")
    void createProjectCase2(){
        Mockito.when(this.projectRepository.existsByTitleAndCreatorId(Mockito.any(), Mockito.any())).thenReturn(true);

        assertThrows(RuntimeException.class, ()-> this.projectService.createProject(ProjectFactory.ProjectFactoryBuilder.projectRequestDTO(), UserFactory.UserFactoryBuilder.userModel()));
    }

    @Test
    @DisplayName("User try delete a project that he is the creator and return void")
    void deleteByIdCase1() {
        UserModel user = UserFactory.UserFactoryBuilder.userModel();

        ProjectModel project = ProjectFactory.ProjectFactoryBuilder.projectModel();

        project.setCreator(user);

        Mockito.when(this.projectRepository.findById(Mockito.any())).thenReturn(Optional.of(project));

        this.projectService.deleteById(1L, user);

        Mockito.verify(this.projectRepository, Mockito.times(1)).deleteById(project.getId());
    }

    @Test
    @DisplayName("User try delete a project that he is not the creator and throw RuntimeException")
    void deleteByIdCase2() {
        UserModel user = UserFactory.UserFactoryBuilder.userModel();

        ProjectModel project = ProjectFactory.ProjectFactoryBuilder.projectModel();

        UserModel user2 = UserFactory.UserFactoryBuilder.userModel();
        user2.setId(2L);

        project.setCreator(user);

        Mockito.when(this.projectRepository.findById(Mockito.any())).thenReturn(Optional.of(project));

        assertThrows(RuntimeException.class, ()-> this.projectService.deleteById(project.getId(), user2));

    }
    @Test
    @DisplayName("User try update a project that he is the creator and return ProjectResponseDTO")
    void updateByIdCase1() {
        UpdateProjectRequestDTO request = ProjectFactory.ProjectFactoryBuilder.updateProjectRequestDTO();

        UserModel user = UserFactory.UserFactoryBuilder.userModel();

        ProjectModel project = ProjectFactory.ProjectFactoryBuilder.projectModel();
        project.setCreator(user);

        Mockito.when(this.projectRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(project));


        this.projectService.UpdateById(project.getId(), request, user);

         Mockito.verify(this.projectMapper, Mockito.times(1)).updateModel(project, request);

        Mockito.verify(this.projectRepository, Mockito.times(1)).save(Mockito.any());

    }

    @Test
    @DisplayName("User try update a project that he is not the creator and throw RuntimeException")
    void updateByIdCase2() {
        UpdateProjectRequestDTO request = ProjectFactory.ProjectFactoryBuilder.updateProjectRequestDTO();

        UserModel user = UserFactory.UserFactoryBuilder.userModel();

        ProjectModel project = ProjectFactory.ProjectFactoryBuilder.projectModel();
        project.setCreator(user);

        UserModel user2 = UserFactory.UserFactoryBuilder.userModel();
        user2.setId(2L);

        Mockito.when(this.projectRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(project));



        assertThrows(RuntimeException.class, () -> this.projectService.UpdateById(project.getId(), request, user2));

    }
}