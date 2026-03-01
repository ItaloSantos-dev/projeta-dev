package santzin.projeta.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import santzin.projeta.dev.DTOs.project_user.CreateProjectUserDTO;
import santzin.projeta.dev.DTOs.project_user.ProjectUserResponseDTO;
import santzin.projeta.dev.exception.ItemNotFoundException;
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
                .orElseThrow(()->new ItemNotFoundException(id, "user"));
        return this.projectUserMapper.modelToResponse(projectUser);
    }

    public ProjectUserResponseDTO createProjectUser(CreateProjectUserDTO createProjectUserDTO, UserModel user ){
        ProjectModel project = this.projectRepository.findById(createProjectUserDTO.projectId())
                .orElseThrow(()->new ItemNotFoundException(createProjectUserDTO.projectId(), "projeto"));

        if (! (user.getId().equals(project.getCreator().getId()) ||
            user.getId().equals(createProjectUserDTO.userId())) )
            throw new RuntimeException("Tu n pode add tu mesmo ou tu n é o criador do projeto");

        if (project.getStatus()!= ProjectStatus.OPEND)
            throw new RuntimeException("O projesto esta " +project.getStatus() );

        UserModel userOfRelation = this.userRepository.findById(createProjectUserDTO.userId())
                .orElseThrow(()->new ItemNotFoundException(createProjectUserDTO.userId(), "user"));

        ProjectPositionModel position = this.projectPositionRepository.findById(createProjectUserDTO.positionId())
                .orElseThrow(()->new ItemNotFoundException(createProjectUserDTO.positionId(), "posição"));

        if (!project.getId().equals(position.getProject().getId()))
            throw  new RuntimeException("Essa position não é desse projeto");

        ProjectUserModel projectUserModel = this.projectUserMapper.createToModel(project, userOfRelation, position);

        return this.projectUserMapper.modelToResponse(this.projectUserRepository.save(projectUserModel));


    }

    @Transactional
    public void deleteProjectUserById(Long projectUserId, UserModel user){
        ProjectUserModel projectUser = this.projectUserRepository.findById(projectUserId)
                .orElseThrow(()->new ItemNotFoundException(projectUserId, "relação entre projeto e user"));

        if(!(projectUser.getProject().getCreator().getId().equals(user.getId()) ||
        projectUser.getUser().getId().equals(user.getId())))
            throw  new RuntimeException("Essr projeto ne teu n p tu apagar user");

        this.projectUserRepository.deleteById(projectUserId);
    }

    public ProjectUserResponseDTO updateById(Long id, Long positionId, UserModel user){
        ProjectUserModel projectUserModel = this.projectUserRepository.findById(id)
                .orElseThrow(()->new ItemNotFoundException(id, "relação entre projeto e user"));

        ProjectPositionModel position = this.projectPositionRepository.findById(positionId)
                .orElseThrow(()->new ItemNotFoundException(positionId, "posição"));

        if (!projectUserModel.getProject().getCreator().getId().equals(user.getId()))
            throw  new RuntimeException("Essr projeto ne teu n p tu editar user");

        if (!projectUserModel.getProject().getId().equals(position.getProject().getId()))
            throw  new RuntimeException("Essa position não é desse projeto");

        projectUserModel.setPosition(position);

        return this.projectUserMapper.modelToResponse(this.projectUserRepository.save(projectUserModel));


    }
}
