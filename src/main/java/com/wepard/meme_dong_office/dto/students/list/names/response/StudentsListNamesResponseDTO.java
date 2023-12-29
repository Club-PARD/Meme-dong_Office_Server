package com.wepard.meme_dong_office.dto.students.list.names.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class StudentsListNamesResponseDTO {
    private List<String> nameList;

    @Builder
    public StudentsListNamesResponseDTO(
            List<String> nameList
    ){
        this.nameList = nameList;
    }
}
