package com.wepard.meme_dong_office.dto.test;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentsTestDTO {
    private String name;

    @Builder
    public StudentsTestDTO(
            final String name
    ){
        this.name = name;
    }
}
