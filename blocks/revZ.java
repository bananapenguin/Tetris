package blocks;

import java.awt.Color;

import tetris.Field;

public class revZ extends Block {
	public revZ(Field field) {
		super(field);
		blockColor = Color.GREEN;
		blockNumber = 4;
		block[1][1] = 1;
		block[1][2] = 1;
		block[2][0] = 1;
		block[2][1] = 1;

		/*
		 * □□□□ □■■□ ■■□□ □□□□
		 */
	}
}
