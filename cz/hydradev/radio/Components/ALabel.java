package cz.hydradev.radio.Components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class ALabel extends JLabel {

	private static final long serialVersionUID = -4070185539619889226L;

	public ALabel(String text, int a, int b) {
		this(text, a, b, 15, 200);
	}
	
	public ALabel(String text, int x, int y, int height, int width) {
		this(text, x, y, height, width, 15);
	}
	
	public ALabel(String text, int x, int y, int height, int width, int fSize) {
		this.setBounds(0, 0, width, height);
		this.setLocation(x, y);
		this.setFocusable(false);
		this.setForeground(Color.white);
		this.setFont(new Font(Font.DIALOG, Font.PLAIN, fSize));
		this.setText(text);
	}
	
	public void setFontSize(int size) {
		this.setFont(new Font(Font.DIALOG, Font.PLAIN, size));
	}
}