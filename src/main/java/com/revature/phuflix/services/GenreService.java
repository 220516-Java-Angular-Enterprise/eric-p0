package com.revature.phuflix.services;

import com.revature.phuflix.daos.GenreDAO;

public class GenreService {

    private final GenreDAO genreDAO;

    public GenreService(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }
}
