package com.ironhack.stadiumhunterapi.repository;

import com.ironhack.stadiumhunterapi.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "SELECT name, comment FROM review JOIN user ON review.user_id = user.id WHERE stadium_id = :stadiumId", nativeQuery = true)
    Object findReviewsByStadiumId(@Param("stadiumId") Long stadiumId);
}
