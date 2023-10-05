package com.martinaleksandrov.wantapet.web;

import com.martinaleksandrov.wantapet.models.dtos.UserRegisterDto;
import com.martinaleksandrov.wantapet.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public  List<UserRegisterDto> getAllUsers(){
        return this.userService.getAll();
    }

    @PostMapping("/add")
    public Boolean addUser(@RequestBody UserRegisterDto userRegisterDto){
         return this.userService.registerUser(userRegisterDto);
    }
}
