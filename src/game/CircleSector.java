package game;
import java.awt.geom.Point2D;

public class CircleSector{

	private double radius;
	public Point2D.Double center;
	private double arcStart;
	public double arcLen;
	
	private double radiusSq;
	
	public void setRadius(double r){
		double R = Math.max(r,0);
		radius = R;
		radiusSq = R * R;
	}
	public double getRadius(){return radius;}
	public double getRadiusSq(){return radiusSq;}
	
	public void setArcStart(double a){
		arcStart = a % (2*Math.PI);
	}
	public double getArcStart(){return arcStart;}

	public CircleSector(double radius, Point2D.Double center){
		setRadius(radius);
		this.center = center;
		arcStart = 0;
		arcLen = 2*Math.PI;
	}

	public CircleSector(double radius, Point2D.Double center, double arcStart, double arcLen){
		setRadius(radius);
		this.center = center;
		setArcStart(arcStart);
		this.arcLen = arcLen;
	}
	
	public boolean intersects(Circle c){
		final double Rsq = getRadiusSq();
		final double rsq = c.getRadiusSq();
		final double R = getRadius();
		final double r = c.getRadius();
		final double dx = c.center.x - center.x;
		final double dy = c.center.y - center.y;
		final double dsq = dx*dx+dy*dy;
		if(dsq >= rsq + Rsq + 2*R*r)return false;
		if(dsq <= rsq || Math.abs(arcLen) >= 2*Math.PI)return true;
		double hitrng = 0;
		if(dsq <= rsq + Rsq) hitrng = Math.asin(r/Math.sqrt(dsq));
		else hitrng = Math.acos((Rsq-rsq+dsq)/(2*Math.sqrt(dsq)*R));
		double relativeAngle = Math.atan2(dy,dx);
		
		if(relativeAngle < 0) relativeAngle += 2*Math.PI;
		if(hitrng < 0) hitrng += 2*Math.PI;
		
		if(arcLen<0){
			arcStart += arcLen;
			while(arcStart < 0) arcStart += 2*Math.PI;
			arcLen*=-1;
		}
		double diff = arcStart - relativeAngle + hitrng;
		if(diff < 0) diff += 2*Math.PI;
		return (diff <= hitrng*2) || (2*Math.PI - diff <= arcLen);
	}
}
