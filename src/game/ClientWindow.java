package game;
import javax.swing.JFrame;
import javax.swing.JPanel;

import network.Sender;

import java.awt.*;

public class ClientWindow extends JFrame{

	public ClientInputListener l;
	
	public ClientWindow(Sender sender){
		super("Hello World");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640,420);
		
		
		l = new ClientInputListener(sender);
		addKeyListener(l);
		addMouseListener(l);
		addMouseMotionListener(l);
		
		setVisible(true);
	}
}
