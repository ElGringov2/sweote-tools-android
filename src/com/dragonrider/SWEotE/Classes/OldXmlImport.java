package com.dragonrider.SWEotE.Classes;

import android.graphics.Bitmap;
import android.util.Base64;

import com.dragonrider.SWEotE.Classes.NPC.NPCTypes;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class OldXmlImport {
//
//
//	public static List<NPC> ImportPCs() {
//		ArrayList<NPC> players = new ArrayList<NPC>();
//
//
//		File sdCardRoot = Environment.getExternalStorageDirectory();
//		File yourDir = new File(sdCardRoot, "SWEotE/PC");
//		if (yourDir.exists()) {
//
//
//			File[] files = yourDir.listFiles();
//			for (File f : files) {
//			    if (f.isFile()) {
//			    	try {
//						NPC npc = XmlImport.ImportNPC(f.getPath(), getWeapons());
//                        players.add(npc);
//					} catch (XmlPullParserException e) {
//						e.printStackTrace();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//
//
//
//			    }
//			}
//		}
//
//		return players;
//
//
//	}
//
	
//
//	public static void ImportFolder(final Context context) {
//
//		final ProgressDialog pd = ProgressDialog.show(context,
//				context.getString(R.string.dialog_initnpcdatabase),
//				context.getString(R.string.dialog_pleasewait), true,
//				false);
//
//		final Handler handler = new Handler() {
//
//
//			@Override
//			public void handleMessage(Message msg) {
//				if (msg.obj.equals("end"))
//					pd.dismiss();
//				else {
//
//					float percentage = (float)msg.arg2 / (float)msg.arg1 * 100.0f;
//					pd.setMessage(String.format("Fichier %d/%d (%,.2f%%)\n%s",
//							msg.arg2,
//							msg.arg1,
//							percentage,
//							msg.obj));
//				}
//			};
//
//		};
//
//
//
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//
//				Database db = new Database(context);
//				db.EmptyNPC();
//
//				File sdCardRoot = Environment.getExternalStorageDirectory();
//				File yourDir = new File(sdCardRoot, "SWEotE");
//				HashMap<String, Weapon> weaponList = getWeapons();
//
//
//				File[] files = yourDir.listFiles();
//				int iFilesCount = files.length;
//
//				int iCount = 0;
//
//				for (File f : files) {
//				    if (f.isFile()) {
//
//				        String name = f.getPath();
//				    	try {
//							NPC npc = XmlImport.ImportNPC(name, weaponList);
//
//							if (npc != null) {
//								Message msg = new Message();
//								msg.obj = f.getName();
//								msg.arg1 = iFilesCount;
//								msg.arg2 = iCount;
//								handler.sendMessage(msg);
//								db.AddNPC(npc);
//								iCount++;
//							}
//						} catch (XmlPullParserException e) {
//							e.printStackTrace();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//
//				    }
//
//				}
//				db.close();
//				Message endMessage = new Message();
//				endMessage.obj = "end";
//				handler.sendMessage(endMessage);
//
//			}
//		}).start();
//
//
//	}

//	public static void UpdateFolder(final Context context) {
//
//		final ProgressDialog pd = ProgressDialog.show(context,
//				context.getString(R.string.dialog_initdatabase),
//				context.getString(R.string.dialog_pleasewait), true,
//				false);
//
//		final Handler handler = new Handler() {
//
//
//			@Override
//			public void handleMessage(Message msg) {
//				if (msg.obj.equals("end"))
//					pd.dismiss();
//				else {
//
//					float percentage = (float)msg.arg2 / (float)msg.arg1 * 100.0f;
//					pd.setMessage(String.format("Fichier %d/%d (%,.2f%%)\n%s",
//							msg.arg2,
//							msg.arg1,
//							percentage,
//							msg.obj));
//				}
//			}
//
//		};
//
//
//
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//
//				Database db = new Database(context);
//
//
//				File sdCardRoot = Environment.getExternalStorageDirectory();
//				File yourDir = new File(sdCardRoot, "SWEotE");
//				HashMap<String, Weapon> weaponList = getWeapons();
//
//
//				File[] files = yourDir.listFiles();
//				int iFilesCount = files.length;
//
//				int iCount = 0;
//
//				for (File f : files) {
//				    if (f.isFile()) {
//
//				        String name = f.getPath();
//				    	try {
//							NPC npc = XmlImport.ImportNPC(name, weaponList);
//                            Bitmap bitmap = null;
//                            if (new File(f.getPath().replace("xml", "jpg")).exists())
//                                bitmap = BitmapFactory.decodeFile(f.getPath().replace("xml", "jpg"));
//                            if (new File(f.getPath().replace("xml", "png")).exists())
//                                bitmap = BitmapFactory.decodeFile(f.getPath().replace("xml", "png"));
//
//
//                            if (npc != null) {
//
//								Message msg = new Message();
//								msg.obj = f.getName();
//								msg.arg1 = iFilesCount;
//								msg.arg2 = iCount;
//								handler.sendMessage(msg);
//
//								NPC ExistingNPC = db.GetNPCbyName(npc.Name);
//								if (ExistingNPC != null) {
//									npc.DatabaseID = ExistingNPC.DatabaseID;
//                                    if (bitmap != null)
//                                        npc.setImage(bitmap);
//
//									db.SaveNPC(npc);
//								}
//								else {
//
//                                    if (bitmap != null)
//                                        npc.setImage(bitmap);
//                                    db.AddNPC(npc);
//
//
//								}
//
//
//								iCount++;
//							}
//						} catch (XmlPullParserException e) {
//							e.printStackTrace();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//
//				    }
//
//				}
//				db.close();
//				Message endMessage = new Message();
//				endMessage.obj = "end";
//				handler.sendMessage(endMessage);
//
//			}
//		}).start();
//
//
//	}
	
	private static HashMap<String, Weapon> getWeapons() {
		HashMap<String, Weapon> weapons = new HashMap<String, Weapon>();
		
		Weapon wp ;
		String wpKey;
		
		
		wp = new Weapon();
		wpKey = "BLASTHOLD";
		wp.Name = "Holdout Blaster";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 4;
		wp.RangeID = 1;
		wp.Encumbrance = 1;
		wp.HardPoints = 1;
		wp.Price = 200;
		wp.Rarity = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTHOLDTT24";
		wp.Name = "TT24 Holdout Blaster";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 6;
		wp.CriticalValue = 3;
		wp.RangeID = 2;
		wp.Encumbrance = 1;
		wp.HardPoints = 1;
		wp.Price = 350;
		wp.Rarity = 6;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTLT";
		wp.Name = "Light Blaster Pistol";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 4;
		wp.RangeID = 2;
		wp.Encumbrance = 1;
		wp.HardPoints = 2;
		wp.Price = 300;
		wp.Rarity = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTLTHL27";
		wp.Name = "HL-27 Light Blaster Pistol";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 3;
		wp.RangeID = 2;
		wp.Encumbrance = 1;
		wp.HardPoints = 2;
		wp.Price = 450;
		wp.Rarity = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.accurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "POCKPIS";
		wp.Name = "Pocket Blaster Pistol";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 3;
		wp.RangeID = 1;
		wp.Encumbrance = 1;
		wp.HardPoints = 1;
		wp.Price = 200;
		wp.Rarity = 2;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTPIS";
		wp.Name = "Blaster Pistol";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 6;
		wp.CriticalValue = 3;
		wp.RangeID = 2;
		wp.Encumbrance = 1;
		wp.HardPoints = 3;
		wp.Price = 400;
		wp.Rarity = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTPISCDEF";
		wp.Name = "CDEF Blaster Pistol";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 4;
		wp.RangeID = 2;
		wp.Encumbrance = 1;
		wp.HardPoints = 1;
		wp.Price = 150;
		wp.Rarity = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.inferior.ordinal(), 0));
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "DUELPIS";
		wp.Name = "Dueling Pistol";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 9;
		wp.CriticalValue = 2;
		wp.RangeID = 1;
		wp.Encumbrance = 2;
		wp.HardPoints = 2;
		wp.Price = 750;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.accurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTPISXL2";
		wp.Name = "XL-2 \"Flashfire\" Blaster Pistol";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 3;
		wp.RangeID = 2;
		wp.Encumbrance = 1;
		wp.HardPoints = 3;
		wp.Price = 450;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.disorient.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTPISH7";
		wp.Name = "H-7 \"Equalizer\" Blaster Pistol";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 7;
		wp.CriticalValue = 2;
		wp.RangeID = 2;
		wp.Encumbrance = 2;
		wp.HardPoints = 3;
		wp.Price = 1200;
		wp.Rarity = 8;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.superior.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTPISHVY";
		wp.Name = "Heavy Blaster Pistol";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 7;
		wp.CriticalValue = 3;
		wp.RangeID = 2;
		wp.Encumbrance = 2;
		wp.HardPoints = 3;
		wp.Price = 700;
		wp.Rarity = 6;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTPISHVYGEO";
		wp.Name = "Geonosian Heavy Blaster Pistol";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 7;
		wp.CriticalValue = 2;
		wp.RangeID = 2;
		wp.Encumbrance = 2;
		wp.HardPoints = 3;
		wp.Price = 1100;
		wp.Rarity = 6;
		wp.Restricted = true;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "MODEL44BLASTPIST";
		wp.Name = "Model 44 Blaster Pistol";
		wp.Encumbrance = 1;
		wp.HardPoints = 4;
		wp.Price = 500;
		wp.Rarity = 4;
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 6;
		wp.CriticalValue = 3;
		wp.RangeID = 2;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "MODEL80BLASTPIST";
		wp.Name = "Model 80 Blaster Pistol";
		wp.Encumbrance = 2;
		wp.HardPoints = 3;
		wp.Price = 550;
		wp.Rarity = 5;
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 6;
		wp.CriticalValue = 2;
		wp.RangeID = 2;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "IR5BLASTPIST";
		wp.Name = "IR-5 \"Intimidator\" Blaster Pistol";
		wp.Encumbrance = 2;
		wp.HardPoints = 2;
		wp.Price = 750;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 3;
		wp.RangeID = 2;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.autofire.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.inaccurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTPISHVYCR2";
		wp.Name = "CR-2 Heavy Blaster Pistol";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 7;
		wp.CriticalValue = 4;
		wp.RangeID = 2;
		wp.Encumbrance = 2;
		wp.HardPoints = 2;
		wp.Price = 600;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "X30LANCER";
		wp.Name = "x-30 Lancer";
		wp.Encumbrance = 1;
		wp.HardPoints = 3;
		wp.Price = 1000;
		wp.Rarity = 5;
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 4;
		wp.RangeID = 3;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.accurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "DL7HBLASTPISTHVY";
		wp.Name = "DL-7h Heavy Blaster Pistol";
		wp.Encumbrance = 2;
		wp.HardPoints = 2;
		wp.Price = 850;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 8;
		wp.CriticalValue = 3;
		wp.RangeID = 2;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTCARB";
		wp.Name = "Blaster Carbine";
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 9;
		wp.CriticalValue = 3;
		wp.RangeID = 2;
		wp.Encumbrance = 3;
		wp.HardPoints = 4;
		wp.Price = 850;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTCARBGEO";
		wp.Name = "Geonosian Blaster Carbine";
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 9;
		wp.CriticalValue = 2;
		wp.RangeID = 2;
		wp.Encumbrance = 3;
		wp.HardPoints = 4;
		wp.Price = 1300;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "OK98BLASTCARB";
		wp.Name = "OK-98 Blaster Carbine";
		wp.Encumbrance = 4;
		wp.HardPoints = 3;
		wp.Price = 1100;
		wp.Rarity = 5;
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 9;
		wp.CriticalValue = 3;
		wp.RangeID = 2;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTRIF";
		wp.Name = "Blaster Rifle";
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 9;
		wp.CriticalValue = 3;
		wp.RangeID = 3;
		wp.Encumbrance = 4;
		wp.HardPoints = 4;
		wp.Price = 900;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTRIFGEO";
		wp.Name = "Geonosian Blaster Rifle";
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 9;
		wp.CriticalValue = 2;
		wp.RangeID = 3;
		wp.Encumbrance = 4;
		wp.HardPoints = 4;
		wp.Price = 1500;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTRIFSKZ";
		wp.Name = "SKZ Sporting Blaster Rifle";
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 8;
		wp.CriticalValue = 4;
		wp.RangeID = 3;
		wp.Encumbrance = 3;
		wp.HardPoints = 4;
		wp.Price = 600;
		wp.Rarity = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "ACPARRAYGUN";
		wp.Name = "ACP Array Gun";
		wp.Encumbrance = 3;
		wp.HardPoints = 3;
		wp.Price = 890;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 6;
		wp.CriticalValue = 3;
		wp.RangeID = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTRIFHVY";
		wp.Name = "Heavy Blaster Rifle";
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 10;
		wp.CriticalValue = 3;
		wp.RangeID = 3;
		wp.Encumbrance = 6;
		wp.HardPoints = 4;
		wp.Price = 1500;
		wp.Rarity = 6;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.autofire.ordinal(), 0));
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "DHXBLASTRIFHVY";
		wp.Name = "DH-X Heavy Blaster Rifle";
		wp.Encumbrance = 7;
		wp.HardPoints = 4;
		wp.Price = 1900;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 10;
		wp.CriticalValue = 3;
		wp.RangeID = 3;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "E11SNIPER";
		wp.Name = "E-11S Sniper Rifle";
		wp.Encumbrance = 6;
		wp.HardPoints = 3;
		wp.Price = 3500;
		wp.Rarity = 7;
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 10;
		wp.CriticalValue = 3;
		wp.RangeID = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.accurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.slow_firing.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "LBR9STUNRIFLE";
		wp.Name = "LBR-9 Stun Rifle";
		wp.Encumbrance = 6;
		wp.HardPoints = 4;
		wp.Price = 2800;
		wp.Rarity = 4;
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 10;
		wp.CriticalValue = 0;
		wp.RangeID = 3;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.disorient.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_damage.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTLTREP";
		wp.Name = "Light Repeating Blaster";
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 11;
		wp.CriticalValue = 3;
		wp.RangeID = 3;
		wp.Encumbrance = 7;
		wp.HardPoints = 4;
		wp.Price = 2250;
		wp.Rarity = 7;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.autofire.ordinal(), 0));
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "SE14RBLASTLTREP";
		wp.Name = "SE-14r Light Repeating Blaster";
		wp.Encumbrance = 2;
		wp.HardPoints = 3;
		wp.Price = 1000;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 6;
		wp.CriticalValue = 3;
		wp.RangeID = 2;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.autofire.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTHVYREP";
		wp.Name = "Heavy Repeating Blaster";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 15;
		wp.CriticalValue = 2;
		wp.RangeID = 3;
		wp.Encumbrance = 9;
		wp.HardPoints = 4;
		wp.Price = 6000;
		wp.Rarity = 8;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.autofire.ordinal(), 0));
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "VXBLASTREP";
		wp.Name = "VX \"Sidewinder\" Repeating Blaster";
		wp.Encumbrance = 8;
		wp.HardPoints = 4;
		wp.Price = 3350;
		wp.Rarity = 7;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 12;
		wp.CriticalValue = 3;
		wp.RangeID = 3;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.autofire.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "HOBBLASTREPHVY";
		wp.Name = "HOB Heavy Repeating Blaster";
		wp.Encumbrance = 10;
		wp.HardPoints = 4;
		wp.Price = 6500;
		wp.Rarity = 8;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 15;
		wp.CriticalValue = 3;
		wp.RangeID = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.autofire.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BOWCAST";
		wp.Name = "Bowcaster";
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 10;
		wp.CriticalValue = 3;
		wp.RangeID = 2;
		wp.Encumbrance = 5;
		wp.HardPoints = 2;
		wp.Price = 1250;
		wp.Rarity = 7;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.knockdown.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTION";
		wp.Name = "Ion Blaster";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 10;
		wp.CriticalValue = 5;
		wp.RangeID = 1;
		wp.Encumbrance = 3;
		wp.HardPoints = 3;
		wp.Price = 250;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.disorient.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "DISRPIS";
		wp.Name = "Disruptor Pistol";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 10;
		wp.CriticalValue = 2;
		wp.RangeID = 1;
		wp.Encumbrance = 2;
		wp.HardPoints = 2;
		wp.Price = 3000;
		wp.Rarity = 6;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "DISRRIF";
		wp.Name = "Disruptor Rifle";
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 10;
		wp.CriticalValue = 2;
		wp.RangeID = 3;
		wp.Encumbrance = 5;
		wp.HardPoints = 4;
		wp.Price = 5000;
		wp.Rarity = 6;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "SLUGPIS";
		wp.Name = "Slugthrower Pistol";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 4;
		wp.CriticalValue = 5;
		wp.RangeID = 1;
		wp.Encumbrance = 1;
		wp.HardPoints = 0;
		wp.Price = 100;
		wp.Rarity = 3;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "SLUGPISASP9";
		wp.Name = "ASP-9 \"Vrelt\" Autopistol";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 4;
		wp.CriticalValue = 5;
		wp.RangeID = 1;
		wp.Encumbrance = 1;
		wp.HardPoints = 0;
		wp.Price = 150;
		wp.Rarity = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.autofire.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "FIVERSLUGPIST";
		wp.Name = "Model C \"Fiver\" Pistol";
		wp.Encumbrance = 2;
		wp.HardPoints = 1;
		wp.Price = 200;
		wp.Rarity = 4;
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 4;
		wp.RangeID = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.accurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "SLUGRIF";
		wp.Name = "Slugthrower Rifle";
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 7;
		wp.CriticalValue = 5;
		wp.RangeID = 2;
		wp.Encumbrance = 5;
		wp.HardPoints = 1;
		wp.Price = 250;
		wp.Rarity = 3;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "SLUGRIFMKV";
		wp.Name = "Mark V \"Sand Panther\" Hunting Rifle";
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 7;
		wp.CriticalValue = 5;
		wp.RangeID = 3;
		wp.Encumbrance = 5;
		wp.HardPoints = 2;
		wp.Price = 1750;
		wp.Rarity = 7;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.accurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "ASSAULTSLUGCARB";
		wp.Name = "FYR Assault Carbine";
		wp.Encumbrance = 4;
		wp.HardPoints = 1;
		wp.Price = 250;
		wp.Rarity = 4;
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 6;
		wp.CriticalValue = 5;
		wp.RangeID = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.autofire.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.inaccurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "SLUGRIFSELSHARD";
		wp.Name = "Selonian Shard Shooter";
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 3;
		wp.RangeID = 2;
		wp.Encumbrance = 4;
		wp.HardPoints = 2;
		wp.Price = 1500;
		wp.Rarity = 7;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.autofire.ordinal(), 0));
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "MODEL77";
		wp.Name = "Model 77 Air Rifle";
		wp.Encumbrance = 3;
		wp.HardPoints = 3;
		wp.Price = 1100;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 6;
		wp.CriticalValue = 0;
		wp.RangeID = 3;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_damage.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "MODEL38";
		wp.Name = "Model 38 Sharpshooter's Rifle";
		wp.Encumbrance = 5;
		wp.HardPoints = 4;
		wp.Price = 3000;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 8;
		wp.CriticalValue = 3;
		wp.RangeID = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.accurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "MODEL38DET";
		wp.Name = "Model 38 Sharpshooter's Rifle (Detonator Round)";
		wp.Encumbrance = 5;
		wp.HardPoints = 4;
		wp.Price = 3000;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 8;
		wp.CriticalValue = 3;
		wp.RangeID = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.accurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.knockdown.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
        weapons.put(wpKey, wp);
        wp = new Weapon();
		wpKey = "HAMMER";
		wp.Name = "KS-23 Hammer";
		wp.Encumbrance = 5;
		wp.HardPoints = 4;
		wp.Price = 1500;
		wp.Rarity = 5;
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 8;
		wp.CriticalValue = 4;
		wp.RangeID = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.knockdown.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BOLA";
		wp.Name = "Bola/Net";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 2;
		wp.CriticalValue = 0;
		wp.RangeID = 1;
		wp.Encumbrance = 1;
		wp.HardPoints = 2;
		wp.Price = 20;
		wp.Rarity = 2;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.knockdown.ordinal(), 0));
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.ensnare.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "FLAME";
		wp.Name = "Flame Projector";
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 8;
		wp.CriticalValue = 2;
		wp.RangeID = 1;
		wp.Encumbrance = 6;
		wp.HardPoints = 2;
		wp.Price = 1000;
		wp.Rarity = 6;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.burn.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "MISS";
		wp.Name = "Missile Tube";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 20;
		wp.CriticalValue = 2;
		wp.RangeID = 4;
		wp.Encumbrance = 7;
		wp.HardPoints = 4;
		wp.Price = 7500;
		wp.Rarity = 8;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.guided.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.prepare.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "NETGUN";
		wp.Name = "AO14 \"Aranea\" Net Gun";
		wp.Encumbrance = 4;
		wp.HardPoints = 2;
		wp.Price = 750;
		wp.Rarity = 5;
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 3;
		wp.CriticalValue = 0;
		wp.RangeID = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.ensnare.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "FRAGGR";
		wp.Name = "Frag Grenade";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 8;
		wp.CriticalValue = 4;
		wp.RangeID = 1;
		wp.Encumbrance = 1;
		wp.HardPoints = 0;
		wp.Price = 50;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "STUNGR";
		wp.Name = "Stun Grenade";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 8;
		wp.CriticalValue = 0;
		wp.RangeID = 1;
		wp.Encumbrance = 1;
		wp.HardPoints = 0;
		wp.Price = 75;
		wp.Rarity = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.disorient.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_damage.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "THERMDET";
		wp.Name = "Thermal Detonator";
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 20;
		wp.CriticalValue = 2;
		wp.RangeID = 1;
		wp.Encumbrance = 1;
		wp.HardPoints = 0;
		wp.Price = 2000;
		wp.Rarity = 8;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.disorient.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "APGREN";
		wp.Name = "Armor Piercing Grenade";
		wp.Encumbrance = 1;
		wp.HardPoints = 0;
		wp.Price = 100;
		wp.Rarity = 0;
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 16;
		wp.CriticalValue = 3;
		wp.RangeID = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "AVMINE";
		wp.Name = "Anti-Vehicle Mine";
		wp.Encumbrance = 4;
		wp.HardPoints = 0;
		wp.Price = 1400;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 25;
		wp.CriticalValue = 3;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "APMINE";
		wp.Name = "Anti-Personnel Mine";
		wp.Encumbrance = 3;
		wp.HardPoints = 0;
		wp.Price = 850;
		wp.Rarity = 3;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 12;
		wp.CriticalValue = 3;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "GLOPGRND";
		wp.Name = "Glop Grenade";
		wp.Encumbrance = 1;
		wp.HardPoints = 0;
		wp.Price = 100;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 0;
		wp.RangeID = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.ensnare.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "INFERGREND";
		wp.Name = "D-24 Inferno Grenade";
		wp.Encumbrance = 1;
		wp.HardPoints = 0;
		wp.Price = 75;
		wp.Rarity = 5;
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 8;
		wp.CriticalValue = 3;
		wp.RangeID = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.burn.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "CONCGREND";
		wp.Name = "G2 Concussion Grenade";
		wp.Encumbrance = 1;
		wp.HardPoints = 0;
		wp.Price = 100;
		wp.Rarity = 5;
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 10;
		wp.CriticalValue = 5;
		wp.RangeID = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.concussive.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.disorient.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "IONGREND";
		wp.Name = "Lightning 22 Ion Grenade";
		wp.Encumbrance = 1;
		wp.HardPoints = 0;
		wp.Price = 65;
		wp.Rarity = 4;
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 10;
		wp.CriticalValue = 5;
		wp.RangeID = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.disorient.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "PLASGREND";
		wp.Name = "NOVA40 Plasma Grenade";
		wp.Encumbrance = 1;
		wp.HardPoints = 0;
		wp.Price = 125;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 12;
		wp.CriticalValue = 3;
		wp.RangeID = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "HVYFRGGREND";
		wp.Name = "Mk.4 Heavy Frag Grenade";
		wp.Encumbrance = 1;
		wp.HardPoints = 0;
		wp.Price = 75;
		wp.Rarity = 5;
		wp.SkillID =  Skill.Skills.ranged_light.ordinal();
		wp.Damage = 9;
		wp.CriticalValue = 4;
		wp.RangeID = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "CONCMISSILEMK10";
		wp.Name = "Mk.10 Concussion Missile";
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 200;
		wp.Rarity = 8;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 14;
		wp.CriticalValue = 4;
		wp.RangeID = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.concussive.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.disorient.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.guided.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "FRAGMISSILEC88";
		wp.Name = "C-88 Fragmentation Missile";
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 75;
		wp.Rarity = 7;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 12;
		wp.CriticalValue = 4;
		wp.RangeID = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.guided.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "PLASMISSILESK44";
		wp.Name = "SK-44 Plasma Missile";
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 160;
		wp.Rarity = 8;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 16;
		wp.CriticalValue = 3;
		wp.RangeID = 2;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.inaccurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "INCENMISSILEC908";
		wp.Name = "C-908 Incendiary Missile";
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 145;
		wp.Rarity = 7;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 10;
		wp.CriticalValue = 3;
		wp.RangeID = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.burn.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.guided.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BARADIUMCHRG";
		wp.Name = "Baradium Charge";
		wp.Encumbrance = 2;
		wp.HardPoints = 0;
		wp.Price = 750;
		wp.Rarity = 5;
		wp.SkillID =  Skill.Skills.mechanics.ordinal();
		wp.Damage = 3;
		wp.CriticalValue = 0;
		wp.RangeID = 3;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "DETONITECHRG";
		wp.Name = "Detonite Charge";
		wp.Encumbrance = 2;
		wp.HardPoints = 0;
		wp.Price = 50;
		wp.Rarity = 2;
		wp.SkillID =  Skill.Skills.mechanics.ordinal();
		wp.Damage = 15;
		wp.CriticalValue = 0;
		wp.RangeID = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "PLASMACHRG";
		wp.Name = "Plasma Charge";
		wp.Encumbrance = 2;
		wp.HardPoints = 0;
		wp.Price = 200;
		wp.Rarity = 5;
		wp.SkillID =  Skill.Skills.mechanics.ordinal();
		wp.Damage = 9;
		wp.CriticalValue = 0;
		wp.RangeID = 2;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "PROTONGRNAD";
		wp.Name = "Proton Grenade";
		wp.Encumbrance = 1;
		wp.HardPoints = 0;
		wp.Price = 60;
		wp.Rarity = 4;
		wp.SkillID =  Skill.Skills.mechanics.ordinal();
		wp.Damage = 10;
		wp.CriticalValue = 0;
		wp.RangeID = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "COMPBOW";
		wp.Name = "Corellian Compound Bow";
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 5;
		wp.RangeID = 2;
		wp.Encumbrance = 3;
		wp.HardPoints = 1;
		wp.Price = 200;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.knockdown.ordinal(), 0));
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "COMPBOWEXP";
		wp.Name = "Corellian Compound Bow (Explosive Tipped)";
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 6;
		wp.CriticalValue = 3;
		wp.RangeID = 2;
		wp.Encumbrance = 3;
		wp.HardPoints = 1;
		wp.Price = 200;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.knockdown.ordinal(), 0));
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "COMPBOWSTUN";
		wp.Name = "Corellian Compound Bow (Stun)";
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 6;
		wp.CriticalValue = 0;
		wp.RangeID = 2;
		wp.Encumbrance = 3;
		wp.HardPoints = 1;
		wp.Price = 200;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.knockdown.ordinal(), 0));
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_damage.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "STYANAX";
		wp.Name = "Styanax Lance";
		wp.SkillID =  Skill.Skills.ranged_heavy.ordinal();
		wp.Damage = 8;
		wp.CriticalValue = 3;
		wp.RangeID = 1;
		wp.Encumbrance = 8;
		wp.HardPoints = 2;
		wp.Price = 200;
		wp.Rarity = 8;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "CZ28FLAME";
		wp.Name = "CZ-28 Flamestrike";
		wp.Encumbrance = 8;
		wp.HardPoints = 3;
		wp.Price = 2000;
		wp.Rarity = 8;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 9;
		wp.CriticalValue = 2;
		wp.RangeID = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.burn.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "FC1FLECHETTE";
		wp.Name = "FC1 Flechette Launcher (Anti-Infantry)";
		wp.Encumbrance = 6;
		wp.HardPoints = 4;
		wp.Price = 2500;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 8;
		wp.CriticalValue = 3;
		wp.RangeID = 2;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.prepare.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "FC1FLECHETTEVEH";
		wp.Name = "FC1 Flechette Launcher (Anti-Vehicle)";
		wp.Encumbrance = 6;
		wp.HardPoints = 4;
		wp.Price = 2500;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 10;
		wp.CriticalValue = 2;
		wp.RangeID = 2;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.prepare.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "GRENADLAUNCHZ50";
		wp.Name = "Z50 Grenade Launcher";
		wp.Encumbrance = 5;
		wp.HardPoints = 3;
		wp.Price = 1250;
		wp.Rarity = 4;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 8;
		wp.CriticalValue = 4;
		wp.RangeID = 2;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BRASS";
		wp.Name = "Brass Knuckles";
		wp.SkillID =  Skill.Skills.brawl.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 4;
		wp.RangeID = 0;
		wp.Encumbrance = 1;
		wp.HardPoints = 0;
		wp.Price = 25;
		wp.Rarity = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.disorient.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "SHOCKGL";
		wp.Name = "Shock Gloves";
		wp.SkillID =  Skill.Skills.brawl.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 5;
		wp.RangeID = 0;
		wp.Encumbrance = 0;
		wp.HardPoints = 1;
		wp.Price = 300;
		wp.Rarity = 2;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLSTKNUK";
		wp.Name = "Blast Knuckles";
		wp.Encumbrance = 2;
		wp.HardPoints = 0;
		wp.Price = 500;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.brawl.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 4;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.inaccurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "VAMBLADES1";
		wp.Name = "S-1 Vamblade (Single)";
		wp.Encumbrance = 2;
		wp.HardPoints = 1;
		wp.Price = 500;
		wp.Rarity = 4;
		wp.SkillID =  Skill.Skills.brawl.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 3;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.defensive.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "VAMBLADE2S1";
		wp.Name = "S-1 Vamblade (Paired)";
		wp.Encumbrance = 4;
		wp.HardPoints = 1;
		wp.Price = 1000;
		wp.Rarity = 4;
		wp.SkillID =  Skill.Skills.brawl.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 3;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.accurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.defensive.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.sunder.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "VIBROKNUK";
		wp.Name = "Vibroknucklers";
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 350;
		wp.Rarity = 4;
		wp.SkillID =  Skill.Skills.brawl.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 2;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "KNIFE";
		wp.Name = "Combat Knife";
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 3;
		wp.RangeID = 0;
		wp.Encumbrance = 1;
		wp.HardPoints = 0;
		wp.Price = 25;
		wp.Rarity = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "CUTLASSCOR";
		wp.Name = "Corellian Cutlass";
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 3;
		wp.RangeID = 0;
		wp.Encumbrance = 2;
		wp.HardPoints = 1;
		wp.Price = 300;
		wp.Rarity = 3;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.defensive.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "GAFF";
		wp.Name = "Gaffi Stick";
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 3;
		wp.RangeID = 0;
		wp.Encumbrance = 3;
		wp.HardPoints = 0;
		wp.Price = 100;
		wp.Rarity = 2;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.defensive.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.disorient.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "FLASHSTICK";
		wp.Name = "Drall Flashstick";
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 0;
		wp.RangeID = 0;
		wp.Encumbrance = 2;
		wp.HardPoints = 2;
		wp.Price = 375;
		wp.Rarity = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.disorient.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_damage.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "FORCEP";
		wp.Name = "Force Pike";
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 2;
		wp.RangeID = 0;
		wp.Encumbrance = 3;
		wp.HardPoints = 3;
		wp.Price = 500;
		wp.Rarity = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.stun_setting.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "LTSABER";
		wp.Name = "Lightsaber";
		wp.SkillID =  Skill.Skills.lightsaber.ordinal();
		wp.Damage = 10;
		wp.CriticalValue = 1;
		wp.RangeID = 0;
		wp.Encumbrance = 1;
		wp.HardPoints = 0;
		wp.Price = 10000;
		wp.Rarity = 10;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.sunder.ordinal(), 0));
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "TRUNCH";
		wp.Name = "Truncheon";
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 5;
		wp.RangeID = 0;
		wp.Encumbrance = 2;
		wp.HardPoints = 0;
		wp.Price = 15;
		wp.Rarity = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.disorient.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "PULSEDRILL";
		wp.Name = "G9-GP Pulse Drill";
		wp.Encumbrance = 5;
		wp.HardPoints = 2;
		wp.Price = 1100;
		wp.Rarity = 4;
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 4;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.inaccurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BEAMDRILLJ7B";
		wp.Name = "J-7b Beamdrill";
		wp.Encumbrance = 6;
		wp.HardPoints = 0;
		wp.Price = 3000;
		wp.Rarity = 5;
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 9;
		wp.CriticalValue = 2;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.inaccurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.sunder.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "ENTRENCHTOOL";
		wp.Name = "Entrenching Tool (Improvised)";
		wp.Encumbrance = 1;
		wp.HardPoints = 0;
		wp.Price = 20;
		wp.Rarity = 1;
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 4;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.inferior.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "GLAIVESEL";
		wp.Name = "Selonian Glaive";
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 3;
		wp.RangeID = 0;
		wp.Encumbrance = 5;
		wp.HardPoints = 3;
		wp.Price = 1200;
		wp.Rarity = 7;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.defensive.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "VIBAX";
		wp.Name = "Vibro-ax";
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 2;
		wp.RangeID = 0;
		wp.Encumbrance = 4;
		wp.HardPoints = 3;
		wp.Price = 750;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.sunder.ordinal(), 0));
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "VIBKN";
		wp.Name = "Vibroknife";
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 2;
		wp.RangeID = 0;
		wp.Encumbrance = 1;
		wp.HardPoints = 2;
		wp.Price = 250;
		wp.Rarity = 3;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "VIBSW";
		wp.Name = "Vibrosword";
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 2;
		wp.RangeID = 0;
		wp.Encumbrance = 3;
		wp.HardPoints = 3;
		wp.Price = 750;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.defensive.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "VIBROSPR";
		wp.Name = "Huntsman Vibrospear";
		wp.Encumbrance = 4;
		wp.HardPoints = 2;
		wp.Price = 950;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 3;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "VIBROSAW";
		wp.Name = "Mk. VIII Vibrosaw";
		wp.Encumbrance = 6;
		wp.HardPoints = 3;
		wp.Price = 1500;
		wp.Rarity = 5;
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 2;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.sunder.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "FUSCUT";
		wp.Name = "Fusion Cutter";
		wp.Encumbrance = 2;
		wp.HardPoints = 0;
		wp.Price = 175;
		wp.Rarity = 2;
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 3;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.burn.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.sunder.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "RYYKBLADE";
		wp.Name = "Ryyk Blade";
		wp.Encumbrance = 3;
		wp.HardPoints = 3;
		wp.Price = 400;
		wp.Rarity = 8;
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 3;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.cumbersome.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.defensive.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.superior.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "VIBROGRTSWRDVX";
		wp.Name = "VX \"Czerhander\" Vibro-Greatsword";
		wp.Encumbrance = 4;
		wp.HardPoints = 3;
		wp.Price = 900;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 2;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.defensive.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.pierce.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "THERMAXMOD7";
		wp.Name = "Model 7 Therm-Ax";
		wp.Encumbrance = 4;
		wp.HardPoints = 3;
		wp.Price = 850;
		wp.Rarity = 5;
		wp.SkillID =  Skill.Skills.melee.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 3;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.burn.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.sunder.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.vicious.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "AUTOBLAST";
		wp.Name = "Auto-Blaster";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 3;
		wp.CriticalValue = 5;
		wp.RangeID = 0;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 3000;
		wp.Rarity = 3;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.autofire.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTCANLT";
		wp.Name = "Light Blaster Cannon";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 4;
		wp.CriticalValue = 4;
		wp.RangeID = 0;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 4000;
		wp.Rarity = 2;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BLASTCANHVY";
		wp.Name = "Heavy Blaster Cannon";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 4;
		wp.RangeID = 0;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 5000;
		wp.Rarity = 3;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "CML";
		wp.Name = "Concussion Missile Launcher";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 6;
		wp.CriticalValue = 3;
		wp.RangeID = 1;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 7500;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.guided.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.slow_firing.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "ACML";
		wp.Name = "Assault Concussion Missile Launcher";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 7;
		wp.CriticalValue = 3;
		wp.RangeID = 1;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 8500;
		wp.Rarity = 6;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.guided.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.inaccurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.slow_firing.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "CMLHK";
		wp.Name = "Hunter Killer Concussion Missile Launcher";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 7;
		wp.CriticalValue = 3;
		wp.RangeID = 1;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 8500;
		wp.Rarity = 7;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.accurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.guided.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "IONLT";
		wp.Name = "Light Ion Cannon";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 4;
		wp.RangeID = 0;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 5000;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.ion.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "IONMED";
		wp.Name = "Medium Ion Cannon";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 6;
		wp.CriticalValue = 4;
		wp.RangeID = 1;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 6000;
		wp.Rarity = 6;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.ion.ordinal(), 0));
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "IONHVY";
		wp.Name = "Heavy Ion Cannon";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 7;
		wp.CriticalValue = 4;
		wp.RangeID = 2;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 7500;
		wp.Rarity = 7;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.ion.ordinal(), 0));
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.slow_firing.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "IONBATT";
		wp.Name = "Battleship Ion Cannon";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 9;
		wp.CriticalValue = 4;
		wp.RangeID = 2;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 8500;
		wp.Rarity = 7;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.ion.ordinal(), 0));
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.slow_firing.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "LASERLT";
		wp.Name = "Light Laser Cannon";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 3;
		wp.RangeID = 0;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 5500;
		wp.Rarity = 4;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "LASERMED";
		wp.Name = "Medium Laser Cannon";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 6;
		wp.CriticalValue = 3;
		wp.RangeID = 0;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 7000;
		wp.Rarity = 4;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "LASERHVY";
		wp.Name = "Heavy Laser Cannon";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 6;
		wp.CriticalValue = 3;
		wp.RangeID = 1;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 7500;
		wp.Rarity = 5;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "LASERPTDEF";
		wp.Name = "Point Defense Laser Cannon";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 3;
		wp.RangeID = 0;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 6500;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.accurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "LASERLONG";
		wp.Name = "Long-Nosed Laser Cannon";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 6;
		wp.CriticalValue = 3;
		wp.RangeID = 0;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 5500;
		wp.Rarity = 7;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.accurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "PTL";
		wp.Name = "Proton Torpedo Launcher";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 8;
		wp.CriticalValue = 2;
		wp.RangeID = 1;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 9000;
		wp.Rarity = 7;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.guided.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.slow_firing.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "LASERQUAD";
		wp.Name = "Quad Laser Cannon";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 3;
		wp.RangeID = 0;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 8000;
		wp.Rarity = 6;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.accurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.linked.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "TRACTLT";
		wp.Name = "Light Tractor Beam";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 3;
		wp.RangeID = 0;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 6000;
		wp.Rarity = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.tractor.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "TRACTMED";
		wp.Name = "Medium Tractor Beam";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 0;
		wp.RangeID = 1;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 8000;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.tractor.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "TRACTHVY";
		wp.Name = "Heavy Tractor Beam";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 0;
		wp.RangeID = 1;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 10000;
		wp.Rarity = 6;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.tractor.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "TURBOLT";
		wp.Name = "Light Turbolaser";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 9;
		wp.CriticalValue = 3;
		wp.RangeID = 2;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 12000;
		wp.Rarity = 7;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.slow_firing.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "TURBOMED";
		wp.Name = "Medium Turbolaser";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 10;
		wp.CriticalValue = 3;
		wp.RangeID = 3;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 15000;
		wp.Rarity = 7;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.slow_firing.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "TURBOHVY";
		wp.Name = "Heavy Turbolaser";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 11;
		wp.CriticalValue = 3;
		wp.RangeID = 3;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 20000;
		wp.Rarity = 8;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.slow_firing.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "ELECHARPOON";
		wp.Name = "Electromagnetic Harpoon";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 0;
		wp.RangeID = 0;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 3000;
		wp.Rarity = 3;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.knockdown.ordinal(), 0));
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.ensnare.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "CONGRENLAUNCH";
		wp.Name = "Concussion Grenade Launcher";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 10;
		wp.CriticalValue = 4;
		wp.RangeID = 4;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 7500;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "PROTONBOMB";
		wp.Name = "Proton Bomb Release Chute";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 7;
		wp.CriticalValue = 4;
		wp.RangeID = 0;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 7500;
		wp.Rarity = 5;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BEAMDRILHVY";
		wp.Name = "Heavy Beamdrill";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 3;
		wp.RangeID = 1;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 7500;
		wp.Rarity = 7;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.inaccurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "BEAMDRIL";
		wp.Name = "Beamdrill";
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 3;
		wp.RangeID = 1;
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 7500;
		wp.Rarity = 7;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "MINIROCKET";
		wp.Name = "Mini-Rocket Launcher";
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 0;
		wp.Rarity = 0;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 3;
		wp.CriticalValue = 4;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.inaccurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "MASSDRIVMSL";
		wp.Name = "Mass Driver Missile Launchers";
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 0;
		wp.Rarity = 0;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 14;
		wp.CriticalValue = 3;
		wp.RangeID = 4;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.inaccurate.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.slow_firing.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "MISSILEPACK";
		wp.Name = "Missile Pack";
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 10000;
		wp.Rarity = 5;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 0;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "MISSILEPACKMINI";
		wp.Name = "Mini-Missile Pack";
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 7000;
		wp.Rarity = 5;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 0;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "MINIMISSILETUBE";
		wp.Name = "MM-XT Mini-Missile Tube";
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 7000;
		wp.Rarity = 5;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 0;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.limited_ammo.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "TRACTOR213";
		wp.Name = "Grappler 213 Tactical Tractor Beam";
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 6200;
		wp.Rarity = 7;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 0;
		wp.RangeID = 0;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "MISSCONCMINI";
		wp.Name = "Concussion Missile (Mini)";
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 400;
		wp.Rarity = 5;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 4;
		wp.CriticalValue = 4;
		wp.RangeID = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.guided.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.slow_firing.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "MISSJAM";
		wp.Name = "Jammer Missile";
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 300;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 0;
		wp.RangeID = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.guided.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.slow_firing.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "MISSDECOY";
		wp.Name = "Decoy Missile";
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 400;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 0;
		wp.RangeID = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.slow_firing.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "MISSJAMMINI";
		wp.Name = "Jammer Missile (Mini)";
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 400;
		wp.Rarity = 6;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 0;
		wp.CriticalValue = 0;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.guided.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.slow_firing.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "MISSUNGROCK";
		wp.Name = "Unguided Rocket";
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 300;
		wp.Rarity = 0;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 5;
		wp.CriticalValue = 3;
		wp.RangeID = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.slow_firing.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);
		wp = new Weapon();
		wpKey = "MISSUNGROCKMINI";
		wp.Name = "Unguided Rocket (Mini)";
		wp.Encumbrance = 0;
		wp.HardPoints = 0;
		wp.Price = 250;
		wp.Rarity = 4;
		wp.SkillID =  Skill.Skills.gunnery.ordinal();
		wp.Damage = 3;
		wp.CriticalValue = 4;
		wp.RangeID = 0;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.blast.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.breach.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		wp.Qualities.add(new ItemQuality(ItemQuality.QualityList.slow_firing.ordinal(), 0));
		wp.Qualities.get(wp.Qualities.size() - 1).Value = 1;
		weapons.put(wpKey, wp);

		
		return weapons;
	}
	
	

	public static NPC ImportNPC(String Filename, HashMap<String, Weapon> weapons) throws XmlPullParserException,
			IOException {
		NPC npc = new NPC();
		

		npc.Characteristics = Characteristic.initializeCharacteristic(0);
		
		HashMap<String, Integer> importChar = new HashMap<String, Integer>();
		importChar.put("BR", 0);
		importChar.put("AG", 1);
		importChar.put("CUN", 2);
		importChar.put("INT", 3);
		importChar.put("WIL", 4);
		importChar.put("PR", 5);
		
		
		HashMap<String, Integer> importSkills = new HashMap<String, Integer>();
		importSkills.put("ASTRO", Skill.Skills.astrogation.ordinal());
		importSkills.put("ATHL", Skill.Skills.athletics.ordinal());
		importSkills.put("BRAWL", Skill.Skills.brawl.ordinal());
		importSkills.put("CHARM", Skill.Skills.charm.ordinal());
		importSkills.put("COERC", Skill.Skills.coercion.ordinal());
		importSkills.put("COMP", Skill.Skills.computers.ordinal());
		importSkills.put("COOL", Skill.Skills.cool.ordinal());
		importSkills.put("COORD", Skill.Skills.coordination.ordinal());
		importSkills.put("CORE", Skill.Skills.core_worlds.ordinal());
		importSkills.put("DECEP", Skill.Skills.deception.ordinal());
		importSkills.put("DISC", Skill.Skills.discipline.ordinal());
		importSkills.put("EDU", Skill.Skills.education.ordinal());
		importSkills.put("GUNN", Skill.Skills.gunnery.ordinal());
		importSkills.put("LEAD", Skill.Skills.leadership.ordinal());
		importSkills.put("LORE", Skill.Skills.lore.ordinal());
		importSkills.put("MECH", Skill.Skills.mechanics.ordinal());
		importSkills.put("MED", Skill.Skills.medicine.ordinal());
		importSkills.put("MELEE", Skill.Skills.melee.ordinal());
		importSkills.put("NEG", Skill.Skills.negotiation.ordinal());
		importSkills.put("OUT", Skill.Skills.outer_rim.ordinal());
		importSkills.put("PERC", Skill.Skills.perception.ordinal());
		importSkills.put("PILOTPL", Skill.Skills.piloting_planetary.ordinal());
		importSkills.put("PILOTSP", Skill.Skills.piloting_space.ordinal());
		importSkills.put("RANGHVY", Skill.Skills.ranged_heavy.ordinal());
		importSkills.put("RANGLT", Skill.Skills.ranged_light.ordinal());
		importSkills.put("RESIL", Skill.Skills.resilience.ordinal());
		importSkills.put("SKUL", Skill.Skills.skulduggery.ordinal());
		importSkills.put("STEAL", Skill.Skills.stealth.ordinal());
		importSkills.put("SW", Skill.Skills.streetwise.ordinal());
		importSkills.put("SURV", Skill.Skills.survival.ordinal());
		importSkills.put("UND", Skill.Skills.underworld.ordinal());
		importSkills.put("VIGIL", Skill.Skills.vigilance.ordinal());
		importSkills.put("XEN", Skill.Skills.xenology.ordinal());
		importSkills.put("LTSABER", Skill.Skills.lightsaber.ordinal());
		importSkills.put("WARF", Skill.Skills.warfare.ordinal());

		HashMap<String, Integer> importRanges = new HashMap<String, Integer>();
		importRanges.put("Engaged", 0);
		importRanges.put("Short", 1);
		importRanges.put("Medium", 2);
		importRanges.put("Long", 3);
		importRanges.put("Extreme", 4);
		
		HashMap<String, Integer> importTalents = new HashMap<String, Integer>();
		importTalents.put("ADV", Talent.TalentIDs.adversary.ordinal());
		importTalents.put("ALLTERDRIV", Talent.TalentIDs.all_terrain_driver.ordinal());
		importTalents.put("ANAT", Talent.TalentIDs.anatomy_lessons.ordinal());
		importTalents.put("ARM", Talent.TalentIDs.armor_master.ordinal());
		importTalents.put("ARMIMP", Talent.TalentIDs.armor_master_improved.ordinal());
		importTalents.put("BACT", Talent.TalentIDs.bacta_specialist.ordinal());
		importTalents.put("BADM", Talent.TalentIDs.bad_motivator.ordinal());
		importTalents.put("BAL", Talent.TalentIDs.balance.ordinal());
		importTalents.put("BAR", Talent.TalentIDs.barrage.ordinal());
		importTalents.put("BASICTRAIN", Talent.TalentIDs.basic_combat_training.ordinal());
		importTalents.put("BLA", Talent.TalentIDs.black_market_contacts.ordinal());
		importTalents.put("BLO", Talent.TalentIDs.blooded.ordinal());
		importTalents.put("BOD", Talent.TalentIDs.body_guard.ordinal());
		importTalents.put("BOUGHT", Talent.TalentIDs.bought_info.ordinal());
		importTalents.put("BRA", Talent.TalentIDs.brace.ordinal());
		importTalents.put("BRI", Talent.TalentIDs.brilliant_evasion.ordinal());
		importTalents.put("BRNGITDWN", Talent.TalentIDs.bring_it_down.ordinal());
		importTalents.put("BURLY", Talent.TalentIDs.burly.ordinal());
		importTalents.put("BYP", Talent.TalentIDs.bypass_security.ordinal());
		importTalents.put("CAREPLAN", Talent.TalentIDs.careful_planning.ordinal());
		importTalents.put("CLEVERSOLN", Talent.TalentIDs.clever_solution.ordinal());
		importTalents.put("COD", Talent.TalentIDs.codebreaker.ordinal());
		importTalents.put("COM", Talent.TalentIDs.command.ordinal());
		importTalents.put("COMMPRES", Talent.TalentIDs.commanding_presence.ordinal());
		importTalents.put("CONF", Talent.TalentIDs.confidence.ordinal());
		importTalents.put("CONT", Talent.TalentIDs.contraption.ordinal());
		importTalents.put("CONV", Talent.TalentIDs.convincing_demeanor.ordinal());
		importTalents.put("COORDASS", Talent.TalentIDs.coordinated_assault.ordinal());
		importTalents.put("CREATKILL", Talent.TalentIDs.creative_killer.ordinal());
		importTalents.put("CRIPV", Talent.TalentIDs.crippling_blow.ordinal());
		importTalents.put("DEAD", Talent.TalentIDs.dead_to_rights.ordinal());
		importTalents.put("DEADACC", Talent.TalentIDs.deadly_accuracy.ordinal());
		importTalents.put("DEADIMP", Talent.TalentIDs.dead_to_rights_improved.ordinal());
		importTalents.put("DEDI", Talent.TalentIDs.dedication.ordinal());
		importTalents.put("DEFDRI", Talent.TalentIDs.defensive_driving.ordinal());
		importTalents.put("DEFSLI", Talent.TalentIDs.defensive_slicing.ordinal());
		importTalents.put("DEFSLIIMP", Talent.TalentIDs.defensive_slicing_improved.ordinal());
		importTalents.put("DEFSTA", Talent.TalentIDs.defensive_stance.ordinal());
		importTalents.put("DEPSHOT", Talent.TalentIDs.debilitating_shot.ordinal());
		importTalents.put("DISOR", Talent.TalentIDs.disorient.ordinal());
		importTalents.put("DODGE", Talent.TalentIDs.dodge.ordinal());
		importTalents.put("DURA", Talent.TalentIDs.durable.ordinal());
		importTalents.put("DYNFIRE", Talent.TalentIDs.dynamic_fire.ordinal());
		importTalents.put("ENDUR", Talent.TalentIDs.enduring.ordinal());
		importTalents.put("EXHPORT", Talent.TalentIDs.exhaust_port.ordinal());
		importTalents.put("EXTRACK", Talent.TalentIDs.expert_tracker.ordinal());
		importTalents.put("FAMSUNS", Talent.TalentIDs.familiar_suns.ordinal());
		importTalents.put("FEARSOME", Talent.TalentIDs.fearsome.ordinal());
		importTalents.put("FERSTR", Talent.TalentIDs.feral_strength.ordinal());
		importTalents.put("FINETUN", Talent.TalentIDs.fine_tuning.ordinal());
		importTalents.put("FIRECON", Talent.TalentIDs.fire_control.ordinal());
		importTalents.put("FLDCOMM", Talent.TalentIDs.field_commander.ordinal());
		importTalents.put("FLDCOMMIMP", Talent.TalentIDs.field_commander_improved.ordinal());
		importTalents.put("FORAG", Talent.TalentIDs.forager.ordinal());
		importTalents.put("FORCERAT", Talent.TalentIDs.force_rating.ordinal());
		importTalents.put("FORCEWILL", Talent.TalentIDs.force_of_will.ordinal());
		importTalents.put("FORMONME", Talent.TalentIDs.form_on_me.ordinal());
		importTalents.put("FRENZ", Talent.TalentIDs.frenzied_attack.ordinal());
		importTalents.put("FULLSTOP", Talent.TalentIDs.full_stop.ordinal());
		importTalents.put("FULLTH", Talent.TalentIDs.full_throttle.ordinal());
		importTalents.put("FULLTHIMP", Talent.TalentIDs.full_throttle_improved.ordinal());
		importTalents.put("FULLTHSUP", Talent.TalentIDs.full_throttle_supreme.ordinal());
		importTalents.put("GALMAP", Talent.TalentIDs.galaxy_mapper.ordinal());
		importTalents.put("GEARHD", Talent.TalentIDs.gearhead.ordinal());
		importTalents.put("GREASE", Talent.TalentIDs.greased_palms.ordinal());
		importTalents.put("GRIT", Talent.TalentIDs.grit.ordinal());
		importTalents.put("HARDHD", Talent.TalentIDs.hard_headed.ordinal());
		importTalents.put("HARDHDIMP", Talent.TalentIDs.hard_headed_improved.ordinal());
		importTalents.put("HEAVYHITTER", Talent.TalentIDs.heavy_hitter.ordinal());
		importTalents.put("HEIGHT", Talent.TalentIDs.heightened_awareness.ordinal());
		importTalents.put("HERO", Talent.TalentIDs.heroic_fortitude.ordinal());
		importTalents.put("HEROICRES", Talent.TalentIDs.heroic_resilience.ordinal());
		importTalents.put("HIDD", Talent.TalentIDs.hidden_storage.ordinal());
		importTalents.put("HOLDTOG", Talent.TalentIDs.hold_together.ordinal());
		importTalents.put("HUNT", Talent.TalentIDs.hunter.ordinal());
		importTalents.put("HUNTERQUARRY", Talent.TalentIDs.hunters_quarry.ordinal());
		importTalents.put("HUNTQIMP", Talent.TalentIDs.hunters_quarry_improved.ordinal());
		importTalents.put("IMPDET", Talent.TalentIDs.improvised_detonation.ordinal());
		importTalents.put("IMPDETIMP", Talent.TalentIDs.improved_improvised_detonation.ordinal());
		importTalents.put("INCITE", Talent.TalentIDs.incite_rebellion.ordinal());
		importTalents.put("INDIS", Talent.TalentIDs.indistinguishable.ordinal());
		importTalents.put("INSIGHT", Talent.TalentIDs.insight.ordinal());
		importTalents.put("INSPRHET", Talent.TalentIDs.inspiring_rhetoric.ordinal());
		importTalents.put("INSPRHETIMP", Talent.TalentIDs.inspiring_rhetoric_improved.ordinal());
		importTalents.put("INSPRHETSUP", Talent.TalentIDs.inspiring_rhetoric_supreme.ordinal());
		importTalents.put("INTENSFOC", Talent.TalentIDs.intense_focus.ordinal());
		importTalents.put("INTENSPRE", Talent.TalentIDs.intense_presence.ordinal());
		importTalents.put("INTIM", Talent.TalentIDs.intimidating.ordinal());
		importTalents.put("INVENT", Talent.TalentIDs.inventor.ordinal());
		importTalents.put("INVIG", Talent.TalentIDs.invigorate.ordinal());
		importTalents.put("ITSNOTTHATBAD", Talent.TalentIDs.its_not_that_bad.ordinal());
		importTalents.put("JUMP", Talent.TalentIDs.jump_up.ordinal());
		importTalents.put("JURY", Talent.TalentIDs.jury_rigged.ordinal());
		importTalents.put("KILL", Talent.TalentIDs.kill_with_kindness.ordinal());
		importTalents.put("KNOCK", Talent.TalentIDs.knockdown.ordinal());
		importTalents.put("KNOWSCH", Talent.TalentIDs.known_schematic.ordinal());
		importTalents.put("KNOWSOM", Talent.TalentIDs.know_somebody.ordinal());
		importTalents.put("KNOWSPEC", Talent.TalentIDs.knowledge_specialization.ordinal());
		importTalents.put("LETHALBL", Talent.TalentIDs.lethal_blows.ordinal());
		importTalents.put("LETSRIDE", Talent.TalentIDs.lets_ride.ordinal());
		importTalents.put("LOOM", Talent.TalentIDs.loom.ordinal());
		importTalents.put("MASDOC", Talent.TalentIDs.master_doctor.ordinal());
		importTalents.put("MASDRIV", Talent.TalentIDs.master_driver.ordinal());
		importTalents.put("MASGREN", Talent.TalentIDs.master_grenadier.ordinal());
		importTalents.put("MASLEAD", Talent.TalentIDs.master_leader.ordinal());
		importTalents.put("MASMERC", Talent.TalentIDs.master_merchant.ordinal());
		importTalents.put("MASPIL", Talent.TalentIDs.master_pilot.ordinal());
		importTalents.put("MASSHAD", Talent.TalentIDs.master_of_shadows.ordinal());
		importTalents.put("MASSLIC", Talent.TalentIDs.master_slicer.ordinal());
		importTalents.put("MASSTAR", Talent.TalentIDs.master_starhopper.ordinal());
		importTalents.put("MASTGREN", Talent.TalentIDs.master_grenadier.ordinal());
		importTalents.put("MENTFOR", Talent.TalentIDs.mental_fortress.ordinal());
		importTalents.put("MUSEUMWORTHY", Talent.TalentIDs.museum_worthy.ordinal());
		importTalents.put("NATBRAW", Talent.TalentIDs.natural_brawler.ordinal());
		importTalents.put("NATCHARM", Talent.TalentIDs.natural_charmer.ordinal());
		importTalents.put("NATDOC", Talent.TalentIDs.natural_doctor.ordinal());
		importTalents.put("NATDRIV", Talent.TalentIDs.natural_driver.ordinal());
		importTalents.put("NATENF", Talent.TalentIDs.natural_enforcer.ordinal());
		importTalents.put("NATHUN", Talent.TalentIDs.natural_hunter.ordinal());
		importTalents.put("NATLEAD", Talent.TalentIDs.natural_leader.ordinal());
		importTalents.put("NATMAR", Talent.TalentIDs.natural_marksman.ordinal());
		importTalents.put("NATNEG", Talent.TalentIDs.natural_negotiator.ordinal());
		importTalents.put("NATOUT", Talent.TalentIDs.natural_outdoorsman.ordinal());
		importTalents.put("NATPIL", Talent.TalentIDs.natural_pilot.ordinal());
		importTalents.put("NATPRO", Talent.TalentIDs.natural_programmer.ordinal());
		importTalents.put("NATROG", Talent.TalentIDs.natural_rogue.ordinal());
		importTalents.put("NATSCH", Talent.TalentIDs.natural_scholar.ordinal());
		importTalents.put("NATTIN", Talent.TalentIDs.natural_tinkerer.ordinal());
		importTalents.put("NOBFOOL", Talent.TalentIDs.nobodys_fool.ordinal());
		importTalents.put("OUTDOOR", Talent.TalentIDs.outdoorsman.ordinal());
		importTalents.put("OVERDEF", Talent.TalentIDs.overwhelm_defenses.ordinal());
		importTalents.put("OVEREM", Talent.TalentIDs.overwhelm_emotions.ordinal());
		importTalents.put("PHYSTRAIN", Talent.TalentIDs.physical_training.ordinal());
		importTalents.put("PIN", Talent.TalentIDs.pin.ordinal());
		importTalents.put("PLAUSDEN", Talent.TalentIDs.plausible_deniability.ordinal());
		importTalents.put("POINTBL", Talent.TalentIDs.point_blank.ordinal());
		importTalents.put("PRECAIM", Talent.TalentIDs.precise_aim.ordinal());
		importTalents.put("PRESPNT", Talent.TalentIDs.pressure_point.ordinal());
		importTalents.put("PWRBLST", Talent.TalentIDs.powerful_blast.ordinal());
		importTalents.put("QUICKDR", Talent.TalentIDs.quick_draw.ordinal());
		importTalents.put("QUICKFIX", Talent.TalentIDs.quick_fix.ordinal());
		importTalents.put("QUICKST", Talent.TalentIDs.quick_strike.ordinal());
		importTalents.put("RAINDEATH", Talent.TalentIDs.rain_of_death.ordinal());
		importTalents.put("RAPREA", Talent.TalentIDs.rapid_reaction.ordinal());
		importTalents.put("RAPREC", Talent.TalentIDs.rapid_recovery.ordinal());
		importTalents.put("REDUNSYS", Talent.TalentIDs.redundant_systems.ordinal());
		importTalents.put("RESEARCH", Talent.TalentIDs.researcher.ordinal());
		importTalents.put("RESOLVE", Talent.TalentIDs.resolve.ordinal());
		importTalents.put("RESPSCHOL", Talent.TalentIDs.respected_scholar.ordinal());
		importTalents.put("SCATH", Talent.TalentIDs.scathing_tirade.ordinal());
		importTalents.put("SCATHIMP", Talent.TalentIDs.scathing_tirade_improved.ordinal());
		importTalents.put("SCATHSUP", Talent.TalentIDs.scathing_tirade_supreme.ordinal());
		importTalents.put("SECWIND", Talent.TalentIDs.second_wind.ordinal());
		importTalents.put("SELDETON", Talent.TalentIDs.selective_detonation.ordinal());
		importTalents.put("SENSDANG", Talent.TalentIDs.sense_danger.ordinal());
		importTalents.put("SENSDEMO", Talent.TalentIDs.sense_emotions.ordinal());
		importTalents.put("SHORTCUT", Talent.TalentIDs.short_cut.ordinal());
		importTalents.put("SIDESTEP", Talent.TalentIDs.side_step.ordinal());
		importTalents.put("SITAWARE", Talent.TalentIDs.situational_awareness.ordinal());
		importTalents.put("SIXSENSE", Talent.TalentIDs.sixth_sense.ordinal());
		importTalents.put("SKILLJOCK", Talent.TalentIDs.skilled_jockey.ordinal());
		importTalents.put("SKILLSLIC", Talent.TalentIDs.skilled_slicer.ordinal());
		importTalents.put("SLEIGHTMIND", Talent.TalentIDs.sleight_of_mind.ordinal());
		importTalents.put("SMOOTHTALK", Talent.TalentIDs.smooth_talker.ordinal());
		importTalents.put("SNIPSHOT", Talent.TalentIDs.sniper_shot.ordinal());
		importTalents.put("SOFTSP", Talent.TalentIDs.soft_spot.ordinal());
		importTalents.put("SOLREP", Talent.TalentIDs.solid_repairs.ordinal());
		importTalents.put("SOUNDINV", Talent.TalentIDs.sound_investments.ordinal());
		importTalents.put("SPARECL", Talent.TalentIDs.spare_clip.ordinal());
		importTalents.put("SPKBIN", Talent.TalentIDs.speaks_binary.ordinal());
		importTalents.put("STALK", Talent.TalentIDs.stalker.ordinal());
		importTalents.put("STEADYNERVES", Talent.TalentIDs.steady_nerves.ordinal());
		importTalents.put("STIMAP", Talent.TalentIDs.stim_application.ordinal());
		importTalents.put("STIMAPIMP", Talent.TalentIDs.stim_application_improved.ordinal());
		importTalents.put("STIMAPSUP", Talent.TalentIDs.stim_application_supreme.ordinal());
		importTalents.put("STIMSPEC", Talent.TalentIDs.stimpack_specialization.ordinal());
		importTalents.put("STNERV", Talent.TalentIDs.steely_nerves.ordinal());
		importTalents.put("STRGEN", Talent.TalentIDs.stroke_of_genius.ordinal());
		importTalents.put("STRONG", Talent.TalentIDs.strong_arm.ordinal());
		importTalents.put("STRSMART", Talent.TalentIDs.street_smarts.ordinal());
		importTalents.put("STUNBL", Talent.TalentIDs.stunning_blow.ordinal());
		importTalents.put("STUNBLIMP", Talent.TalentIDs.stunning_blow_improved.ordinal());
		importTalents.put("SUPREF", Talent.TalentIDs.superior_reflexes.ordinal());
		importTalents.put("SURG", Talent.TalentIDs.surgeon.ordinal());
		importTalents.put("SWIFT", Talent.TalentIDs.swift.ordinal());
		importTalents.put("TACTTRAIN", Talent.TalentIDs.tactical_combat_training.ordinal());
		importTalents.put("TALKTALK", Talent.TalentIDs.talk_the_talk.ordinal());
		importTalents.put("TARGBL", Talent.TalentIDs.targeted_blow.ordinal());
		importTalents.put("TECHAPT", Talent.TalentIDs.technical_aptitude.ordinal());
		importTalents.put("TIME2GO", Talent.TalentIDs.time_to_go.ordinal());
		importTalents.put("TIME2GOIMP", Talent.TalentIDs.time_to_go_improved.ordinal());
		importTalents.put("TINK", Talent.TalentIDs.tinkerer.ordinal());
		importTalents.put("TOUCH", Talent.TalentIDs.touch_of_fate.ordinal());
		importTalents.put("TOUGH", Talent.TalentIDs.toughened.ordinal());
		importTalents.put("TRICK", Talent.TalentIDs.tricky_target.ordinal());
		importTalents.put("TRUEAIM", Talent.TalentIDs.true_aim.ordinal());
		importTalents.put("UNCANREAC", Talent.TalentIDs.uncanny_reactions.ordinal());
		importTalents.put("UNCANSENS", Talent.TalentIDs.uncanny_senses.ordinal());
		importTalents.put("UNSTOP", Talent.TalentIDs.unstoppable.ordinal());
		importTalents.put("UTIL", Talent.TalentIDs.utility_belt.ordinal());
		importTalents.put("UTINNI", Talent.TalentIDs.utinni.ordinal());
		importTalents.put("VEHTRAIN", Talent.TalentIDs.vehicle_combat_training.ordinal());
		importTalents.put("WALKWALK", Talent.TalentIDs.walk_the_walk.ordinal());
		importTalents.put("WELLROUND", Talent.TalentIDs.well_rounded.ordinal());
		importTalents.put("WELLTRAV", Talent.TalentIDs.well_travelled.ordinal());
		importTalents.put("WHEEL", Talent.TalentIDs.wheel_and_deal.ordinal());
		importTalents.put("WORKLIKECHARM", Talent.TalentIDs.works_like_a_charm.ordinal());


		
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser xpp = factory.newPullParser();

		xpp.setInput(new FileReader(Filename));

		int eventType = xpp.getEventType();
		String tagName = "";
		String mode = "";
		String key = "";
		String attribute = "";
		ForcePowers fp = null;
		Weapon wp = null;
		
		while (eventType != XmlPullParser.END_DOCUMENT) {
			
			int depth = xpp.getDepth();
			
			

			
			if (xpp.getName() != null)
				tagName = xpp.getName();
			

			
			if (depth == 2 && eventType == XmlPullParser.START_TAG) {


				mode = tagName;
			}
				
			if (depth == 3 && eventType == XmlPullParser.START_TAG && mode.equals("Attributes")) {

				attribute = tagName;
			}
			if (depth == 2 && eventType == XmlPullParser.START_TAG && mode.equals("ForceAbilities")) {

				fp = new ForcePowers();
			}
			if (depth == 2 && eventType == XmlPullParser.END_TAG && mode.equals("ForceAbilities"))
			{


				if (fp != null) {
					if (fp.Sense.Purchased) npc.Abilities.add(fp.Sense.getDescription());
					if (fp.Move.Purchased) npc.Abilities.add(fp.Move.getDescription());
					if (fp.Influence.Purchased) npc.Abilities.add(fp.Influence.getDescription());
					
					
				}
			}
			if (depth == 3 && eventType == XmlPullParser.START_TAG && mode.equals("SpecialWeapons"))
			{


				
				wp = new Weapon();
				
			}
			if (depth == 3 && eventType == XmlPullParser.END_TAG && mode.equals("SpecialWeapons"))
			{



				if (wp != null) {

					
					npc.Items.add(wp);
				}
			}
			
			if (eventType == XmlPullParser.TEXT) {
				
			
				String text = xpp.getText().trim();
				if (!text.equals("\n") && !text.isEmpty()) { 
					if (tagName.equals("Name") && xpp.getDepth() == 2)
						npc.Name = text;
					if (tagName.equals("Credits") && xpp.getDepth() == 2)
						npc.Credits = Integer.valueOf(text);
                    if (tagName.equals("Portrait") && xpp.getDepth() == 2) {



                        byte[] dataArray = Base64.decode(text, Base64.DEFAULT);
                        npc.setImage(dataArray);

                        Bitmap bmp = npc.getImage();

                        npc.Category = npc.Category;
                    }
					if (tagName.equals("Key") && xpp.getDepth() == 2)
						npc.Key = text;
					if (tagName.equals("UsedExperience")) 
						npc.Experience = Integer.valueOf(text);
					if (tagName.equals("Description") && xpp.getDepth() == 2)
						npc.Description = text;
					if (tagName.equals("Category") && xpp.getDepth() == 2)
						npc.Category = text;
					if (tagName.equals("Type") && xpp.getDepth() == 2)
						if (text.equals("Nemesis")) npc.Type = NPCTypes.Nemesis;
						else if (text.equals("Rival")) npc.Type = NPCTypes.Rival;
						else npc.Type = NPCTypes.Minion;
//					if (tagName.equals("Type") && xpp.getDepth() == 2)
//						npc.setImage(text.getBytes());
					if (tagName.equals("Key") && xpp.getDepth() != 2)
						key = text;
					if (tagName.equals("StartingRanks") 
							|| tagName.equals("SpeciesRanks")
							|| tagName.equals("CareerRanks")
							|| tagName.equals("TalentRanks")
							|| tagName.equals("ObligationRanks")
							|| tagName.equals("DutyRanks")
							|| tagName.equals("AttachRanks")
							|| tagName.equals("ItemRanks")
							|| tagName.equals("PurchasedRanks")
							|| tagName.equals("CharRanks")) {
						

						if (mode.equals("Characteristics"))
							npc.Characteristics.set(importChar.get(key), 
									npc.Characteristics.get(importChar.get(key)) + Integer.valueOf(text)
									);
						if (mode.equals("Skills")) {
							if (importSkills.containsKey(key)) {
								Skill sk = npc.GetSkill(importSkills.get(key));
								npc.SetSkill(importSkills.get(key), sk.Value + Integer.valueOf(text));

							}
							else { 
								android.util.Log.d("hopla", "*******************");
								android.util.Log.d("hopla", "manque " + key);
								android.util.Log.d("hopla", "*******************");
							}
						}
						if (mode.equals("Attributes")) {
							if (attribute.equals("SoakValue"))
								npc.Soak += Integer.valueOf(text);
							if (attribute.equals("WoundThreshold"))
								npc.TotalWounds += Integer.valueOf(text);
							if (attribute.equals("StrainThreshold"))
								npc.TotalStrain += Integer.valueOf(text);
							if (attribute.equals("DefenseMelee"))
								npc.MeleeDefense += Integer.valueOf(text);
							if (attribute.equals("DefenseRanged"))
								npc.RangedDefense += Integer.valueOf(text);
							if (attribute.equals("ForceRating"))
								npc.ForceRating += Integer.valueOf(text);							
						}
						
					}
				
					if (mode.equals( "SpecialWeapons")) {
						if (tagName.equals("Name"))
							wp.Name = text;
						if (tagName.equals("Description"))
							wp.Description = text;
						if (tagName.equals("SkillKey"))
							wp.SkillID = importSkills.get(text);
						if (tagName.equals("Damage"))
							wp.Damage += Integer.valueOf(text);
						if (tagName.equals("DamageAdd"))
							wp.Damage += Integer.valueOf(text);
						if (tagName.equals("Crit"))
							wp.CriticalValue = Integer.valueOf(text);
						if (tagName.equals("Range"))
							wp.RangeID = importRanges.get(text);
					}
					
					if (mode.equals("ForceAbilities")) {
						if (tagName.equals("Key"))
						{
							
							
							if (text.startsWith("MOVE")) {
								
								if (text.endsWith("BASIC"))
									fp.Move.SetPurchased();
								if (text.endsWith("MAGNITUDE"))
									fp.Move.AddMagnitude();
								if (text.endsWith("RANGE"))
									fp.Move.AddRange();
								if (text.endsWith("STRENGTH"))
									fp.Move.AddStrenght();
								if (text.endsWith("DURATION"))
									fp.Move.AddDuration();
								
								if (text.contains("CONTROL"))
								{
									int iControl = Integer.valueOf(text.substring(text.length() - 1));
									fp.Move.AddControl(iControl-1);
								}
								
							}
							else if (text.startsWith("SENSE"))
							{
							
								if (text.endsWith("BASIC"))
									fp.Sense.SetPurchased();
								if (text.endsWith("MAGNITUDE"))
									fp.Sense.AddMagnitude();
								if (text.endsWith("RANGE"))
									fp.Sense.AddRange();
								if (text.endsWith("STRENGTH"))
									fp.Sense.AddStrenght();
								if (text.endsWith("DURATION"))
									fp.Sense.AddDuration();
								
								if (text.contains("CONTROL"))
								{
									int iControl = Integer.valueOf(text.substring(text.length() - 1));
									fp.Sense.AddControl(iControl-1);
								}
							}
							else {
								
								
								if (text.endsWith("BASIC"))
									fp.Influence.SetPurchased();
								if (text.endsWith("MAGNITUDE"))
									fp.Influence.AddMagnitude();
								if (text.endsWith("RANGE"))
									fp.Influence.AddRange();
								if (text.endsWith("STRENGTH"))
									fp.Influence.AddStrenght();
								if (text.endsWith("DURATION"))
									fp.Influence.AddDuration();
								
								if (text.contains("CONTROL"))
								{
									int iControl = Integer.valueOf(text.substring(text.length() - 1));
									fp.Influence.AddControl(iControl-1);
								}
							
							}
						}
					}
					
					if (mode.equals("Description")) {
						if (tagName.equals("CharName"))
							npc.Name = text;
						if (tagName.equals("PlayerName"))
							npc.OwnerName = text;
					}
					
					
					
					if (mode.equals("Weapons")) {
						if (tagName.equals("ItemKey"))
							npc.Items.add(weapons.get(text));						
						
					}
					
					if (mode.equals("Talents")) {
						
						if (tagName.equals("Key")) {
							if (xpp.getDepth() > 4)
								npc.Talents.get(npc.Talents.size() - 1).SelectedSkillID = importSkills.get(text);
							else
								npc.Talents.add(new Talent(importTalents.get(text)));
						}
						if (tagName.equals("Ranks"))
							npc.Talents.get(npc.Talents.size() - 1).TalentValue = Integer.valueOf(text);
						
						
					}
					
					if (mode.equals("Abilities")) {
						
						if (tagName.equals("Name"))
							npc.Abilities.add(text);
						if (tagName.equals("Description"))
							npc.Abilities.set(npc.Abilities.size() - 1, npc.Abilities.get(npc.Abilities.size() - 1) + ": " + text); 
						
						
					}
				}
			}
			eventType = xpp.next();
		}

		return npc;
	}
	
	
	

}
