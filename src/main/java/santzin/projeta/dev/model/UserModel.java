package santzin.projeta.dev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "telephone_number", length = 12, nullable = false, unique = true)
    private String telephoneNumber;

    @Column(name = "experience_level", nullable = false )
    private String professionalLevel;

    @Column(name = "principal_stack")
    private String principalStack;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
}

