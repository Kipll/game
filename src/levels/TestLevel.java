package levels;

import game.Game;

public class TestLevel extends Level {

	public TestLevel(Game g) {
		
		this.addProp(new Wall(0.5, 1, 1, 1, g));
	}

}
