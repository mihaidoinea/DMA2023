package ro.ase.csie.dma.s03;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

public class DownloadCallablePoster implements Callable<Bitmap> {

    private String posterUrl;

    public DownloadCallablePoster(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Override
    public Bitmap call() throws Exception {
        URL url = new URL(posterUrl);
        URLConnection httpURLConnection = url.openConnection();
        httpURLConnection.connect();
        InputStream inputStream = httpURLConnection.getInputStream();
        return BitmapFactory.decodeStream(inputStream);
    }
}
