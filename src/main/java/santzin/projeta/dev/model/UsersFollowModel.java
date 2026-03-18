package santzin.projeta.dev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_users_follow")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersFollowModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_following_id")
    private UserModel userFollowing;

    @ManyToOne
    @JoinColumn(name = "user_followed_id")
    private UserModel userFollowed;

    @Column(name = "created_at")
    private LocalDate createdAt;
}
