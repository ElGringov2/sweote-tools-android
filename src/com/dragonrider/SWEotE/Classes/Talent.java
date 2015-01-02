package com.dragonrider.SWEotE.Classes;

import java.util.ArrayList;
import java.util.List;

public class Talent {

	public int SelectedSkillID = 0;
	public int TalentID = 0;
	public int TalentValue = 0;
	
	public void setTalentValue(int talentValue) {
		TalentValue = talentValue;
	}
	public void setTalentID(int talentID) {
		TalentID = talentID;
	}
	
	@Override
	public String toString() {
		String s = GetTalentName(TalentID);
		if (TalentValue != 0) 
			s += " " + String.valueOf(TalentValue);
		s += ": " + GetTalentDesc(TalentID);
		
		String skillName = App.getContext().getString(Skill.SkillRessources[SelectedSkillID]);
		s = s.replace("{COMBATSKILLNAME}", skillName);
		
		return s;
		
				
		
	}
	
	public Talent(){}
	
	public Talent(int TalentID) {
		this.TalentID = TalentID;
	}
	

	public static String toDatabaseString(List<Talent> talents) {
		String s = "";
		
		for (Talent talent : talents) {
			s += String.format("%03d%02d%02d", talent.TalentID, talent.TalentValue, talent.SelectedSkillID);
		}
		
		
		
		return s;
	}
	
	public static ArrayList<Talent> fromDatabaseString(String s) {
		ArrayList<Talent> array = new ArrayList<Talent>();
		for (int i = 0; i < s.length(); i += 7)
		{
			Talent ta = new Talent();
			ta.TalentID = Integer.valueOf(s.substring(i, i+3));
			ta.TalentValue = Integer.valueOf(s.substring(i+3, i+5));
			ta.SelectedSkillID = Integer.valueOf(s.substring(i+5, i+7));
			array.add(ta);
		}
		
		
		return array;
	}
	
	public static Boolean Contains(TalentIDs talentID, NPC npc) {
		for (Talent talent : npc.Talents) {
			if (talent.TalentID == talentID.ordinal()) return true;
		}
		
		return false;
	}
	
	public static int getCount(TalentIDs talentID, NPC npc) {
		for (Talent talent : npc.Talents) {
			if (talent.TalentID == talentID.ordinal()) return talent.TalentValue;
		}
		return 0;
	}
	
	
	
	public enum TalentIDs {adversary,
            anatomy_lessons,
            armor_master,
            armor_master_improved,
            bacta_specialist,
            bad_motivator,
            balance,
            barrage,
            black_market_contacts,
            blooded,
            body_guard,
            brace,
            brilliant_evasion,
            bypass_security,
            codebreaker,
            command,
            confidence,
            contraption,
            convincing_demeanor,
            crippling_blow,
            dead_to_rights,
            dead_to_rights_improved,
            deadly_accuracy,
            dedication,
            defensive_driving,
            defensive_slicing,
            defensive_slicing_improved,
            defensive_stance,
            disorient,
            dodge,
            durable,
            enduring,
            expert_tracker,
            familiar_suns,
            feral_strength,
            field_commander,
            field_commander_improved,
            fine_tuning,
            forager,
            force_rating,
            frenzied_attack,
            full_throttle,
            full_throttle_improved,
            full_throttle_supreme,
            galaxy_mapper,
            gearhead,
            grit,
            hard_headed,
            hard_headed_improved,
            heightened_awareness,
            heroic_fortitude,
            hidden_storage,
            hold_together,
            hunter,
            indistinguishable,
            insight,
            inspiring_rhetoric,
            inspiring_rhetoric_improved,
            inspiring_rhetoric_supreme,
            intense_focus,
            intense_presence,
            intimidating,
            inventor,
            jump_up,
            jury_rigged,
            kill_with_kindness,
            knockdown,
            know_somebody,
            knowledge_specialization,
            known_schematic,
            lets_ride,
            lethal_blows,
            master_doctor,
            master_merchant,
            master_of_shadows,
            master_pilot,
            master_slicer,
            master_starhopper,
            mental_fortress,
            natural_brawler,
            natural_charmer,
            natural_doctor,
            natural_enforcer,
            natural_hunter,
            natural_marksman,
            natural_negotiator,
            natural_outdoorsman,
            natural_pilot,
            natural_programmer,
            natural_rogue,
            natural_scholar,
            natural_tinkerer,
            nobodys_fool,
            outdoorsman,
            overwhelm_emotions,
            plausible_deniability,
            point_blank,
            precise_aim,
            pressure_point,
            quick_draw,
            quick_strike,
            rapid_reaction,
            rapid_recovery,
            redundant_systems,
            researcher,
            resolve,
            respected_scholar,
            scathing_tirade,
            scathing_tirade_improved,
            scathing_tirade_supreme,
            second_wind,
            sense_danger,
            sense_emotions,
            shortcut,
            side_step,
            sixth_sense,
            skilled_jockey,
            skilled_slicer,
            smooth_talker,
            sniper_shot,
            soft_spot,
            solid_repairs,
            spare_clip,
            speaks_binary,
            stalker,
            steely_nerves,
            stim_application,
            stim_application_improved,
            stim_application_supreme,
            street_smarts,
            stroke_of_genius,
            strong_arm,
            stunning_blow,
            stunning_blow_improved,
            superior_reflexes,
            surgeon,
            swift,
            targeted_blow,
            technical_aptitude,
            tinkerer,
            touch_of_fate,
            toughened,
            tricky_target,
            true_aim,
            uncanny_reactions,
            uncanny_senses,
            utility_belt,
            utinni,
            well_rounded,
            wheel_and_deal,
            mechanical_being,
            inorganic,
            droid_traits,
            claws,
            regeneration,
            heat_resistance,
            wookiee_rage,
            lungless_gand,
            gand_with_lungs,
            amphibious,
            vacuum_dweller,
            energy_parasite,
            flyers,
            helium_allergy,
            short_cut,
            selective_detonation,
            stimpack_specialization,
            sound_investments,
            sleight_of_mind,
            situational_awareness,
            all_terrain_driver,
            basic_combat_training,
            bought_info,
            bring_it_down,
            burly,
            careful_planning,
            clever_solution,
            commanding_presence,
            coordinated_assault,
            creative_killer,
            debilitating_shot,
            dynamic_fire,
            fearsome,
            exhaust_port,
            fire_control,
            force_of_will,
            form_on_me,
            full_stop,
            greased_palms,
            heavy_hitter,
            heroic_resilience,
            hunters_quarry,
            hunters_quarry_improved,
            improved_improvised_detonation,
            improvised_detonation,
            incite_rebellion,
            invigorate,
            its_not_that_bad,
            loom,
            master_driver,
            master_grenadier,
            master_leader,
            museum_worthy,
            natural_driver,
            natural_leader,
            overwhelm_defenses,
            physical_training,
            pin,
            powerful_blast,
            quick_fix,
            rain_of_death,
            works_like_a_charm,
            well_travelled,
            walk_the_walk,
            vehicle_combat_training,
            unstoppable,
            time_to_go_improved,
            time_to_go,
            talk_the_talk,
            tactical_combat_training,
            steady_nerves,

