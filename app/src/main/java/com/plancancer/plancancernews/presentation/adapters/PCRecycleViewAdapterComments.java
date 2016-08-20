package com.plancancer.plancancernews.presentation.adapters;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.plancancer.R;
import com.plancancer.plancancernews.persistance.model.NewsCommentElements;
import com.plancancer.plancancernews.persistance.model.NewsCommentsElementsResources;
import com.plancancer.plancancernews.presentation.fragments.CommentsFragment;
import com.plancancer.plancancernews.presentation.views.NewsCardNativeComment;

import java.util.ArrayList;

/*import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
*/

/**
 * Created by Assou on 05/08/2015.
 */
public class PCRecycleViewAdapterComments extends RecyclerView.Adapter<PCRecycleViewAdapterComments.NewsViewHolder> {

    private static final String TAG = "PlanCancerNewsAdapter";
    private ArrayList<NewsCommentElements> elementsList;
    ArrayList<Integer> ElementsLoaded = new ArrayList<>();
    private boolean loadedElement;
    private CommentsFragment commentsFragment;


    private void handleInnerClick(long id){
        if(this.commentsFragment!=null)
            commentsFragment.goToPost(id);
        Log.e("handleinner","the id is: "+id);
    }


    public void setCommentsFragment(CommentsFragment fragment){
        this.commentsFragment=fragment;
    }





    public int getListSize() {
        return this.elementsList.size() + 1;
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder  {
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

        public TextView getTextBrief() {
            return textBrief;
        }

        private final TextView newsAuthor;
        private final TextView newsTime;
        /*image*/
        private final ImageView newsImage;
        //
        //private  final LinearLayout goToPostBtn;
        /*
        private final TextView gotoPostTxt;
        private final ImageView gotoPostImage;
        */

        private final TextView textBrief;


        public NewsViewHolder(View itemView) {
            super(itemView);

/*
            LinearLayout goToPost = ((LinearLayout) itemView.findViewById(R.id.news_card_controll_comment));
            goToPost.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("GoToPost", "GoToPost is clicked ");
                }
            });*/

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("ViewHolder", "(View in)ViewHolderClicked a View was clicked");
                    Log.e("Card position is ", ":" + getAdapterPosition());
                    int cardPosition = getAdapterPosition();
                    //TODO Showing detail here please don't forget ->yazid
                    handleInnerClick(getAdapterPosition());

                }
            });

            /*
            gotoPostTxt = ((TextView) itemView.findViewById(R.id.gotoPostTxt));
            gotoPostImage = ((ImageView) itemView.findViewById(R.id.gotoPostImage));
            gotoPostImage.setOnClickListener(this);
            gotoPostTxt.setOnClickListener(this);


            //   goToPostBtn=((LinearLayout)itemView.findViewById(R.id.news_card_controll_comment));

            //goToPost=((LinearLayout)itemView.findViewById(R.id.news_card_controll_comment));*/



            newsTitle = ((TextView) itemView.findViewById(R.id.news_card_header_title_comment));
            newsAuthor = ((TextView) itemView.findViewById(R.id.news_card_header_author_comment));
            newsTime = ((TextView) itemView.findViewById(R.id.news_card_header_time_comment));
            textBrief = ((TextView) itemView.findViewById(R.id.news_card_comment_txt));
            newsImage = ((ImageView) itemView.findViewById(R.id.comment_auteur_avatar));
        }

    }





    public void clearInnerList() {
        this.elementsList.clear();
    }

    public void addAnotherElement(NewsCommentElements element) {
        this.elementsList.add(element);
    }

    public PCRecycleViewAdapterComments(ArrayList<NewsCommentElements> list, Fragment fragment) {
        //this.fragmentNative = (NewsCardsFragmentNative) fragment;
        this.elementsList = list;
    }

    public void clearData() {
        int size = this.elementsList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.elementsList.remove(0);
                this.notifyItemRemoved(0);
                //this.notifyItemRangeRemoved(0, size);
                if (this.elementsList.size() > 0)
                    this.notifyItemMoved(1, 0);
            }

        }
    }


    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card_content,parent,false);
        NewsCommentsElementsResources resources = new NewsCommentsElementsResources();
        resources.commentAuthor = R.id.news_card_header_author_comment;
        resources.commentTitle = R.id.news_card_header_title_comment;
        resources.commentTime = R.id.news_card_header_time_comment;
        resources.CommentAvatar = R.id.comment_auteur_avatar;
        resources.commentBrief = R.id.news_card_comment_txt;

        NewsCardNativeComment viewNative = new NewsCardNativeComment(parent.getContext(), R.layout.pc_card_content_comments, resources);
        return new NewsViewHolder(viewNative);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, int position) {
        //viewHolder
        holder.getNewsTitle().setText(this.elementsList.get(position).getTitle());
        holder.getNewsTime().setText(this.elementsList.get(position).getPubDate());
        holder.getNewsAuthor().setText(this.elementsList.get(position).getAuthor());
        holder.getTextBrief().setText(this.elementsList.get(position).getBrief());
        String imageUrl = this.elementsList.get(position).getImageAvatar();
        if (imageUrl != null && imageUrl.length() > 0 && !imageUrl.equalsIgnoreCase("image")) {
            if (!this.ElementsLoaded.contains(position)) {
                this.ElementsLoaded.add(position);
            }

        }
    }

    @Override
    public int getItemCount() {
        return this.elementsList.size();
    }


}
