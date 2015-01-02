package com.dragonrider.SWEotE.EditorNPC;

import java.io.FileNotFoundException;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.Classes.Armor;
import com.dragonrider.SWEotE.Classes.Database;
import com.dragonrider.SWEotE.Classes.Item;
import com.dragonrider.SWEotE.Classes.NPC;
import com.dragonrider.SWEotE.Classes.NPC.NPCTypes;
import com.dragonrider.SWEotE.Classes.Skill;
import com.dragonrider.SWEotE.Classes.Talent;
import com.dragonrider.SWEotE.Classes.Util;
import com.dragonrider.SWEotE.Classes.Weapon;
import com.dragonrider.SWEotE.EditorNPC.AbilitiesAlertDialog.IAbilityUpdate;
import com.dragonrider.SWEotE.EditorNPC.ItemEditor.IItemUpdate;
import com.dragonrider.SWEotE.EditorNPC.SkillAlertDialog.ISkillUpdate;
import com.dragonrider.SWEotE.EditorNPC.TalentsAlertDialog.ITalentUpdate;

public class NPCEditorActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_npceditor);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(String NPCName) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container, NPCEditorFragment.newInstance(NPCName))
				.commit();
	}

	public void onSectionAttached(String Title) {
		mTitle = Title;
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.npceditor, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.menuNpcEditorSaveNPC) {
			NPCEditorFragment frag = (NPCEditorFragment) getFragmentManager()
					.findFragmentById(R.id.container);
			frag.SaveNPC();
			mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
					.findFragmentById(R.id.navigation_drawer);

			mNavigationDrawerFragment.RefreshList();
			return true;
		}
		if (id == R.id.menuNpcEditorAddNPC) {
			
			final EditText npcNameText = new EditText(this);
			new AlertDialog.Builder(this)
			.setView(npcNameText)
			.setTitle(R.string.npceditor_newnpctitle)
			.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					String npcName = npcNameText.getText().toString();
					Database db = new Database(NPCEditorActivity.this);
					if (db.GetNPCbyName(npcName) != null) {
						dialog.cancel();
						new AlertDialog.Builder(NPCEditorActivity.this)
						.setMessage(R.string.npceditor_npcexist)
						.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
								
							}
						})
						.show();
					}
					else {


						if (!npcName.isEmpty()) {
							NPC npc = new NPC();
							npc.Name = npcName;
							npc.Key = npcName.replace(" ", "");
							db.AddNPC(npc);
							db.close();
							FragmentManager fragmentManager = getFragmentManager();
							fragmentManager
									.beginTransaction()
									.replace(R.id.container, NPCEditorFragment.newInstance(npcName))
									.commit();
						}
						else 
							dialog.cancel();
					}
					
					
				}
			})
			.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
					
				}
			})
			.show();

			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent imageReturnedIntent) {
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

	}

	public static class NPCEditorFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_NPC_NAME = "npc_name";

		public static NPCEditorFragment newInstance(String NPCName) {
			NPCEditorFragment fragment = new NPCEditorFragment();
			Bundle args = new Bundle();
			args.putString(ARG_NPC_NAME, NPCName);
			fragment.setArguments(args);
			return fragment;
		}
		


		public void UpdateImage(Bitmap yourSelectedImage) {
			npc.setImage(yourSelectedImage);

			ImageButton icon = (ImageButton) rootView
					.findViewById(R.id.NPCEditorImageButton);
			icon.setImageBitmap(npc.getImage());

		}

		NPC npc;

		public NPCEditorFragment() {

		}

		public void SaveNPC() {

			Database db = new Database(getActivity());
			db.SaveNPC(npc);
			Toast.makeText(getActivity(), "Enregistré", Toast.LENGTH_LONG)
					.show();
			
			db.close();
		}

		View rootView;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			if (getArguments().containsKey(ARG_NPC_NAME)) {
				String npcName = getArguments().getString(ARG_NPC_NAME);
				Database db = new Database(getActivity());
				npc = db.GetNPCbyName(npcName);
				db.close();
			}


			if (npc == null) {
				return new LinearLayout(getActivity());
			}

			rootView = inflater.inflate(R.layout.fragment_npceditor, container,
					false);

			((TextView) rootView.findViewById(R.id.NPCEditorCharName))
					.setText(npc.Name);
			ImageButton icon = (ImageButton) rootView
					.findViewById(R.id.NPCEditorImageButton);
			icon.setImageBitmap(npc.getImage());

			icon.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
					photoPickerIntent.setType("image/*");
					startActivityForResult(photoPickerIntent, 100);
				}
			});

			EditText text = (EditText) rootView
					.findViewById(R.id.NPCEditorCategory);
			text.setText(npc.Category);
			text.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					if (s.toString().isEmpty())
						return;
					npc.Category = s.toString();

				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {

				}

				@Override
				public void afterTextChanged(Editable s) {

				}
			});

			text = (EditText) rootView.findViewById(R.id.NPCEditorDescription);
			text.setText(npc.Description);
			text.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					if (s.toString().isEmpty())
						return;
					npc.Description = s.toString();

				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {

				}

				@Override
				public void afterTextChanged(Editable s) {

				}
			});

			// final RadioButton minionRadio =
			// (RadioButton)rootView.findViewById(R.id.NPCEditorMinion);
			// final RadioButton rivalRadio =
			// (RadioButton)rootView.findViewById(R.id.NPCEditorRival);
			// final RadioButton nemesisRadio =
			// (RadioButton)rootView.findViewById(R.id.NPCEditorNemesis);

			if (npc.Type == NPCTypes.Minion)
				((RadioButton) rootView.findViewById(R.id.NPCEditorMinion))
						.setChecked(true);
			((RadioButton) rootView.findViewById(R.id.NPCEditorMinion))
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							if (isChecked)
								npc.Type = NPCTypes.Minion;

						}
					});

			if (npc.Type == NPCTypes.Rival)
				((RadioButton) rootView.findViewById(R.id.NPCEditorRival))
						.setChecked(true);
			((RadioButton) rootView.findViewById(R.id.NPCEditorRival))
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							if (isChecked)
								npc.Type = NPCTypes.Rival;

						}
					});

			if (npc.Type == NPCTypes.Nemesis)
				((RadioButton) rootView.findViewById(R.id.NPCEditorNemesis))
						.setChecked(true);
			((RadioButton) rootView.findViewById(R.id.NPCEditorNemesis))
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							if (isChecked)
								npc.Type = NPCTypes.Nemesis;

						}
					});

			String[] chars = { "Brawn", "Presence", "Intellect", "Cunning",
					"Agility", "Willpower" };
			int count = -1;

			
			for (String string : chars) {
				count++;
				final int charID = count;
				int identifier = getActivity().getResources().getIdentifier(
						"NPCEditor" + string, "id",
						getActivity().getPackageName());
				text = (EditText) rootView.findViewById(identifier);
				text.setText(String.format("%d", npc.Characteristics.get(charID)));
						
				text.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						if (s.toString().isEmpty())
							return;
						npc.Characteristics.set(charID, Integer.valueOf(s.toString()));

					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {

					}

					@Override
					public void afterTextChanged(Editable s) {

					}
				});

			}

			text = (EditText) rootView.findViewById(R.id.NPCEditorWounds);

			text.setText(String.format("%d", npc.TotalWounds));
			text.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					if (s.toString().isEmpty())
						return;
					npc.TotalWounds = Integer.valueOf(s.toString());

				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {

				}

				@Override
				public void afterTextChanged(Editable s) {

				}
			});

			text = (EditText) rootView.findViewById(R.id.NPCEditorStrain);

			text.setText(String.format("%d", npc.TotalStrain));
			text.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					if (s.toString().isEmpty())
						return;
					npc.TotalStrain = Integer.valueOf(s.toString());

				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {

				}

				@Override
				public void afterTextChanged(Editable s) {

				}
			});

			text = (EditText) rootView.findViewById(R.id.NPCEditorSoak);

			text.setText(String.format("%d", npc.Soak));
			text.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					if (s.toString().isEmpty())
						return;
					npc.Soak = Integer.valueOf(s.toString());

				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {

				}

				@Override
				public void afterTextChanged(Editable s) {

				}
			});

			text = (EditText) rootView.findViewById(R.id.NPCEditorMeleeDefense);

			text.setText(String.format("%d", npc.MeleeDefense));
			text.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					if (s.toString().isEmpty())
						return;
					npc.MeleeDefense = Integer.valueOf(s.toString());

				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {

				}

				@Override
				public void afterTextChanged(Editable s) {

				}
			});

			text = (EditText) rootView
					.findViewById(R.id.NPCEditorRangedDefense);

			text.setText(String.format("%d", npc.RangedDefense));
			text.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					if (s.toString().isEmpty())
						return;
					npc.RangedDefense = Integer.valueOf(s.toString());

				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {

				}

				@Override
				public void afterTextChanged(Editable s) {

				}
			});

		    rootView.findViewById(R.id.NPCEditorAddSkill).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {


                    new SkillAlertDialog(npc, 0, new ISkillUpdate() {

                        @Override
                        public void UpdateDataSet(int skillID, int skillValue) {
                            npc.SetSkill(skillID, skillValue);
                            Log.d("hopla", "notification");
                            RefreshSkills();
                        }
                    }).Show(getActivity());

                }
            });
			
			SkillLayout = (LinearLayout)rootView.findViewById(R.id.NPCEditorSkillList);
			RefreshSkills();
			
			
		    rootView.findViewById(R.id.NPCEditorAddTalent).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    final int position = npc.Talents.size();
                    Talent talent = new Talent(0);
                    npc.Talents.add(talent);

                    new TalentsAlertDialog(npc, position, new ITalentUpdate() {


                        @Override
                        public void UpdateDataSet(int TalentID,
                                                  int TalentValue) {


                            npc.Talents.get(position).setTalentID(TalentID);
                            npc.Talents.get(position).setTalentValue(TalentValue);
                            RefreshTalents();
                        }
                    }).Show(getActivity());

                }
            });
			
		    
		    TalentsLayout = (LinearLayout)rootView.findViewById(R.id.NPCEditorTalentList);
			RefreshTalents();
			

			

		    rootView.findViewById(R.id.NPCEditorAddAbilities).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    final int position = npc.Abilities.size();
                    npc.Abilities.add(getActivity().getString(R.string.npceditor_newability));

                    new AbilitiesAlertDialog(npc, position, new IAbilityUpdate() {

                        @Override
                        public void UpdateDataSet(String AbilitiesString, int Position) {
                            npc.Abilities.set(Position, AbilitiesString);

                            RefreshAbilities();

                        }
                    }).Show(getActivity());

                }
            });
			
			AbilitiesLayout = (LinearLayout)rootView.findViewById(R.id.NPCEditorAbilitiesList);
			RefreshAbilities();

			
			
			
		    rootView.findViewById(R.id.NPCEditorAddWeapon).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Builder builder = new Builder(getActivity());
                    builder.setTitle(R.string.itemeditor_newitem);
                    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                        }
                    });
                    String[] strings = {
                            getActivity().getString(R.string.weapon),
                            getActivity().getString(R.string.armor),
                            getActivity().getString(R.string.gear)
                    };

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strings);

                    builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Item newItem = new Item();
                            if (which == 0)
                                newItem = new Weapon();
                            if (which == 1)
                                newItem = new Armor();

                            new ItemEditor(getActivity(), newItem).setUpdater(new IItemUpdate() {

                                @Override
                                public void Update(Item EditedItem) {
                                    npc.Items.add(EditedItem);
                                    RefreshWeapons();

                                }
                            }).show();

                        }
                    });
                    builder.show();
                }
            });
			

		    WeaponLayout = (LinearLayout)rootView.findViewById(R.id.NPCEditorWeaponsList);
			RefreshWeapons();
			
			
			return rootView;
		}

		LinearLayout SkillLayout;
		private void RefreshSkills() {
			
			SkillLayout.removeAllViews();
			
			for(final Skill sk : npc.Skills)
			{
				View skillView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_npceditor_skill, SkillLayout, false);
				
				
				((TextView)skillView.findViewById(R.id.NPCEditorSkillListItemName)).setText(sk.toString());
				skillView.findViewById(R.id.NPCEditorSkillListItemEdit).setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        new SkillAlertDialog(npc, sk.SkillID.ordinal(), new ISkillUpdate() {

                            @Override
                            public void UpdateDataSet(int skillID, int skillValue) {
                                npc.SetSkill(skillID, skillValue);

                                RefreshSkills();

                            }
                        }).Show(getActivity());

                    }
                });
                skillView.findViewById(R.id.NPCEditorSkillListItemRemove).setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        npc.Skills.remove(sk);
                        RefreshSkills();
                    }
                });
				
				SkillLayout.addView(skillView);
				
			}
			
			
			
		}
		
		LinearLayout TalentsLayout;
		private void RefreshTalents() {
			
			TalentsLayout.removeAllViews();
			int iCount = -1;
			for(final Talent str : npc.Talents)
			{
				iCount++;
				final int Position = iCount;
				View talentView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_npceditor_skill, null);
				
				Util.setTextViewSymbols((TextView)talentView.findViewById(R.id.NPCEditorSkillListItemName), str.toString());
				

				talentView.findViewById(R.id.NPCEditorSkillListItemEdit).setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        new TalentsAlertDialog(npc, Position, new ITalentUpdate() {


                            @Override
                            public void UpdateDataSet(int TalentID,
                                                      int TalentValue) {
                                npc.Talents.get(Position).setTalentID(TalentID);
                                npc.Talents.get(Position).setTalentValue(TalentValue);
                                RefreshTalents();
                            }
                        }).Show(getActivity());

                    }
                });
                talentView.findViewById(R.id.NPCEditorSkillListItemRemove).setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        npc.Talents.remove(str);
                        RefreshTalents();
                    }
                });
				
				TalentsLayout.addView(talentView);
				
			}
			
			
			
		}
		

		LinearLayout AbilitiesLayout;
		private void RefreshAbilities() {
			
			AbilitiesLayout.removeAllViews();
			int iCount = -1;
			for(final String str : npc.Abilities)
			{
				iCount++;
				final int Position = iCount;
				View abilityView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_npceditor_skill, null);
				
				
				Util.setTextViewSymbols((TextView)abilityView.findViewById(R.id.NPCEditorSkillListItemName), str);

				abilityView.findViewById(R.id.NPCEditorSkillListItemEdit).setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        new AbilitiesAlertDialog(npc, Position, new IAbilityUpdate() {

                            @Override
                            public void UpdateDataSet(String AbilityString, int Position) {
                                npc.Abilities.set(Position, AbilityString);

                                RefreshAbilities();

                            }
                        }).Show(getActivity());

                    }
                });
                abilityView.findViewById(R.id.NPCEditorSkillListItemRemove).setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        npc.Abilities.remove(str);
                        RefreshAbilities();
                    }
                });
				
				AbilitiesLayout.addView(abilityView);
				
			}
			
			
			
		}
		

		LinearLayout WeaponLayout;
		private void RefreshWeapons() {
			
			WeaponLayout.removeAllViews();

			
			for (final Item itm : npc.Items) {

				View ItemView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_npceditor_skill, null);
				
				String s = itm.toString();
				if (itm.getClass() == Weapon.class) s = itm.toString();
				if (itm.getClass() == Armor.class) s = itm.toString();
				
				Util.setTextViewSymbols(((TextView)ItemView.findViewById(R.id.NPCEditorSkillListItemName)), s);

				ItemView.findViewById(R.id.NPCEditorSkillListItemEdit).setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        new ItemEditor(getActivity(), itm)
                                .setUpdater(new IItemUpdate() {

                                    @Override
                                    public void Update(Item EditedItem) {

                                        RefreshWeapons();

                                    }
                                })
                                .show();


                    }
                });

                ItemView.findViewById(R.id.NPCEditorSkillListItemRemove).setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        npc.Items.remove(itm);
                        RefreshWeapons();
                    }
                });

				WeaponLayout.addView(ItemView);
			}
			
			
			
			
		}
		
		
		
		
		
				@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((NPCEditorActivity) activity).onSectionAttached(getArguments()
					.getString(ARG_NPC_NAME));
		}

		@Override
		public void onActivityResult(int requestCode, int resultCode,
				Intent imageReturnedIntent) {
			super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

			switch (requestCode) {
			case 100:
				if (resultCode == RESULT_OK) {

					try {

						Uri selectedImage = imageReturnedIntent.getData();
						Bitmap yourSelectedImage = Util.decodeUri(
								selectedImage, getActivity());
						NPCEditorFragment frag = (NPCEditorFragment) getFragmentManager()
								.findFragmentById(R.id.container);
						frag.UpdateImage(yourSelectedImage);
					} catch (FileNotFoundException e) {

						e.printStackTrace();
					}

				}
			}
		}

	}

}
