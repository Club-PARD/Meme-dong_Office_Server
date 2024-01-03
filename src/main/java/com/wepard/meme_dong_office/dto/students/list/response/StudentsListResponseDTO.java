package com.wepard.meme_dong_office.dto.students.list.response;

import com.wepard.meme_dong_office.dto.students.response.StudentsResponseDTO;
import com.wepard.meme_dong_office.entity.students.list.StudentsList;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class StudentsListResponseDTO {
    private Long id;
    private LocalDateTime createdAt;
    private String name;
    private Integer listRow;
    private Integer listCol;
    private Boolean seatSpacing;
    private Integer studentsCount;
    private List<StudentsResponseDTO> studentsList;

    @Builder
    public StudentsListResponseDTO(
            Long id,
            LocalDateTime createdAt,
            String name,
            Integer listRow,
            Integer listCol,
            Boolean seatSpacing,
            Integer studentsCount,
            List<StudentsResponseDTO> studentsList
    ){
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.listRow = listRow;
        this.listCol = listCol;
        this.seatSpacing = seatSpacing;
        this.studentsCount = studentsCount;
        this.studentsList = studentsList;
    }

    public StudentsListResponseDTO(
            StudentsList studentsList
    ){
        this.id = studentsList.getId();
        this.createdAt = studentsList.getCreatedAt();
        this.name = studentsList.getName();
        this.listRow = studentsList.getListRow();
        this.listCol = studentsList.getListCol();
        this.seatSpacing = studentsList.getSeatSpacing();
        this.studentsCount = studentsList.getStudentsCount();
        this.studentsList = studentsList.getStudentsList()
                .stream()
                .map(StudentsResponseDTO::new)
                .collect(Collectors.toList());
    }
}
