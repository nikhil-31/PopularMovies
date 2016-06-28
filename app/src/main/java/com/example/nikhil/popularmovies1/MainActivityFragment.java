package com.example.nikhil.popularmovies1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;

import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }
    private MoviesAdapter moviesAdapter;
    moviePoster[] posters = {
            new moviePoster("civil war","2014",R.drawable.civil_war),
            new moviePoster("Fight Club","1999",R.drawable.fightclub),
            new moviePoster("Godfather","1972",R.drawable.godfather),
            new moviePoster("Range","2014",R.drawable.range),
            new moviePoster("Salt","2010",R.drawable.salt),
            new moviePoster("The silence of lambs","2016",R.drawable.silence),
            new moviePoster("ironman","2008",R.drawable.ironman),
            new moviePoster("lucy","2013",R.drawable.lucy),
            new moviePoster("The social network","2010",R.drawable.socialnetwork),



    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        moviesAdapter  = new MoviesAdapter(getActivity(), Arrays.asList(posters));
        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view);
        gridView.setAdapter(moviesAdapter);

        return rootView;
    }
}
