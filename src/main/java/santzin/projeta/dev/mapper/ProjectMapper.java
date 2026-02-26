package santzin.projeta.dev.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import santzin.projeta.dev.DTOs.CreateProjectRequestDTO;
import santzin.projeta.dev.DTOs.ProjectResponseDTO;
import santzin.projeta.dev.DTOs.UpdateProjectRequestDTO;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.ProjectUserModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.model.enums.ProjectStatus;
import santzin.projeta.dev.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Component
public class ProjectMapper {
    @Autowired
    private UserRepository userRepository;

    public ProjectModel requestCreateToModel(CreateProjectRequestDTO requestDTO, UserModel user) {
        ProjectModel project = new ProjectModel();

        project.setTitle(requestDTO.title());
        project.setImgUrl(requestDTO.imgUrl());
        project.setDescription(requestDTO.description());
        project.setStack(requestDTO.stack());
        project.setStatus(ProjectStatus.OPEND);
        project.setCreator(user);
        project.setInputType(requestDTO.inputType());
        project.setRepositoryLink(requestDTO.repositoryLink());
        project.setCreatedAt(LocalDate.now());


        return project;
    }

    public ProjectResponseDTO modelToResponse(ProjectModel project) {

        return new ProjectResponseDTO(
                project.getId(),
                project.getTitle(),
                project.getImgUrl(),
                project.getDescription(),
                project.getStack(),
                project.getStatus(),
                project.getInputType(),
                project.getRepositoryLink(),
                project.getCreatedAt(),
                project.getUsers(),
                project.getPositions()
        );
    }

    public void updateModel(ProjectModel project, UpdateProjectRequestDTO requestDTO) {

        project.setTitle(requestDTO.title());
        project.setImgUrl(requestDTO.imgUrl());
        project.setDescription(requestDTO.description());
        project.setStack(requestDTO.stack());
        project.setStatus(requestDTO.status());
        project.setInputType(requestDTO.inputType());
        project.setRepositoryLink(requestDTO.repositoryLink());

    }

}
