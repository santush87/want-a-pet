package com.martinaleksandrov.wantapet.services;

import com.martinaleksandrov.wantapet.reporitories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
