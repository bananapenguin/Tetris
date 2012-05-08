package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import blocks.*;

public class MainPanel extends Panel implements KeyListener, Runnable {

	Block block;

	Block nextblock;

	Field field;

	NextBlockPanel nextBlockPanel;

	LinePanel linePanel;

	ScorePanel scorePanel;

	ImageBox ib;

	SoundBox sb;

	int dropSpeed;

	int hoge = 0;

	public int score;

	boolean gameover = true;

	public MainPanel(ImageBox ib, SoundBox sb) {
		setSize(500, 500);
		field = new Field(ib, sb);
		block = createBlock();
		nextblock = createBlock();
		addKeyListener(this);
		this.ib = ib;
		this.sb = sb;
		dropSpeed = 1000;
		score = 0;

		nextBlockPanel = new NextBlockPanel(this);
		linePanel = new LinePanel(field);
		scorePanel = new ScorePanel(this);

		draw();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		try {
			if (key == KeyEvent.VK_LEFT) {
				block.Move(Block.LEFT);
				if (block.canMove(Block.LEFT))
					hoge = 0;
			} else if (key == KeyEvent.VK_RIGHT) {
				block.Move(Block.RIGHT);
				if (block.canMove(Block.RIGHT))
					hoge = 0;
			} else if (key == KeyEvent.VK_DOWN) {
				block.Move(Block.DROP);
				addDropScore();
				field.Fixed(block);
				field.delLine();
				block = nextblock;
				nextblock = createBlock();
				dropSpeed();
				hoge = 0;
				addLineScore();

				gameover = !GameOver();
			} else if (key == KeyEvent.VK_SPACE) {
				block.Turn();
			} else if (key == KeyEvent.VK_UP) {
				block.Move(Block.DOWN);
			}
			draw();
		} catch (NullPointerException ex) {

		}
	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void draw() {
		ib.gBuf.setColor(Color.white);
		ib.gBuf.fillRect(0, 0, 500, 450);
		field.drawField(ib.GameGra);
		block.drawBlock(ib.GameGra);
		nextBlockPanel.draw(ib.NextGra);
		linePanel.draw(ib.LineGra);
		scorePanel.draw(ib.ScoreGra);
		ib.gBuf.drawImage(ib.GameImg, 130, 40, this);
		ib.gBuf.drawImage(ib.NextImg, 385, 40, this);
		ib.gBuf.drawImage(ib.LineImg, 385, 200, this);
		ib.gBuf.drawImage(ib.ScoreImg, 385, 400, this);

		ib.gBuf.setColor(Color.black);
		ib.gBuf.drawString(Integer.toString(dropSpeed), 10, 10);
		ib.gBuf.drawString(Integer.toString(block.getDistance()), 10, 30);
		ib.gBuf.drawString(Integer.toString(hoge), 10, 40);

		repaint();
	}

	public void paint(Graphics g) {
		g.drawImage(ib.imgBuf, 0, 0, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void run() {
		while (gameover) {
			try {
				Thread.sleep(dropSpeed);
				block.Move(Block.DOWN);

				if (!block.canMove(Block.DOWN)) {
					hoge++;
					if (hoge == 2) {
						field.Fixed(block);
						field.delLine();
						block = nextblock;
						nextblock = createBlock();
						dropSpeed();
						hoge = 0;
						addLineScore();
						gameover = !GameOver();
					}
				}
				draw();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (!gameover) {
			block = null;
			for (int i = 19; i >= 0; i--) {
				try {
					if (i % 2 == 0) {
						for (int j = 1; j < 11; j++) {
							field.field[i][j] = 9;
							field.drawField(ib.GameGra);
							ib.gBuf.drawImage(ib.GameImg, 130, 40, this);
							repaint();
							Thread.sleep(5);
						}
					} else {
						for (int j = 10; j > 0; j--) {
							field.field[i][j] = 9;
							field.drawField(ib.GameGra);
							ib.gBuf.drawImage(ib.GameImg, 130, 40, this);
							repaint();
							Thread.sleep(5);
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void dropSpeed() {
		dropSpeed = 1000 - ((field.delLineNumber % 50) / 10) * 185;
	}

	public Block createBlock() {
		int BlockNum = (int) (Math.random() * 7);
		switch (BlockNum) {
		case 0:
			return new Box(field);
		case 1:
			return new T(field);
		case 2:
			return new Z(field);
		case 3:
			return new revZ(field);
		case 4:
			return new L(field);
		case 5:
			return new revL(field);
		case 6:
			return new Bar(field);
		default:
			return null;
		}
	}

	public void addLineScore() {
		switch (field.getLine()) {
		case 1:
			score += 100;
			break;
		case 2:
			score += 200;
			break;
		case 3:
			score += 300;
			break;
		case 4:
			score += 1000;
			break;
		default:
			break;
		}
	}

	public void addDropScore() {
		score += block.getDropDistance();
	}

	public boolean GameOver() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (block.block[i][j]
						+ field.field[block.getY() + i][block.getX() + j] >= 2) {
					return true;
				}
			}
		}
		return false;
	}
}
