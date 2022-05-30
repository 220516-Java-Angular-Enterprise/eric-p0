package com.revature.phuflix.daos;

import com.revature.phuflix.models.Orders;
import com.revature.phuflix.models.Phubox;
import com.revature.phuflix.util.custom_exception.UserInputException;
import com.revature.phuflix.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements CrudDAO<Orders> {

    // location of database
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Orders obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders (id, user_id, phubox_id, movie_id, qty, ordered_on) VALUES (?,?,?,?,?,?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUser_id());
            ps.setString(3, obj.getPhubox_id());
            ps.setString(4, obj.getMovie_id());
            ps.setInt(5, obj.getQty());
            ps.setTimestamp(6, obj.getTimestamp());
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("An error occurred when trying to save to the database");
        }

    }

    @Override
    public void update(Orders obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Orders getById(String id) {
        return null;
    }

    @Override
    public List<Orders> getAll() {
        return null;
    }

    public List<Orders> getShoppingCart(String user_id){
        List<Orders> shopingCart = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE user_id = ? AND ordered_on is null");
            ps.setString(1, user_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Orders order = new Orders();

                order.setId(rs.getString("id"));
                order.setUser_id(rs.getString("user_id"));
                order.setPhubox_id(rs.getString("phubox_id"));
                order.setMovie_id(rs.getString("movie_id"));
                order.setQty(rs.getInt("qty"));

                shopingCart.add(order);
            }
        }catch (SQLException e){
            throw new UserInputException("Sql Error");
        }
        return shopingCart;
    }

}
