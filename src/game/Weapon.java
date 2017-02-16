package game;
import java.awt.geom.Point2D;

public abstract class Weapon{
	public double cooldown;
	public double cooldownTimer;
	
	public Player player;
	
	public Weapon(double cooldown, Player player){
		this.cooldown = cooldown;
		this.player = player;
		
		this.cooldownTimer = 0;
	}

	public void update(double delta){
		if(cooldownTimer>0)cooldownTimer-=delta;
	}
	
	public boolean usable(){
		return cooldownTimer<=0;
	}
	
	public void use(Point2D.Double pos){
		cooldownTimer = cooldown;
	}
}
