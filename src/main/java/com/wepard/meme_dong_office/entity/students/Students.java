package com.wepard.meme_dong_office.entity.students;

import com.wepard.meme_dong_office.dto.students.request.StudentsRequestDTO;
import com.wepard.meme_dong_office.entity.students.list.StudentsList;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 2047)
    private String imageURL;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isCaution; //요주의 인물인지

    private LocalDateTime birthday;

    @Column(columnDefinition = "TEXT")
    private String allergy;

    @Column(columnDefinition = "TEXT")
    private String studyLevel;

    @Column(columnDefinition = "TEXT")
    private String etc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "students")
    private StudentsList studentsList;

    @Builder
    public Students(
            final StudentsList studentsList,
            final String name,
            final String imageURL,
            final Boolean isCaution,
            final LocalDateTime birthday,
            final String allergy,
            final String studyLevel,
            final String etc
    ){
        this.studentsList = studentsList;
        this.name = name;
        this.imageURL = imageURL;
        //null일 때 기본 값 지정
        if(isCaution == null){
            this.isCaution = false;
        }
        else{
            this.isCaution = isCaution;
        }
        this.birthday = birthday;
        this.allergy = allergy;
        this.studyLevel = studyLevel;
        this.etc = etc;
    }

    public void updateStudentsList(
            final StudentsList studentsList
    ){
        this.studentsList = studentsList;
    }

    public void updateName(
            final String name
    ){
        this.name = name;
    }

    public void updateImageURL(
            final String imageURL
    ){
        this.imageURL = imageURL;
    }

    public void updateIsCaution(
            final Boolean isCaution
    ){

        //null일 때 기본 값 지정
        if(isCaution == null){
            this.isCaution = false;
        }
        else{
            this.isCaution = isCaution;
        }
    }

    public void updateBirthday(
            final LocalDateTime birthday
    ){
        this.birthday = birthday;
    }

    public void updateAllergy(
            final String allergy
    ){
        this.allergy = allergy;
    }

    public void updateStudyLevel(
            final String studyLevel
    ){
        this.studyLevel = studyLevel;
    }

    public void updateEtc(
            final String etc
    ){
        this.etc = etc;
    }
}
