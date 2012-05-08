package tetris;

import java.applet.Applet;
import java.awt.*;

public class ImageBox extends Applet {
	public Image imgBuf, GameImg, NextImg, LineImg, ScoreImg, TetrominoImg, BG;

	Graphics gBuf, GameGra, NextGra, LineGra, ScoreGra;

	public ImageBox(Image[] buffer) {
		imgBuf = buffer[0];
		GameImg = buffer[1];
		NextImg = buffer[2];
		LineImg = buffer[3];
		ScoreImg = buffer[4];
		TetrominoImg = buffer[5];
		BG = buffer[6];

		gBuf = imgBuf.getGraphics();
		GameGra = GameImg.getGraphics();
		NextGra = NextImg.getGraphics();
		LineGra = LineImg.getGraphics();
		ScoreGra = ScoreImg.getGraphics();
	}

}
