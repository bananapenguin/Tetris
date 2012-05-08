package tetris;

import java.applet.*;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Tetris extends Applet {
	MainPanel mainPane;

	Field field;

	ImageBox ib;

	SoundBox sb;

	Image[] img = new Image[7];

	AudioClip[] ac = new AudioClip[3];

	Thread th;

	public void init() {
		setSize(500, 500);
		setLayout(null);

		createImgs();
		createSound();

		mainPane = new MainPanel(ib, sb);
		th = new Thread(mainPane);

		add(mainPane);
		mainPane.setFocusable(true);
		th.start();
	}

	public void createImgs() {
		// imgBuf
		img[0] = createImage(500, 500);
		// GameImg
		img[1] = createImage(240, 420);
		// NextImg
		img[2] = createImage(100, 100);
		// LineImg
		img[3] = createImage(100, 50);
		// ScoreImg
		img[4] = createImage(100, 50);
		// Tetromino.gif
		img[5] =new ImageIcon(getClass().getResource("/img/tetromino.gif")).getImage();
		// BuckGround
		img[6] = new ImageIcon(getClass().getResource("/img/background.jpg")).getImage();

		ib = new ImageBox(img);
	}

	public void createSound() {
		ac[0] =
			Applet.newAudioClip(getClass().getResource("/se/bachi.wav"));
		ac[1] =
			Applet.newAudioClip(getClass().getResource("/se/bosu00.wav"));
		ac[2] =
			Applet.newAudioClip(getClass().getResource("/se/power27.wav"));

		sb = new SoundBox(ac);

	}

	public void ThreadStop() {
		th = null;
	}

}
