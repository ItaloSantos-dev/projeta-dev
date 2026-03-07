package santzin.projeta.dev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_hability")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HabilityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "have_icon", nullable = false)
    private Boolean haveIcon;

    @Column(name = "icon_link")
    private String iconLink;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
}
