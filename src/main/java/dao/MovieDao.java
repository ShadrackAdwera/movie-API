package dao;

import models.Movie;

import java.util.List;

public interface MovieDao {

    //CREATE
    void save(Movie movie);
    //void addCategoryToMovie()

    //READ
    List<Movie> allMovies();
    Movie findById(int id);
    //List<Movies> getAllMoviesInACategory(int categoryId);

    //UPDATE
    //void update(String title, int releaseYear, String writer, int duration, String summary, String review);

    //DESTROY
    void deleteById(int id);
    void clearAll();
}
