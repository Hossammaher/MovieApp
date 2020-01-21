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

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {

    private MyDataBase db;
    private List<film> allFavorite = new ArrayList<>();
    private RecyclerView FavoriteRecycler;
    private TextView FAV_text;
    private int orientation;

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
        check();
    }

    @Override
    public void onResume() {
        super.onResume();

        check();
    }

    private void check() { // check orination

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