package cz.hydradev.radio;

import java.util.concurrent.CopyOnWriteArrayList;

import cz.hydradev.radio.Components.RadioStation;
import cz.hydradev.radio.Util.OsFixer.OsFixer;

public class Main {
	
	public static CopyOnWriteArrayList<RadioStation> stations = new CopyOnWriteArrayList<RadioStation>();
	public static OsFixer osFix = new OsFixer();
	
	public static void main(String[] args) {
		stations.add(new RadioStation("ILoveRadio", "http://streams.ilovemusic.de/iloveradio1.mp3"));
		stations.add(new RadioStation("ILoveHits", "http://streams.ilovemusic.de/iloveradio109.mp3"));
		stations.add(new RadioStation("ILoveGreatestHits", "https://streams.ilovemusic.de/iloveradio16.mp3"));
		stations.add(new RadioStation("ILoveMusicAndChill", "http://streams.ilovemusic.de/iloveradio10.mp3"));
		stations.add(new RadioStation("ILoveBeach", "http://streams.ilovemusic.de/iloveradio7.mp3"));
		stations.add(new RadioStation("ILoveMainstage", "http://streams.ilovemusic.de/iloveradio22.mp3"));
		stations.add(new RadioStation("ILoveHardStyle", "http://streams.ilovemusic.de/iloveradio21.mp3"));
		stations.add(new RadioStation("ILoveDance", "https://streams.ilovemusic.de/iloveradio36.mp3"));
		stations.add(new RadioStation("ILoveHipHop", "https://streams.ilovemusic.de/iloveradio3.mp3"));
		stations.add(new RadioStation("ILoveMashUp", "https://streams.ilovemusic.de/iloveradio5.mp3"));
		stations.add(new RadioStation("ILovePartyHard", "https://streams.ilovemusic.de/iloveradio14.mp3"));
		
		new RadioFrame();
	}
}