package com.wepard.meme_dong_office.repository;

import com.wepard.meme_dong_office.entity.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmailAndProvider(String email, String provider);
}
