package no.hig.MartinNGlen.GUIRenderer;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
public class JTextFieldComponent extends ComponentDecorator{
	private static final long serialVersionUID = 1L;
	
	int column; int width; int height;
	
	JSpinner columnSpinner; JSpinner widthSpinner; JSpinner heightSpinner;
	JFrame contextWindow;

	// Set up for internationalization.
	Locale  currentLocale = Locale.getDefault();
	ResourceBundle messages = ResourceBundle.getBundle("GUIRenderer", currentLocale);
	public JTextFieldComponent(BaseComponent newComponent) {
		super(newComponent);
		column = 0; width = 0; height = 0;
	}
	
	/**
	 * Provides a string that declares a JTextField object
	 * @return a string declaring the object, containing column data if set
	 */
	public String stringDeclare() {
		if(column == 0) return "\tJTextField " +baseComponent.getName()+ " = new JTextField(\"" +baseComponent.getContent()+ "\");";
		else return "\tJTextField " +baseComponent.getName()+ " = new JTextField(\"" +baseComponent.getContent()+ ", " +column+ "\");";
	}
	
	/**
	 * stringDefine sets JTextField specific variables, overrides the parent function
	 * @variable sb the object-universal content defined in the baseComponent class
	 * @return a string that defines all the content of the object
	 */
	public String stringDefine() {
		String name = baseComponent.getName();
		StringBuilder sb = new StringBuilder(baseComponent.stringDefine());
		if(width != 0 || height != 0) sb.append("\t\t" +name+ ".setPreferredSize(new java.awt.Dimension (" +width+ ", " +height+ "));\n");
		sb.append("\t\tlayout.setConstraints(" +name+ ", gbc);\n");
		sb.append("\t\tadd(" +name+ ");\n");
		return sb.toString();
	}

	@Override
	public void contextWindow() {
		contextWindow = new JFrame();
		JPanel contextPanel = new JPanel();
		JPanel spinnerPanel = new JPanel();
		
		spinnerPanel.add(new JLabel(messages.getString("ComponentDecorator.specificDataColumn")+": "));
		columnSpinner = new JSpinner(new SpinnerNumberModel(column, 0, 100, 1));
		spinnerPanel.add(columnSpinner);
		spinnerPanel.add(new JLabel(messages.getString("ComponentDecorator.specificDataWidth")+": "));
		widthSpinner = new JSpinner(new SpinnerNumberModel(width, 0, 100, 1));
		spinnerPanel.add(widthSpinner);
		spinnerPanel.add(new JLabel(messages.getString("ComponentDecorator.specificDataHeight")+": "));
		heightSpinner = new JSpinner(new SpinnerNumberModel(height, 0, 100, 1));
		contextPanel.add(spinnerPanel);
		
		contextWindow.add(contextPanel, BorderLayout.CENTER);
		
		JButton okButton = new JButton(messages.getString("ComponentDecorator.specificDataOkButton"));
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				column = (Integer)columnSpinner.getValue();
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
