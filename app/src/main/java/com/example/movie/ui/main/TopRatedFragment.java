package com.example.movie.ui.main;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;
import com.example.movie.data.MovieInterface;
import com.example.movie.pojo.MovieModel;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import es.dmoral.toasty.Toasty;


public class TopRatedFragment extends Fragment {

    private View view;
    private MovieInterface movieInterface;
    private AVLoadingIndicatorView loadingIndicatorView;

    public TopRatedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_top_rated, container, false);
        loadingIndicatorView = view.findViewById(R.id.loadingTopRated);

        if (haveNetwork()) { // check internet connection

            initview();

        } else {
            Toasty.custom(getContext(), "No Internet Connection",
                    R.drawable.no_internet_24dp, R.color.colorPrimary,
                    5000, true, true).show();
            loadingIndicatorView.hide();
        }

        return view;
    }


    private void initview() {


        MovieViewModel movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        loadingIndicatorView.show();
        movieViewModel.getTopRatedMovies();


        RecyclerView recyclerView = view.findViewById(R.id.TopMovieRecycler);
        final MoviesAdapter adapter = new MoviesAdapter(getContext());

        //set orientation
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
            recyclerView.setAdapter(adapter);
        } else {


            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            recyclerView.setAdapter(adapter);
        }

        movieViewModel.moviesMutableLiveData.observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> chapterModels) {

                adapter.setList(chapterModels);
                loadingIndicatorView.hide();
            }
        });

    }


    private boolean haveNetwork() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();

    }


}