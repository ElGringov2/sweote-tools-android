package com.dragonrider.SWEotE.GroundFightActivities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.dragonrider.SWEotE.Classes.EncounterFile;
import com.dragonrider.SWEotE.Classes.Item;
import com.dragonrider.SWEotE.Classes.NPC.NPCTypes;
import com.dragonrider.SWEotE.Classes.SimpleEncounterFighter;
import com.dragonrider.SWEotE.Classes.Weapon;
import com.dragonrider.SWEotE.R;

import java.util.ArrayList;
import java.util.List;

public class GroundFightPrepareActivity extends Activity {

	
	//public static GroundFightPrepareAdapter mainAdapter;
    public static List<SimpleEncounterFighter> innerFighters;

    private static GroundFightPrepareActivity innerReference;

    public static void UpdateFighterLayout() {
        if (innerReference == null) return;
        innerReference.updateFighterLayout();
    }

    public static EncounterFile File;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ground_fight_prepare);

        innerReference = this;
		//mainAdapter = new GroundFightPrepareAdapter(this);

        //mainAdapter.innerFighters = File.Fighters;
		
		//((ListView)findViewById(R.id.GroundFightPrepareList)).setAdapter(mainAdapter);

        innerFighters = File.Fighters;

        ((EditText)findViewById(R.id.GroundFightPrepareNameEditText)).setText(File.EncounterName);
        ((EditText)findViewById(R.id.GroundFightPrepareNameEditText)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                File.EncounterName = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ((EditText)findViewById(R.id.GroundFightPrepareLocationEditText)).setText(File.Location);
        ((EditText)findViewById(R.id.GroundFightPrepareLocationEditText)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                File.Location = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        ((EditText)findViewById(R.id.GroundFightPrepareAdventureEditText)).setText(File.Adventure);
        ((EditText)findViewById(R.id.GroundFightPrepareAdventureEditText)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                File.Adventure = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ((EditText)findViewById(R.id.GroundFightPrepareCampaignEditText)).setText(File.Campaign);
        ((EditText)findViewById(R.id.GroundFightPrepareCampaignEditText)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                File.Campaign = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        updateFighterLayout();


	}

    private void updateFighterLayout() {
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.GroundFightPrepareList);

        linearLayout.removeAllViews();

        if (File.Fighters == null)
            File.Fighters = new ArrayList<SimpleEncounterFighter>();

        for (final SimpleEncounterFighter _fighter : File.Fighters)
        {
            View convertView = LayoutInflater.from(this).inflate(R.layout.listitem_groundfight_prepare, linearLayout, false);


            ImageView imageView = (ImageView)convertView.findViewById(R.id.GroundFighterItemImage);
            EditText textView = (EditText)convertView.findViewById(R.id.GroundFighterItemName);
            TextView typeView = (TextView)convertView.findViewById(R.id.GroundFightPrepareNPCTypeTextView);
            final TextView woundTextView = (TextView)convertView.findViewById(R.id.GroundFighterItemStartingWoundsValue);
            final TextView strainTextView = (TextView)convertView.findViewById(R.id.GroundFighterItemStartingStrainValue);
            SeekBar seekBarWounds = (SeekBar)convertView.findViewById(R.id.GroundFighterItemStartingWoundsSeekBar);
            SeekBar seekBarStrain = (SeekBar)convertView.findViewById(R.id.GroundFighterItemStartingStrainSeekBar);
            LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.GroundFighterItemStartingStrainInfos);
            Spinner spinner = (Spinner)convertView.findViewById(R.id.GroundFighterItemStartingWeaponSpinner);

            final ImageButton imageButton = (ImageButton)convertView.findViewById(R.id.GroundFighterItemImageButton);



            if (_fighter.getBaseNPC().getImage() != null)
                imageView.setImageBitmap(_fighter.getBaseNPC().getImage());



            typeView.setText(R.string.minion);
            if (_fighter.getBaseNPC().Type == NPCTypes.Rival)
                typeView.setText(R.string.rival);
            if (_fighter.getBaseNPC().Type == NPCTypes.Nemesis)
                typeView.setText(R.string.nemesis);





            textView.setText(_fighter.Name);
            textView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    _fighter.setAltName(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });



            woundTextView.setText(String.valueOf(_fighter.StartingWounds));

            seekBarWounds.setMax(_fighter.getBaseNPC().TotalWounds);
            seekBarWounds.setProgress(_fighter.StartingWounds);
            seekBarWounds.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {


                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {


                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    _fighter.StartingWounds = progress;
                    woundTextView.setText(String.valueOf(progress));
                }
            });

            if (_fighter.getBaseNPC().Type == NPCTypes.Nemesis) {


                strainTextView.setText(String.valueOf(_fighter.StartingStrain));

                seekBarStrain.setMax(_fighter.getBaseNPC().TotalStrain);
                seekBarStrain.setProgress(_fighter.StartingStrain);
                seekBarStrain.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {


                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {


                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser) {
                        _fighter.StartingStrain = progress;
                        strainTextView.setText(String.valueOf(progress));
                    }
                });

            }
            else
                layout.setVisibility(View.GONE);



            List<String> lst = new ArrayList<String>();
            int weaponPosition = 0;
            lst.add("Aucune");
            Log.i("hopla", _fighter.StartingWeapon);

            for (Item item : _fighter.getBaseNPC().Items)
            {
                Log.i("hopla", item.Name);
                if (item.getClass() != Weapon.class) continue;

                if (item.Name.equals(_fighter.StartingWeapon))
                    weaponPosition = lst.size();
                lst.add(item.Name);
            }



            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lst);
            spinner.setAdapter(adapter);
            spinner.setSelection(weaponPosition);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    _fighter.StartingWeapon = adapter.getItem(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final ListPopupWindow listPopupWindow = new ListPopupWindow(GroundFightPrepareActivity.this);
                    String[] stringList = {GroundFightPrepareActivity.this.getString(R.string.remove)};
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GroundFightPrepareActivity.this, android.R.layout.simple_list_item_1, stringList);
                    listPopupWindow.setAdapter(arrayAdapter);
                    listPopupWindow.setWidth(300);

                    listPopupWindow.setContentWidth(300);
                    listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            if (i == 0) {
                                innerFighters.remove(_fighter);
                                updateFighterLayout();
                                listPopupWindow.dismiss();
                            }
                        }
                    });

                    listPopupWindow.setAnchorView(imageButton);
                    listPopupWindow.setModal(true);
                    listPopupWindow.show();
                }
            });


            linearLayout.addView(convertView);


        }
    }


    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ground_fight_prepare, menu);
		return true;
	}
	
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		
		if (item.getItemId() == R.id.GroundFightPrepareMenuItem_AddFighter) {
			Intent intent = new Intent(this, AddFightersActivity.class);
            intent.putExtra("EDITOR", true);

            startActivity(intent);
			return true;
		}
        if (item.getItemId() == R.id.GroundFightPrepareMenuItem_SaveEncounter) {

            File.Fighters = innerFighters;

            File.Save();
        }
		return super.onMenuItemSelected(featureId, item);
		
	} 
	
