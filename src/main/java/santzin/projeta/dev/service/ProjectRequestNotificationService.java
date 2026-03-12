package santzin.projeta.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import santzin.projeta.dev.mapper.ProjectRequestNotificationMapper;
import santzin.projeta.dev.model.ProjectRequestModel;
import santzin.projeta.dev.model.ProjectRequestNotificationModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.repository.ProjectRequestNotificationRepository;

@Service
public class ProjectRequestNotificationService {
    @Autowired
    private ProjectRequestNotificationRepository projectRequestNotificationRepository;

    @Autowired
    private ProjectRequestNotificationMapper projectRequestNotificationMapper;

    public ProjectRequestNotificationModel create(ProjectRequestModel projectRequestModel, UserModel userModel){
        ProjectRequestNotificationModel projectRequestNotificationModel = this.projectRequestNotificationMapper.requestToModel(projectRequestModel, userModel);

        return this.projectRequestNotificationRepository.save(projectRequestNotificationModel);

    }
}
