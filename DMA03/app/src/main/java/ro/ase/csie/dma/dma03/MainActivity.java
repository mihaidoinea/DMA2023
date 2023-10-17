package ro.ase.csie.dma.dma03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w(MAIN_TAG, "onCreate");
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