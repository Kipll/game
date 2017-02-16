package levels;

import java.awt.geom.Point2D.Double;

import game.Animation;
import game.Spritesheet;

public class CardboardBox extends Prop {

	public CardboardBox() {
		/*
		 * this.setCircleHitbox(false); this.setHasHitbox(true);
		 * this.setHitboxLeft(0.1); this.setHitboxTop(0.1);
		 * this.setHitboxRight(0.9); this.setHitboxBottom(0.9);
		 */

		this.setImageLocation("cardboardbox.png");
		//this.setSpritesheet(new Spritesheet(, 0, 0, 65, 65, 1, 1, new int[] { 1 }));
		this.setAnimation(new Animation(this.getSpritesheet(), 0, 0, 0, Animation.AnimationMode.PLAYONCE));
	}

}
