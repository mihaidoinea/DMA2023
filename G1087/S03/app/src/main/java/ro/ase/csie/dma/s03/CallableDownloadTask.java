package ro.ase.csie.dma.s03;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

public class CallableDownloadTask implements Callable<Bitmap> {

    private String posterUrl;
    public CallableDownloadTask(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Override
    public Bitmap call() throws Exception {
        Bitmap bitmap = null;
        URL imageUrl = new URL(posterUrl);
        URLConnection urlConnection = imageUrl.openConnection();
        urlConnection.connect();
        InputStream inputStream = urlConnection.getInputStream();
        bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }
}
