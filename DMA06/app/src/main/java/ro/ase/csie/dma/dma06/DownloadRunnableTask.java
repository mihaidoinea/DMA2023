package ro.ase.csie.dma.dma06;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadRunnableTask implements Runnable {
    private final String movieUrl;
    private ImageView ivPoster;

    public DownloadRunnableTask(String movieUrl, ImageView ivRunnable) {
        this.movieUrl = movieUrl;
        this.ivPoster = ivRunnable;
    }

    @Override
    public void run() {
        Bitmap bitmap = null;
        try {
            URL url = new URL(this.movieUrl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);



            ivPoster.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
