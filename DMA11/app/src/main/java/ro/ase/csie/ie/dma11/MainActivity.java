package ro.ase.csie.ie.dma11;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button btnGraph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        AnimationDrawable animationDrawable1 = (AnimationDrawable) imageView1.getBackground();
        animationDrawable1.start();

        ImageView imageView2 = (ImageView) findViewById(R.id.imageView3);
        imageView2.setOnClickListener(this);

        btnGraph = (Button) findViewById(R.id.button1);
        btnGraph.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(),"Test",Toast.LENGTH_SHORT).show();
        if(v.getId() == R.id.imageView3)
        {
            Toast.makeText(getApplicationContext(),"Test",Toast.LENGTH_SHORT).show();
            playAlphaAnimation(v);
        }
        if(v.getId() == R.id.button1)
        {
            Intent intent = new Intent(this,DrawingActivity.class);
            startActivity(intent);
        }
    }

    private void playAlphaAnimation(View v) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        v.startAnimation(animation);
    }
}