package audio;

import java.util.Observable;

/**
 * 
 * @author Farrah Aina Mohd Zulkifli
 *
 */

//Model of Easter class. 

public class BGMModel extends Observable 
{
	private BGM bgm;
	
	public BGMModel(BGM bgm)
	{
		super();
		this.bgm = bgm;
	}
	
	/**
	 Get the year of Easter day
	 @return year of Easter day
	 */
	public int getValue()
	{
		return bgm.getValue();
	}
	
	public boolean getMuteStatus(){
		return bgm.getMuteStatus();
	}

	public void setMuteStatus(boolean mute){
		bgm.setMuteStatus(mute);
		setChanged();
		notifyObservers();
	}
	
	/**
	 Change the year of Easter day
	 @param year The new year of Easter day
	 */
	public void setValue(int value)
	{
		bgm.setValue(value);
		setChanged();
		notifyObservers();
	}
}
