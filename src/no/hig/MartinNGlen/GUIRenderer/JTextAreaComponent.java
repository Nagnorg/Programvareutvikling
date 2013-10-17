package no.hig.MartinNGlen.GUIRenderer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class JTextAreaComponent extends ComponentDecorator{
	
	int row; int width; int height; int column; 
	boolean isScrollPane; boolean isWordWrap;
	
	
	JSpinner rowSpinner; JSpinner widthSpinner; JSpinner heightSpinner; JSpinner columnSpinner;
	JCheckBox scrollPane; JCheckBox wordWrap; 
	JFrame contextWindow;
	
	
	public JTextAreaComponent(BaseComponent newComponent) {
		super(newComponent);
		row = 0; width = 0; height = 0;
		isScrollPane = true; isWordWrap = true;
	}

	public String stringDeclare() {
		return "\tJTextAreaComponent " +baseComponent.getName()+ " = new JTextAreaComponent(\"" +baseComponent.getContent()+ "\");";
	}
	
	public void contextWindow(){
		contextWindow = new JFrame();
		GridLayout gLayout = new GridLayout(0, 1);
		JPanel contextPanel = new JPanel();
		JPanel spinnerPanel = new JPanel();
		JPanel checkBoxPanel = new JPanel();
		
		contextPanel.setLayout(gLayout);
		
		spinnerPanel.add(new JLabel("Row: "));
		rowSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		spinnerPanel.add(rowSpinner);
		spinnerPanel.add(new JLabel("Width: "));
		widthSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		spinnerPanel.add(widthSpinner);
		spinnerPanel.add(new JLabel("Height: "));
		heightSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		spinnerPanel.add(heightSpinner);
		spinnerPanel.add(new JLabel("Coulumn: "));
		columnSpinner = new JSpinner(new SpinnerNumberModel(0,0,100,1));
		
		contextPanel.add(spinnerPanel);
		
		wordWrap = new JCheckBox("Wordwrap text in area");
		checkBoxPanel.add(wordWrap);
		wordWrap.setSelected(isWordWrap);
		scrollPane = new JCheckBox("Put area in scroll pane");
		scrollPane.setSelected(isScrollPane);
		checkBoxPanel.add(scrollPane);
		contextPanel.add(checkBoxPanel);
		
		
		contextWindow.add(contextPanel, BorderLayout.CENTER);
		
		JButton okButton = new JButton("Ok");
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
