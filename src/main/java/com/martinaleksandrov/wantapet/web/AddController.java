package com.martinaleksandrov.wantapet.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("add")
public class AddController {

    @GetMapping
    public ModelAndView add(){
        return new ModelAndView("add");
    }

    @GetMapping("/dog")
    public ModelAndView addDog(){
        return new ModelAndView("add-dog");
    }

    @GetMapping("/cat")
    public ModelAndView addCat(){
        return new ModelAndView("add-cat");
    }
}
