package ro.ase.csie.dma.s02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_ACTIVITY = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w(TAG_ACTIVITY,"onCreate()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG_ACTIVITY,"onDestroy()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w(TAG_ACTIVITY,"onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG_ACTIVITY,"onStop()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG_ACTIVITY,"onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG_ACTIVITY,"onResume()");
    }

    public void doSomething(View view)
    {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("key0", "Hello from MainActivity!");

        startActivity(intent);
    }
}