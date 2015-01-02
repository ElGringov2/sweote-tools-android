package com.dragonrider.SWEotE.Classes;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dragonrider.SWEotE.R;
import com.dragonrider.SWEotE.Classes.NPC.NPCTypes;
public class GroundFighter {

	public boolean IsActive = false;
	public String id;
	public int ActualWounds;

	public int ActualStrain;
	private NPC Base = new NPC();
	public Weapon InHand;
    public String Name;


    public String getFullName() {

        if (Base == null) return Name;
        String sReturn = Base.Name;

        if (Color.getColor() == 0xFFFF0000) sReturn += " (Rouge)";
        if (Color.getColor() == 0xFF00FF00) sReturn += " (Vert)";
        if (Color.getColor() == 0xFF0000FF) sReturn += " (Bleu)";
        if (Color.getColor() == 0xFFFFFF00) sReturn += " (Jaune)";
        if (Color.getColor() == 0xFF00FFFF) sReturn += " (Cyan)";
        if (Color.getColor() == 0xFFFF00FF) sReturn += " (Magenta)";

        return sReturn;


    }

    public int X;
    public int Y;

    public GroundFighter setCoord(int x, int y) {
        this.X = x;
        this.Y = y;
        return this;
    }

	public int TotalWounds = 0;

	public int TotalStrain = 0;
	
	private int playerCount;
	public boolean isPlayer = false;

	public String LastAction = "Aucune";
	public String LastManeuver = "Aucune";
	
	public void setLastAction(String lastAction) {
		LastAction = lastAction;
	}
	public void setLastManeuver(String lastManeuver) {
		LastManeuver = lastManeuver;
	}
	
	public List<String> Attacks = new ArrayList<String>();
    public List<String> Defends = new ArrayList<String>();

    public void addAttack(String name) {
        Attacks.add(name);
    }

    public void addDefends(String name) {
        Defends.add(name);
    }

	private ColorDrawable Color = new ColorDrawable(0x00000000);
	
	
	
	private int aimingBonus = 0;
	
	public void setAimingBonus(int Bonus) {
		aimingBonus = Bonus;
		if (aimingBonus > 2) aimingBonus = 2;
		
		
	}
	public int getAimingBonus(){
		return aimingBonus;
	}
	
	public void increaseAimingBonus() {
		if (aimingBonus < 2)
			aimingBonus++;
	}
	
	public Boolean isDefensive = false;
	public Boolean isProne = false;
	public Boolean isCover = false;
	
	private int mainInitiative = -1;
	private int initiative = -1;
	private int subInitiative = -1;
	
	public int getMainInitiative() {
		if (mainInitiative == -1)
			return InitiativeRoll.Triumph();
		return mainInitiative;
	}
	public void setMainInitiative(int mainInitiative) {
		this.mainInitiative = mainInitiative;
	}
	
	public void setInitiative(int Initiative){
		this.initiative = Initiative;
	}
	public int getInitiative(){
		if (initiative == -1) return InitiativeRoll.Succcess();
		return initiative;
	}
	public void setSubinitiative(int Initiative){
		this.subInitiative = Initiative;
	}
	public int getSubinitiative(){
		if (subInitiative == -1) return InitiativeRoll.Advantage();
		return subInitiative;
	}
	
	public void setInitiativeRoll(RollResult initiativeRoll) {
		InitiativeRoll = initiativeRoll;
		this.mainInitiative = -1;
		this.initiative = -1;
		this.subInitiative = -1;
	}
	
	public RollResult InitiativeRoll = new RollResult();
	
	public Boolean Played = false;
	
	public List<CriticalInjury> Criticals = new ArrayList<CriticalInjury>();


    
	
	public GroundFighter(int PlayerCount) {
		playerCount = PlayerCount;
	}


