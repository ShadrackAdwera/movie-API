package dao;

import models.Category;
import models.Movie;

import java.util.List;

public interface MovieDao {

    //CREATE
    void save(Movie movie);
    void addCategoryToMovie(Movie movie, Category category);

    //READ
    List<Movie> allMovies();
    Movie findById(int id);
    List<Category> getAllCategoriesInAMovie(int movieId);

    //UPDATE
    //void update(String title, int releaseYear, String writer, int duration, String summary, String review);

    //DESTROY
    void deleteById(int id);
    void clearAll();
}
