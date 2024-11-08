package com.example.filmoteca_lucascandela;

public class Film {

    public final static int FORMAT_DVD = 0;
    public final static int FORMAT_BLURAY = 1;
    public final static int FORMAT_DIGITAL = 2;

    public final static int GENRE_ACTION = 0;
    public final static int GENRE_COMEDY = 1;
    public final static int GENRE_DRAMA = 2;
    public final static int GENRE_SCIFI = 3;
    public final static int GENRE_HORROR = 4;

    public int imageResId;
    public String title;
    public String director;
    public int year;
    public int genre;
    public int format;
    public String imdbUrl;
    public String comments;

    public Film(int imageResId, String title,String director, int year, int genre, int format, String imdbUrl, String comments){
        this.imageResId = imageResId;
        this.title = title;
        this.director = director;
        this.year = year;
        this.genre = genre;
        this.format = format;
        this.imdbUrl = imdbUrl;
        this.comments = comments;
    }

    public Film(String title, String director, int imageResId){

    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public String getImdbUrl() {
        return imdbUrl;
    }

    public void setImdbUrl(String imdbUrl) {
        this.imdbUrl = imdbUrl;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
