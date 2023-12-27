package com.wepard.meme_dong_office.entity.users;

import jakarta.persistence.*;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String provider;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 320, nullable = false)
    private String email;
}
