package com.wepard.meme_dong_office.service.students;

import com.wepard.meme_dong_office.dto.students.request.StudentsCreateUpdateRequestDTO;
import com.wepard.meme_dong_office.dto.students.response.StudentsResponseDTO;
import com.wepard.meme_dong_office.entity.students.Students;
import com.wepard.meme_dong_office.entity.students.list.StudentsList;
import com.wepard.meme_dong_office.exception.CustomException;
import com.wepard.meme_dong_office.exception.constants.ExceptionCode;
import com.wepard.meme_dong_office.repository.StudentsListRepository;
import com.wepard.meme_dong_office.repository.StudentsRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public Long createStudents(
            final StudentsCreateUpdateRequestDTO studentsCreateUpdateRequestDTO,
            final Long userId
    ){

        final Long studentsListId = studentsCreateUpdateRequestDTO.getStudentsListId();

        final StudentsList studentsList;
        try{
            studentsList = studentsListRepository.findById(studentsListId).get();
        } catch (NoSuchElementException ex){
            throw new CustomException(ExceptionCode.DATA_NOT_EXIST);
        } catch (Exception ex){
            log.error("StudentsService.createStudents message:{}",ex.getMessage(),ex);
            throw new CustomException(ExceptionCode.FAILED_TO_FIND_STUDENTS_LIST);
        }

        //다른 유저의 접근 막기
        if(!studentsList.getUsers().getId().equals(userId)){
            throw new CustomException(ExceptionCode.INVALID_ACCESS);
        }

        final Students students = Students
                .builder()
                .studentsList(studentsList)
                .name(studentsCreateUpdateRequestDTO.getName())
                .imageURL(studentsCreateUpdateRequestDTO.getImageURL())
                .isCaution(studentsCreateUpdateRequestDTO.getIsCaution())
                .birthday(studentsCreateUpdateRequestDTO.getBirthday())
                .allergy(studentsCreateUpdateRequestDTO.getAllergy())
                .studyLevel(studentsCreateUpdateRequestDTO.getStudyLevel())
                .etc(studentsCreateUpdateRequestDTO.getEtc())
                .build();

        try{
            studentsRepository.save(students);
        } catch (Exception ex){
            log.error("StudentsService.createStudents message:{}",ex.getMessage(),ex);
            throw new CustomException(ExceptionCode.FAILED_TO_CREATE_STUDENTS);
        }

        return students.getId();
    }

    public StudentsResponseDTO getStudent(
            final Long studentsId,
            final Long userId
    ){

        final Students students;
        try{
            students = studentsRepository.findById(studentsId).get();
        } catch (NoSuchElementException ex){
            throw new CustomException(ExceptionCode.DATA_NOT_EXIST);
        } catch (Exception ex){
            log.error("StudentsService.getStudents message:{}",ex.getMessage(),ex);
            throw new CustomException(ExceptionCode.FAILED_TO_FIND_STUDENTS);
        }

        //다른 유저의 접근 막기
        if(!students.getStudentsList().getUsers().getId().equals(userId)){
            throw new CustomException(ExceptionCode.INVALID_ACCESS);
        }

        return new StudentsResponseDTO(students);
    }

    @Transactional
    public StudentsResponseDTO updateStudents(
            final StudentsCreateUpdateRequestDTO studentsCreateUpdateRequestDTO,
            final Long studentsId,
            final Long userId
    ){

        final Long studentsListId = studentsCreateUpdateRequestDTO.getStudentsListId();
        final String name = studentsCreateUpdateRequestDTO.getName();
        final String imageURL = studentsCreateUpdateRequestDTO.getImageURL();
        final Boolean isCaution = studentsCreateUpdateRequestDTO.getIsCaution();
        final LocalDateTime birthday = studentsCreateUpdateRequestDTO.getBirthday();
        final String allergy = studentsCreateUpdateRequestDTO.getAllergy();
        final String studyLevel = studentsCreateUpdateRequestDTO.getStudyLevel();
        final String etc = studentsCreateUpdateRequestDTO.getEtc();

        final Students students;
        try{
            students = studentsRepository.findById(studentsId).get();
        } catch (NoSuchElementException ex){
            throw new CustomException(ExceptionCode.DATA_NOT_EXIST);
        } catch (Exception ex){
            log.error("StudentsService.updateStudents message:{}",ex.getMessage(),ex);
            throw new CustomException(ExceptionCode.INTERNAL_SERVER_ERROR);
        }

        //다른 유저 접근 막기
        if(!students.getStudentsList().getUsers().getId().equals(userId)){
            throw new CustomException(ExceptionCode.INVALID_ACCESS);
        }

        //학급 번호 비어있지 않을 때 업데이트
        if(!StringUtils.isEmpty(studentsListId.toString())){

            final StudentsList studentsList;
            try{
                studentsList = studentsListRepository.findById(studentsListId).get();
            } catch (NoSuchElementException ex){
                throw new CustomException(ExceptionCode.DATA_NOT_EXIST);
            } catch (Exception ex){
                log.error("StudentsService.updateStudents message:{}",ex.getMessage(),ex);
                throw new CustomException(ExceptionCode.FAILED_TO_FIND_STUDENTS_LIST);
            }

            students.updateStudentsList(studentsList);
        }

        //이름이 비어있지 않을 때 업데이트
        if(!StringUtils.isEmpty(name)){
            students.updateName(name);
        }

        //이미지 비어있지 않을 때 업데이트
        if(!StringUtils.isEmpty(imageURL)){
            students.updateImageURL(imageURL);
        }

        //요주의 인물 비어있지 않을 때 업데이트
        if(Optional.ofNullable(isCaution).isPresent()){
            students.updateIsCaution(isCaution);
        }

        //생일 비어있지 않을 때 업데이트
        if(Optional.ofNullable(birthday).isPresent()){
            students.updateBirthday(birthday);
        }

        //알러지 비어있지 않을 때 업데이트
        if(!StringUtils.isEmpty(allergy)){
            students.updateAllergy(allergy);
        }

        //공부 레벨 비어있지 않을 때 업데이트
        if(!StringUtils.isEmpty(studyLevel)){
            students.updateStudyLevel(studyLevel);
        }

        //기타 비어있지 않을 때 업데이트
        if(!StringUtils.isEmpty(etc)){
            students.updateEtc(etc);
        }

        return StudentsResponseDTO
                .builder()
                .id(students.getId())
                .name(students.getName())
                .imageURL(students.getImageURL())
                .isCaution(students.getIsCaution())
                .birthday(students.getBirthday())
                .allergy(students.getAllergy())
                .studyLevel(students.getStudyLevel())
                .etc(students.getEtc())
                .build();
    }

    public void deleteStudents(
            final Long studentsId,
            final Long userId
    ){

        final Students students;
        try {
            students = studentsRepository.findById(studentsId).get();
        } catch (NoSuchElementException ex){
            throw new CustomException(ExceptionCode.DATA_NOT_EXIST);
        } catch (Exception ex){
            log.error("StudentsService.deleteStudents message:{}",ex.getMessage(),ex);
            throw new CustomException(ExceptionCode.FAILED_TO_FIND_STUDENTS);
        }

        if(!students.getStudentsList().getUsers().getId().equals(userId)){
            throw new CustomException(ExceptionCode.INVALID_ACCESS);
        }

        try{
            studentsRepository.deleteById(studentsId);
        } catch (NoSuchElementException ex){
            throw new CustomException(ExceptionCode.DATA_NOT_EXIST);
        } catch (Exception ex){
            log.error("StudentsService.deleteStudents message:{}",ex.getMessage(),ex);
            throw new CustomException(ExceptionCode.FAILED_TO_FIND_STUDENTS);
        }
    }
}
