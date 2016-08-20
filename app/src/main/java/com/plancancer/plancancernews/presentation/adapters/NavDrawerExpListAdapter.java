package com.plancancer.plancancernews.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.plancancer.R;
import com.plancancer.plancancernews.persistance.model.PlanCancerPostListItem;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Yazid on 12/03/2016.
 */
public class NavDrawerExpListAdapter extends BaseExpandableListAdapter {


    private Context context;
    private List<String> listDataHeader; // header titles
    private HashMap<String, List<String>> listDataChild;

    private HashMap<String, List<PlanCancerPostListItem>> listPostLists;


    public HashMap<String, List<PlanCancerPostListItem>> getListPostLists() {
        return listPostLists;
    }

    public void setListPostLists(HashMap<String, List<PlanCancerPostListItem>> listPostLists) {
        this.listPostLists = listPostLists;
    }




    private class ExpandViwHolderParent {
    }

    private class ExpandViewHolderChild {
    }




    public NavDrawerExpListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listDataChild) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }





    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) this.getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflaterCompat = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflaterCompat.inflate(R.layout.navdrawer_explist_childs_item, null);
        }
        TextView lblListHeader = (TextView) convertView.findViewById(R.id.navdrawer_explist_child_txtV);
        lblListHeader.setText(childText);
        return convertView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final String groupText = (String) this.getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflaterCompat = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflaterCompat.inflate(R.layout.navdrawer_explist_headers_item, null);
        }
        TextView lblListHeader = (TextView) convertView.findViewById(R.id.navdrawer_explist_header_txtV);
        lblListHeader.setText(groupText);
        return convertView;
    }


    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listDataChild.get(listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
