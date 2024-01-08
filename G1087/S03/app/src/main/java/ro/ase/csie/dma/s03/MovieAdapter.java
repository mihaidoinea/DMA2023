package ro.ase.csie.dma.s03;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private ArrayList<Movie> movies;
    private Context context;
    private IMovieItemEvents mainActivityCallback;

    private ExecutorService executors;

    private DatabaseManager dbManager;

    public MovieAdapter(MainActivity mainActivity, ArrayList<Movie> movieArrayList) {
        this.context = mainActivity;
        this.movies = movieArrayList;
        this.executors = Executors.newFixedThreadPool(5);
        this.mainActivityCallback = mainActivity;
        this.dbManager = DatabaseManager.getInstance(mainActivity);
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
        holder.tvRelease.setText("Release: " + movie.release.toString());
        holder.rbRating.setRating(movie.getRating());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(), "Movie:"+movie, Toast.LENGTH_LONG).show();
                int position = movies.indexOf(movie);
                mainActivityCallback.onClickedMovie(position);
            }
        });
        Future<Bitmap> submit = executors.submit(new CallableDownloadTask(movie.posterUrl));
        try {
            holder.ivPoster.setImageBitmap(submit.get());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        holder.cbSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MovieDao movieDao = dbManager.getMovieDao();
                if(isChecked)
                {
                   //save in a local database
                    movieDao.insert(movie);
                }
                else
                {
                    movieDao.delete(movie);
                }
            }
        });




        //from static resources
        /*String imgName = "img_" + ((position % 2) + 1);
        int drawable = context.getResources().getIdentifier(imgName, "drawable", context.getPackageName());
        holder.ivPoster.setImageResource(drawable);*/
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    protected static class MovieHolder extends RecyclerView.ViewHolder
    {

        protected TextView tvTitle;
        protected TextView tvRelease;
        protected RatingBar rbRating;
        protected ImageView ivPoster;
        protected CheckBox cbSelected;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRelease = itemView.findViewById(R.id.tvRelease);
            rbRating = itemView.findViewById(R.id.rbRating);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            cbSelected = itemView.findViewById(R.id.cbSelected);
        }
    }

}
