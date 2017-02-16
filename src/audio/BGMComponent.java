package audio;

import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * 
 * @author Farrah Aina Mohd Zulkifli
 *
 */

public class BGMComponent extends JPanel
{
	public BGMComponent(BGM bgm, int min, int max, int initial)
	{
		super();
		
		//model
		BGMModel model = new BGMModel(bgm);
		
		//view
		BGMView est = new BGMView(model);
		
		//make view observe model
		model.addObserver(est);
		
		//create control
		JSlider slider = new JSlider(min, max, initial);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing((max - min) / 4);
		slider.setPaintLabels(true);
		slider.setLabelTable(slider.createStandardLabels((max - min) / 4));
		
		
		//create listener
		BGMSliderListener listen = new BGMSliderListener(model, slider);
		
		//make listeners listen to controls
		slider.addChangeListener(listen);
		
		BGMMuteButton mute = new BGMMuteButton(model);
		
		//place views and controls on panel

		add(slider);
		add(est);
		add(mute);
	}
}
