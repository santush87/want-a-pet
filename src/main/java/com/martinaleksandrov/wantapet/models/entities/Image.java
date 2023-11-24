package com.martinaleksandrov.wantapet.models.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "images")
@Getter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] data;

    public Image setId(Long id) {
        this.id = id;
        return this;
    }

    public Image setData(byte[] data) {
        this.data = data;
        return this;
    }
}
