package ro.ase.csie.ie.dma12;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Movie implements Parcelable {

    private String movieId;
    private String title;
    private Genre genre;
    private Date release;
    private Integer duration;
    private String poster;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) && genre == movie.genre && Objects.equals(release, movie.release) && Objects.equals(duration, movie.duration) && Objects.equals(poster, movie.poster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, release, duration, poster);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        title = in.readString();
        release = new Date(in.readLong());
        genre = Genre.valueOf(in.readString());
        if (in.readByte() == 0) {
            duration = null;
        } else {
            duration = in.readInt();
        }
        poster = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeLong(release.getTime());
        dest.writeString(genre.toString());
        if (duration == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(duration);
        }
        dest.writeString(poster);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Movie(String title, Genre genre, Date release, Integer duration) {
        this.title = title;
        this.genre = genre;
        this.release = release;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre=" + genre +
                ", release=" + release +
                ", duration=" + duration +
                ", poster='" + poster + '\'' +
                '}';
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
