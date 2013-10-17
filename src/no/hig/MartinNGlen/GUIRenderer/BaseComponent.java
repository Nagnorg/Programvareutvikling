package no.hig.MartinNGlen.GUIRenderer;

import java.io.Serializable;

public class BaseComponent implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String content;
	private int row;
	private int column;
	private int numOfRows;
	private int numOfColumns;
	private String fill;
	private String anchor;
	
	public BaseComponent(String tempName){
		name = tempName;
		content = "";
		row = 1;
		column = 1;
		numOfRows = 1;
		numOfColumns = 1;
		fill = "NONE";
		anchor = "CENTER";
		
	}
	
	public BaseComponent(BaseComponent component) {
		name = component.getName();
		content = component.getContent();
		row = component.getRow();
		column = component.getColumn();
		numOfRows = component.getNumOfRows();
		numOfColumns = component.getNumOfColumns();
		fill = component.getFill();
		anchor = component.getAnchor();
	}
	
	/**
	 * Generates component specific definitions
	 * @return string that defines the properties of the BaseComponent object
	 */
	public String stringDefine() {
		StringBuilder sb = new StringBuilder();
		sb.append("\t\tgbc.gridx = " +column+ ";\n");
		sb.append("\t\tgbc.gridx = " +row+ ";\n");
		sb.append("\t\tgbc.gridwidth = " +numOfColumns+ ";\n");
		sb.append("\t\tgbc.gridheight = " +numOfRows+ ";\n");
		sb.append("\t\tgbc.anchor = java.awt.GridBagConstraints." +anchor+ ";\n");
		sb.append("\t\tgbc.fill = java.awt.GridBagConstraints." +fill+ ";\n");
		return sb.toString();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * @return the numOfRows
	 */
	public int getNumOfRows() {
		return numOfRows;
	}

	/**
	 * @param numOfRows the numOfRows to set
	 */
	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}

	/**
	 * @return the numOfColumns
	 */
	public int getNumOfColumns() {
		return numOfColumns;
	}

	/**
	 * @param numOfColumns the numOfColumns to set
	 */
	public void setNumOfColumns(int numOfColumns) {
		this.numOfColumns = numOfColumns;
	}

	/**
	 * @return the fill
	 */
	public String getFill() {
		return fill;
	}

	/**
	 * @param fill the fill to set
	 */
	public void setFill(String fill) {
		this.fill = fill;
	}

	/**
	 * @return the anchor
	 */
	public String getAnchor() {
		return anchor;
	}

	/**
	 * @param anchor the anchor to set
	 */
	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}


}
