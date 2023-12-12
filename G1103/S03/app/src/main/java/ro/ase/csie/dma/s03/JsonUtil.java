package ro.ase.csie.dma.s03;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
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

    public static ArrayList<Movie> readJsonMovies(String jsonContent)
    {
        ArrayList<Movie> result = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonContent);
            result = new ArrayList<>();
            for(int index=0; index<jsonArray.length(); index++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                Movie movie = parseJsonMovie(jsonObject);
                result.add(movie);
            }
        } catch (JSONException | ParseException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static Movie parseJsonMovie(JSONObject jsonObject) throws JSONException, ParseException {
        String title = jsonObject.getString("title");
        String release = jsonObject.getString("release");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date mRelease = sdf.parse(release);
        String genre = jsonObject.getString("genre");
        MovieGenre mGenre = MovieGenre.valueOf(genre);
        Double budget = jsonObject.getDouble("budget");
        Float rating = (float) jsonObject.getDouble("rating");
        Integer duration = jsonObject.getInt("duration");
        Boolean oscar = jsonObject.getBoolean("oscarWinner");
        String poster = jsonObject.getString("poster");
        return new Movie(title, mGenre, budget, duration,rating,
                true, oscar, ParentalApprovalEnum.G, mRelease, poster);
    }
}
