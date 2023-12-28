package com.wepard.meme_dong_office.entity.students.list;

import com.wepard.meme_dong_office.entity.students.Students;
import com.wepard.meme_dong_office.entity.users.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class StudentsList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(length = 30)
    private String name;

    private Integer studentsCount;

    @OneToMany(mappedBy = "studentsList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Students> studentsList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "students_list")
    private Users users;

    @Builder
    public StudentsList(
            String name,
            Integer studentsCount,
            Users users
    ){
        this.name = name;
        this.studentsCount = studentsCount;
        this.users = users;
    }
}
