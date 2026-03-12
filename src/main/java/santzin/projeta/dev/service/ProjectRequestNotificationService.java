package santzin.projeta.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import santzin.projeta.dev.DTOs.project_request_notification.ProjectRequestNotificationResponseDTO;
import santzin.projeta.dev.mapper.ProjectRequestNotificationMapper;
import santzin.projeta.dev.model.ProjectRequestModel;
import santzin.projeta.dev.model.ProjectRequestNotificationModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.repository.ProjectRequestNotificationRepository;

import java.util.List;

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

    public List<ProjectRequestNotificationResponseDTO> getAllNotifications(UserModel userModel){
        return this.projectRequestNotificationRepository.findByUserId(userModel.getId()).stream()
                .map(projectRequestNotificationModel
                        -> this.projectRequestNotificationMapper.modelToResponse(projectRequestNotificationModel))
                .toList();
    }
}
