package santzin.projeta.dev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import santzin.projeta.dev.model.enums.UserExperienceLevel;
import santzin.projeta.dev.model.enums.UserRole;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "username", length = 100, nullable = false)
    private String usernameProperty;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "role", length = 20,nullable = false)
    private UserRole role;

    @Column(name = "telephone_number", length = 12, nullable = false, unique = true)
    private String telephoneNumber;

    @Column(name = "experience_level", nullable = false )
    private UserExperienceLevel experienceLevel;

    @Column(name = "principal_stack")
    private String principalStack;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @OneToMany(mappedBy = "creator")
    private List<ProjectModel> myProjects;

    @Column(name = "about")
    private String about;

    @Column(name ="cover_url")
    private String coverUrl;

    @Column(name ="perfil_url")
    private String perfilUrl;

    @Column(name = "link1")
    private String link1;

    @Column(name = "link2")
    private String link2;

    @Column(name = "link3")
    private String link3;

    @Column(name = "link4")
    private String link4;

    @Column(name = "link5")
    private String link5;

    @OneToMany(mappedBy = "user")
    private List<ProjectUserModel> projects;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role==UserRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

