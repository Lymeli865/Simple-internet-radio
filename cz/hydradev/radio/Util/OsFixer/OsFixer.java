package cz.hydradev.radio.Util.OsFixer;

public class OsFixer {

	private OS getCurrOS() {
		String currOs = System.getProperty("os.name").toLowerCase();
		if(currOs.startsWith("windows")) {
			return OS.Win;
		}else if(currOs.startsWith("mac")) {
			return OS.Mac;
		}
		
		return OS.Linux;
	}
	
	public int fixGuiWidth() {
		OS currOs = getCurrOS();
		
		if(currOs.equals(OS.Win))
			return 20-6;
		
		return 0;
	}
}