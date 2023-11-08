package ro.ase.csie.dma.s03;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

public class DownloadCallableTask implements Callable<Bitmap> {
    String movieUrl;
    public DownloadCallableTask(String movieUrl) {
        this.movieUrl = movieUrl;
    }

    @Override
    public Bitmap call() throws Exception {
        Bitmap bitmap = null;
        try {
            URL url = new URL(this.movieUrl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bitmap;
    }
}
