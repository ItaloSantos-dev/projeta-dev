package santzin.projeta.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import santzin.projeta.dev.model.UserModel;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserDetails> findByEmail(String email);
    Boolean existsByEmail(String email);

    Optional<UserModel> findByUsername(String  username);

    Boolean existsByUsername(String username);
}
