package com.wepard.meme_dong_office.service.users;

import com.wepard.meme_dong_office.config.WebSecurityConfig;
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

@Service
@Slf4j
public class UsersService {

    private final UsersRepository usersRepository;
    private final WebSecurityConfig webSecurityConfig;

    @Autowired
    public UsersService(
            UsersRepository usersRepository,
            WebSecurityConfig webSecurityConfig
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
}
