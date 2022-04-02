package cz.hydradev.radio.Radio;

import java.io.InputStream;
import java.net.URL;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Radio {
	
	private Player player;
	private Thread thread;
	public float minVol = 0;
	
	public void start(String stream) {
		try {
			setStream(new URL(stream).openStream());
			thread = new Thread(() -> {
				try {
					player.play();
				}catch(JavaLayerException e) {
					e.printStackTrace();
				}
			});
			thread.start();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setVolume(this.minVol);
	}
	
	public void setMinVol(float vol) {
		this.minVol = vol;
	}
	
	public void setVolume(float vol) {
		if(player != null)
			player.setVolume(vol);
	}
	
	public float getVol() {
		if(player != null)
			return player.getVolume();
		return -999;
	}
	
	public void stop() {
		if(isRunning()) {
			thread.interrupt();
			thread = null;
			
			if (player != null) {
				player.close();
			}
		}
	}
	
	private void setStream(InputStream inStream) {
		try {
			player = new Player(inStream);
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isRunning() {
		return thread != null;
	}
}