package com.example.movie.database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie.R;
import com.example.movie.pojo.MovieModel;
import com.example.movie.ui.main.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class FavAdpater extends RecyclerView.Adapter<FavAdpater.myViewHolder> {

    private Context mContext;
    private List<film> filmList = new ArrayList<>();

    public FavAdpater(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_item, viewGroup, false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final myViewHolder viewHolder, int i) {

        viewHolder.Movie_Name.setText(filmList.get(i).getTitle());
        viewHolder.ratingBar.setRating((filmList.get(i).getRate()));

        String poster = "https://image.tmdb.org/t/p/w500" + filmList.get(i).getPosterPath();

        Glide.with(mContext)
                .load(poster)
                .into(viewHolder.MovieImg);

    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public void setList(List<film> filmList) {
        this.filmList = filmList;
        notifyDataSetChanged();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView Movie_Name;
        ImageView MovieImg;
        RatingBar ratingBar;


        public myViewHolder(View view) {
            super(view);


            Movie_Name = view.findViewById(R.id.MovieName);
            MovieImg = view.findViewById(R.id.MovieImage);
            ratingBar = view.findViewById(R.id.ratingBar);
            ratingBar.setNumStars(5);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {

                        film clicked = filmList.get(pos);
                        float movieRate = (clicked.getRate());

                        Intent intent = new Intent(mContext, DetailsActivity.class);
                        intent.putExtra("MovieName", clicked.getTitle());
                        intent.putExtra("posterUrl", clicked.getPosterPath());
                        intent.putExtra("rate", movieRate);
                        intent.putExtra("overview", clicked.getOverview());
                        intent.putExtra("date", clicked.getDate());
                        intent.putExtra("movie_id", clicked.getMovieId());

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
//                        Toast.makeText(context, " " + clicked.getId(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(context, " "+xx, Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }
    }
}
