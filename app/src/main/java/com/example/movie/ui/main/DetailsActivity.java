package com.example.movie.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.movie.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class DetailsActivity extends AppCompatActivity {
    TextView overView,date ;
    ImageView MovieImg;
    RatingBar movieRate ;
    String name ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        overView=findViewById(R.id.overview);
        date=findViewById(R.id.date);
        MovieImg=findViewById(R.id.thumbnail_image_header);
        movieRate=findViewById(R.id.movieRateBar);
        Toolbar toolbar = findViewById(R.id.toolbar);
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
        getIntentFromMain();

    }


    public void getIntentFromMain() {

        Intent intent = getIntent();
        if (intent.hasExtra("MovieName")){

            name = intent.getStringExtra("MovieName");
            String overview = intent.getStringExtra("overview");
            String movDate = intent.getStringExtra("date");
            String imgPath=intent.getStringExtra("posterUrl");
            float rate= intent.getFloatExtra("rate",0);

            String img = "https://image.tmdb.org/t/p/w500" + imgPath;
            Glide.with(this)
                    .load(img)
                    .into(MovieImg);

            overView.setText(overview);
            date.setText(movDate);
            movieRate.setRating(rate);
        }
    }

    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout =
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener(){
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset){
                if (scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0){
                    collapsingToolbarLayout.setTitle(name);
                    isShow = true;
                }else if (isShow){
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
}
