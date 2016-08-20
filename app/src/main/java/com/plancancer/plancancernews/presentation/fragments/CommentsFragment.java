package com.plancancer.plancancernews.presentation.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.plancancer.plancancernews.PlanCancerServiceConsumer;
import com.plancancer.R;
import com.plancancer.plancancernews.core_services.applications.NewsApplication;
import com.plancancer.plancancernews.persistance.model.NewsCommentElements;
import com.plancancer.plancancernews.persistance.model.NewsElements;
import com.plancancer.plancancernews.persistance.model.PlanCancerAccount;
import com.plancancer.plancancernews.persistance.model.PlanCancerComment;
import com.plancancer.plancancernews.presentation.activities.NewsActivityDrawer;
import com.plancancer.plancancernews.presentation.adapters.PCRecycleViewAdapterComments;
import com.plancancer.plancancernews.service.ServiceAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yazid on 01/04/2016.
 */
public class CommentsFragment extends Fragment implements PlanCancerServiceConsumer {
    public static final String ARG_PAGE = "COMMENT_PAGE";

    private int mPage = 2;


    private SwipeRefreshLayout refreshLayout;
    private double rNumber;
    private ArrayList<NewsElements> newsList;
    private PCRecycleViewAdapterComments mCardAdapter;
    private RecyclerView newsListV;
    ArrayList<NewsCommentElements> lisNv = new ArrayList<>();
    private List<PlanCancerComment> commentList;
    RequestQueue newsRequestQueue;


    public static CommentsFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        CommentsFragment fragment = new CommentsFragment();
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
        View view = inflater.inflate(R.layout.plancaner_comments_fragment, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //this.initRefreshLayout();
        //this.initCardsList();
        ArrayList<com.plancancer.plancancernews.persistance.model.NewsCommentElements> list = new ArrayList<>();// = ((NewsActivityDrawer) this.getActivity()).getContent();
        if (list.size() == 0) {
            Resources resources = NewsApplication.getNewsContext().getResources();
            String title = "La Lutte contre le cancer en algérie";
            String author = "Pr.Zittouni";
            String time = "15/03/2016 00:53";
            list.add(new NewsCommentElements(title, time, "", author, "it's a brief form cancer plan app"));
        }

        this.mCardAdapter = new PCRecycleViewAdapterComments(list, this);
        this.mCardAdapter.setCommentsFragment(this);
        this.newsListV = ((RecyclerView) this.getActivity().findViewById(R.id.recycleListCommentsFragment));
        this.newsListV.setAdapter(this.mCardAdapter);
        this.newsListV.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        this.initRefreshLayout();

        //this.newsRequestQueue = VolleySingleton.getInstance().getRequestQueue();
        //this.createFloatingMenu();

    }

    private void initRefreshLayout() {
        this.refreshLayout = (SwipeRefreshLayout) this.getActivity().findViewById(R.id.swiperFragmentComments);
        this.refreshLayout.setColorSchemeColors(R.color.red, R.color.green, R.color.blue);
        this.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e("Refresh", "hello ref");
                long scrollPosition = ((LinearLayoutManager) newsListV.getLayoutManager())
                        .findFirstCompletelyVisibleItemPosition();
                //Uncomment call
                parseNewComments();
                refreshLayout.setRefreshing(false);
            }
        });
    }

/*
    @Override
    public void onRefresh() {

    }
*/

    private void parseNewComments() {
        ServiceAccess.getServiceAccessController().getCommentsService(this);

    }


    private void addParsedComments(List<PlanCancerComment> comments) {
        Log.e("hallo", "i am in the loop ");
        String author = PlanCancerAccount.getCurrentAccount().getNom() + " " + PlanCancerAccount.getCurrentAccount().getPnom();
        Log.e("author:", "" + author);
        Log.e("addParsedComments","");
        PlanCancerComment comment;
        for (int i=0;i<comments.size();i++) {
            comment=comments.get(i);
            Log.e("Comment number : i id" + comments.get(i).getId(), "content :" + comments.get(i).getContent());
            this.mCardAdapter.addAnotherElement(new NewsCommentElements("", comment.getDate(), "", "moi", comment.getContent()));
            this.mCardAdapter.notifyItemInserted(i);
            Log.e("hallo", "i am in the loop " + i);
        }
        //Log.e("list size", "i :" + i);
    }


    //title//pubdate//imageurl//author//brief
    private void prepareList(List<PlanCancerComment> res) {
        lisNv.clear();
        for (int i = 0; i < res.size(); i++) {
            lisNv.add(new NewsCommentElements(res.get(i).getPost(), res.get(i).getDate(), "", "", res.get(i).getContent()));
        }
    }


    public void goToPost(long id) {
        if(this.commentList!=null)
            ((NewsActivityDrawer)this.getActivity()).goToPost(this.commentList.get((int) id).getPost());
        else
            Log.e("goToPost","commentList is null ::");

    }


    @Override
    public void consumeService(Object result) {

        //if(((List<PlanCancerComment>)result).size()>0)
        //this.mCardAdapter.clearInnerList();
        this.mCardAdapter.clearData();
        /*this.newsListV.getRecycledViewPool().clear();
        this.newsListV.invalidate();*/
        //this.newsListV.setAdapter(null);
        //this.newsListV.setAdapter(this.mCardAdapter);
//        prepareList(((List<PlanCancerComment> )result));
        //      this.mCardAdapter=new PCRecycleViewAdapterComments(lisNv,this);
        //    this.newsListV.setAdapter(this.mCardAdapter);
        //  this.newsListV.invalidate();
        //this.mCardAdapter.notifyItemInserted(0);
        int test = ((List<PlanCancerComment>) result).size();
        Log.e("size:", "the size of the list is :" + test);
        Log.e("size:", "the size of the list is :" + test);
        Log.e("size:", "the size of the list is :" + test);
        this.commentList=((List<PlanCancerComment>) result);
        this.addParsedComments(((List<PlanCancerComment>) result));
    }

    @Override
    public void consumeServiceMultiple(List<Object> result) {
        this.addParsedComments(((List<PlanCancerComment>) result.get(0)));
    }
}
