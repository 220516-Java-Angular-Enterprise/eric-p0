package com.revature.phuflix.services;

import com.revature.phuflix.daos.ReviewDAO;
import com.revature.phuflix.models.Review;

public class ReviewService {
    private final ReviewDAO reviewDAO;

    public ReviewService(ReviewDAO reviewDAO){
        this.reviewDAO = reviewDAO;
    }

    public void save(Review review){
        reviewDAO.save(review);
    }

    public double getAverageScore(String movie_id){
        return reviewDAO.getAvgRatingByMovieID(movie_id);
    }
}
