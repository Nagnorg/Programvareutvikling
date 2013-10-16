package no.hig.MartinNGlen.GUIRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.*;

public class GUIRendWorkspace extends JFrame {
	private static int MAX_COMBOBOX = 4;
	
	private ComponentModel workspaceModel = new ComponentModel();
	private JTable workspaceTable = new JTable(workspaceModel);
	private JToolBar workspaceToolbar = new JToolBar();
	private String[] componentTypes = {"JLabel", "JTextField", "JTextArea", "JButton"};
	private String[] componentFill = {"NONE", "HORIZONTAL", "VERTICAL", "BOTH"};
	private String[] componentAnchor = {"CENTER", "NORTH", "NORTHEAST", "EAST", "SOUTH", "SOUTHEAST", "SOUTHWEST", "WEST", "NORTHWEST"};
	private JComboBox componentTypeEditor = new JComboBox (componentTypes);
	private JComboBox componentFillEditor;
	private JComboBox componentAnchorEditor;
	// Set up for internationalization.
	Locale  currentLocale = Locale.getDefault();
	ResourceBundle messages = ResourceBundle.getBundle("GUIRenderer", currentLocale);
	
	public GUIRendWorkspace (){
		super("GUIRenderer");
		
		createMenu();
		
		//table creation
		workspaceModel.setTableFrame(this);
		add(new JScrollPane(workspaceTable));
		workspaceTable.setRowHeight(27);
		Integer[] intArray = new Integer[componentFill.length];
		
		for(int i = 0; i < componentFill.length; i++) intArray[i] = new Integer(i);
		componentFillEditor = new JComboBox(intArray);
		componentFillEditor.setRenderer(new IconComboBox(componentFill));
		
		intArray = new Integer[componentAnchor.length];
		for(int i = 0; i < componentAnchor.length; i++) intArray[i] = new Integer(i);
		componentAnchorEditor = new JComboBox(intArray);
		componentAnchorEditor.setRenderer(new IconComboBox(componentAnchor));
		componentAnchorEditor.setMaximumRowCount(MAX_COMBOBOX);
		
		workspaceTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(componentTypeEditor));
		workspaceTable.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(componentFillEditor));
		workspaceTable.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(componentAnchorEditor));
		workspaceModel.addNewComponentEntry();
		
		//toolbar creation
		JButton newlineButton = new JButton(new ImageIcon(getClass().getResource("/images/toolbar/newlineIcon.png")));
		newlineButton.setToolTipText(messages.getString("GUIRendWorkspace.toolbarNewline"));
		
		JButton saveButton = new JButton(new ImageIcon(getClass().getResource("/images/toolbar/saveIcon.png")));
		saveButton.setToolTipText(messages.getString("GUIRendWorkspace.toolbarSave"));
		
		JButton loadButton  = new JButton(new ImageIcon(getClass().getResource("/images/toolbar/loadIcon.png")));
		loadButton.setToolTipText(messages.getString("GUIRendWorkspace.toolbarLoad"));
		
		newlineButton.addActionListener(new newComponent());
		saveButton.addActionListener(new saveState());
		loadButton.addActionListener(new loadState());
		
		workspaceToolbar.add(newlineButton);
		workspaceToolbar.add(saveButton);
		workspaceToolbar.add(loadButton);
		add(workspaceToolbar, BorderLayout.NORTH);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void createMenu() {
		// Creates file menu and items
		JMenu fileMenu = new JMenu(messages.getString("GUIRendWorkspace.file"));
		fileMenu.setMnemonic('F');
		JMenuItem newlineItem = new JMenuItem(messages.getString("GUIRendWorkspace.fileNewline"));
		newlineItem.setMnemonic('N');
		newlineItem.addActionListener(new newComponent());
		JMenuItem deletelineItem = new JMenuItem(messages.getString("GUIRendWorkspace.fileDeleteline"));
		deletelineItem.setMnemonic('D');
		deletelineItem.addActionListener(new deleteComponent());
		JMenuItem saveItem = new JMenuItem(messages.getString("GUIRendWorkspace.fileSave"));
		saveItem.setMnemonic('S');
		saveItem.addActionListener(new saveState());
		JMenuItem loadItem = new JMenuItem(messages.getString("GUIRendWorkspace.fileLoad"));
		loadItem.setMnemonic('L');
		loadItem.addActionListener(new loadState());
		JMenuItem sourceItem = new JMenuItem(messages.getString("GUIRendWorkspace.fileSource"));
		sourceItem.setMnemonic('O');
		JMenuItem aboutItem = new JMenuItem(messages.getString("GUIRendWorkspace.fileAbout"));
		aboutItem.setMnemonic('A');
		fileMenu.add(newlineItem);
		fileMenu.add(deletelineItem);
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		fileMenu.add(sourceItem);
		fileMenu.add(aboutItem);
		
		JMenu windowMenu = new JMenu(messages.getString("GUIRendWorkspace.window"));
		windowMenu.setMnemonic('W');
		JMenuItem showWindowItem = new JMenuItem(messages.getString("GUIRendWorkspace.windowShow"));
		showWindowItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				workspaceToolbar.setVisible(true);
			}
		});
		JMenuItem hideWindowItem = new JMenuItem(messages.getString("GUIRendWorkspace.windowHide"));
		hideWindowItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				workspaceToolbar.setVisible(false);
			}
		});
		windowMenu.add(showWindowItem);
		windowMenu.add(hideWindowItem);
		
		// Creates the menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
	}
	
	public class IconComboBox extends JLabel implements ListCellRenderer{
		
		String[] componentArray;
		
	    public IconComboBox(String[] tempArray) {
	        setOpaque(true);
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
	        componentArray = tempArray;
	    }
		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			int selectedIndex = ((Integer)value).intValue();
			 
	        if (isSelected) {
	            setBackground(list.getSelectionBackground());
	            setForeground(list.getSelectionForeground());
	        } else {
	            setBackground(list.getBackground());
	            setForeground(list.getForeground());
	        }
	        
	        setText("");
	        setIcon (new ImageIcon (getClass().getResource("/images/table/" + componentArray[selectedIndex].toLowerCase() + "Icon.png")));

			return this;
		}

	}
	
	class newComponent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			workspaceModel.addNewComponentEntry();
		}
		
	}
	
	class deleteComponent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			workspaceModel.removeComponentEntry(workspaceTable.getSelectedRow());
			
		}
		
	}
	
	class saveState implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser(new File("."));
			chooser.setFileSelectionMode (JFileChooser.FILES_ONLY);
			if (chooser.showSaveDialog(GUIRendWorkspace.this)==JFileChooser.CANCEL_OPTION)
				return;
			File f = chooser.getSelectedFile();
			if (f.exists())
				if (JOptionPane.showConfirmDialog(GUIRendWorkspace.this, "Filen finnes, overskrive", "Bekreft", JOptionPane.QUESTION_MESSAGE)!=JOptionPane.YES_OPTION)
					return;
			try {
				ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(f));
				workspaceModel.save (oos);
				oos.close ();	
			} catch (IOException ioe) {
				System.err.println ("Feil på filhåndteringen.");
			}
			
		}
	}
	
	class loadState implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser(new File("."));
			chooser.setFileSelectionMode (JFileChooser.FILES_ONLY);
			if (chooser.showOpenDialog(GUIRendWorkspace.this)==JFileChooser.CANCEL_OPTION)
				return;
			File f = chooser.getSelectedFile();
			try {
				ObjectInputStream ois = new ObjectInputStream (new FileInputStream(f));
				workspaceModel.load (ois);
				ois.close ();	
			} catch (IOException ioe) {
				System.err.println ("Feil på filhåndteringen.");
			}
		}
	}
	
	public static void main (String args[]){
		GUIRendWorkspace workspace = new GUIRendWorkspace();
		workspace.setSize(700,300);
		
		workspace.setVisible(true);
	}

}