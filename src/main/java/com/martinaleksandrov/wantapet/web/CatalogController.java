package com.martinaleksandrov.wantapet.web;

import com.martinaleksandrov.wantapet.models.dtos.PetCreatingDto;
import com.martinaleksandrov.wantapet.models.dtos.PetDetailsDto;
import com.martinaleksandrov.wantapet.models.dtos.PetViewModelDto;
import com.martinaleksandrov.wantapet.services.AdoptedPetsService;
import com.martinaleksandrov.wantapet.services.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalog")
public class CatalogController {

    private final PetService petService;
    private final AdoptedPetsService adoptedPetsService;

    @GetMapping
    public ModelAndView catalog() {
        return new ModelAndView("catalog");
    }

    @GetMapping("/dogs")
    public ModelAndView dogCatalog() {
        ModelAndView modelAndView = new ModelAndView("catalog-dogs");

        List<PetViewModelDto> pets = this.petService.getAllDogs();
        modelAndView.addObject("pets", pets);

        return modelAndView;
    }

    @GetMapping("/cats")
    public ModelAndView catCatalog() {
        ModelAndView modelAndView = new ModelAndView("catalog-cats");

        List<PetViewModelDto> pets = this.petService.getAllCats();
        modelAndView.addObject("pets", pets);

        return modelAndView;
    }

    @GetMapping("/cats-and-dogs")
    public ModelAndView catAndDogcatalog() {
        ModelAndView modelAndView = new ModelAndView("catalog-dogs-cats");

        List<PetViewModelDto> pets = this.petService.getAllPets();
        modelAndView.addObject("pets", pets);

        return modelAndView;
    }

    @GetMapping("/my-pets")
    public ModelAndView myPets(@AuthenticationPrincipal UserDetails viewer) {
        ModelAndView modelAndView = new ModelAndView("catalog-my-pets");

        List<PetViewModelDto> pets = this.petService.getAllMyPets(viewer.getUsername());
        modelAndView.addObject("pets", pets);

        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable("id") Long id,
                                @AuthenticationPrincipal UserDetails viewer) {
        ModelAndView modelAndView = new ModelAndView("pet-details");

        PetDetailsDto petDetails = this.petService.getPetDetails(id, viewer.getUsername());

        modelAndView.addObject("petDetails", petDetails);

        return modelAndView;
    }

    @PostMapping("/details/{id}")
    public ModelAndView delete(@PathVariable("id") Long id,
                               @AuthenticationPrincipal UserDetails principal) {

        ModelAndView modelAndView = new ModelAndView("redirect:/catalog/cats-and-dogs");

        this.petService.deletePet(id, principal.getUsername());

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") Long id,
                                 @AuthenticationPrincipal UserDetails viewer) {
        ModelAndView modelAndView = new ModelAndView("edit-pet");
        PetDetailsDto petDetails = this.petService.getPetDetails(id, viewer.getUsername());
        modelAndView.addObject("petDetails", petDetails);

        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editPet(@Valid PetCreatingDto petCreatingDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                @PathVariable("id") Long id) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("petDetails", petCreatingDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.petCreatingDto", bindingResult);

            return new ModelAndView("redirect:/catalog/edit/" + id);
        }

        this.petService.editPet(id, petCreatingDto);

        return new ModelAndView("redirect:/catalog/details/" + id);
    }

    @PostMapping("/adopt/{id}")
    public ModelAndView adoptPet(@PathVariable("id") Long id,
                                 @AuthenticationPrincipal UserDetails viewer){

        this.adoptedPetsService.adoptPet(id, viewer.getUsername());

        return new ModelAndView("redirect:/catalog/cats-and-dogs");
    }
}
