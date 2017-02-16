package game;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel{
	
	public Game game;
	public KeyboardInput keyboard;
	public MouseInput mouse;
	
	public GamePanel(){
		//setPreferredSize(new Dimension(300, 300));
		keyboard = new KeyboardInput();
		mouse = new MouseInput();
		addKeyListener(keyboard);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		game = new Game(keyboard, mouse);
	}
	
	@Override
	public void paint(Graphics g){
		game.render((Graphics2D) g);
	}

}