	@Override
	public String toString() {
		String sReturn = "";
		if (Base.Type.equals(NPCTypes.Minion)) sReturn = String.valueOf(getCount()) + "x";
		sReturn += Base.Name + "\nW" + ActualWounds + "/" + TotalWounds + ", S" + ActualStrain + "/" + TotalStrain;
		String sThirdLine = "";
		if (Played) sThirdLine += App.getContext().getString(R.string.fightstatus_played);
		if (isProne) sThirdLine += (sThirdLine.equals("") ? "" : " - ") + App.getContext().getString(R.string.fightstatus_prone);
		if (isCover) sThirdLine += (sThirdLine.equals("") ? "" : " - ") + App.getContext().getString(R.string.fightstatus_cover);
		if (isDefensive) sThirdLine += (sThirdLine.equals("") ? "" : " - ") + App.getContext().getString(R.string.fightstatus_defensive);
		
		if (IsActive) sThirdLine += "\nActif";

		
		if (!sThirdLine.equals("")) sReturn += "\n" + sThirdLine;
		return sReturn;
	}


	
	
	
	public GroundFighter setBase(NPC npc){
		Base= npc;
		if (Base == null) {
			isPlayer = true;
			return this;
		}
		if (Base.Type.equals(NPCTypes.Minion)){
			
			TotalWounds = playerCount * Base.TotalWounds;
			TotalStrain = 0;
		}
		if (Base.Type.equals(NPCTypes.Nemesis)) {
			TotalWounds = Base.TotalWounds;
			TotalStrain = Base.TotalStrain;
				
		}
		if (Base.Type.equals(NPCTypes.Rival))
		{
			TotalWounds = Base.TotalWounds;
			TotalStrain = 0;
		}
		
		
		

        this.Name = Base.Name;
		return this;
		
	}
	public NPC getBase() {
		return Base;
	}
	
	public int getCount() {
		if (ActualWounds >= TotalWounds)
			return 0;
		
		if (Base.Type.equals(NPCTypes.Minion)) {

            return (int) Math.ceil(((double)TotalWounds - (double)ActualWounds) / (double)TotalWounds * playerCount);
			
		}
		else return 1;
		
		
		
	}
	
	
	public RollResult GetSkillRollResult(int skillID)
    {


		Skill sk = Base.GetSkill(skillID);



        int skValue = sk.Value;
        if (Base.Type == NPCTypes.Minion)
            skValue = (skValue > 0 ? getCount() - 1 : 0);

        RollResult rr = new RollResult();
        




        int iCharValue = Base.Characteristics.get(sk.CharacteristicID);

        if (iCharValue > skValue)
        {
            rr.DiceAbility = iCharValue;
            rr.UpgradePositivePool(skValue);

        }
        else
        {
            rr.DiceAbility = skValue;
            rr.UpgradePositivePool(iCharValue);
        }


        return rr;
    }
	

    public List<String> GetPossibleManeuvers () {
        List<String> lst = new ArrayList<String>();
        lst.add(App.getContext().getString(R.string.none));
        if (aimingBonus <= 1) lst.add(App.getContext().getString(R.string.action_aim_plusoneboost) + "#" + App.getContext().getString(R.string.action_desc_aim_plusoneboost));
        lst.add(App.getContext().getString(R.string.action_assist) + "#" + App.getContext().getString(R.string.action_desc_assist));
        lst.add(App.getContext().getString(R.string.action_defensivestance_desc) + "#" + App.getContext().getString(R.string.action_desc_defensivestance_desc) );
        lst.add(App.getContext().getString(R.string.action_movinglargeitem));
        lst.add(App.getContext().getString(R.string.action_open_close_door));
        if (!isCover) lst.add(App.getContext().getString(R.string.action_cover_desc) + "#" + App.getContext().getString(R.string.action_desc_cover_desc));
        lst.add(App.getContext().getString(R.string.action_mound_dismount));
        lst.add(App.getContext().getString(R.string.action_move));
        if (isProne) lst.add(App.getContext().getString(R.string.action_standfromprone));
        else lst.add(App.getContext().getString(R.string.action_drop_prone) + "#" + App.getContext().getString(R.string.action_desc_drop_prone));
        lst.add(App.getContext().getString(R.string.action_prepare));
        for (Item item : Base.Items)
        {
        	if (item.getClass() != Weapon.class) continue;
            if (this.InHand != item)
                lst.add(App.getContext().getString(R.string.action_equipweapon) + " " + item.Name);
            else
                lst.add(App.getContext().getString(R.string.action_holsterweapon) + " " + item.Name);
        }
        return lst;
    }

    public void setPlayerCount(int iCount) {
        this.playerCount = iCount;
        if (Base.Type.equals(NPCTypes.Minion)){

            TotalWounds = iCount * Base.TotalWounds;
            TotalStrain = 0;
        }
    }

    
    
