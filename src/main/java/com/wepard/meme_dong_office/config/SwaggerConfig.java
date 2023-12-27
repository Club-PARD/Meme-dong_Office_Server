package com.wepard.meme_dong_office.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .addSecurityItem(securityRequirement())
                .components(components())
                .info(APIInfo());
    }

    private SecurityRequirement securityRequirement(){
        return new SecurityRequirement()
                .addList("jwtAuth");
    }

    private Components components(){
        return new Components()
                .addSecuritySchemes("jwtAuth", new SecurityScheme()
                        .name("jwtAuth")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                );
    }

    private Info APIInfo(){
        return new Info()
                .title("학급 아이들 이름 외우기 서비스")
                .description("학급 아이들의 이름과 자리 배치를 외울 수 있는 서비스")
                .version("0.1.0");
    }
}
