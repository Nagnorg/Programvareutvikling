package no.hig.MartinNGlen.GUIRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.*;

/**
 * Class containing creation of GUI and most of its handling for the GUI renderer.
 * @version 0.1
 * @author Glen & Martin
 *
 */
public class GUIRendWorkspace extends JFrame {
	private static final long serialVersionUID = 1L;

	private static int MAX_COMBOBOX = 4;
	
	private ComponentModel workspaceModel = new ComponentModel();
	private JTable workspaceTable = new JTable(workspaceModel);
	private JMenuBar workspaceMenu;
	private JToolBar workspaceToolbar;
	private JPopupMenu tablePopup = new JPopupMenu();
	
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
		
		workspaceMenu = createMenu();
		workspaceToolbar = createToolbar();
		
		//table creation
		workspaceModel.setTableFrame(this);
		add(new JScrollPane(workspaceTable));
		workspaceTable.setRowHeight(27);		// Set row height as 27 for icons in rows.
		
		// Fills the combobox for "fill" and "anchor" with icons.
		Integer[] intArray = new Integer[componentFill.length];
		
		for(int i = 0; i < componentFill.length; i++) intArray[i] = new Integer(i);
		componentFillEditor = new JComboBox(intArray);
		componentFillEditor.setRenderer(new IconComboBox(componentFill));
		
		intArray = new Integer[componentAnchor.length];
		for(int i = 0; i < componentAnchor.length; i++) intArray[i] = new Integer(i);
		componentAnchorEditor = new JComboBox(intArray);
		componentAnchorEditor.setRenderer(new IconComboBox(componentAnchor));
		componentAnchorEditor.setMaximumRowCount(MAX_COMBOBOX);
		
