package blocks;

import java.awt.Color;

import tetris.Field;

public class Box extends Block {
	public Box(Field field) {
		super(field);
		blockColor = Color.YELLOW;
		blockNumber = 1;
		block[1][1] = 1;
		block[2][1] = 1;
		block[1][2] = 1;
		block[2][2] = 1;

		/*
		 * □□□□ □■■□ □■■□ □□□□
		 */
	}
}
