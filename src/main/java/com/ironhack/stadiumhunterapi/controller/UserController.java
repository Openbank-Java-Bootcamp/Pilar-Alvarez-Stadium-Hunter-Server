package com.ironhack.stadiumhunterapi.controller;


import com.ironhack.stadiumhunterapi.model.Stadium;
import com.ironhack.stadiumhunterapi.model.User;
import com.ironhack.stadiumhunterapi.repository.UserRepository;
import com.ironhack.stadiumhunterapi.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

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
    public void addStadiumToUser(@PathVariable("stadiumID") Long stadiumId, Authentication authentication){
        userService.addStadiumToUser(stadiumId, authentication);
    }

    //DELETE--delete stadium from huntedStadiums
    @DeleteMapping("/users/{stadiumID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeStadium(@PathVariable("stadiumID") Long stadiumId){
        userService.removeStadium(stadiumId);
    }

    @GetMapping("/users/stadiums")
    @ResponseStatus(HttpStatus.OK)
    public List<Stadium> getUserStadiums(){
        return userService.getUserStadiums();
    }

    @GetMapping("/users/remainingStadiums")
    @ResponseStatus(HttpStatus.OK)
    public List<Stadium> getRemainingStadiums(){
        return userService.getRemainingStadiums();
    }

    @GetMapping("/users/count")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getTopUsersCount(){
        return userService.getTopUsers();
    }

    @GetMapping("users/countries")
    @ResponseStatus(HttpStatus.OK)
    public List<String> countriesByUserId(){
        return userService.countriesByUserId();
    }

}
