package it.alessio.eliminacode.view;

import it.alessio.eliminacode.common.model.ItalianMonthConverter;
import it.alessio.eliminacode.controller.HistoryController;
import it.alessio.eliminacode.listeners.MyListSelectionListener;
import it.alessio.eliminacode.listeners.OkButtonActionListener;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class HistoryView extends JFrame{
	private JList list = new JList();
	private JButton okButton;
	private JTextArea textArea;

	public HistoryView(String title){
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);
	}
	
	public void initialize(List<Date> dates){
		/*OK BUTTON*/
		okButton = new JButton("OK");
		this.add(okButton);
		/*LIST*/
		DefaultListModel model = new DefaultListModel();
		GregorianCalendar calendar = new GregorianCalendar();
		
		for(Date date : dates){
			calendar.setTime(date);
			int day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
			int month = calendar.get(GregorianCalendar.MONTH);
			String monthInItalian = ItalianMonthConverter.fromNumberToMonth(month);
			int year = calendar.get(GregorianCalendar.YEAR);
			String value = "" + day +"-"+monthInItalian+"-"+year;
			model.addElement(value);
		}
		list.setFont(new Font("SansSerif", Font.BOLD, 14));
		list.setModel(model);
				
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 600));
		this.setLayout(new FlowLayout());
		this.add(listScroller);
		
		/* TEXT AREA */
		this.textArea = new JTextArea();
		this.textArea.setText("");
		this.textArea.setAutoscrolls(true);
		this.textArea.setLineWrap(true);
		this.textArea.setPreferredSize(new Dimension(900,600));
		this.textArea.setFont(new Font("SansSerif", Font.BOLD, 16));

		this.add(this.textArea);
		
		this.pack();

	}
	
	
	public void addListeners(HistoryController historyController){
		this.list.addListSelectionListener(new MyListSelectionListener(historyController));
		this.okButton.addActionListener(new OkButtonActionListener(historyController));
	}
	
	public void updateText(String text){
		this.textArea.setText(text);
	}
}
