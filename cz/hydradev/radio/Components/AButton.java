package cz.hydradev.radio.Components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class AButton extends JButton {

	private static final long serialVersionUID = -6536351721250125589L;

	public AButton(String text, int x, int y) {
		this(text, x, y, 200, 30);
	}
	
	public AButton(String text, int x, int y, int width, int height) {
		this(text, x, y, width, height, 15);
	}
	
	public AButton(String text, int x, int y, int width, int height, int fSize) {
		this.setText(text);
		this.setBounds(x, y, width, height);
		this.setFocusable(false);
		this.setForeground(new Color(255, 255, 255));
		this.setBackground(new Color(65, 65, 65));
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(140, 140, 140)));
		this.setFont(new Font(Font.DIALOG, Font.PLAIN, fSize));
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
}