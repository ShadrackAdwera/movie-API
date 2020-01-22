package dao;

import models.Category;
import models.Movie;

import java.util.List;

public interface CategoryDao {
    //CREATE
    void save(Category category);
    void addMovieToCategory(Category category, Movie movie);

    //READ
    List<Category> allCategories();
    Category findById(int id);
    List<Movie> allMoviesInCategory(int categoryId);

    //UPDATE
    //later

    //DELETE
    void deleteCategoryById(int id);
    void clearAll();
}
