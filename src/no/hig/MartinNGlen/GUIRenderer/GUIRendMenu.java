package no.hig.MartinNGlen.GUIRenderer;

import javax.swing.*;

public class GUIRendMenu extends JFrame{
	private static final long serialVersionUID = 1L;

	public GUIRendMenu() {
		super("GUImenu");
		// Creates file menu and items
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		JMenuItem newItem = new JMenuItem("New");
		newItem.setMnemonic('N');
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.setMnemonic('A');
		fileMenu.add(newItem);
		fileMenu.add(aboutItem);
		
		// Creates the menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
	}
	
}
