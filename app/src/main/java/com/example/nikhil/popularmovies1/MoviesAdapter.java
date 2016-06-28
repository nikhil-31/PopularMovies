package com.example.nikhil.popularmovies1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nikhil on 28-06-2016.
 */
public class MoviesAdapter extends ArrayAdapter<moviePoster> {
    private static final String LOG_TAG = MoviesAdapter.class.getSimpleName();


    public  MoviesAdapter(Activity context,List<moviePoster>posters){
        super(context,0,posters);

    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        moviePoster poster = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_pop,parent,false);
        }
        ImageView iconView = (ImageView) convertView.findViewById(R.id.movie_poster);
        iconView.setImageResource(poster.image);

        TextView MovieName = (TextView) convertView.findViewById(R.id.movie_name);
        MovieName.setText(poster.name+ "-" +poster.year );

        return convertView;
    }
}
