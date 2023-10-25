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
        sbDuration = findViewById(R.id.sbDuration);
        spGenre = findViewById(R.id.spGenre);

//        etTitle.setOnClickListener(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MovieActivity", "onClickWithListener");
                String title = etTitle.getText().toString();
                Double budget = Double.parseDouble(etBudget.getText().toString());
                String genre = spGenre.getSelectedItem().toString();
                Integer duration = sbDuration.getProgress();

                float rating = 0;
                boolean recommended = false;
                boolean oscar = false;
                ParentalApprovalEnum approval = null;
                Date release = null;

                Movie movie = new Movie(title, genre,budget,duration,rating,recommended,oscar,approval, release);
                Intent intent = new Intent();
                intent.putExtra("keyParam", movie);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
//        btnSave.setOnClickListener(this);
    }


    public void btnSaveMovie(View view)
    {
        Log.d("MovieActivity", "onClickWithProperty");

    }

    @Override
    public void onClick(View v) {
        Log.d("MovieActivity", "onClickWithImplementedInterface");
        if(v.getId() == R.id.btnSave)
        {
            Log.d("MovieActivity", "onClickWithImplementedInterfaceFromButton");
        }
        else if(v.getId() == R.id.etTitle)
        {
            Log.d("MovieActivity", "onClickWithImplementedInterfaceFromEditText");
        }
    }
}