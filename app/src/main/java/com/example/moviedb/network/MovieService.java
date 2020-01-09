package com.example.moviedb.network;

import com.example.moviedb.model.MovieResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
//https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher
    @GET("/3/search/movie")
    Call<MovieResult> getMovies(
            @Query("api_key") String api_key,
            @Query("query") String query,
            @Query("include_adult") String include_adult
    );
}
