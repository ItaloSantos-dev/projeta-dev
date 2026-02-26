package santzin.projeta.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import santzin.projeta.dev.DTOs.CreateProjectRequestDTO;
import santzin.projeta.dev.DTOs.ProjectResponseDTO;
import santzin.projeta.dev.DTOs.UpdateProjectRequestDTO;
import santzin.projeta.dev.mapper.ProjectMapper;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.repository.ProjectRepository;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;


    public List<ProjectResponseDTO> getAll(){
        List<ProjectModel> projects = this.projectRepository.findAll();
        return projects.stream()
                .map(p -> this.projectMapper.modelToResponse(p))
                .toList();
    }

    public ProjectResponseDTO createProject(CreateProjectRequestDTO requestDTO, UserModel user){
        ProjectModel projectModel = this.projectMapper.requestCreateToModel(requestDTO, user);
        ProjectResponseDTO response = this.projectMapper.modelToResponse(this.projectRepository.save(projectModel));
        return response;
    }

    public ProjectResponseDTO getById(Long id){
         return this.projectMapper.modelToResponse(this.projectRepository.findById(id)
                 .orElseThrow(()->new RuntimeException("Deu ruin Pegar")));
    }

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
