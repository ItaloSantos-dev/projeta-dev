package santzin.projeta.dev.mapper;

import org.springframework.stereotype.Component;
import santzin.projeta.dev.model.ProjectRequestModel;
import santzin.projeta.dev.model.ProjectRequestNotificationModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.model.enums.TypeProjectRequestNotification;

import java.time.LocalDate;

@Component
public class ProjectRequestNotificationMapper {
    public ProjectRequestNotificationModel requestToModel(ProjectRequestModel projectRequestModel, UserModel user){
        ProjectRequestNotificationModel projectRequestNotificationModel = new ProjectRequestNotificationModel();

        projectRequestNotificationModel.setProjectRequest(projectRequestModel);
        projectRequestNotificationModel.setUser(user);
        projectRequestNotificationModel.setRead(false);
        projectRequestNotificationModel.setType(TypeProjectRequestNotification.REQUEST);
        projectRequestNotificationModel.setCreatedAt(LocalDate.now());

        return projectRequestNotificationModel;
    }
}
