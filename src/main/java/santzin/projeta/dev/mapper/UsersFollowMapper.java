package santzin.projeta.dev.mapper;

import org.springframework.stereotype.Component;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.model.UsersFollowModel;

import java.time.LocalDate;

@Component
public class UsersFollowMapper {
    public UsersFollowModel requestToModel(UserModel userFollowing, UserModel userFollowed){
        UsersFollowModel usersFollowModel = new UsersFollowModel();
        usersFollowModel.setUserFollowing(userFollowing);
        usersFollowModel.setUserFollowed(userFollowed);
        usersFollowModel.setCreatedAt(LocalDate.now());

        return usersFollowModel;
    }
}
