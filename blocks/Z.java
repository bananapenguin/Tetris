package blocks;

import java.awt.Color;

import tetris.Field;

public class Z extends Block {
	public Z(Field field) {
		super(field);
		blockColor = Color.RED;
		blockNumber = 3;
		block[1][0] = 1;
		block[1][1] = 1;
		block[2][1] = 1;
		block[2][2] = 1;

		/*
		 * □□□□ ■■□□ □■■□ □□□□
		 */
	}
}
