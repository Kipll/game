package audio;

import javax.swing.JFrame;

public class SFXGUI {

	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("BGM Tester");
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SFX sfx = new SFX(50,"/Music/SFX_Health_Replenish.wav");
//		BGM bgm = new BGM(50,"/Music/BGM_Dark_Temple.wav");
		SFXComponent panel = new SFXComponent(sfx, 0, 100, 50);

		frame.add(panel);
		frame.setVisible(true);
		sfx.play();
		

	}
}
