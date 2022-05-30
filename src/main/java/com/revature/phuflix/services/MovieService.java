package com.revature.phuflix.services;

import com.revature.phuflix.daos.MovieDAO;
import com.revature.phuflix.daos.PhuboxDAO;
import com.revature.phuflix.models.Movies;
import com.revature.phuflix.util.custom_exception.UserInputException;

import java.util.List;

public class MovieService {

    private final MovieDAO movieDAO;
    public MovieService(MovieDAO movieDAO){
        this.movieDAO  = movieDAO;

    }

    public List<Movies> getAllMovies() {
        return movieDAO.getAll();
    }

    public boolean isValidTitle(String input){
        if(input.matches("^(?! ).+(?<! )$")){
            return true;
        }
        throw new UserInputException("Input valid movie title.");
    }

    public boolean isValidPrice(String input){
        if(input.matches("^[+-]?[0-9]{1,3}(?:,?[0-9]{3})*\\.[0-9]{2}$")){
            return true;
        }
        throw new UserInputException("Input valid price format");
    }

    public void save(Movies movie) {
        movieDAO.save(movie);
    }

    public boolean isValidMovie(Movies movie) {
        if(movie.getId()==null){throw new UserInputException("Invalid input");}
        return true;
    }

    public Movies getByID(String id){
        return movieDAO.getById(id);
    }
}
