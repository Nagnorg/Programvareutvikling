package no.hig.MartinNGlen.GUIRenderer;

import java.io.Serializable;

abstract class ComponentDecorator implements Serializable {

	private static final long serialVersionUID = 1L;
	private BaseComponent baseComponent;
	
	public ComponentDecorator(BaseComponent newComponent){
		baseComponent = newComponent;
	}
	
	public BaseComponent getBaseComponent() {
		  return baseComponent;
		}
	
	public String toString(){
		return baseComponent.toString();
	}
}