//
//
//	public class GroundFightPrepareAdapter extends BaseAdapter  {
//
//		private List<SimpleEncounterFighter> innerFighters = new ArrayList<SimpleEncounterFighter>();
//
//		private Context actualContext;
//
//
//		public GroundFightPrepareAdapter(Context context) {
//			this.actualContext = context;
//
//
//
//
//		}
//
//
//		@Override
//		public int getCount() {
//			return innerFighters.size();
//		}
//
//		@Override
//		public Object getItem(int position) {
//			return innerFighters.get(position);
//		}
//
//		@Override
//		public long getItemId(int position) {
//			return position;
//		}
//
//        class SimpleFighterViewHolder {
//            ImageView imageView;
//            TextView textView;
//            TextView woundTextView;
//            TextView strainTextView;
//            SeekBar seekBarWounds;
//            SeekBar seekBarStrain;
//            LinearLayout layout;
//            Spinner spinner;
//        }
//
//		@Override
//		public View getView(final int position, View convertView, ViewGroup parent) {
//
//            final SimpleFighterViewHolder holder;
//
//            if (convertView == null)
//                convertView = LayoutInflater.from(actualContext).inflate(R.layout.listitem_groundfight_prepare, parent, false);
//
//
//
//
//
//            if (convertView.getTag() == null) {
//                holder = new SimpleFighterViewHolder();
//                holder.imageView = (ImageView)convertView.findViewById(R.id.GroundFighterItemImage);
//                holder.textView = (TextView)convertView.findViewById(R.id.GroundFighterItemName);
//                holder.woundTextView = (TextView)convertView.findViewById(R.id.GroundFighterItemStartingWoundsValue);
//                holder.strainTextView = (TextView)convertView.findViewById(R.id.GroundFighterItemStartingStrainValue);
//                holder.seekBarWounds = (SeekBar)convertView.findViewById(R.id.GroundFighterItemStartingWoundsSeekBar);
//                holder.seekBarStrain = (SeekBar)convertView.findViewById(R.id.GroundFighterItemStartingStrainSeekBar);
//                holder.layout = (LinearLayout) convertView.findViewById(R.id.GroundFighterItemStartingStrainInfos);
//                holder.spinner = (Spinner)convertView.findViewById(R.id.GroundFighterItemStartingWeaponSpinner);
//
//
//                convertView.setTag(holder);
//            }
//            else
//                holder = (SimpleFighterViewHolder) convertView.getTag();
//
//            final SimpleEncounterFighter _fighter = (SimpleEncounterFighter)getItem(position);
//
//
//			if (_fighter.getBaseNPC().getImage() != null) {
//
//				holder.imageView.setImageBitmap(_fighter.getBaseNPC().getImage());
//
//			}
//
//			String fighterName = _fighter.Name;
//
//			if (_fighter.getBaseNPC().Type == NPCTypes.Minion)
//				fighterName = String.format("[X]x%s",
//						fighterName);
//
//
//			holder.textView.setText(fighterName);
//
//
//
//			holder.woundTextView.setText(String.valueOf(_fighter.StartingWounds));
//
//			holder.seekBarWounds.setMax(_fighter.getBaseNPC().TotalWounds);
//			holder.seekBarWounds.setProgress(_fighter.StartingWounds);
//            holder.seekBarWounds.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//
//                @Override
//                public void onStopTrackingTouch(SeekBar seekBar) {
//
//
//                }
//
//                @Override
//                public void onStartTrackingTouch(SeekBar seekBar) {
//
//
//                }
//
//                @Override
//                public void onProgressChanged(SeekBar seekBar, int progress,
//                                              boolean fromUser) {
//                    ((SimpleEncounterFighter) getItem(position)).StartingWounds = progress;
//                    holder.woundTextView.setText(String.valueOf(progress));
//                }
//            });
//
//			if (_fighter.getBaseNPC().Type == NPCTypes.Nemesis) {
//
//
//				holder.strainTextView.setText(String.valueOf(_fighter.StartingStrain));
//
//				holder.seekBarStrain.setMax(_fighter.getBaseNPC().TotalStrain);
//                holder.seekBarStrain.setProgress(_fighter.StartingStrain);
//                holder.seekBarStrain.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//
//                    @Override
//                    public void onStopTrackingTouch(SeekBar seekBar) {
//
//
//                    }
//
//                    @Override
//                    public void onStartTrackingTouch(SeekBar seekBar) {
//
//
//                    }
//
//                    @Override
//                    public void onProgressChanged(SeekBar seekBar, int progress,
//                                                  boolean fromUser) {
//                        ((SimpleEncounterFighter) getItem(position)).StartingStrain = progress;
//                        holder.strainTextView.setText(String.valueOf(progress));
//                    }
//                });
//
//			}
//			else
//				holder.layout.setVisibility(View.GONE);
//
//
//
//			List<String> lst = new ArrayList<String>();
//			int weaponPosition = 0;
//			lst.add("Aucune");
//			for (Item item : _fighter.getBaseNPC().Items)
//	        {
//	        	if (item.getClass() != Weapon.class) continue;
//	        	if (item.Name.equals(_fighter.StartingWeapon))
//	        		weaponPosition = _fighter.getBaseNPC().Items.indexOf(item);
//	            lst.add(item.Name);
//	        }
//
//
//
//			final ArrayAdapter<String> adapter = new ArrayAdapter<String>(actualContext, android.R.layout.simple_spinner_item, lst);
//			holder.spinner.setAdapter(adapter);
//            holder.spinner.setSelection(weaponPosition);
//
//            holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                    _fighter.StartingWeapon = adapter.getItem(i);
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> adapterView) {
//
//                }
//            });
//
//
//
//
//			return convertView;
//		}
//
//
//        public void addFighter(String npcName, int count) {
//            Database db = new Database(App.getContext());
//            NPC base = db.GetNPCbyName(npcName);
//
//            for (int i = 0; i < count; i++)
//            {
//
//                SimpleEncounterFighter fighter = new SimpleEncounterFighter();
//                fighter.setBaseNPC(base);
//
//
//                //mainAdapter.innerFighters.add(fighter);
//
//            }
//
//
//
//            notifyDataSetChanged();
//        }
//    }

}
