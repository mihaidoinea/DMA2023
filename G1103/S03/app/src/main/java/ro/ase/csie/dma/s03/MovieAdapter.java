package ro.ase.csie.dma.s03;

import android.content.Context;
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

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private Context context;
    private ArrayList<Movie> movies;
    static int index = 0;
    public MovieAdapter(MainActivity mainActivity, ArrayList<Movie> movieArrayList) {
        this.context = mainActivity;
        this.movies = movieArrayList;
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
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder {

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
        }
    }
}
