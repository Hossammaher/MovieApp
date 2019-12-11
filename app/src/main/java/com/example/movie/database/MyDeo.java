package com.example.movie.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.movie.pojo.MovieModel;

import java.util.List;

@Dao
public interface MyDeo {

    @Insert
    void addFav(film film);

    @Query("select * from favourite")
    List<film> getData();

    @Query("DELETE FROM favourite WHERE movieId = :movieId ")
    void deleteFav(int movieId);


    @Query("SELECT * FROM favourite WHERE movieId = :movie_id LIMIT 1")
    boolean findDirectorByID(int movie_id);


}
