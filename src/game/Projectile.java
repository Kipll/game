package game;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;

public abstract class Projectile extends Entity{
	public boolean isDisposable = false;
	public double damage;
	public Point2D.Double knockbackDir;
	public double knockbackAmp;
	//the monsters hit will be immune to this projectile for this amount of time
	public double immunityTime;
	public CircleSector hitbox;
	public Player player;
	public boolean hasLifetime;
	public double lifetime;
	public boolean breaksOnContact;
	
	public Projectile(double damage, CircleSector hitbox, Point2D.Double knockback, double immunityTime, Player player, boolean hasLifetime, double lifetime, boolean breaksOnContact){
		super();
		this.damage = damage;
		this.hitbox = hitbox;
		this.immunityTime = immunityTime;
		this.player = player;
		this.hasLifetime = hasLifetime;
		this.lifetime = lifetime;
		this.breaksOnContact = breaksOnContact;
		this.knockbackAmp = Math.sqrt(knockback.x*knockback.x+knockback.y*knockback.y);
		this.knockbackDir = (knockbackAmp > 0)?(new Point2D.Double(knockback.x / knockbackAmp, knockback.y / knockbackAmp)):(new Point2D.Double(0,0));
	}
	
	@Override
	public Point2D.Double getPos(){return hitbox.center;}
	
	public double getSize(){return hitbox.getRadius();}
	
	public Point2D.Double getKnockback(){
		return new Point2D.Double(knockbackDir.x * knockbackAmp, knockbackDir.y * knockbackAmp);
	}
	
	@Override
	public boolean disposable(){
		return isDisposable;
	}
	
	@Override
	public void update(double delta, Game game){
		if(hasLifetime)
		{
			lifetime-=delta;
			if(lifetime<=0) isDisposable = true;
		}
		
		super.update(delta, game);
		
		ListIterator<Monster> mit = game.monsters.listIterator(0);
		while(mit.hasNext()){
			Monster m = mit.next(); 
			if(hitbox.intersects(m.hitbox)){m.hit(this); if(breaksOnContact){isDisposable = true; break;}}
		}
	}
}
