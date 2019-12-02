package com.example.movie.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.movie.R;
import com.example.movie.ui.main.FavouriteFragment;
import com.example.movie.ui.main.HomeFragment;
import com.example.movie.ui.main.TopRatedFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final HomeFragment homeFragment = new HomeFragment();
        final TopRatedFragment topRatedFragment = new TopRatedFragment();
        final FavouriteFragment favouriteFragment = new FavouriteFragment();

        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){

                    case R.id.home:
                        initFragment(homeFragment);

                        break;

                    case R.id.top:
                        initFragment(topRatedFragment);
                        break;

                    case R.id.fav:
                        initFragment(favouriteFragment);
                        break;

                }

                return true;
            }
        });

        navigationView.setSelectedItemId(R.id.home);

    }

    private void initFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment);
        fragmentTransaction.commit();
    }
}