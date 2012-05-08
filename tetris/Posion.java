package tetris;

public class Posion {
	private int x, y;

	public Posion() {
		x = y = 0;
	}

	public Posion(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setPosion(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void show() {
		System.out.println(x + "," + y);
	}

}