            improvised_detonation_improved,
            idealist,
            against_all_odds,
            animal_bond,
            animal_empathy,
            ataru_technique,
            body_guard_improved,
            calming_aura,
            calming_aura_improved,
            center_of_being,
            center_of_being_improved,
            circle_of_shelter,
            comprehend_technology,
            conditioned,
            contingency_plan,
            counterstrike,
            defensive_circle,
            defensive_training,
            disruptive_strike,
            djem_so_deflection,
            draw_closer,
            duelists_training,
            enhanced_leader,
            falling_avalanche,
            feint,
            force_assault,
            force_protection,
            forewarning,
            hawk_bat_swoop,
            healing_trance,
            healing_trance_improved,
            imbue_item,
            intuitive_evasion,
            intuitive_improvements,
            intuitive_shot,
            intuitive_strike,
            keen_eyed,
            knowledge_is_power,
            knowledgeable_healing,
            makashi_finish,
            makashi_flourish,
            makashi_technique,
            master_artisan,
            mental_bond,
            mental_tools,
            multiple_opponents,
            natural_blademaster,
            natural_mystic,
            niman_technique,
            now_you_see_me,
            one_with_the_universe,
            parry,
            parry_improved,
            parry_supreme,
            physician,
            pre_emptive_avoidance,
            quick_movement,
            reflect,
            reflect_improved,
            reflect_supreme,
            resist_disarm,
            saber_swarm,
            saber_throw,
            sarlacc_sweep,
            sense_advantage,
            share_pain,
            shien_technique,
            shroud,
            slippery_minded,
            soresu_technique,
            strategic_form,
            sum_djem,
            terrify,
            terrify_improved,
            the_force_is_my_ally,
            unity_assault,
            valuable_fact,
            bad_cop,
            biggest_fan,
            congenial,
            coordination_dodge,
            distracting_behavior,
            distracting_behavior_improved,
            deceptive_taunt,
            good_cop,
            natural_athlete,
            natural_merchant,
            throwing_credits,
            unrelenting_skeptic,
            unrelenting_skeptic_improved,

	}

