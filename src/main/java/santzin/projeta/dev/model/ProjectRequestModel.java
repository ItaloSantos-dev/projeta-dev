package santzin.projeta.dev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import santzin.projeta.dev.model.enums.StatusRequestProject;

import java.time.LocalDate;

@Entity
@Table(name = "tb_project_request")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectRequestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectModel project;

    @Column(name = "status", nullable = false)
    private StatusRequestProject status;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "responded_at")
    private LocalDate respondedAt;

}
