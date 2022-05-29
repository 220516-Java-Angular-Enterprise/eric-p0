package com.revature.phuflix.daos;

import com.revature.phuflix.models.Actor;
import com.revature.phuflix.util.database.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class ActorDAO implements CrudDAO<Actor> {

    // location of database
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Actor obj) {

    }

    @Override
    public void update(Actor obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Actor getById(String id) {
        return null;
    }

    @Override
    public List<Actor> getAll() {
        return null;
    }
}
