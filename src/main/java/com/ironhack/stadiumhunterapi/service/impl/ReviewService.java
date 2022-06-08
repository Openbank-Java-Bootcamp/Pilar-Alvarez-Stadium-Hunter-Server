package com.ironhack.stadiumhunterapi.service.impl;

import com.ironhack.stadiumhunterapi.DTO.ReviewDTO;
import com.ironhack.stadiumhunterapi.model.Review;
import com.ironhack.stadiumhunterapi.model.Stadium;
import com.ironhack.stadiumhunterapi.repository.ReviewRepository;
import com.ironhack.stadiumhunterapi.repository.StadiumRepository;
import com.ironhack.stadiumhunterapi.service.interfaces.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReviewService implements IReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private StadiumService stadiumService;

    @Autowired
    private UserService userService;

    public Object findByStadiumId(Long stadiumId){
        return reviewRepository.findReviewsByStadiumId(stadiumId);
    }

    public void saveReview(ReviewDTO reviewDTO){
        Review newReview = new Review();
        newReview.setRating(reviewDTO.getRating());
        newReview.setComment(reviewDTO.getComment());
        newReview.setStadium(stadiumService.findById(reviewDTO.getStadiumId()));
        newReview.setUser(userService.findById(reviewDTO.getUserId()));
        reviewRepository.save(newReview);
    }
}
