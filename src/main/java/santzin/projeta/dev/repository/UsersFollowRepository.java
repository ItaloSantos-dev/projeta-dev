package santzin.projeta.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.model.UsersFollowModel;
import java.util.List;

@Repository
public interface UsersFollowRepository extends JpaRepository<UsersFollowModel, Long> {

    //retorna lista que o user segue
    List<UsersFollowModel> findByUserFollowingId(Long id);


    List<UsersFollowModel> findByUserFollowingUsernameProperty(String  username);

    //Retorna lista de quem segue o user
    List<UsersFollowModel> findByUserFollowedId(Long id);

    List<UsersFollowModel> findByUserFollowedUsernameProperty(String  username);


    boolean existsByUserFollowingIdAndUserFollowedId(Long userFollowingId, Long userFollowedId);

    void deleteByUserFollowingIdAndUserFollowedId(Long userFollowingId, Long userFollowedId);
}
