package com.example.movie.data;

import com.example.movie.pojo.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieInterface {
    @GET("discover/movie")
    public Call<MovieResponse> getMovie(@Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

}
