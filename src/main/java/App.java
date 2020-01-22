import static spark.Spark.*;
import com.google.gson.Gson;
import dao.Sql2oMovieDao;
import dao.Sql2oCategoryDao;
import models.Movie;
import models.Category;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;


public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
    public static void main(String[]args){
        port(getHerokuAssignedPort());
        Connection connection;
        Gson gson = new Gson();
//        String connectionString = "jdbc:postgresql://localhost:5432/movie_api";
//        Sql2o sql2o = new Sql2o(connectionString, "adwesh", "password");
        String connectionString = "jdbc:postgresql://ec2-34-193-42-173.compute-1.amazonaws.com:5432/dbsf7kvstn938j";
        Sql2o sql2o = new Sql2o(connectionString, "kcdrwkcfrvnnqf", "c3aa461e0793734fe327ff392013ce7eb14d39c95c71ebec0acc4f998743fdb8");
        Sql2oMovieDao movieDao = new Sql2oMovieDao(sql2o);
        Sql2oCategoryDao categoryDao = new Sql2oCategoryDao(sql2o);
        connection = sql2o.open();

        //display all movies
        get("/", "application/json", (request, response) -> {
            return gson.toJson(movieDao.allMovies());
        });

        //add a category/genre
        post("/category/new", "application/json", (request, response) -> {
            Category category = gson.fromJson(request.body(), Category.class);
            categoryDao.save(category);
            response.status(201);
            return gson.toJson(category);
        });
        //view all categories
        get("/categories", "application/json", (request, response) -> {
            return gson.toJson(categoryDao.allCategories());
        });

        //create a movie
        post("/movie/new", "application/json", (request, response) -> {
            Movie movie = gson.fromJson(request.body(), Movie.class);
            movieDao.save(movie);
            response.status(201);
            return gson.toJson(movie);
        });
        //display all movies
        get("/movies", "application/json", (request, response) -> {
            return gson.toJson(movieDao.allMovies());
        });

        //add movie to a category
        post("category/:categoryId/movies/:movieId", "application/json", (request, response) -> {
            int categoryId = Integer.parseInt(request.params("categoryId"));
            int movieId = Integer.parseInt(request.params("movieId"));
            Category category = categoryDao.findById(categoryId);
            Movie movie = movieDao.findById(movieId);
            movieDao.addCategoryToMovie(movie, category);
            response.status(201);
            return gson.toJson(String.format("Category '%s' and movie '%s' have been associated",category.getName(), movie.getTitle()));
        });
            //get movie in a category
            get("category/:id/movies", "application/json", (request, response) -> {
               int categoryId = Integer.parseInt(request.params("id"));
               Category foundCategory = categoryDao.findById(categoryId);
               return gson.toJson(categoryDao.allMoviesInCategory(categoryId));
            });

        //FILTERS
        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
        after((request, response) ->{
            response.type("application/json");
        });

    }
    }
