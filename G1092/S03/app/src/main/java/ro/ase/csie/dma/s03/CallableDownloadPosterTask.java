package ro.ase.csie.dma.s03;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

public class CallableDownloadPosterTask implements Callable<Bitmap> {
    private String imageUrl;
    public CallableDownloadPosterTask(String posterUrl) {
        this.imageUrl = posterUrl;
    }

    @Override
    public Bitmap call() throws Exception {
        Bitmap bitmap = null;
        URL taskUrl = new URL(imageUrl);
        URLConnection urlConnection = taskUrl.openConnection();
        urlConnection.connect();
        InputStream inputStream = urlConnection.getInputStream();
        bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }
}
