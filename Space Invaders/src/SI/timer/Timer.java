package SI.timer;

public class Timer {

	private long previousTime;
	
	public Timer()
	{
		setPreviousTime(System.currentTimeMillis());
	}

	public long getPreviousTime() {
		return previousTime;
	}

	public void setPreviousTime(long currentTime) {
		this.previousTime = currentTime;
	}
	
	public void resetTimer() {
		previousTime = System.currentTimeMillis();
	}
	
	public boolean timerEvent(int timer)
	{
		if(System.currentTimeMillis() - getPreviousTime() > timer)
		{
			resetTimer();
			return true;
		}
		return false;
	}
	
	public boolean isTimerReady (int timer)
	{
		if(System.currentTimeMillis() - getPreviousTime() > timer)
			return true;
		
		return false;
	}
}
