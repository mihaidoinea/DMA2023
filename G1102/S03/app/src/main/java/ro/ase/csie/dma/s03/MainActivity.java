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
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    static ArrayList<Movie> movieArrayList = new ArrayList<>();

    ActivityResultLauncher<Intent> activityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}