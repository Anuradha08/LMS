package com.lms.userservice.repository;

import com.lms.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends JpaRepository<User,Integer> {
    User findByUsernameAndPassword(String username, String password);
}
