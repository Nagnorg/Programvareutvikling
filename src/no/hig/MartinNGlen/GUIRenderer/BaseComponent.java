package no.hig.MartinNGlen.GUIRenderer;

public class BaseComponent implements Component{
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
		
	}
	
	public BaseComponent(BaseComponent component) {
		name = component.getName();
		content = component.getContent();
		row = component.getRow();
		column = component.getColumn();
		numOfRows = component.getNumOfRows();
		numOfColumns = component.getNumOfColumns();
	}
	
	public String toString(){
		
		return "";
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