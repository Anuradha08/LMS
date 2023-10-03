package com.lms.userservice.service;

import com.lms.userservice.entity.User;
import com.lms.userservice.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRespository userRespository;

    public User save(User user) {
        return userRespository.save(user);
    }

    public User login(User user) {
        return userRespository.findByUsernameAndPassword(user.getName(), user.getPassword());
    }
}
