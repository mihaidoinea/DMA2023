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

import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MovieActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etTitle;
    private EditText etBudget;
    private Spinner spGenre;
    private SeekBar sbDuration;
    private Button btnSave;
    private EditText etRelease;
    private RatingBar rbRating;
    private Switch swOscar;
    private CheckBox cbRecommended;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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
        rbRating = findViewById(R.id.ratingBar);
        etRelease=findViewById(R.id.etRelease);
        swOscar = findViewById(R.id.swOscar);
        cbRecommended = findViewById(R.id.cbRecommended);
        Calendar calendar = Calendar.getInstance();
        etRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(MovieActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                etRelease.setText(dateFormat.format(calendar.getTime()));
                            }
                        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dpd.show();
            }
        });

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
                try {
                    release = dateFormat.parse(etRelease.getText().toString());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                Float rating = rbRating.getRating();
                Boolean oscar = swOscar.isChecked();
                Boolean recommended = cbRecommended.isChecked();

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