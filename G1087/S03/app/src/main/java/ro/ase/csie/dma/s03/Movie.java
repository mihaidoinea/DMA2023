package ro.ase.csie.dma.s03;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;
import java.util.Objects;

public class Movie implements Parcelable {
    String title;
    String genre;
    Integer duration;
    Double budget;
    Float rating;
    Boolean oscarWinner;
    Boolean recommended;
    Date release;
    String posterUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return title.equals(movie.title) && release.equals(movie.release);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, release);
    }

    protected Movie(Parcel in) {
        title = in.readString();
        genre = in.readString();
        if (in.readByte() == 0) {
            duration = null;
        } else {
            duration = in.readInt();
        }
        if (in.readByte() == 0) {
            budget = null;
        } else {
            budget = in.readDouble();
        }
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readFloat();
        }
        byte tmpOscarWinner = in.readByte();
        oscarWinner = tmpOscarWinner == 0 ? null : tmpOscarWinner == 1;
        byte tmpRecommended = in.readByte();
        recommended = tmpRecommended == 0 ? null : tmpRecommended == 1;
        release = new Date(in.readLong());
        posterUrl = in.readString();
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
                ", duration=" + duration +
                ", budget=" + budget +
                ", rating=" + rating +
                ", oscarWinner=" + oscarWinner +
                ", recommended=" + recommended +
                ", release=" + release +
                ", poster=" + posterUrl +
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Boolean getOscarWinner() {
        return oscarWinner;
    }

    public void setOscarWinner(Boolean oscarWinner) {
        this.oscarWinner = oscarWinner;
    }

    public Boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public Movie(String title, String genre, Integer duration, Double budget, Float rating, Boolean oscarWinner, Boolean recommended, Date release, String poster) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.budget = budget;
        this.rating = rating;
        this.oscarWinner = oscarWinner;
        this.recommended = recommended;
        this.release = release;
        this.posterUrl = poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(genre);
        if (duration == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(duration);
        }
        if (budget == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(budget);
        }
        if (rating == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(rating);
        }
        dest.writeByte((byte) (oscarWinner == null ? 0 : oscarWinner ? 1 : 2));
        dest.writeByte((byte) (recommended == null ? 0 : recommended ? 1 : 2));
        dest.writeLong(release.getTime());
        dest.writeString(posterUrl);
    }
}
