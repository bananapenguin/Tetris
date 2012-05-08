package blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import tetris.Field;
import tetris.ImageBox;
import tetris.Posion;
import tetris.SoundBox;

public class Block implements ImageObserver {
	public Field field;

	public Block nextBlock;

	private Posion posion;

	public int blockNumber;

	protected Color blockColor;

	public int dropDistance;

	public ImageBox ib;

	public SoundBox sb;

	int type = (int) (7 * Math.random());

	public int block[][] = new int[4][4];

	int turnblock[][] = new int[4][4];

	public final int tileSize = 20;

	public static final int RIGHT = 1;

	public static final int LEFT = 2;

	public static final int DOWN = 3;

	public static final int DROP = 4;

	public Block() {
		posion = new Posion();
		allZero();
	}

	public Block(Field field) {
		this();
		setPosion(4, 0);
		this.field = field;
		this.ib = field.ib;
		this.sb = field.sb;
	}

	public void allZero() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				block[i][j] = 0;
			}
		}
	}

	public void drawBlock(Graphics g) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (block[i][j] == 1) {
					g.drawImage(ib.TetrominoImg, (getX() + j) * tileSize,
							(getY() + i) * tileSize, (getX() + j + 1)
									* tileSize, (getY() + i + 1) * tileSize,
							(blockNumber - 1) * 20, 0, blockNumber * 20, 20,
							this);
				}
			}
		}
		// for(int i=0; i<4; i++){
		// for(int j=0; j<4; j++){
		// g.drawRect((getX()+j)*20, (getY()+i)*20, 20, 20);
		// }
		// }
		GhostBlock(g);
	}

	public void GhostBlock(Graphics g) {
		int ghostY = getY() + getDistance();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (block[i][j] == 1) {
					g.drawRect((getX() + j) * tileSize,
							(ghostY + i) * tileSize, tileSize, tileSize);
				}
			}
		}
	}

	public boolean canMove(int dir) {
		switch (dir) {
		case LEFT:
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (block[i][j] == 1
							&& field.field[getY() + i][getX() + j - 1] >= 1) {
						return false;
					}
				}
			}
			return true;
		case RIGHT:
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (block[i][j] == 1
							&& field.field[getY() + i][getX() + j + 1] >= 1) {
						return false;
					}
				}
			}
			return true;
		case DOWN:
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (block[i][j] == 1
							&& field.field[getY() + i + 1][getX() + j] >= 1) {
						return false;
					}
				}
			}
			return true;
		case DROP:
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (block[i][j] == 1
							&& field.field[getY() + i + 1][getX() + j] >= 1) {
						return false;
					}
				}
			}
			return true;
		default:
			return false;
		}
	}

	public void Move(int dir) {
		try {
			switch (dir) {
			case LEFT:
				if (canMove(LEFT)) {
					setX(getX() - 1);
				}
				break;
			case RIGHT:
				if (canMove(RIGHT)) {
					setX(getX() + 1);
				}
				break;
			case DOWN:
				if (canMove(DOWN)) {
					setY(getY() + 1);
				}
				break;
			case DROP:
				int drop = getDistance();
				setDropDistance(getDistance());
				for (int i = 0; i < drop; i++) {
					Move(DOWN);
				}
				break;
			default:
				break;
			}
		} catch (NullPointerException e) {

		}

	}

	public void Turn() {
		try {
			if (canTurn()) {
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						block[i][j] = turnblock[i][j];
					}
				}
				sb.turn.play();
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}

	}

	public boolean canTurn() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				turnblock[j][3 - i] = block[i][j];
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (turnblock[i][j] == 1
						&& field.field[getY() + i][getX() + j] >= 1) {
					return false;
				}
			}
		}
		return true;
	}

	public int getDistance() {
		int distance = 0;
		while (true) {
			for (int i = 3; i >= 0; i--) {
				for (int j = 0; j < 4; j++) {
					if (block[i][j] == 1
							&& field.field[getY() + distance + i][getX() + j] >= 1) {
						return distance - 1;
					}
				}
			}
			distance++;
		}
	}

	public void setDropDistance(int distance) {
		dropDistance = distance;
	}

	public int getDropDistance() {
		return dropDistance;
	}

	public int getNumber() {
		return blockNumber;
	}

	public Color getColor() {
		return blockColor;
	}

	public int getX() {
		return posion.getX();
	}

	public int getY() {
		return posion.getY();
	}

	public void setX(int x) {
		posion.setX(x);
	}

	public void setY(int y) {
		posion.setY(y);
	}

	public Posion getPosition() {
		return posion;
	}

	public void setPosion(int x, int y) {
		posion.setPosion(x, y);
	}

	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
