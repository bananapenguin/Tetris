package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

import blocks.Block;

public class NextBlockPanel extends Panel {

	Block nextBlock;

	MainPanel mp;

	int tileSize;

	public NextBlockPanel(MainPanel mp) {
		this.mp = mp;
		this.nextBlock = mp.nextblock;
		tileSize = nextBlock.tileSize;
	}

	public void draw(Graphics g) {
		nextBlock = mp.nextblock;
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 100, 100);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (nextBlock.block[i][j] == 1) {
					g.drawImage(nextBlock.ib.TetrominoImg, 10 + 20 * j,
							10 + 20 * i, 30 + 20 * j, 30 + 20 * i,
							(nextBlock.blockNumber - 1) * 20, 0,
							nextBlock.blockNumber * 20, 20, this);
				}
			}
		}
		g.setColor(Color.BLACK);
		g.drawString("NEXT", 10, 15);
	}
}