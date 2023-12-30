package com.wepard.meme_dong_office.service.token;

import com.wepard.meme_dong_office.config.WebSecurityConfig;
import com.wepard.meme_dong_office.dto.refreshToken.request.RefreshTokenRequestDTO;
import com.wepard.meme_dong_office.dto.token.request.TokenRequestDTO;
import com.wepard.meme_dong_office.dto.token.response.TokenResponseDTO;
import com.wepard.meme_dong_office.entity.users.Users;
import com.wepard.meme_dong_office.exception.CustomException;
import com.wepard.meme_dong_office.exception.constants.ExceptionCode;
import com.wepard.meme_dong_office.repository.UsersRepository;
import com.wepard.meme_dong_office.security.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class TokenService {

    private final UsersRepository usersRepository;
    private final TokenProvider tokenProvider;
    private final WebSecurityConfig webSecurityConfig;

    @Autowired
    public TokenService(
            final UsersRepository usersRepository,
            final TokenProvider tokenProvider,
            final WebSecurityConfig webSecurityConfig
    ){
        this.usersRepository = usersRepository;
        this.tokenProvider = tokenProvider;
        this.webSecurityConfig = webSecurityConfig;
    };


    public TokenResponseDTO signIn(
            final TokenRequestDTO tokenRequestDTO
    ){
        final Users users;
        final String email = tokenRequestDTO.getEmail();
        final String password = tokenRequestDTO.getPassword();

        if(StringUtils.containsAny(password, "'", "\"", "\\")){
            throw new CustomException(ExceptionCode.INVALID_INPUT);
        }

        try{
            users = usersRepository.findByEmail(email).get();
        } catch (NoSuchElementException ex){
            throw new CustomException(ExceptionCode.LOGIN_FAILED);
        }

        boolean isPasswordMatch = webSecurityConfig.getPasswordEncoder().matches(
                password,
                users.getHashedPassword()
        );

        // when password is invalid, 패스워드 틀렸을 때
        if(!isPasswordMatch){
            throw new CustomException(ExceptionCode.LOGIN_FAILED);
        }

        //accessToken expire time : 1 hour, 엑세스 토큰 유효 시간 : 1시간
        final String accessToken = tokenProvider.createToken(users.getId(), 1);
        //refreshToken expire time : 2 weeks, 리프레시 토큰 유효 시간 : 2주
        final String refreshToken = tokenProvider.createToken(users.getId(), 336);
        //accessToken expire time (second), 엑세스 토큰 유효 시간 (초)
        final Integer exprTime = 3600;

        final String tokenType = tokenProvider.getTokenType();

        return TokenResponseDTO.builder()
                .accessToken(accessToken)
                .tokenType(tokenType)
                .refreshToken(refreshToken)
                .exprTime(exprTime)
                .userId(users.getId())
                .build();

    }

    public TokenResponseDTO refreshToken(
            final RefreshTokenRequestDTO refreshTokenRequestDTO
    ){
        final String token = refreshTokenRequestDTO.getRefreshToken();
        final Long userId = Long.parseLong(tokenProvider.validate(token));

        //accessToken expire time : 1 hour, 엑세스 토큰 유효 시간 : 1시간
        final String accessToken = tokenProvider.createToken(userId, 1);
        //refreshToken expire time : 2 weeks, 리프레시 토큰 유효 시간 : 2주
        final String refreshToken = tokenProvider.createToken(userId, 336);
        //accessToken expire time (second), 엑세스 토큰 유효 시간 (초)
        final Integer exprTime = 3600;

        final String tokenType = tokenProvider.getTokenType();

        return TokenResponseDTO.builder()
                .accessToken(accessToken)
                .tokenType(tokenType)
                .refreshToken(refreshToken)
                .exprTime(exprTime)
                .userId(userId)
                .build();
    }

}
