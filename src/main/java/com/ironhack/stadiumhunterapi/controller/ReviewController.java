package com.ironhack.stadiumhunterapi.controller;

import com.ironhack.stadiumhunterapi.DTO.ReviewDTO;
import com.ironhack.stadiumhunterapi.model.Review;
import com.ironhack.stadiumhunterapi.model.Stadium;
import com.ironhack.stadiumhunterapi.model.User;
import com.ironhack.stadiumhunterapi.repository.ReviewRepository;
import com.ironhack.stadiumhunterapi.repository.StadiumRepository;
import com.ironhack.stadiumhunterapi.service.impl.ReviewService;
import com.ironhack.stadiumhunterapi.service.impl.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object getReviewByStadiumId(@PathVariable(name = "id") Long stadiumId) {
        return reviewService.findByStadiumId(stadiumId);
    }

    @PostMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveReview(@RequestBody @Valid ReviewDTO reviewDTO) {
            reviewService.saveReview(reviewDTO);
    }

}
