package audio;

import javax.swing.JLabel;
import java.util.Observer;
import java.util.Observable;

/**
 * 
 * @author Farrah Aina Mohd Zulkifli
 *
 */
//Text view of date of Easter day.

public class BGMView extends JLabel implements Observer
{
	private BGMModel model;
	
	/**
	 Initialise view with model.
	 @param model The underlying bgm model
	 */
	public BGMView(BGMModel model)
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
