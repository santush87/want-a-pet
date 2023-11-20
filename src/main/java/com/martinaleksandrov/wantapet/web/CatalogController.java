package com.martinaleksandrov.wantapet.web;

import com.martinaleksandrov.wantapet.models.dtos.PetViewModelDto;
import com.martinaleksandrov.wantapet.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalog")
public class CatalogController {

    private final PetService petService;

    @GetMapping
    public ModelAndView catalog() {
        return new ModelAndView("catalog");
    }

    @GetMapping("/dogs")
    public ModelAndView dogCatalog() {
        return new ModelAndView("catalog-dogs");
    }

    @GetMapping("/cats")
    public ModelAndView catCatalog() {
        return new ModelAndView("catalog-cats");
    }

    @GetMapping("/cats-and-dogs")
    public ModelAndView catAndDogcatalog() {
        ModelAndView modelAndView = new ModelAndView("catalog-dogs-cats");

        List<PetViewModelDto> pets = this.petService.getAllPets();
        modelAndView.addObject("pets", pets);

        return modelAndView;
    }
}
