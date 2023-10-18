package ro.ase.csie.dma.s03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MovieActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText etTitle;
    private EditText etBudget;
    private Spinner spGenre;
    private SeekBar sbDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initializeControls();
    }

    private void initializeControls() {
        btnSave = findViewById(R.id.btnSave);
        etTitle = findViewById(R.id.etTitle);
        etBudget = findViewById(R.id.etBudget);
        spGenre = findViewById(R.id.spGenre);
        sbDuration = findViewById(R.id.sbDuration);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MovieActivity", "onClickEventWithListener");
                String title = etTitle.getText().toString();
                String genre = spGenre.getSelectedItem().toString();
                Double budget = Double.parseDouble(etBudget.getText().toString());
                Integer duration = sbDuration.getProgress();
                Movie movie = new Movie(title, genre, budget, duration);
                Intent intent = new Intent();
                intent.putExtra("keyParam", movie);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
//        btnSave.setOnClickListener(this);
//        etTitle.setOnClickListener(this);


    }

    public void saveMovieClick(View view)
    {
       Log.d("MovieActivity", "onClickEventWithProperty");
    }

    @Override
    public void onClick(View v) {
        Log.d("MovieActivity", "onClickEventWithActivityInterface");
        if(v.getId() == R.id.btnSave)
        {
            Log.d("MovieActivity", "onClickEventWithActivityInterfaceFromButton");
        }
        else if(v.getId() == R.id.etTitle)
        {
            Log.d("MovieActivity", "onClickEventWithActivityInterfaceFromEditText");
        }
    }
}