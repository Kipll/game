package game;
import java.awt.geom.Point2D;

public class LinearHome implements Behaviour{

	public double speed;
	public Point2D.Double towards;
	
	public LinearHome(double speed, Point2D.Double towards){
		this.speed = speed;
		this.towards = towards;
	}
	
	public boolean disposable(){
		return false;
	}
	
	public void update(Game game, Point2D.Double pos, double delta){
		
		final double r = Math.sqrt((pos.x - towards.x)*(pos.x - towards.x) + (pos.y - towards.y)*(pos.y - towards.y));
		pos.x += (towards.x-pos.x)*speed*delta/r;
		pos.y += (towards.y-pos.y)*speed*delta/r;
	}
}
