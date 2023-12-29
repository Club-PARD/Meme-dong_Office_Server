package com.wepard.meme_dong_office.service.students.list;

import com.wepard.meme_dong_office.dto.students.list.request.StudentsListRequestDTO;
import com.wepard.meme_dong_office.dto.students.list.response.StudentsListResponseDTO;
import com.wepard.meme_dong_office.dto.students.request.StudentsRequestDTO;
import com.wepard.meme_dong_office.dto.students.response.StudentsResponseDTO;
import com.wepard.meme_dong_office.entity.students.Students;
import com.wepard.meme_dong_office.entity.students.list.StudentsList;
import com.wepard.meme_dong_office.entity.users.Users;
import com.wepard.meme_dong_office.exception.CustomException;
import com.wepard.meme_dong_office.exception.constants.ExceptionCode;
import com.wepard.meme_dong_office.repository.StudentsListRepository;
import com.wepard.meme_dong_office.repository.StudentsRepository;
import com.wepard.meme_dong_office.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentsListService {

    private final StudentsListRepository studentsListRepository;
    private final StudentsRepository studentsRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public StudentsListService(
            final StudentsListRepository studentsListRepository,
            final StudentsRepository studentsRepository,
            final UsersRepository usersRepository
    ){
        this.studentsListRepository = studentsListRepository;
        this.studentsRepository = studentsRepository;
        this.usersRepository = usersRepository;
    }

    public Long createStudentsList(
            final StudentsListRequestDTO studentsListRequestDTO,
            final Long userId
    ){

        final Users users;
        try{
            users = usersRepository.findById(userId).get();
        } catch (Exception ex){
            log.error("StudentsListService.createStudentsList message:{}",ex.getMessage(),ex);
            throw new CustomException(ExceptionCode.FAILED_TO_FIND_USER);
        }


        final String name = studentsListRequestDTO.getName();
        final List<StudentsRequestDTO> students = studentsListRequestDTO.getStudentsList();
        final Integer studentsCount = students.size();

        final StudentsList savedStudentsList;
        final StudentsList putStudentsList = StudentsList.builder()
                .name(name)
                .studentsCount(studentsCount)
                .users(users)
                .build();

        final List<Students> putStudents = new ArrayList<>();

        //putStudentsList를 넣은 Students의 리스트를 생성하기 위함
        for(StudentsRequestDTO student : students){
            putStudents.add(
                    Students
                            .builder()
                            .studentsList(putStudentsList)
                            .name(student.getName())
                            .imageURL(student.getImageURL())
                            .isCaution(student.isCaution())
                            .birthday(student.getBirthday())
                            .allergy(student.getAllergy())
                            .studyLevel(student.getStudyLevel())
                            .etc(student.getEtc())
                            .build()
            );
        }

        try{
            savedStudentsList = studentsListRepository.save(putStudentsList);
            studentsRepository.saveAll(putStudents);
        } catch (Exception ex){
            log.error("StudentsListService.createStudentsList:{}",ex.getMessage(),ex);
            throw new CustomException(ExceptionCode.FAILED_TO_CREATE_STUDENTS_LIST);
        }

        return savedStudentsList.getId();
    }

    public StudentsListResponseDTO getStudentsList(
            final Long studentsListId,
            final Long userId
    ){
        final StudentsList studentsList;
        try{
            studentsList = studentsListRepository.findById(studentsListId).get();
        } catch (Exception ex){
            log.error("StudentsListService.getStudentsList message:{}",ex.getMessage(),ex);
            throw new CustomException(ExceptionCode.FAILED_TO_FIND_STUDENTS_LIST);
        }

        //다른 유저의 정보 접근 막기
        if(!studentsList.getUsers().getId().equals(userId)){
            throw new CustomException(ExceptionCode.INVALID_ACCESS);
        }

        return StudentsListResponseDTO
                .builder()
                .id(studentsList.getId())
                .createdAt(studentsList.getCreatedAt())
                .name(studentsList.getName())
                .studentsCount(studentsList.getStudentsCount())
                .studentsList(studentsList.getStudentsList()
                        .stream()
                        .map(StudentsResponseDTO::new)
                        .collect(Collectors.toList()))
                .build();
    }
}
