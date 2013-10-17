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
	
	public String toString(){
		return baseComponent.toString();
	}
	
	public String stringDeclare() {
		return "";
	}
	
	public String stringDefine() {
		String name = baseComponent.getName();
		StringBuilder sb = new StringBuilder(baseComponent.stringDefine());
		sb.append("\t\tlayout.setConstraints(" +name+ ", gbc);\n");
		sb.append("\t\tadd(" +name+ ");\n");
		return sb.toString();
	}
	
	public void contextWindow() {
	
	}
}
