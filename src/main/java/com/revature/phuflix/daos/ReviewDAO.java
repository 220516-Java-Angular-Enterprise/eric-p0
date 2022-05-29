package com.revature.phuflix.daos;

import com.revature.phuflix.models.Review;
import com.revature.phuflix.util.database.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class ReviewDAO  implements CrudDAO<Review> {

    // location of database
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Review obj) {

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

    @Override
    public List<Review> getAll() {
        return null;
    }
}
