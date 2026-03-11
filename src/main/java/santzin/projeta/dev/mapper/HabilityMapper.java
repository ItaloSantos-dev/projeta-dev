package santzin.projeta.dev.mapper;

import org.springframework.stereotype.Component;
import santzin.projeta.dev.DTOs.hability.CreateHabilityRequestDTO;
import santzin.projeta.dev.DTOs.hability.HabilityResponseDTO;
import santzin.projeta.dev.model.HabilityModel;
import santzin.projeta.dev.model.UserModel;

import java.time.LocalDate;

@Component
public class HabilityMapper {
    public HabilityModel requestToModel(CreateHabilityRequestDTO requestDTO, UserModel user){
        HabilityModel hability = new HabilityModel();
        hability.setTitle(requestDTO.title());
        hability.setUser(user);
        hability.setHaveIcon(requestDTO.haveIcon());
        if (hability.getHaveIcon())
            hability.setIconLink(requestDTO.iconLink());
        hability.setCreatedAt(LocalDate.now());
        return hability;
    }

    public HabilityResponseDTO modelToResponse(HabilityModel habilityModel){
        return new HabilityResponseDTO(habilityModel.getId(),habilityModel.getTitle(), habilityModel.getHaveIcon(), habilityModel.getIconLink());
    }
}
