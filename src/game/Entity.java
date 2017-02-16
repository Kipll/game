package game;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;

public abstract class Entity implements Comparable<Entity>{
	
	public LinkedList<Behaviour> behaviour;
	
	public Entity(){
		behaviour = new LinkedList<Behaviour>();
	}
	
	public abstract Point2D.Double getPos();
	
	public abstract boolean disposable();
	
	public void update(double delta, Game game){
		ListIterator<Behaviour> bit = behaviour.listIterator(0);
		while(bit.hasNext()){Behaviour b = bit.next(); b.update(game, getPos(), delta); if(b.disposable()) bit.remove();}
	}
	
	public abstract void draw(Graphics2D g, Viewport viewport);
	
	public void addBehaviour(Behaviour b){
		behaviour.add(b);
	}
	
	@Override
	public int compareTo(Entity e){
		return hashCode() - e.hashCode();
	}
}
