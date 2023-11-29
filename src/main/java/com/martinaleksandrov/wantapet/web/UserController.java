package com.martinaleksandrov.wantapet.web;

import com.martinaleksandrov.wantapet.models.dtos.UserDetailsDto;
import com.martinaleksandrov.wantapet.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/users/my-account")
    public ModelAndView getMyAcc(@AuthenticationPrincipal UserDetails viewer){
        ModelAndView modelAndView = new ModelAndView("my-account");

        UserDetailsDto userDetailsDto = this.userService.getUserDetails(viewer.getUsername());

        modelAndView.addObject("userDetails", userDetailsDto);

        return modelAndView;
    }
}
