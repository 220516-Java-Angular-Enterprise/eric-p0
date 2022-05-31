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

    // will only get >0
    public Inventory getInventoryByIDs(String movie_id, String phubox_id){
        return inventoryDAO.getById(movie_id,phubox_id);
    }

    public boolean isValidQuanity(String s){
        if(s.matches("^\\d*")){
            return true;
        }
        throw new UserInputException("Please enter a number");
    }

    public void substract(String movie_id, String phubox_id) {
        int qty = getQty(movie_id, phubox_id) -1;
        inventoryDAO.orderDone(movie_id, phubox_id, qty);
    }

    public int getQty(String movie_id, String phubox_id) {
        return inventoryDAO.getQTY(movie_id,phubox_id);
    }
}
