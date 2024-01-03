package com.wepard.meme_dong_office.dto.students.list.request;

import lombok.Getter;

@Getter
public class StudentsListUpdateRequestDTO {
    private String name;
    private Integer listRow;
    private Integer listCol;
    private Boolean seatSpacing;
}
