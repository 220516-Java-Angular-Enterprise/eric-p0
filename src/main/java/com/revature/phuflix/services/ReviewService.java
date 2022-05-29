package com.revature.phuflix.services;

import com.revature.phuflix.daos.ReviewDAO;
import com.revature.phuflix.models.Review;

public class ReviewService {
    private final ReviewDAO reviewDAO;

    public ReviewService(ReviewDAO reviewDAO){
        this.reviewDAO = reviewDAO;
    }
}
