package santzin.projeta.dev.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import santzin.projeta.dev.DTOs.project_request.ProjectRequestResponseDTO;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.ProjectRequestModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.model.enums.StatusRequestProject;

import java.time.LocalDate;

@Component
public class ProjectRequestMapper {
    @Autowired
    private ProjectMapper projectMapper;
    public ProjectRequestModel requestToModel(UserModel user, ProjectModel projectModel){
        ProjectRequestModel projectRequestModel = new ProjectRequestModel();

        projectRequestModel.setUser(user);
        projectRequestModel.setProject(projectModel);
        projectRequestModel.setStatus(StatusRequestProject.PENDING);
        projectRequestModel.setCreatedAt(LocalDate.now());

        return projectRequestModel;
    }

    public ProjectRequestResponseDTO modelToResonse(ProjectRequestModel projectRequestModel){
        return new ProjectRequestResponseDTO(projectRequestModel.getId(),this.projectMapper.modelToResponse(projectRequestModel.getProject()),projectRequestModel.getStatus(), projectRequestModel.getCreatedAt());
    }
}
