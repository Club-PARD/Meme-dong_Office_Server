package com.wepard.meme_dong_office.service.students.images;


import com.wepard.meme_dong_office.entity.students.Students;
import com.wepard.meme_dong_office.exception.CustomException;
import com.wepard.meme_dong_office.exception.constants.ExceptionCode;
import com.wepard.meme_dong_office.repository.StudentsRepository;
import com.wepard.meme_dong_office.service.awsS3.AWSS3Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentsImagesService {

    private final AWSS3Service awss3Service;
    private final StudentsRepository studentsRepository;

    @Transactional
    public String uploadStudentsImages(
            final MultipartFile multipartFile,
            final Long studentsId,
            final Long userId
    ){

        final Students students;
        try{
            students = studentsRepository.findById(studentsId).get();
        } catch (NoSuchElementException ex){
            throw new CustomException(ExceptionCode.DATA_NOT_EXIST);
        } catch (Exception ex){
            log.error("StudentsImagesService.uploadStudentsImages message:{}",ex.getMessage(),ex);
            throw new CustomException(ExceptionCode.FAILED_TO_FIND_STUDENTS);
        }

        //다른 유저의 정보 접속 막기
        if(!students.getStudentsList().getUsers().getId().equals(userId)){
            throw new CustomException(ExceptionCode.INVALID_ACCESS);
        }

        final String imageURL = awss3Service.uploadImages(multipartFile);

        students.updateImageURL(imageURL);

        return imageURL;
    }

    @Transactional
    public void deleteStudentsImages(
            final Long studentsId,
            final Long userId
    ){

        final Students students;
        try{
            students = studentsRepository.findById(studentsId).get();
        } catch (NoSuchElementException ex){
            throw new CustomException(ExceptionCode.DATA_NOT_EXIST);
        } catch (Exception ex){
            log.error("StudentsImagesService.uploadStudentsImages message:{}",ex.getMessage(),ex);
            throw new CustomException(ExceptionCode.FAILED_TO_FIND_STUDENTS);
        }

        //다른 유저의 정보 접속 막기
        if(!students.getStudentsList().getUsers().getId().equals(userId)){
            throw new CustomException(ExceptionCode.INVALID_ACCESS);
        }

        students.updateImageURL("");
    }
}
