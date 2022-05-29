package com.revature.phuflix.daos;

import com.revature.phuflix.models.Inventory;
import com.revature.phuflix.models.Phubox;
import com.revature.phuflix.util.custom_exception.UserInputException;
import com.revature.phuflix.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhuboxDAO implements CrudDAO<Phubox>{
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Phubox obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Phuboxes (id, address, city,state) VALUES (?,?,?,?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getAddress());
            ps.setString(3, obj.getCity());
            ps.setString(4, obj.getState());
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("An error occurred when trying to save to the database");
        }
    }

    @Override
    public void update(Phubox obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Phubox getById(String id) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM phuboxes WHERE id = ?");
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            return new Phubox(rs.getString("id"), rs.getString("address"), rs.getString("city"),rs.getString("state"));

        }catch(SQLException e){
            throw new UserInputException("Error getting SQL");
        }
    }

    @Override
    public List<Phubox> getAll() {
        List<Phubox> boxes = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * from phuboxes");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Phubox box = new Phubox(rs.getString("id"), rs.getString("address"), rs.getString("city"), rs.getString("state"));
                boxes.add(box);
            }
        }catch (SQLException e){
            throw new UserInputException("Sql Error");
        }
        return boxes;
    }
}
