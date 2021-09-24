package cz.hydradev.radio;

import java.awt.Component;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import cz.hydradev.radio.radio.Radio;

public class Main {

	private static int index = 0;
	
	private static void sendToCon(String message) {
		System.out.println(message);
	}
	
	public static void main(String args[]) {
		sendToCon("Starting Radio v1.0 by Hydra");
		
		Radio radio = new Radio(); //0-5
		JFrame frame = new JFrame("Radio");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 0)); //8 0
		
		JButton d = new JButton("Start");
		JButton ee = new JButton("Next station");
		
		JTextField b = new JTextField("");
		JLabel lab = new JLabel("Station: None");
		panel.add(lab);
		panel.add(new JLabel("Custom: "));
		panel.add(b);
		panel.add(new JLabel(""));
		panel.add(d);
		panel.add(ee);
		frame.add(panel);
		frame.setSize(400, 250);
		frame.setResizable(false);
		frame.setLocationRelativeTo((Component)null);
		
		d.addActionListener(e -> {
			if (d.getText().contains("Start")) {
				d.setText("Stop");
				
				if (radio.isRunning()) {
					radio.stop();
				}
				
				if (index == 6) {
					radio.start(b.getText());
				}else {
					radio.start(getStream(index));
				}
				
				lab.setText("Station: " + getName(index));
			}else {
				lab.setText("Station: None");
				radio.stop();
				d.setText("Start");
			}
		});

		ee.addActionListener(e -> {
			if (d.getText().contains("Stop")) {
				radio.stop();
				if (index < 6) {
					index++;
				}else
					index = 0;
				
				if (index == 6) {
					radio.start(b.getText());
				}else {
					radio.start(getStream(index));
				}
				
				lab.setText("Station: " + getName(index));
			}
		});
		frame.setVisible(true);
	}
	
	private static String getName(int mode) {
		if (mode == 0)
			return "ILoveRadio";
		if (mode == 1)
			return "ILoveHits";
		if (mode == 2)
			return "ILoveMusicAndChill";
		if (mode == 3)
			return "ILoveBeach";
		if (mode == 4)
			return "ILoveMainstage";
		if (mode == 5)
			return "ILoveHardStyle";
		if (mode == 6)
			return "Custom";
		return "ILoveRadio";
	}
	
	private static String getStream(int mode) {
		if (mode == 0)
			return "http://streams.ilovemusic.de/iloveradio1.mp3"; //i love radio
		if (mode == 1)
			return "http://streams.ilovemusic.de/iloveradio109.mp3"; //i love hits
		if (mode == 2)
			return "http://streams.ilovemusic.de/iloveradio10.mp3"; //i love music and chill
		if (mode == 3)
			return "http://streams.ilovemusic.de/iloveradio7.mp3"; //i love beach
		if (mode == 4)
			return "http://streams.ilovemusic.de/iloveradio22.mp3"; //i love mainstage
		if (mode == 5)
			return "http://streams.ilovemusic.de/iloveradio21.mp3"; //i love hard style
		return "http://streams.ilovemusic.de/iloveradio1.mp3"; //def
	}
}