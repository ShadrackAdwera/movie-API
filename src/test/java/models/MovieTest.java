package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest {

    @Test
    public void testEquals_returnBothInstancesTrue() {
        Movie movie = setUpMovie();
        Movie movie1 = setUpMovie();
        assertEquals(movie, movie1);
    }

    @Test
    public void getTitle_savesMovieTitleCorrectly() {
        Movie movie = setUpMovie();
        assertEquals("Avengers: Infinity War", movie.getTitle());
    }

    @Test
    public void setTitle_changesMovieTitleSuccessfully() {
        Movie movie = setUpMovie();
        movie.setTitle("Inception");
        assertEquals("Inception", movie.getTitle());
    }

    @Test
    public void getReleaseYear_savesReleaseYearSuccessfully() {
        Movie movie = setUpMovie();
        assertEquals(2018, movie.getReleaseYear());
    }

    @Test
    public void setReleaseYear_changesReleaseYearSuccessfully() {
        Movie movie = setUpMovie();
        movie.setReleaseYear(2019);
        assertEquals(2019, movie.getReleaseYear());
    }

    @Test
    public void getWriter_savesWriterSuccessfully() {
        Movie movie = setUpMovie();
        assertEquals("Russo Brothers", movie.getWriter());
    }

    @Test
    public void setWriter_changesWriterSuccessfully() {
        Movie movie = setUpMovie();
        movie.setWriter("Mario Brothers");
        assertEquals("Mario Brothers", movie.getWriter());

    }

    @Test
    public void getDuration_savesDurationSuccessfully() {
        Movie movie = setUpMovie();
        assertEquals(150, movie.getDuration());
    }

    @Test
    public void setDuration_changesDurationSuccessfully() {
        Movie movie = setUpMovie();
        movie.setDuration(270);
        assertEquals(270, movie.getDuration());
    }

    @Test
    public void getSummary_savesSummarySuccessfully() {
        Movie movie = setUpMovie();
        assertEquals("Thanos, Mad Titan", movie.getSummary());

    }

    @Test
    public void setSummary_changesSummarySuccessfully() {
        Movie movie = setUpMovie();
        movie.setSummary("BRO!!!");
        assertEquals("BRO!!!", movie.getSummary());
    }

    @Test
    public void getReview_savesReviewsSuccessfully() {
        Movie movie = setUpMovie();
        assertEquals("Excellent", movie.getReview());

    }

    @Test
    public void setReview_changesReviewSuccessfully() {
        Movie movie = setUpMovie();
        movie.setReview("Mui Calientez");
        assertEquals("Mui Calientez", movie.getReview());
    }

    @Test
    public void setId_changesIdSuccessfully(){
        Movie movie = setUpMovie();
        movie.setId(2);
        assertEquals(2, movie.getId());
    }

    //helpers
    public Movie setUpMovie(){
        return new Movie("Avengers: Infinity War", 2018,"Russo Brothers", 150,"Thanos, Mad Titan","Excellent",1);
    }
}