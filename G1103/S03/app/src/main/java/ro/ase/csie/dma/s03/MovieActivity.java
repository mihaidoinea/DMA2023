package ro.ase.csie.dma.s03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MovieActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText etTitle;
    private EditText etBudget;
    private Spinner spGenre;
    private SeekBar sbDuration;
    private RatingBar rbRating;
    private EditText etRelease;
    Calendar calendar = Calendar.getInstance();

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
        etRelease = findViewById(R.id.etRelease);
        etRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        Date release = calendar.getTime();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        etRelease.setText(sdf.format(release));
                    }
                };
                DatePickerDialog dpd = new DatePickerDialog(MovieActivity.this, onDateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dpd.show();
            }
        });
        rbRating = findViewById(R.id.rbRating);

//        etTitle.setOnClickListener(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MovieActivity", "onClickWithListener");
                String title = etTitle.getText().toString();
                Double budget = Double.parseDouble(etBudget.getText().toString());
                String genre = spGenre.getSelectedItem().toString();
                Integer duration = sbDuration.getProgress();

                float rating = rbRating.getRating();
                boolean recommended = false;
                boolean oscar = false;
                ParentalApprovalEnum approval = null;
                String date = etRelease.getText().toString();
                Date release = null;
                try {
                    release = new SimpleDateFormat("dd-MM-yyyy").parse(date);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                Movie movie = new Movie(title, genre, budget, duration, rating, recommended, oscar, approval, release);
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