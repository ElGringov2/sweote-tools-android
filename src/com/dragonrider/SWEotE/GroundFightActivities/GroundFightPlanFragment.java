package com.dragonrider.SWEotE.GroundFightActivities;



import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dragonrider.SWEotE.Classes.GroundFightScene;
import com.dragonrider.SWEotE.Classes.GroundFighter;
import com.dragonrider.SWEotE.Classes.MoveableView;
import com.dragonrider.SWEotE.R;


public class GroundFightPlanFragment extends Fragment {



    FrameLayout _mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        _mainView = (FrameLayout)inflater.inflate(R.layout.fragment_ground_fight_plan, container, false);

        GroundFightScene.FighterListChanged = Refresh;



        return _mainView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.ground_fight_plan, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_cast_button) {




            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    GroundFightScene.INotifyFighterListChanged Refresh = new GroundFightScene.INotifyFighterListChanged() {
        @Override
        public void RunWhenFighterListChanged() {




            _mainView.removeAllViews();


            for (final GroundFighter fighter : GroundFightScene.getFighters()) {
                MoveableView view;



                if (fighter.getBase() == null)
                {
                    view = new MoveableView(GroundFightPlanFragment.this.getActivity(), fighter.X, fighter.Y, getActivity().getResources().getDrawable(R.drawable.human), 0x33333333 );
                }
                else
                    view = new MoveableView(GroundFightPlanFragment.this.getActivity(), fighter.X, fighter.Y, new BitmapDrawable(getResources(), fighter.getBase().getImage()), fighter.getColor().getColor());



                view.setMoveListener(new MoveableView.IEndMove() {
                    @Override
                    public void NewPos(int X, int Y) {
                        fighter.setCoord(X, Y);
                    }
                });





                _mainView.addView(view);

            }








            _mainView.invalidate();
        }
    };

}
