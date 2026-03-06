package santzin.projeta.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import santzin.projeta.dev.DTOs.project.ProjectResponseDTO;
import santzin.projeta.dev.DTOs.user.UserResponseDTO;
import santzin.projeta.dev.exception.FailedLoginException;
import santzin.projeta.dev.exception.ItemNotFoundException;
import santzin.projeta.dev.mapper.UserMapper;
import santzin.projeta.dev.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProjectService projectService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));
    }

    public UserResponseDTO getUserByUsername(String username){
        return this.userMapper.modelToResponse(this.userRepository.findByUsernameProperty(username)
                .orElseThrow(ItemNotFoundException::new));
    }

    public ProjectResponseDTO getProjectOfUserByUsernameAndSlug(String username, String slug){
        if (!this.userRepository.existsByUsernameProperty(username))
            throw new ItemNotFoundException();
        return this.projectService.getProjectOfUserByUsernameAndSlug(username, slug);
    }


}
