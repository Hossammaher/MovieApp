package com.example.movie.ui.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie.R;
import com.example.movie.pojo.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.PostViewHolder> {
    Context context;
    private List<MovieModel> moviesList = new ArrayList<>();

    public MoviesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.Movie_Name.setText(moviesList.get(position).getTitle());
        holder.ratingBar.setRating((moviesList.get(position).getVoteAverage().floatValue()) / 2);

        String poster = "https://image.tmdb.org/t/p/w500" + moviesList.get(position).getPosterPath();

        Glide.with(context)
                .load(poster)
                .into(holder.MovieImg);


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void setList(List<MovieModel> moviesList) {
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView Movie_Name;
        ImageView MovieImg;
        RatingBar ratingBar;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            Movie_Name = itemView.findViewById(R.id.MovieName);
            MovieImg = itemView.findViewById(R.id.MovieImage);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            ratingBar.setNumStars(5);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {

                        MovieModel clicked = moviesList.get(pos);
                        float movieRate = (clicked.getVoteAverage().floatValue()) / 2;
                        Intent intent = new Intent(context, DetailsActivity.class);
                        intent.putExtra("MovieName", clicked.getTitle());
                        intent.putExtra("posterUrl", clicked.getPosterPath());
                        intent.putExtra("rate", movieRate);
                        intent.putExtra("overview", clicked.getOverview());
                        intent.putExtra("date", clicked.getReleaseDate());

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
//                        Toast.makeText(context, " "+xx, Toast.LENGTH_SHORT).show();

                    }

                }
            });


        }
    }
}
