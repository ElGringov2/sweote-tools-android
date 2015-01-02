package com.dragonrider.SWEotE.Scenario;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.dragonrider.SWEotE.R;

import java.util.ArrayList;
import java.util.List;

public class activityScenarioViewer extends Activity {

    ScenarioItemAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_scenario_viewer);


        TextScenarioItem item = new TextScenarioItem();
        item.Name = "Reliques";
        item.Text = "Faite un jet de [skillroll id=\"4\" difficulty=\"3\" upgrade=\"1\" boost=\"1\" setback=\"0\"]. En cas de [DESPAIR], les mechants tuent automatiquement les gentils. Sinon, pour chaque [SUCCESS] obtenu, les gentils avancent d'une case, et pour chaque [FAILURE] obtenu, les mechant avancent d'une case. Les [ADVANTAGE] ainsi que les [THREAT] ne servent qu'a regagner ou perdre du stress.";


        List<ScenarioItem> items = new ArrayList<ScenarioItem>();
        items.add(item);
        mainAdapter = new ScenarioItemAdapter(items, this);

        ListView lst = (ListView)findViewById(R.id.listView1);

        lst.setAdapter(mainAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_scenario_viewer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
