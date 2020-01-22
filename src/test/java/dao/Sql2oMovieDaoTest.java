package dao;

import models.Category;
import models.Movie;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oMovieDaoTest {

    private static Sql2oMovieDao movieDao;
    private static Sql2oCategoryDao categoryDao;
    private static Connection connection;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/movie_api_test";
        Sql2o sql2o = new Sql2o(connectionString, "adwesh", "password");
        categoryDao = new Sql2oCategoryDao(sql2o);
        movieDao = new Sql2oMovieDao(sql2o);
        connection = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("CLEARING DATABASE!!!!!!!!!");
        categoryDao.clearAll();
        movieDao.clearAll();
    }
    @AfterClass
    public static void shutDown() throws Exception {
        System.out.println("SHUTTING DOWN DATABASE!!!!!!");
        connection.close();
    }

    @Test
    public void save_savesMovieSuccessfullyInTheDatabaseWithID() {
        Movie movie = setUpMovie();
        movieDao.save(movie);
        int movieId = movie.getId();
        int idOfFoundMovie = movieDao.findById(movieId).getId();
        assertEquals(movieId, idOfFoundMovie);

    }

    @Test
    public void addCategoryToMovie() {
        Movie movie = setUpMovie();
        movieDao.save(movie);
        Category category = new Category("Horror", "Haunted House");
        categoryDao.save(category);
        Category category1 = new Category("Comedy","Laugh your ass out");
        movieDao.addCategoryToMovie(movie,category);
        movieDao.addCategoryToMovie(movie,category1);
        assertEquals(2, movieDao.getAllCategoriesInAMovie(movie.getId()).size());

    }

    @Test
    public void getAllCategoriesInAMovie() {
        Movie movie = setUpMovie();
        movieDao.save(movie);
        Category category = new Category("Horror", "Haunted House");
        categoryDao.save(category);
        Category category1 = new Category("Comedy","Laugh your ass out");
        categoryDao.save(category1);
        movieDao.addCategoryToMovie(movie,category);
        movieDao.addCategoryToMovie(movie,category1);

        Category [] categories = {category, category1};
        assertEquals(Arrays.asList(categories), movieDao.getAllCategoriesInAMovie(movie.getId()));

    }

    @Test
    public void allMovies_moviesFoundByAllMovies() {
        Movie movie = setUpMovie();
        movieDao.save(movie);
        Movie movie1 = setUpMovie();
        movieDao.save(movie1);
        assertEquals(2, movieDao.allMovies().size());
    }

    @Test
    public void findById_returnsMovieBasedOnId() {
        Movie movie = setUpMovie();
        movieDao.save(movie);
        assertEquals(movie.getId(), movieDao.findById(movie.getId()).getId());

    }

    @Test
    public void deleteById_deleteMovieBasedOnID() {
        Movie movie =setUpMovie();
        movieDao.save(movie);
        Movie movie1 = setUpMovie();
        movieDao.save(movie1);
        assertEquals(2, movieDao.allMovies().size());
        movieDao.deleteById(movie1.getId());
        assertEquals(1, movieDao.allMovies().size());
    }

    @Test
    public void clearAll_deletesAllMoviesFromDB() {
        Movie movie =setUpMovie();
        movieDao.save(movie);
        Movie movie1 = setUpMovie();
        movieDao.save(movie1);
        assertEquals(2, movieDao.allMovies().size());
        movieDao.clearAll();
        assertEquals(0, movieDao.allMovies().size());
    }

    //helper
    public Movie setUpMovie(){
        return new Movie("Avengers: Infinity War", 2018,"Russo Brothers", 150,"Thanos, Mad Titan","Excellent");
    }
}