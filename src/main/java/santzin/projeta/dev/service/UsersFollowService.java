package santzin.projeta.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import santzin.projeta.dev.DTOs.user.UserResponseDTO;
import santzin.projeta.dev.exception.ItemNotFoundException;
import santzin.projeta.dev.exception.ItemWithValueAlreadyExistsException;
import santzin.projeta.dev.exception.NotPermitException;
import santzin.projeta.dev.mapper.UserMapper;
import santzin.projeta.dev.mapper.UsersFollowMapper;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.model.UsersFollowModel;
import santzin.projeta.dev.repository.UserRepository;
import santzin.projeta.dev.repository.UsersFollowRepository;

import java.util.List;

@Service
public class UsersFollowService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersFollowRepository usersFollowRepository;

    @Autowired
    private UsersFollowMapper usersFollowMapper;

    @Autowired
    private UserMapper userMapper;

    public void followUserById(UserModel userFollowing, Long userFollowedId){
        if (userFollowing.getId().equals(userFollowedId))
            throw new NotPermitException();

        if (this.usersFollowRepository.existsByUserFollowingIdAndUserFollowedId(userFollowing.getId(),userFollowedId))
            throw new ItemWithValueAlreadyExistsException();

        UserModel userFollowed = this.userRepository.findById(userFollowedId)
                .orElseThrow(ItemNotFoundException::new);

        UsersFollowModel newUsersFollowModel = this.usersFollowMapper.requestToModel(userFollowing, userFollowed);

        this.usersFollowRepository.save(newUsersFollowModel);
    }

    public List<UserResponseDTO> getFollowingOfUserByUsername(String username){
        if (!this.userRepository.existsByUsernameProperty(username))
            throw new ItemNotFoundException();

        return this.usersFollowRepository.findByUserFollowingUsernameProperty(username).stream()
                .map(userFollow -> this.userMapper.modelToResponse(userFollow.getUserFollowed()))
                .toList();
    }

    public List<UserResponseDTO> getFollowersOfUserByUsername(String username){
        if (!this.userRepository.existsByUsernameProperty(username))
            throw new ItemNotFoundException();

        return this.usersFollowRepository.findByUserFollowedUsernameProperty(username).stream()
                .map(userFollow -> this.userMapper.modelToResponse(userFollow.getUserFollowing()))
                .toList();
    }
}
