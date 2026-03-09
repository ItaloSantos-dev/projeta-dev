package santzin.projeta.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import santzin.projeta.dev.DTOs.hability.CreateHabilityRequestDTO;
import santzin.projeta.dev.DTOs.hability.HabilityResponseDTO;
import santzin.projeta.dev.model.UserModel;
import santzin.projeta.dev.service.HabilityService;

@RestController
@RequestMapping("/habilitys")
public class HabilityController {
    @Autowired
    private HabilityService habilityService;

    @PostMapping
    public HabilityResponseDTO createHability(
            @RequestBody CreateHabilityRequestDTO requestDTO,
            @AuthenticationPrincipal UserModel user
    ){
        return this.habilityService.createHability(requestDTO, user);
    }

    @DeleteMapping("/{id}")
    public void delteHability(@AuthenticationPrincipal UserModel user, @PathVariable Long id){
        this.habilityService.deleteHability(id, user);
    }
}
