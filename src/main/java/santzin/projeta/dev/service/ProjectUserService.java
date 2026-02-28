package santzin.projeta.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import santzin.projeta.dev.DTOs.project_user.CreateProjectUserDTO;
import santzin.projeta.dev.DTOs.project_user.ProjectUserResponseDTO;
import santzin.projeta.dev.mapper.ProjectUserMapper;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.ProjectPositionModel;
import santzin.projeta.dev.model.ProjectUserModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.model.enums.ProjectStatus;
import santzin.projeta.dev.repository.ProjectPositionRepository;
import santzin.projeta.dev.repository.ProjectRepository;
import santzin.projeta.dev.repository.ProjectUserRepository;
import santzin.projeta.dev.repository.UserRepository;

@Service
public class ProjectUserService {
    @Autowired
    private ProjectUserRepository projectUserRepository;

    @Autowired
    private ProjectUserMapper projectUserMapper;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectPositionRepository projectPositionRepository;

    public ProjectUserResponseDTO getProjectUserById(Long id){
        ProjectUserModel projectUser = this.projectUserRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Não achou project/user"));
        return this.projectUserMapper.modelToResponse(projectUser);
    }

    public ProjectUserResponseDTO createProjectUser(CreateProjectUserDTO createProjectUserDTO){
        ProjectModel project = this.projectRepository.findById(createProjectUserDTO.projectId())
                .orElseThrow(()-> new RuntimeException("Não achou project"));

        if (project.getStatus()!= ProjectStatus.OPEND)
            throw new RuntimeException("O projesto esta " +project.getStatus() );

        UserModel user = this.userRepository.findById(createProjectUserDTO.userId())
                .orElseThrow(()-> new RuntimeException("Não achou user"));

        ProjectPositionModel position = this.projectPositionRepository.findById(createProjectUserDTO.positionId())
                .orElseThrow(()-> new RuntimeException("Não achou position"));

        ProjectUserModel projectUserModel = this.projectUserMapper.createToModel(project, user, position);

        return this.projectUserMapper.modelToResponse(this.projectUserRepository.save(projectUserModel));

    }

    @Transactional
    public void deleteByUserIdAnProjectId(Long projectUserId, UserModel user){
        ProjectUserModel projectUser = this.projectUserRepository.findById(projectUserId)
                .orElseThrow(()-> new RuntimeException("Relação nao existe"));

        if(!projectUser.getProject().getCreator().getId().equals(user.getId()))
            throw  new RuntimeException("Essr projeto ne teu n p tu apagar user");

        this.projectUserRepository.deleteById(projectUserId);
    }

    public ProjectUserResponseDTO updateById(Long id, Long positionId, UserModel user){
        ProjectUserModel projectUserModel = this.projectUserRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Relação nao existe"));

        ProjectPositionModel position = this.projectPositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Essa posição n existe"));

        if (!projectUserModel.getProject().getCreator().getId().equals(user.getId()))
            throw  new RuntimeException("Essr projeto ne teu n p tu apagar user");
        if (!projectUserModel.getProject().getId().equals(position.getProject().getId()))
            throw  new RuntimeException("Essa position não é desse projeto");

        projectUserModel.setPosition(position);

        return this.projectUserMapper.modelToResponse(this.projectUserRepository.save(projectUserModel));


    }
}
