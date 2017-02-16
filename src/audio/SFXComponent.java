package audio;

import javax.swing.JPanel;
import javax.swing.JSlider;

public class SFXComponent extends JPanel {
	
	public SFXComponent(SFX sfx, int min, int max, int initial)
	{
		super();
		
		//model
		SFXModel model = new SFXModel(sfx);
		
		//view
		SFXView est = new SFXView(model);
		
		//make view observe model
		model.addObserver(est);
		
		//create control
		JSlider slider = new JSlider(min, max, initial);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing((max - min) / 4);
		slider.setPaintLabels(true);
		slider.setLabelTable(slider.createStandardLabels((max - min) / 4));
		
		//create listener
		SFXSliderListener listen = new SFXSliderListener(model, slider);
		
		//make listeners listen to controls
		slider.addChangeListener(listen);
		
		SFXMuteButton mute = new SFXMuteButton(model);
		
		//place views and controls on panel

		add(slider);
		add(est);
		add(mute);
	}

}
