package com.wepard.meme_dong_office.service.students;

import com.wepard.meme_dong_office.dto.students.request.StudentsRequestDTO;
import com.wepard.meme_dong_office.dto.students.response.StudentsResponseDTO;
import com.wepard.meme_dong_office.entity.students.Students;
import com.wepard.meme_dong_office.entity.students.list.StudentsList;
import com.wepard.meme_dong_office.repository.StudentsListRepository;
import com.wepard.meme_dong_office.repository.StudentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentsService {

    private final StudentsRepository studentsRepository;
    private final StudentsListRepository studentsListRepository;

    @Autowired
    public StudentsService(
            final StudentsRepository studentsRepository,
            final StudentsListRepository studentsListRepository
    ){
        this.studentsRepository = studentsRepository;
        this.studentsListRepository = studentsListRepository;
    }

}
