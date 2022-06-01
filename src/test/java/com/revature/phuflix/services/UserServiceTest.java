package com.revature.phuflix.services;

import com.revature.phuflix.daos.UserDAO;
import com.revature.phuflix.util.custom_exception.UserInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UserServiceTest {
    UserService userService = new UserService(new UserDAO());

    @Test
    void isUniqueUsername() {
        String usernameFail = "Phutondog12";
        Assertions.assertThrows(UserInputException.class, () -> userService.isUniqueUsername(usernameFail));
        String usernamePass = "PSFEASSCA123";
        Assertions.assertTrue(userService.isUniqueUsername(usernamePass));
    }

    @Test
    void isValidUsername() {
        String toShort = "hello";
        String beginFAil = ".Sodaman123";
        String insideFail= "He..lloman123";
        Assertions.assertThrows(UserInputException.class, () -> userService.isValidUsername(toShort));
        Assertions.assertThrows(UserInputException.class, () -> userService.isValidUsername(beginFAil));
        Assertions.assertThrows(UserInputException.class, () -> userService.isValidUsername(insideFail));
    }

    @Test
    void isValidPassword() {
        String noSpecialCharter = "Sofaking123";
        String noNumber = "Sof@king123";
        Assertions.assertThrows(UserInputException.class, () -> userService.isValidPassword(noSpecialCharter));
        Assertions.assertThrows(UserInputException.class, () -> userService.isValidPassword(noNumber));
    }
}