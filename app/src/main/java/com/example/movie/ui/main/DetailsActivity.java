package com.example.movie.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.movie.R;
import com.example.movie.data.MovieInterface;
import com.example.movie.database.MyDataBase;
import com.example.movie.database.film;
import com.example.movie.pojo.MovieModel;
import com.example.movie.pojo.TrailerModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class DetailsActivity extends AppCompatActivity {

    public static int movie_id;
    public static MyDataBase myDataBase;
    TextView overView, date;
    ImageView MovieImg;
    RatingBar movieRate;
    String Movie_name, overview, movDate, imgPath;
    float rate;
    MovieViewModel movieViewModel;
    View view;
    RecyclerView trailer_recycler;
    MovieModel movieModel;
    boolean CheckIfINFavorite;
    CheckBox FavoriteButton;
    private MovieInterface movieInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        overView = findViewById(R.id.overview);
        date = findViewById(R.id.date);
        MovieImg = findViewById(R.id.thumbnail_image_header);
        movieRate = findViewById(R.id.movieRateBar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        trailer_recycler = findViewById(R.id.Trailer_recycler);

        FavoriteButton = findViewById(R.id.FavoriteButton);
        getIntentFromMain();
        myDataBase = Room.databaseBuilder(this, MyDataBase.class, "favdb").allowMainThreadQueries().build();
        CheckIfINFavorite = myDataBase.myDeo().findDirectorByID(movie_id);

        if (CheckIfINFavorite) {
            FavoriteButton.setChecked(true);
        }

        FavoriteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    buttonView.setChecked(true);
                    saveFavourite();
                } else {
                    buttonView.setChecked(false);
                    DelFavourite();
                }
            }
        });

        setSupportActionBar(toolbar);
        //add back button in toolbar and action to back to the same fragment
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });

        //init the toolbar
        initCollapsingToolbar();
        //get intent from main activity and post in this activity

        initView();


    }


    public void getIntentFromMain() {

        Intent intent = getIntent();
        if (intent.hasExtra("MovieName")) {

            Movie_name = intent.getStringExtra("MovieName");
            overview = intent.getStringExtra("overview");
            movDate = intent.getStringExtra("date");
            imgPath = intent.getStringExtra("posterUrl");
            rate = intent.getFloatExtra("rate", 0);
            movie_id = intent.getIntExtra("movie_id", 0);
//            Toast.makeText(this, " " + movie_id, Toast.LENGTH_SHORT).show();

            String img = "https://image.tmdb.org/t/p/w500" + imgPath;
            Glide.with(this)
                    .load(img)
                    .into(MovieImg);

            overView.setText(overview);
            date.setText(movDate);
            movieRate.setRating(rate);

        }
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbarLayout =
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(Movie_name);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


    private void initView() { //fill the recyclerview


        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovieTrailer();  // show trailer from moiveViewmodel


        final TrailerAdapter adapter = new TrailerAdapter(DetailsActivity.this);

        trailer_recycler.setLayoutManager(new LinearLayoutManager(this));
        trailer_recycler.setAdapter(adapter);

        movieViewModel.trailerMutableLiveData.observe(this, new Observer<List<TrailerModel>>() {
            @Override
            public void onChanged(List<TrailerModel> trailerModels) {

                adapter.setList(trailerModels);

            }
        });

    }


    private void saveFavourite() {

        // to room database
        film film = new film();
        film.setMovieId(movie_id);
        film.setTitle(Movie_name);
        film.setOverview(overview);
        film.setPosterPath(imgPath);
        film.setRate(rate);
        film.setDate(movDate);

        myDataBase.myDeo().addFav(film);
        Toasty.success(getApplicationContext(), "Added to Favorite").show();

    }

    public void DelFavourite() {

        // Toasty.error(getApplicationContext(),"already exist").show();
        film film = new film();
//                  int id = getIntent().getIntExtra("movie_id", 0);
        myDataBase.myDeo().deleteFav(movie_id);
        Toasty.error(getApplicationContext(), "Removed from Favorite ").show();
//        onBackPressed();


    }


}