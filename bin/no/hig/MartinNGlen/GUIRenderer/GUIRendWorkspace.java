package no.hig.MartinNGlen.GUIRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.*;

public class GUIRendWorkspace extends JFrame {
	private ComponentModel workspaceModel = new ComponentModel();
	private JTable workspaceTable = new JTable(workspaceModel);
	private JToolBar workspaceToolbar = new JToolBar();
	private String[] componentTypes = {"JLabel", "JTextField", "JTextArea", "JButton"};
	private String[] componentFill = {"NONE", "HORIZONTAL", "VERTICAL", "BOTH"};
	private String[] componentAnchor = {"NORTH", "SOUTH", "EAST", "WEST"};
	private JComboBox componentTypeEditor = new JComboBox (componentTypes);
	private JComboBox componentFillEditor = new JComboBox(componentFill);
	private JComboBox componentAnchorEditor = new JComboBox(componentAnchor);
	// Set up for internationalization.
	Locale  currentLocale = Locale.getDefault();
	ResourceBundle messages = ResourceBundle.getBundle("GUIRenderer", currentLocale);
	
	public GUIRendWorkspace (){
		super("GUIRenderer");
		
		createMenu();
		workspaceModel.setTableFrame(this);
		add(new JScrollPane(workspaceTable));
		workspaceTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(componentTypeEditor));
		workspaceTable.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(componentFillEditor));
		workspaceTable.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(componentAnchorEditor));
		workspaceModel.addNewComponentEntry();
		JButton newButton = new JButton();
		newButton.addActionListener(new newComponent());
		
		workspaceToolbar.add(newButton);
		add(workspaceToolbar, BorderLayout.NORTH);
		pack();
		
	}
	
	public void createMenu() {
		// Creates file menu and items
		JMenu fileMenu = new JMenu(messages.getString("GUIRendWorkspace.file"));
		fileMenu.setMnemonic('F');
		JMenuItem newItem = new JMenuItem(messages.getString("GUIRendWorkspace.fileNew"));
		newItem.setMnemonic('N');
		newItem.addActionListener(new newComponent());
		JMenuItem saveItem = new JMenuItem(messages.getString("GUIRendWorkspace.fileSave"));
		saveItem.setMnemonic('S');
		JMenuItem loadItem = new JMenuItem(messages.getString("GUIRendWorkspace.fileLoad"));
		loadItem.setMnemonic('L');
		JMenuItem sourceItem = new JMenuItem(messages.getString("GUIRendWorkspace.fileSource"));
		sourceItem.setMnemonic('O');
		JMenuItem aboutItem = new JMenuItem(messages.getString("GUIRendWorkspace.fileAbout"));
		aboutItem.setMnemonic('A');
		fileMenu.add(newItem);
		fileMenu.add(sourceItem);
		fileMenu.add(aboutItem);
		
		JMenu windowMenu = new JMenu(messages.getString("GUIRendWorkspace.window"));
		windowMenu.setMnemonic('W');
		JMenuItem showWindowItem = new JMenuItem(messages.getString("GUIRendWorkspace.windowShow"));
		JMenuItem hideWindowItem = new JMenuItem(messages.getString("GUIRendWorkspace.windowHide"));
		windowMenu.add(showWindowItem);
		windowMenu.add(hideWindowItem);
		
		// Creates the menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
	}
	
	class newComponent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			workspaceModel.addNewComponentEntry();
		}
		
	}
	
	public static void main (String args[]){
		GUIRendWorkspace workspace = new GUIRendWorkspace();
		workspace.setSize(700,300);
		
		workspace.setVisible(true);
	}

}