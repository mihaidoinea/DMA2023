package ro.ase.csie.dma.s03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
    }

    public void saveMovie(View view)
    {
        Movie movie = new Movie("Challenger", "Action", 120, 1.5);
        Intent intent = new Intent();
        intent.putExtra("movie", movie);
        setResult(RESULT_OK, intent);
        finish();
    }
}