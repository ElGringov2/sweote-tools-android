package com.dragonrider.SWEotE.Classes;

import com.dragonrider.SWEotE.R;

public class ForcePowers {

	public class ForcePower {

		private String baseName;
		public int RangeUpgrades;
		public int MagnitudeUpgrades;
		public int[] ControlUpgrades = { 0, 0, 0, 0 };
		public int StrenghtUpgrades;
		public int DurationUpgrades;
		public boolean Purchased = false;
		
		public void AddRange(){
			this.RangeUpgrades++;
		}
		public void AddStrenght(){
			this.StrenghtUpgrades++;
		}
		public void AddControl(int ControlID){
			this.ControlUpgrades[ControlID] = 1;
		}
		public void AddDuration() {
			this.DurationUpgrades++;
		}
		public void AddMagnitude() {
			this.MagnitudeUpgrades++;
		}
		public void SetPurchased() {
			this.Purchased = true;
		}

		@Override
		public String toString() {

			return super.toString();
		}

		public ForcePower(String baseName) {
			this.baseName = baseName;
		}

		public String getDescription() {
			String sReturn = App.getContext().getString(R.string.force_power)
					+ " " + baseName + ": \n";
			if (baseName.equals(App.getContext().getString(
					R.string.force_sense_basic))) {

				int[] range1 = { R.string.range_short, 
						R.string.range_medium,
						R.string.range_long, 
						R.string.range_extreme };
				int[] range2 = { R.string.range_engaged, 
						R.string.range_short,
						R.string.range_medium, 
						R.string.range_long,
						R.string.range_extreme };
				sReturn += App.getContext().getString(
						R.string.force_sense_description1);
				if (RangeUpgrades > 0)
					sReturn += String.format(
							App.getContext().getString(
									R.string.force_sense_rangeupgrade),
							range1[RangeUpgrades]);
				sReturn += "\n"
						+ App.getContext().getString(
								R.string.force_sense_description2);
				if (RangeUpgrades > 0)
					sReturn += String.format(
							App.getContext().getString(
									R.string.force_sense_rangeupgrade),
							range2[RangeUpgrades]);
				if (MagnitudeUpgrades > 0)
					sReturn += String.format(
							App.getContext().getString(
									R.string.force_sense_magnitudeupgrade),
							MagnitudeUpgrades);

				if (ControlUpgrades[0] == 1) {
					sReturn += "\n"
							+ String.format(
									App.getContext().getString(
											R.string.force_sense_control0),
									StrenghtUpgrades == 1 ? App.getContext()
											.getString(R.string.force_two_time)
											: App.getContext().getString(
													R.string.force_one_time),
									DurationUpgrades == 1 ? App.getContext()
											.getString(R.string.force_of_two)
											: App.getContext().getString(
													R.string.force_of_one));
				}
				if (ControlUpgrades[1] == 1) {
					sReturn += "\n"
							+ App.getContext().getString(
									R.string.force_sense_control1);
					if (RangeUpgrades > 0)
						sReturn += String.format(
								App.getContext().getString(
										R.string.force_sense_rangeupgrade),
								range2[RangeUpgrades]);
					if (MagnitudeUpgrades > 0)
						sReturn += String.format(
								App.getContext().getString(
										R.string.force_sense_magnitudeupgrade),
								MagnitudeUpgrades);

				}
				if (ControlUpgrades[2] == 1) {
					sReturn += "\n"
							+ String.format(
									App.getContext().getString(
											R.string.force_sense_control2),
									StrenghtUpgrades == 1 ? App.getContext()
											.getString(R.string.force_two_time)
											: App.getContext().getString(
													R.string.force_one_time),
									DurationUpgrades == 1 ? App.getContext()
											.getString(R.string.force_two)
											: App.getContext().getString(
													R.string.force_one));

				}

			}

			if (baseName.equals(App.getContext().getString(R.string.force_move_basic))) {
				int[] range1 = { R.string.range_short, R.string.range_medium, R.string.range_long, R.string.range_extreme };
				sReturn += App.getContext().getString(R.string.force_move_description);
				if (RangeUpgrades > 0)
					sReturn += String.format(App.getContext().getString(R.string.force_move_rangeupgrade),range1[RangeUpgrades]);
				if (MagnitudeUpgrades > 0)
					sReturn += String.format(App.getContext().getString(R.string.force_move_magnitudeupgrade),MagnitudeUpgrades);
				if (StrenghtUpgrades > 0)
					sReturn += String.format(App.getContext().getString(R.string.force_move_strenghtupgrade),StrenghtUpgrades);

				if (ControlUpgrades[0] == 1) 
					sReturn += "\n" + App.getContext().getString(R.string.force_move_control0);
				if (ControlUpgrades[1] == 1) 
					sReturn += "\n" + App.getContext().getString(R.string.force_move_control1);
				if (ControlUpgrades[2] == 1) 
					sReturn += "\n" + App.getContext().getString(R.string.force_move_control2);
				
						
			}

			return sReturn;
		}
	}

	public ForcePower Sense = new ForcePower(App.getContext().getString(
			R.string.force_sense_basic));
	public ForcePower Move = new ForcePower(App.getContext().getString(
			R.string.force_move_basic));
	public ForcePower Influence = new ForcePower(App.getContext().getString(
			R.string.force_sense_basic));

}
