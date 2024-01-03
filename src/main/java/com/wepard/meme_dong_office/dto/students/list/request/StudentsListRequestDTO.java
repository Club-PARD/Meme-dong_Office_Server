package com.wepard.meme_dong_office.dto.students.list.request;

import com.wepard.meme_dong_office.dto.students.request.StudentsRequestDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class StudentsListRequestDTO {
    private String name;
    private Integer listRow;
    private Integer listCol;
    private Boolean seatSpacing;
    private List<StudentsRequestDTO> studentsList;

    public StudentsListRequestDTO(
            final String name,
            final Integer listRow,
            final Integer listCol,
            final Boolean seatSpacing,
            final List<StudentsRequestDTO> studentsList
    ){
        this.name = name;
        this.listRow = listRow;
        this.listCol = listCol;
        this.seatSpacing = seatSpacing;
        this.studentsList = studentsList;
    }
}
