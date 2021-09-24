package cz.hydradev.radio.radio;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Radio {

	private Player player;
	private Thread thread;
	
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
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void stop() {
		if (isRunning()) {
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
