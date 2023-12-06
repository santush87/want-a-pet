package com.martinaleksandrov.wantapet.web;

import com.martinaleksandrov.wantapet.models.dtos.view.AdoptedPetsViewDto;
import com.martinaleksandrov.wantapet.services.AdoptedPetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdoptedPetsController {

    private final AdoptedPetsService adoptedPetsService;

    @GetMapping("/catalog/my-adopted-pets")
    public ModelAndView adoptedPets(@AuthenticationPrincipal UserDetails viewer){
        ModelAndView modelAndView = new ModelAndView("my-adopted-pets");
        List<AdoptedPetsViewDto> pets = this.adoptedPetsService.getAllMyAdoptedPets(viewer.getUsername());
        modelAndView.addObject("pets", pets);

        return modelAndView;
    }
}
