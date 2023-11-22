package ro.ase.csie.dma.s03;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements IMovieItemEvents {

    private Button btnShow;
    private static final String MAIN_TAG = MainActivity.class.getSimpleName();
    static ArrayList<Movie> movieArrayList = new ArrayList<Movie>();

    private RecyclerView rvMovies;
    ActivityResultLauncher<Intent> activityLauncher;
    private RecyclerView.Adapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovies = findViewById(R.id.rvMovies);
        movieAdapter = new MovieAdapter(this, movieArrayList);
        rvMovies.setAdapter(movieAdapter);
        btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<Movie> movies = MovieAdapter.movieOptions.keySet();
                for(Movie movie: movies)
                {
                    Integer radioButtonId = MovieAdapter.movieOptions.get(movie);
                    if(radioButtonId == R.id.rbDisplay)
                    {
                        Log.d(MAIN_TAG, "Display: " + movie);
                    }
                    if(radioButtonId == R.id.rbExport)
                    {
                        Log.d(MAIN_TAG, "Export: " + movie);
                    }
                }
            }
        });

        activityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        Log.d(MAIN_TAG, "onActivityResult");
                        int resultCode = result.getResultCode();
                        if (resultCode == RESULT_OK) {
                            Intent data = result.getData();
                            Movie movie = data.getParcelableExtra("keyParam");
                            Toast.makeText(MainActivity.this.getApplicationContext(), "Movie: " + movie, Toast.LENGTH_LONG).show();
                            if(!movieArrayList.contains(movie)) {
                                Toast.makeText(getApplicationContext(), "Movie inserted!", Toast.LENGTH_LONG).show();
                                movieArrayList.add(movie);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Movie updated!", Toast.LENGTH_LONG).show();
                                int index = movieArrayList.indexOf(movie);
                                movieArrayList.remove(index);
                                movieArrayList.add(movie);
                            }
                            movieAdapter.notifyDataSetChanged();

                        }
                    }
                });
    }

    public void btnAddMovie(View view)
    {
        Intent intent = new Intent(MainActivity.this, MovieActivity.class);
        //startActivity(intent);
        activityLauncher.launch(intent);
    }

    @Override
    public void onMovieItemClicked(int position) {
        Intent intent = new Intent(this, MovieActivity.class);
        Movie movie = movieArrayList.get(position);
        intent.putExtra("movieKey", movie);
        activityLauncher.launch(intent);
    }
}