package com.plancancer.plancancernews.presentation.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.baoyz.widget.PullRefreshLayout;
import com.plancancer.R;
import com.plancancer.plancancernews.presentation.adapters.NewsRecycleViewAdapter_Posts;
import com.plancancer.plancancernews.core_services.applications.NewsApplication;
import com.plancancer.plancancernews.persistance.model.NewsElements;
import com.plancancer.plancancernews.persistance.model.NewsPostElements;

import java.util.ArrayList;

/**
 * Created by Yazid on 01/04/2016.
 */
public class PostsFragments extends Fragment implements PullRefreshLayout.OnRefreshListener  {

    public static final String ARG_PAGE = "POSTS_PAGE";

    private int mPage=3;

    private com.baoyz.widget.PullRefreshLayout refreshLayout;
    private double rNumber;
    private ArrayList<NewsPostElements> newsList;
    private NewsRecycleViewAdapter_Posts mCardAdapter;
    private RecyclerView newsListV;
    RequestQueue newsRequestQueue;

    private View inflatedRootView;


    public static PostsFragments newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PostsFragments fragment = new PostsFragments();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflatedRootView = inflater.inflate(R.layout.fragment_news_cards_native, container, false);
        return this.inflatedRootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //this.initRefreshLayout();
        //this.initCardsList();
        ArrayList<NewsPostElements> list=new ArrayList<NewsPostElements>();// = ((NewsActivityDrawer) this.getActivity()).getContent();
        if (list.size() == 0) {
            Resources resources = NewsApplication.getNewsContext().getResources();
            String title="La Contamination du Virus Zika dans la Zone d'Afrique";
            String author="Pr.Zittouni";
            String time="15/03/2016 00:53";

            list.add(new NewsPostElements(title,time,"",author,"it's a brief form cancer plan app","",""));
        }

        this.mCardAdapter = new NewsRecycleViewAdapter_Posts(list, this);
        this.newsListV = ((RecyclerView) this.getActivity().findViewById(R.id.recycleList));
        this.newsListV.setAdapter(this.mCardAdapter);
        this.newsListV.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        this.initRefreshLayout();

        //this.newsRequestQueue = VolleySingleton.getInstance().getRequestQueue();
        //this.createFloatingMenu();
    }


    private void initRefreshLayout() {
        this.refreshLayout = (PullRefreshLayout) this.inflatedRootView.findViewById(R.id.swiperNewsNative);//this.getActivity().findViewById(R.id.swiperNewsNative);
        this.refreshLayout.setOnRefreshListener(this);
    }


    @Override
    public void onRefresh() {
        Log.e("Refresh", "hello ref");
        long scrollPosition = ((LinearLayoutManager) newsListV.getLayoutManager())
                .findFirstCompletelyVisibleItemPosition();
        //Uncomment call
        parseNewPosts();
        refreshLayout.setRefreshing(false);
    }


    private void parseNewPosts(){
        Snackbar.make(this.inflatedRootView, "News Showed",Snackbar.LENGTH_SHORT).show();
    }


    public void parseNews() {
        boolean ok = false;
        NewsElements elem;

       /* SyndContent content;
        try {

            URL feedUrl = new URL("http://www.jiltarjih.org/ar/?feed=rss2");
            SyndFeedInput input = new SyndFeedInput();
            SyndEntry entry;
            SyndFeed feed = input.build(new XmlReader(feedUrl));
            ok = true;

            for (int i = 0; i < feed.getEntries().size(); i++) {
                entry = (SyndEntry) feed.getEntries().get(i);
                 mCardAdapter.addAnotherElement(new NewsElements(""+entry.getTitleEx().getValue()," "," ", ""+((SyndContent) entry.getContents().get(0)).getValue()));
            }


        } catch (Exception e) {
            Log.e("Feed Error", "" + e.getMessage());
        }

        if (!ok) {
            Log.e("FeedError", "Error");
        }*/


    }


}
