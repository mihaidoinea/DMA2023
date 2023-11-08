package ro.ase.csie.dma.s03;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private Context context;
    static private ArrayList<Movie> movies;

    static private HashMap<Movie, Integer> movieOptions;

    public MovieAdapter(MainActivity mainActivity, ArrayList<Movie> movieArrayList) {
        this.context = mainActivity;
        this.movies = movieArrayList;
        movieOptions = new HashMap<>();
        for(Movie movie: movies)
        {
            movieOptions.put(movie, 0);
        }
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.movie_item, parent, false);
        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.title);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
        holder.tvRelease.setText(sdf.format(movie.release));
        holder.rbRating.setRating(movie.rating);
        Bitmap bitmap = getPosterURL(movie.posterUrl);
        holder.ivPoster.setImageBitmap(bitmap);
        holder.position = movies.indexOf(movie);

//        int identifier = context.getResources().getIdentifier("superman", "drawable", context.getPackageName());
//        holder.ivPoster.setImageResource(identifier);
    }

    private Bitmap getPosterURL(String posterUrl) {
        Bitmap bitmap = null;
        DownloadCallablePoster dcp = new DownloadCallablePoster(posterUrl);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Bitmap> submit = executor.submit(dcp);
        try {
            bitmap = submit.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return bitmap;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder {

        protected  int position;
        protected TextView tvTitle;
        protected TextView tvRelease;
        protected RatingBar rbRating;
        protected RadioGroup rgOptions;
        protected ImageView ivPoster;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRelease = itemView.findViewById(R.id.tvRelease);
            rbRating = itemView.findViewById(R.id.rbRating);
            rgOptions = itemView.findViewById(R.id.rgOptions);
            ivPoster = itemView.findViewById(R.id.ivPoster);

            rgOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    Movie movie = movies.get(position);
                    movieOptions.put(movie, checkedId);
                }
            });
        }
    }
}
