package tetris;

import java.awt.Color;
import java.awt.Graphics;

public class ScorePanel {
	MainPanel mp;

	ScorePanel(MainPanel mp) {
		this.mp = mp;
	}

	public void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 100, 50);
		g.setColor(Color.BLACK);
		g.drawString("SCORE", 10, 15);
		g.drawString(Integer.toString(mp.score), 10, 35);
	}
}
