package santzin.projeta.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import santzin.projeta.dev.DTOs.project_request.UpdateProjectRequestRequestDTO;
import santzin.projeta.dev.DTOs.project_request_notification.ProjectRequestNotificationResponseDTO;
import santzin.projeta.dev.exception.ItemNotFoundException;
import santzin.projeta.dev.exception.NotPermitException;
import santzin.projeta.dev.mapper.ProjectRequestNotificationMapper;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.ProjectRequestModel;
import santzin.projeta.dev.model.ProjectRequestNotificationModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.model.enums.StatusRequestProject;
import santzin.projeta.dev.repository.ProjectRepository;
import santzin.projeta.dev.repository.ProjectRequestNotificationRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectRequestNotificationService {
    @Autowired
    private ProjectRequestNotificationRepository projectRequestNotificationRepository;

    @Autowired
    private ProjectRequestNotificationMapper projectRequestNotificationMapper;

    @Autowired
    private ProjectRequestService projectRequestService;

    @Autowired
    private ProjectRepository projectRepository;

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

    public List<ProjectRequestNotificationResponseDTO> getNotificationsRequestsOfProjectBySlug(
            String slug, UserModel user
    ){
        ProjectModel projectModel = this.projectRepository.findBySlug(slug)
                .orElseThrow(ItemNotFoundException::new);

        if (!projectModel.getCreator().getId().equals(user.getId()))
            throw new NotPermitException();

        return this.projectRequestNotificationRepository.findByProjectRequest_Project_Id(projectModel.getId()).stream()
                .map(prnm -> this.projectRequestNotificationMapper.modelToResponse(prnm))
                .toList();

    }

    public void updateNotificationAndRequest(Long notificationId, UpdateProjectRequestRequestDTO requestDTO, UserModel user){
        ProjectRequestNotificationModel prnm = this.projectRequestNotificationRepository.findById(notificationId)
                .orElseThrow(ItemNotFoundException::new);

        if (!prnm.getUser().getId().equals(user.getId()))
            throw new NotPermitException();

        if (!prnm.getProjectRequest().getProject().getCreator().getId().equals(user.getId()))
            throw new NotPermitException();

        prnm.setRead(true);
        prnm.setReadAt(LocalDate.now());

        this.projectRequestService.updateStatus(user, prnm.getProjectRequest().getId(), requestDTO);

        this.projectRequestNotificationRepository.save(prnm);
    }
}
