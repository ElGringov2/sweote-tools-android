package com.dragonrider.SWEotE.Classes;




public class ShipFighterTemplate {

	public String Name = "";
	private Ship BaseShip;
	private NPC[] Pilots;
	private NPC[] Copilots ;
	private NPC[] Crews ;
	
	public ShipFighterTemplate (String TemplateName, Ship BaseShip, String PilotNames, String CopilotsNames, String CrewNames) {
		this.Name = TemplateName;
		setBaseShip(BaseShip);
		String[] split;
		int i;
		split = PilotNames.split("[|]");
		i = -1;
		for (String string : split) {
			i++;
			setPilot(string, i);
		}
		split = CopilotsNames.split("[|]");
		i = -1;
		for (String string : split) {
			i++;
			setCopilot(string, i);
		}
		split = CrewNames.split("[|]");
		i = -1;
		for (String string : split) {
			i++;
			setCrew(string, i);
		}
	}
	
	

	
	public ShipFighterTemplate (String TemplateName, String BaseShipName, String PilotNames, String CopilotsNames, String CrewNames) {
		this.Name = TemplateName;
		setBaseShip(BaseShipName);
		String[] split;
		int i;
		split = PilotNames.split("[|]");
		i = -1;
		for (String string : split) {
			if (string.equals("")) continue;
			i++;
			setPilot(string, i);
		}
		split = CopilotsNames.split("[|]");
		i = -1;
		for (String string : split) {
			if (string.equals("")) continue;
			i++;
			setCopilot(string, i);
		}
		split = CrewNames.split("[|]");
		i = -1;
		for (String string : split) {
			if (string.equals("")) continue;
			i++;
			setCrew(string, i);
		}
	}

	public Ship getBaseShip() {
		
		return BaseShip;
	}

	public void setBaseShip(String ShipName) {
		Database db = new Database(App.getContext());
	
		Ship s = db.GetShipByName(ShipName);
		if (s == null) return;
		setBaseShip(s);
		
	}
	
	public void setBaseShip(Ship baseShip) {
		

		if (baseShip == null) {
			Pilots = new NPC[0];
			Copilots = new NPC[0];
			Crews = new NPC[0];
			return;
		}
		
		BaseShip = baseShip;
		Pilots = new NPC[baseShip.Pilots];
		Copilots = new NPC[baseShip.CoPilots];
		Crews = new NPC[baseShip.Crew];
				
	}

	public NPC[] getPilots() {
		return Pilots;
	}

	public void setPilot(NPC Pilot, int Position){
		if (Pilot == null) return;
		Pilots[Position] = Pilot;
	}
	public void setPilot(String PilotName, int Position) {
		Database db = new Database(App.getContext());
		NPC pilot = db.GetNPCbyName(PilotName);
		setPilot(pilot, Position);
	}
	
	public NPC[] getCopilots() {
		return Copilots;
	}

	public void setCopilot(NPC Copilot, int Position){
		if (Copilot == null) return;
		Copilots[Position] = Copilot;
	}
	public void setCopilot(String CopilotName, int Position) {
		Database db = new Database(App.getContext());
		NPC Copilot = db.GetNPCbyName(CopilotName);
		setCopilot(Copilot, Position);
	}
	
	public NPC[] getCrew() {
		return Crews;
	}

	public void setCrew(NPC Crew, int Position){
		if (Crew == null) return;
		Crews[Position] = Crew;
	}
	public void setCrew(String CrewName, int Position) {
		Database db = new Database(App.getContext());
		NPC crew = db.GetNPCbyName(CrewName);
		setCrew(crew, Position);
	}

	
	

}