    public List<String> GetPossibleActions(List<String> PlayerNames, Context context)
    {

        List<String> lst = new ArrayList<String>();
        lst.add(context.getString(R.string.none));
        lst.add(context.getString(R.string.swap_action_maneuver));
        lst.add(context.getString(R.string.special_abilities));
        if (getBase().ForceRating > 0)
        	lst.add(context.getString(R.string.action_force_power));
        lst.add(context.getString(R.string.skill));
        for (String item : PlayerNames)
            lst.add("Attaque sur " + item);
        for (GroundFighter fighter : GroundFightScene.getFighters()) {
            if (PlayerNames.contains(fighter.Name)) continue;
            if (fighter.equals(this)) continue;

            lst.add("Attaque sur " + fighter.getFullName());

        }

        

        return lst;
    }
	public ColorDrawable getColor() {
		return Color;
	}
	
	public void setColor(int iId) {
		ColorDrawable[] colors = {
                new ColorDrawable(0x00FF0000),
				new ColorDrawable(0xFFFF0000),
				new ColorDrawable(0xFF00FF00),
				new ColorDrawable(0xFF0000FF),
                new ColorDrawable(0xFFFFFF00),
                new ColorDrawable(0xFF00FFFF),
				new ColorDrawable(0xFFFF00FF),
		};
		this.Color = colors[iId];
	}
	
	


	
	
