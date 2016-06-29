package com.example.nikhil.popularmovies1;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.moviesfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            fetchMoviesTask moviesTask =  new fetchMoviesTask();
            moviesTask.execute("top_rated");
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    public class fetchMoviesTask extends AsyncTask<String,Void,String[]>{
        private final String LOG_TAG = fetchMoviesTask.class.getSimpleName();


        private String[] getMoviesDataFromJson(String moviesJsonStr)
            throws JSONException {


            JSONObject movies = new JSONObject(moviesJsonStr);
            JSONArray results = movies.getJSONArray("results");

            String[] resultstrs = new String[results.length()];
            for(int i=0;i<results.length();i++){
                JSONObject jsonObject = results.getJSONObject(i);

                String poster = jsonObject.optString("poster_path");
                String overview = jsonObject.optString("overview");
                String title = jsonObject.optString("original_title");
                String release= jsonObject.optString("release_date");
                float rating = Float.parseFloat(jsonObject.optString("vote_average").toString());

                resultstrs[i] = poster+"-"+overview+"-"+title+"-"+release+"-"+rating;

            }
            for(String s:resultstrs){
                Log.v(LOG_TAG,"Movies entry"+s);
            }
            return  resultstrs;
        }


        @Override
        protected String[] doInBackground(String ...params){


            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String moviesJsonStr = null;


            try{


                String baseUrl = "http://api.themoviedb.org/3/movie/"+params[0]+"?";
                String apikey = "api_key=" + BuildConfig.MOVIES_API_KEY;
                URL url = new URL(baseUrl.concat(apikey));
                //Log.v(LOG_TAG,"url"+url);
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

                Log.v(LOG_TAG,"movie Data string"+ moviesJsonStr);
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
            try {
                return getMoviesDataFromJson(moviesJsonStr);
                            } catch (JSONException e) {
                                Log.e(LOG_TAG, e.getMessage(), e);
                                e.printStackTrace();
                           }
            return null;
        }
    }
            }




