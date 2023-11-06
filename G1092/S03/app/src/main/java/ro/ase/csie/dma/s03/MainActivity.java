package ro.ase.csie.dma.s03;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> startActivity;
    static ArrayList<Movie> movies = new ArrayList<>();

    private RecyclerView rvMovies;
    private RecyclerView.Adapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovies = findViewById(R.id.recyclerView);
        movieAdapter = new MovieAdapter(this, movies);
        rvMovies.setAdapter(movieAdapter);

        startActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                   //manage activity response coming from other activities
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int resultCode = result.getResultCode();
                        if (resultCode == RESULT_OK) {
                            Intent data = result.getData();
                            Movie movie = data.getParcelableExtra("movie");

                            if(!movies.contains(movie))
                                movies.add(movie);
                            else
                            {
                                int index = movies.indexOf(movie);
                                movies.remove(index);
                                movies.add(movie);
                            }

                            int indexOf = movies.indexOf(movie);
                            movieAdapter.notifyItemChanged(indexOf);

                            //Snackbar.make(getApplicationContext(),  ,"Movie: " + movie, Snackbar.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), "Movie:" + movie, Toast.LENGTH_LONG).show();
                            Log.d("MainActivity", "onActivityResult");
                        }
                    }
                });
    }

    public void addMovie(View view)
    {
        Intent intent = new Intent(MainActivity.this, MovieActivity.class);
//        startActivity(intent);
        startActivity.launch(intent);
    }
}