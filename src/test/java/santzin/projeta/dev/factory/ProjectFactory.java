package santzin.projeta.dev.factory;

import santzin.projeta.dev.DTOs.project.CreateProjectRequestDTO;
import santzin.projeta.dev.DTOs.project.ProjectResponseDTO;
import santzin.projeta.dev.DTOs.project.UpdateProjectRequestDTO;
import santzin.projeta.dev.model.ProjectModel;
import santzin.projeta.dev.model.enums.ProjectInputType;
import santzin.projeta.dev.model.enums.ProjectStatus;

import java.util.List;

public class ProjectFactory {
    public static class ProjectFactoryBuilder {
        public static CreateProjectRequestDTO projectRequestDTO(){
            return new CreateProjectRequestDTO(
                    "Sistema de Gestão de Projetos",
                    "https://meusite.com/imagens/projeto.png",
                    "Sistema para gerenciar projetos e usuários com permissões",
                    "Java, Spring Boot, PostgreSQL",
                    ProjectInputType.FREE,
                    "https://github.com/italo/projeto-gestao",
                    "Backend Developer"
            );
        }

        public static ProjectModel projectModel( ){
            CreateProjectRequestDTO request = ProjectFactoryBuilder.projectRequestDTO();
            ProjectModel project = new ProjectModel();
            project.setId(1L);
            project.setTitle(request.title());
            project.setImgUrl(request.imgUrl());
            project.setDescription(request.description());
            project.setStack(request.stack());
            project.setInputType(request.inputType());
            project.setStatus(ProjectStatus.OPEND);
            project.setRepositoryLink(request.repositoryLink());

            return project;
        }

        public static ProjectResponseDTO projectResponseDTO(ProjectModel project) {
            return new ProjectResponseDTO(
                    project.getId(),
                    project.getTitle(),
                    project.getImgUrl(),
                    project.getDescription(),
                    project.getStack(),
                    project.getStatus(),
                    project.getInputType(),
                    project.getRepositoryLink(),
                    project.getCreatedAt(),
                    List.of(),
                    List.of("Backend Developer")
            );
        }

        public static UpdateProjectRequestDTO updateProjectRequestDTO() {
            return new UpdateProjectRequestDTO(
                    "Sistema de Gestão de Projetos",
                    "https://meusite.com/imagens/projeto.png",
                    "Sistema para gerenciar projetos e usuários com permissões",
                    ProjectStatus.CLOSED,
                    "Java, Spring Boot, PostgreSQL",
                    ProjectInputType.PAID,
                    "https://github.com/italo/projeto-gestao"
                    );
        }
    }
}
