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
    float rating;
    boolean recommended;
    boolean oscarWinner;
    ParentalApprovalEnum approval;
    Date release;

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
        rating = in.readFloat();
        recommended = in.readByte() != 0;
        oscarWinner = in.readByte() != 0;
        release = new Date(in.readLong());
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
                ", rating=" + rating +
                ", recommended=" + recommended +
                ", oscarWinner=" + oscarWinner +
                ", approval=" + approval +
                ", release=" + release +
                '}';
    }

    public Movie(String title, String genre, double budget, int duration, float rating, boolean recommended, boolean oscarWinner, ParentalApprovalEnum approval, Date release) {
        this.title = title;
        this.genre = genre;
        this.budget = budget;
        this.duration = duration;
        this.rating = rating;
        this.recommended = recommended;
        this.oscarWinner = oscarWinner;
        this.approval = approval;
        this.release = release;
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
        dest.writeFloat(rating);
        dest.writeByte((byte) (recommended ? 1 : 0));
        dest.writeByte((byte) (oscarWinner ? 1 : 0));
        dest.writeLong(release.getTime());
    }
}
