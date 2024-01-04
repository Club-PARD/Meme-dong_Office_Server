package com.wepard.meme_dong_office.security;

import com.wepard.meme_dong_office.exception.CustomException;
import com.wepard.meme_dong_office.exception.constants.ExceptionCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@Slf4j
public class TokenProvider {

    private static final Key SECURITY_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String getTokenType(){
        return "bearer";
    }

    public String createToken(Long id, int tokenValidityHours){
        Date exprTime = localDateTimeToDate(LocalDateTime.now().plusHours(tokenValidityHours));
        Date nowTime = localDateTimeToDate(LocalDateTime.now());
        String userId = id.toString();
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
                .setSubject(userId).setIssuedAt(nowTime).setExpiration(exprTime)
                .compact();
    }

    public String validate(String token){
        try{
            Claims claims = Jwts.parserBuilder().setSigningKey(SECURITY_KEY).build().parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (Exception ex){
            log.error("TokenProvider.validate message:{}",ex.getMessage(), ex);
            throw new CustomException(ExceptionCode.TOKEN_NOT_VALID);
        }
    }

    private Date localDateTimeToDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
