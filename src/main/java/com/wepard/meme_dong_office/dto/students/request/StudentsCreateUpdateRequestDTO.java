package com.wepard.meme_dong_office.dto.students.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class StudentsCreateUpdateRequestDTO {
    private Long studentsListId;
    private String name;
    private String imageURL;
    private Boolean isCaution;
    private LocalDateTime birthday;
    private String allergy;
    private String studyLevel;
    private String etc;

    @Builder
    public StudentsCreateUpdateRequestDTO(
            final Long studentsListId,
            final String name,
            final String imageURL,
            final Boolean isCaution,
            final LocalDateTime birthday,
            final String allergy,
            final String studyLevel,
            final String etc
    ){
        this.studentsListId = studentsListId;
        this.name = name;
        this.imageURL = imageURL;
        this.isCaution = isCaution;
        this.birthday = birthday;
        this.allergy = allergy;
        this.studyLevel = studyLevel;
        this.etc = etc;
    }
}