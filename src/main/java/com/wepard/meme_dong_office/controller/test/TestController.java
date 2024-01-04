package com.wepard.meme_dong_office.controller.test;

import com.wepard.meme_dong_office.dto.test.StudentsTestDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/applelogintest")
    public String applelogintest(){
        return "asfd";
    }

    @GetMapping("/students")
    public List<StudentsTestDTO> getAllStudentsName(){
        List<StudentsTestDTO> result = new ArrayList<>();

        result.add(StudentsTestDTO.builder().name("강산길").build());
        result.add(StudentsTestDTO.builder().name("김민섭").build());
        result.add(StudentsTestDTO.builder().name("김형진").build());
        result.add(StudentsTestDTO.builder().name("서하민").build());
        result.add(StudentsTestDTO.builder().name("이신원").build());
        result.add(StudentsTestDTO.builder().name("임예진").build());

        return result;
    }

    @GetMapping("/xss")
    public String xssTest(@RequestParam final String s){
        return s;
    }
}
