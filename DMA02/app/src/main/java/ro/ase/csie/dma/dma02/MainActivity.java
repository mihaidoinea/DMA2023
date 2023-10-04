package ro.ase.csie.dma.dma02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int testing = R.layout.testing;
        int appName = R.string.app_name;
        String string = getResources().getString(R.string.app_name);


    }
}