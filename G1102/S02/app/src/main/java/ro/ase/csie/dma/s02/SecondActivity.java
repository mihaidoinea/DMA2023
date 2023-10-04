package ro.ase.csie.dma.s02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        int appName = R.string.app_name;
        String string = getResources().getString(R.string.app_name);
    }
}