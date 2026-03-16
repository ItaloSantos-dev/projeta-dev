package santzin.projeta.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import santzin.projeta.dev.DTOs.hability.HabilityResponseDTO;
import santzin.projeta.dev.DTOs.project.ProjectResponseDTO;
import santzin.projeta.dev.DTOs.user.UserResponseDTO;
import santzin.projeta.dev.exception.FailedLoginException;
import santzin.projeta.dev.exception.ItemNotFoundException;
import santzin.projeta.dev.mapper.HabilityMapper;
import santzin.projeta.dev.mapper.ProjectMapper;
import santzin.projeta.dev.mapper.UserMapper;
import santzin.projeta.dev.model.HabilityModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.repository.HabilityRepository;
import santzin.projeta.dev.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private HabilityMapper habilityMapper;

    @Autowired
    private ProjectRequestNotificationService projectRequestNotificationService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));
    }

    public UserResponseDTO getUserByUsername(String username){
        UserModel user = this.userRepository.findByUsernameProperty(username)
                .orElseThrow(ItemNotFoundException::new);
        return this.userMapper.modelToResponse(user);
    }

    public ProjectResponseDTO getProjectOfUserByUsernameAndSlug(String username, String slug){
        if (!this.userRepository.existsByUsernameProperty(username))
            throw new ItemNotFoundException();
        return this.projectService.getProjectOfUserByUsernameAndSlug(username, slug);
    }

    public List<ProjectResponseDTO> getProjectsOfUserByUsername(String username){
        UserModel user = this.userRepository.findByUsernameProperty(username)
                .orElseThrow(ItemNotFoundException::new);
        List<ProjectResponseDTO> response = new ArrayList<>();

        if (user.getMyProjects()!=null){
            response = user.getMyProjects().stream()
                    .map(p -> this.projectMapper.modelToResponse(p))
                    .toList();
        }

        return response;
    }

    public List<HabilityResponseDTO> getHabilitys(String username){
        UserModel user = this.userRepository.findByUsernameProperty(username)
                .orElseThrow(ItemNotFoundException::new);

        return user.getHabilitys().stream()
                .map(h -> this.habilityMapper.modelToResponse(h))
                .toList();
    }


}