	public void DamageUI(final Context context, final BaseAdapter parentAdapter) {
		

		
		
		List<String> strings = new ArrayList<String>();
		
		int iMinDamage = getBase().Soak;
		int iArmorSoak = getBase().Soak - getBase().Characteristics.get(0);
		strings.add(String.format("%d ou moins (%d sans armure): pas de dégâts", iMinDamage, iMinDamage - iArmorSoak));
		int iMaxDamage = TotalWounds - ActualWounds  + getBase().Soak;
		
		for (int i = iMinDamage + 1; i < iMaxDamage; i++ ) {
			if (getBase().Type == NPCTypes.Minion){
				android.util.Log.d("hopla", String.valueOf((((double)TotalWounds - (double)(ActualWounds + i - getBase().Soak)) / (double)TotalWounds) * (double)playerCount));
				
				int iCount =  (int) Math.ceil((((double)TotalWounds - (double)(ActualWounds + i - getBase().Soak)) / (double)TotalWounds) * playerCount);
				strings.add(String.format("%d (%d sans armure), reste %d", i, i - iArmorSoak, iCount));
			}
			else 
				strings.add(String.format("%d (%d sans armure)", i, i - iArmorSoak));
			
		}
		
		strings.add(String.format("%d ou plus (%d sans armure), élimination", iMaxDamage, iMaxDamage - iArmorSoak));
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String> (context, android.R.layout.simple_dropdown_item_1line, strings); 
		
		
		
		View baseView = LayoutInflater.from(context).inflate(R.layout.popup_damage_groundfighter, null);
		
		ListView lv =  (ListView) baseView.findViewById(R.id.popupDamageGroundFighterMainList);
		lv.setAdapter(adapter);
		
		
		

		TextView tview = (TextView) baseView.findViewById(R.id.popupDamageGroundFighterDifficulty);
		
		int iUpgrades = Talent.getCount(Talent.TalentIDs.adversary, getBase());

		
		RollResult diffRoll = new RollResult();
		diffRoll.DiceDifficulty = 2;
		diffRoll.DiceSetback = getBase().MeleeDefense + (isDefensive ? 1 : 0);
		diffRoll.DiceBoost = (isProne ? 1 : 0);
		diffRoll.UpgradeNegativePool(iUpgrades);
		String text = "CàC:" + diffRoll.toSymbolFormattableString();
				
		diffRoll = new RollResult();
		diffRoll.DiceDifficulty = 1;
		diffRoll.DiceSetback = getBase().RangedDefense + (isCover ? 1 : 0) + (isProne ? 1 : 0);
		diffRoll.UpgradeNegativePool(iUpgrades);
		text += "\nCourte:" + diffRoll.toSymbolFormattableString(); 
		
				
		diffRoll = new RollResult();
		diffRoll.DiceDifficulty = 2;
		diffRoll.DiceSetback = getBase().RangedDefense + (isCover ? 1 : 0) + (isProne ? 1 : 0);
		diffRoll.UpgradeNegativePool(iUpgrades);
		text += "\nMoyenne:" + diffRoll.toSymbolFormattableString(); 
		
		diffRoll = new RollResult();
		diffRoll.DiceDifficulty = 3;
		diffRoll.DiceSetback = getBase().RangedDefense + (isCover ? 1 : 0) + (isProne ? 1 : 0);
		diffRoll.UpgradeNegativePool(iUpgrades);
		text += "\nLongue:" + diffRoll.toSymbolFormattableString(); 
		
		diffRoll = new RollResult();
		diffRoll.DiceDifficulty = 4;
		diffRoll.DiceSetback = getBase().RangedDefense + (isCover ? 1 : 0) + (isProne ? 1 : 0);
		diffRoll.UpgradeNegativePool(iUpgrades);
		text += "\nExtrème:" + diffRoll.toSymbolFormattableString();
		
		Util.setTextViewSymbols(tview, text);
		
		final AlertDialog dialog = new AlertDialog.Builder(context)
		.setTitle("Infliger des dégâts")
		.setView(baseView)
		.setNegativeButton(R.string.cancel, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				
			}
		})
		.show();
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int oldValue = getCount();
				ActualWounds += position;
				int newValue = getCount();

				dialog.dismiss();
				
				if (newValue== 0) {
					new AlertDialog.Builder(context)
					.setTitle("Infliger des dégâts")
					.setMessage("Eliminé")
					.setPositiveButton(R.string.ok, new OnClickListener() {
								
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							
						}
					})
					.show();


				}
				else if (oldValue != newValue) {
					new AlertDialog.Builder(context)
					.setTitle("Infliger des dégâts")
					.setMessage(String.format("%d mort%s", 
							oldValue - newValue,
							oldValue - newValue > 1 ? "s": ""))
					.setPositiveButton(R.string.ok, new OnClickListener() {
								
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							
						}
					})
					.show();
				}

				parentAdapter.notifyDataSetChanged();
				
			}
		});
		
				
	}

	public void CriticalUI(Context context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (Base.Type == NPCTypes.Minion) {
            builder.setMessage(R.string.critical_autokill_minion);
            builder.setTitle(R.string.weapon_critical);
            builder.setPositiveButton(R.string.ok, new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActualWounds += Base.TotalWounds;
                }
            });
            builder.setNegativeButton(R.string.cancel, new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
            return;

        }

        int _criticals = this.Criticals.size() * 10;
        final List<String> lst1 = new ArrayList<String>();
        List<String> lst2 = new ArrayList<String>();

        for (int i = _criticals; i <= 151; i++) {
            String crit = CriticalInjury.getCriticalName(i);
            if (!lst1.contains(crit))
            {
                lst1.add(crit);
                lst2.add(String.format("%s (%d): %s", crit, i, CriticalInjury.getCriticalDescription(i)));

            }
        }

        builder.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, lst2), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CriticalInjury criticalInjury = new CriticalInjury(CriticalInjury.CriticalIDs.values()[i]);
                Criticals.add(criticalInjury);

            }
        });

        builder.setNegativeButton(R.string.cancel, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });


        builder.show();
    }




	private int equippedItemID = -1;
	
	public Weapon getEquippedWeapon() {
		if (equippedItemID == -1)
			return Weapon.getFists();
		
		else return (Weapon) Base.Items.get(equippedItemID);
		
		
		
	}
	
	
	public void setEquippedWeapon(int WeaponID) {
		if (Base.Items.get(WeaponID).getClass() == Weapon.class)
			this.equippedItemID = WeaponID;
		
	}
	public String getStatus() {
		if (ActualWounds >= TotalWounds)
			return "Mort";
		
		String s = "";
		
		if (isProne) 
			s += "A terre ";
		if (isDefensive)
			s += "Posture défensive ";
		if (isCover)
			s += "A couvert ";
		
		if (getAimingBonus() > 0)
			s += String.format("Visée (%d) ", getAimingBonus());
		
		s += "\n"; 
		
		
		for (CriticalInjury injury : Criticals) {
			s += injury.toString() + "\n";
		}
		
		
		
		return s.substring(0, s.length() - 1);
	}
	public void setCover(boolean b) {
		isCover = b;
		
	}
	public void setProne(boolean b) {
		isProne = b;
		
	}
	public void setDefensive(boolean b) {
		isDefensive = b;
		
	}
	
	int committedDices = 0;
	
	public int getForceDices() {
		
		return getBase().ForceRating - committedDices;
	}

    public void setName(String name) {
        this.Name = name;
    }

}
