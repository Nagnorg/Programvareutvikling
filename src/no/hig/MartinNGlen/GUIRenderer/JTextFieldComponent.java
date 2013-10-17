package no.hig.MartinNGlen.GUIRenderer;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
public class JTextFieldComponent extends ComponentDecorator{
	
	int row; int width; int height;
	
	JSpinner rowSpinner; JSpinner widthSpinner; JSpinner heightSpinner;
	JFrame contextWindow;

	public JTextFieldComponent(BaseComponent newComponent) {
		super(newComponent);
		row = 0; width = 0; height = 0;
	}
	
	public String stringDeclare() {
		return "\tJTextFieldComponent " +baseComponent.getName()+ " = new JTextFieldComponent(\"" +baseComponent.getContent()+ "\");";
	}

	@Override
	public void contextWindow() {
		contextWindow = new JFrame();
		GridLayout gLayout = new GridLayout(0, 1);
		JPanel contextPanel = new JPanel();
		JPanel spinnerPanel = new JPanel();
		
		spinnerPanel.add(new JLabel("Row: "));
		rowSpinner = new JSpinner(new SpinnerNumberModel(row, 0, 100, 1));
		spinnerPanel.add(rowSpinner);
		spinnerPanel.add(new JLabel("Width: "));
		widthSpinner = new JSpinner(new SpinnerNumberModel(width, 0, 100, 1));
		spinnerPanel.add(widthSpinner);
		spinnerPanel.add(new JLabel("Height: "));
		heightSpinner = new JSpinner(new SpinnerNumberModel(height, 0, 100, 1));
		contextPanel.add(spinnerPanel);
		
		contextWindow.add(contextPanel, BorderLayout.CENTER);
		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				row = (Integer)rowSpinner.getValue();
				width = (Integer)widthSpinner.getValue();
				height = (Integer)heightSpinner.getValue();
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
