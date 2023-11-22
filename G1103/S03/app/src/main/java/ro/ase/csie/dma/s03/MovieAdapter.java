package ro.ase.csie.dma.s03;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
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
    private static ArrayList<Movie> movies;
    public static HashMap<Movie, Integer> movieOptions;
    static int index = 0;

    private IMovieItemEvents mainActivityCallback;
    public MovieAdapter(MainActivity mainActivity, ArrayList<Movie> movieArrayList) {
        this.context = mainActivity;
        this.mainActivityCallback = mainActivity;
        this.movies = movieArrayList;
        movieOptions = new HashMap<>();
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d("MovieAdapter", "onCreateViewHolder_" + index++);
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
        Bitmap bitmap = getMoviePoster(movie.posterUrl);
        holder.ivPoster.setImageBitmap(bitmap);
        int pos = movies.indexOf(movie);
        holder.position = pos;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityCallback.onMovieItemClicked(pos);
            }
        });
       /* String imageName = "img_"+(position+1);
        int drawable = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        holder.ivPoster.setImageResource(drawable);*/
    }

    private Bitmap getMoviePoster(String posterUrl) {
        Bitmap bitmap = null;
        ExecutorService executors = Executors.newFixedThreadPool(3);
        Future<Bitmap> submit = executors.submit(new DownloadCallableTask(posterUrl));
        try {
            bitmap = submit.get();
            //ivCallable.setImageBitmap(submit.get());
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

        protected int position;
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
