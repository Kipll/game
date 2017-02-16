package audio;

import java.awt.Color;

import javax.swing.JFrame;

/**
 * 
 * @author Farrah Aina Mohd Zulkifli
 *
 */

public class BGMGUI 
{
	
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("BGM Tester");
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BGM bgm = new BGM(50,"/Music/BGM_Asian_Gravedrum.wav");
//		BGM bgm = new BGM(50,"/Music/BGM_Dark_Temple.wav");
		BGMComponent panel = new BGMComponent(bgm, 0, 100, 50);
		Color brown = new Color(102, 51, 00);
		panel.setBackground(brown);

		frame.add(panel);
		frame.setVisible(true);
		bgm.playOption();
		


	}

}
