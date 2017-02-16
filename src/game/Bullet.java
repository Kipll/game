package game;
import java.awt.*;
import java.awt.geom.Point2D;

public class Bullet extends Projectile{
	
	public Bullet(Player player, Point2D.Double pos, Point2D.Double to, double size, double damage, double speed, double immunity, double knockbackLen){
		super(damage, new CircleSector(size, new Point2D.Double(pos.x, pos.y)), new Point2D.Double((to.x-pos.x),(to.y-pos.y)), immunity, player, true, 8.0, true);
		knockbackAmp = knockbackLen;
		addBehaviour(new LinearMotion(speed, to, pos));
	}
	
	public void draw(Graphics2D g, Viewport viewport){
		viewport.drawCircle(getPos(), getSize(), Color.CYAN, g);
	}
}
