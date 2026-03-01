package santzin.projeta.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import santzin.projeta.dev.DTOs.project_position.CreateProjectPositionRequestDTO;
import santzin.projeta.dev.DTOs.project_position.ProjectPositionResponseDTO;
import santzin.projeta.dev.exception.ItemNotFoundException;
import santzin.projeta.dev.mapper.ProjectPositionMapper;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.ProjectPositionModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.repository.ProjectPositionRepository;
import santzin.projeta.dev.repository.ProjectRepository;

import java.util.List;

@Service
public class ProjectPositionService {
    @Autowired
    private ProjectPositionRepository projectPositionRepository;

    @Autowired
    private ProjectPositionMapper projectPositionMapper;

    @Autowired
    private ProjectRepository projectRepository;


    public List<String> getAllOfProject(Long projectId){
        return this.projectPositionRepository.findAllByProjectId(projectId)
                .stream().map(ProjectPositionModel::getName).toList();
    }

    public ProjectPositionResponseDTO getById(Long id){
        ProjectPositionModel position = this.projectPositionRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Deu ruin achar essa posição"));
        return this.projectPositionMapper.modelToResponse(position);
    }

    @Transactional
    public ProjectPositionResponseDTO createProjectPosition(
            CreateProjectPositionRequestDTO createProjectPositionRequestDTO, UserModel user
    ){
        ProjectModel projectModel = this.projectRepository.findById(createProjectPositionRequestDTO.projectId())
                .orElseThrow(()-> new RuntimeException("Deu ruin achar projeto pra ligar com position"));

        if (!projectModel.getCreator().getId().equals(user.getId())) throw new RuntimeException("Esse projeto ne teu n");

        ProjectPositionModel newPosition = this.projectPositionMapper.requestToModel(createProjectPositionRequestDTO.name(), projectModel);

        return this.projectPositionMapper.modelToResponse(this.projectPositionRepository.save(newPosition));
    }

    public void deleteById(Long id, UserModel user){
        ProjectPositionModel position = this.projectPositionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Deu ruin apagar"));

        if(!position.getProject().getCreator().getId().equals(user.getId()))
            throw  new RuntimeException("Deu ruin, tu né dono não");

        this.projectPositionRepository.deleteById(id);
    }

    public ProjectPositionResponseDTO updateById(Long id, String newName, UserModel user){
        ProjectPositionModel position = this.projectPositionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Deu ruin apagar"));

        if(!position.getProject().getCreator().getId().equals(user.getId()))
            throw  new RuntimeException("Deu ruin, tu né dono não");

        position.setName(newName);
        return this.projectPositionMapper.modelToResponse(this.projectPositionRepository.save(position));
    }
}
