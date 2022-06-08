package com.ironhack.stadiumhunterapi.repository;

import com.ironhack.stadiumhunterapi.model.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Long> {
}
