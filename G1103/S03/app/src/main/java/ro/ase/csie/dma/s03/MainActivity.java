package ro.ase.csie.dma.s03;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnShow;
    private static final String MAIN_TAG = MainActivity.class.getSimpleName();
    static ArrayList<Movie> movieArrayList = new ArrayList<Movie>();

    ActivityResultLauncher<Intent> activityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Movie movie: movieArrayList)
                {
                    Log.d(MAIN_TAG, "Movie: " + movie);
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
}