package com.martinaleksandrov.wantapet.services;

import com.martinaleksandrov.wantapet.exceptions.AppException;
import com.martinaleksandrov.wantapet.models.dtos.UserLoginDto;
import com.martinaleksandrov.wantapet.models.dtos.UserRegisterDto;
import com.martinaleksandrov.wantapet.models.entities.UserAddress;
import com.martinaleksandrov.wantapet.models.entities.UserEntity;
import com.martinaleksandrov.wantapet.models.enums.CountryEnum;
import com.martinaleksandrov.wantapet.models.enums.RoleEnum;
import com.martinaleksandrov.wantapet.reporitories.UserAddressRepository;
import com.martinaleksandrov.wantapet.reporitories.UserRepository;
import com.martinaleksandrov.wantapet.reporitories.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserAddressRepository addressRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public boolean register(UserRegisterDto userRegisterDto) {

        Optional<UserEntity> optUser = this.userRepository.findByEmail(userRegisterDto.getEmail());

        if (optUser.isPresent()) {
            return false;
        }

        UserEntity user = this.modelMapper.map(userRegisterDto, UserEntity.class);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.getRoles().add(this.userRoleRepository.findByRole(RoleEnum.USER));
        if (this.userRepository.count() == 0){
            user.getRoles().add(this.userRoleRepository.findByRole(RoleEnum.ADMIN));
        }

        addressMap(userRegisterDto, user);

        userRepository.save(user);

        return true;
    }

    private void addressMap(UserRegisterDto userRegisterDto, UserEntity user) {
        UserAddress userAddress = new UserAddress();
        userAddress.setCountryEnum(CountryEnum.valueOf(userRegisterDto.getCountry()));
        userAddress.setCity(userRegisterDto.getCity());
        userAddress.setStreet(userRegisterDto.getStreet());
        userAddress.setStreetNumber(userRegisterDto.getStreetNumber());

        this.addressRepository.save(userAddress);

        user.setAddress(userAddress);
    }

    public List<UserRegisterDto> getAll() {

        List<UserEntity> allUsers = this.userRepository.findAll();

        List<UserRegisterDto> usersToShow = allUsers.stream()
                .map(userEntity -> this.modelMapper.map(userEntity, UserRegisterDto.class))
                .collect(Collectors.toList());


        return usersToShow;
    }

    public UserLoginDto findByEmail(String email) {
        UserEntity user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        return this.modelMapper.map(user, UserLoginDto.class);
    }

//    public UserLoginDto login(CredentialsDto credentialsDto) {
//        UserEntity user = this.userRepository.findByEmail(credentialsDto.getEmail())
//                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
//
//        if (this.passwordEncoder.matches(credentialsDto.getPassword(), user.getPassword())) {
//
//            return this.modelMapper.map(user, UserLoginDto.class);
//        }
//
//        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
//    }
}
