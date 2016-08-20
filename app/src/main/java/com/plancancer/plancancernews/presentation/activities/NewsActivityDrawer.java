package com.plancancer.plancancernews.presentation.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.plancancer.plancancernews.IPrincipal;
import com.plancancer.R;
import com.plancancer.plancancernews.persistance.database.PlanCancerDatabaseHelper;
import com.plancancer.plancancernews.persistance.model.NewsElements;
import com.plancancer.plancancernews.persistance.model.PlanCancerAccount;
import com.plancancer.plancancernews.presentation.adapters.NewsPagerAdapter;
import com.plancancer.plancancernews.presentation.fragments.HomeFragment;
import com.plancancer.plancancernews.presentation.fragments.NavigationDrawerFragment;
import com.plancancer.plancancernews.service.FragmentsCoordinator;

import java.util.ArrayList;



public class NewsActivityDrawer extends AppCompatActivity implements IPrincipal,FragmentsCoordinator {

    private PlanCancerDatabaseHelper databaseHelper;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    //private NewsCardsFragmentNative cardsFragmentNative;
    private Toolbar toolbar;
    private boolean userLogged;
    private TabLayout tabLayout;
    private TabLayout.Tab[] tabsArray = new TabLayout.Tab[4];
    private ViewPager viewPager;
    private BottomSheetBehavior bottomSheetBehavior;


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_activity_drawer_home);
        this.setTitle("");

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        viewPager = ((ViewPager) this.findViewById(R.id.newsViewPager));
        viewPager.setAdapter(new NewsPagerAdapter(this, getSupportFragmentManager(), NewsActivityDrawer.this));
        tabLayout = ((TabLayout) this.findViewById(R.id.newsTab));
        tabLayout.setupWithViewPager(viewPager);
        this.styleTabs();
        PlanCancerAccount.getAccount("","");
        this.mNavigationDrawerFragment.setUpContent();

    }


    private void styleTabs() {
        tabLayout.getTabAt(0).setIcon(R.drawable.pc_home);
        if (!this.userLogged)
            tabLayout.getTabAt(1).setIcon(R.drawable.pc_logg);
        else {
            tabLayout.getTabAt(1).setIcon(R.drawable.pc_comment);
            tabLayout.getTabAt(2).setIcon(R.drawable.pc_post);
            tabLayout.getTabAt(3).setIcon(R.drawable.pc_contrib);
            tabLayout.getTabAt(4).setIcon(R.drawable.pc_search);
        }

    }


    public void handleUserLogged() {
        //this.tabLayout.removeTab(this.tabLayout.getTabAt(1));
        this.userLogged = true;
        this.showTabs();
        this.styleTabs();
        PlanCancerAccount.setUserLogged(true);
        Log.e("Activity","hadleUserLoggedMethod : the user is :"+PlanCancerAccount.isUserAuth());
        HomeFragment.getCurrentInstance().showFABMenu();
    }

    private void hideTabs() {
        for (int i = 2; i < 5; i++) {
            if (tabsArray.length == 0)
                this.tabsArray[i - 2] = this.tabLayout.getTabAt(i);
            this.tabLayout.removeTab(this.tabsArray[i - 2]);
        }


    }


    private void prepareBottomSheet(){
        View BottomSheet=this.findViewById(R.id.bottom_sheet);
        this.bottomSheetBehavior=BottomSheetBehavior.from(BottomSheet);
        //
        // this.bottomSheetBehavior.setPeekHeight(300);
        this.bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }


    private void showTabs() {
        this.viewPager.removeViewAt(1);
        ((NewsPagerAdapter) this.viewPager.getAdapter()).notifyChangeInPosition(1);
        this.viewPager.getAdapter().notifyDataSetChanged();
        this.tabLayout.removeTabAt(1);
        this.tabLayout.setupWithViewPager(this.viewPager);
    }


    public boolean isUsersLogged() {
        return this.userLogged;
    }


    @Override
    protected void onResume() {
        super.onResume();
//        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //      AppEventsLogger.deactivateApp(this);
    }


    public ArrayList<NewsElements> getContent() {
        return null;
    }

    public void showNewsDetail(NewsElements elements) {

    }

    public void showNewsDetailNShare(NewsElements elements) {

    }


    @Override
    public void coordinateHandlingEvent(Object o,Object obj) {
       // if(HomeFragment.getCurrentInstance()!=null)
        Log.e("coordinator","coordinator called");
            HomeFragment.getCurrentInstance().changeCurrentUrl((String)o,(Boolean)obj);
    }

    public void goToPost(String postId) {
        HomeFragment.getCurrentInstance().changeCurrentUrl(postId,true);
        this.viewPager.setCurrentItem(0,true);
        this.tabLayout.invalidate();
    }
}

