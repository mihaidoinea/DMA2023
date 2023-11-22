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

public class MainActivity extends AppCompatActivity implements IMovieItemEvents {

    private static final String TAG = MainActivity.class.getSimpleName();
    static ArrayList<Movie> movieArrayList = new ArrayList<>();

    ActivityResultLauncher<Intent> activityLauncher;

    private RecyclerView rvMovies;

    private Button btnShow;
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
                for(Movie movie: movieArrayList)
                {
                    Log.d(TAG, movie.toString());
                }
            }
        });

        activityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int resultCode = result.getResultCode();
                        if(resultCode == RESULT_OK)
                        {
                            Intent data = result.getData();
                            Movie movie = data.getParcelableExtra("keyParam");
                            Log.d(TAG, "Parameter: " + movie);
                            if(!movieArrayList.contains(movie))
                                movieArrayList.add(movie);
                            else
                            {
                                int index = movieArrayList.indexOf(movie);
                                movieArrayList.remove(index);
                                movieArrayList.add(movie);
                            }
                            movieAdapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(), "Saved movie:" + movie, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void addMovieClick(View view)
    {
        Intent intent = new Intent(MainActivity.this, MovieActivity.class);
        //startActivity(intent);
        activityLauncher.launch(intent);
    }

    @Override
    public void onMovieItemClicked(int position) {
        Intent intent = new Intent(MainActivity.this, MovieActivity.class);
        Movie movie = movieArrayList.get(position);
        intent.putExtra("keyMovie", movie);
        activityLauncher.launch(intent);
    }
}