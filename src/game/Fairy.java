package game;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class Fairy extends Projectile{
	
	
	public double size;
	public Point2D.Double pos;
	
	public Fairy(double lifetime, double size, double damage, double hitrate, double acceleration, Point2D.Double pos, Player player, Game game){
		super(damage, new CircleSector(size, pos), new Point2D.Double(0,0), hitrate, player, true, lifetime, false);
		
		addBehaviour(new Gravitate(player.pos, acceleration));
		
		this.size = size;
		this.pos = pos;
	}
	
	public void draw(Graphics2D g, Viewport viewport){
		viewport.drawCircle(pos, size, Color.YELLOW, g);
	}
}
