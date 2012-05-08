package tetris;

import java.awt.*;
import blocks.*;

public class Field extends Panel {

	public int field[][] = { { 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 } };

	public int delLineNumber = 0;

	public int line = 0;

	public ImageBox ib;

	public SoundBox sb;

	public Image img;

	public Field(ImageBox ib, SoundBox sb) {
		setSize(250, 450);
		this.ib = ib;
		this.sb = sb;
	}

	public void drawField(Graphics g) {
		g.drawImage(ib.BG, 0, 0, 240, 420, (delLineNumber % 50) / 10 * 240, 0,
				((delLineNumber % 50) / 10) * 240 + 240, 420, this);
		int BN = 0;
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 12; j++) {
				switch (field[i][j]) {
				case 1:
					BN = 1;
					break;
				case 2:
					BN = 2;
					break;
				case 3:
					BN = 3;
					break;
				case 4:
					BN = 4;
					break;
				case 5:
					BN = 5;
					break;
				case 6:
					BN = 6;
					break;
				case 7:
					BN = 7;
					break;
				case 9:
					BN = 8;
					break;
				default:
					BN = 0;
					break;
				}
				g.drawImage(ib.TetrominoImg, j * 20, i * 20, j * 20 + 20,
						i * 20 + 20, (BN - 1) * 20, 0, BN * 20, 20, this);
			}
		}
	}

	public void paint(Graphics g) {
	}

	public void setField(Block b) {
		field[b.getX()][b.getY()] = 2;
	}

	public void Fixed(Block block) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (block.block[i][j] == 1) {
					field[block.getY() + i][block.getX() + j] = block
							.getNumber();
				}
			}
		}
		sb.drop.play();
	}

	public void delLine() {
		int delete;
		line = 0;

		for (int i = 0; i < 20; i++) {
			delete = 0;
			for (int j = 1; j < 10; j++) {
				if (field[i][j] > 0 && field[i][j + 1] > 0) {
					delete++;
				}
			}
			if (delete == 9) {
				for (int j = 1; j < 11; j++) {
					field[i][j] = 0;
					for (int k = i; k > 0; k--) {
						field[k][j] = field[k - 1][j];
					}
				}
				delLineNumber++;
				line++;
			}
		}
		if (line > 0) {
			sb.del.play();
		}
	}

	public int getLine() {
		return line;
	}
}
