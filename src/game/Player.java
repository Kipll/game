package game;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import game.Animation.AnimationMode;

public class Player extends Entity{
	public KeyboardInput keyboard;
	public MouseInput mouse;
	public Viewport viewport;

	public Point2D.Double pos;
	public double size;
	public double speed;
	public Game game;
	public Circle hitbox;
	
	public double maxHp;
	public double hp;
	public double immunityTime;
	public double maxImmunityTime;
	public Animation anim;
	
	public Weapon[] weapon;
	
	public Player(Game game, KeyboardInput keyboard, MouseInput mouse, Viewport viewport){
		super();
		this.game = game;
		this.mouse = mouse;
		this.viewport = viewport;
		this.keyboard = keyboard;
		pos = new Point2D.Double(1.0,1.0);
		size = 0.14; //used to be 0.28
		speed = 1.0;
		weapon = new Weapon[2];
		weapon[0] = new Sword(game, this);
		weapon[1] = new Gun(game, this);
		hitbox = new Circle(size, pos);
		maxHp = 100;
		hp = maxHp;
		maxImmunityTime = 0.7;
		immunityTime = 0;
		anim = new Animation(game.getSprite(Game.SPRITESHEET.PLAYER), 0, 0, 0.1 , Animation.AnimationMode.LOOP);
	}
	
	@Override
	public void update(double delta, Game game){
		super.update(delta, game);
		keyboard.poll();
		mouse.poll();
		
		if(hp>0){
			//adjust speed so it's the same in all directions
			if(game.keyboard.keyDown(KeyEvent.VK_W)){pos.y = Math.max(pos.y - speed*delta, size);}
			if(game.keyboard.keyDown(KeyEvent.VK_A)){pos.x = Math.max(pos.x - speed*delta, size); anim.setSet(1);}
			if(game.keyboard.keyDown(KeyEvent.VK_S)){pos.y = Math.min(pos.y + speed*delta, game.roomH-size);}
			if(game.keyboard.keyDown(KeyEvent.VK_D)){pos.x = Math.min(pos.x + speed*delta, game.roomW-size); anim.setSet(0);}
			
			if(!game.keyboard.keyDown(KeyEvent.VK_W)&&!game.keyboard.keyDown(KeyEvent.VK_A)&&!game.keyboard.keyDown(KeyEvent.VK_S)&&!game.keyboard.keyDown(KeyEvent.VK_D))
				{anim.setSet(2);}
			
			
			
			
			if(mouse.isPressed(0) && hp>0){
				weapon[0].use(viewport.toGameCoord(mouse.getPos()));
			}
			if(mouse.isPressed(1) && hp>0){
				weapon[1].use(viewport.toGameCoord(mouse.getPos()));
			}
		
			int l = weapon.length;
			for(int i=0;i<l;i++)weapon[i].update(delta);
		
		
		
			if(immunityTime>0)immunityTime -= delta;
			ListIterator<Monster> mit = game.monsters.listIterator(0);
			while(mit.hasNext()){
				Monster m = mit.next(); 
				if(hitbox.intersects(m.hitbox)){if(immunityTime<=0){hp -= 10; immunityTime = maxImmunityTime;}}
			}
			anim.update(delta);
		}
	}
	
	@Override
	public void draw(Graphics2D g, Viewport viewport){
		
		viewport.drawCircleSprite(pos, size, anim, g);
	}
	
	@Override
	public Point2D.Double getPos(){
		return pos;
	}
	
	@Override
	public boolean disposable(){
		return false;
	}
}
