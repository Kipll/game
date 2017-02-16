package game;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class SwordSwing extends Projectile{
	
	public Point2D.Double pos;
	public double len;
	public double arcStart;
	public double arc;
	
	public SwordSwing(Player player, Point2D.Double pos, double damage, double len, double arcStart, double arc, double lifetime){
		super(damage, new CircleSector(len, pos, arcStart, arc), new Point2D.Double(0,0), lifetime, player, true, lifetime, false);
		
		this.pos = pos;
		this.len = len;
		this.arcStart = arcStart;
		this.arc = arc;
	}
	
	public void draw(Graphics2D g, Viewport viewport){
		g.setColor(Color.CYAN);
		Point coord = viewport.toScreenCoord(new Point2D.Double(pos.x-len, pos.y-len));
		g.fillArc(coord.x, coord.y, viewport.scaleToScreen(2*len), viewport.scaleToScreen(2*len), -(int)(arcStart*180/Math.PI), -(int)(arc*180/Math.PI));
	}
}
