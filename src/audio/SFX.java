package audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;

public class SFX{

	private boolean mute;
	private int value;
	private Clip clip;
	public FloatControl volume;
	public BooleanControl muteControl;
	
	public SFX(int value, String s){
		
		this.value = value;
		
		try {
		      
			AudioInputStream ais =
				AudioSystem.getAudioInputStream(
					getClass().getResourceAsStream(s)
				);
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED,
				baseFormat.getSampleRate(),
				16,
				baseFormat.getChannels(),
				baseFormat.getChannels() * 2,
				baseFormat.getSampleRate(),
				false
			);
			AudioInputStream dais =
				AudioSystem.getAudioInputStream(
					decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
			
			volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);			
			muteControl = (BooleanControl)clip.getControl(BooleanControl.Type.MUTE);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	public String toString()
	{
		return "Volume : " + this.value + "%";
	}
	
	/**
	 * 
	 * @return	The audio volume 
	 */
	public int getValue()
	{
		return value;
	}
	
	/**
	 * 
	 * @param value	The audio volume from slider	
	 */
	public void setValue(int value)
	{
		this.value = value;
			
	}
	
	public boolean getMuteStatus(){
		return mute;
	}
	
	public void setMuteStatus(boolean isMute){
		this.mute = isMute;
	}
	
	/**
	 * updates the audio volume 
	 */
	private void update(){
		double dB1 = (double)value / 100;
		float gain1 = (float)(Math.log(dB1)/Math.log(10.0)*20.0);
		volume.setValue(gain1);
//		System.out.println("muting..");
		muteControl.setValue(mute);
	}
	
	public void play(){
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();

	}
	
	/**
	 * stop the audio / clip
	 */
	public void stop() {
		if(clip.isRunning()) clip.stop();
	}
	
	/**
	 * close the audio / clip line
	 */
	public void close() {
		stop();
		clip.close();
	}
	
//	public static void main(String[] args){
//		JFrame frame = new JFrame("SFX Tester");
//		frame.setSize(300, 300);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//		
//		SFX sfx = new SFX("/Music/SFX_Powerups.wav");
//		SFX sfx1 = new SFX("/Music/SFX_Health_Replenish.wav");
//		
//		sfx.play();
//		sfx1.play();
//	}
}
