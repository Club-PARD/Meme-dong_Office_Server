package com.wepard.meme_dong_office.entity.users;

import com.wepard.meme_dong_office.entity.students.list.StudentsList;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 320, nullable = false)
    private String email;

    @Column(length = 60, nullable = false)
    private String hashedPassword;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentsList> studentsLists;


    @Builder
    public Users(
            final String name,
            final String email,
            final String hashedPassword
    ){
        this.name = name;
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    public void updateEmail(
            final String email
    ){
        this.email = email;
    }

    public void updatePassword(
            final String hashedPassword
    ){
        this.hashedPassword = hashedPassword;
    }

    public void updateName(
            final String name
    ){
        this.name = name;
    }
}
