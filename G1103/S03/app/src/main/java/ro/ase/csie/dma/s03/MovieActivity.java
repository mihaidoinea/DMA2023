package ro.ase.csie.dma.s03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
    private EditText etPoster;
    Calendar calendar = Calendar.getInstance();
    Movie movie;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initializeControls();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!= null)
        {
             movie = extras.getParcelable("movieKey");
             etTitle.setEnabled(false);
             etTitle.setText(movie.title);
             etRelease.setEnabled(false);
             etRelease.setText(sdf.format(movie.release));
             etBudget.setText(String.valueOf(movie.budget));
             sbDuration.setProgress(movie.duration);
             etPoster.setText(movie.posterUrl);
             rbRating.setRating(movie.rating);
             spGenre.setSelection(movie.genre.ordinal());
        }

    }

    private void initializeControls() {
        btnSave = findViewById(R.id.btnSave);
        etTitle = findViewById(R.id.etTitle);
        etBudget = findViewById(R.id.etBudget);
        sbDuration = findViewById(R.id.sbDuration);
        spGenre = findViewById(R.id.spGenre);
        etRelease = findViewById(R.id.etRelease);
        etPoster = findViewById(R.id.etPoster);

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
                MovieGenre genre = MovieGenre.valueOf(spGenre.getSelectedItem().toString());
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

                String posterUrl = etPoster.getText().toString();

                Movie movie = new Movie(title, genre, budget, duration, rating, recommended, oscar, approval, release, posterUrl);
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