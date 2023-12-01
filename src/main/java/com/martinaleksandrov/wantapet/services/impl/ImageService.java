package com.martinaleksandrov.wantapet.services.impl;

import com.martinaleksandrov.wantapet.models.entities.Image;
import com.martinaleksandrov.wantapet.reporitories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}
