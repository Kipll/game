package game;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


//Create JPanel - DONE
//Insert image onto JPanel - DONE
//create JFrame, add JPanel to JFrame - DONE
//Needs "Back" JButton
public class ControlMenu 
{
	public int width = 500;
	public int height = 500;
	public JButton back = new JButton("Back");
	public JButton quit = new JButton("Quit");

	public ControlMenu() throws IOException
	{
		//JPanel
		JPanel controlPanel = new JPanel();
		Color brown = new Color(102, 51, 00);
		controlPanel.setBackground(brown);
		
		//Converts Image to a Swing Component
		BufferedImage control = ImageIO.read(new File("Resources/Images/ControlsBrownFinal.png")); 
		JLabel controlLabel = new JLabel(new ImageIcon(control));
		controlPanel.add(controlLabel);
		
		//Creates JFrame, adds JPanel to JFrame
		JFrame menu = new JFrame("Betrayal");
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(width, height - 75);
		menu.add(controlPanel);
		menu.setVisible(true);
		menu.revalidate();
	}
	
	public static void main(String args[]) throws IOException
	{
		new ControlMenu();
	}

}
