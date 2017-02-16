package audio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BGMMuteButton extends JPanel{
	
	public BGMMuteButton(BGMModel model){
		
		super();
		
		JButton mute = new JButton("Mute/Unmute");
		
		mute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(model.getMuteStatus()==true){
					model.setMuteStatus(false);
				}else if (model.getMuteStatus()==false){
					model.setMuteStatus(true);
				}
				
			}
		});
		
		add(mute);
	}

}
