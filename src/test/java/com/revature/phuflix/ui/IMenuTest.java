package com.revature.phuflix.ui;

import com.revature.phuflix.daos.UserDAO;
import com.revature.phuflix.services.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IMenuTest {

    StartMenu container = new StartMenu(new UserService(new UserDAO()));

    @Test
    void scoreToStars() {

        assertEquals("No Reviews", container.scoreToStars( 0));
        assertEquals("⭑⭒⭒⭒⭒", container.scoreToStars( 0.8));
        assertEquals("⭑⭒⭒⭒⭒", container.scoreToStars( 1.3));
        assertEquals("⭑⭑⭒⭒⭒", container.scoreToStars( 1.7));
        assertEquals("⭑⭑⭒⭒⭒", container.scoreToStars( 2.2));
        assertEquals("⭑⭑⭑⭒⭒", container.scoreToStars( 2.8));
        assertEquals("⭑⭑⭑⭒⭒", container.scoreToStars( 3.1));
        assertEquals("⭑⭑⭑⭑⭒", container.scoreToStars( 3.9));
        assertEquals("⭑⭑⭑⭑⭒", container.scoreToStars( 4.4));
        assertEquals("⭑⭑⭑⭑⭑", container.scoreToStars( 4.8));
        assertEquals("⭑⭑⭑⭑⭑", container.scoreToStars( 5));


    }
}