package com.chiara.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.aggregation.SelectionOperators.First.first;

// create a review to push into a specific movie section into MongoDB
@Service
public class ReviewService {
    @Autowired

    private ReviewRepository reviewRepository;
@Autowired
    private MongoTemplate mongoTemplate;
//we receive from the user the reviewBody and the imdbId to upload a review
    public Review createReviewBy(String reviewBody, String imdbId){
//new object
        Review review = reviewRepository.insert(new Review(reviewBody));

        reviewRepository.insert(review);
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                //push new review inside the reviewIds of a specific movie into MongoDB
                .apply(new Update().push( "reviewIds").value(review))
                .first();// push as first into the repository
        return review;

    }



}
