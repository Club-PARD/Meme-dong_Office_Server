package com.wepard.meme_dong_office.dto.students.list.request;

import com.wepard.meme_dong_office.dto.students.request.StudentsRequestDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class StudentsListRequestDTO {
    private String name;
    private List<StudentsRequestDTO> studentsList;

    public StudentsListRequestDTO(
            String name,
            List<StudentsRequestDTO> studentsList
    ){
        this.name = name;
        this.studentsList = studentsList;
    }
}