	public static String GetTalentName(int id) {

		if (id == TalentIDs.adversary.ordinal())
			return "Adversary";

		if (id == TalentIDs.anatomy_lessons.ordinal())
			return "leçons d'anatomie";

		if (id == TalentIDs.armor_master.ordinal())
			return "Armor Master";

		if (id == TalentIDs.armor_master_improved.ordinal())
			return "Armor Master (Improved)";

		if (id == TalentIDs.bacta_specialist.ordinal())
			return "Bacta Specialist";

		if (id == TalentIDs.bad_motivator.ordinal())
			return "Bad Motivator";

		if (id == TalentIDs.balance.ordinal())
			return "Balance";

		if (id == TalentIDs.barrage.ordinal())
			return "Barrage";

		if (id == TalentIDs.black_market_contacts.ordinal())
			return "Black Market Contacts";

		if (id == TalentIDs.blooded.ordinal())
			return "Blooded";

		if (id == TalentIDs.body_guard.ordinal())
			return "Body Guard";

		if (id == TalentIDs.brace.ordinal())
			return "Brace";

		if (id == TalentIDs.brilliant_evasion.ordinal())
			return "Brilliant Evasion";

		if (id == TalentIDs.bypass_security.ordinal())
			return "Bypass Security";

		if (id == TalentIDs.codebreaker.ordinal())
			return "Codebreaker";

		if (id == TalentIDs.command.ordinal())
			return "Command";

		if (id == TalentIDs.confidence.ordinal())
			return "Confidence";

		if (id == TalentIDs.contraption.ordinal())
			return "Contraption";

		if (id == TalentIDs.convincing_demeanor.ordinal())
			return "Convincing Demeanor";

		if (id == TalentIDs.crippling_blow.ordinal())
			return "Crippling Blow";

		if (id == TalentIDs.dead_to_rights.ordinal())
			return "Dead to Rights";

		if (id == TalentIDs.dead_to_rights_improved.ordinal())
			return "Dead to Rights (Improved)";

		if (id == TalentIDs.deadly_accuracy.ordinal())
			return "Précision mortelle";

		if (id == TalentIDs.dedication.ordinal())
			return "Consécration";

		if (id == TalentIDs.defensive_driving.ordinal())
			return "Defensive Driving";

		if (id == TalentIDs.defensive_slicing.ordinal())
			return "Defensive Slicing";

		if (id == TalentIDs.defensive_slicing_improved.ordinal())
			return "Defensive Slicing (Improved)";

		if (id == TalentIDs.defensive_stance.ordinal())
			return "Defensive Stance";

		if (id == TalentIDs.disorient.ordinal())
			return "Disorient";

		if (id == TalentIDs.dodge.ordinal())
			return "Esquive";

		if (id == TalentIDs.durable.ordinal())
			return "Durable";

		if (id == TalentIDs.enduring.ordinal())
			return "Enduring";

		if (id == TalentIDs.expert_tracker.ordinal())
			return "Expert Tracker";

		if (id == TalentIDs.familiar_suns.ordinal())
			return "Familiar Suns";

		if (id == TalentIDs.feral_strength.ordinal())
			return "Feral Strength";

		if (id == TalentIDs.field_commander.ordinal())
			return "Field Commander";

		if (id == TalentIDs.field_commander_improved.ordinal())
			return "Field Commander (Improved)";

		if (id == TalentIDs.fine_tuning.ordinal())
			return "Fine Tuning";

		if (id == TalentIDs.forager.ordinal())
			return "Forager";

		if (id == TalentIDs.force_rating.ordinal())
			return "Force Rating";

		if (id == TalentIDs.frenzied_attack.ordinal())
			return "Frenzied Attack";

		if (id == TalentIDs.full_throttle.ordinal())
			return "Full Throttle";

		if (id == TalentIDs.full_throttle_improved.ordinal())
			return "Full Throttle (Improved)";

		if (id == TalentIDs.full_throttle_supreme.ordinal())
			return "Full Throttle (Supreme)";

		if (id == TalentIDs.galaxy_mapper.ordinal())
			return "Galaxy Mapper";

		if (id == TalentIDs.gearhead.ordinal())
			return "Gearhead";

		if (id == TalentIDs.grit.ordinal())
			return "Robustesse";

		if (id == TalentIDs.hard_headed.ordinal())
			return "Hard Headed";

		if (id == TalentIDs.hard_headed_improved.ordinal())
			return "Hard Headed (Improved)";

		if (id == TalentIDs.heightened_awareness.ordinal())
			return "Heightened Awareness";

		if (id == TalentIDs.heroic_fortitude.ordinal())
			return "Heroic Fortitude";

		if (id == TalentIDs.hidden_storage.ordinal())
			return "Hidden Storage";

		if (id == TalentIDs.hold_together.ordinal())
			return "Hold Together";

		if (id == TalentIDs.hunter.ordinal())
			return "Hunter";

		if (id == TalentIDs.indistinguishable.ordinal())
			return "Indistinguishable";

		if (id == TalentIDs.insight.ordinal())
			return "Insight";

		if (id == TalentIDs.inspiring_rhetoric.ordinal())
			return "Inspiring Rhetoric";

		if (id == TalentIDs.inspiring_rhetoric_improved.ordinal())
			return "Inspiring Rhetoric (Improved)";

		if (id == TalentIDs.inspiring_rhetoric_supreme.ordinal())
			return "Inspiring Rhetoric (Supreme)";

		if (id == TalentIDs.intense_focus.ordinal())
			return "Intense Focus";

		if (id == TalentIDs.intense_presence.ordinal())
			return "Intense Presence";

		if (id == TalentIDs.intimidating.ordinal())
			return "Intimidant";

		if (id == TalentIDs.inventor.ordinal())
			return "Inventor";

		if (id == TalentIDs.jump_up.ordinal())
			return "Ressort";

		if (id == TalentIDs.jury_rigged.ordinal())
			return "Jury Rigged";

		if (id == TalentIDs.kill_with_kindness.ordinal())
			return "Kill with Kindness";

		if (id == TalentIDs.knockdown.ordinal())
			return "Knockdown";

		if (id == TalentIDs.know_somebody.ordinal())
			return "Know Somebody";

		if (id == TalentIDs.knowledge_specialization.ordinal())
			return "Knowledge Specialization";

		if (id == TalentIDs.known_schematic.ordinal())
			return "Known Schematic";

		if (id == TalentIDs.lets_ride.ordinal())
			return "Let's Ride";

		if (id == TalentIDs.lethal_blows.ordinal())
			return "Coups mortels";

		if (id == TalentIDs.master_doctor.ordinal())
			return "Master Doctor";

		if (id == TalentIDs.master_merchant.ordinal())
			return "Master Merchant";

		if (id == TalentIDs.master_of_shadows.ordinal())
			return "Maitre des Ombres";

		if (id == TalentIDs.master_pilot.ordinal())
			return "Master Pilot";

		if (id == TalentIDs.master_slicer.ordinal())
			return "Master Slicer";

		if (id == TalentIDs.master_starhopper.ordinal())
			return "Master Starhopper";

		if (id == TalentIDs.mental_fortress.ordinal())
			return "Mental Fortress";

		if (id == TalentIDs.natural_brawler.ordinal())
			return "Natural Brawler";

		if (id == TalentIDs.natural_charmer.ordinal())
			return "Natural Charmer";

		if (id == TalentIDs.natural_doctor.ordinal())
			return "Natural Doctor";

		if (id == TalentIDs.natural_enforcer.ordinal())
			return "Natural Enforcer";

		if (id == TalentIDs.natural_hunter.ordinal())
			return "Natural Hunter";

		if (id == TalentIDs.natural_marksman.ordinal())
			return "Natural Marksman";

		if (id == TalentIDs.natural_negotiator.ordinal())
			return "Natural Negotiator";

		if (id == TalentIDs.natural_outdoorsman.ordinal())
			return "Natural Outdoorsman";

		if (id == TalentIDs.natural_pilot.ordinal())
			return "Natural Pilot";

		if (id == TalentIDs.natural_programmer.ordinal())
			return "Natural Programmer";

		if (id == TalentIDs.natural_rogue.ordinal())
			return "Natural Rogue";

		if (id == TalentIDs.natural_scholar.ordinal())
			return "Natural Scholar";

		if (id == TalentIDs.natural_tinkerer.ordinal())
			return "Natural Tinkerer";

		if (id == TalentIDs.nobodys_fool.ordinal())
			return "Nobody's Fool";

		if (id == TalentIDs.outdoorsman.ordinal())
			return "Outdoorsman";

		if (id == TalentIDs.overwhelm_emotions.ordinal())
			return "Overwhelm Emotions";

		if (id == TalentIDs.plausible_deniability.ordinal())
			return "Plausible Deniability";

		if (id == TalentIDs.point_blank.ordinal())
			return "Point Blank";

		if (id == TalentIDs.precise_aim.ordinal())
			return "Visée précise";

		if (id == TalentIDs.pressure_point.ordinal())
			return "Pressure Point";

		if (id == TalentIDs.quick_draw.ordinal())
			return "Dégainement vif";

		if (id == TalentIDs.quick_strike.ordinal())
			return "Assaut Rapide";

		if (id == TalentIDs.rapid_reaction.ordinal())
			return "Rapid Reaction";

		if (id == TalentIDs.rapid_recovery.ordinal())
			return "Rapid Recovery";

		if (id == TalentIDs.redundant_systems.ordinal())
			return "Redundant Systems";

		if (id == TalentIDs.researcher.ordinal())
			return "Researcher";

		if (id == TalentIDs.resolve.ordinal())
			return "Détermination"; // TODO Mettre en place la détermination
									// (traduit trop vite)

		if (id == TalentIDs.respected_scholar.ordinal())
			return "Respected Scholar";

		if (id == TalentIDs.scathing_tirade.ordinal())
			return "Scathing Tirade";

		if (id == TalentIDs.scathing_tirade_improved.ordinal())
			return "Scathing Tirade (Improved)";

		if (id == TalentIDs.scathing_tirade_supreme.ordinal())
			return "Scathing Tirade (Supreme)";

		if (id == TalentIDs.second_wind.ordinal())
			return "Second Wind";

		if (id == TalentIDs.sense_danger.ordinal())
			return "Sense Danger";

		if (id == TalentIDs.sense_emotions.ordinal())
			return "Sense Emotions";

		if (id == TalentIDs.shortcut.ordinal())
			return "Shortcut";

		if (id == TalentIDs.side_step.ordinal())
			return "Side Step";

		if (id == TalentIDs.sixth_sense.ordinal())
			return "Sixth Sense";

		if (id == TalentIDs.skilled_jockey.ordinal())
			return "Skilled Jockey";

		if (id == TalentIDs.skilled_slicer.ordinal())
			return "Skilled Slicer";

		if (id == TalentIDs.smooth_talker.ordinal())
			return "Smooth Talker";

		if (id == TalentIDs.sniper_shot.ordinal())
			return "Tir de sniper";

		if (id == TalentIDs.soft_spot.ordinal())
			return "Soft Spot";

		if (id == TalentIDs.solid_repairs.ordinal())
			return "Solid Repairs";

		if (id == TalentIDs.spare_clip.ordinal())
			return "Spare Clip";

		if (id == TalentIDs.speaks_binary.ordinal())
			return "Speaks Binary";

		if (id == TalentIDs.stalker.ordinal())
			return "Traqueur";

		if (id == TalentIDs.steely_nerves.ordinal())
			return "Steely Nerves";

		if (id == TalentIDs.stim_application.ordinal())
			return "Dose de Stimulant";

		if (id == TalentIDs.stim_application_improved.ordinal())
			return "Dose de Stimulant (Amélioré)";

		if (id == TalentIDs.stim_application_supreme.ordinal())
			return "Dose de Stimulant (Suprême)";

		if (id == TalentIDs.street_smarts.ordinal())
			return "Street Smarts";

		if (id == TalentIDs.stroke_of_genius.ordinal())
			return "Stroke of Genius";

		if (id == TalentIDs.strong_arm.ordinal())
			return "Strong Arm";

		if (id == TalentIDs.stunning_blow.ordinal())
			return "Stunning Blow";

		if (id == TalentIDs.stunning_blow_improved.ordinal())
			return "Stunning Blow (Improved)";

		if (id == TalentIDs.superior_reflexes.ordinal())
			return "Superior Reflexes";

		if (id == TalentIDs.surgeon.ordinal())
			return "Chirurgien";

		if (id == TalentIDs.swift.ordinal())
			return "Swift";

		if (id == TalentIDs.targeted_blow.ordinal())
			return "Coup visé";

		if (id == TalentIDs.technical_aptitude.ordinal())
			return "Technical Aptitude";

		if (id == TalentIDs.tinkerer.ordinal())
			return "Tinkerer";

		if (id == TalentIDs.touch_of_fate.ordinal())
			return "Touch of Fate";

		if (id == TalentIDs.toughened.ordinal())
			return "Endurci";

		if (id == TalentIDs.tricky_target.ordinal())
			return "Tricky Target";

		if (id == TalentIDs.true_aim.ordinal())
			return "True Aim";

		if (id == TalentIDs.uncanny_reactions.ordinal())
			return "Uncanny Reactions";

		if (id == TalentIDs.uncanny_senses.ordinal())
			return "Uncanny Senses";

		if (id == TalentIDs.utility_belt.ordinal())
			return "Utility Belt";

		if (id == TalentIDs.utinni.ordinal())
			return "Utinni!";

		if (id == TalentIDs.well_rounded.ordinal())
			return "Well Rounded";

		if (id == TalentIDs.wheel_and_deal.ordinal())
			return "Wheel and Deal";

		if (id == TalentIDs.mechanical_being.ordinal())
			return "Mechanical Being";

		if (id == TalentIDs.inorganic.ordinal())
			return "Inorganic";

		if (id == TalentIDs.droid_traits.ordinal())
			return "Droid Traits";

		if (id == TalentIDs.claws.ordinal())
			return "Claws";

		if (id == TalentIDs.regeneration.ordinal())
			return "Regeneration";

		if (id == TalentIDs.heat_resistance.ordinal())
			return "Heat Resistance";

		if (id == TalentIDs.wookiee_rage.ordinal())
			return "Wookiee Rage";

		if (id == TalentIDs.lungless_gand.ordinal())
			return "Lungless Gand";

		if (id == TalentIDs.gand_with_lungs.ordinal())
			return "Gand with lungs";

		if (id == TalentIDs.amphibious.ordinal())
			return "Amphibious";

		if (id == TalentIDs.vacuum_dweller.ordinal())
			return "Vacuum Dweller";

		if (id == TalentIDs.energy_parasite.ordinal())
			return "Energy Parasite";

		if (id == TalentIDs.flyers.ordinal())
			return "Flyers";

		if (id == TalentIDs.helium_allergy.ordinal())
			return "Helium Allergy";

		return "UNKNOW";
	}

