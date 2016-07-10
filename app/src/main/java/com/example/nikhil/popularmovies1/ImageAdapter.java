package com.example.nikhil.popularmovies1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by nikhil on 07-07-2016.
 */
public class ImageAdapter extends BaseAdapter{
    private Context mcontext;
    private Movie[] mmovie;

    public ImageAdapter(Context c,Movie[] movies){
        this.mcontext=c;
        this.mmovie=movies;
    }

    @Override
    public int getCount() {
        if (mmovie == null || mmovie.length == 0) {
            return -1;
        }

        return mmovie.length;
    }

    @Override
    public Movie getItem(int position) {
        if (mmovie == null || mmovie.length == 0) {
            return null;
        }

        return mmovie[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;


        if (convertView == null) {
            imageView = new ImageView(mcontext);
            imageView.setAdjustViewBounds(true);
        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(mcontext)
                .load(mmovie[position].getPosterPath())
                .into(imageView);

        return imageView;
    }
}
