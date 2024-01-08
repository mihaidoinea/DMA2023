package ro.ase.csie.dma.s03;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("select * from movie")
    List<Movie> getMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Movie movie);

    @Delete
    int delete(Movie movie);

}
