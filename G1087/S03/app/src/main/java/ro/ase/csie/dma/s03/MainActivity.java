package ro.ase.csie.dma.s03;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    static ArrayList<Movie> movieArrayList = new ArrayList<>();

    ActivityResultLauncher<Intent> startActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}