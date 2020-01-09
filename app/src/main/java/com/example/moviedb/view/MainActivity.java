package com.example.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviedb.R;
import com.example.moviedb.adapter.MovieAdapter;
import com.example.moviedb.model.MovieResult;
import com.example.moviedb.model.Result;
import com.example.moviedb.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieDelegate{

    private MovieViewModel viewModel;
    Observer<MovieResult> myObserver;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.query_et)
    EditText queryEditText;

    private ViewMovieFragment viewMovieFragment = new ViewMovieFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        viewModel = ViewModelProviders
                .of(this).get(MovieViewModel.class);

        myObserver = new Observer<MovieResult>() {
            @Override
            public void onChanged(MovieResult movieResult) {

                setRV(movieResult.getResults());
            }
        };

        viewModel.getResultLiveData().observe(this, myObserver);
        viewModel.getMovieViewModel("Batman");
    }

    @OnClick(R.id.search_bt)
    public void searchButton(){
        if(!queryEditText.getText().toString().equals("")){
            viewModel.getMovieViewModel(queryEditText.getText().toString());
            queryEditText.setText("");
            queryEditText.setHint("movies");
        }
        else
            Toast.makeText(this, "Enter a Movie Title", Toast.LENGTH_SHORT).show();
    }

    private void setRV (List<Result> movieResults){
        //Log.d("TAG_X", movieResults.get(0).getTitle());
        MovieAdapter adapter = new MovieAdapter(movieResults,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));
    }

    @Override
    public void viewItem(Result movie) {
        Bundle movieBundle = new Bundle();
        movieBundle.putParcelable("selected_movie", movie);

        viewMovieFragment.setArguments(movieBundle);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_frame_layout, viewMovieFragment)
                .addToBackStack(viewMovieFragment.getTag())
                .commit();
    }
}
