package com.martinaleksandrov.wantapet.web;

import com.martinaleksandrov.wantapet.models.dtos.UserLoginDto;
import com.martinaleksandrov.wantapet.models.dtos.UserRegisterDto;
import com.martinaleksandrov.wantapet.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    /***    LOGIN   ***/
    @ModelAttribute("userLoginDto")
    public UserLoginDto logInit(){
        return new UserLoginDto();
    }

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    /***    REGISTER   ***/

    /***    Model Attribute   ***/
    @ModelAttribute("userRegisterDto")
    public UserRegisterDto regInit(){
        return new UserRegisterDto();
    }

    @GetMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView doRegister(@Valid UserRegisterDto userRegisterDto,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterDto", userRegisterDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);

            return new ModelAndView("redirect:/register");
        }

        try {
            boolean registered = this.userService.register(userRegisterDto);
            if (registered) {
                return new ModelAndView("redirect:/home");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return new ModelAndView("register");
    }
}
