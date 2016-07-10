package com.example.nikhil.popularmovies1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ImageView poster = (ImageView) findViewById(R.id.poster_details);
        TextView releaseDate = (TextView) findViewById(R.id.Release_write);
        TextView rating = (TextView) findViewById(R.id.Rating_write);
        TextView title = (TextView) findViewById(R.id.Title_write);
        TextView overview = (TextView) findViewById(R.id.Overview_write);
        ImageView backdrop = (ImageView) findViewById(R.id.backdrop);

        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("PARCEL_MOVIE");

        Picasso.with(this)
                .load(movie.getPosterPath())
                .into(poster);

        Picasso.with(this)
                .load(movie.getBackdrop())
                .into(backdrop);

        releaseDate.setText(movie.getReleaseDate());

        rating.setText(movie.getVoteAverage());

        title.setText(movie.getOriginalTitle());

        overview.setText(movie.getOverview());

    }
}
