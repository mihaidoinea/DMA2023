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

import java.util.Date;

public class MovieActivity extends AppCompatActivity implements View.OnClickListener {

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

//        btnSave.setOnClickListener(new ClassOnClick());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MovieTag", "onClickWithListener");
                String title = etTitle.getText().toString();
                Double budget = Double.valueOf(etBudget.getText().toString());
                String genre = spGenre.getSelectedItem().toString();
                Integer duration = sbDuration.getProgress();

                Date release = null;
                Float rating = null;
                Boolean oscar = null;
                Boolean recommended = null;

                Movie movie = new Movie(title, genre, budget, duration, release,rating, oscar, recommended);
                Intent intent = new Intent();
                intent.putExtra("movie",movie);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
       // btnSave.setOnClickListener(this);
    }

    //used only for XML onClick property
    public void saveMovie(View view)
    {
        Log.d("MovieTag", "onClickWithProperty");
    }

    //used only by the interface implemented at class level
    @Override
    public void onClick(View v) {
        Log.d("MovieTag", "onClickWithClassInterface");
    }
}