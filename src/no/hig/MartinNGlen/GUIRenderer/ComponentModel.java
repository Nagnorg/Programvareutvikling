package no.hig.MartinNGlen.GUIRenderer;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.*;

public class ComponentModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	Locale  currentLocale = Locale.getDefault();
	ResourceBundle messages = ResourceBundle.getBundle("GUIRenderer", currentLocale);
	JFrame mainFrame;
	
	Vector<ComponentDecorator> componentData = new Vector<ComponentDecorator>();
	String attribute[] = 
		{messages.getString("ComponentModel.attributeType"), messages.getString("ComponentModel.attributeVariableName"), 
			messages.getString("ComponentModel.attributeText"), messages.getString("ComponentModel.attributeRow"),
			messages.getString("ComponentModel.attributeColumn"), messages.getString("ComponentModel.attributeRows"),
			messages.getString("ComponentModel.attributeColumns"), messages.getString("ComponentModel.attributeFill"), 
			messages.getString("ComponentModel.attributeAnchor")};
	
	public void addNewComponentEntry () {
		String uniqueName = "Var" + componentData.size();
		ComponentDecorator newComponent = new JLabelComponent(new BaseComponent(uniqueName));
		componentData.add (newComponent);
		fireTableRowsInserted(componentData.size(), componentData.size());
	}
	
	public void removeComponentEntry(int row) {
		fireTableRowsDeleted(row, row);
	}
	
	public void setTableFrame (JFrame f) {
		mainFrame = f;
	}
	
	@Override
	public String getColumnName(int column) {
		return attribute[column];
	}
	@Override
	public int getRowCount() {
		return componentData.size();
	}
	@Override
	public int getColumnCount() {
		return 9;
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0 : return ("").getClass();
			case 1 : return ("").getClass();
			case 2 : return ("").getClass();
			case 3 : return (new Integer(0)).getClass();
			case 4 : return (new Integer(0)).getClass();
			case 5 : return (new Integer(0)).getClass();
			case 6 : return (new Integer(0)).getClass();
			case 7 : return (new ImageIcon()).getClass();
			case 8 : return (new ImageIcon()).getClass();
		}
		return ("").getClass();
	}
	@Override
	public Object getValueAt(int row, int col) {
		ComponentDecorator component = componentData.get(row);
		if (col>0) {
			switch (col) {
				case 1 : return ((BaseComponent)component.getBaseComponent()).getName();
				case 2 : return ((BaseComponent)component.getBaseComponent()).getContent();
				case 3 : return ((BaseComponent)component.getBaseComponent()).getRow();
				case 4 : return ((BaseComponent)component.getBaseComponent()).getColumn();
				case 5 : return ((BaseComponent)component.getBaseComponent()).getNumOfRows();
				case 6 : return ((BaseComponent)component.getBaseComponent()).getNumOfColumns();
				case 7 : return new ImageIcon(getClass().getResource("/images/table/" +((BaseComponent)component.getBaseComponent()).getFill() + "Icon.png"));
				case 8 : return new ImageIcon(getClass().getResource("/images/table/" +((BaseComponent)component.getBaseComponent()).getAnchor() + "Icon.png"));
			}
		} else {
			if (component instanceof JLabelComponent)
				return "Label";
			else if (component instanceof JTextFieldComponent)
				return "Text field";
			else if (component instanceof JTextAreaComponent)
				return "Text area";
			else if (component instanceof JButtonComponent)
				return "Button";
		}
		return null;
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		ComponentDecorator component = componentData.get(rowIndex);
		if (columnIndex>0 && columnIndex < 7){
			switch(columnIndex){
				case 1 : ((BaseComponent)component.getBaseComponent()).setName ((String)aValue); break; 
				case 2 : ((BaseComponent)component.getBaseComponent()).setContent ((String)aValue); break;
				case 3 : ((BaseComponent)component.getBaseComponent()).setRow((Integer)aValue); break;
				case 4 : ((BaseComponent)component.getBaseComponent()).setColumn((Integer)aValue); break;
				case 5 : ((BaseComponent)component.getBaseComponent()).setNumOfRows((Integer)aValue); break;
				case 6 : ((BaseComponent)component.getBaseComponent()).setNumOfColumns((Integer)aValue); break;
			}
		}
		else if (columnIndex==7){
			switch((Integer)aValue){
				case 0 : ((BaseComponent)component.getBaseComponent()).setFill("NONE"); break;
				case 1 : ((BaseComponent)component.getBaseComponent()).setFill("HORIZONTAL"); break;
				case 2 : ((BaseComponent)component.getBaseComponent()).setFill("VERTICAL"); break;
				case 3 : ((BaseComponent)component.getBaseComponent()).setFill("BOTH"); break;
			}
				
			
		}
		else if (columnIndex==8){
			switch((Integer)aValue){
				case 0 : ((BaseComponent)component.getBaseComponent()).setAnchor("CENTER"); break;
				case 1 : ((BaseComponent)component.getBaseComponent()).setAnchor("NORTH"); break;
				case 2 : ((BaseComponent)component.getBaseComponent()).setAnchor("NORTHEAST"); break;
				case 3 : ((BaseComponent)component.getBaseComponent()).setAnchor("EAST"); break;
				case 4 : ((BaseComponent)component.getBaseComponent()).setAnchor("SOUTHEAST"); break;
				case 5 : ((BaseComponent)component.getBaseComponent()).setAnchor("SOUTH"); break;
				case 6 : ((BaseComponent)component.getBaseComponent()).setAnchor("SOUTHWEST"); break;
				case 7 : ((BaseComponent)component.getBaseComponent()).setAnchor("WEST"); break;
				case 9 : ((BaseComponent)component.getBaseComponent()).setAnchor("NORTHWEST"); break;
			}
		}
		else if (columnIndex==0) {
			ComponentDecorator newComponent = null;
			if (((String)aValue).equals("JLabel"))
				newComponent = new JLabelComponent (component.getBaseComponent());
			else if(((String)aValue).equals("JTextField"))
				newComponent = new JTextFieldComponent(component.getBaseComponent());
			else if(((String)aValue).equals("JTextArea"))
				newComponent = new JTextAreaComponent(component.getBaseComponent());
			else if(((String)aValue).equals("JButton"))
				newComponent = new JButtonComponent(component.getBaseComponent());
			componentData.set(rowIndex, newComponent);
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex){
		return true;
		
	}
	
	// Save and Load taken from filehandling example
	
	/**
	 * Handles the writing of the serialized data to the specified file
	 * @param oos output stream of the designated file
	 * @param componentData ComponentDecorator vector with the runtime objects
	 * @param ioe Exception thrown from input/output errors
	 */
	
	public void save (ObjectOutputStream oos) {
		try {
			for (int i = 0; i < componentData.size(); i++)
				oos.writeObject(componentData.get(i));
		} catch (IOException ioe) {
			System.err.println ("Feil under skriving til fil");
		}
	}
	
	/**
	 * Clears all the ComponentDecorator objects and loads new ones from a file
	 * @param ois input stream of the designated file
	 * @param componentData ComponentDecorator vector with the runtime objects
	 * @param cce Exception thrown from trying to cast an object to an unsuitable class
	 * @param cnfe Exception thrown from trying to load an undefined class
	 * @param ioe Exception thrown from input/output errors
	 */
	public void load (ObjectInputStream ois) {
		componentData.clear();
		try {
			while(true) {
				ComponentDecorator item = (ComponentDecorator)ois.readObject();
				componentData.add(item);
			}
		} catch (EOFException eofe) {
			// Slutt på fila
		} catch (ClassCastException cce) {
			System.err.println ("Beklager, men filen inneholder objeckter som ikke er egnet til dette programmet");
		} catch (ClassNotFoundException cnfe) {
			System.err.println ("Beklager, men filen inneholder objekter som ikke er egnet til dette programmet");
		} catch (IOException ioe) {
			System.err.println ("Feil under lesing fra fil");
		} finally {
			fireTableDataChanged();
		}
	}
}
