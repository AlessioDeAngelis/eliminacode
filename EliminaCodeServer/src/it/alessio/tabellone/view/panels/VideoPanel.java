package it.alessio.tabellone.view.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;

import com.sun.jna.NativeLibrary;

public class VideoPanel extends JPanel {
	
	private EmbeddedMediaPlayer player;

	public VideoPanel(String vlcPath) {
		NativeLibrary.addSearchPath("libvlc", vlcPath);
		EmbeddedMediaPlayerComponent videoCanvas = new EmbeddedMediaPlayerComponent();
		this.setLayout(new BorderLayout());
//		this.add(videoCanvas, BorderLayout.CENTER);
		this.add(videoCanvas);
		this.player = videoCanvas.getMediaPlayer();
	}
	
	public void play(String media) {
//		player.prepareMedia(media);
		player.setRepeat(true);
		player.setAdjustVideo(true);
		player.setPlaySubItems(true);
		player.prepareMedia(media);
		player.parseMedia();
		player.play();
	}
}
