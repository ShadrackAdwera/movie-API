package dao;

import models.Category;
import models.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oMovieDaoTest {

    private Sql2oMovieDao movieDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        movieDao = new Sql2oMovieDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void save_savesMovieSuccessfullyInTheDatabaseWithID() {
        Movie movie = setUpMovie();
        movieDao.save(movie);
        int movieId = movie.getId();
        int idOfFoundMovie = movieDao.findById(movieId).getId();
        assertEquals(movieId, idOfFoundMovie);

    }

//    @Test
//    public void addCategoryToMovie() {
//        Movie movie = setUpMovie();
//        movieDao.save(movie);
//        Category category = new Category("Horror", "Haunted House");
//
//    }
//
//    @Test
//    public void getAllCategoriesInAMovie() {
//    }

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