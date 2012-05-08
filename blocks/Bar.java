package blocks;

import java.awt.Color;

import tetris.Field;

public class Bar extends Block {
	public Bar(Field field) {
		super(field);
		blockColor = Color.CYAN;
		blockNumber = 7;
		block[1][0] = 1;
		block[1][1] = 1;
		block[1][2] = 1;
		block[1][3] = 1;

		/*
		 * □□□□ ■■■■ □□□□ □□□□
		 */
	}
}
