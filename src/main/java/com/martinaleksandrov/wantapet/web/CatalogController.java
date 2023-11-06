package com.martinaleksandrov.wantapet.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalog")
public class CatalogController {

    @GetMapping
    public ModelAndView catalog(){
        return new ModelAndView("catalog");
    }

    @GetMapping("/dogs")
    public ModelAndView dogCatalog(){
        return new ModelAndView("dogs");
    }

    @GetMapping("/cats")
    public ModelAndView catCatalog(){
        return new ModelAndView("cats");
    }

    @GetMapping("/cats-and-dogs")
    public ModelAndView catAndDogcatalog(){
        return new ModelAndView("cats-and-dogs");
    }
}
