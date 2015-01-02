package com.dragonrider.SWEotE.GroundFightActivities;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dragonrider.SWEotE.Classes.GroundFightScene;
import com.dragonrider.SWEotE.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class GroundFightListFragment extends Fragment {




    View MainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MainView = inflater.inflate(R.layout.fragment_ground_fight_list, container, false);

        ListView mainList = (ListView)MainView.findViewById(R.id.GroundFightActivyty_MainList);

        mainList.setAdapter(GroundFightScene.MainAdapter);


        return MainView;
    }


}
