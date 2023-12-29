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
import lombok.extern.slf4j.Slf4j;
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

    /**
     *
     * @param usersRequestDTO
     * @return userId
     */
    public Long signUp(final UsersRequestDTO usersRequestDTO){

        final String email = usersRequestDTO.getEmail();
        final String hashedPassword = webSecurityConfig
                .getPasswordEncoder()
                .encode(usersRequestDTO.getPassword());
        usersRequestDTO.updatePassword(hashedPassword);

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
                .hashedPassword(usersRequestDTO.getPassword())
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
            Long userId
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
}
