package ro.ase.csie.dma.s03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private ArrayList<Movie> movies;
    private Context context;

    public MovieAdapter(MainActivity mainActivity, ArrayList<Movie> movieArrayList) {
        this.context = mainActivity;
        this.movies = movieArrayList;
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