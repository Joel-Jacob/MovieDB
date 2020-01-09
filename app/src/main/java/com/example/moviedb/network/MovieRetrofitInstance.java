package com.example.moviedb.network;

import android.util.Log;

import com.example.moviedb.model.MovieResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRetrofitInstance {
    private final String BASE_URL = "https://api.themoviedb.org";
    private final String API_KEY = "4f775d59b627d6e89557b34f030412bd";
    private final String INCLUDE_ADULT = "false";
    private MovieService movieService;

    public MovieRetrofitInstance() {
        movieService = createMovieService(getInstance());
    }

    private Retrofit getInstance(){
        return new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private MovieService createMovieService(Retrofit retrofit){
        return retrofit.create(MovieService.class);
    }

    //https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher
    public Call<MovieResult> getMoviesInstance(String query){
        //Log.d("TAG_X", API_KEY +" "+ query);
        return movieService.getMovies(API_KEY, query, INCLUDE_ADULT);

    }
}
