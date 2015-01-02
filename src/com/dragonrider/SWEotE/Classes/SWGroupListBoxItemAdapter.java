package com.dragonrider.SWEotE.Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dragonrider.SWEotE.R;

public class SWGroupListBoxItemAdapter extends BaseExpandableListAdapter {

    private String filter = "";
	
	private Context context;
	
	private HashMap<String, List<SWListBoxItem>> _listDataChild;
	
	private List<String> _listDataHeader = new ArrayList<String>();

	public SWGroupListBoxItemAdapter(Context context, List<SWListBoxItem> data) {

		this.context = context;
		
		
		_listDataChild = new HashMap<String, List<SWListBoxItem>>();
		
		for (SWListBoxItem item : data)
		{
			if (_listDataChild.containsKey(item.getCategory())) 
			{
				_listDataChild.get(item.getCategory()).add(item);
			}
			else 
			{
				_listDataChild.put(item.getCategory(), new ArrayList<SWListBoxItem>());
				_listDataChild.get(item.getCategory()).add(item);
				_listDataHeader.add(item.getCategory());
			}
		}
		
		
	}
	
	@Override
	public int getGroupCount() {
		return _listDataHeader.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
        List<SWListBoxItem> group = _listDataChild.get(_listDataHeader.get(groupPosition));
        int iSize = 0;
        for (SWListBoxItem item : group)
            if (ShowChild(item)) iSize ++;
		return iSize;
	}

	@Override
	public Object getGroup(int groupPosition) {
        List<SWListBoxItem> group = _listDataChild.get(_listDataHeader.get(groupPosition));
        List<SWListBoxItem> tempGroup = new ArrayList<SWListBoxItem>();
        for (SWListBoxItem item : group)
            if (ShowChild(item)) tempGroup.add(item);

        return tempGroup;
		
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {

        return ((List<SWListBoxItem>)getGroup(groupPosition)).get(childPosition);
	}

    private boolean ShowChild(SWListBoxItem item) {
        return filter.equals("") || item.getName().toLowerCase().contains(filter) || item.getCategory().toLowerCase().contains(filter);

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
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {


        if (getChildrenCount(groupPosition) > 0) {

            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.listitem_swlistitemheader, parent, false);


            ((TextView) convertView).setText(_listDataHeader.get(groupPosition));
            ((TextView) convertView).setTypeface(null, Typeface.BOLD);


        }
        else
            convertView = new View(this.context);

        return convertView;


    }

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		SWListBoxItem item = (SWListBoxItem) getChild(groupPosition, childPosition);
		

        if (item != null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.listitem_swlistitem, parent, false);


            ((TextView) convertView.findViewById(R.id.swListItem_Title)).setText(item.getName());
            ((TextView) convertView.findViewById(R.id.swListItem_Desc)).setText(item.getDesc());


            if (item.getImage() != null) {

                ((ImageView) convertView.findViewById(R.id.swListItem_Image)).setImageBitmap(item.getImage());

            }
        }
        else
            convertView = new View(this.context);

		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		
		return true;
	}

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
