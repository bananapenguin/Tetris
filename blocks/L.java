package blocks;

import java.awt.Color;

import tetris.Field;

public class L extends Block {
	public L(Field field) {
		super(field);
		blockColor = Color.ORANGE;
		blockNumber = 5;
		block[1][1] = 1;
		block[1][2] = 1;
		block[1][3] = 1;
		block[2][1] = 1;

		/*
		 * □□□□ □■■■ □■□□ □□□□
		 */
	}
}
