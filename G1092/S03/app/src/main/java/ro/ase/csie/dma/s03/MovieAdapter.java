package ro.ase.csie.dma.s03;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<Movie> movies;
    private Context context;
    ExecutorService executors;


    public MovieAdapter(MainActivity mainActivity, ArrayList<Movie> movies) {
        this.context = mainActivity;
        this.movies = movies;
        this.executors = Executors.newFixedThreadPool(5);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.title);
        holder.tvRelease.setText(movie.release.toString());
        holder.rbRating.setRating(movie.getRating());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MovieAdapter","Clicked on: " + movie);
            }
        });
        Future<Bitmap> submit = executors.submit(new CallableDownloadPosterTask(movie.posterUrl));
        Bitmap bitmap = null;
        try {
            bitmap = submit.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        holder.poster.setImageBitmap(bitmap);

       /* String resName = "img_" + (position+1);
        int drawable = context.getResources().getIdentifier(resName, "drawable", context.getPackageName());
        holder.poster.setImageResource(drawable);*/
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder
    {
        protected TextView tvTitle;
        protected TextView tvRelease;
        protected RatingBar rbRating;
        protected ImageView poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRelease = itemView.findViewById(R.id.tvRelease);
            rbRating = itemView.findViewById(R.id.tbRating);
            poster = itemView.findViewById(R.id.imageView);
        }
    }
}
