package com.wepard.meme_dong_office.repository;

import com.wepard.meme_dong_office.entity.students.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Students, Long> {
}
