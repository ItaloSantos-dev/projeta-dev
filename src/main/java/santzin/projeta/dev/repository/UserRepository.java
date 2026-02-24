package santzin.projeta.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import santzin.projeta.dev.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
}
