package com.revature.phuflix.services;

import com.revature.phuflix.daos.ActorDAO;

public class ActorService {
    private final ActorDAO actorDAO;

    public ActorService(ActorDAO actorDAO) {
        this.actorDAO = actorDAO;
    }
}
