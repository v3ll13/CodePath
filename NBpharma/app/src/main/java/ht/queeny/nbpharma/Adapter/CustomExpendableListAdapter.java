package ht.queeny.nbpharma.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import ht.queeny.nbpharma.R;

/**
 * Created by root on 8/16/18.
 */

public class CustomExpendableListAdapter extends BaseExpandableListAdapter {


    private Context context;
    private List<String> listTitle;
    private Map<String,List<String>> listItem;

    public CustomExpendableListAdapter(Context context, List<String> listTitle, Map<String, List<String>> listItem) {
        this.context = context;
        this.listTitle = listTitle;
        this.listItem = listItem;
    }

    @Override
    public int getGroupCount() {
        return listTitle.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listItem.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listItem.get(listTitle.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition    ) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPostion, int childPosition) {
        return groupPostion;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String title =(String)getGroup(groupPosition);

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_group,null);
        }

        TextView txtTitle= (TextView)convertView.findViewById(R.id.list_title);
        txtTitle.setTypeface(null, Typeface.BOLD);
        txtTitle.setText(title);

        return convertView;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getChildView(int groupPosition,int childPosition,  boolean isLastChild, View convertView, ViewGroup parent) {
        String title =(String)getChild(groupPosition, childPosition);

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);
        }

        TextView txtChild = (TextView)convertView.findViewById(R.id.expendableListItem);

        txtChild.setTypeface(null, Typeface.BOLD);
        txtChild.setText(title);


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
