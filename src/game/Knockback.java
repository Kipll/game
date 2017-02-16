package game;
import java.awt.geom.Point2D;

public class Knockback implements Behaviour{
	
	Point2D.Double displacement;
	double time;
	double timer;
	
	public Knockback(Point2D.Double displacement, double time){
		this.displacement = displacement;
		this.time = time;
		timer = 0;
	}


	public boolean disposable(){
		return timer >= time;
	}
	
	public void update(Game game, Point2D.Double pos, double delta){
		timer += delta;
		
		pos.x += (delta/time) * displacement.x;
		pos.y += (delta/time) * displacement.y;
	}
}
