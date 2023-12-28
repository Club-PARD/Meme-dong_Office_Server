package com.wepard.meme_dong_office.dto.users.response;

import com.wepard.meme_dong_office.dto.students.list.response.StudentsListResponseDTO;
import com.wepard.meme_dong_office.entity.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class UsersResponseDTO {
    private Long id;
    private String email;
    private String name;
    private List<StudentsListResponseDTO> studentsList;

    @Builder
    public UsersResponseDTO(
            final Long id,
            final String email,
            final String name,
            final List<StudentsListResponseDTO> studentList
    ){
        this.id = id;
        this.email = email;
        this.name = name;
        this.studentsList = studentList;
    }
}
