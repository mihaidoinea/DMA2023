package ro.ase.csie.dma.s03;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("select * from movie")
    List<Movie> getMovies();

    @Insert
    long insert(Movie movie);

}
