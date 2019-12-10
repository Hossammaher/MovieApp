package com.example.movie.data;


import com.example.movie.pojo.MovieResponse;
import com.example.movie.pojo.TrailerResponse;
import com.example.movie.ui.main.DetailsActivity;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieClient {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "cd55b239ae483743e2de1ef704e13dac";

    private MovieInterface movieInterface;
    private static MovieClient INSTANCE;


    public MovieClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieInterface = retrofit.create(MovieInterface.class);
    }

    public static MovieClient getINSTANCE() {
        if (null == INSTANCE){
            INSTANCE = new MovieClient();
        }
        return INSTANCE;
    }

    public Call<MovieResponse> getMovie(){
        return movieInterface.getMovie(API_KEY);
    }

    public Call<MovieResponse> getTopRatedMovies(){
        return movieInterface.getTopRatedMovies(API_KEY);
    }

    public Call<TrailerResponse> getMovieTrailer() {
        return movieInterface.getMovieTrailer(DetailsActivity.movie_id, API_KEY);
    }


}
