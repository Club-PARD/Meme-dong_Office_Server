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

    private Integer listRow;

    private Integer listCol;

    private Boolean seatSpacing;

    private Integer studentsCount;

    @OneToMany(mappedBy = "studentsList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Students> studentsList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "students_list")
    private Users users;

    @Builder
    public StudentsList(
            final String name,
            final Integer listRow,
            final Integer listCol,
            final Boolean seatSpacing,
            final Integer studentsCount,
            final Users users
    ){
        this.name = name;
        this.listRow = listRow;
        this.listCol = listCol;
        this.seatSpacing = seatSpacing;
        this.studentsCount = studentsCount;
        this.users = users;
    }

    public void updateName(
            final String name
    ){
        this.name = name;
    }

    public void updateListRow(
            final Integer listRow
    ){
        this.listRow = listRow;
    }

    public void updateListCol(
            final Integer listCol
    ){
        this.listCol = listCol;
    }

    public void updateSeatSpacing(
            final Boolean seatSpacing
    ){
        this.seatSpacing = seatSpacing;
    }
}
