package cz.hydradev.radio.Util;

public class Timer {

	private long lastMS = System.currentTimeMillis();
	private long time;
	
	public void resetTimer() {
		lastMS = System.currentTimeMillis();
	}
	
	public boolean wait(long time, boolean reset) {
		this.time = time;
		if (System.currentTimeMillis() - lastMS > time) {
			if (reset)
				resetTimer();
			
			return true;
		}
		return false;
	}
	
	public long getTime() {
		return time - (System.currentTimeMillis() - lastMS);
	}
	
	public double getTimeInSec() {
		return getTime() / 1000;
	}
}