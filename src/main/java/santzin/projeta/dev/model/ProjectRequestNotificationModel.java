package santzin.projeta.dev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import santzin.projeta.dev.model.enums.TypeProjectRequestNotification;

import java.time.LocalDate;

@Entity
@Table(name = "tb_project_request_notification")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectRequestNotificationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private ProjectRequestModel projectRequest;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @Column(name = "read", nullable = false)
    private Boolean read;

    @Column(name = "type", nullable = false)
    private TypeProjectRequestNotification type;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "read_at")
    private LocalDate readAt;
}
