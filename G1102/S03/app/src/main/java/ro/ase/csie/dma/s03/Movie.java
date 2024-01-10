package ro.ase.csie.dma.s03;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Movie implements Parcelable {

    String title;
    MovieGenre genre;
    double budget;
    int duration;
    Date release;
    Boolean oscarWinner;
    Boolean recommended;
    float rating;
    AgeLimitEnum ageLimit;
    String posterUrl;

    List<Cinema> cinemas;

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
        genre = MovieGenre.valueOf(in.readString());
        budget = in.readDouble();
        duration = in.readInt();
        byte tmpOscarWinner = in.readByte();
        oscarWinner = tmpOscarWinner == 0 ? null : tmpOscarWinner == 1;
        byte tmpRecommended = in.readByte();
        recommended = tmpRecommended == 0 ? null : tmpRecommended == 1;
        rating = in.readFloat();
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
                ", budget=" + budget +
                ", duration=" + duration +
                ", release=" + release +
                ", oscarWinner=" + oscarWinner +
                ", recommended=" + recommended +
                ", rating=" + rating +
                ", ageLimit=" + ageLimit +
                ", posterUrl=" + posterUrl +
                '}';
    }

    public Movie(String title, MovieGenre genre, double budget,
                 int duration, Date release, Boolean oscarWinner,
                 Boolean recommended, float rating,
                 AgeLimitEnum ageLimit, String posterUrl) {
        this.title = title;
        this.genre = genre;
        this.budget = budget;
        this.duration = duration;
        this.release = release;
        this.oscarWinner = oscarWinner;
        this.recommended = recommended;
        this.rating = rating;
        this.ageLimit = ageLimit;
        this.posterUrl = posterUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(genre.toString());
        dest.writeDouble(budget);
        dest.writeInt(duration);
        dest.writeByte((byte) (oscarWinner == null ? 0 : oscarWinner ? 1 : 2));
        dest.writeByte((byte) (recommended == null ? 0 : recommended ? 1 : 2));
        dest.writeFloat(rating);
        dest.writeLong(release.getTime());
        dest.writeString(posterUrl);
    }

    class Cinema
    {
        private String name;
        private float latitude;
        private float longitude;

        public Cinema(String name, float latitude, float longitude) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getLatitude() {
            return latitude;
        }

        public void setLatitude(float latitude) {
            this.latitude = latitude;
        }

        public float getLongitude() {
            return longitude;
        }

        public void setLongitude(float longitude) {
            this.longitude = longitude;
        }
    }
}
