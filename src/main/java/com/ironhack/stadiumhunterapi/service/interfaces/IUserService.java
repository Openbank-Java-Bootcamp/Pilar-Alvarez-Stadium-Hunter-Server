package com.ironhack.stadiumhunterapi.service.interfaces;

import com.ironhack.stadiumhunterapi.model.User;

import java.util.List;

public interface IUserService {
    User saveUser(User userSignupDTO);

    List<User> getUsers();
}
