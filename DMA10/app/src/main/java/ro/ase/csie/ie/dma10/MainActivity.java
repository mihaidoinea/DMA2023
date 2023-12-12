package ro.ase.csie.ie.dma10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private DatabaseManager databaseManager;
    private MovieDao movieDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseManager = DatabaseManager.getInstance(this);
        movieDao = databaseManager.getMovieDao();

        ArrayList<Movie> movies = new ArrayList<Movie>(Arrays.asList(
                new Movie("Reality", "Drama", 1200000.0 ),
                new Movie("Manifest", "Action", 1200000.0),
                new Movie("Enola", "Adventure", 1200000.0),
                new Movie("Extraction", "Action", 1200000.0)));

        for (Movie m:movies) {
            movieDao.insert(m);
        }

        movieDao.delete(movies.get(0));

        movieDao.deleteMovieById(3);

        List<Movie> all = movieDao.getAll();
        for(Movie m:all)
            Log.d(TAG, m.toString());

    }
}