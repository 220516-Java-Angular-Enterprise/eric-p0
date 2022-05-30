package com.revature.phuflix.daos;

import com.revature.phuflix.models.Phubox;
import com.revature.phuflix.models.Review;
import com.revature.phuflix.util.custom_exception.UserInputException;
import com.revature.phuflix.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO  implements CrudDAO<Review> {

    // location of database
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Review obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO reviews (id, movie_id, user_id, rating, message, ordered_on) VALUES (?,?,?,?,?,?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getMovie_id());
            ps.setString(3, obj.getUser_id());
            ps.setInt(4, obj.getRating());
            ps.setString(5, obj.getMsg());
            ps.setTimestamp(6, obj.getTimestamp());
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("An error occurred when trying to save to the database");
        }

    }

    @Override
    public void update(Review obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Review getById(String id) {
        return null;
    }

    public double getAvgRatingByMovieID(String movie_id) {
        double avg = 0.0;

        try {
            PreparedStatement ps = con.prepareStatement("SELECT avg(r.rating) FROM reviews r WHERE movie_id = ?");
            ps.setString(1, movie_id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            avg = rs.getDouble("avg");

        }catch (SQLException e){
            throw new UserInputException("Sql Error");
        }
        return avg;
    }

    @Override
    public List<Review> getAll() {
        return null;
    }
}
