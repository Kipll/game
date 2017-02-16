package audio;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

public class SFXView extends JLabel implements Observer
{
	private SFXModel model;
	
	/**
	 Initialise view with model.
	 @param model The underlying bgm model
	 */
	public SFXView(SFXModel model)
	{
		super();
		this.model = model;
		
		boolean mute = model.getMuteStatus();
		int value = model.getValue();
		setText("Volume : " + value + "%");

	}
	
	public void update(Observable obs, Object obj)
	{
		boolean mute = model.getMuteStatus();
		int value = model.getValue();
		setText("Volume : " + value + "%");

	}

}