		// Fills non-free editable columns with filled combo boxes.
		workspaceTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(componentTypeEditor));
		workspaceTable.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(componentFillEditor));
		workspaceTable.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(componentAnchorEditor));
		
		//popup menu creation
		JMenuItem specialPropertiesItem = new JMenuItem(messages.getString("GUIRendWorkspace.popupProperties"));
		specialPropertiesItem.addActionListener(new editSpecificContent());
		JMenuItem deletelineItem = new JMenuItem(messages.getString("GUIRendWorkspace.fileDeleteline"));
		deletelineItem.addActionListener(new deleteComponent());
		
		tablePopup.add(specialPropertiesItem);
		tablePopup.add(deletelineItem);
		
	    MouseListener popupListener = new PopupListener();
	    workspaceTable.addMouseListener(popupListener);
	    
	    setJMenuBar(workspaceMenu);
	    add(workspaceToolbar, BorderLayout.NORTH);
		
	    pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	/**
	 * Creates the menu used by the GUI
	 * @return JMenu specific for the project
	 */
	public JMenuBar createMenu() {
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
		sourceItem.setMnemonic('G');
		sourceItem.addActionListener(new generateCode());
		
		fileMenu.add(newlineItem);
		fileMenu.add(deletelineItem);
		fileMenu.addSeparator();
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		fileMenu.add(sourceItem);
		
		JMenu windowMenu = new JMenu(messages.getString("GUIRendWorkspace.window"));
		windowMenu.setMnemonic('W');
		
		JMenuItem toolbarWindowItem = new JMenuItem(messages.getString("GUIRendWorkspace.windowToolbar"));
		toolbarWindowItem.setMnemonic('T');
		toolbarWindowItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(workspaceToolbar.isVisible() == true) workspaceToolbar.setVisible(false);
				else workspaceToolbar.setVisible(true);
			}
		});
		
		windowMenu.add(toolbarWindowItem);
		
		// Creates the menu bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		return menuBar;
	}
	
	/**
	 * Creates the toolbar for the GUI
	 * @return JToolBar specific for the project
	 */
	public JToolBar createToolbar(){
		//toolbar creation
		JButton newlineButton = new JButton(new ImageIcon(getClass().getResource("/images/toolbar/newlineIcon.png")));
		newlineButton.setToolTipText(messages.getString("GUIRendWorkspace.toolbarNewline"));
		
		JButton deletelineButton = new JButton(new ImageIcon(getClass().getResource("/images/toolbar/removelineIcon.png")));
		deletelineButton.setToolTipText(messages.getString("GUIRendWorkspace.toolbarDeleteline"));
		
		JButton saveButton = new JButton(new ImageIcon(getClass().getResource("/images/toolbar/saveIcon.png")));
		saveButton.setToolTipText(messages.getString("GUIRendWorkspace.toolbarSave"));
		
		JButton loadButton  = new JButton(new ImageIcon(getClass().getResource("/images/toolbar/loadIcon.png")));
		loadButton.setToolTipText(messages.getString("GUIRendWorkspace.toolbarLoad"));
		
		JButton generateButton = new JButton(new ImageIcon(getClass().getResource("/images/toolbar/generateIcon.png")));
		generateButton.setToolTipText(messages.getString("GUIRendWorkspace.toolbarGenerate"));
		
		newlineButton.addActionListener(new newComponent());
		deletelineButton.addActionListener(new deleteComponent());
		saveButton.addActionListener(new saveState());
		loadButton.addActionListener(new loadState());
		generateButton.addActionListener(new generateCode());
		
		JToolBar toolBar = new JToolBar();
		toolBar.add(newlineButton);
		toolBar.add(deletelineButton);
		toolBar.add(saveButton);
		toolBar.add(loadButton);
		toolBar.add(generateButton);
		
		return toolBar;
	}
	
	/**
	 * 
	 * Draws every member of the combobox as a icon.
	 * @source "/images/tables/*icontype*Icon.png
	 *
	 */
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
	/**
	 * 
	 * Adds a new component item in the model
	 *
	 */
	class newComponent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			workspaceModel.addNewComponentEntry();
		}
		
	}
	
	/**
	 * 
	 * removes a component item from the model
	 *
	 */
	class deleteComponent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			workspaceModel.removeComponentEntry(workspaceTable.getSelectedRow());
			
		}
		
	}
	
	// Save and Load taken from FileHandling example
	
	/** 
	 * Takes the current list of objects in the file and outputs it to a file in a serialized format
	 * @param chooser JFileChooser object, java's file selection mechanism 
	 * @param workspaceModel ComponentModel object with all the necessary resources
	 * @param ioe Signal of an input/output error
	 */
	class saveState implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser(new File("."));
			chooser.setFileSelectionMode (JFileChooser.FILES_ONLY);
			if (chooser.showSaveDialog(GUIRendWorkspace.this) == JFileChooser.CANCEL_OPTION)
				return;
			File f = chooser.getSelectedFile();
			if (f.exists())
				if (JOptionPane.showConfirmDialog(GUIRendWorkspace.this, messages.getString("GUIRendWorkspace.fileExistMessage"), messages.getString("GUIRendWorkspace.fileExistConfirm"), JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)
					return;
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				workspaceModel.save(oos);
				oos.close();	
			} catch(IOException ioe) {
				System.err.println(messages.getString("ErrorMessage.IOException"));
			}
			
		}
	}
	
	/** 
	 * Loads a serialized file into the workspace of the program
	 * @param chooser JFileChooser object, java's file selection mechanism 
	 * @param workspaceModel ComponentModel object with all the necessary resources
	 * @param ioe Signal of an input/output error
	 */
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
				System.err.println(messages.getString("ErrorMessage.IOException"));
			}
		}
	}
	
	/**
	 * Creates a source code out of all the created objects
	 * @param chooser JFileChoosen object, java's file selection mechanism
	 * @param dateString string format of the user's computer time in Unix epoch time
	 */
	class generateCode implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser(new File("."));
			chooser.setFileSelectionMode (JFileChooser.FILES_ONLY);
			if (chooser.showSaveDialog(GUIRendWorkspace.this)==JFileChooser.CANCEL_OPTION)
				return;
			File f = chooser.getSelectedFile();
			if (f.exists())
				if (JOptionPane.showConfirmDialog(GUIRendWorkspace.this, messages.getString("GUIRendWorkspace.fileExistMessage"), messages.getString("GUIRendWorkspace.fileExistConfirm"), JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)
					return;
			try {
				BufferedWriter bw = new BufferedWriter (new FileWriter (f));
				Long time = System.currentTimeMillis();
				String dateString = time.toString();
				Vector<ComponentDecorator> items = workspaceModel.getComponentData(); 
				
				bw.write("import javax.swing.*;");
				bw.newLine();
				bw.write("import java.awt.*;");
				bw.newLine(); bw.newLine();
				bw.write("// Autogenerated code");
				bw.newLine();
				bw.write("public class Auto" +dateString+ " extends JPanel {");
				bw.newLine();
				
				// Iterates through all the ComponentDecorator objects and writes their object declaration
				for (ComponentDecorator item : items) {
					bw.write(item.stringDeclare());
					bw.newLine();
				}
				
				bw.write("public Auto" +dateString+ " () {");
				bw.newLine();
				bw.write("\t\tGridBagLayout layout = new GridBagLayout();");
				bw.newLine();
				bw.write("\t\tGridBagConstraints gbc = new GridBagConstraints();");
				bw.newLine();
				bw.write("\t\tsetLayout(layout);");
				bw.newLine(); bw.newLine();
				
				// Iterates through all the ComponentDecorator objects and defines their content
				for (ComponentDecorator item : items) {
					bw.write(item.stringDefine());
					bw.newLine();
				}
				
				bw.write("\t}");
				bw.newLine();
				bw.write("}");
				
				bw.close();
				
			} catch (IOException ioe) {
				System.err.println (messages.getString("ErrorMessage.IOException"));
			}
		}
	}
	
	class editSpecificContent implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			workspaceModel.getData(workspaceTable.getSelectedRow()).contextWindow();
		}
		
	}
	/**
	 * Creates a popup at mouse if it clicks on the table while a row is selected
	 *
	 */
	class PopupListener extends MouseAdapter {
	    public void mousePressed(MouseEvent e) {
	        showPopup(e);
	    }

	    public void mouseReleased(MouseEvent e) {
	        showPopup(e);
	    }

	    private void showPopup(MouseEvent e) {
	        if (e.isPopupTrigger() && workspaceTable.getSelectedRow() != -1) {
	            tablePopup.show(e.getComponent(),
	                       e.getX(), e.getY());
	        }
	    }
	}
	
	public static void main (String args[]){
		GUIRendWorkspace workspace = new GUIRendWorkspace();
		workspace.setSize(700,300);
		
		workspace.setVisible(true);
	}

}