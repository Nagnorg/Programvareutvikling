package no.hig.MartinNGlen.GUIRenderer;

import java.io.Serializable;

abstract class ComponentDecorator implements Serializable {

	protected static final long serialVersionUID = 1L;
	protected BaseComponent baseComponent;
	
	public ComponentDecorator(BaseComponent newComponent){
		baseComponent = newComponent;
	}
	
	public BaseComponent getBaseComponent() {
		  return baseComponent;
	}
	
	// A string containing the declaration of an object. Function is overwritten by child classes.
	abstract String stringDeclare();
	
	/**
	 * Provides a default way for child classes to provide a string that defines the content of their attributes
	 * @return a string that defines and adds a standard object to the JPanel
	 */
	public String stringDefine() {
		String name = baseComponent.getName();
		StringBuilder sb = new StringBuilder(baseComponent.stringDefine());
		sb.append("\t\tlayout.setConstraints(" +name+ ", gbc);\n");
		sb.append("\t\tadd(" +name+ ");\n");
		return sb.toString();
	}
	
	abstract void contextWindow();
}
