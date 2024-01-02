package com.wepard.meme_dong_office.dto.students.list.response;

import com.wepard.meme_dong_office.entity.students.list.StudentsList;
import lombok.Getter;

@Getter
public class StudentsListSimpleResponseDTO {
    private Long id;

    public StudentsListSimpleResponseDTO(
            final StudentsList studentsList
    ){
        this.id = studentsList.getId();
    }
}
