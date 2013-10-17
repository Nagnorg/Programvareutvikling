package no.hig.MartinNGlen.GUIRenderer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class JTextAreaComponent extends ComponentDecorator{
	
	int row; int width; int height; int column; 
	boolean isScrollPane; boolean isWordWrap;
	
	
	JSpinner rowSpinner; JSpinner widthSpinner; JSpinner heightSpinner; JSpinner columnSpinner;
	JCheckBox scrollPane; JCheckBox wordWrap; 
	JFrame contextWindow;
	
	// Set up for internationalization.
	Locale  currentLocale = Locale.getDefault();
	ResourceBundle messages = ResourceBundle.getBundle("GUIRenderer", currentLocale);
	public JTextAreaComponent(BaseComponent newComponent) {
		super(newComponent);
		row = 0; width = 0; height = 0;
		isScrollPane = true; isWordWrap = true;
	}

	public String stringDeclare() {
		if(row == 0 && column == 0) return "\tJTextArea " +baseComponent.getName()+ " = new JTextArea(\"" +baseComponent.getContent()+ "\");";
		else return "\tJTextArea " +baseComponent.getName()+ " =new JTextArea(\"" +baseComponent.getContent()+ ", " +row+ ", " +column+ "\");";
	}
	
	/**
	 * @param sb the object-universal content defined in the baseComponent class
	 * @return a string that defines all the content of the object
	 */
	public String stringDefine() {
		String name = baseComponent.getName();
		StringBuilder sb = new StringBuilder(baseComponent.stringDefine());
		if(!isScrollPane) {
			sb.append("\t\tlayout.setConstraints(" +name+ ", gbc);\n");
			sb.append("\t\tadd(" +name+ ");\n");
		}
		else {
			sb.append("\t\tJScrollPane " +name+ "ScrollPane = new JScrollPane(" +name+ ");\n");
			sb.append("\t\t" +name+ "ScrollPane.setPreferredSize(new java.awt.Dimension(" +width+ ", " +height+ "));\n");
			sb.append("\t\tlayout.setConstraints(" +name+ "ScrollPane, gbc);\n");
			sb.append("\t\tadd(" +name+ ");\n");
		}
		if(!isWordWrap) {
			sb.append("\t\t" +name+ ".setLineWrap(true);\n");
			sb.append("\t\t" +name+ ".setWrapStyleWord(true);\n");
		}
		return sb.toString();
	}
	
	public void contextWindow(){
		contextWindow = new JFrame();
		GridLayout gLayout = new GridLayout(0, 1);
		JPanel contextPanel = new JPanel();
		JPanel spinnerPanel = new JPanel();
		JPanel checkBoxPanel = new JPanel();
		
		contextPanel.setLayout(gLayout);
		
		spinnerPanel.add(new JLabel(messages.getString("ComponentDecorator.specificDataRow")+": "));
		rowSpinner = new JSpinner(new SpinnerNumberModel(row, 0, 100, 1));
		spinnerPanel.add(rowSpinner);
		spinnerPanel.add(new JLabel(messages.getString("ComponentDecorator.specificDataColumn")+": "));
		columnSpinner = new JSpinner(new SpinnerNumberModel(column,0,100,1));
		spinnerPanel.add(columnSpinner);
		spinnerPanel.add(new JLabel(messages.getString("ComponentDecorator.specificDataWidth")+": "));
		widthSpinner = new JSpinner(new SpinnerNumberModel(width, 0, 100, 1));
		spinnerPanel.add(widthSpinner);
		spinnerPanel.add(new JLabel(messages.getString("ComponentDecorator.specificDataHeight")+": "));
		heightSpinner = new JSpinner(new SpinnerNumberModel(height, 0, 100, 1));
		spinnerPanel.add(heightSpinner);
		
		contextPanel.add(spinnerPanel);
		
		wordWrap = new JCheckBox(messages.getString("ComponentDecorator.specificDataWordWrap"));
		checkBoxPanel.add(wordWrap);
		wordWrap.setSelected(isWordWrap);
		scrollPane = new JCheckBox(messages.getString("ComponentDecorator.specificDataScrollPane"));
		scrollPane.setSelected(isScrollPane);
		checkBoxPanel.add(scrollPane);
		contextPanel.add(checkBoxPanel);
		
		
		contextWindow.add(contextPanel, BorderLayout.CENTER);
		
		JButton okButton = new JButton(messages.getString("ComponentDecorator.specificDataOkButton"));
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				row = (Integer)rowSpinner.getValue();
				width = (Integer)widthSpinner.getValue();
				height = (Integer)heightSpinner.getValue();
				column = (Integer)columnSpinner.getValue();
				isScrollPane = scrollPane.isSelected();
				isWordWrap = wordWrap.isSelected();
				if(contextWindow != null) {
			        EventQueue.invokeLater(new Runnable() {
			            public void run() {
			                contextWindow.dispatchEvent(new WindowEvent(contextWindow, WindowEvent.WINDOW_CLOSING));
			            }
			        });
			    }
			}
		});
		contextWindow.add(okButton, BorderLayout.SOUTH);
		
		contextWindow.setVisible(true);
		contextWindow.pack();
	}
}
