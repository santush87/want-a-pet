package com.martinaleksandrov.wantapet.web;

import com.martinaleksandrov.wantapet.models.dtos.PetCreatingDto;
import com.martinaleksandrov.wantapet.models.dtos.PetDetailsDto;
import com.martinaleksandrov.wantapet.models.dtos.UserDetailsDto;
import com.martinaleksandrov.wantapet.models.dtos.UserEditDto;
import com.martinaleksandrov.wantapet.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/my-account")
    public ModelAndView getMyAcc(@AuthenticationPrincipal UserDetails viewer) {
        ModelAndView modelAndView = new ModelAndView("my-account");

        UserDetailsDto userDetailsDto = this.userService.getUserDetails(viewer.getUsername());

        modelAndView.addObject("userDetails", userDetailsDto);

        return modelAndView;
    }

    @GetMapping("/users/my-account/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") String id,
                                 @AuthenticationPrincipal UserDetails viewer) {

        ModelAndView modelAndView = new ModelAndView("edit-user");

        UserDetailsDto userDetails = this.userService.getUserDetails(viewer.getUsername());
        modelAndView.addObject("userDetails", userDetails);

        return modelAndView;
    }

    @PostMapping("/users/my-account/edit/{id}")
    public ModelAndView editPet(@Valid UserEditDto userEditDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                @PathVariable("id") String id) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userDetails", userEditDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userEditDto", bindingResult);

            return new ModelAndView("redirect:/users/my-account/edit/" + id);
        }

        boolean isSuccessful = this.userService.editUser(id, userEditDto);

        if (isSuccessful) {
            return new ModelAndView("redirect:/users/my-account");
        }

        return new ModelAndView("redirect:/users/my-account/edit/" + id);
    }
}
