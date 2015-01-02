package com.dragonrider.SWEotE.Scenario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dragonrider.SWEotE.Classes.XmlImport;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class ScenarioItemAdapter extends BaseAdapter {


    public static ScenarioItemAdapter getFromFile(String filename, Context context) {


        return new ScenarioItemAdapter(XmlImport.ImportScenarioFile(filename), context);



    }



    private List<ScenarioItem> Items;

    private Context context;

    public ScenarioItemAdapter(List<ScenarioItem> items, Context context) {
        Items = items;
        this.context = context;
    }


    @Override
    public int getCount() {
        return Items.size();
    }

    @Override
    public Object getItem(int i) {
        return Items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return Items.get(i).getView(LayoutInflater.from(context), viewGroup);
    }
}
