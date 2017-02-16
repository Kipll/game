package audio;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SFXSliderListener implements ChangeListener
{
	private SFXModel model;
	private JSlider slider;
	
	public SFXSliderListener(SFXModel model, JSlider slider)
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
