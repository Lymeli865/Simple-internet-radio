package cz.hydradev.radio.Util;

public class TimeHelper {

	public long SEC = 1000, MIN = SEC*60, HOUR = MIN*60, DAY = HOUR*24;
	
	public TimeHelper() {
		
	}
	
	public String getFormattedTime(long milis) {
		if(milis >= SEC) {
			if(milis >= MIN) {
				if(milis >= HOUR) {
					if(milis >= DAY) {
						return (milis/DAY) + " day.";
					}
					return (milis/HOUR) + " hour.";
				}
				return (milis/MIN) + " min.";
			}
			return (milis/SEC) + " sec.";
		}
			
		return milis + " milis.";
	}
	
}