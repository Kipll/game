package game;
import java.awt.geom.Point2D;

public class Gravitate implements Behaviour{

	private Point2D.Double acc0;
	private Point2D.Double acc;
	private Point2D.Double v0;
	private Point2D.Double v;
	
	private double accfactor;
	private Point2D.Double towards;
	
	public Gravitate(Point2D.Double towards, double accfactor){
		this.towards = towards;
		this.accfactor = accfactor;
		
		acc0 = new Point2D.Double(0,0);
		acc = new Point2D.Double(0,0);
		v0 = new Point2D.Double(0,0);
		v = new Point2D.Double(0,0);
	}
	
	public boolean disposable(){
		return false;
	}

	public void update(Game game, Point2D.Double pos, double delta){
		acc0.x = acc.x;
		acc0.y = acc.y;
		v0.x = v.x;
		v0.y = v.y;
		
		final double r = Math.sqrt((pos.x - towards.x)*(pos.x - towards.x) + (pos.y - towards.y)*(pos.y - towards.y));
		acc.x = (towards.x - pos.x)*accfactor/r;
		acc.y = (towards.y - pos.y)*accfactor/r;
		
		v.x += (acc.x + acc0.x)*delta/2;
		v.y += (acc.y + acc0.y)*delta/2;
		pos.x += (v.x + v0.x)*delta/2;
		pos.y += (v.y + v0.y)*delta/2;
	}
}
