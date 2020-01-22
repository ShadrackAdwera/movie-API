package dao;

import models.Category;
import models.Movie;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oMovieDao implements MovieDao {
    private final Sql2o sql2o;

    public Sql2oMovieDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void save(Movie movie){
        String sql = "INSERT INTO movie (title, releaseyear, writer, duration, summary, review) VALUES (:title, :releaseYear, :writer, :duration, :summary, :review)";
        try (Connection connection = sql2o.open()){
            int id = (int) connection.createQuery(sql, true)
                    .bind(movie)
                    .executeUpdate()
                    .getKey();
            movie.setId(id);
        } catch (Sql2oException ex) { System.out.println(ex); }
    }

    @Override
    public void addCategoryToMovie(Movie movie, Category category){
        String sql = "INSERT INTO movie_category (movieid, categoryid) VALUES (:movieId, :categoryId)";
        try (Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .addParameter("movieId", movie.getId())
                    .addParameter("categoryId", category.getId())
                    .executeUpdate();
        }
    }

    @Override
    public List<Category> getAllCategoriesInAMovie(int movieId){
        List<Category> categories = new ArrayList<>();

        String joinQuery = "SELECT categoryid FROM movie_category WHERE movieid = :movieId";

        try (Connection con = sql2o.open()) {
            List<Integer> allCategoryIds = con.createQuery(joinQuery)
                    .addParameter("movieId", movieId)
                    .executeAndFetch(Integer.class);
            for (Integer categoryId : allCategoryIds){
                String categoryQuery = "SELECT * FROM category WHERE id = :categoryId";
                categories.add(
                        con.createQuery(categoryQuery)
                                .addParameter("categoryId", categoryId)
                                .executeAndFetchFirst(Category.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return categories;
    }

    @Override
    public List<Movie> allMovies(){
        String sql = "SELECT * FROM movie";
        try(Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetch(Movie.class);
        }
    }
    @Override
    public Movie findById(int id){
        String sql = "SELECT * FROM movie WHERE id = :id";
        try (Connection connection = sql2o.open()){
            return connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Movie.class);
        }
    }
    @Override
    public void deleteById(int id){
        String sql = "DELETE FROM movie WHERE id = :id";
        try (Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        } catch (Sql2oException ex) { System.out.println(ex); }
    }

    @Override
    public void clearAll(){
        String sql = "DELETE FROM movie";
        try (Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){ System.out.println(ex); }
    }

}
