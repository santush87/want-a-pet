package com.martinaleksandrov.wantapet.services.impl;

import com.martinaleksandrov.wantapet.models.entities.UserAddress;
import com.martinaleksandrov.wantapet.reporitories.AddressRepository;
import com.martinaleksandrov.wantapet.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    @Override
    public void saveAddress(UserAddress address) {
        this.addressRepository.save(address);
    }
}
