package com.martinaleksandrov.wantapet.web;

import com.martinaleksandrov.wantapet.models.dtos.PetCreatingDto;
import com.martinaleksandrov.wantapet.models.dtos.UserRegisterDto;
import com.martinaleksandrov.wantapet.services.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/add")
public class AddController {

    private final PetService petService;

    @GetMapping
    public ModelAndView add(){
        return new ModelAndView("add");
    }

    @ModelAttribute("petCreatingDto")
    public PetCreatingDto petInit(){
        return new PetCreatingDto();
    }

    /*******    ADDING DOG  *******/
    @GetMapping("/dog")
    public ModelAndView addDog(){
        return new ModelAndView("add-dog");
    }

    @PostMapping("/dog")
    public ModelAndView addDog(@Valid PetCreatingDto petCreatingDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("petCreatingDto", petCreatingDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.petCreatingDto", bindingResult);

            return new ModelAndView("redirect:/add/dog");
        }

        this.petService.addDog(petCreatingDto);
        return new ModelAndView("redirect:/catalog");
    }

    /*******    ADDING CAT  *******/

    @GetMapping("/cat")
    public ModelAndView addCat(){
        return new ModelAndView("add-cat");
    }
    @PostMapping("/cat")
    public ModelAndView addCat(@Valid PetCreatingDto petCreatingDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("petCreatingDto", petCreatingDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.petCreatingDto", bindingResult);

            return new ModelAndView("redirect:/add/cat");
        }

        this.petService.addCat(petCreatingDto);
        return new ModelAndView("redirect:/catalog");
    }
}
