package ro.ase.csie.dma.s02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String parama1 = extras.getString("param1");

        String param1 = intent.getStringExtra("param1");
        Log.d(TAG, "Parameter 1" + parama1 + " - " + param1);

    }
}