package blocks;

import java.awt.Color;

import tetris.Field;

public class revL extends Block {
	public revL(Field field) {
		super(field);
		blockColor = Color.BLUE;
		blockNumber = 6;
		block[1][0] = 1;
		block[1][1] = 1;
		block[1][2] = 1;
		block[2][2] = 1;

		/*
		 * □□□□ ■■■□ □□■□ □□□□
		 */
	}
}
