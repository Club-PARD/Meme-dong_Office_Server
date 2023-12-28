package com.wepard.meme_dong_office.repository;

import com.wepard.meme_dong_office.entity.students.list.StudentsList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsListRepository extends JpaRepository<StudentsList, Long> {
}
