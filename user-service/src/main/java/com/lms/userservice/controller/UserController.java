package com.lms.userservice.controller;

import com.lms.userservice.entity.User;
import com.lms.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1.0/lms/company")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) throws Exception {
        User response = new User();
        try {
            response = userService.save(user);
            logger.info("User registration successful");
        }
        catch(Exception e){
            logger.info("User registration failed - {}", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/login")
    public ResponseEntity loginUser(@RequestBody User user) {
        User response = new User();
        try {
            response = userService.login(user);
            if(response == null){
                logger.info("Incorrect Username or Password");
            }
        }
        catch(Exception e){
            logger.info("Unable to login - {}",e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
