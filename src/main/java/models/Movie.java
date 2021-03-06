package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Movie implements Comparable<Movie> {
    private String title;
    private int releaseYear;
    private String writer;
    private int duration;
    private String summary;
    private String review;
    private long reviewTime;
    private String formattedReviewTime;
    private int id;

    public Movie(String title, int releaseYear, String writer, int duration, String summary, String review){
        this.title = title;
        this.releaseYear = releaseYear;
        this.writer = writer;
        this.duration = duration;
        this.summary = summary;
        this.review = review;
        //this.reviewTime = System.currentTimeMillis();
        //setFormattedReviewTime();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return releaseYear == movie.releaseYear &&
                duration == movie.duration &&
                reviewTime == movie.reviewTime &&
                id == movie.id &&
                Objects.equals(title, movie.title) &&
                Objects.equals(writer, movie.writer) &&
                Objects.equals(summary, movie.summary) &&
                Objects.equals(review, movie.review) &&
                Objects.equals(formattedReviewTime, movie.formattedReviewTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseYear, writer, duration, summary, review, reviewTime, formattedReviewTime, id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public long getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(long reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getFormattedReviewTime() {
        Date date = new Date(reviewTime);
        String formattedDate = "MM/dd/yyyy @ K:mm a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formattedDate);
        return simpleDateFormat.format(date);

    }

    public void setFormattedReviewTime() {
        Date date = new Date(this.reviewTime);
        String formattedDate = "MM/dd/yyyy @ K:mm a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formattedDate);
        this.formattedReviewTime = simpleDateFormat.format(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Movie movieObject){
        if (this.reviewTime < movieObject.reviewTime)
        {
            return -1;
        }
        else if (this.reviewTime > movieObject.reviewTime){
            return 1;
        }
        else {
            return 0;
        }
    }
}
