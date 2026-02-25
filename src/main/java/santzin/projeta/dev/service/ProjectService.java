package santzin.projeta.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import santzin.projeta.dev.DTOs.CreateProjectRequestDTO;
import santzin.projeta.dev.DTOs.ProjectResponseDTO;
import santzin.projeta.dev.DTOs.UpdateProjectRequestDTO;
import santzin.projeta.dev.mapper.ProjectMapper;
import santzin.projeta.dev.model.ProjectModel;
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

    public ProjectResponseDTO createProject(CreateProjectRequestDTO requestDTO){
        ProjectModel projectModel = this.projectMapper.requestCreateToModel(requestDTO);
        ProjectResponseDTO response = this.projectMapper.modelToResponse(this.projectRepository.save(projectModel));
        return response;
    }

    public ProjectResponseDTO getById(Long id){
        return this.projectMapper.modelToResponse(this.projectRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Deu ruin Pegar")));
    }

    public void deleteById(Long id){
        if (! projectRepository.existsById(id)) throw new RuntimeException("Deu ruin apagar");
        this.projectRepository.deleteById(id);
    }

    public ProjectResponseDTO UpdateById(Long id, UpdateProjectRequestDTO requestDTO){

        ProjectModel project = this.projectRepository.findById(id).orElseThrow(()->new RuntimeException("Deu ruin editar"));

        this.projectMapper.updateModel(project,requestDTO);

        return this.projectMapper.modelToResponse(this.projectRepository.save(project));
    }




}
