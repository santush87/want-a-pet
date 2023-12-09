package com.martinaleksandrov.wantapet.web;

import com.martinaleksandrov.wantapet.models.dtos.view.AdoptedPetsViewDto;
import com.martinaleksandrov.wantapet.models.dtos.view.PetsToSendDto;
import com.martinaleksandrov.wantapet.services.AdoptedPetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalog")
public class AdoptedPetsController {

    private final AdoptedPetsService adoptedPetsService;

    @GetMapping("/my-adopted-pets")
    public ModelAndView adoptedPets(@AuthenticationPrincipal UserDetails viewer) {
        ModelAndView modelAndView = new ModelAndView("my-adopted-pets");
        List<AdoptedPetsViewDto> pets = this.adoptedPetsService.getAllMyAdoptedPets(viewer.getUsername());
        modelAndView.addObject("pets", pets);

        return modelAndView;
    }

    @GetMapping("/pets-to-send")
    public ModelAndView petToSend(@AuthenticationPrincipal UserDetails viewer) {
        ModelAndView modelAndView = new ModelAndView("send-pet");

        List<PetsToSendDto> pets = this.adoptedPetsService.getPetsToSend(viewer.getUsername());

        modelAndView.addObject("pets", pets);

        return modelAndView;
    }

    @PostMapping("/pets-to-send/{id}")
    public ModelAndView sendPet(@PathVariable("id") Long id) {

        ModelAndView modelAndView = new ModelAndView("redirect:/catalog/pets-to-send");

        this.adoptedPetsService.sendPet(id);
        return modelAndView;
    }

}
