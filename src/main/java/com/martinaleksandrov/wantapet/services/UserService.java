package com.martinaleksandrov.wantapet.services;

import com.martinaleksandrov.wantapet.models.dtos.UserRegisterDto;
import com.martinaleksandrov.wantapet.models.entities.UserEntity;
import com.martinaleksandrov.wantapet.reporitories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public boolean registerUser(UserRegisterDto userRegisterDto){
        Optional<UserEntity> userOpt = this.userRepository.findByEmail(userRegisterDto.getEmail());

        if (userOpt.isPresent()){
            return false;
        }

        UserEntity userToSave = this.modelMapper.map(userRegisterDto, UserEntity.class);
        this.userRepository.save(userToSave);

        return true;
    }

    public List<UserRegisterDto> getAll(){

        List<UserEntity> allUsers = this.userRepository.findAll();

        List<UserRegisterDto> usersToShow = allUsers.stream()
                .map(userEntity -> this.modelMapper.map(userEntity, UserRegisterDto.class))
                .collect(Collectors.toList());


        return usersToShow;
    }
}
