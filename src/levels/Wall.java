/**
 * 
 */
package levels;

import java.awt.geom.Rectangle2D;

import game.Animation;
import game.Game;
import game.Viewport;

/**
 * @author Luke
 *
 */
public class Wall extends Prop {

	/**
	 * 
	 */
	public Wall(double h, double w, double x, double y, Game g) {
		/*
		 * this.setCircleHitbox(false); this.setHasHitbox(true);
		 * this.setHitboxLeft(0.0); this.setHitboxTop(0.0);
		 * this.setHitboxRight(1.0); this.setHitboxBottom(1.0);
		 */
		this.setImageLocation("wall.png");
		this.setDestination(new Rectangle2D.Double(h, w, x, y));
		this.setAnimation(new Animation(g.getSprite(Game.SPRITESHEET.MONSTER), 0, 0, 0.1, Animation.AnimationMode.PLAYONCE));

	}

}
