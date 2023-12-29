package com.wepard.meme_dong_office.service.students.list.names;

import com.wepard.meme_dong_office.dto.students.list.names.response.StudentsListNamesResponseDTO;
import com.wepard.meme_dong_office.entity.students.Students;
import com.wepard.meme_dong_office.entity.students.list.StudentsList;
import com.wepard.meme_dong_office.exception.CustomException;
import com.wepard.meme_dong_office.exception.constants.ExceptionCode;
import com.wepard.meme_dong_office.repository.StudentsListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class StudentsListNamesService {

    private final StudentsListRepository studentsListRepository;

    @Autowired
    public StudentsListNamesService(
            final StudentsListRepository studentsListRepository
    ){
        this.studentsListRepository = studentsListRepository;
    }

    public StudentsListNamesResponseDTO getStudentsListNames(
            final Long studentsListId,
            final Long userId
    ){

        final StudentsList studentsList;
        final List<String> nameList = new ArrayList<>();

        try{
            studentsList = studentsListRepository.findById(studentsListId).get();
        } catch (NoSuchElementException ex){
            throw new CustomException(ExceptionCode.DATA_NOT_EXIST);
        } catch (Exception ex){
            log.error("StudentsListNamesService.getStudentsListNames message:{}",ex.getMessage(),ex);
            throw new CustomException(ExceptionCode.FAILED_TO_FIND_STUDENTS_LIST);
        }

        //다른 유저의 정보 접근 막기
        if(!studentsList.getUsers().getId().equals(userId)){
            throw new CustomException(ExceptionCode.INVALID_ACCESS);
        }

        for(Students students : studentsList.getStudentsList()){
            nameList.add(students.getName());
        }

        return StudentsListNamesResponseDTO
                .builder()
                .nameList(nameList)
                .build();
    }
}
