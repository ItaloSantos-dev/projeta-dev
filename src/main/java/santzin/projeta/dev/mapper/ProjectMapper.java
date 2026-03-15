package santzin.projeta.dev.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import santzin.projeta.dev.DTOs.project.CreateProjectRequestDTO;
import santzin.projeta.dev.DTOs.project.ProjectResponseDTO;
import santzin.projeta.dev.DTOs.project.UpdateProjectRequestDTO;
import santzin.projeta.dev.DTOs.user.UserResponseDTO;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.model.enums.ProjectStatus;
import santzin.projeta.dev.model.enums.StatusRequestProject;
import santzin.projeta.dev.repository.UserRepository;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectMapper {
    @Autowired
    private UserRepository userRepository;

    private String  titleToSlug(String  title){
        String normalized = Normalizer.normalize(title, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return normalized
                .toLowerCase()
                .trim()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");

    }

    public ProjectModel requestCreateToModel(CreateProjectRequestDTO requestDTO, UserModel user) {
        ProjectModel project = new ProjectModel();

        project.setTitle(requestDTO.title());
        project.setSlug(titleToSlug(requestDTO.title()));
        project.setImgUrl(requestDTO.imgUrl());
        project.setDescription(requestDTO.description());
        project.setStack(requestDTO.stack());
        project.setStatus(ProjectStatus.OPEND);
        project.setCreator(user);
        project.setInputType(requestDTO.inputType());
        project.setPaid(requestDTO.paid());
        project.setRepositoryLink(requestDTO.repositoryLink());
        project.setCreatedAt(LocalDate.now());

        return project;
    }

    public ProjectResponseDTO modelToResponse(ProjectModel project) {

        List<String> users = new ArrayList<>();

        if (project.getUsers()!=null){
            users = project.getUsers().stream()
                    .map(u -> u.getUser().getUsernameProperty())
                    .toList();
        }

        List<String> positions = new ArrayList<>();
        if (project.getPositions()!=null){
            positions = project.getPositions().stream()
                    .map(p -> p.getName())
                    .toList();
        }

        Integer requestCount = 0;
        if (project.getProjectRequests()!=null){
            requestCount = project.getProjectRequests().stream().filter( pr-> pr.getStatus()== StatusRequestProject.PENDING).toList().size();
        }


        return new ProjectResponseDTO(
                project.getId(),
                project.getTitle(),
                project.getSlug(),
                project.getImgUrl(),
                project.getDescription(),
                project.getStack(),
                project.getCreator().getUsernameProperty(),
                project.getStatus(),
                project.getInputType(),
                project.getPaid(),
                project.getRepositoryLink(),
                project.getCreatedAt(),
                requestCount,
                users,
                positions
        );
    }

    public void updateModel(ProjectModel project, UpdateProjectRequestDTO requestDTO) {

        project.setTitle(requestDTO.title());
        project.setSlug(titleToSlug(requestDTO.title()));
        project.setImgUrl(requestDTO.imgUrl());
        project.setDescription(requestDTO.description());
        project.setStack(requestDTO.stack());
        project.setStatus(requestDTO.status());
        project.setInputType(requestDTO.inputType());
        project.setPaid(requestDTO.paid());
        project.setRepositoryLink(requestDTO.repositoryLink());

    }

}
