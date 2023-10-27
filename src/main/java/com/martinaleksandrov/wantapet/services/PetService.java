package com.martinaleksandrov.wantapet.services;

import com.martinaleksandrov.wantapet.reporitories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;



}
