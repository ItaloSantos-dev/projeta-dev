package santzin.projeta.dev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import santzin.projeta.dev.model.enums.ProjectInputType;
import santzin.projeta.dev.model.enums.ProjectStatus;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_project")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String title;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "stack", nullable = false )
    private String stack;

    @Column(name = "status", nullable = false)
    private ProjectStatus status;

    @Column(name = "input_type", nullable = false)
    private ProjectInputType inputType;

    @Column(name = "repository_link")
    private String repositoryLink;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @OneToMany(mappedBy = "project")
    private List<ProjectUserModel> users;

    @OneToMany(mappedBy = "project")
    private List<ProjectPositionModel> positions;
}

