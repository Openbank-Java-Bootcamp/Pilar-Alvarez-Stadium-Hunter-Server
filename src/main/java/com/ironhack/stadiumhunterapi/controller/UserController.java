package com.ironhack.stadiumhunterapi.controller;


import com.ironhack.stadiumhunterapi.repository.UserRepository;
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
    @PatchMapping("/users/{stadiumID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addStadiumToUser(@PathVariable("stadiumID") Long stadiumId){
        userService.addStadiumToUser(stadiumId);
    }

    //DELETE--delete stadium from huntedStadiums
    @DeleteMapping("/users/{stadiumID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeStadium(@PathVariable("stadiumID") Long stadiumId){
        userService.removeStadium(stadiumId);
    }

}
