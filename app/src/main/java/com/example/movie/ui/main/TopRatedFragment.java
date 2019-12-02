package com.example.movie.ui.main;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movie.R;
import com.example.movie.data.MovieInterface;
import com.example.movie.pojo.MovieModel;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;


public class TopRatedFragment extends Fragment {

    private MovieInterface movieInterface;
    private AVLoadingIndicatorView loadingIndicatorView ;
    private MovieViewModel movieViewModel;

    public TopRatedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_rated, container, false);



            loadingIndicatorView=view.findViewById(R.id.loadingTopRated);
            movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
            loadingIndicatorView.show();
            movieViewModel.getTopRatedMovies();


            RecyclerView recyclerView = view.findViewById(R.id.TopMovieRecycler);
            final MoviesAdapter adapter = new MoviesAdapter(getContext());

            //set orientation
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
                recyclerView.setAdapter(adapter);
            } else {


                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                recyclerView.setAdapter(adapter);
            }

            movieViewModel.moviesMutableLiveData.observe(this, new Observer<List<MovieModel>>() {
                @Override
                public void onChanged(List<MovieModel> chapterModels) {

                    adapter.setList(chapterModels);
                    loadingIndicatorView.hide();
                }
            });

        return view;
    }


}