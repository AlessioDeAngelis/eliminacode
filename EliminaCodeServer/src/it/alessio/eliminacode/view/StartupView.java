package it.alessio.eliminacode.view;

import it.alessio.eliminacode.controller.StartUpController;
import it.alessio.eliminacode.listeners.ModifyInputSerialCodeActionListener;
import it.alessio.eliminacode.listeners.OkButtonStartupActionListener;
import it.alessio.eliminacode.listeners.ValidateSerialActionListener;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class StartupView extends JFrame {
	private JButton generateCodeButton, submitKeyButton;
	private JTextField keyTextField, generatedCodeTextField;
	private JTextArea validationResultTextArea;
	private StartUpController controller;
	
	public StartupView(StartUpController controller) {
		super("SETUP");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);
		this.controller = controller;
	}
	
	public void initialize(){
		this.setLayout(new MigLayout());
		this.generateCodeButton = new JButton("CLICCA PER GENERARE CODICE DI ATTIVAZIONE");
		
		generateCodeButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		generatedCodeTextField = new JTextField();
		generatedCodeTextField.setFont(new Font("SansSerif", Font.BOLD, 20));
		keyTextField = new JTextField();
		keyTextField.setEditable(true);
		keyTextField.setFont(new Font("SansSerif", Font.BOLD, 40));
		keyTextField.setToolTipText("Inserisci qui il tuo codice seriale");
		
		submitKeyButton = new JButton(" CLICCA PER CONVALIDARE CODICE SERIALE");
		submitKeyButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		this.validationResultTextArea = new JTextArea();
		this.validationResultTextArea.setFont(new Font("SansSerif", Font.BOLD, 20));
		this.validationResultTextArea.setForeground(Color.RED);
		this.add(generateCodeButton);
		this.add(generatedCodeTextField,"wrap, w 500!");
		this.add(keyTextField, "w 500!");
		this.add(submitKeyButton, "wrap");	
		this.add(validationResultTextArea, "growx");
	}
	
	public void addListeners() {
		this.generateCodeButton.addActionListener(new OkButtonStartupActionListener(this.controller));
		this.submitKeyButton.addActionListener(new ValidateSerialActionListener(controller));
		this.keyTextField.addActionListener(new ModifyInputSerialCodeActionListener(controller));
	}
	
	public void updateGenerateCodeTextField(String text){
		this.generatedCodeTextField.setText(text);
	}
	
	public void updateValidationResultTextField(String text){
		this.validationResultTextArea.setText(text);
	}
}
