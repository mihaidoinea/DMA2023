package ro.ase.csie.dma.s03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MovieActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etTitle;
    private EditText etBudget;
    private Spinner spGenre;
    private SeekBar sbDuration;
    private Button btnSave;
    private RatingBar rbRating;
    private CheckBox cbRecommended;
    private Switch swOscar;
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
        setControlsBehaviour();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null)
        {
            movie = extras.getParcelable("movieKey");
            etTitle.setText(movie.title);
            etTitle.setEnabled(false);
            etRelease.setEnabled(false);
            etRelease.setText(sdf.format(movie.release));
            sbDuration.setProgress(movie.duration);
            rbRating.setRating(movie.rating);
            cbRecommended.setChecked(movie.recommended);
            swOscar.setChecked(movie.oscarWinner);
            etPoster.setText(movie.posterUrl);
            spGenre.setSelection(movie.genre.ordinal());
            etBudget.setText(movie.budget.toString());
        }

    }

    private void initializeControls() {
        etTitle = findViewById(R.id.etTitle);
        etBudget = findViewById(R.id.etBudget);
        spGenre = findViewById(R.id.spGenre);
        sbDuration = findViewById(R.id.sbDuration);
        btnSave = findViewById(R.id.btnSaveMovie);
        rbRating = findViewById(R.id.ratingBar);
        cbRecommended  = findViewById(R.id.checkBox);
        swOscar = findViewById(R.id.switch1);
        etRelease = findViewById(R.id.etRelease);
        etPoster = findViewById(R.id.etPoster);

        etRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        etRelease.setText(sdf.format(calendar.getTime()));
                    }
                };
                DatePickerDialog dpd = new DatePickerDialog(MovieActivity.this, dateSetListener,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dpd.show();
            }
        });
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
                MovieGenre movieGenre = MovieGenre.valueOf(spGenre.getSelectedItem().toString());
                Float rating = rbRating.getRating();
                Boolean oscarWinner = swOscar.isChecked();
                Boolean recommended = cbRecommended.isChecked();
                Date release = movie != null ? movie.release: calendar.getTime();
                String poster = etPoster.getText().toString();
                movie = new Movie(movieTitle,movieGenre,movieDuration, movieBudget, rating, oscarWinner,recommended, release, poster);
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