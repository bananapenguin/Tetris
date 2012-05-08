package tetris;

import java.awt.Color;
import java.awt.Graphics;

public class LinePanel {

	Field field;

	LinePanel(Field field) {
		this.field = field;
	}

	public void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 100, 50);
		g.setColor(Color.BLACK);
		g.drawString("LINE", 10, 15);
		g.drawString(Integer.toString(field.delLineNumber), 10, 35);
	}
}
