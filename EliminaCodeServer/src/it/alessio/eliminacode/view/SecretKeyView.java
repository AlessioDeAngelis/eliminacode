package it.alessio.eliminacode.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.alessio.eliminacode.controller.SecretKeyGeneratorController;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class SecretKeyView extends JFrame {
	private JTextField inputText, outputText;
	private JButton generateButton;
	private SecretKeyGeneratorController controller;

	public SecretKeyView(SecretKeyGeneratorController controller) {
		super("GENERATORE DI CHIAVI");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);
		this.controller = controller;
		initialize();
	}

	private void initialize() {
		this.setLayout(new MigLayout("fill"));

		this.inputText = new JTextField();
		this.inputText.setEditable(true);
		this.inputText.setFont(new Font("SansSerif", Font.BOLD, 20));
		this.generateButton = new JButton("GENERA SERIALE");
		this.generateButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		this.outputText = new JTextField();
		this.outputText.setFont(new Font("SansSerif", Font.BOLD, 20));

		this.add(inputText, "w 700!, growx");
		this.add(generateButton, "wrap");
		this.add(outputText, "w 700!, growx");

		this.generateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.generateSerialCode();
			}
		});

		this.inputText.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JTextField field = (JTextField) event.getSource();
				if (field != null && field.getText() != null && !field.getText().equals("")) {
					controller.modifyInputText(field.getText());
				}
			}
		});
	}

	public void changeOutputText(String serialCode) {
		this.outputText.setText(serialCode);
	}
}
