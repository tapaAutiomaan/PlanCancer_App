package com.plancancer.plancancernews.presentation.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.plancancer.R;
import com.plancancer.plancancernews.persistance.model.PlanCancerAccount;

/**
 * Created by Yazid on 01/04/2016.
 */
public class HomeFragment extends Fragment{

    public static final String ARG_PAGE = "HOME_PAGE";

    private int mPage=1;
    private WebView homeWebView;
    private FloatingActionButton floatingActionsMenu;
    private boolean userAuth=false;
    private  String URL_ROOT="http://plan-cancer.esi.dz/";
    private  String currentUrl="";
    private boolean comments;
    private static HomeFragment singleton;
    private Dialog dialog;
    private EditText addCommentText;
    private Button addCommentConfirm;
    private Button addCommentCancel;
    private String currentPostId="";


    private FirebaseAnalytics mFirebaseAnalytics;


    public static HomeFragment newInstance(int page) {
        Log.e("news Instance","news Instance Invoked");
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        singleton = new HomeFragment();
        singleton.setArguments(args);
        return singleton;
    }

    public static HomeFragment getCurrentInstance(){
        return singleton;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("onCreate","On create invoked");
        mPage = getArguments().getInt(ARG_PAGE);

        /*if(floatingActionsMenu!=null)
            this.showFABMenu();*/
    }


    public void changeCurrentUrl(String url,Boolean comments){
        //this.homeWebView.loadUrl(this.URL_ROOT+url);
        this.currentUrl=url;
        this.currentPostId=url;
        Log.e("cheCurntUrl","the current url is :"+url);
        this.comments=comments;
        Log.e("Comments","they ares ::: "+comments);
        if(this.comments)
            this.floatingActionsMenu.setVisibility(View.VISIBLE);
        else
            this.floatingActionsMenu.setVisibility(View.INVISIBLE);

        //this.homeWebView.loadUrl("http://www.google.dz");
        this.homeWebView.loadUrl(this.URL_ROOT.concat("?service=post&posteid=P").concat(url));
        //this.homeWebView.reload();
    }


    public void showFABMenu(){
        if(this.comments)
            this.floatingActionsMenu.setVisibility(View.VISIBLE);
    }
    public void hideFABMenu(){
        this.floatingActionsMenu.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this.getActivity());

    }


    private void createCommentPopup(){
        this.dialog=new Dialog(this.getContext());
        this.dialog.setContentView(R.layout.add_comment_windows);
        this.dialog.setTitle("Ajout d'un Commentaire");
        //testing
        this.addCommentText=((EditText)this.dialog.findViewById(R.id.editComment)) ;
        this.addCommentConfirm=((Button)this.dialog.findViewById(R.id.addCommentValid))  ;
        this.addCommentConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestAddCommentService();
                mFirebaseAnalytics.setUserProperty("testing",addCommentText.getText().toString());
            }
        });
        this.addCommentCancel=((Button) this.dialog.findViewById(R.id.addCommentCancel)) ;
        this.addCommentCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void requestAddCommentService() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plancancer_home_fragment, container, false);

        this.homeWebView=((WebView)view.findViewById(R.id.homeWebView));
        this.floatingActionsMenu=((FloatingActionButton)view.findViewById(R.id.menu_fab_home));
        this.homeWebView.getSettings().setJavaScriptEnabled(true);
        this.homeWebView.setWebViewClient(new WebViewClient());
        this.homeWebView.loadUrl(this.URL_ROOT.concat(this.currentUrl));


        Log.e("OCV","On create view invoked");
        Log.e("OCV","the stat of user logged : "+PlanCancerAccount.isUserAuth());

        if(!PlanCancerAccount.isUserAuth()){
            this.floatingActionsMenu.setVisibility(View.INVISIBLE);
            Log.e("Auth ","User is not auth onCreateView");
        }
        else {
            if(this.comments)
                this.floatingActionsMenu.setVisibility(View.VISIBLE);
            Log.e("Auth ","User is auth onCreateView");
        }

        this.createCommentPopup();


        this.floatingActionsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


        return view;
    }

}
