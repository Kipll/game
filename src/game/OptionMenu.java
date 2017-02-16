package game;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class OptionMenu 
{

	//Creates JPanel
	//Insert Buttons/Options onto JPanel
	//Create JFrame, add JPanel to JFrame
	public int width = 500;
	public int height = 500;
	
	public OptionMenu()
	{
		//JPanel
		JPanel optionsPanel = new JPanel();
		Color brown = new Color(102, 51, 00);
		optionsPanel.setBackground(brown);
		
		
		
		
		//Create JFrame
		JFrame menu = new JFrame("Betrayal");
		menu.setSize(width, height);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args)
	{
		new OptionMenu();
	}

}
