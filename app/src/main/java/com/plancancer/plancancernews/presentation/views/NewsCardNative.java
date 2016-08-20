package com.plancancer.plancancernews.presentation.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.plancancer.plancancernews.persistance.model.NewsElementsResources;

/**
 * Created by Assou on 04/08/2015.
 */
public class NewsCardNative extends CardView {


    /*Header*/
    private TextView newsTitle;
    private TextView newsAuthor;
    private TextView newsTime;
    /*image*/
    private ImageView newsImage;


    public NewsCardNative(Context context, int layoutResource, NewsElementsResources resources) {
        super(context);
        View v = LayoutInflater.from(context).inflate(layoutResource, (ViewGroup) this.getRootView(), false);
        this.newsImage = ((ImageView) v.findViewById(resources.newsImage));
        this.newsTitle = ((TextView) v.findViewById(resources.newsTitle));
        this.newsAuthor = ((TextView) v.findViewById(resources.newsAuthor));
        this.newsTime = ((TextView) v.findViewById(resources.newsTime));
        this.addView(v);
    }


    public TextView getNewsTitle() {
        return newsTitle;
    }

    public TextView getNewsAuthor() {
        return newsAuthor;
    }

    public TextView getNewsTime() {
        return newsTime;
    }

    public ImageView getNewsImage() {
        return newsImage;
    }


}
