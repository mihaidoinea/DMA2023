package ro.ase.csie.dma.s03;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Movie implements Parcelable {

    String title;
    String genre;
    double budget;
    int duration;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return title.equals(movie.title) && genre.equals(movie.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre);
    }

    protected Movie(Parcel in) {
        title = in.readString();
        genre = in.readString();
        budget = in.readDouble();
        duration = in.readInt();
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(genre);
        dest.writeDouble(budget);
        dest.writeInt(duration);
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

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", budget=" + budget +
                ", duration=" + duration +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Movie(String title, String genre, double budget, int duration) {
        this.title = title;
        this.genre = genre;
        this.budget = budget;
        this.duration = duration;
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
