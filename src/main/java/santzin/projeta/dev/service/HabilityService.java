package santzin.projeta.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import santzin.projeta.dev.DTOs.hability.CreateHabilityRequestDTO;
import santzin.projeta.dev.DTOs.hability.HabilityResponseDTO;
import santzin.projeta.dev.exception.ItemNotFoundException;
import santzin.projeta.dev.exception.ItemWithValueAlreadyExistsException;
import santzin.projeta.dev.exception.NotPermitException;
import santzin.projeta.dev.mapper.HabilityMapper;
import santzin.projeta.dev.model.HabilityModel;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.repository.HabilityRepository;

@Service
public class HabilityService {
    @Autowired
    private HabilityRepository habilityRepository;

    @Autowired
    private HabilityMapper habilityMapper;

    public HabilityResponseDTO createHability(CreateHabilityRequestDTO request, UserModel user){
        if (this.habilityRepository.existsByTitle(request.title()))
            throw new ItemWithValueAlreadyExistsException("habilidade", "título", request.title());
        HabilityModel newHability = this.habilityMapper.requestToModel(request, user);

        return this.habilityMapper.modelToResponse(this.habilityRepository.save(newHability));
    }
    public void deleteHability(Long id, UserModel user){
        HabilityModel hability = this.habilityRepository.findById(id)
                .orElseThrow(ItemNotFoundException::new);

        if(!hability.getUser().getId().equals(user.getId()))
            throw new NotPermitException();

        this.habilityRepository.delete(hability);
    }
}
