package com.example.moviedb.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.model.MovieResult;
import com.example.moviedb.network.MovieRetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends AndroidViewModel {
    private MovieRetrofitInstance retrofitInstance = new MovieRetrofitInstance();
    private MutableLiveData<MovieResult> resultLiveData = new MutableLiveData<>();

    public MovieViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<MovieResult> getResultLiveData(){
        return resultLiveData;
    }

    public void getMovieViewModel(String query){
        retrofitInstance.getMoviesInstance(query).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                resultLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                //TODO: handle error
                Log.d("TAG_X", "no movies here boss" + t);
            }
        });
    }



}
