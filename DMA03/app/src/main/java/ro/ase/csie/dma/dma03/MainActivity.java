package ro.ase.csie.dma.dma03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_TAG = MainActivity.class.getSimpleName();

    private Button btnAddNew;
    private ViewGroup mainLayout;
    private RadioGroup radioGroup;
    private Switch aSwitch;

    private SeekBar seekBar;

    private RatingBar ratingBar;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w(MAIN_TAG, "onCreate");

        initializeControls();

    }
    static int index = 0;

    private void initializeControls() {
        mainLayout = findViewById(R.id.mainLayout);
        btnAddNew = findViewById(R.id.btnAddNew);
        radioGroup = findViewById(R.id.radioGroup);
        aSwitch = findViewById(R.id.switch1);

        seekBar = findViewById(R.id.seekBar);
        ratingBar = findViewById(R.id.ratingBar);
        spinner = findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(MAIN_TAG, "Selected:" + parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.d(MAIN_TAG, "Rating: " + rating);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(MAIN_TAG, "Progress:" +progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(MAIN_TAG, "Checked: " + isChecked);
            }
        });



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rbTrue)
                    Log.d(MAIN_TAG, "True");
                else if(checkedId == R.id.rbFalse)
                    Log.d(MAIN_TAG, "False");
            }
        });

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button newButton= new Button(MainActivity.this);
                newButton.setText("Button_" + index++);
                mainLayout.addView(newButton);

            }
        });
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        byte key = savedInstanceState.getByte("key");
        Log.w(MAIN_TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putByte("key", (byte)21);
        Log.w(MAIN_TAG, "onSaveInstanceState");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w(MAIN_TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(MAIN_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(MAIN_TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(MAIN_TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(MAIN_TAG, "onResume");
    }
}