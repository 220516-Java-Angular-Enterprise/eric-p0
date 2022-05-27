package com.revature.phuflix.daos;

import com.revature.phuflix.models.Phubox;
import com.revature.phuflix.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PhuboxDAO implements CrudDAO<Phubox>{
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Phubox obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Phubox (id, city,state) VALUES (?,?,?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getCity());
            ps.setString(3, obj.getState());
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
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }
}
