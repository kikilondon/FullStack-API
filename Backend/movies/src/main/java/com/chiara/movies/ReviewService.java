package com.chiara.movies;

import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    public Review createReviewBy(String reviewBody, String imdbId){

        Review review = new Review(reviewBody);
        return review;


    }



}
