package ro.ase.csie.dma.dma06;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String movieUrl = "https://www.joblo.com/wp-content/uploads/2020/09/enola-review-face.jpg";

    private Button btnAsync;
    private Button btnCallable;
    private Button btnThread;
    private Button btnRunnable;
    private ImageView ivAsync;
    private ImageView ivCallable;
    private ImageView ivRunnable;
    private ImageView ivThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeControls();
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnAsync)
        {
            DownloadAsyncTask dat = new DownloadAsyncTask()
            {
                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    super.onPostExecute(bitmap);
                    ivAsync.setImageBitmap(bitmap);
                }
            };
            dat.execute(movieUrl);
        }
        if(v.getId() == R.id.btnCallable)
        {
            ExecutorService executors = Executors.newFixedThreadPool(3);
            Future<Bitmap> submit = executors.submit(new DownloadCallableTask(movieUrl));
            try {
                ivCallable.setImageBitmap(submit.get());
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if(v.getId() == R.id.btnRunnable)
        {
            DownloadRunnableTask drt = new DownloadRunnableTask(movieUrl, ivRunnable);
            Thread download = new Thread(drt);
            download.start();
        }
    }

    private void initializeControls() {
        btnAsync = findViewById(R.id.btnAsync);
        btnRunnable = findViewById(R.id.btnRunnable);
        btnCallable = findViewById(R.id.btnCallable);
        btnThread = findViewById(R.id.btnThreads);
        btnAsync.setOnClickListener(this);
        btnRunnable.setOnClickListener(this);
        btnCallable.setOnClickListener(this);
        btnThread.setOnClickListener(this);
        ivAsync = findViewById(R.id.ivAsync);
        ivCallable = findViewById(R.id.ivCallable);
        ivThread = findViewById(R.id.ivThread);
        ivRunnable = findViewById(R.id.ivRunnable);
    }
}