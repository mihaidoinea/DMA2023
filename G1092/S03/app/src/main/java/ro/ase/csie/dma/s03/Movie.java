package ro.ase.csie.dma.s03;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Movie implements Parcelable {
    String movieName;
    String movieGenre;
    Double movieBudget;

    protected Movie(Parcel in) {
        movieName = in.readString();
        movieGenre = in.readString();
        if (in.readByte() == 0) {
            movieBudget = null;
        } else {
            movieBudget = in.readDouble();
        }
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

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public Double getMovieBudget() {
        return movieBudget;
    }

    public void setMovieBudget(Double movieBudget) {
        this.movieBudget = movieBudget;
    }

    public Movie(String movieName, String movieGenre, Double movieBudget) {
        this.movieName = movieName;
        this.movieGenre = movieGenre;
        this.movieBudget = movieBudget;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(movieName);
        dest.writeString(movieGenre);
        if (movieBudget == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(movieBudget);
        }
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieName='" + movieName + '\'' +
                ", movieGenre='" + movieGenre + '\'' +
                ", movieBudget=" + movieBudget +
                '}';
    }
}
