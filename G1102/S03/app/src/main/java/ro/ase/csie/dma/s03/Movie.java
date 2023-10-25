package ro.ase.csie.dma.s03;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.Objects;

public class Movie implements Parcelable {

    String title;
    String genre;
    double budget;
    int duration;
    Date release;
    Boolean oscarWinner;
    Boolean recommended;
    float rating;
    AgeLimitEnum ageLimit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) && Objects.equals(release, movie.release);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, release);
    }

    protected Movie(Parcel in) {
        title = in.readString();
        genre = in.readString();
        budget = in.readDouble();
        duration = in.readInt();
        byte tmpOscarWinner = in.readByte();
        oscarWinner = tmpOscarWinner == 0 ? null : tmpOscarWinner == 1;
        byte tmpRecommended = in.readByte();
        recommended = tmpRecommended == 0 ? null : tmpRecommended == 1;
        rating = in.readFloat();
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
                ", release=" + release +
                ", oscarWinner=" + oscarWinner +
                ", recommended=" + recommended +
                ", rating=" + rating +
                ", ageLimit=" + ageLimit +
                '}';
    }

    public Movie(String title, String genre, double budget, int duration, Date release, Boolean oscarWinner, Boolean recommended, float rating, AgeLimitEnum ageLimit) {
        this.title = title;
        this.genre = genre;
        this.budget = budget;
        this.duration = duration;
        this.release = release;
        this.oscarWinner = oscarWinner;
        this.recommended = recommended;
        this.rating = rating;
        this.ageLimit = ageLimit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(genre);
        dest.writeDouble(budget);
        dest.writeInt(duration);
        dest.writeByte((byte) (oscarWinner == null ? 0 : oscarWinner ? 1 : 2));
        dest.writeByte((byte) (recommended == null ? 0 : recommended ? 1 : 2));
        dest.writeFloat(rating);
    }
}
