package com.example.movie.data;

import com.example.movie.pojo.MovieResponse;
import com.example.movie.pojo.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieInterface {
    @GET("discover/movie")
    Call<MovieResponse> getMovie(@Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Call<TrailerResponse> getMovieTrailer(@Path("movie_id") int id, @Query("api_key") String apiKey);



}
