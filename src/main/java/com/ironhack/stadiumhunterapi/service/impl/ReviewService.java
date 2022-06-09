package com.ironhack.stadiumhunterapi.service.impl;

import com.ironhack.stadiumhunterapi.DTO.ReviewDTO;
import com.ironhack.stadiumhunterapi.model.Review;
import com.ironhack.stadiumhunterapi.model.User;
import com.ironhack.stadiumhunterapi.repository.ReviewRepository;
import com.ironhack.stadiumhunterapi.repository.UserRepository;
import com.ironhack.stadiumhunterapi.service.interfaces.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class ReviewService implements IReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private StadiumService stadiumService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    public Object findByStadiumId(Long stadiumId){
        return reviewRepository.findReviewsByStadiumId(stadiumId);
    }

    public void saveReview(ReviewDTO reviewDTO){
        Review newReview = new Review();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            email = authentication.getName();
        }
        User currentUser = userRepository.findByEmail(email);

        newReview.setRating(reviewDTO.getRating());
        newReview.setComment(reviewDTO.getComment());
        newReview.setStadium(stadiumService.findById(reviewDTO.getStadiumId()));
        newReview.setUser(currentUser);
        reviewRepository.save(newReview);
    }
}
