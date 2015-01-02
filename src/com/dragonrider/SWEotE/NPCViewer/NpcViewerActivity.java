package com.dragonrider.SWEotE.NPCViewer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import com.dragonrider.SWEotE.Classes.Database;
import com.dragonrider.SWEotE.Classes.SWGroupListBoxItemAdapter;
import com.dragonrider.SWEotE.Classes.SWListBoxItem;
import com.dragonrider.SWEotE.R;

import java.util.List;

public class NpcViewerActivity extends Activity {

    SWGroupListBoxItemAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npc_viewer);



        ExpandableListView baseListView = (ExpandableListView) findViewById(R.id.MainListView);

        Database db = new Database(this);


        List<SWListBoxItem> lst1 = db.GetNPCShortList();
        db.close();


        adapter1 = new SWGroupListBoxItemAdapter(this, lst1);

        baseListView.setAdapter(adapter1);
        baseListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {


                String npcName = ((SWListBoxItem) adapter1.getChild(groupPosition, childPosition)).getName();
                npcName = npcName.replace(NpcViewerActivity.this.getString(R.string.minion), "").replace(NpcViewerActivity.this.getString(R.string.nemesis), "").replace(NpcViewerActivity.this.getString(R.string.rival), "");
                npcName = npcName.replace(" []", "");

                ShowNPCActivity.loadFromNPCName(NpcViewerActivity.this, npcName);


                return true;


            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.npc_viewer_activity_menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter1.setFilter(s);
                adapter1.notifyDataSetChanged();
                return true;
            }
        });

        return true;
    }


}
