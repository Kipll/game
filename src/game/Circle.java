package game;
import java.awt.geom.Point2D;

public class Circle{

	private double radius;
	public Point2D.Double center;
	
	private double radiusSq;
	
	public void setRadius(double r){
		double R = Math.max(r,0);
		radius = R;
		radiusSq = R * R;
	}
	public double getRadius(){return radius;}
	public double getRadiusSq(){return radiusSq;}

	public Circle(double radius, Point2D.Double center){
		setRadius(radius);
		this.center = center;
	}
	
	public boolean intersects(Circle c){
		double dx = c.center.x - center.x;
		double dy = c.center.y - center.y;
		return (dx*dx + dy*dy < c.getRadiusSq() + getRadiusSq() + 2*getRadius()*c.getRadius());
	}
	
	public boolean intersects(CircleSector s){
		return s.intersects(this);
	}
}
