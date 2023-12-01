package com.martinaleksandrov.wantapet.reporitories;

import com.martinaleksandrov.wantapet.models.entities.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<UserAddress, String> {
}
