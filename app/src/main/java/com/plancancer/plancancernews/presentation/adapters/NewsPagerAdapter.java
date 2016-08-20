package com.plancancer.plancancernews.presentation.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.plancancer.plancancernews.IPrincipal;
import com.plancancer.plancancernews.presentation.fragments.CommentsFragment;
import com.plancancer.plancancernews.presentation.fragments.ContributionsFragment;
import com.plancancer.plancancernews.presentation.fragments.FindFragment;
import com.plancancer.plancancernews.presentation.fragments.HomeFragment;
import com.plancancer.plancancernews.presentation.fragments.LoggFragment;
import com.plancancer.plancancernews.presentation.fragments.PostsFragments;

/**
 * Created by Yazid on 02/04/2016.
 */
public class NewsPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    final int PAGE_COUNT_logged = 5;
    private String tabTitles[] = new String[] { "Acceuil","Authentification"};
    private String tabTitlesLogged[] = new String[] { "Acceuil", "Commentaires", "Postes", "Contribution", "Recherche" };

    IPrincipal principal;
    private Context context;

    private long baseId = 0;



    public NewsPagerAdapter(IPrincipal principal,FragmentManager fm, Context context) {
        super(fm);
        this.principal=principal;
        this.context = context;
    }




    @Override
    public int getCount() {
        if(this.principal.isUsersLogged())
            return PAGE_COUNT_logged;
        else
            return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if(this.principal.isUsersLogged()) {
            switch (position) {

                case 0:
                    return HomeFragment.newInstance(position + 1);
                case 1:
                    return CommentsFragment.newInstance(position + 1);
                case 2:
                    return PostsFragments.newInstance(position + 1);
                case 3:
                    return ContributionsFragment.newInstance(position + 1);
                case 4:
                    return FindFragment.newInstance(position + 1);
            }
        }
        else {
            switch (position) {
                case 0:
                    return HomeFragment.newInstance(position + 1);
                case 1:
                    if(principal.isUsersLogged())
                            return CommentsFragment.newInstance(position + 1);
                        else
                            return LoggFragment.newInstance(position + 1);
            }
        }
        //return HomeFragment.newInstance(position + 1);
        return null;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(this.principal.isUsersLogged())
            return "";//tabTitlesLogged[position];
        else
            return tabTitles[position];

    }



    //this is called when notifyDataSetChanged() is called
    @Override
    public int getItemPosition(Object object) {
        // refresh all fragments when data set changed
        return PagerAdapter.POSITION_NONE;
    }


    @Override
    public long getItemId(int position) {
        // give an ID different from position when position has been changed
        return baseId + position;
    }



    public void notifyChangeInPosition(int n) {
        // shift the ID returned by getItemId outside the range of all previous fragments
        baseId += getCount() + n;
    }



}
