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

    ActivityResultLauncher<Intent> startActivity;
    static ArrayList<Movie> movies = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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