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

    private EditText etTitle;
    private EditText etBudget;
    private Spinner spGenre;
    private SeekBar sbDuration;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initializeControls();

        setControlsBehaviour();
    }

    private void initializeControls() {
        etTitle = findViewById(R.id.etTitle);
        etBudget = findViewById(R.id.etBudget);
        spGenre = findViewById(R.id.spGenre);
        sbDuration = findViewById(R.id.sbDuration);
        btnSave = findViewById(R.id.btnSaveMovie);
    }

    private void setControlsBehaviour() {
//        etTitle.setOnClickListener(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MovieActivity", "onClickWithListener");
                String movieTitle = etTitle.getText().toString();
                Double movieBudget = Double.parseDouble(etBudget.getText().toString());
                Integer movieDuration = sbDuration.getProgress();
                String movieGenre = spGenre.getSelectedItem().toString();

                Movie movie = new Movie(movieTitle, movieGenre, movieDuration, movieBudget);
                Intent intent = new Intent();
                intent.putExtra("movie", movie);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
//        btnSave.setOnClickListener(this);
    }

    public void saveMovie(View view)
    {
        Log.d("MovieActivity", "onClickWithProperty");
    }

    @Override
    public void onClick(View v) {
        Log.d("MovieActivity", "onClickWithImplementedInterface");
        if(v.getId() == R.id.btnSaveMovie)
        {
            Log.d("MovieActivity", "onClickWithImplementedInterfaceFromButton");
        }
        else if (v.getId() == R.id.etTitle)
        {
            Log.d("MovieActivity", "onClickWithImplementedInterfaceFromEditText");
        }
    }
}