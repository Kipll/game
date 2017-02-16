package game;
import javax.swing.JFrame;
import javax.swing.JPanel;

import network.Listener;
import network.Server;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class Game{
	
	
	public boolean isRunning;
	public KeyboardInput keyboard;
	public MouseInput mouse;
	
	public double roomW;//in game units
	public double roomH;//in game units
	public LinkedList<Player> players;
	public LinkedList<Monster> monsters;
	public LinkedList<Entity> entities;
	public LinkedList<Monster> monstersWaiting;
	public LinkedList<Entity> entitiesWaiting;
	
	public Server server;
	public static int port = 4444;
	
	private Viewport viewport;
	
	Animation arena;
	
	public enum SPRITESHEET {
		MONSTER(0),
		PLAYER(1),
		ARENA(2),
		WALL(3);
		
		private final int value;
		public int val(){return value;}
		
		public Spritesheet getSpritesheet(){
			switch(this){
				case MONSTER:
					int[] cir = new int[]{4,4,4,2};
					try{
						//return new Spritesheet(ImageIO.read(new File("../Resources/Images/monster.png")),0,0,682,682,3,1, cir);
						return new Spritesheet(ImageIO.read(new File("Resources/Images/monster_bird.png")),0,0,150,150,4,4, cir);
					} catch (IOException e) {
						System.err.println("Single checking");
						System.err.println(e.getStackTrace());
						System.err.println(e.getMessage());
						System.exit(1);
					}
				
				case PLAYER:

					int[] cir2 = new int[]{6,6,1};
					try{
						//return new Spritesheet(ImageIO.read(new File("../Resources/Images/monster.png")),0,0,682,682,3,1, cir);
						return new Spritesheet(ImageIO.read(new File("Resources/Images/player.png")),0,0,104,150,6,3, cir2);
					} catch (IOException e) {
						System.err.println("Double checking");
						System.err.println(e.getStackTrace());
						System.err.println(e.getMessage());
						System.exit(1);
					}
					
				case ARENA:

					int[] cir3 = new int[]{1};
					
					try{
						//return new Spritesheet(ImageIO.read(new File("../Resources/Images/monster.png")),0,0,682,682,3,1, cir);
						return new Spritesheet(ImageIO.read(new File("Resources/Images/arena.png")),0,0,1366,768,1,1, cir3);
					} catch (IOException e) {
						System.err.println("Triple checking");
						System.err.println(e.getMessage());
						System.exit(1);
					}
					
				case WALL:
					try{
						//return new Spritesheet(ImageIO.read(new File("../Resources/Images/monster.png")),0,0,682,682,3,1, cir);
						return new Spritesheet(ImageIO.read(new File("../Resources/Images/wall.png")),0,0,65,65,1,1,new int[]{1});
					} catch (IOException e) {
						System.err.println("3");
						System.err.println(e.getMessage());
						System.exit(1);
					}					
			}
			return null;
		}

		private SPRITESHEET(int value) {
		    this.value = value;
		}
	}
	private Spritesheet[] sprites;
	public Spritesheet getSprite(SPRITESHEET s){
		if(sprites[s.val()]==null){
			sprites[s.val()] = s.getSpritesheet();
		}
		return sprites[s.val()];
	}
	
	
	public Random rand;
	
	public double maxSpawnTime;
	public double minSpawnTime;
	public double timeUntilSpawn;
	
	public int score = 0;
	
	public void spawnEntity(Entity e){
		entitiesWaiting.add(e);
	}
	public void spawnMonster(Monster e){
		monstersWaiting.add(e);
	}
	
	//private levels.Level level = new levels.TestLevel(this);
	//java is dumb
	
	public Game(KeyboardInput keyboard, MouseInput mouse){
		isRunning = true;
		
		this.keyboard = keyboard;
		this.mouse = mouse;
		
		players = new LinkedList<Player>();
		monsters = new LinkedList<Monster>();
		entities = new LinkedList<Entity>();
		monstersWaiting = new LinkedList<Monster>();
		entitiesWaiting = new LinkedList<Entity>();
		
		roomW = 5.0;
		roomH = 3.0;
		
		viewport = new Viewport(this);
		viewport.ppu = 180.0;
		
		System.out.println("Here");
		int l = SPRITESHEET.values().length;
		System.err.println(l);
		sprites = new Spritesheet[l];
		for(int i=0;i<l;i++)
			sprites[i] = null;
		
		
		
		
		server = new Server();
		server.listen(port, keyboard, mouse);
		
		
		
		
		players.add(new Player(this, keyboard, mouse, viewport));
		

		viewport.p = players.getFirst();
		
		arena= new Animation(getSprite(SPRITESHEET.ARENA),0,0,1,Animation.AnimationMode.LOOP);
		
		rand = new Random();
		maxSpawnTime = 4.3;
		minSpawnTime = 1.1;
	}
	
	public void update(double delta, int w, int h){
		
		viewport.screenW = w;
		viewport.screenH = h;
		viewport.update(delta);
		
		if(keyboard.keyDown(KeyEvent.VK_UP)) viewport.ppu+=15.0*delta;
		if(keyboard.keyDown(KeyEvent.VK_DOWN)) viewport.ppu-=15.0*delta;
		
		monsters.addAll(monstersWaiting);
		monstersWaiting = new LinkedList<Monster>();
		entities.addAll(entitiesWaiting);
		entitiesWaiting = new LinkedList<Entity>();
		
		ListIterator<Player> pit = players.listIterator(0);
		while(pit.hasNext()){Player p = pit.next(); p.update(delta, this); if(p.disposable()) pit.remove();}
		ListIterator<Monster> mit = monsters.listIterator(0);
		while(mit.hasNext()){Monster m = mit.next(); m.update(delta, this); if(m.disposable()) mit.remove();}
		ListIterator<Entity> eit = entities.listIterator(0);
		while(eit.hasNext()){Entity e = eit.next(); e.update(delta, this); if(e.disposable()) eit.remove();}
		
		
		if(timeUntilSpawn <= 0)
		{
			timeUntilSpawn = minSpawnTime + rand.nextDouble()*(maxSpawnTime - minSpawnTime);
			spawnMonster(new Monster(this));
		}
		else timeUntilSpawn -= delta;
	}
	
	public void render(Graphics2D g){
	
		g.setColor(Color.BLACK);
		g.fillRect(0,0,viewport.screenW,viewport.screenH);
		
		//g.setColor(Color.GREEN);
	//Point roomCoord = viewport.toScreenCoord(new Point2D.Double(0,0));
		//g.fillRect(roomCoord.x, roomCoord.y, (int)(roomW*viewport.ppu), (int)(roomH*viewport.ppu));
		
		viewport.drawSprite(new Rectangle.Double(0,0,roomW,roomH), arena, g);
		//level.draw(g);
		
		ListIterator<Player> pit = players.listIterator(0);
		while(pit.hasNext())pit.next().draw(g, viewport);
		ListIterator<Monster> mit = monsters.listIterator(0);
		while(mit.hasNext())mit.next().draw(g, viewport);
		ListIterator<Entity> eit = entities.listIterator(0);
		while(eit.hasNext())eit.next().draw(g, viewport);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
		g.drawString(Integer.toString(score), 30, 30);
		
		g.setColor(Color.BLACK);
		g.fillRect(30,60,100,10);
		try{
			g.setColor(Color.RED);
			g.fillRect(30,60,(int)(100 * players.getFirst().hp / players.getFirst().maxHp),10);
		}
		catch(NoSuchElementException e){}
	}
}