	public static String GetTalentDesc(int id) {
		if (id == TalentIDs.adversary.ordinal())
			return "Améliorez la difficulté d'un jet d'attaque ciblant le personnage d'autant de fois que le rang.";

		if (id == TalentIDs.anatomy_lessons.ordinal())
			return "Dépensez un point de Destin pour ajouter votre valeur d'Intellect au dégât d'une attaque";

		if (id == TalentIDs.armor_master.ordinal())
			return "When wearing armor, increase total soak value by 1.";

		if (id == TalentIDs.armor_master_improved.ordinal())
			return "When wearing armor with a soak value of 2 or higher, the character increases his defense by 1.";

		if (id == TalentIDs.bacta_specialist.ordinal())
			return "Patients recover 1 additional wound per rank when recovering wounds from Bacta tanks or long term care.";

		if (id == TalentIDs.bad_motivator.ordinal())
			return "Once per session, may perform Bad Motivator action: make a Hard [DDD] Mechanics check to cause one targeted device to spontaneously fail.";

		if (id == TalentIDs.balance.ordinal())
			return "When recovering strain after an encounter, may roll FD per Force Rating. Recover 1 additional strain per LS.";

		if (id == TalentIDs.barrage.ordinal())
			return "Add 1 damage per rank to damage inflicted while using Ranged (Heavy) or Gunnery skills while at Medium or Long range.";

		if (id == TalentIDs.black_market_contacts.ordinal())
			return "When purchasing illegal goods, may reduce rarity by 1 per rank and increase cost by 50% of base cost per reduction.";

		if (id == TalentIDs.blooded.ordinal())
			return "Add [B] per rank to all checks to resist or recover from poisons, venoms or toxins. Reduce duration of ongoing poisons by one round per rank (min 1).";

		if (id == TalentIDs.body_guard.ordinal())
			return "Once per round may perform a Body Guard maneuver to protect an engaged ally: suffer a number of strain (max ranks). Until start of next turn, upgrade difficulty of attacks targeting that ally a number of times equal to strain suffered.";

		if (id == TalentIDs.brace.ordinal())
			return "Manoeuvre: Enlevez [S] par rang sur votre prochain jet";

		if (id == TalentIDs.brilliant_evasion.ordinal())
			return "Once per encounter may perform Brilliant Evasion action: select 1 opponent and make opposed Pilot check to stop opponent attacking for rounds equal to Agility.";

		if (id == TalentIDs.bypass_security.ordinal())
			return "Remove [S] per rank from checks made to disable a security device or open a locked door.";

		if (id == TalentIDs.codebreaker.ordinal())
			return "Remove [S] per rank from checks to break codes or decrypt communications. Decrease difficulty of checks to break codes or decrypt communications by 1.";

		if (id == TalentIDs.command.ordinal())
			return "Add [B] per rank when making Leadership checks. Affected targets add [B] to Discipline checks for next 24 hours.";

		if (id == TalentIDs.confidence.ordinal())
			return "May decrease difficulty of Discipline check to avoid fear by 1 per rank.";

		if (id == TalentIDs.contraption.ordinal())
			return "Once per session, may perform a Contraption action: make a Hard [DDD] Mechanics check to fashion a device to solve a current problem using just the tools and parts on hand.";

		if (id == TalentIDs.convincing_demeanor.ordinal())
			return "Remove [S] per rank from Deceit or Skullduggery checks.";

		if (id == TalentIDs.crippling_blow.ordinal())
			return "Increase the difficulty of next combat check by 1. If check deals damage, target suffers 1 strain whenever they move for remainder of encounter.";

		if (id == TalentIDs.dead_to_rights.ordinal())
			return "Spend 1 Destiny Point to add damage equal to half Agility (round up) to one hit of successful attack made with ship or vehicle mounted weaponry.";

		if (id == TalentIDs.dead_to_rights_improved.ordinal())
			return "Spend 1 Destiny Point to add damage equal to Agility to one hit of successful attack made with ship or vehicle mounted weaponry.";

		if (id == TalentIDs.deadly_accuracy.ordinal())
			return "{COMBATSKILLNAME}: Ajouter vos rangs au dégâts fait avec une arme personnelle.";

		if (id == TalentIDs.dedication.ordinal())
			return "Augmentez d'un point une de vos caractéristiques (max 6)";

		if (id == TalentIDs.defensive_driving.ordinal())
			return "Increase defense of vehicle or starship being piloted by 1 per rank.";

		if (id == TalentIDs.defensive_slicing.ordinal())
			return "When defending computer systems, add [S] per rank to opponent's checks.";

		if (id == TalentIDs.defensive_slicing_improved.ordinal())
			return "Defensive Slicing now upgrades opponent's difficulty once per rank, replacing usual Defensive Slicing benefits.";

		if (id == TalentIDs.defensive_stance.ordinal())
			return "Once per round, may perform Defensive Stance maneuver: suffer a number of strain (max ranks). Until start of next turn, upgrade difficulty of melee attacks targeting the character a number of times equal to strain suffered.";

		if (id == TalentIDs.disorient.ordinal())
			return "After making a successful attack, may spend ADV ADV to disorient the target for a number of rounds equal to ranks.";

		if (id == TalentIDs.dodge.ordinal())
			return "Broutille: Une fois ciblé, subissez X tension (limité par le nombre de rang): améliorez d'autant la difficulté du jet vous ciblant.";

		if (id == TalentIDs.durable.ordinal())
			return "Reduce any Critical Injury suffered by 10 per rank (min. 1).";

		if (id == TalentIDs.enduring.ordinal())
			return "Gain +1 soak value.";

		if (id == TalentIDs.expert_tracker.ordinal())
			return "Remove [S] per rank from checks to find tracks or track targets. Decrease time to track a target by half.";

		if (id == TalentIDs.familiar_suns.ordinal())
			return "Once per session, may perform Familiar Suns maneuver: make a Hard [DDD] Knowledge (Outer Rim) or Knowledge (Core Worlds) check to reveal the current type of planetary environment and other useful information.";

		if (id == TalentIDs.feral_strength.ordinal())
			return "Add 1 damage per rank to damage inflicted while using Brawl or Melee skills.";

		if (id == TalentIDs.field_commander.ordinal())
			return "May perform Field Commander action: make a Medium [DD] Leadership check. A number of allies equal to Presence may immediately suffer 1 strain to perform a free maneuver.";

		if (id == TalentIDs.field_commander_improved.ordinal())
			return "Field Commander action affects allies equal to double Presence, and may spend TRI to allow allies to suffer 1 strain and gain a free action instead.";

		if (id == TalentIDs.fine_tuning.ordinal())
			return "When taking an action to reduce the amount of strain a starship or vehicle suffers, reduce 1 additional strain per rank.";

		if (id == TalentIDs.forager.ordinal())
			return "Remove up to [S] SB from skill checks to find food, water or shelter. Survival checks to forage take half normal time.";

		if (id == TalentIDs.force_rating.ordinal())
			return "Gain +1 Force Rating.";

		if (id == TalentIDs.frenzied_attack.ordinal())
			return "When making a melee or brawl check, may suffer a number of strain (max. ranks) to upgrade the attack an equal number of times.";

		if (id == TalentIDs.full_throttle.ordinal())
			return "May perform a Full Throttle action: make a Hard [DDD] Pilot check to increase vehicle's speed by 1 for a number of rounds equal to Cunning.";

		if (id == TalentIDs.full_throttle_improved.ordinal())
			return "Suffer 1 strain to attempt Full Throttle as a maneuver and decrease its difficulty to Medium [DD].";

		if (id == TalentIDs.full_throttle_supreme.ordinal())
			return "When performing Full Throttle, speed increases by 2 instead of 1.";

		if (id == TalentIDs.galaxy_mapper.ordinal())
			return "Remove [S] per rank from Astrogation checks. Astrogation checks take half normal time.";

		if (id == TalentIDs.gearhead.ordinal())
			return "Remove [S] per rank from Mechanics checks. Halve the credit cost to add mods to attachments.";

		if (id == TalentIDs.grit.ordinal())
			return "Augmentez votre seuil de stress de 1";

		if (id == TalentIDs.hard_headed.ordinal())
			return "When staggered or disoriented, perform the Hard Headed action: make a Daunting [DDDD] Discipline check to remove status. Difficulty decreases by 1 per additional rank (min Easy [D]).";

		if (id == TalentIDs.hard_headed_improved.ordinal())
			return "When incapacitated due to strain exceeding threshold, may make a more difficult Hard Headed action to regain consciousness and reduce strain by 1.";

		if (id == TalentIDs.heightened_awareness.ordinal())
			return "Allies within Short range add [B] to Perception and Vigilance checks. Engaged allies add [B] BD.";

		if (id == TalentIDs.heroic_fortitude.ordinal())
			return "Spend 1 Destiny Point to ignore effects of critical injuries on Brawn or Agility checks until end of encounter.";

		if (id == TalentIDs.hidden_storage.ordinal())
			return "Gain hidden storage in vehicles or equipment that holds items with total encumbrance equal to ranks.";

		if (id == TalentIDs.hold_together.ordinal())
			return "Spend 1 Destiny Point to perform a Hold Together incidental immediately after vehicle or starship takes damage to turn it into system strain.";

		if (id == TalentIDs.hunter.ordinal())
			return "Add [B] per rank to all checks when interacting with beasts or animals. Add +10 per rank to Critical Injury rolls against beasts or animals.";

		if (id == TalentIDs.indistinguishable.ordinal())
			return "Upgrade difficulty of checks to identify character once per rank.";

		if (id == TalentIDs.insight.ordinal())
			return "Perception and Vigilance become career skills.";

		if (id == TalentIDs.inspiring_rhetoric.ordinal())
			return "May perform Inspiring Rhetoric action: make an Average [DD] Leadership check. Each success causes one ally in Short range to recover 1 strain. Spend ADV to allow 1 affected ally to recover 1 additional strain.";

		if (id == TalentIDs.inspiring_rhetoric_improved.ordinal())
			return "Each ally affected by Inspiring Rhetoric gains [B] on all skill checks for a number of rounds equal to ranks in Leadership.";

		if (id == TalentIDs.inspiring_rhetoric_supreme.ordinal())
			return "Suffer 1 strain to perform Inspiring Rhetoric as a maneuver.";

		if (id == TalentIDs.intense_focus.ordinal())
			return "May perform Intense Focus maneuver: suffer 1 strain and upgrade next skill check once.";

		if (id == TalentIDs.intense_presence.ordinal())
			return "Spend 1 destiny point to recover strain equal to Presence rating.";

		if (id == TalentIDs.intimidating.ordinal())
			return "Subissez X points de tension (max rang) pour dégrader la difficulté de d'un jet de Coercicion, ou améliorer la difficulté d'un jet de coercicion contre le personnage.";

		if (id == TalentIDs.inventor.ordinal())
			return "When constructing new items or modifying attachments, add [B] or remove [S] per rank.";

		if (id == TalentIDs.jump_up.ordinal())
			return "Broutille: Une fois par round, que vous soyez assis ou à terre, vous pouvez vous relever au prix d'une broutille.";

		if (id == TalentIDs.jury_rigged.ordinal())
			return "Choose 1 weapon, armor, or other item and give it a permanent improvement.";

		if (id == TalentIDs.kill_with_kindness.ordinal())
			return "Remove [S] per rank from Charm and Leadership checks.";

		if (id == TalentIDs.knockdown.ordinal())
			return "After making a successful melee attack, may spend TRI to knock target prone.";

		if (id == TalentIDs.know_somebody.ordinal())
			return "Once per session, when purchasing legal goods, reduce rarity by 1 per rank.";

		if (id == TalentIDs.knowledge_specialization.ordinal())
			return "Choose 1 Knowledge skill. When making checks with that skill, may spend TRI to gain additional successes equal to ranks.";

		if (id == TalentIDs.known_schematic.ordinal())
			return "Once per session may perform Known Schematic maneuver: make a Hard [DDD] Education check. Success grants familiarity with building or capital ship design.";

		if (id == TalentIDs.lets_ride.ordinal())
			return "Once per round, mount or dismount a vehicle or beast, or enter a cockpit or weapon station on a vehicle, as an incidental.";

		if (id == TalentIDs.lethal_blows.ordinal())
			return "Ajouter +10 par rang au jets de Blessures Critiques infligées";

		if (id == TalentIDs.master_doctor.ordinal())
			return "Once per round, suffer 2 strain to decrease difficulty of next Medicine check by 1.";

		if (id == TalentIDs.master_merchant.ordinal())
			return "When buying or selling goods, or paying off or taking Obligation, may suffer 2 strain to sell for 25% more, buy for 25% less, pay off 1 more Obligation, or take 1 less.";

		if (id == TalentIDs.master_of_shadows.ordinal())
			return "Une fois par round, subissez 2 tensions, et diminuez d'un la difficulté du prochain jet de Discretion.";

		if (id == TalentIDs.master_pilot.ordinal())
			return "Once per round when piloting a starship or vehicle, may suffer 2 strain to perform any action as a maneuver.";

		if (id == TalentIDs.master_slicer.ordinal())
			return "Once per round, may perform Master Slicer incidental to suffer 2 strain and descrease difficulty of Computers or other slicing check by 1 (min Easy [D]).";

		if (id == TalentIDs.master_starhopper.ordinal())
			return "Once per round, suffer 2 strain to decrease difficulty of next Astrogation check by 1 (min. 1).";

		if (id == TalentIDs.mental_fortress.ordinal())
			return "Spend 1 Destiny Point to ignore effects of critical injuries on Intellect or Cunning checks until end of encounter.";

		if (id == TalentIDs.natural_brawler.ordinal())
			return "Once per session may re-roll a Brawl or Melee check.";

		if (id == TalentIDs.natural_charmer.ordinal())
			return "Once per session may re-roll a Charm or Deceit check.";

		if (id == TalentIDs.natural_doctor.ordinal())
			return "Once per session may re-roll a Medicine check.";

		if (id == TalentIDs.natural_enforcer.ordinal())
			return "Once per session may re-roll a Coerce or Streetwise check.";

		if (id == TalentIDs.natural_hunter.ordinal())
			return "Once per session may re-roll a Perception or Vigilance check.";

		if (id == TalentIDs.natural_marksman.ordinal())
			return "Once per session may re-roll a Ranged (Light) or Ranged (Heavy) check.";

		if (id == TalentIDs.natural_negotiator.ordinal())
			return "Once per session may re-roll a Cool or Negotiate check.";

		if (id == TalentIDs.natural_outdoorsman.ordinal())
			return "Once per session may re-roll a Resilience or Survival check.";

		if (id == TalentIDs.natural_pilot.ordinal())
			return "Once per session may re-roll a Pilot (Space) or Gunnery check.";

		if (id == TalentIDs.natural_programmer.ordinal())
			return "Once per session may re-roll a Computers or Astrogation check.";

		if (id == TalentIDs.natural_rogue.ordinal())
			return "Once per session may re-roll a Skulduggery or Stealth check.";

		if (id == TalentIDs.natural_scholar.ordinal())
			return "Once per session may re-roll a Knowledge check.";

		if (id == TalentIDs.natural_tinkerer.ordinal())
			return "Once per session may re-roll a Mechanics check.";

		if (id == TalentIDs.nobodys_fool.ordinal())
			return "May upgrade difficulty once per rank when targeted by Charm, Coerce or Deceit checks.";

		if (id == TalentIDs.outdoorsman.ordinal())
			return "Remove [S] per rank from checks to move through terrain or manage environmental effects. Decrease overland travel times by half.";

		if (id == TalentIDs.overwhelm_emotions.ordinal())
			return "Add FD per Force Rating to Charm, Coerce or Deceit checks. LS and DS add SCS to some checks and FLR to others.";

		if (id == TalentIDs.plausible_deniability.ordinal())
			return "Remove [S] per rank from Coerce and Deceit checks.";

		if (id == TalentIDs.point_blank.ordinal())
			return "Add 1 damage per rank to damage inflicted while using Ranged (Heavy) or Ranged (Light) skills while Engaged or at Short range.";

		if (id == TalentIDs.precise_aim.ordinal())
			return "Manoeuvre: Une fois par round, réduisez la défense de la cible du nombre de rang";

		if (id == TalentIDs.pressure_point.ordinal())
			return "When making a Brawl attack against a living target, instead of dealing damage may deal strain plus additional strain equal to ranks in Medicine (ignores soak).";

		if (id == TalentIDs.quick_draw.ordinal())
			return "Broutille Une fois par round, dégainer une arme.";

		if (id == TalentIDs.quick_strike.ordinal())
			return "Ajouter [BD] aux attaques contre les cibles que vous n'avez pas encore attaqué";

		if (id == TalentIDs.rapid_reaction.ordinal())
			return "Suffer a number of strain (max. ranks) to add an equal number of SCS to initiative checks.";

		if (id == TalentIDs.rapid_recovery.ordinal())
			return "When recovering strain after an encounter, recover 1 additional strain per rank.";

		if (id == TalentIDs.redundant_systems.ordinal())
			return "Once per session, may perform Redundant Systems action: make an Easy [D] Mechanics check to harvest components from functioning device to repair a broken device of similar size and function, without breaking functioning device.";

		if (id == TalentIDs.researcher.ordinal())
			return "Remove [S] per rank from all Knowledge checks. Researching a subject takes half normal time.";

		if (id == TalentIDs.resolve.ordinal())
			return "Quand le personnage subit du stress de façon involontaire, il en reçoit 1 point de moins par rang de Détermination, pour un minimum de 1.";

		if (id == TalentIDs.respected_scholar.ordinal())
			return "May downgrade difficulty of checks to interact with institutes of learning once per rank.";

		if (id == TalentIDs.scathing_tirade.ordinal())
			return "May perform Scathing Tirade action: make an Hard [DDD] Coerce check. Each success causes one enemy in Short range to suffer 1 strain. Spend ADV to cause 1 affected enemy to suffer 1 additional strain.";

		if (id == TalentIDs.scathing_tirade_improved.ordinal())
			return "Each enemy affected by Scathing Tirade suffers [S] on all skill checks for a number of rounds equal to ranks in Coerce.";

		if (id == TalentIDs.scathing_tirade_supreme.ordinal())
			return "Suffer 1 strain to perform Scathing Tirade as a maneuver.";

		if (id == TalentIDs.second_wind.ordinal())
			return "Once per encounter, may use Second Wind incidental to recover strain equal to ranks.";

		if (id == TalentIDs.sense_danger.ordinal())
			return "Once per session, remove [S] SB from any check.";

		if (id == TalentIDs.sense_emotions.ordinal())
			return "Add [B] to Charm, Coerce and Deceit checks unless target is immune to Force powers.";

		if (id == TalentIDs.shortcut.ordinal())
			return "During a chase, add [B] per rank to any checks made to catch or escape opponent.";

		if (id == TalentIDs.side_step.ordinal())
			return "Once per round, may perform a Side Step maneuver: suffer a number of strain (max ranks). Until start of next turn, upgrade difficulty of ranged attacks targeting the character a number of times equal to strain suffered.";

		if (id == TalentIDs.sixth_sense.ordinal())
			return "Gain +1 ranged defense.";

		if (id == TalentIDs.skilled_jockey.ordinal())
			return "Remove [S] per rank from Pilot (Planet) and Pilot (Space) checks.";

		if (id == TalentIDs.skilled_slicer.ordinal())
			return "When making a Computers check, may spend TRI to make further Computers checks within this system as maneuvers.";

		if (id == TalentIDs.smooth_talker.ordinal())
			return "Choose Charm, Coerce, Negotiate or Deceit skill. When making checks with that skill, may spend TRI to gain additional successes equal to ranks.";

		if (id == TalentIDs.sniper_shot.ordinal())
			return "Manoeuvre: Avant de faire un tir, vous pouvez améliorer la difficulté du jet autant de fois que vous le souhaitez, puis augmentez la port�e de l'arme d'autant.";

		if (id == TalentIDs.soft_spot.ordinal())
			return "Spend 1 Destiny Point to add damage equal to Cunning to one hit of successful attack.";

		if (id == TalentIDs.solid_repairs.ordinal())
			return "When taking an action to repair a starship or vehicle, repair 1 additional hull trauma per rank.";

		if (id == TalentIDs.spare_clip.ordinal())
			return "Cannot run out of ammo due to DSP.";

		if (id == TalentIDs.speaks_binary.ordinal())
			return "When directing NPC droids, grant them [B] per rank on checks.";

		if (id == TalentIDs.stalker.ordinal())
			return "Ajouter [BD] par rangs au jets de Coordination et de Discretion.";

		if (id == TalentIDs.steely_nerves.ordinal())
			return "Spend 1 Destiny Point to ignore effects of critical injuries on Willpower or Presence checks until end of encounter.";

		if (id == TalentIDs.stim_application.ordinal())
			return "Action: Faite un jet de Medecine Moyen ([D][D]). En cas de réussite, un allié engagé augmente une caractéristique de 1 point, et subit 4 points de tension jusqu'� la fin de la rencontre.";

		if (id == TalentIDs.stim_application_improved.ordinal())
			return "Action: Faite un jet de Medecine Difficile ([D][D][D]). En cas de réussite, un allié engagé augmente une caractéristique de 1 point, et subit 1 point de tension jusqu'� la fin de la rencontre.";

		if (id == TalentIDs.stim_application_supreme.ordinal())
			return "Chaque [T] augmente de 1 point supplémentaire la caracteristique.";

		if (id == TalentIDs.street_smarts.ordinal())
			return "Remove [S] per rank from Streetwise or Knowledge (Underworld) checks.";

		if (id == TalentIDs.stroke_of_genius.ordinal())
			return "Once per session, make one skill check using Intellect rather than the characteristic linked to that skill.";

		if (id == TalentIDs.strong_arm.ordinal())
			return "Treat thrown weapons as if they had 1 greater range.";

		if (id == TalentIDs.stunning_blow.ordinal())
			return "When making Melee checks, may inflict damage as strain instead of wounds.";

		if (id == TalentIDs.stunning_blow_improved.ordinal())
			return "When dealing strain damage with Melee or Brawl checks, may spend TRI to stagger target for 1 round.";

		if (id == TalentIDs.superior_reflexes.ordinal())
			return "Gain +1 melee defense.";

		if (id == TalentIDs.surgeon.ordinal())
			return "Quand le personnage effectue un test de Médecine pour soigner les blessures d'un patient, ce dernier récupère de 1 blessure de plus par rang de Chirurgien.";

		if (id == TalentIDs.swift.ordinal())
			return "Do not suffer usual penalties for moving through difficult terrain.";

		if (id == TalentIDs.targeted_blow.ordinal())
			return "Dépensez un point de Destin pour ajouter votre agilité aux dégâts d'une touche.";

		if (id == TalentIDs.technical_aptitude.ordinal())
			return "Reduce time needed to complete computer-related tasks by 25% per rank.";

		if (id == TalentIDs.tinkerer.ordinal())
			return "May add 1 additional hard point to an item per rank (max 1 additional hard point per item).";

		if (id == TalentIDs.touch_of_fate.ordinal())
			return "Once per session, add [B][B] to any check.";

		if (id == TalentIDs.toughened.ordinal())
			return "Augmentez votre seuil de blessure de 2.";

		if (id == TalentIDs.tricky_target.ordinal())
			return "Vehicle or starship piloted counts as having silhouette 1 lower when attacked.";

		if (id == TalentIDs.true_aim.ordinal())
			return "Once per round, may perform True Aim maneuver: gain benefits of aiming and upgrade combat check once per rank.";

		if (id == TalentIDs.uncanny_reactions.ordinal())
			return "Add [B] per rank to Vigilance checks.";

		if (id == TalentIDs.uncanny_senses.ordinal())
			return "Add [B] per rank to Perception checks.";

		if (id == TalentIDs.utility_belt.ordinal())
			return "Spend 1 Destiny Point to perform Utility Belt incidental: produce a non-weapon tool from a utility belt or satchel, with a rarity no greater than 3.";

		if (id == TalentIDs.utinni.ordinal())
			return "Remove [S] per rank from checks to find of scavenge items or gear. Such checks take half normal time.";

		if (id == TalentIDs.well_rounded.ordinal())
			return "Choose any 2 skills. They permanently become class skills.";

		if (id == TalentIDs.wheel_and_deal.ordinal())
			return "When selling goods legally, gain 10% more credits per rank.";

		if (id == TalentIDs.mechanical_being.ordinal())
			return "Droids cannot become Force sensitive, nor acquire a Force Rating by any means. Droids cannot use Force powers, and also cannot be affected by mind-altering Force powers.";

		if (id == TalentIDs.inorganic.ordinal())
			return "You do not gain benefits of recovering with a bacta tank, stimpack, or medicine skill checks. You do recover naturally by resting. Droids need to be tended to with a mechanics check rather than a medicine check. Repair kits act like stimpacks.";

		if (id == TalentIDs.droid_traits.ordinal())
			return "Droids do not need to eat, sleep, or breath, and are unaffected by toxins or poisons. Droids have a cybernetic implant cap of 6 instead of their Brawn rating.";

		if (id == TalentIDs.claws.ordinal())
			return "When you make Brawl checks to deal damage to an opponent, you deal +1 damage and have a Critical Rating of 3.";

		if (id == TalentIDs.regeneration.ordinal())
			return "Whenever you would recover one or more wounds from natural rest or recuperation in a bacta tank, you recover one additional wound. This does not occur when receiving first aid or medical treatment from a character, or when using a stimpack.";

		if (id == TalentIDs.heat_resistance.ordinal())
			return "When making skill checks, you may remove [S] imposed due to arid or hot environmental conditions.";

		if (id == TalentIDs.wookiee_rage.ordinal())
			return "When a Wookiee has suffered any wounds, he deals +1 damage to his Brawl and Melee attacks. When a Wookiee is Critically Injured, he deals +2 damage to his Brawl and Melee attacks.";

		if (id == TalentIDs.lungless_gand.ordinal())
			return "Immune to suffocation";

		if (id == TalentIDs.gand_with_lungs.ordinal())
			return "Oxygen environments are a Rating 8 dangerous atmosphere. +10 XP and free Ammonia respirator";

		if (id == TalentIDs.amphibious.ordinal())
			return "Can breathe underwater, and never suffer penalties for travelling through water";

		if (id == TalentIDs.vacuum_dweller.ordinal())
			return "Can survive in vacuum without penalty, and can move in vacuum environment";

		if (id == TalentIDs.energy_parasite.ordinal())
			return "Can make an Average (2) Coordination check to latch into any starships or vehicule they are engaged with. The ship/vehicule suffer 1 system strain per user attached each day, and handling is reduced by 1.";

		if (id == TalentIDs.flyers.ordinal())
			return "Can fly (see p202)";

		if (id == TalentIDs.helium_allergy.ordinal())
			return "When exposed to helium, suffer 1 wound per round ignoring soak.";

		return "";
	}

}
