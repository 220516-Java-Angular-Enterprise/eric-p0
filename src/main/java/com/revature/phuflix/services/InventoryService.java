package com.revature.phuflix.services;

import com.revature.phuflix.daos.InventoryDAO;
import com.revature.phuflix.daos.UserDAO;
import com.revature.phuflix.models.Inventory;
import com.revature.phuflix.util.custom_exception.UserInputException;

import java.util.List;

public class InventoryService {

    private final InventoryDAO inventoryDAO;


    public InventoryService(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }


    public List<Inventory> getInventoryByPhuboxID(String id) {
        return inventoryDAO.getAllbyPhuboxId(id);
    }

    public void save(Inventory inv){
        inventoryDAO.save(inv);
    }

    public void update(Inventory inv) {
        inventoryDAO.update(inv);
    }

    public Inventory getInventoryByIDs(String movie_id, String phubox_id){
        return inventoryDAO.getById(movie_id,phubox_id);
    }

    public boolean isValidQuanity(String s){
        if(s.matches("^\\d*")){
            return true;
        }
        throw new UserInputException("Please enter a number");
    }
}
