package com.ironhack.stadiumhunterapi.controller;

import com.ironhack.stadiumhunterapi.repository.ReviewRepository;
import com.ironhack.stadiumhunterapi.repository.UserRepository;
import com.ironhack.stadiumhunterapi.service.impl.ReviewService;
import com.ironhack.stadiumhunterapi.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;



    //PATCH--add stadium to huntedStadiums
    @PatchMapping("/users/{userId}/{stadiumID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addStadiumToUser(@PathVariable Long userId, @PathVariable Long stadiumId){
        userService.addStadiumToUser(userId, stadiumId);
    }

    //DELETE
}
