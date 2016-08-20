package com.plancancer.plancancernews.presentation.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.plancancer.plancancernews.persistance.model.NewsCommentsElementsResources;

/**
 * Created by Assou on 04/08/2015.
 */
public class NewsCardNativeComment extends CardView {


    /*Header*/
    private TextView newsTitle;
    private TextView newsAuthor;
    private TextView newsTime;
    /*image*/
    private ImageView newsImage;
    private TextView newsCommentBrief;






    public NewsCardNativeComment(Context context, int layoutResource, NewsCommentsElementsResources resources) {
        super(context);
        View v = LayoutInflater.from(context).inflate(layoutResource, (ViewGroup) this.getRootView(), false);
        this.newsImage = ((ImageView) v.findViewById(resources.CommentAvatar));
        this.newsTitle = ((TextView) v.findViewById(resources.commentTitle));
        this.newsAuthor = ((TextView) v.findViewById(resources.commentAuthor));
        this.newsTime = ((TextView) v.findViewById(resources.commentTime));
        this.newsCommentBrief=((TextView)v.findViewById(resources.commentBrief));
        this.addView(v);
    }

    public TextView getNewsCommentBrief() {
        return newsCommentBrief;
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
