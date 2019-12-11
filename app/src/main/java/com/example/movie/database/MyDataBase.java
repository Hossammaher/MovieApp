package com.example.movie.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {film.class}, version = 1, exportSchema = false)

public abstract class MyDataBase extends RoomDatabase {

    public abstract MyDeo myDeo();

}