package com.ironhack.stadiumhunterapi.service.interfaces;

import com.ironhack.stadiumhunterapi.model.Stadium;

public interface IStadiumService {
    Stadium findById(Long id);



    //In case i add an admin
    //void saveStadium(Stadium stadium);
    //void update(Long id, Stadium stadium);
    //void deleteStadium(Long id);
}
