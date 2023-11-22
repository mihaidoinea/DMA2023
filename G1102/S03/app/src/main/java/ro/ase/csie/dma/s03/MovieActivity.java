package ro.ase.csie.dma.s03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
    private CheckBox cbOscar;
    private Switch swRecommended;
    private EditText etDate;
    private EditText etPoster;
    private Movie movie;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initializeControls();

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        if(extras != null) {
            movie = extras.getParcelable("keyMovie");
            etTitle.setEnabled(false);
            etDate.setEnabled(false);
            etTitle.setText(movie.title);
            etDate.setText(sdf.format(movie.release));
            etBudget.setText(String.valueOf(movie.budget));
            sbDuration.setProgress(movie.duration);
            swRecommended.setChecked(movie.recommended);
            cbOscar.setChecked(movie.oscarWinner);
            etPoster.setText(movie.posterUrl);
            rbRating.setRating(movie.rating);
            spGenre.setSelection(movie.genre.ordinal());
        }

    }

    private void initializeControls() {
        btnSave = findViewById(R.id.btnSave);
        etTitle = findViewById(R.id.etTitle);
        etBudget = findViewById(R.id.etBudget);
        spGenre = findViewById(R.id.spGenre);
        sbDuration = findViewById(R.id.sbDuration);
        rbRating = findViewById(R.id.rbRating);
        cbOscar = findViewById(R.id.cbOscar);
        swRecommended = findViewById(R.id.swRecommended);
        etDate = findViewById(R.id.etDate);
        etPoster = findViewById(R.id.etPoster);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.YEAR, year);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        etDate.setText(sdf.format(calendar.getTime()));
                    }
                };
                DatePickerDialog dpd = new DatePickerDialog(MovieActivity.this, onDateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get((Calendar.MONTH)),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dpd.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("MovieActivity", "onClickEventWithListener");
                String title = etTitle.getText().toString();
                MovieGenre genre = MovieGenre.valueOf(spGenre.getSelectedItem().toString());
                Double budget = Double.parseDouble(etBudget.getText().toString());
                Integer duration = sbDuration.getProgress();

                Float rating = rbRating.getRating();
                Boolean oscar = cbOscar.isChecked();
                String posterUrl = etPoster.getText().toString();

                String dateString = etDate.getText().toString();


                Date release = null;
                try {
                    release = sdf.parse(dateString);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                Boolean recommended = swRecommended.isChecked();
                AgeLimitEnum approval = null;

                Movie movie = new Movie(title,genre,budget,duration, release, oscar, recommended, rating, approval, posterUrl);

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