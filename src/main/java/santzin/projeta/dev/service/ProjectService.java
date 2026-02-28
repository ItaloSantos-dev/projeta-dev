package santzin.projeta.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import santzin.projeta.dev.DTOs.project.CreateProjectRequestDTO;
import santzin.projeta.dev.DTOs.project.MyProjectsResponseDTO;
import santzin.projeta.dev.DTOs.project.ProjectResponseDTO;
import santzin.projeta.dev.DTOs.project.UpdateProjectRequestDTO;
import santzin.projeta.dev.mapper.ProjectMapper;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.ProjectPositionModel;
import santzin.projeta.dev.model.ProjectUserModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.repository.ProjectPositionRepository;
import santzin.projeta.dev.repository.ProjectRepository;
import santzin.projeta.dev.repository.ProjectUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectPositionRepository projectPositionRepository;

    @Autowired
    private ProjectUserRepository projectUserRepository;



    public MyProjectsResponseDTO getMyProjects(UserModel user){

        List<Long> idsOfMyProjects = new ArrayList<>();

        List<ProjectResponseDTO> myProjects = this.projectRepository.findByCreatorId(user.getId())
                .stream()
                .map( mp -> {
                    idsOfMyProjects.add(mp.getId());
                    return this.projectMapper.modelToResponse(mp);
                })
                .toList();



        List<ProjectResponseDTO> projectsIPaticipe = projectUserRepository.findByUserId(user.getId())
                .stream()
                .filter(up -> !idsOfMyProjects.contains(up.getProject().getId()))
                .map(up -> this.projectMapper.modelToResponse(up.getProject()))
                .toList();

        return new MyProjectsResponseDTO(myProjects, projectsIPaticipe);

    }


    public List<ProjectResponseDTO> getAll(){
        List<ProjectModel> projects = this.projectRepository.findAll();
        return projects.stream()
                .map(p -> this.projectMapper.modelToResponse(p))
                .toList();
    }

    @Transactional
    public ProjectResponseDTO createProject(CreateProjectRequestDTO requestDTO, UserModel user){
        //Salva o projeto
        ProjectModel newProjectModel = this.projectRepository
                .save(this.projectMapper.requestCreateToModel(requestDTO, user));

        //Cria uma posição principal para o projeto
        ProjectPositionModel principalPosition = new ProjectPositionModel();
        principalPosition.setName(requestDTO.principalPosition());
        principalPosition.setProject(newProjectModel);
        ProjectPositionModel newPrincipalPosition = this.projectPositionRepository.save(principalPosition);

        //Cria a relação projeto - User e adiciona o campo posição
        ProjectUserModel projectUserModel = new ProjectUserModel();
        projectUserModel.setUser(user);
        projectUserModel.setProject(newProjectModel);
        projectUserModel.setPosition(newPrincipalPosition);
        this.projectUserRepository.save(projectUserModel);

        return  this.projectMapper.modelToResponse(newProjectModel);

    }

    public ProjectResponseDTO getById(Long id){
         return this.projectMapper.modelToResponse(this.projectRepository.findById(id)
                 .orElseThrow(()->new RuntimeException("Deu ruin Pegar")));
    }

    @Transactional
    public void deleteById(Long id, UserModel user){
        ProjectModel project = this.projectRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Deu ruin apagar"));

        if(!project.getCreator().getId().equals(user.getId()))
            throw  new RuntimeException("Deu ruin, tu né dono não");


        this.projectRepository.deleteById(id);
    }

    public ProjectResponseDTO UpdateById(Long id, UpdateProjectRequestDTO requestDTO, UserModel user){

        ProjectModel project = this.projectRepository.findById(id).orElseThrow(()->new RuntimeException("Deu ruin editar"));

        if(!project.getCreator().getId().equals(user.getId()))
            throw  new RuntimeException("Deu ruin, tu né dono não");

        this.projectMapper.updateModel(project,requestDTO);

        return this.projectMapper.modelToResponse(this.projectRepository.save(project));
    }




}
