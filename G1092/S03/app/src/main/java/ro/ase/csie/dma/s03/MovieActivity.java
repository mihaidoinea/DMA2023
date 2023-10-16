package ro.ase.csie.dma.s03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MovieActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etBudget;
    private Spinner spGenre;
    private SeekBar sbDuration;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        initializeControls();
    }

    private void initializeControls() {
        etTitle = findViewById(R.id.etTitle);
        etBudget = findViewById(R.id.etBudget);
        spGenre = findViewById(R.id.spGenre);
        sbDuration = findViewById(R.id.sbDuration);
        btnSave = findViewById(R.id.btnSave);
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