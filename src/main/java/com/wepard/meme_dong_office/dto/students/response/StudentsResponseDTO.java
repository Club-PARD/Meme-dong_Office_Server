package com.wepard.meme_dong_office.dto.students.response;

import com.wepard.meme_dong_office.entity.students.Students;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class StudentsResponseDTO {
    private Long id;
    private String name;
    private String imageURL;
    private boolean isCaution;
    private LocalDateTime birthday;
    private String allergy;
    private String studyLevel;
    private String etc;

    @Builder
    public StudentsResponseDTO(
            final Long id,
            final String name,
            final String imageURL,
            final boolean isCaution,
            final LocalDateTime birthday,
            final String allergy,
            final String studyLevel,
            final String etc
    ){
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.isCaution = isCaution;
        this.birthday = birthday;
        this.allergy = allergy;
        this.studyLevel = studyLevel;
        this.etc = etc;
    }

    public StudentsResponseDTO(
            Students students
    ){
        this.id = students.getId();
        this.name = students.getName();
        this.imageURL = students.getImageURL();
        this.isCaution = students.isCaution();
        this.birthday = students.getBirthday();
        this.allergy = students.getAllergy();
        this.studyLevel = students.getStudyLevel();
        this.etc = students.getEtc();
    }
}

