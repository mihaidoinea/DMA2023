package ro.ase.csie.dma.s02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG_ACTIVITY = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String key0 = extras.getString("key0");
        Log.w(TAG_ACTIVITY, "Param: " + key0);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG_ACTIVITY,"onDestroy(2)");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w(TAG_ACTIVITY,"onStart(2)");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG_ACTIVITY,"onStop(2)");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG_ACTIVITY,"onPause(2)");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG_ACTIVITY,"onResume(2)");
    }
}