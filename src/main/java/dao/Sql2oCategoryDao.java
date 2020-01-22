package dao;

import models.Category;
import models.Movie;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oCategoryDao implements CategoryDao {
    private final Sql2o sql2o;

    public Sql2oCategoryDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void save(Category category){
        String sql = "INSERT INTO category (name, description) VALUES (:name, :description)";
        try (Connection connection = sql2o.open()){
            int id = (int) connection.createQuery(sql)
                    .bind(category)
                    .executeUpdate()
                    .getKey();
            category.setId(id);
        } catch (Sql2oException ex) { System.out.println(ex); }
    }
    @Override
    public void addMovieToCategory(Category category, Movie movie){
    String sql = "INSERT INTO movie_category (categoryid, movieid) VALUES (:categoryId, :movieId)";
    try(Connection connection = sql2o.open()) {
        connection.createQuery(sql)
                .addParameter("categoryId", category.getId())
                .addParameter("movieId",movie.getId())
                .executeUpdate();
    } catch (Sql2oException ex) { System.out.println(ex); }
    }
    @Override
    public List<Category> allCategories(){
        String sql = "SELECT * FROM category";
        try(Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetch(Category.class);
        }
    }
    @Override
    public Category findById(int id){
        String sql = "SELECT * FROM category WHERE id = :id";
        try(Connection connection = sql2o.open()) {
           return connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Category.class);
        }
    }

    @Override
    public List<Movie> allMoviesInCategory(int categoryId){
        List<Movie> movies = new ArrayList<>();

        String joinQuery = "SELECT movieid FROM movie_category WHERE categoryid = :categoryId";

        try (Connection con = sql2o.open()) {
            List<Integer> allMovieIds = con.createQuery(joinQuery)
                    .addParameter("categoryId", categoryId)
                    .executeAndFetch(Integer.class);
            for (Integer movieId : allMovieIds){
                String movieQuery = "SELECT * FROM movie WHERE id = :movieId";
                movies.add(
                        con.createQuery(movieQuery)
                                .addParameter("movieId", movieId)
                                .executeAndFetchFirst(Movie.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return movies;
    }
    @Override
    public void deleteCategoryById(int id){
        String sql = "DELETE FROM category WHERE id = :id";
        try(Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        } catch (Sql2oException ex) { System.out.println(ex); }
    }
    @Override
    public void clearAll(){
        String sql = "DELETE FROM category";
        try(Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) { System.out.println(ex); }
    }
}
