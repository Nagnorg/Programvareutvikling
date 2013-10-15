package no.hig.MartinNGlen.GUIRenderer;

import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.*;

public class GUIRendWorkspace extends JFrame {
	private ComponentModel workspaceModel = new ComponentModel();
	private JTable workspaceTable = new JTable(workspaceModel);
	private String[] componentTypes = {"JLabel", "JTextField", "JTextArea", "JButton"};
	private JComboBox componentTypeEditor = new JComboBox (componentTypes);
	// Set up for internationalization.
	Locale  currentLocale = Locale.getDefault();
	ResourceBundle messages = ResourceBundle.getBundle("GUIRenderer", currentLocale);
	
	public GUIRendWorkspace (){
		super("GUIRenderer");
		
		createMenu();
		workspaceModel.setTableFrame(this);
		add(new JScrollPane(workspaceTable));
		workspaceTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(componentTypeEditor));
		workspaceModel.addNewComponentEntry();
	}
	
	public void createMenu() {
		// Creates file menu and items
		JMenu fileMenu = new JMenu(messages.getString("GUIRendWorkspace.file"));
		fileMenu.setMnemonic('F');
		JMenuItem newItem = new JMenuItem(messages.getString("GUIRendWorkspace.fileNew"));
		newItem.setMnemonic('N');
		JMenuItem saveItem = new JMenuItem(messages.getString("GUIRendWorkspace.fileSave"));
		saveItem.setMnemonic('S');
		JMenuItem loadItem = new JMenuItem(messages.getString("GUIRendWorkspace.fileLoad"));
		loadItem.setMnemonic('L');
		JMenuItem aboutItem = new JMenuItem(messages.getString("GUIRendWorkspace.fileAbout"));
		aboutItem.setMnemonic('A');
		fileMenu.add(newItem);
		fileMenu.add(aboutItem);
		
		// Creates the menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
	}
	
	public static void main (String args[]){
		GUIRendWorkspace workspace = new GUIRendWorkspace();
		workspace.setSize(700,300);
		
		workspace.setVisible(true);
	}

}