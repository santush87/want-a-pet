package com.martinaleksandrov.wantapet.services;

import com.martinaleksandrov.wantapet.models.dtos.view.UserDetailsDto;
import com.martinaleksandrov.wantapet.models.dtos.view.UserEditDto;
import com.martinaleksandrov.wantapet.models.dtos.binding.UserRegisterDto;
import com.martinaleksandrov.wantapet.models.entities.UserEntity;

import java.util.List;
import java.util.Optional;


public interface UserService {
    boolean register(UserRegisterDto userRegisterDto);
    List<UserRegisterDto> getAll();
    boolean isAdmin(UserEntity userEntity);
    Optional<UserEntity> findById(String id);
    Optional<UserEntity> findByEmail(String email);
    UserDetailsDto getUserDetails(String username);
    boolean editUser(String id, UserEditDto userEditDto);
//    int getAdoptedPetsCount(String username);
//    int getUploadedPetsCount(String username);
}
