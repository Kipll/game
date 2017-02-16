package levels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Animation;
import game.Circle;
import game.Game;
import game.Spritesheet;
import game.Viewport;
import game.Game.SPRITESHEET;

public abstract class Prop extends game.Entity {

	private static final String imageFolder = "../Resources/Images/";
	private String imageLocation; // e.g. "monster.png"

	// private boolean hasHitbox;
	// private double width; //internal width not number of pixels
	// private double height;
	// private boolean circleHitbox;
	// private double hitboxTop, hitboxBottom, hitboxLeft, hitboxRight;
	// private Circle hitboxCircle;
	// private Point2D.Double coordinates;

	private BufferedImage image;
	private Rectangle2D.Double dest;
	private Spritesheet ss;
	private Animation anim;

	public Prop() {

	}

	@Override
	public Double getPos() {
		// TODO Auto-generated method stub
		return null;
	}

	protected Rectangle2D.Double getDestination() {
		return dest;
	}

	protected void setDestination(Rectangle2D.Double dest) {
		this.dest = dest;
	}

	protected Spritesheet getSpritesheet() {
		return ss;
	}

	protected void setSpritesheet(Spritesheet ss) {
		this.ss = ss;
	}

	protected Animation getAnimation() {
		return anim;
	}

	protected void setAnimation(Animation anim) {
		this.anim = anim;
	}

	protected static String getImagefolder() {
		return imageFolder;
	}

	protected void setImage(BufferedImage image) {
		this.image = image;
	}

	public Spritesheet getSprite() {
		return ss;
	}

	@Override
	public boolean disposable() {
		return false;
	}

	@Override
	public void draw(Graphics2D g, Viewport viewport) {
		viewport.drawSprite(dest, anim, g);
	}

	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @return the full address of imageLocation
	 */
	public String getImageLocationFull() {
		return imageFolder + imageLocation;
	}

	/**
	 * @param imageLocation
	 *            the imageLocation to set
	 */
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	/**
	 * @return the imageLocation
	 */
	public String getImageLocation() {
		return imageLocation;
	}

	/*
	 * /**
	 * 
	 * @param hitboxRight the hitboxRight to set
	 *//*
		 * public void setHitboxCoords(double hitboxTop, double hitboxBottom,
		 * double hitboxLeft, double hitboxRight) { this.hitboxTop = hitboxTop;
		 * this.hitboxBottom = hitboxBottom; this.hitboxLeft = hitboxLeft;
		 * this.hitboxRight = hitboxRight; }
		 */

}
