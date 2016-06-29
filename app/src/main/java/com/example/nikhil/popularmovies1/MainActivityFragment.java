package com.example.nikhil.popularmovies1;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

    public class fetchMoviesTask extends AsyncTask<Void,Void,Void>{
        private final String LOG_TAG = fetchMoviesTask.class.getSimpleName();

        @Override
        protected Void doInBackground(Void ...params){


            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String moviesJsonStr = null;


            try{


                String baseUrl = "http://api.themoviedb.org/3/movie/top_rated?";
                String apikey = "api_key=" + BuildConfig.MOVIES_API_KEY;
                URL url = new URL(baseUrl.concat(apikey));

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {

                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                moviesJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);

                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
            return null;
        }
    }
            }




