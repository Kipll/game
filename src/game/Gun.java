package game;
import java.awt.geom.Point2D;

public class Gun extends Weapon{
	public Game game;
	public Player p;
	public double bulletdamage;
	public double bulletspeed;
	public double bulletsize;
	public double immunity;
	public double knockbackLen;
	
	public Gun(Game game, Player p){
		super(0.1, p);
		this.game = game;
		this.p = p;
		
		bulletdamage = 4.1;
		bulletspeed = 4.2;
		bulletsize = 0.02;
		immunity = 0.15;
		knockbackLen = 0.04;
	}
	
	@Override
	public void use(Point2D.Double pos){
		if(!usable())return;
		super.use(pos);
		
		game.spawnEntity(new Bullet(p, p.pos, pos, bulletsize, bulletdamage, bulletspeed, immunity, knockbackLen));
	}
}
