package com.revature.phuflix.services;

import com.revature.phuflix.daos.PhuboxDAO;
import com.revature.phuflix.util.custom_exception.UserInputException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhuboxServiceTest {

    PhuboxService phuboxService = new PhuboxService(new PhuboxDAO());

    @Test
    void isValidST() {
        String[] states = new String[] {"CT", "AZ","NY","VT","CA","NV"};
        List<String> pass = Arrays.asList(states);
        for(String state: pass){
            assertTrue(phuboxService.isValidST(state));
        }

        String fail = "HA";
        assertThrows(UserInputException.class, () -> phuboxService.isValidST(fail));
    }

}