package com.revature.phuflix.daos;

import com.revature.phuflix.models.Inventory;
import com.revature.phuflix.util.custom_exception.UserInputException;
import com.revature.phuflix.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO implements CrudDAO<Inventory>{


    // location of database
    Connection con = DatabaseConnection.getCon();
    @Override
    public void save(Inventory obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO inventory (movie_id, phubox_id, qty) VALUES (?,?,?)");
            ps.setString(1, obj.getMovie_id());
            ps.setString(2, obj.getPhubox_id());
            ps.setInt(3, obj.getQty());
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("An error occurred when trying to save to the database");
        }

    }

    @Override
    public void update(Inventory obj) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE inventory SET qty = ? WHERE movie_id = ? AND phubox_id = ?");
            ps.setInt(1, obj.getQty());
            ps.setString(2, obj.getMovie_id());
            ps.setString(3, obj.getPhubox_id());
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("An error occurred when trying to save to the database");
        }

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Inventory getById(String id) {
        return null;
    }

    public Inventory getById(String movie_id, String phubox_id) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM inventory WHERE movie_id = ? AND phubox_id = ?");
            ps.setString(1,movie_id);
            ps.setString(2, phubox_id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            return new Inventory(rs.getString("movie_id"), rs.getString("phubox_id"), rs.getInt("qty"));

        }catch(SQLException e){
            throw new UserInputException("Error getting SQL");
        }
    }

    @Override
    public List<Inventory> getAll() {
        return null;
    }

    public List<Inventory> getAllbyPhuboxId(String id) {
        List<Inventory> inventories = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM inventory WHERE phubox_id = " + id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Inventory inv = new Inventory(rs.getString("movie_id"),id,rs.getInt("qty"));
                inventories.add(inv);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return inventories;
    }
}
