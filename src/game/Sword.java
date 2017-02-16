package game;
import java.awt.geom.Point2D;

public class Sword extends Weapon{
	public Game game;
	public Player p;
	public double damage;
	public double len;
	public double arc;
	
	public Sword(Game game, Player p){
		super(0.3, p);
		this.game = game;
		this.p = p;
		
		damage = 10;
		len = 0.4; //used to be 0.75
		arc = Math.PI/2;
	}
	
	@Override
	public void use(Point2D.Double pos){
		if(!usable())return;
		super.use(pos);
		
		double arcStart = Math.atan2(pos.y-p.pos.y,pos.x-p.pos.x)-arc/2;
		while(arcStart<0)arcStart += 2*Math.PI;
		game.spawnEntity(new SwordSwing(p, p.pos, damage, len, arcStart, arc, 0.2));
	}
}
