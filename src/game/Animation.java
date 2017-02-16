package game;
public class Animation{

	public enum AnimationMode {LOOP, PLAYONCE;}

	public Spritesheet ss;
	//X coordinate on the spritesheet grid
	public int frame;
	//Y coordinate on the spritesheet grid
	public int set;
	public int[] framesInSet;
	public double timeBetweenFrames;
	public AnimationMode mode;
	
	public double timeLeft;
	public boolean finished;
	
	public Animation(Spritesheet ss, int frame, int set, double timeBetweenFrames, AnimationMode mode){
		this.ss = ss;
		this.frame = frame;
		this.set = set;
		this.timeBetweenFrames = timeBetweenFrames;
		this.mode = mode;
		
		timeLeft = timeBetweenFrames;
		finished = false;
	}
	
	public void setSet(int set){
		if(this.set != set){
			this.set = set;
			frame = 0;
		}
	}
	
	public void restart(){
		frame = 0;
	}
	
	public void update(double delta){
		timeLeft -= delta;
		while(timeLeft<=0){
			timeLeft+=timeBetweenFrames;
			frame++;
			if(frame >= ss.colsInRow[set]){
				finished = true;
				switch(mode){
					case LOOP:
						frame = frame % ss.colsInRow[set];
						break;
					case PLAYONCE:
						frame = ss.colsInRow[set]-1;
						break;
				}
			}
		}
	}
}
