package tetris;

import java.applet.AudioClip;

public class SoundBox {

	public AudioClip turn, drop, del;

	public SoundBox(AudioClip[] ac) {
		turn = ac[0];
		drop = ac[1];
		del = ac[2];

	}
}
