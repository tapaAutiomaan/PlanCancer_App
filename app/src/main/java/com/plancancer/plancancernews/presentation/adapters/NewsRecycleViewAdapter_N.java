package com.plancancer.plancancernews.presentation.adapters;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.plancancer.R;
import com.plancancer.plancancernews.persistance.model.NewsElements;
import com.plancancer.plancancernews.persistance.model.NewsElementsResources;
//import com.plancancer.presentation.fragments.principal_view.native_cards.NewsCardsFragmentNative;
import com.plancancer.plancancernews.presentation.views.NewsCardNative;

import java.util.ArrayList;

/*import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
*/

/**
 * Created by Assou on 05/08/2015.
 */
public class NewsRecycleViewAdapter_N extends RecyclerView.Adapter<NewsRecycleViewAdapter_N.NewsViewHolder> {

    private static final String TAG = "PlanCancerNewsAdapter";
    private ArrayList<NewsElements> elementsList;
    ArrayList<Integer> ElementsLoaded = new ArrayList<>();

    //private ImageLoader imageLoader = VolleySingleton.getInstance().getImageLoader();
    private boolean loadedElement;
    //private NewsCardsFragmentNative fragmentNative;


    public class NewsViewHolder extends RecyclerView.ViewHolder {


        /*Header*/
        private final TextView newsTitle;

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

        private final TextView newsAuthor;
        private final TextView newsTime;
        /*image*/
        private final ImageView newsImage;


        public NewsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("ViewHolder", "(View in)ViewHolderClicked a View was clicked");
                    Log.e("Card position is ", ":" + getAdapterPosition());
                    int cardPosition = getAdapterPosition();
                    //TODO Showing detail here please don't forget ->yazid
                    //NewsRecycleViewAdapter_N.this.fragmentNative.showDetail(NewsRecycleViewAdapter_N.this.elementsList.get(cardPosition));

                }
            });


            newsTitle = ((TextView) itemView.findViewById(R.id.news_card_header_title));
            newsAuthor = ((TextView) itemView.findViewById(R.id.news_card_header_author));
            newsTime = ((TextView) itemView.findViewById(R.id.news_card_header_time));

            newsImage = ((ImageView) itemView.findViewById(R.id.news_card_image));


        }


    }


    public void addAnotherElement(NewsElements element) {
        this.elementsList.add(element);

    }

    public NewsRecycleViewAdapter_N(ArrayList<NewsElements> list, Fragment fragment) {
        //this.fragmentNative = (NewsCardsFragmentNative) fragment;
        this.elementsList = list;
    }


    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card_content,parent,false);
        NewsElementsResources resources = new NewsElementsResources();
        resources.newsAuthor = R.id.news_card_header_author;
        resources.newsTitle = R.id.news_card_header_title;
        resources.newsTime = R.id.news_card_header_time;
        resources.newsImage = R.id.news_card_image;
        NewsCardNative viewNative = new NewsCardNative(parent.getContext(), R.layout.news_card_content_card_v, resources);
        return new NewsViewHolder(viewNative);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, int position) {
        //viewHolder


        holder.getNewsTitle().setText(this.elementsList.get(position).getTitle());
        holder.getNewsTime().setText(this.elementsList.get(position).getPubDate());
        holder.getNewsAuthor().setText(this.elementsList.get(position).getAuthor());
        String imageUrl = this.elementsList.get(position).getImageUrl();
        if (imageUrl != null && imageUrl.length() > 0 && !imageUrl.equalsIgnoreCase("image")) {
            if (!this.ElementsLoaded.contains(position)) {
                this.ElementsLoaded.add(position);
/*
                this.imageLoader.get(imageUrl, new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                        Log.e("Invoked", ":" + response.getRequestUrl());
                        holder.getImgV1().setImageBitmap(response.getBitmap());
                    }

                    @Override
                    public void onErrorResponse(com.android.volley.VolleyError error) {
                        Log.e("Error loading", ":" + error.toString());
                        //Picasso.with(holder.getImgV1().getContext()).load(R.drawable.jiltargih).into(holder.getImgV1());
                    }
                });*/


            }

        }
    }

    @Override
    public int getItemCount() {
        return this.elementsList.size();
    }


}
