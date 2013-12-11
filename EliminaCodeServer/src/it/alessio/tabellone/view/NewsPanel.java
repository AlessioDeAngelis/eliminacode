package it.alessio.tabellone.view;

import it.alessio.tabellone.news.FeedMessage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewsPanel extends JPanel {
	private FeedMessage feedMessage;
	/**
	 * The field that shows the number that is being served now
	 * */
	private JTextArea newsTextField;

	public NewsPanel() {
		super();
	}

	public NewsPanel(FeedMessage feedMessage) {
		super();
		this.feedMessage = feedMessage;
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new GridLayout(1, 2));

		this.newsTextField = new JTextArea();
//		 this.newsTextField.setPreferredSize(new Dimension(500, 300));
		this.newsTextField.setFont(new Font("SansSerif", Font.BOLD, 20));
//		this.newsTextField.setHorizontalAlignment(JTextField.CENTER);
		// this.currentNumberTextField.setLineWrap(true);
		this.newsTextField.setText(feedMessage.getTitle() + "\n" + feedMessage.getDescription());
		// this.currentNumberTextField.setBackground(Color.LIGHT_GRAY);
		this.add(newsTextField, BorderLayout.SOUTH);
	}
	
	public void updateText(FeedMessage message){
		this.feedMessage = message;
		this.newsTextField.setText(feedMessage.getTitle() + "\n" + feedMessage.getDescription());
	}

	public FeedMessage getFeedMessage() {
		return feedMessage;
	}

	public void setFeedMessage(FeedMessage feedMessage) {
		this.feedMessage = feedMessage;
	}

}
