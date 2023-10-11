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

    public void btnSaveMovie(View view)
    {
        Movie movie = new Movie("Challenger", "Action", 1.5, 120);
        Intent intent = new Intent();
        intent.putExtra("keyParam", movie);

        setResult(RESULT_OK, intent);
        finish();
    }
}