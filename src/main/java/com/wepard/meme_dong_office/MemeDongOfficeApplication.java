package com.wepard.meme_dong_office;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MemeDongOfficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemeDongOfficeApplication.class, args);
	}

}
