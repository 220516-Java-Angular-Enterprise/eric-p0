package com.revature.phuflix.services;

import com.revature.phuflix.daos.PhuboxDAO;
import com.revature.phuflix.models.Phubox;
import com.revature.phuflix.util.custom_exception.UserInputException;

import java.util.ArrayList;
import java.util.Arrays;

public class PhuboxService {
    private static PhuboxDAO phuboxDAO;

    public PhuboxService(PhuboxDAO phuboxDAO){
        this.phuboxDAO = phuboxDAO;
    }

    public boolean isValidST(String state){
    String[] states = {"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"};

    for (String s: states){
        if (state.equals(s)){
            return true;
        }
    }
    throw new UserInputException("Not valid State Abbreviation");
    }

    public void newBox(Phubox phubox){
        phuboxDAO.save(phubox);
    }
}
