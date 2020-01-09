package com.example.moviedb.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.model.Result;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewMovieFragment extends Fragment {
    private final String IMAGE_URL = "https://image.tmdb.org/t/p/w500/";

    @BindView(R.id.frag_poster_view)
    ImageView posterImage;

    @BindView(R.id.frag_title_tv)
    TextView titleTextView;

    @BindView(R.id.frag_score_tv)
    TextView scoreTextView;

    @BindView(R.id.frag_date_tv)
    TextView dateTextView;

    @BindView(R.id.frag_overview_tv)
    TextView overviewTextView;

    @BindView(R.id.frag_background_iv)
    ImageView backgroundImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_fragment_layout, container, false);
        //Log.d("TAG_X", "onCreateView: "+(view==null));
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        //Log.d("TAG_X", "onViewCreated: "+(bundle==null));
        Result movie = bundle.getParcelable("selected_movie");

        //Log.d("TAG_X", "onViewCreated: "+ movie.getTitle());

        Glide.with(view.getContext().getApplicationContext())
                .load(IMAGE_URL + movie.getPosterPath())
                .into(posterImage);

        titleTextView.setText(movie.getTitle());
        scoreTextView.setText("Score: " + movie.getVoteAverage().toString());
        dateTextView.setText(movie.getReleaseDate());
        overviewTextView.setText(movie.getOverview());

        Glide.with(view.getContext().getApplicationContext())
                .load(IMAGE_URL + movie.getBackdropPath())
                .into(backgroundImageView);


    }


}
