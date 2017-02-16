package audio;

import java.util.Observable;

public class SFXModel extends Observable 
{
	private SFX sfx;
	
	public SFXModel(SFX sfx)
	{
		super();
		this.sfx = sfx;
	}
	
	/**
	 Get the year of Easter day
	 @return year of Easter day
	 */
	public int getValue()
	{
		return sfx.getValue();
	}
	
	public boolean getMuteStatus(){
		return sfx.getMuteStatus();
	}

	public void setMuteStatus(boolean mute){
		sfx.setMuteStatus(mute);
		setChanged();
		notifyObservers();
	}
	
	/**
	 Change the year of Easter day
	 @param year The new year of Easter day
	 */
	public void setValue(int value)
	{
		sfx.setValue(value);
		setChanged();
		notifyObservers();
	}

}
