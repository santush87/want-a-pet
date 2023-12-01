package com.martinaleksandrov.wantapet.services.impl;


import com.martinaleksandrov.wantapet.models.dtos.UserDetailsDto;
import com.martinaleksandrov.wantapet.models.dtos.UserEditDto;
import com.martinaleksandrov.wantapet.models.dtos.UserRegisterDto;
import com.martinaleksandrov.wantapet.models.entities.UserAddress;
import com.martinaleksandrov.wantapet.models.entities.UserEntity;
import com.martinaleksandrov.wantapet.models.entities.UserRoleEntity;
import com.martinaleksandrov.wantapet.models.enums.CountryEnum;
import com.martinaleksandrov.wantapet.models.enums.RoleEnum;
import com.martinaleksandrov.wantapet.models.enums.TypeOfUser;
import com.martinaleksandrov.wantapet.reporitories.UserRepository;
import com.martinaleksandrov.wantapet.reporitories.UserRoleRepository;
import com.martinaleksandrov.wantapet.services.AddressService;
import com.martinaleksandrov.wantapet.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final AddressService addressService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean register(UserRegisterDto userRegisterDto) {

        Optional<UserEntity> optUser = this.userRepository.findByEmail(userRegisterDto.getEmail());

        if (optUser.isPresent()) {
            return false;
        }

        UserEntity user = this.modelMapper.map(userRegisterDto, UserEntity.class);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.getRoles().add(this.userRoleRepository.findByRole(RoleEnum.USER));
        if (this.userRepository.count() == 0) {
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

        this.addressService.saveAddress(userAddress);

        user.setAddress(userAddress);
    }

    @Override
    public List<UserRegisterDto> getAll() {

        List<UserEntity> allUsers = this.userRepository.findAll();

        List<UserRegisterDto> usersToShow = allUsers.stream()
                .map(userEntity -> this.modelMapper.map(userEntity, UserRegisterDto.class))
                .collect(Collectors.toList());

        return usersToShow;
    }

    @Override
    public boolean isAdmin(UserEntity userEntity) {
        return userEntity
                .getRoles()
                .stream()
                .map(UserRoleEntity::getRole)
                .anyMatch(role -> RoleEnum.ADMIN == role);
    }
    @Override
    public Optional<UserEntity> findById(String id) {
        return this.userRepository.findById(id);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public UserDetailsDto getUserDetails(String username) {
        Optional<UserEntity> optionalUser = this.userRepository.findByEmail(username);
        if (optionalUser.isEmpty()) {
            throw new NoSuchElementException("User with email: " + username + " not exists!");
        }

        UserAddress address = optionalUser.get().getAddress();

        UserDetailsDto userDetails = this.modelMapper.map(optionalUser.get(), UserDetailsDto.class);
        String userType = isAdmin(optionalUser.get()) ? "Admin" : "User";

        userDetails.setCountry(address.getCountryEnum().toString())
                .setCity(address.getCity())
                .setStreet(address.getStreet())
                .setStreetNumber(address.getStreetNumber())
                .setJoinedOn(optionalUser.get().getCreatedOn().toString())
                .setUser(userType);

        return userDetails;
    }

    public boolean editUser(String id, UserEditDto userEditDto) {
        Optional<UserEntity> optUser = this.userRepository.findById(id);
        if (optUser.isPresent()) {
            UserEntity user = optUser.get();

            UserAddress address = user.getAddress();

            address .setCountryEnum(CountryEnum.valueOf(userEditDto.getCountry()))
                    .setCity(userEditDto.getCity())
                    .setStreet(userEditDto.getStreet())
                    .setStreetNumber(userEditDto.getStreetNumber());

            this.addressService.saveAddress(address);

            user.setFirstName(userEditDto.getFirstName())
                    .setLastName(userEditDto.getLastName())
                    .setUserType(TypeOfUser.valueOf(userEditDto.getUserType()))
                    .setPhoneNumber(userEditDto.getPhoneNumber())
                    .setAddress(address);

            this.userRepository.save(user);
            return true;
        }
        return false;
    }
}

