package com.martinaleksandrov.wantapet.services;

import com.martinaleksandrov.wantapet.reporitories.PetRepository;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }


}
