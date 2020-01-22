package dao;

import models.Category;
import models.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oCategoryDaoTest {

    private Sql2oMovieDao movieDao;
    private Sql2oCategoryDao categoryDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        categoryDao = new Sql2oCategoryDao(sql2o);
        movieDao = new Sql2oMovieDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("TEARING DOWN DATABASE!!!");
        conn.close();
    }

    @Test
    public void save_addsCategorySuccessfullyWithId() {
        Category category = setUpCategory();
        categoryDao.save(category);
        int categoryId = category.getId();
        int foundCategory = categoryDao.findById(categoryId).getId();
        assertEquals(categoryId, foundCategory);
        assertEquals(1, categoryDao.allCategories().size());

    }

    @Test
    public void addMovieToCategory() {
        Movie movie = new Movie("aa",3456,"www", 234, "ert", "ay");
        movieDao.save(movie);
        Movie movie1 = new Movie("aa",3456,"www", 234, "ert", "ay");
        movieDao.save(movie1);

        Category category = setUpCategory();
        categoryDao.save(category);

        categoryDao.addMovieToCategory(category, movie);
        categoryDao.addMovieToCategory(category, movie1);
        assertEquals(2, categoryDao.allMoviesInCategory(category.getId()).size());
    }

    @Test
    public void allCategories() {
        Category category = setUpCategory();
        categoryDao.save(category);
        Category category1 = setUpCategory();
        categoryDao.save(category1);
        assertEquals(2, categoryDao.allCategories().size());
    }

    @Test
    public void findById() {
        Category category = setUpCategory();
        categoryDao.save(category);
        int idOfCategory = categoryDao.findById(category.getId()).getId();
        assertEquals(category.getId(), idOfCategory);
    }

//    @Test
//    public void allMoviesInCategory() {
//        Movie movie = new Movie("aa",3456,"www", 234, "ert", "ay");
//        movieDao.save(movie);
//        Movie movie1 = new Movie("a",456,"ww", 214, "erth", "ayy");
//        movieDao.save(movie1);
//
//        Category category = setUpCategory();
//        categoryDao.save(category);
//
//        categoryDao.addMovieToCategory(category, movie);
//        categoryDao.addMovieToCategory(category, movie1);
//
//        Movie [] movies = {movie, movie1};
//        assertEquals(Arrays.asList(movies), categoryDao.allMoviesInCategory(category.getId()));
//    }

    @Test
    public void deleteCategoryById() {
        Category category = setUpCategory();
        categoryDao.save(category);
        assertEquals(1, categoryDao.allCategories().size());
        categoryDao.deleteCategoryById(category.getId());
        assertEquals(0, categoryDao.allCategories().size());
    }

    @Test
    public void clearAll() {
        Category category = setUpCategory();
        categoryDao.save(category);
        Category category1 = setUpCategory();
        categoryDao.save(category1);
        assertEquals(2, categoryDao.allCategories().size());
        categoryDao.clearAll();
        assertEquals(0, categoryDao.allCategories().size());
    }

    //helper
    public Category setUpCategory(){
        return new Category("Comedy", "Laugh your Ass Off");
    }
}