package com.revature.phuflix.daos;

import com.revature.phuflix.models.Orders;
import com.revature.phuflix.util.database.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class OrderDAO implements CrudDAO<Orders> {

    // location of database
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Orders obj) {

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
}
