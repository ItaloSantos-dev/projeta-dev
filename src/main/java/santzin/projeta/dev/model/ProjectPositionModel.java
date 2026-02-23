package santzin.projeta.dev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_project_position")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectPositionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private  String  name;

    @OneToMany(mappedBy = "position")
    private List<ProjectUserModel> users;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectModel project;
}
