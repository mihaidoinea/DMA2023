package ro.ase.csie.dma.s03;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IMovieItemEvents {

    private static final String TAG = MainActivity.class.getSimpleName();
    static ArrayList<Movie> movieArrayList = new ArrayList<>();

    private RecyclerView rvMovies;

    ActivityResultLauncher<Intent> startActivity;
    private RecyclerView.Adapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovies = findViewById(R.id.rvMovies);

        movieAdapter = new MovieAdapter(this, movieArrayList);
        rvMovies.setAdapter(movieAdapter);
        rvMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "View: " +v.getId());
            }
        });

        startActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Log.d(TAG, "onActivityResult");
                        int resultCode = result.getResultCode();
                        if(resultCode == RESULT_OK) {
                            Intent data = result.getData();
                            Bundle extras = data.getExtras();
                            Movie movie = extras.getParcelable("movie");
                            if (movieArrayList.contains(movie)) {
                                int index = movieArrayList.indexOf(movie);
                                movieArrayList.remove(index);
                                movieArrayList.add(movie);
                                Toast.makeText(getApplicationContext(), "Movie updated!", Toast.LENGTH_LONG).show();
                            } else {
                                movieArrayList.add(movie);
                                Toast.makeText(getApplicationContext(), "Movie added: " + movie, Toast.LENGTH_LONG).show();
                            }
                            int index = movieArrayList.indexOf(movie);
                            movieAdapter.notifyItemChanged(index);
                        }
                    }
                });
    }

    public void addMovie(View view)
    {
        Intent intent = new Intent(MainActivity.this,MovieActivity.class);
//        startActivity(intent);
        startActivity.launch(intent);
    }

    public void showMovies(View view) {
        for(Movie movie: movieArrayList)
        {
            Log.d(TAG, "Movie: " + movie);
        }
    }

    @Override
    public void onClickedMovie(int position) {
        Movie movie = movieArrayList.get(position);
        Log.d("MainActivity", "Movie: " + movie);
        Intent intent = new Intent(MainActivity.this,MovieActivity.class);
        intent.putExtra("movieKey", movie);
        startActivity.launch(intent);
    }
}