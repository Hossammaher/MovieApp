package com.example.movie.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerResponse {


    @SerializedName("id")
    private int id_trailer;
    @SerializedName("results")
    private List<TrailerModel> results;

    public int getIdTrailer() {
        return id_trailer;
    }

    public void seIdTrailer(int id_trailer) {
        this.id_trailer = id_trailer;
    }

    public List<TrailerModel> getResults() {
        return results;
    }

}
