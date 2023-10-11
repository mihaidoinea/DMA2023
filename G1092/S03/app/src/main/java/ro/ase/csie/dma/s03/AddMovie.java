package ro.ase.csie.dma.s03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
    }

    public void saveMovie(View view)
    {
        Movie movie = new Movie("PeterPan", "Action", 15000.0);
        Intent intent = new Intent();
        intent.putExtra("movie",movie);
        setResult(RESULT_OK, intent);
        finish();
    }
}