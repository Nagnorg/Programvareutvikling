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
		return baseComponent.stringDefine();
	}
	
	public void contextWindow() {
	
	}
}
