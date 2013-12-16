package it.alessio.tastierino.news;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import com.sun.jna.NativeLibrary;

	public class PlayerPanel extends JPanel {

		private String path = "C:/Program Files/VideoLAN/VLC";

		private EmbeddedMediaPlayer player;

		public PlayerPanel() {
			NativeLibrary.addSearchPath("libvlc", path);
			EmbeddedMediaPlayerComponent videoCanvas = new EmbeddedMediaPlayerComponent();
			this.setLayout(new BorderLayout());
			this.add(videoCanvas, BorderLayout.CENTER);
			this.player = videoCanvas.getMediaPlayer();
		}

		public void play(String media) {
			player.prepareMedia(media);
			player.parseMedia();
			player.play();
		}
	}

