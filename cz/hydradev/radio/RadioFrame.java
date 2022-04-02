package cz.hydradev.radio;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cz.hydradev.radio.Components.AButton;
import cz.hydradev.radio.Components.ALabel;
import cz.hydradev.radio.Components.ASliderUI;
import cz.hydradev.radio.Radio.Radio;

public class RadioFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private AButton btnRadio, btnBack, btnNext;
	private ALabel labStation, labCustom, labVol;
	private JTextField boxCustom;
	private JSlider volSlider;
	
	private boolean radioRunning = false;
	public static Radio radio = new Radio();
	private int width = 400, height = 235, station = 0, maxStation = Main.stations.size();
	
	public RadioFrame() {
		labStation = new ALabel("Station: none", 18, 3, 15, 250);
		labStation.setForeground(new Color(205, 205, 205));
		labStation.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
		
		labCustom = new ALabel("Custom: ", 18, 23);
		labCustom.setForeground(new Color(205, 205, 205));
		labCustom.setFontSize(12);
		
		labVol = new ALabel("Volume: ", 20, 70/*62*/);
		labVol.setForeground(new Color(205, 205, 205));
		labVol.setFontSize(12);
		
		volSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 75);
		volSlider.setBounds(22, 85, width-22*2, 45);
		volSlider.setUI(new ASliderUI(volSlider));
		volSlider.setBackground(new Color(50, 50, 50));
		volSlider.setForeground(new Color(150, 150, 150));
		volSlider.setMinorTickSpacing(10);
		volSlider.setMajorTickSpacing(25);
		volSlider.setPaintTicks(true);
		volSlider.setPaintLabels(true);
		volSlider.addChangeListener(new ChangeListener() {
		      public void stateChanged(ChangeEvent e) {
		    	  float a = 86, b = 100;
		    	  radio.setVolume((-80 + (float)((a/b)*volSlider.getValue())));
		      }
		});
		
		boxCustom = new JTextField("");
		boxCustom.setBounds(22, 38, width-22*2, 24);
		boxCustom.addActionListener(this);
		boxCustom.getInsets().set(0, 20, 0, 5);
		boxCustom.setForeground(new Color(205, 205, 205));
		boxCustom.setBackground(new Color(48, 48, 48));
		boxCustom.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(70, 70, 70)));
		boxCustom.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		boxCustom.setCaretColor(new Color(150, 150, 150));
		
		int a = 130;
		
		btnRadio = new AButton("Start radio", 20, a, width-20*2, 26);
		btnRadio.addActionListener(this);
		
		btnBack = new AButton("Previous station", 20, a+30, (width/2)-2-20, 26);
		btnBack.addActionListener(this);
		
		btnNext = new AButton("Next station", (width/2)+2, a+30, (width/2)-2-20, 26);
		btnNext.addActionListener(this);
		
		this.setTitle("DartRadio v1.2");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setSize(width+Main.osFix.fixGuiWidth(), height);
		this.setLocationRelativeTo((Component)null);
		this.getContentPane().setBackground(new Color(50, 50, 50));
		
		this.add(labStation);
		this.add(labCustom);
		this.add(labVol);
		this.add(volSlider);
		this.add(boxCustom);
		this.add(btnRadio);
		this.add(btnBack);
		this.add(btnNext);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnRadio) {
			float a = 86, b = 100;
			radio.setMinVol((-80 + (float)((a/b)*volSlider.getValue())));
			
			if(station != maxStation) {
				if(!radio.isRunning())
					radio.start(getStream(station));
				else
					radio.stop();
				
				labStation.setText(radio.isRunning() ? "Station: " + getName(station) : "Station: none");
			}else {
				if(!radio.isRunning())
					radio.start(boxCustom.getText());
				else
					radio.stop();
				
				labStation.setText(radio.isRunning() ? "Station: Custom" : "Station: none");
			}
			
			radioRunning = !radioRunning;
			btnRadio.setText(radioRunning ? "Stop radio" : "Start radio");
		}else if(e.getSource().equals(btnNext)) {
			if(station <= maxStation-1 && station >= 0 && radio.isRunning())
				station += 1;
			else if(station >= maxStation && radioRunning)
				station = 0;
			
			float a = 86, b = 100;
			radio.setMinVol((-80 + (float)((a/b)*volSlider.getValue())));
			
			if(station != maxStation && radioRunning) {
				radio.stop();
				radio.start(getStream(station));
				
				labStation.setText("Station: " + getName(station));
			}else if(radioRunning){
				radio.stop();
				radio.start(boxCustom.getText());
				
				labStation.setText("Station: Custom");
			}
		}else if(e.getSource().equals(btnBack)) {
			if(station <= maxStation && station >= 1 && radioRunning)
				station -= 1;
			else if(station == 0 && radioRunning)
				station = maxStation;
			
			float a = 86, b = 100;
			radio.setMinVol((-80 + (float)((a/b)*volSlider.getValue())));
			
			if(station != maxStation && radioRunning) {
				radio.stop();
				radio.start(getStream(station));
				
				labStation.setText("Station: " + getName(station));
			}else if(radioRunning){
				radio.stop();
				radio.start(boxCustom.getText());
				
				labStation.setText("Station: Custom");
			}
		}
	}
	
	private static String getName(int mode) {
		return Main.stations.get(mode).getStationName();
	}
	
	private static String getStream(int mode) {
		return Main.stations.get(mode).getStationUrl();
	}
}