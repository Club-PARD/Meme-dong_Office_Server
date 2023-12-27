package com.wepard.meme_dong_office.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig {

    @Bean
    public WebMvcConfigurer CORSConfigurer(){
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry corsRegistry){

                //for develop
                corsRegistry
                        .addMapping("/**")
                        .allowedOriginPatterns("*")
                        .allowedMethods("GET","POST","PATCH","DELETE","OPTION");
            }
        };
    }
}
