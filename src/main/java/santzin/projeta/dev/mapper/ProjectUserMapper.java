package santzin.projeta.dev.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import santzin.projeta.dev.DTOs.project_user.ProjectUserResponseDTO;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.ProjectPositionModel;
import santzin.projeta.dev.model.ProjectUserModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.repository.ProjectRepository;
import santzin.projeta.dev.repository.UserRepository;

@Component
public class ProjectUserMapper {


    public ProjectUserResponseDTO modelToResponse(ProjectUserModel projectUserModel){
        return new ProjectUserResponseDTO(
                projectUserModel.getId(),
                projectUserModel.getUser().getName(),
                projectUserModel.getProject().getTitle(),
                projectUserModel.getPosition().getName()
        );
    }

    public ProjectUserModel createToModel(ProjectModel projectModel, UserModel user, ProjectPositionModel position){
       ProjectUserModel projectUserModel = new ProjectUserModel();
       projectUserModel.setUser(user);
       projectUserModel.setProject(projectModel);
       projectUserModel.setPosition(position);

       return projectUserModel;

    }
}
