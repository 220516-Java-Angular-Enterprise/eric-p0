package com.revature.phuflix.services;

import com.revature.phuflix.daos.InventoryDAO;
import com.revature.phuflix.daos.UserDAO;
import com.revature.phuflix.models.Inventory;

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
}
