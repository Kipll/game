package levels;

import java.util.ArrayList;

public abstract class Wave {
	ArrayList<ArrayList<game.Monster>> gateList = new ArrayList<ArrayList<game.Monster>>();
	
	public Wave() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<game.Monster> getMonsters (int gateID ){
		return gateList.get(gateID);		
	}

}
