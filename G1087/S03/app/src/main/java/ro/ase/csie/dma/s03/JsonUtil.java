package ro.ase.csie.dma.s03;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class JsonUtil {

    public static String getJsonFromResources(Context context, int resId)
    {
        String result = null;
        try(InputStream is = context.getResources().openRawResource(resId))
        {
            result = new BufferedReader(new InputStreamReader(is))
                    .lines()
                    .parallel()
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static ArrayList<Movie> parseJsonContent(String jsonContent)
    {
        ArrayList<Movie> result = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonContent);
            result = new ArrayList<>();
            for(int index=0; index<jsonArray.length(); index++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                Movie movie = readJsonMovie(jsonObject);
                result.add(movie);
            }
        } catch (JSONException | ParseException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static Movie readJsonMovie(JSONObject jsonObject) throws JSONException, ParseException {
        String title = jsonObject.getString("title");
        String genre = jsonObject.getString("genre");
        MovieGenre mGenre = MovieGenre.valueOf(genre);
        String release = jsonObject.getString("release");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date mRelease = sdf.parse(release);
        Integer duration = jsonObject.getInt("duration");
        Double budget = jsonObject.getDouble("budget");
        Float rating = (float) jsonObject.getDouble("rating");
        Boolean oscar = jsonObject.getBoolean("oscarWinner");
        String poster = jsonObject.getString("posterUrl");
        return new Movie(title, mGenre,duration,budget,rating,oscar, true,mRelease,poster);
    }
}
