package com.example.moviedb.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.model.Result;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private List<Result> movieResults;
    private Context appplicationContext;
    private MovieDelegate memberDelegate;

    private final String IMAGE_URL = "https://image.tmdb.org/t/p/w500/";

    public interface MovieDelegate{
        void viewItem(Result movie);
    }

    public MovieAdapter(List<Result> movieResults, MovieDelegate delegate){
        this.movieResults = movieResults;
        this.memberDelegate = delegate;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        appplicationContext = parent.getContext().getApplicationContext();

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item_layout,parent,false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.titleTV.setText(movieResults.get(position).getTitle());

        //Log.d("TAG_X", "onBindViewHolder: " + movieResults.get(position).getTitle().length());

        if(movieResults.get(position).getTitle().length() >= 30)
            holder.titleTV.setTextSize(12f);
        else if(movieResults.get(position).getTitle().length() >= 20)
            holder.titleTV.setTextSize(16f);

        holder.dateTV.setText(movieResults.get(position).getReleaseDate());
        holder.scoreTV.setText("Score: " + movieResults.get(position).getVoteAverage().toString());

        Glide.with(appplicationContext)
                .load(IMAGE_URL + movieResults.get(position).getPosterPath())
                .into(holder.posterImage);

        Animation transition = AnimationUtils.loadAnimation(appplicationContext, R.anim.transition_animation);
        holder.itemView.startAnimation(transition);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("TAG_X", movieResults.get(position).getTitle());
                memberDelegate.viewItem(movieResults.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieResults.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView titleTV;
        TextView scoreTV;
        TextView dateTV;
        ImageView posterImage;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.item_title_tv);
            scoreTV = itemView.findViewById(R.id.item_score_tv);
            dateTV = itemView.findViewById(R.id.item_date_tv);
            posterImage = itemView.findViewById(R.id.item_image_view);

        }
    }
}
