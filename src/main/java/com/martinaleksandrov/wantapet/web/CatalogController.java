package com.martinaleksandrov.wantapet.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class CatalogController {

    @GetMapping("/catalog")
    public ModelAndView catalog(){
        return new ModelAndView("catalog");
    }
}
