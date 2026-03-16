package santzin.projeta.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import santzin.projeta.dev.DTOs.project_request.ProjectRequestResponseDTO;
import santzin.projeta.dev.DTOs.project_request.UpdateProjectRequestRequestDTO;
import santzin.projeta.dev.DTOs.project_user.CreateProjectUserDTO;
import santzin.projeta.dev.exception.ItemNotFoundException;
import santzin.projeta.dev.exception.NotPermitException;
import santzin.projeta.dev.mapper.ProjectRequestMapper;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.ProjectRequestModel;
import santzin.projeta.dev.model.ProjectRequestNotificationModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.model.enums.ProjectStatus;
import santzin.projeta.dev.model.enums.StatusRequestProject;
import santzin.projeta.dev.model.enums.TypeProjectRequestNotification;
import santzin.projeta.dev.repository.ProjectRepository;
import santzin.projeta.dev.repository.ProjectRequestNotificationRepository;
import santzin.projeta.dev.repository.ProjectRequestRespository;
import santzin.projeta.dev.repository.ProjectUserRepository;

import java.time.LocalDate;

@Service
public class ProjectRequestService {

    @Autowired
    private ProjectRequestRespository projectRequestRespository;

    @Autowired
    private ProjectRequestNotificationService projectRequestNotificationService;

    @Autowired
    private ProjectRequestMapper projectRequestMapper;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectRequestNotificationRepository projectRequestNotificationRepository;

    @Autowired
    private ProjectUserRepository projectUserRepository;

    @Autowired
    private ProjectUserService projectUserService;

    @Transactional
    public ProjectRequestResponseDTO create(UserModel user, Long projectId){

        //busca projeto
        ProjectModel project = this.projectRepository.findById(projectId)
                .orElseThrow(() -> new ItemNotFoundException(projectId, "projeto"));

        //verifica se não é o dono
        if (user.getId().equals(project.getCreator().getId()))
            throw new NotPermitException();

        //verifica se ja é um participante
        if(this.projectUserRepository.existsByUserIdAndProjectId(user.getId(), project.getId()))
            throw new NotPermitException();

        if (project.getStatus()!=ProjectStatus.OPEND)
            throw new NotPermitException();

        //cria pedido
        ProjectRequestModel projectRequestModel = this.projectRequestMapper.requestToModel(user, project);

        projectRequestModel = this.projectRequestRespository.save(projectRequestModel);

        //cria notificação para o criador do projeto
        ProjectRequestNotificationModel projectRequestNotificationModel = this.projectRequestNotificationService.create(projectRequestModel, project.getCreator());

        return this.projectRequestMapper.modelToResonse(projectRequestModel);

    }

    @Transactional
    public void updateStatus (UserModel userModel, Long projectRequestId, UpdateProjectRequestRequestDTO requestDTO){
        ProjectRequestModel projectRequestModel = this.projectRequestRespository.findById(projectRequestId)
                .orElseThrow(() -> new ItemNotFoundException(projectRequestId, "pedido"));

        if (!userModel.getId().equals(projectRequestModel.getProject().getCreator().getId()))
            throw new NotPermitException();

        projectRequestModel.setStatus(requestDTO.newStatus());
        projectRequestModel.setRespondedAt(LocalDate.now());

        this.projectRequestRespository.save(projectRequestModel);

        if (requestDTO.newStatus()==StatusRequestProject.ACCEPTED){
            this.projectUserService.createProjectUser(
                    new CreateProjectUserDTO(
                            projectRequestModel.getUser().getId(),
                            projectRequestModel.getProject().getId(),
                            requestDTO.positionId()
                    ),
                    userModel
            );
        }

        this.projectRequestNotificationService.create(projectRequestModel, projectRequestModel.getUser());

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

        this.updateStatus(user, prnm.getProjectRequest().getId(), requestDTO);

        this.projectRequestNotificationRepository.save(prnm);
    }
}
