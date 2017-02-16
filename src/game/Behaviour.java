package game;
import java.awt.geom.Point2D;

public interface Behaviour{
	public boolean disposable();
	public void update(Game game, Point2D.Double pos, double delta);
}
