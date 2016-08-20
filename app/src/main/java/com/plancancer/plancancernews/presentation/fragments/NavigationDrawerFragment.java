package com.plancancer.plancancernews.presentation.fragments;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.plancancer.plancancernews.PlanCancerServiceConsumer;
import com.plancancer.R;
import com.plancancer.plancancernews.persistance.model.PlanCancerPostListItem;
import com.plancancer.plancancernews.presentation.adapters.NavDrawerExpListAdapter;
import com.plancancer.plancancernews.service.FragmentsCoordinator;
import com.plancancer.plancancernews.service.ServiceAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class NavigationDrawerFragment extends Fragment implements PlanCancerServiceConsumer {


    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

    private NavigationDrawerCallbacks mCallbacks;

    private android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private ExpandableListView mDrawerListView;
    private View mFragmentContainerView;
    private int mCurrentSelectedPosition = 0;
    NavDrawerExpListAdapter navDrawerExpListAdapter;

    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private HashMap<String, List<PlanCancerPostListItem>> listDataChildItems = new HashMap<String, List<PlanCancerPostListItem>>();
    private FragmentsCoordinator coordinator;


    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;






    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }
    }


    public void setUpContent() {
        this.prepareData();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        this.mDrawerListView = (ExpandableListView) v.findViewById(R.id.drawerList);
        return v;
    }


    private void prepareData() {
        ServiceAccess.getServiceAccessController().getPostListItem(this);
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
    }


    private void selectItem(int position) {
        mCurrentSelectedPosition = position;
        if (mDrawerListView != null) {
            mDrawerListView.setItemChecked(position, true);
        }
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // If the drawer is open, show the global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.
        if (mDrawerLayout != null && isDrawerOpen()) {
            inflater.inflate(R.menu.global, menu);
            showGlobalContextActionBar();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void setCoordinator(FragmentsCoordinator coordinator){
        this.coordinator=coordinator;
    }

    private void showGlobalContextActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(R.string.app_name);
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();

    }

    @Override
    public void consumeService(Object result) {

    }

    @Override

    public void consumeServiceMultiple(List<Object> result) {

        Log.e("consumeServM","consumeServiceMultiple invoked");
        this.listDataChild = new HashMap<String, List<String>>();
        this.listDataHeader = ((ArrayList<String>) result.get(0));
        HashMap<String, List<PlanCancerPostListItem>> map = (HashMap<String, List<PlanCancerPostListItem>>) result.get(1);
        this.listDataChildItems = (HashMap<String, List<PlanCancerPostListItem>>) result.get(1);
        List<String> listChilds = new ArrayList<String>();

        List<PlanCancerPostListItem> lst;
        for (int i = 0; i < listDataHeader.size(); i++) {
            lst = map.get(listDataHeader.get(i));
            Log.e("Header :", "" + listDataHeader.get(i) + "size :" + listDataHeader.size());
            for (int j = 0; j < lst.size(); j++) {
                Log.e("child at : " + j, "" + lst.get(j).getTitle());
                listChilds.add(lst.get(j).getTitle());
            }
            this.listDataChild.put(listDataHeader.get(i), listChilds);
            listChilds = new ArrayList<String>();
            Log.e("Header end", "-----------------------------");
        }


        Log.e("---", "-----------------------------");

        this.navDrawerExpListAdapter = new NavDrawerExpListAdapter(this.getContext(),this.listDataHeader, this.listDataChild);
        this.setCoordinator((FragmentsCoordinator) this.getActivity());
        this.navDrawerExpListAdapter.setListPostLists(map);
        this.mDrawerListView.setAdapter(this.navDrawerExpListAdapter);
        this.mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);
        this.mDrawerListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.e("onChildClick","onChildClick clicked:::group "+groupPosition+"  :child"+childPosition);
                return  handleChildClick(groupPosition,childPosition);
            }
        });
        setHasOptionsMenu(true);
    }

    private boolean handleChildClick(int groupPos,int childPos){
        Log.e("handleChildClick","The link is ::: "+this.listDataChildItems.get(this.listDataHeader.get(groupPos)).get(childPos).getLink());
        if(this.listDataChildItems!=null && this.listDataChildItems.get(this.listDataHeader.get(groupPos))!=null && this.listDataChildItems.get(this.listDataHeader.get(groupPos)).get(childPos)!=null){
            this.coordinator.coordinateHandlingEvent(this.listDataChildItems.get(this.listDataHeader.get(groupPos)).get(childPos).getLink(),
                    this.listDataChildItems.get(this.listDataHeader.get(groupPos)).get(childPos).isComments());
        }

        else{
            Log.e("Group Pos","::"+groupPos);
            Log.e("Child Pos",":: "+childPos);
            Log.e("handleChildClick","On of the params is null");
            Log.e("grandPa","listDataChildItems is null :"+(this.listDataChildItems==null));
            Log.e("Pa","this.listDataChildItems.get is null :"+( this.listDataChildItems.get(this.listDataHeader.get(groupPos))==null));
            Log.e("Me","listDataChildItems.get.get is null :"+( this.listDataChildItems.get(this.listDataHeader.get(groupPos)).get(childPos)==null));
            return false;
        }

        return true;
    }

    public static interface NavigationDrawerCallbacks {

        void onNavigationDrawerItemSelected(int position);
    }
}
