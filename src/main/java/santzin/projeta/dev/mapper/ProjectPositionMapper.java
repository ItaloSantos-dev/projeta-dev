package santzin.projeta.dev.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import santzin.projeta.dev.DTOs.project_position.ProjectPositionResponseDTO;
import santzin.projeta.dev.DTOs.project_position.ProjectPositionSimplifiedResponseDTO;
import santzin.projeta.dev.DTOs.user.UserResponseDTO;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.ProjectPositionModel;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectPositionMapper {
    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private UserMapper userMapper;



    public ProjectPositionResponseDTO modelToResponse(ProjectPositionModel position){
        List<UserResponseDTO> users = new ArrayList<>();
        if(position.getUsers()!=null) {
             users= position.getUsers().stream()
                    .map(u -> this.userMapper.modelToResponse(u.getUser())
                    ).toList();
        }
        return new ProjectPositionResponseDTO(
                position.getId(),
                position.getName(),
                this.projectMapper.modelToResponse(position.getProject()),
                users
        );
    }

    public ProjectPositionModel requestToModel(String name, ProjectModel project){
        ProjectPositionModel newPosition = new ProjectPositionModel();
        newPosition.setName(name);
        newPosition.setProject(project);
        return newPosition;
    }

    public ProjectPositionSimplifiedResponseDTO modelToSimplifiedResponse(ProjectPositionModel position){
        return new ProjectPositionSimplifiedResponseDTO(position.getId(), position.getName());
    }
}
