package it.alessio.eliminacode.common.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {


	public static synchronized void playSound(final String soundName) {
		new Thread(new Runnable() {
			public void run() {
				AudioInputStream audioInputStream = null;
				try {
					audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				} catch (UnsupportedAudioFileException | IOException e) {
					e.printStackTrace();
				}
				Clip clip = null;
				try {
					clip = AudioSystem.getClip();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
				try {
					clip.open(audioInputStream);
				} catch (LineUnavailableException | IOException e) {
					e.printStackTrace();
				}
				clip.start();
			}
		}).start();
	}
}
