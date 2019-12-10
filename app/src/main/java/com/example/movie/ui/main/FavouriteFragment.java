package com.example.movie.ui.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.movie.R;
import com.example.movie.database.FavAdpater;
import com.example.movie.database.MyDataBase;
import com.example.movie.database.film;
import com.example.movie.pojo.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {

    public MyDataBase db;
    MovieModel movieModel;
    List<film> allFavorite = new ArrayList<>();
    RecyclerView FavoriteRecycler;
    TextView FAV_text;
    int orientation;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        FAV_text = view.findViewById(R.id.favText);

        FavoriteRecycler = view.findViewById(R.id.FavoriteRecycler);

        db = Room.databaseBuilder(getContext(), MyDataBase.class, "favdb").allowMainThreadQueries().build();

        orientation = getResources().getConfiguration().orientation;
        allFavorite = db.myDeo().getData();
        FavAdpater adapter = new FavAdpater(getContext());
        if (allFavorite.size() != 0) {

            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                FavoriteRecycler.setLayoutManager(new GridLayoutManager(getContext(), 4));
                FavoriteRecycler.setAdapter(adapter);
            } else {
                FavoriteRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
                FavoriteRecycler.setAdapter(adapter);
            }

            adapter.setList(allFavorite);

        } else {

            FAV_text.setVisibility(View.VISIBLE);

        }


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

//        Toast.makeText(getContext(), "onStart", Toast.LENGTH_SHORT).show();
        check();

    }


    @Override
    public void onResume() {
        super.onResume();

        check();
    }

    public void check() {

        FavAdpater adapter = new FavAdpater(getContext());
        if (allFavorite.size() != 0) {
            allFavorite = db.myDeo().getData();
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                FavoriteRecycler.setLayoutManager(new GridLayoutManager(getContext(), 4));
                FavoriteRecycler.setAdapter(adapter);
            } else {
                FavoriteRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
                FavoriteRecycler.setAdapter(adapter);
            }

            adapter.setList(allFavorite);

        } else {

            FAV_text.setVisibility(View.VISIBLE);

        }


    }


}

