package com.dragonrider.SWEotE.Classes;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dragonrider.SWEotE.R;

public class SWListBoxItemAdapter extends BaseAdapter {

	private List<SWListBoxItem> data;
	private Context context;



	public SWListBoxItemAdapter(Context context, List<SWListBoxItem> data) {
		this.data = data;
		this.context = context;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		SWListBoxItem item = (SWListBoxItem) getItem(position);
		
		LayoutInflater infalInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = infalInflater.inflate(R.layout.listitem_swlistitem, null);

        //android.util.Log.d("test", item.getName());

		
		((TextView)convertView.findViewById(R.id.swListItem_Title)).setText(item.getName());
		
		if (item.getDesc().isEmpty())
			((TextView)convertView.findViewById(R.id.swListItem_Desc)).setVisibility(View.GONE);
		else
			((TextView)convertView.findViewById(R.id.swListItem_Desc)).setText(item.getDesc());


		
		if (item.getImage() != null) 
			((ImageView)convertView.findViewById(R.id.swListItem_Image)).setImageBitmap(item.getImage());
		else
			((ImageView)convertView.findViewById(R.id.swListItem_Image)).setVisibility(View.GONE);
		
		
		
		return convertView;

	}

}
