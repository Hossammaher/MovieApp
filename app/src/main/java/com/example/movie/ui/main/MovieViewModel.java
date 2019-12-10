package com.example.movie.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movie.data.MovieClient;
import com.example.movie.pojo.MovieModel;
import com.example.movie.pojo.MovieResponse;
import com.example.movie.pojo.TrailerModel;
import com.example.movie.pojo.TrailerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MovieViewModel extends ViewModel {
    MutableLiveData<List<MovieModel>> moviesMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> movies = new MutableLiveData<>();

    MutableLiveData<List<TrailerModel>> trailerMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> trailers = new MutableLiveData<>();

    public void getMovie() {
        MovieClient.getINSTANCE().getMovie().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                moviesMutableLiveData.setValue(response.body().getResults());

                Log.d(TAG, "onResponse: " + response.body().getResults());
            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    public void getTopRatedMovies(){

        MovieClient.getINSTANCE().getTopRatedMovies().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                moviesMutableLiveData.setValue(response.body().getResults());

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

    }


    public void getMovieTrailer() {

        MovieClient.getINSTANCE().getMovieTrailer().enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {

                trailerMutableLiveData.setValue(response.body().getResults());


            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {

            }
        });

    }


}
