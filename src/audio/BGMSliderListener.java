package audio;

import javax.swing.event.*;
import javax.swing.JSlider;

/**
 * 
 * @author Farrah Aina Mohd Zulkifli
 *
 */
//Respond to the user moving a slider.

public class BGMSliderListener implements ChangeListener
{
	private BGMModel model;
	private JSlider slider;
	
	public BGMSliderListener(BGMModel model, JSlider slider)
	{
		this.model = model;
		this.slider = slider;
	}
	
	public void stateChanged(ChangeEvent e)
	{
		int value = slider.getValue();
		model.setValue(value);
	}

}
