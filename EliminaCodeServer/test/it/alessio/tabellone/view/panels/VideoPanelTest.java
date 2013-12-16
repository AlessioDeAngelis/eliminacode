package it.alessio.tabellone.view.panels;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.junit.Test;

public class VideoPanelTest {

	
	public static void main(String[] args) {
		JFrame frame = new JFrame("TEST");
		frame.setEnabled(true);
		frame.setVisible(true);
		frame.setSize(600, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.validate();
        frame.setLayout(new BorderLayout());
        JButton b = new JButton("ciao");
//        frame.add(b,BorderLayout.LINE_START);
        String vlcPath = "C:/Program Files/VideoLAN/VLC";
        String videoPath = "http://www.engr.colostate.edu/me/facil/dynamics/files/bird.avi";
        VideoPanel panel = new VideoPanel(vlcPath);
        frame.add(panel,BorderLayout.CENTER);
        panel.play(videoPath);
	}
	
	public void playTest() {
	
	}
	
	
}
