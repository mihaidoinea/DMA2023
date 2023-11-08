package ro.ase.csie.dma.dma06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String movieUrl = "https://www.joblo.com/wp-content/uploads/2020/09/enola-review-face.jpg";
    private static final String RECIPE_GET_JSON = "https://jsonkeeper.com/b/OCIE";
    private static final String RECIPE_POST_JSON = "https://webhook.site/b300cd9b-886c-4361-8f41-1ef8c2882624";
    private Button btnAsync;
    private Button btnCallable;
    private Button btnThread;
    private Button btnRunnable;
    private Button btnGetJSON;
    private Button btnPostJSON;
    private ImageView ivAsync;
    private ImageView ivCallable;
    private ImageView ivRunnable;
    private ImageView ivThread;

    public static int BITMAP_CODE = 1122;
    private static final String TAG = MainActivity.class.getName();
    private List<Recipe> recipes= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeControls();
        trustEveryone();
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
        if(v.getId() == R.id.btnThreads)
        {
            Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message message) {
//                    int receivedBitmapCode = 1122;
                    if(message.what == BITMAP_CODE)
                    {
                        ivThread.setImageBitmap((Bitmap) message.obj);
                        return  true;
                    }
                    else
                        return false;
                }
            });
            DownloadRunnableHandlerTask drht = new DownloadRunnableHandlerTask(movieUrl, handler);
            Thread download = new Thread(drht);
            download.start();
        }

    }

    public void getJson(View view)
    {
        Thread thread= new Thread()
        {
            @Override
            public void run() {
                HttpConnectionService httpConnectionService = new HttpConnectionService(RECIPE_GET_JSON);
                String recipeJSONArray = httpConnectionService.getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //1.parsing the json result to a list of Recipe objects
                        List<Recipe> results = RecipeJsonParser.fromJson(recipeJSONArray);
                        if(!recipes.containsAll(results))
                            recipes.addAll(results);
                        for(Recipe recipe: recipes)
                        {
                            Log.d(TAG, "Recipe:" + recipe);
                        }
                    }
                });
            }
        };
        thread.start();
    }

    public void postJson(View view)
    {
        Thread thread= new Thread()
        {
            @Override
            public void run() {
                HttpConnectionService httpConnectionService = new HttpConnectionService(RECIPE_POST_JSON);
                String jsonArray = RecipeJsonParser.toJson(recipes);
                String value = httpConnectionService.postData(jsonArray);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, value);
                    }
                });
            }
        };
        thread.start();
    }



    private void initializeControls() {
        btnAsync = findViewById(R.id.btnAsync);
        btnRunnable = findViewById(R.id.btnRunnable);
        btnCallable = findViewById(R.id.btnCallable);
        btnThread = findViewById(R.id.btnThreads);
        btnGetJSON = findViewById(R.id.btnThreads);
        btnPostJSON = findViewById(R.id.btnThreads);
        btnAsync.setOnClickListener(this);
        btnRunnable.setOnClickListener(this);
        btnCallable.setOnClickListener(this);
        btnThread.setOnClickListener(this);
        btnGetJSON.setOnClickListener(this);
        btnPostJSON.setOnClickListener(this);
        ivAsync = findViewById(R.id.ivAsync);
        ivCallable = findViewById(R.id.ivCallable);
        ivThread = findViewById(R.id.ivThread);
        ivRunnable = findViewById(R.id.ivRunnable);
    }


    private void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }});
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager(){
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }}}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }

    public void getRest(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.jsonkeeper.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RecipeService recipeService = retrofit.create(RecipeService.class);

        Call<List<Recipe>> recipesCall = recipeService.getRecipes();
        recipesCall.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> results = response.body();
                if(!recipes.containsAll(results))
                    recipes.addAll(results);
                for(Recipe recipe: recipes)
                {
                    Log.d(TAG, "Recipe:" + recipe);
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

            }
        });
    }
}