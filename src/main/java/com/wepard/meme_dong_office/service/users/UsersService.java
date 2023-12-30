package com.wepard.meme_dong_office.service.users;

import com.wepard.meme_dong_office.config.WebSecurityConfig;
import com.wepard.meme_dong_office.dto.students.list.response.StudentsListResponseDTO;
import com.wepard.meme_dong_office.dto.token.response.TokenResponseDTO;
import com.wepard.meme_dong_office.dto.users.request.UsersRequestDTO;
import com.wepard.meme_dong_office.dto.users.response.UsersResponseDTO;
import com.wepard.meme_dong_office.entity.users.Users;
import com.wepard.meme_dong_office.exception.CustomException;
import com.wepard.meme_dong_office.exception.constants.ExceptionCode;
import com.wepard.meme_dong_office.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UsersService {

    private final UsersRepository usersRepository;
    private final WebSecurityConfig webSecurityConfig;

    @Autowired
    public UsersService(
            final UsersRepository usersRepository,
            final WebSecurityConfig webSecurityConfig
    ){
        this.usersRepository = usersRepository;
        this.webSecurityConfig = webSecurityConfig;
    }

    public Long signUp(final UsersRequestDTO usersRequestDTO){

        final String email = usersRequestDTO.getEmail();
        final String hashedPassword = webSecurityConfig
                .getPasswordEncoder()
                .encode(usersRequestDTO.getPassword());

        boolean isEmailExist;
        try{
            isEmailExist = usersRepository.existsByEmail(email);
        } catch (Exception ex){
            log.error("UsersService.signUp:{}",ex.getMessage(),ex);
            throw new CustomException(ExceptionCode.INTERNAL_SERVER_ERROR);
        }


        if(isEmailExist){
            throw new CustomException(ExceptionCode.EMAIL_DUPLICATE);
        }

        final Users putUsers = Users.builder()
                .name(usersRequestDTO.getName())
                .email(usersRequestDTO.getEmail())
                .hashedPassword(hashedPassword)
                .build();
        final Users savedUsers;

        try{
            savedUsers = usersRepository.save(putUsers);
        } catch (Exception ex){
            log.error("UsersService.signUp:{}",ex.getMessage(),ex);
            throw new CustomException(ExceptionCode.INTERNAL_SERVER_ERROR);
        }

        return savedUsers.getId();
    }

    public UsersResponseDTO getUsers(
            final Long userId
    ){

        final Users users;
        try{
            users = usersRepository.findById(userId).get();
        } catch (NoSuchElementException ex){
            throw new CustomException(ExceptionCode.DATA_NOT_EXIST);
        } catch (Exception ex){
            log.error("UsersService.getUsers message:{}",ex.getMessage(),ex);
            throw new CustomException(ExceptionCode.FAILED_TO_FIND_USER);
        }

        return UsersResponseDTO
                .builder()
                .id(users.getId())
                .email(users.getEmail())
                .name(users.getName())
                .studentsList(
                        users.getStudentsLists()
                                .stream()
                                .map(StudentsListResponseDTO::new)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Transactional
    public UsersResponseDTO updateUsers(
            final UsersRequestDTO usersRequestDTO,
            final Long userId
    ){

        final String email = usersRequestDTO.getEmail();
        final String password = usersRequestDTO.getPassword();
        final String name = usersRequestDTO.getName();
        boolean isEmailExist;

        final Users users;
        try{
            users = usersRepository.findById(userId).get();
            isEmailExist = usersRepository.existsByEmail(email);
        } catch (NoSuchElementException ex){
            throw new CustomException(ExceptionCode.DATA_NOT_EXIST);
        } catch (Exception ex){
            log.error("UsersService.updateUsers message:{}",ex.getMessage(),ex);
            throw new CustomException(ExceptionCode.FAILED_TO_FIND_USER);
        }

        //이메일 중복 방지
        if(isEmailExist){
            throw new CustomException(ExceptionCode.EMAIL_DUPLICATE);
        }

        //이메일이 비어있지 않을 때 정보 수정
        if(!StringUtils.isEmpty(email)){
            users.updateEmail(email);
        }

        //비밀번호가 비어있지 않을 때 정보 수정
        if(!StringUtils.isEmpty(password)){
            final String hashedPassword = webSecurityConfig
                    .getPasswordEncoder()
                    .encode(password);
            users.updatePassword(hashedPassword);
        }

        //이름이 비어있지 않을 때 정보 수정
        if(!StringUtils.isEmpty(name)){
            users.updateName(name);
        }

        return UsersResponseDTO
                .builder()
                .id(users.getId())
                .email(users.getEmail())
                .name(users.getName())
                .studentsList(
                        users.getStudentsLists()
                                .stream()
                                .map(StudentsListResponseDTO::new)
                                .collect(Collectors.toList())
                )
                .build();
    }

    public void deleteUsers(
            final Long userId
    ){
        try{
            usersRepository.deleteById(userId);
        } catch (NoSuchElementException ex){
            throw new CustomException(ExceptionCode.DATA_NOT_EXIST);
        } catch (Exception ex){
            log.error("UsersService.deleteUsers message:{}",ex.getMessage(),ex);
            throw new CustomException(ExceptionCode.FAILED_TO_FIND_USER);
        }
    }
}
