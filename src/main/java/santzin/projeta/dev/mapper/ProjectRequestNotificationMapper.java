package santzin.projeta.dev.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import santzin.projeta.dev.DTOs.project_request_notification.ProjectRequestNotificationResponseDTO;
import santzin.projeta.dev.model.ProjectRequestModel;
import santzin.projeta.dev.model.ProjectRequestNotificationModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.model.enums.TypeProjectRequestNotification;

import java.time.LocalDate;

@Component
public class ProjectRequestNotificationMapper {

    @Autowired
    private ProjectRequestMapper projectRequestMapper;

    public ProjectRequestNotificationModel requestToModel(ProjectRequestModel projectRequestModel, UserModel user){
        ProjectRequestNotificationModel projectRequestNotificationModel = new ProjectRequestNotificationModel();

        projectRequestNotificationModel.setProjectRequest(projectRequestModel);
        projectRequestNotificationModel.setUser(user);
        projectRequestNotificationModel.setRead(false);
        projectRequestNotificationModel.setType(TypeProjectRequestNotification.REQUEST);
        projectRequestNotificationModel.setCreatedAt(LocalDate.now());

        return projectRequestNotificationModel;
    }

    public ProjectRequestNotificationResponseDTO modelToResponse(ProjectRequestNotificationModel projectRequestNotificationModel){
        return new ProjectRequestNotificationResponseDTO(
                projectRequestNotificationModel.getId(),
                this.projectRequestMapper.modelToResonse(projectRequestNotificationModel.getProjectRequest()),
                projectRequestNotificationModel.getProjectRequest().getUser().getUsernameProperty(),
                projectRequestNotificationModel.getUser().getUsernameProperty(),
                projectRequestNotificationModel.getRead(),
                projectRequestNotificationModel.getType(),
                projectRequestNotificationModel.getCreatedAt()
                );
    }
}
