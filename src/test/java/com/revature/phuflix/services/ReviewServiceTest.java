package com.revature.phuflix.services;

import com.revature.phuflix.daos.ReviewDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewServiceTest {
    ReviewService reviewService = new ReviewService(new ReviewDAO());

    @Test
    void getAverageScore() {
        String movieID = "64a45a1d-44ad-492a-93f6-364d571625b7";
        assertEquals(4.333333333333333, reviewService.getAverageScore("64a45a1d-44ad-492a-93f6-364d571625b7"));
    }
}